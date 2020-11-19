/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.listener;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.event.player.CorePlayerLoadedEvent;
import eu.mcone.coresystem.api.bukkit.facades.Sound;
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
import org.bukkit.potion.PotionEffect;

import java.util.Random;

public class PlayerJoinListener implements Listener {

    private static final Random SPAWN_RANDOM = new Random();

    @EventHandler(priority = EventPriority.LOWEST)
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        preloadLobbyPlayer(p);

        p.getInventory().setItem(0, HotbarItem.LOADING);
        p.getInventory().setItem(7, HotbarItem.LOADING);
        p.getInventory().setItem(5, null);
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

        Lobby.getSystem().registerLobbyPlayer(lp);

        p.playEffect(p.getLocation(), org.bukkit.Effect.HAPPY_VILLAGER, 5);
        Sound.play(p, org.bukkit.Sound.FIREWORK_TWINKLE);

        loadLobbyPlayer(p, lp, e.getCorePlayerLoadedEvent());
    }

    public static void loadLobbyPlayer(Player p, LobbyPlayer lp, CorePlayerLoadedEvent e) {
        Bukkit.getPluginManager().callEvent(new LobbyPlayerLoadedEvent(lp, e.getLoadReason()));
        WorldRealTimeScheduler.setCurrentRealTime(lp);
        NpcEmoteScheduler.setEmote(p);

        if (e.getLoadReason().equals(CorePlayerLoadedEvent.Reason.JOIN)) {
            switch (lp.getSettings().getSpawnPoint()) {
                case SPAWN: {
                    if (lp.getSettings().getSpawnVillage() == SpawnVillage.RANDOM) {
                        LobbyWorld.ONE_ISLAND.getWorld().teleportSilently(p, SPAWN_RANDOM.nextBoolean() ? "spawn" : "spawn2");
                    } else {
                        LobbyWorld.ONE_ISLAND.getWorld().teleportSilently(
                                p,
                                lp.getSettings().getSpawnVillage().equals(SpawnVillage.RAISEN) ? "spawn" : "spawn2"
                        );
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

        switch (lp.getSettings().getJoinPlayerVisibility()) {
            case PLAYERHIDER: {
                LobbyPlugin.getInstance().getVanishManager().setVanishPlayerVisibility(p, VanishPlayerVisibility.NOBODY);
                break;
            }
            case SILENTLOBBY: {
                LobbyPlugin.getInstance().getVanishManager().joinSilentLobby(p);
            }
        }

        CorePlayer cp = CoreSystem.getInstance().getCorePlayer(p);
        if (lp.getSettings().isScoreboard()) {
            cp.getScoreboard().setNewObjective(new SidebarObjective());
        }
    }

    private static void preloadLobbyPlayer(Player p) {
        p.getInventory().clear();
        p.getInventory().setArmorContents(null);

        p.setGameMode(GameMode.SURVIVAL);
        p.setMaxHealth(20);
        p.setHealth(20);
        p.setLevel(0);
        p.setExp(0);
        p.setFoodLevel(20);
        p.setWalkSpeed(0.2F);
        p.setFlying(false);
        p.setAllowFlight(false);
    }

    private static void postloadLobbyPlayer(Player p, LobbyPlayer lp) {
        for (PotionEffect effect : p.getActivePotionEffects()) {
            p.removePotionEffect(effect.getType());
        }

        LobbyPlugin.getInstance().getHotbarSettings().updateInventory(p, lp);
    }

    public static void resetPlayerDataAndHotbarItems(Player p) {
        preloadLobbyPlayer(p);
        postloadLobbyPlayer(p, LobbyPlugin.getInstance().getLobbyPlayer(p));
    }

}
