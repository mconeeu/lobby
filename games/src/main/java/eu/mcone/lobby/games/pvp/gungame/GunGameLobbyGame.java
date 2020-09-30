/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.games.pvp.gungame;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.world.CoreLocation;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.api.games.pvp.GunGame;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.games.AbstractLobbyPvPGame;
import eu.mcone.lobby.games.listener.GungameListener;
import eu.mcone.lobby.games.scoreboard.GungameObjective;
import org.bukkit.*;
import org.bukkit.entity.Player;

import java.util.*;

public class GunGameLobbyGame extends AbstractLobbyPvPGame implements GunGame {

    private static final Map<Location, Long> SPAWN_LOCATIONS = new HashMap<>();
    private static final ArrayList<Player> SAVE_PLAYER = new ArrayList<>();

    static {
        for (Map.Entry<String, CoreLocation> location : LobbyWorld.GUNGAME.getWorld().getLocations().entrySet()) {
            if (location.getKey().startsWith("Lobby-Gungame-1-")) {
                SPAWN_LOCATIONS.put(location.getValue().bukkit(), (System.currentTimeMillis() / 1000) - 5);
            }
        }
        LobbyPlugin.getInstance().sendConsoleMessage("§2Loaded " + SPAWN_LOCATIONS.size() + " Locations for GunGame");
    }

    public GunGameLobbyGame(LobbyPlugin plugin) {
        super("GunGame", ChatColor.RED, GungameObjective.class, "gungame");

        plugin.registerEvents(new GungameListener(this));
//        plugin.registerCommands(new GunGameTeamCMD());
        plugin.sendConsoleMessage("§aLoading LobbyGame GunGame");
    }

    @Override
    public void onJoin(Player p, LobbyPlayer lp) {
        CoreSystem.getInstance().createActionBar().message("§cTeaming verboten!").send(p);

        p.setExp(1);
        p.setLevel(0);
        p.setGameMode(GameMode.ADVENTURE);
        p.setAllowFlight(false);
        lp.teleportAnimation(getRandomSpawn());
        setSaveMode(p);

        LobbyPlugin.getInstance().getMessenger().send(p, "§7Töte alle §fSpieler §7mit einem §fBlauen Hut§7!");
    }

    @Override
    public void onQuit(Player p, LobbyPlayer lp) {}

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
    public void setSaveMode(Player p) {
        if (isPlaying(p)) {
            setGungameSaveItems(p);
            SAVE_PLAYER.add(p);

            Bukkit.getScheduler().runTaskLater(LobbyPlugin.getInstance(), () -> {
                SAVE_PLAYER.remove(p);

                setFightItems(p);
                LobbyPlugin.getInstance().getPlayerSounds().playSounds(p, Sound.NOTE_PLING);
                LobbyPlugin.getInstance().getMessenger().send(p, "§cDu kannst nun angreifen und angegriffen werden!");
            }, 60);
        }
    }

    @Override
    public boolean isInSaveMode(Player p) {
        return SAVE_PLAYER.contains(p);
    }

    public void setGungameSaveItems(Player p) {
        p.getInventory().clear();
        p.setMaxHealth(20);
        p.setHealth(20);

        p.getInventory().setHelmet(GunGameItem.SAVE_HELMET);
        p.getInventory().setChestplate(GunGameItem.SAVE_CHESTPLATE);
        p.getInventory().setLeggings(GunGameItem.SAVE_LEGGINGS);
        p.getInventory().setBoots(GunGameItem.SAVE_BOOTS);
    }


    public void setFightItems(Player p) {
        Map<Integer, ItemBuilder> items = GungameArmor.getItemsForStreak(p.getLevel());

        p.getActivePotionEffects().clear();
        p.getInventory().setArmorContents(null);
        p.getInventory().clear();

        p.getInventory().setItem(8, GunGameItem.LEAVE_GUNGAME_FIGHTING);
        p.getInventory().setHelmet(GunGameItem.HELMET);

        for (Map.Entry<Integer, ItemBuilder> item : items.entrySet()) {
            p.getInventory().setItem(item.getKey(), item.getValue().unbreakable(true).create());
        }
    }

}
