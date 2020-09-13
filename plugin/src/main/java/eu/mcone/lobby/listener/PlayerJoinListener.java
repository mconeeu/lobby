/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.listener;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.event.CorePlayerLoadedEvent;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.gameapi.api.event.player.GamePlayerLoadedEvent;
import eu.mcone.gameapi.api.player.GamePlayer;
import eu.mcone.lobby.Lobby;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.api.event.LobbyPlayerLoadedEvent;
import eu.mcone.lobby.api.player.HotbarItem;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.api.player.scoreboard.SidebarObjective;
import eu.mcone.lobby.api.player.settings.JoinPlayerVisibility;
import eu.mcone.lobby.api.player.settings.LobbySettings;
import eu.mcone.lobby.api.player.settings.SpawnVillage;
import eu.mcone.lobby.api.player.vanish.VanishPlayerVisibility;
import eu.mcone.lobby.scheduler.NpcEmoteScheduler;
import eu.mcone.lobby.scheduler.WorldRealTimeScheduler;
import eu.mcone.lobby.story.LobbyStory;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Random;

public class PlayerJoinListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        p.setGameMode(GameMode.SURVIVAL);

        preloadLobbyPlayer(p);
        p.getInventory().setItem(0, HotbarItem.LOADING);
        p.getInventory().setItem(7, HotbarItem.LOADING);
        p.getInventory().setItem(8, HotbarItem.LOADING);
    }

    @EventHandler
    public void onGamePlayerLoaded(GamePlayerLoadedEvent e) {
        Player p = e.getBukkitPlayer();
        GamePlayer gp = e.getPlayer();

        if (e.getCorePlayerLoadedEvent().getLoadReason().equals(CorePlayerLoadedEvent.Reason.RELOAD))
            preloadLobbyPlayer(p);

        LobbyPlayer lp = new LobbyPlayer(gp);
        LobbySettings settings = lp.getSettings();

        postloadLobbyPlayer(p, lp);

        if (settings.isRankBoots()) {
            LobbyPlugin.getInstance().getBackpackManager().setRankBoots(p);
        }

        gp.setLastUsedBackPackItemInventar();

        Lobby.getSystem().registerLobbyPlayer(lp);

        p.playEffect(p.getLocation(), org.bukkit.Effect.HAPPY_VILLAGER, 5);
        //   LobbyPlugin.getInstance().getPlayerSounds().lateSounds(p, Sound.FIREWORK_TWINKLE);

        loadLobbyPlayer(p, lp, e.getCorePlayerLoadedEvent());
    }

    public static void loadLobbyPlayer(Player p, LobbyPlayer lp, CorePlayerLoadedEvent e) {
        Bukkit.getPluginManager().callEvent(new LobbyPlayerLoadedEvent(lp, e.getLoadReason()));
        WorldRealTimeScheduler.setCurrentRealTime(lp);
        NpcEmoteScheduler.setEmote(p);

        if (e.getLoadReason().equals(CorePlayerLoadedEvent.Reason.JOIN)) {
            switch (lp.getSettings().getSpawnPoint()) {
                case SPAWN: {
                    if (lp.getSettings().getSpawnVillage().equals(SpawnVillage.RANDOM)) {
                        int spawnlocation = getRandomNumberInRange(1, 3);
                        if (spawnlocation == 1) {
                            LobbyWorld.ONE_ISLAND.getWorld().teleportSilently(p, "spawn2");
                        } else {
                            LobbyWorld.ONE_ISLAND.getWorld().teleportSilently(p, "spawn");
                        }
                    } else {
                        if (lp.getSettings().getSpawnVillage().equals(SpawnVillage.RAISEN)) {
                            LobbyWorld.ONE_ISLAND.getWorld().teleportSilently(p, "spawn");
                        } else if (lp.getSettings().getSpawnVillage().equals(SpawnVillage.SKYLECK)) {
                            LobbyWorld.ONE_ISLAND.getWorld().teleportSilently(p, "spawn2");
                        }
                    }
                    break;
                }
                case OFFICE: {
                    LobbyStory.getInstance().getOfficeManager().joinOffice(p);
                    break;
                }
                case LAST_LOCATION: {
                    if (lp.getCorePlayer().getWorld().equals(LobbyWorld.OFFICE.getWorld())) {
                        LobbyStory.getInstance().getOfficeManager().joinOffice(p);
                    }
                }
            }
        } else {
            p.closeInventory();
        }


        if (lp.getSettings().getJoinPlayerVisibility().equals(JoinPlayerVisibility.SILENTLOBBY)) {
            p.getInventory().setItem(3, null);
        }

        CorePlayer cp = CoreSystem.getInstance().getCorePlayer(p);
        if (lp.getSettings().isScoreboard()) {
            cp.getScoreboard().setNewObjective(new SidebarObjective());
        }
    }

    private static void preloadLobbyPlayer(Player p) {
        p.getInventory().clear();
        p.getInventory().setArmorContents(null);

        p.setMaxHealth(20);
        p.setHealth(20);
        p.setLevel(0);
        p.setExp(0);
        p.setFoodLevel(20);
        p.setWalkSpeed(0.2F);
        p.setFlying(false);
        p.setAllowFlight(false);

        p.getInventory().setItem(1, HotbarItem.LOBBY_CHANGER);
        p.getInventory().setItem(4, HotbarItem.COMPASS);
    }

    private static void postloadLobbyPlayer(Player p, LobbyPlayer lp) {
        CorePlayer cp = CoreSystem.getInstance().getCorePlayer(p);
        p.getActivePotionEffects().clear();

        if (p.hasPermission("mcone.premium")) p.setAllowFlight(true);

        if (p.hasPermission("lobby.silenthub")) {
            if (lp.getSettings().getJoinPlayerVisibility().equals(JoinPlayerVisibility.SILENTLOBBY)) {
                LobbyPlugin.getInstance().getVanishManager().joinSilentLobby(p);
            } else {
                p.getInventory().setItem(2, HotbarItem.SILENT_LOBBY_JOIN);
            }
        } else if (lp.getSettings().getJoinPlayerVisibility().equals(JoinPlayerVisibility.PLAYERHIDER)) {
            LobbyPlugin.getInstance().getVanishManager().setVanishPlayerVisibility(p, VanishPlayerVisibility.NOBODY);
        }

        if (LobbyPlugin.getInstance().getVanishManager().isInSilentLobby(p)) {
            p.getInventory().setItem(0, HotbarItem.LOBBY_HIDER_UNAVAILABLE_SILENT_LOBBY);
        } else {
            p.getInventory().setItem(0, LobbyPlugin.getInstance().getVanishManager().getVanishPlayerVisibility(p).getItem());
        }

        if (p.hasPermission("system.bungee.nick")) {
            p.getInventory().setItem(6, CoreSystem.getInstance().getCorePlayer(p).isNicked() ? HotbarItem.NICK_ENABLED : HotbarItem.NICK_DISABLED);
        }

        p.getInventory().setItem(7, HotbarItem.BACKPACK);


        p.getInventory().setItem(
                8,
                HotbarItem.getProfile(cp.getSkin())
        );

    }

    public static void resetPlayerDataAndHotbarItems(Player p) {
        preloadLobbyPlayer(p);
        postloadLobbyPlayer(p, LobbyPlugin.getInstance().getLobbyPlayer(p));
    }


    private static int getRandomNumberInRange(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

}
