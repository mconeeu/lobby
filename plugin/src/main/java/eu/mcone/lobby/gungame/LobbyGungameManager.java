package eu.mcone.lobby.gungame;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.world.CoreLocation;
import eu.mcone.gameapi.api.GameAPI;
import eu.mcone.lobby.Lobby;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.api.gungame.GungameManager;
import eu.mcone.lobby.api.player.HotbarItems;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.api.player.LobbySettings;
import eu.mcone.lobby.listener.GungameListener;
import eu.mcone.lobby.listener.PlayerJoinListener;
import eu.mcone.lobby.scoreboard.GungameObjective;
import lombok.Getter;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

import java.util.*;

public class LobbyGungameManager implements GungameManager {


    private static final Map<Location, Long> SPAWN_LOCATIONS = new HashMap<>();

    static {
        for (Map.Entry<String, CoreLocation> location : LobbyWorld.GUNGAME.getWorld().getLocations().entrySet()) {
            if (location.getKey().startsWith("gungame-1-")) {
                SPAWN_LOCATIONS.put(location.getValue().bukkit(), (System.currentTimeMillis() / 1000) - 5);
            }
        }
    }

    @Getter
    private final Set<Player> fighting;
    private static final ArrayList<Player> savePlayer = new ArrayList<>();


    public LobbyGungameManager(Lobby plugin) {
        plugin.registerEvents(new GungameListener(this));
        this.fighting = new HashSet<>();
    }


    @Override
    public void setStart(Player p) {
        if (LobbyPlugin.getInstance().getPlayerHiderManager().isHidden(p)) {
            LobbyPlugin.getInstance().getPlayerHiderManager().showPlayers(p);
        }
        if (LobbyPlugin.getInstance().getSilentLobbyManager().isActivatedSilentHub(p)) {
            LobbyPlugin.getInstance().getSilentLobbyManager().deactivateSilentLobby(p);
        }
        if (!fighting.contains(p)) {
            GameAPI.getInstance().getGamePlayer(p).setEffectsVisible(false);
            CoreSystem.getInstance().getCorePlayer(p.getUniqueId()).getScoreboard().setNewObjective(new GungameObjective(this));

            CoreSystem.getInstance().getLabyModAPI().setCurrentServer(p, "MCONE-Gungame");

            p.setExp(1);
            p.setLevel(0);
            fighting.add(p);
            p.setGameMode(GameMode.ADVENTURE);
            p.setAllowFlight(false);
            LobbyPlugin.getInstance().getLobbyPlayer(p).teleportAnimation(getRandomSpawn());

            addSave(p);

            if (fighting.size() <= 1) {
                LobbyPlugin.getInstance().getMessenger().send(p, "§7Du bist gerade der §f§oeinzigste§7, der §fGungame§7 spielt!");
                CoreSystem.getInstance().getCorePlayer(p).getScoreboard().getObjective(DisplaySlot.SIDEBAR).reload();
            } else {
                LobbyPlugin.getInstance().getMessenger().send(p, "§7Es spielen gerade §f§o" + fighting.size() + "§7 Spieler §fGungame§7!");
                for (Player all : Bukkit.getOnlinePlayers()) {
                    if (fighting.contains(all))
                        CoreSystem.getInstance().getCorePlayer(all).getScoreboard().getObjective(DisplaySlot.SIDEBAR).reload();
                }

                LobbyPlugin.getInstance().getMessenger().send(p, "§7Töte alle §fSpieler §7mit einem §fBlauen Hut§7!");

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
            GameAPI.getInstance().getGamePlayer(p).setEffectsVisible(true);

            LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);
            LobbySettings settings = lp.getSettings();
            if (settings.isRankBoots()) {
                LobbyPlugin.getInstance().getBackpackManager().setRankBoots(p);
            }

            CoreSystem.getInstance().getLabyModAPI().setCurrentServer(p, "MCONE-Lobby");

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

    @Override
    public void addSave(Player p) {
        setGungameSaveItems(p);
        savePlayer.add(p);

        if (p.getLevel() != 0) {
            if (p.getLevel() <= 5) {
                p.setLevel(p.getLevel() - 1);
            } else if (p.getLevel() <= 12) {
                p.setLevel(p.getLevel() - 4);
            } else if (p.getLevel() <= 17) {
                p.setLevel(p.getLevel() - 6);
            } else if (p.getLevel() <= 23) {
                p.setLevel(p.getLevel() - 8);
            } else if (p.getLevel() <= 29) {
                p.setLevel(p.getLevel() - 10);
            } else if (p.getLevel() <= 35) {
                p.setLevel(p.getLevel() - 14);
            } else if (p.getLevel() <= 41) {
                p.setLevel(p.getLevel() - 18);
            } else if (p.getLevel() <= 50) {
                p.setLevel(p.getLevel() - 25);
            } else if (p.getLevel() <= 60) {
                p.setLevel(p.getLevel() - 29);
            }
        }

        CoreSystem.getInstance().getCorePlayer(p).getScoreboard().getObjective(DisplaySlot.SIDEBAR).reload();


        Bukkit.getScheduler().runTaskLater(Lobby.getSystem(), () -> {
            if (fighting.contains(p)) {
                updateGungameFightItems(p);
                savePlayer.remove(p);
                p.playSound(p.getLocation(), Sound.NOTE_PLING, 1, 1);
                LobbyPlugin.getInstance().getMessenger().send(p, "§cDu kannst nun angreifen und angegriffen werden!");
            } else {
                savePlayer.remove(p);
            }
        }, 60);
    }

    @Override
    public boolean isSave(Player p) {
        return savePlayer.contains(p);
    }

    public void setGungameSaveItems(Player p) {
        p.getInventory().clear();
        p.setMaxHealth(20);
        p.setHealth(20);

        p.getInventory().setHelmet(ItemBuilder.createLeatherArmorItem(Material.LEATHER_HELMET, Color.BLUE).create());
        p.getInventory().setChestplate(ItemBuilder.createLeatherArmorItem(Material.LEATHER_CHESTPLATE, Color.RED).create());
        p.getInventory().setLeggings(ItemBuilder.createLeatherArmorItem(Material.LEATHER_LEGGINGS, Color.RED).create());
        p.getInventory().setBoots(ItemBuilder.createLeatherArmorItem(Material.LEATHER_BOOTS, Color.RED).create());

    }


    public void updateGungameFightItems(Player p) {
        Map<Integer, ItemBuilder> items = GungameArmor.getItemsForStreak(p.getLevel());


        p.getActivePotionEffects().clear();
        p.getInventory().setArmorContents(null);
        p.getInventory().clear();

        p.getInventory().setItem(8, HotbarItems.LEAVE_GUNGAME_FIGHTING);
        p.getInventory().setHelmet(ItemBuilder.createLeatherArmorItem(Material.LEATHER_HELMET, Color.BLUE).create());

        for (Map.Entry<Integer, ItemBuilder> item : items.entrySet()) {
            p.getInventory().setItem(item.getKey(), item.getValue().unbreakable(true).create());
        }


    }

    public void playerLeaved(Player p) {
        fighting.remove(p);
    }
}
