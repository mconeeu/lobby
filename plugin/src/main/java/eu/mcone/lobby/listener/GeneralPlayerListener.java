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
import eu.mcone.lobby.api.enums.LobbyItem;
import eu.mcone.lobby.api.enums.bank.BankRobberySmallProgress;
import eu.mcone.lobby.api.event.LobbyPlayerLoadedEvent;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.inventory.InteractionInventory;
import eu.mcone.lobby.items.inventory.office.secretary.SecretaryInventory;
import eu.mcone.lobby.items.manager.OfficeManager;
import eu.mcone.lobby.story.inventory.john.JohnBankRobberyInventory;
import org.bukkit.Bukkit;
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
import org.bukkit.potion.PotionEffectType;
import org.spigotmc.event.entity.EntityDismountEvent;

public class GeneralPlayerListener implements Listener {

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
    public void on(EntityDismountEvent e) {
        Player p = (Player) e.getEntity();
        Player isStacked = (Player) e.getDismounted();
        if (InteractionInventory.stacking.containsValue(p)) {


            InteractionInventory.stacking.remove(isStacked, p);
            LobbyPlugin.getInstance().getMessenger().send(isStacked, "§c" + p.getName() + "§4 ist nun nicht mehr auf deimem Kopf");
            LobbyPlugin.getInstance().getMessenger().send(p, "§c" + isStacked.getName() + " §4trägt dich nun nicht mehr!");
        }
    }

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent e) {
        Player p = e.getPlayer();
        Bukkit.getScheduler().runTaskLater(Lobby.getSystem(), () -> {
            if (InteractionInventory.stacking.containsKey(p)) {
                if (e.isSneaking()) {
                    Player isStacked = InteractionInventory.stacking.get(p);
                    InteractionInventory.stacking.remove(p, isStacked);
                    p.eject();

                    LobbyPlugin.getInstance().getMessenger().send(p, "§c" + isStacked.getName() + " §4ist nun nicht mehr auf deinem Kopf");
                    LobbyPlugin.getInstance().getMessenger().send(isStacked, "§c" + p.getName() + " §4trägt dich nun nicht mehr!");
                }
            }
        }, 1);
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
        e.setCancelled(p.getGameMode() != GameMode.CREATIVE);
    }

    @EventHandler
    public void onDropItem(PlayerDropItemEvent e) {
        Player p = e.getPlayer();
        e.setCancelled(p.getGameMode() != GameMode.CREATIVE);
    }

    @EventHandler
    public void onAchievementAwarded(PlayerAchievementAwardedEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onEntryBed(PlayerBedEnterEvent e) {
        e.setCancelled(true);
        Player p = e.getPlayer();
        LobbyPlugin.getInstance().getMessenger().send(p, "§4Du darfst in diesem §cBett §4nicht schlafen!");
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();

        if (LobbyPlugin.getInstance().getSilentLobbyManager().isActivatedSilentHub(player)) {
            LobbyPlugin.getInstance().getSilentLobbyManager().deactivateSilentLobby(player);
        }

        if (InteractionInventory.stacking.containsKey(player)) {
            Player isStacked = InteractionInventory.stacking.get(player);
            InteractionInventory.stacking.remove(player);

            LobbyPlugin.getInstance().getMessenger().send(isStacked, "§4" + player.getName() + " ist nun nicht mehr auf deinem Kopf");
        } else if (InteractionInventory.stacking.containsValue(player)) {
            Player isStacked = InteractionInventory.stacking.get(player);
            InteractionInventory.stacking.remove(player);
            LobbyPlugin.getInstance().getMessenger().send(isStacked, "§4" + isStacked.getName() + " hat dich fallen gelassen!");
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

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getClickedBlock() != null) {
            if (e.getClickedBlock().getType() == Material.BED_BLOCK) {
                e.setCancelled(true);
                Player p = e.getPlayer();

                p.setSleepingIgnored(false);

                LobbyPlugin.getInstance().getMessenger().send(p, "§4Du darfst in diesem §cBett §4nicht schlafen!");
            }
        }
    }
}
