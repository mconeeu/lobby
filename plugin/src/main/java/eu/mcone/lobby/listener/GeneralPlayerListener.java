/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.listener;

import eu.mcone.gamesystem.api.enums.Item;
import eu.mcone.gamesystem.api.game.player.GamePlayer;
import eu.mcone.lobby.Lobby;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.api.event.LobbyPlayerLoadedEvent;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.inventory.InteractionInventory;
import eu.mcone.lobby.items.manager.OfficeManager;
import eu.mcone.lobby.util.PlayerSpawnLocation;
import eu.mcone.lobby.util.SilentLobbyUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.potion.PotionEffectType;

public class GeneralPlayerListener implements Listener {

    @EventHandler
    public void onLobbyPlayerLoaded(LobbyPlayerLoadedEvent e) {
        LobbyPlayer p = e.getPlayer();
        Player bp = p.bukkit();
        GamePlayer gamePlayer = LobbyPlugin.getInstance().getGamePlayer(bp.getUniqueId());

        bp.removePotionEffect(PotionEffectType.BLINDNESS);

//        if (!gamePlayer.hasItem(Item.BANKCARD_PREMIUM)) {
//            if (bp.hasPermission("mcone.premium")) {
//                if (!gamePlayer.hasItem(Item.BANKCARD)) {
//                    gamePlayer.addItem(Item.BANKCARD_PREMIUM);
//                } else {
//                    gamePlayer.removeItem(Item.BANKCARD);
//                    gamePlayer.addItem(Item.BANKCARD_PREMIUM);
//                }
//
//            }
//        }

        if (e.getReason().equals(LobbyPlayerLoadedEvent.Reason.JOINED)) {
            if (!p.getSettings().getSpawnLocation().equalsIgnoreCase(PlayerSpawnLocation.LAST_LOGIN.toString())) {
                Bukkit.getScheduler().runTask(LobbyPlugin.getInstance(), () -> {
                    if (p.getSettings().isSpawnInSilentLobby()) {
                        SilentLobbyUtils.activateSilentLobby(bp);
                        LobbyPlugin.getInstance().getMessager().send(bp, "§2Du bist in der §aPrivaten Lobby§2 gespawnt. Hier bist du vollkommen ungestört!");
                    }

                    if (p.getSettings().getSpawnLocation().equalsIgnoreCase(PlayerSpawnLocation.SPAWN.toString())) {
                        PlayerSpawnLocation.SPAWN.getWorld().teleportSilently(bp, "spawn");
                    } else if (p.getSettings().getSpawnLocation().equalsIgnoreCase(PlayerSpawnLocation.OFFICE.toString())) {
                        if (gamePlayer.hasItem(Item.OFFICE_CARD_BRONZE)) {
                            LobbyWorld.OFFICE.getWorld().teleportSilently(bp, OfficeManager.OfficeType.BRONZE_OFFICE.getSpawnLocation());
                        } else if (gamePlayer.hasItem(Item.OFFICE_CARD_SILVER)) {
                            LobbyWorld.OFFICE.getWorld().teleportSilently(bp, OfficeManager.OfficeType.SILVER_OFFICE.getSpawnLocation());
                        } else if (gamePlayer.hasItem(Item.OFFICE_CARD_GOLD)) {
                            LobbyWorld.OFFICE.getWorld().teleportSilently(bp, OfficeManager.OfficeType.GOLD_OFFICE.getSpawnLocation());
                        }
                    }
                });
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getWhoClicked() instanceof Player) {
            Player p = (Player) e.getWhoClicked();
            if (!Lobby.getInstance().getBuildSystem().hasBuildModeEnabled(p)) e.setCancelled(true);
        }
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent e) {
        if (e.getEntity() instanceof Player) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPickupItem(PlayerPickupItemEvent e) {
        Player p = e.getPlayer();

        if (p.getGameMode() == GameMode.CREATIVE) {
            e.setCancelled(false);
        } else {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onInteractEntity(PlayerInteractEntityEvent e) {
        Player p = e.getPlayer();

        if (e.getRightClicked() instanceof Player) {
            Player clicked = (Player) e.getRightClicked();
            new InteractionInventory(p, clicked);
        }
    }

    @EventHandler
    public void onDropItem(PlayerDropItemEvent e) {
        Player p = e.getPlayer();

        if (p.getGameMode() == GameMode.CREATIVE) {
            e.setCancelled(false);
        } else {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();

        e.setKeepInventory(true);
        e.setKeepLevel(true);
        e.setDeathMessage("");
        p.spigot().respawn();
    }

    @EventHandler
    public void onAchievementAwarded(PlayerAchievementAwardedEvent e) {
        e.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onQuit(PlayerQuitEvent e) {
        e.setQuitMessage(null);

        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(e.getPlayer().getUniqueId());
        lp.saveData();

        LobbyPlugin.getInstance().unregisterLobbyPlayer(lp);
    }

}
