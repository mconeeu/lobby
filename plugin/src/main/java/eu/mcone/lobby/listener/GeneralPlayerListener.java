/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.listener;

import eu.mcone.gamesystem.api.enums.Item;
import eu.mcone.gamesystem.api.game.player.GamePlayer;
import eu.mcone.lobby.Lobby;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.enums.BankProgress;
import eu.mcone.lobby.api.event.LobbyPlayerLoadedEvent;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.inventory.InteractionInventory;
import eu.mcone.lobby.items.listener.effects.SplashPotionListener;
import eu.mcone.lobby.story.inventory.john.JohnBankRobberyInventory;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

public class GeneralPlayerListener implements Listener {

    @EventHandler
    public void onLobbyPlayerLoaded(LobbyPlayerLoadedEvent e) {
        LobbyPlayer p = e.getPlayer();
        Player bp = p.bukkit();

        bp.removePotionEffect(PotionEffectType.BLINDNESS);
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
        Player p = e.getPlayer();

        if (SplashPotionListener.Splahpotioneffect.contains(p)) {
            SplashPotionListener.Splahpotioneffect.remove(p);
        }
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(e.getPlayer().getUniqueId());
        GamePlayer gamePlayer = LobbyPlugin.getInstance().getGamePlayer(e.getPlayer().getUniqueId());

        if (lp.getBankprogressId() == BankProgress.BANK_ROBBERY_MIDDLE.getId()) {
            lp.setBankProgress(BankProgress.BANK_ROBBERY_START);
            JohnBankRobberyInventory.currentlyInBank = null;
            gamePlayer.removeItem(Item.GOLD_BARDING);

        }
        lp.saveData();
        LobbyPlugin.getInstance().unregisterLobbyPlayer(lp);
    }

    @EventHandler
    public void onItemConsume(PlayerItemConsumeEvent e) {
        ItemStack consumed = e.getItem();
        if (consumed.getType().equals(Material.POTION)) {
            e.setCancelled(true);

        }
    }
}
