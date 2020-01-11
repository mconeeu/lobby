/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.listener;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.event.CorePlayerLoadedEvent;
import eu.mcone.coresystem.api.bukkit.event.LabyModPlayerJoinEvent;
import eu.mcone.coresystem.api.bukkit.gamemode.Gamemode;
import eu.mcone.coresystem.api.bukkit.item.Skull;
import eu.mcone.coresystem.api.bukkit.npc.NPC;
import eu.mcone.coresystem.api.bukkit.npc.entity.PlayerNpc;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.coresystem.api.core.labymod.LabyModEmote;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.api.enums.LobbyItem;
import eu.mcone.lobby.api.event.LobbyPlayerLoadedEvent;
import eu.mcone.lobby.api.player.HotbarItems;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.items.manager.OfficeManager;
import eu.mcone.lobby.util.PlayerHiderManager;
import eu.mcone.lobby.scoreboard.SidebarObjective;
import eu.mcone.lobby.util.RealTimeUtil;
import eu.mcone.lobby.util.SilentLobbyManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        preloadLobbyPlayer(p);
        p.getInventory().setItem(0, HotbarItems.LOADING);
        p.getInventory().setItem(7, HotbarItems.LOADING);
        p.getInventory().setItem(8, HotbarItems.LOADING);
    }

    @EventHandler
    public void onCorePlayerLoaded(CorePlayerLoadedEvent e) {
        Player p = e.getBukkitPlayer();
        CorePlayer cp = e.getPlayer();

        if (e.getLoadReason().equals(CorePlayerLoadedEvent.Reason.RELOAD)) preloadLobbyPlayer(p);
        postloadLobbyPlayer(p);

        LobbyPlayer lp = new LobbyPlayer(cp);
        LobbyPlugin.getInstance().registerGamePlayer(lp);

        LobbyPlugin.getInstance().getPlayerHiderManager().playerJoined(p);

        p.playEffect(p.getLocation(), org.bukkit.Effect.HAPPY_VILLAGER, 5);
        p.playSound(p.getLocation(), Sound.FIREWORK_TWINKLE, 2.0F, 5.0F);

        loadLobbyPlayer(p, lp, e);
    }

    public static void loadLobbyPlayer(Player p, LobbyPlayer lp, CorePlayerLoadedEvent e) {
        Bukkit.getPluginManager().callEvent(new LobbyPlayerLoadedEvent(lp, e.getLoadReason()));
        RealTimeUtil.setCurrentRealTime(lp);

        if (e.getLoadReason().equals(CorePlayerLoadedEvent.Reason.JOIN)) {
            if (p.hasPermission("lobby.silenthub") && lp.getSettings().isSpawnInSilentLobby()) {
                e.setHidden(true);
                LobbyPlugin.getInstance().getSilentLobbyManager().activateSilentLobby(p);
                LobbyPlugin.getInstance().getMessager().send(p, "§2Du bist in der §aPrivaten Lobby§2 gespawnt. Hier bist du vollkommen ungestört!");
            }

            switch (lp.getSettings().getSpawnPoint()) {
                case SPAWN: {
                    LobbyWorld.ONE_ISLAND.getWorld().teleportSilently(p, "spawn");
                    break;
                }
                case OFFICE: {
                    if (LobbyItem.OFFICE_CARD_BRONZE.has(lp)) {
                        LobbyWorld.OFFICE.getWorld().teleportSilently(p, OfficeManager.OfficeType.BRONZE_OFFICE.getSpawnLocation());
                    } else if (LobbyItem.OFFICE_CARD_SILVER.has(lp)) {
                        LobbyWorld.OFFICE.getWorld().teleportSilently(p, OfficeManager.OfficeType.SILVER_OFFICE.getSpawnLocation());
                    } else if (LobbyItem.OFFICE_CARD_GOLD.has(lp)) {
                        LobbyWorld.OFFICE.getWorld().teleportSilently(p, OfficeManager.OfficeType.GOLD_OFFICE.getSpawnLocation());
                    }
                }
            }
        }

        if (!LobbyItem.BANKCARD_PREMIUM.has(lp)) {
            if (p.hasPermission("mcone.premium")) {
                if (!LobbyItem.BANKCARD.has(lp)) {
                    LobbyItem.BANKCARD_PREMIUM.add(lp);
                } else {
                    LobbyItem.BANKCARD.remove(lp);
                    LobbyItem.BANKCARD_PREMIUM.add(lp);
                }

            } else {
                if (LobbyItem.BANKCARD_PREMIUM.has(lp)) {
                    LobbyItem.BANKCARD_PREMIUM.remove(lp);
                }
            }
        } else {
            if (p.hasPermission("mcone.premium")) {
                if (LobbyItem.BANKCARD.has(lp)) {
                    LobbyItem.BANKCARD.remove(lp);
                }
            }
        }
    }

    @EventHandler
    public void on(LabyModPlayerJoinEvent e) {
        for (Gamemode gm : Gamemode.values()) {
            NPC npc = LobbyWorld.ONE_ISLAND.getWorld().getNPC(gm.getName().toLowerCase());

            if (npc != null) {
                ((PlayerNpc) npc).playLabymodEmote(LabyModEmote.T_POSE, e.getPlayer());
            }
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

        p.getInventory().setItem(1, HotbarItems.LOBBY_CHANGER);
        p.getInventory().setItem(4, HotbarItems.COMPASS);
    }

    private static void postloadLobbyPlayer(Player p) {
        p.getActivePotionEffects().clear();

        if (p.hasPermission("mcone.premium")) p.setAllowFlight(true);

        if (LobbyPlugin.getInstance().getSilentLobbyManager().isActivatedSilentHub(p)) {
            p.getInventory().setItem(0, HotbarItems.LOBBY_HIDER_UNAVAILABLE);
        } else if (LobbyPlugin.getInstance().getPlayerHiderManager().isHidden(p)) {
            p.getInventory().setItem(0, HotbarItems.SHOW_PLAYERS);
        } else {
            p.getInventory().setItem(0, HotbarItems.HIDE_PLAYERS);
        }

        if (p.hasPermission("system.bungee.nick")) {
            p.getInventory().setItem(6, CoreSystem.getInstance().getCorePlayer(p).isNicked() ? HotbarItems.DEACTIVATE_NICK : HotbarItems.ACTIVATE_NICK);
        }

        p.getInventory().setItem(7, HotbarItems.BACKPACK);

        if (p.hasPermission("lobby.silenthub")) {
            p.getInventory().setItem(2, HotbarItems.PRIVATE_LOBBY);
        }

        p.getInventory().setItem(8, new Skull(p.getName(), 1).toItemBuilder().displayName("§3§lProfil §8» §7§oEinstellungen / Stats / Freunde").create());

        LobbyPlugin.getInstance().getBackpackManager().setRankBoots(p);
        CoreSystem.getInstance().getCorePlayer(p.getUniqueId()).getScoreboard().setNewObjective(new SidebarObjective());
    }

    public static void setLobbyItems(Player p) {
        preloadLobbyPlayer(p);
        postloadLobbyPlayer(p);
    }

}
