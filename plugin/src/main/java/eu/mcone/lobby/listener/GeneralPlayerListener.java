/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.listener;

import eu.mcone.lobby.Lobby;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.enums.BankProgress;
import eu.mcone.lobby.api.enums.LobbyItem;
import eu.mcone.lobby.api.event.LobbyPlayerLoadedEvent;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.story.inventory.john.JohnBankRobberyInventory;
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
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;

public class GeneralPlayerListener implements Listener {
    private Plugin plugin;

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
    public void onDropItem(PlayerDropItemEvent e) {
        Player p = e.getPlayer();

        if (p.getGameMode() == GameMode.CREATIVE) {
            e.setCancelled(false);
        } else {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onAchievementAwarded(PlayerAchievementAwardedEvent e) {
        e.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onQuit(PlayerQuitEvent e) {
        e.setQuitMessage(null);
        LobbyPlayer lp = LobbyPlugin.getInstance().getGamePlayer(e.getPlayer().getUniqueId());

        if (lp.getBankprogressId() == BankProgress.BANK_ROBBERY_MIDDLE.getId()) {
            lp.setBankProgress(BankProgress.BANK_ROBBERY_START);
            JohnBankRobberyInventory.currentlyInBank = null;
            LobbyItem.GOLD_BARDING.remove(lp);

        }
        lp.saveData();
        LobbyPlugin.getInstance().unregisterGamePlayer(lp);
    }

    @EventHandler
    public void onItemConsume(PlayerItemConsumeEvent e) {
        ItemStack consumed = e.getItem();
        if (consumed.getType().equals(Material.POTION)) {
            e.setCancelled(true);

        }
    }
}
