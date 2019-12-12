package eu.mcone.lobby.onehit;

import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.lobby.Lobby;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.api.onehit.OneHitManager;
import eu.mcone.lobby.api.player.HotbarItems;
import eu.mcone.lobby.listener.OneHitListener;
import eu.mcone.lobby.listener.PlayerJoinListener;
import eu.mcone.lobby.util.PlayerHider;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LobbyOneHitManager implements OneHitManager {

    private static final List<String> SPAWN_LOCATIONS = new ArrayList<>();
    static {
        for (int i = 1; i <= 6; i++) {
            SPAWN_LOCATIONS.add("Onehit-"+i);
        }
    }
    public final ArrayList<Player> fighting;

    public LobbyOneHitManager(Lobby plugin) {
        plugin.registerEvents(new OneHitListener(this));
        this.fighting = new ArrayList<>();
    }

    @Override
    public void setStart(Player p) {
        if (!fighting.contains(p)) {
            PlayerHider.showPlayers(p);
            setOneHitFightItems(p);
            fighting.add(p);

            teleportToRandomSpawn(p);

            LobbyPlugin.getInstance().getMessager().send(p, "§cTöte alle Spieler mit einen roten Helm!");
            LobbyPlugin.getInstance().getBackpackManager().getPetHandler().despawnPet(p);
        }
    }

    @Override
    public void leave(Player p) {
        if (fighting.contains(p)) {
            fighting.remove(p);
            p.getInventory().clear();
            LobbyWorld.ONE_ISLAND.getWorld().teleportSilently(p, "spawn");
            PlayerJoinListener.setLobbyItems(p);
        }
    }

    @Override
    public boolean isFighting(Player p) {
        return fighting.contains(p);
    }

    public void teleportToRandomSpawn(Player p) {
        LobbyWorld.ONE_ISLAND.getWorld().teleportSilently(p, SPAWN_LOCATIONS.get(new Random().nextInt(SPAWN_LOCATIONS.size()-1)));
    }

    public void setOneHitFightItems(Player p) {
        p.getInventory().clear();
        p.getActivePotionEffects().clear();

        p.setMaxHealth(2);
        p.setHealth(2);

        p.getInventory().setHelmet(ItemBuilder.createLeatherArmorItem(Material.LEATHER_HELMET, Color.RED).create());

        p.getInventory().setItem(0, HotbarItems.ONEHIT_SWORD);
        p.getInventory().setItem(1, HotbarItems.ONEHIT_BOW);
        p.getInventory().setItem(7, HotbarItems.ONEHIT_ARROW);
        p.getInventory().setItem(8, HotbarItems.LEAVE_ONEHIT_FIGHTING);
    }

    public void playerLeaved(Player p) {
        fighting.remove(p);
    }

}






