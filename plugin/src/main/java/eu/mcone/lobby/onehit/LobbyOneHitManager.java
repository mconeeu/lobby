package eu.mcone.lobby.onehit;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.world.CoreLocation;
import eu.mcone.lobby.Lobby;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.api.enums.LobbyItem;
import eu.mcone.lobby.api.onehit.OneHitManager;
import eu.mcone.lobby.api.player.HotbarItems;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.listener.OneHitListener;
import eu.mcone.lobby.listener.PlayerJoinListener;
import eu.mcone.lobby.scoreboard.OneHitObjective;
import lombok.Getter;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

import java.util.*;

public class LobbyOneHitManager implements OneHitManager {

    private static final Map<Location, Long> SPAWN_LOCATIONS = new HashMap<>();

    static {
        for (Map.Entry<String, CoreLocation> location : LobbyWorld.ONE_ISLAND.getWorld().getLocations().entrySet()) {
            if (location.getKey().startsWith("Onehit-")) {
                SPAWN_LOCATIONS.put(location.getValue().bukkit(), (System.currentTimeMillis() / 1000) - 5);
            }
        }
    }

    @Getter
    private final ArrayList<Player> fighting;

    public LobbyOneHitManager(Lobby plugin) {
        plugin.registerEvents(new OneHitListener(this));
        this.fighting = new ArrayList<>();
    }

    @Override
    public void setStart(Player p) {
        if (!fighting.contains(p)) {

            CoreSystem.getInstance().getCorePlayer(p.getUniqueId()).getScoreboard().setNewObjective(new OneHitObjective(this));

            p.setExp(1);
            p.setLevel(0);
            setOneHitFightItems(p);
            fighting.add(p);
            p.setGameMode(GameMode.ADVENTURE);
            p.setAllowFlight(false);
            p.teleport(getRandomSpawn());

            if (fighting.size() <= 1) {
                LobbyPlugin.getInstance().getMessager().send(p, "§7Du bist gerade der §f§oeinzigste§7, der §fOneHit§7 spielt!");
                CoreSystem.getInstance().getCorePlayer(p).getScoreboard().getObjective(DisplaySlot.SIDEBAR).reload();
            } else {
                LobbyPlugin.getInstance().getMessager().send(p, "§7Es spielen gerade §f§o" + fighting.size() + "§7 Spieler §fOneHit§7!");
                for (Player all : Bukkit.getOnlinePlayers()) {
                    if (fighting.contains(all))
                        CoreSystem.getInstance().getCorePlayer(all).getScoreboard().getObjective(DisplaySlot.SIDEBAR).reload();
                }

            }
            LobbyPlugin.getInstance().getBackpackManager().getPetHandler().despawnPet(p);
        }
    }

    @Override
    public void leave(Player p) {
        if (fighting.contains(p)) {
            fighting.remove(p);
            p.getInventory().clear();
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1, 1);
            LobbyWorld.ONE_ISLAND.getWorld().teleportSilently(p, "spawn");
            PlayerJoinListener.setLobbyItems(p);
            p.getActivePotionEffects().clear();
            p.setLevel(0);
            p.setExp(0);

            for (Player player : fighting) {
                CoreSystem.getInstance().getCorePlayer(player).getScoreboard().getObjective(DisplaySlot.SIDEBAR).reload();
            }
        }
    }

    @Override
    public boolean isFighting(Player p) {
        return fighting.contains(p);
    }

    public Location getRandomSpawn() {
        List<Location> locations = new ArrayList<>();

        for (Map.Entry<Location, Long> location : SPAWN_LOCATIONS.entrySet()) {
            if (((System.currentTimeMillis() / 1000) - location.getValue()) > 3) {
                locations.add(location.getKey());
            }
        }

        Location location = locations.get(new Random().nextInt(locations.size() - 1));
        SPAWN_LOCATIONS.put(location, System.currentTimeMillis() / 1000);

        return location;
    }

    public void setOneHitFightItems(Player p) {
        p.getInventory().clear();
        p.getActivePotionEffects().clear();

        p.setMaxHealth(2);
        p.setHealth(2);

        p.getInventory().setHelmet(ItemBuilder.createLeatherArmorItem(Material.LEATHER_HELMET, Color.RED).create());

        LobbyPlayer lp = LobbyPlugin.getInstance().getGamePlayer(p);

        if (LobbyItem.ONE_HIT_SWORD.has(lp)) {
            p.getInventory().setItem(0, HotbarItems.STORY_ONEHIT_SWORD);
        } else {
            p.getInventory().setItem(0, HotbarItems.ONEHIT_SWORD);
        }

        p.getInventory().setItem(7, HotbarItems.ONEHIT_GADGET);
        p.getInventory().setItem(1, HotbarItems.ONEHIT_BOW);
        p.getInventory().setItem(6, HotbarItems.ONEHIT_ARROW);
        p.getInventory().setItem(8, HotbarItems.LEAVE_ONEHIT_FIGHTING);
    }

    public void playerLeaved(Player p) {
        fighting.remove(p);
    }

}






