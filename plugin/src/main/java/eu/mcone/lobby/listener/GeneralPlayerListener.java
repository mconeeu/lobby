/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.listener;

import eu.mcone.coresystem.api.bukkit.event.player.LanguageChangeEvent;
import eu.mcone.coresystem.api.bukkit.facades.Sound;
import eu.mcone.gameapi.api.event.backpack.BackpackItemRemoveEvent;
import eu.mcone.lobby.Lobby;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.games.LobbyGames;
import eu.mcone.lobby.inventory.InteractionInventory;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;

public class GeneralPlayerListener implements Listener {

    @EventHandler
    public void on(BackpackItemRemoveEvent e) {
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(e.getPlayer().bukkit());

        if (lp != null) {
            e.setApplyRankBoots(lp.getSettings().isRankBoots());
        }
    }

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent e) {
        e.setCancelled(!e.getWhoClicked().getGameMode().equals(GameMode.CREATIVE));
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent e) {
        if (e.getEntity() instanceof Player) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPickupItem(PlayerPickupItemEvent e) {
        e.setCancelled(
                !Lobby.getSystem().getBuildSystem().hasBuildModeEnabled(e.getPlayer())
        );
    }

    @EventHandler
    public void onDropItem(PlayerDropItemEvent e) {
        Player p = e.getPlayer();
        e.setCancelled(!p.getGameMode().equals(GameMode.CREATIVE));
    }

    @EventHandler
    public void onAchievementAwarded(PlayerAchievementAwardedEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onBedEnter(PlayerBedEnterEvent e) {
        Player p = e.getPlayer();

        LobbyPlugin.getInstance().getMessenger().sendError(p, "Du darfst in diesem ![Bett] nicht schlafen!");
        p.setSleepingIgnored(false);
        e.setCancelled(true);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        e.setQuitMessage(null);

        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(e.getPlayer().getUniqueId());
        lp.saveData();
        Lobby.getSystem().unregisterLobbyPlayer(lp);
    }

    @EventHandler
    public void onItemConsume(PlayerItemConsumeEvent e) {
        ItemStack consumed = e.getItem();

        if (consumed.getType().equals(Material.POTION)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void on(PlayerItemHeldEvent e) {
        Player player = e.getPlayer();
        LobbyPlayer lobbyPlayer = LobbyPlugin.getInstance().getLobbyPlayer(player);

        if (lobbyPlayer != null) {
            if (lobbyPlayer.getSettings().isHotbarChangeSound()) {
                Sound.play(player, org.bukkit.Sound.ITEM_PICKUP);
            }
        }
    }

    @EventHandler
    public void on(LanguageChangeEvent e) {
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(e.getPlayer().bukkit());
        LobbyPlugin.getInstance().getHotbarSettings().updateInventory(e.getPlayer().bukkit(), lp);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInteractEntity(PlayerInteractEntityEvent e) {
        Player p = e.getPlayer();

        if (e.getRightClicked() instanceof Player) {
            Player clicked = (Player) e.getRightClicked();

            if (!LobbyGames.getInstance().isPlaying(p)) {
                new InteractionInventory(p, clicked);
            }
        }
    }

}
