/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.listener;

import eu.mcone.lobby.Lobby;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.event.LobbyPlayerLoadedEvent;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.inventory.InteractionInventory;
import eu.mcone.lobby.util.SilentLobbyUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;

public class GeneralPlayerListener implements Listener {

    @EventHandler
    public void onLobbyPlayerLoaded(LobbyPlayerLoadedEvent e) {
        LobbyPlayer p = e.getPlayer();
        Player bp = p.bukkit();

        if (e.getReason().equals(LobbyPlayerLoadedEvent.Reason.JOINED) && p.getSettings().isSilentHubActivatedOnJoin()) {
            Bukkit.getScheduler().runTask(LobbyPlugin.getInstance(), () -> SilentLobbyUtils.activateSilentLobby(bp));
            LobbyPlugin.getInstance().getMessager().send(bp, "§2Du bist in der §aPrivaten Lobby§2 gespawnt. Hier bist du vollkommen ungestört!");
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
    public void onDropItem(PlayerDropItemEvent e){
        Player p = e.getPlayer();

        if (p.getGameMode() == GameMode.CREATIVE){
            e.setCancelled(false);
        } else {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e){
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

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        e.setQuitMessage(null);

        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(e.getPlayer().getUniqueId());
        lp.saveData();
        LobbyPlugin.getInstance().unregisterLobbyPlayer(lp);
    }

}
