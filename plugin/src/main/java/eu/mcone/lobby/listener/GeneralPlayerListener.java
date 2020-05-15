/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.listener;

import eu.mcone.coresystem.api.bukkit.event.AfkEvent;
import eu.mcone.coresystem.api.core.player.PlayerState;
import eu.mcone.lobby.Lobby;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.api.enums.bank.BankRobberySmallProgress;
import eu.mcone.lobby.api.enums.LobbyItem;
import eu.mcone.lobby.api.event.LobbyPlayerLoadedEvent;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.inventory.InteractionInventory;
import eu.mcone.lobby.items.inventory.office.secretary.SecretaryInventory;
import eu.mcone.lobby.items.manager.OfficeManager;
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
    public void onAFK(AfkEvent e) {
        Player p = e.getPlayer();

        if (LobbyPlugin.getInstance().getOneHitManager().isFighting(p) || LobbyPlugin.getInstance().getJumpNRunManager().isJumping(p) || LobbyPlugin.getInstance().getCatchManager().isCatching(p) || LobbyPlugin.getInstance().getGungameManager().isFighting(p)) {
            if (e.getState().equals(PlayerState.AFK)) {
                LobbyPlugin.getInstance().getJumpNRunManager().setCancel(p);
                LobbyPlugin.getInstance().getOneHitManager().leave(p);
                LobbyPlugin.getInstance().getCatchManager().leave(p);
                LobbyPlugin.getInstance().getGungameManager().leave(p);
                LobbyPlugin.getInstance().getMessenger().send(p, "§4Du wurdest automatisch von deiner Lobby Aktivität gekickt!");
            }
        }
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(e.getPlayer().getUniqueId());
        if (lp.getBankprogressId() == BankRobberySmallProgress.BANK_ROBBERY_MIDDLE.getId()) {
            lp.setBankProgress(BankRobberySmallProgress.BANK_ROBBERY_START);
            LobbyPlugin.getInstance().getMessenger().send(p, "§4Der Banküberfall ist gescheitert!");
            LobbyWorld.ONE_ISLAND.getWorld().teleportSilently(p, "office-entrance");
            JohnBankRobberyInventory.currentlyInBank = null;
            if (lp.hasLobbyItem(LobbyItem.GOLD_BARDING)) {
                lp.removeLobbyItem(LobbyItem.GOLD_BARDING);
            }
        }
    }


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
            if (!Lobby.getSystem().getBuildSystem().hasBuildModeEnabled(p)) e.setCancelled(true);
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

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        if (LobbyPlugin.getInstance().getSilentLobbyManager().isActivatedSilentHub(e.getPlayer())) {
            LobbyPlugin.getInstance().getSilentLobbyManager().deactivateSilentLobby(e.getPlayer());
        }
        e.setQuitMessage(null);
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(e.getPlayer().getUniqueId());

        if (LobbyPlugin.getInstance().getJumpNRunManager().isJumping(e.getPlayer())) {
            LobbyPlugin.getInstance().getJumpNRunManager().setCancel(e.getPlayer());
        }


        OfficeManager.quitOffice(player);

        if (SecretaryInventory.isInviting.contains(player)) {
            SecretaryInventory.isInviting.remove(player);
            LobbyPlugin.getInstance().getMessenger().send(player, "§4Du hast das Büro verlassen dadurch wurde dein Einladungslink gelöscht!");
        }

        if (OfficeManager.ISTOGETHEROFFICE.contains(player)) {
            OfficeManager.ISTOGETHEROFFICE.remove(player);

            for (Player all : OfficeManager.ISTOGETHEROFFICE) {
                all.hidePlayer(player);
            }

        }

        if (lp.getBankprogressId() == BankRobberySmallProgress.BANK_ROBBERY_MIDDLE.getId()) {
            lp.setBankProgress(BankRobberySmallProgress.BANK_ROBBERY_START);
            JohnBankRobberyInventory.currentlyInBank = null;
            lp.removeLobbyItem(LobbyItem.GOLD_BARDING);
        }

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

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInteractEntity(PlayerInteractEntityEvent e) {
        Player p = e.getPlayer();

        if (e.getRightClicked() instanceof Player) {
            Player clicked = (Player) e.getRightClicked();
            if (!LobbyPlugin.getInstance().getCatchManager().isCatching(p) && !LobbyPlugin.getInstance().getOneHitManager().isFighting(p) && !LobbyPlugin.getInstance().getGungameManager().isFighting(p)) {
                new InteractionInventory(p, clicked);
            }
        }
    }

}
