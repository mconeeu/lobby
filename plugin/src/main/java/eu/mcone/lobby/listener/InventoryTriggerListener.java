/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.listener;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.player.HotbarItems;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.api.player.LobbySettings;
import eu.mcone.lobby.inventory.LobbyInventory;
import eu.mcone.lobby.inventory.compass.MinigamesInventory;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryTriggerListener implements Listener {

    @EventHandler
    public void on(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        CorePlayer cp = CoreSystem.getInstance().getCorePlayer(p);
        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            ItemStack i = e.getItem();
            if ((i == null) || (!i.hasItemMeta()) || (!i.getItemMeta().hasDisplayName())) {
                return;
            }


            if (i.getItemMeta().getDisplayName().equalsIgnoreCase("§3§lProfil §8» §7§oEinstellungen / Stats / Freunde")) {
                e.setCancelled(true);
                p.performCommand("profile");
            } else if (i.equals(HotbarItems.HIDE_PLAYERS)) {
                e.setCancelled(true);
                LobbyPlugin.getInstance().getPlayerHiderManager().hidePlayers(p);
            } else if (i.equals(HotbarItems.SHOW_PLAYERS)) {
                e.setCancelled(true);
                LobbyPlugin.getInstance().getPlayerHiderManager().showPlayers(p);
            } else if (i.equals(HotbarItems.SETTING_HIDER)) {
                LobbyPlugin.getInstance().getMessenger().send(p, "§4Du hast alle Spieler über eine Lobby Einstellung ausgeschaltet!");
            } else if (i.equals(HotbarItems.COMPASS)) {
                e.setCancelled(true);
                new MinigamesInventory(p);
                p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
            } else if (i.equals(HotbarItems.PRIVATE_LOBBY) || i.equals(HotbarItems.LEAVE_PRIVATE_LOBBY)) {
                if (!CoreSystem.getInstance().getCooldownSystem().addAndCheck(CoreSystem.getInstance(), this.getClass(), p.getUniqueId()))
                    return;

                if (LobbyPlugin.getInstance().getSilentLobbyManager().isActivatedSilentHub(p)) {
                    LobbyPlugin.getInstance().getSilentLobbyManager().deactivateSilentLobby(p);
                } else {
                    LobbyPlugin.getInstance().getSilentLobbyManager().activateSilentLobby(p);
                }
            } else if (i.equals(HotbarItems.LOBBY_CHANGER)) {
                if (!LobbyPlugin.getInstance().getOfficeManager().isVanished(p)) {
                    new LobbyInventory(p);
                } else {
                    LobbyPlugin.getInstance().getMessenger().send(p, "§4Der Lobby Wechsler ist im Büro deaktiviert!");
                }
            } else if (i.equals(HotbarItems.NICK_ACTIVATED)) {
                if (cp.isNicked()) {
                    CoreSystem.getInstance().getChannelHandler().createSetRequest(p, "CMD", "nick");
                }
                LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);
                LobbySettings settings = lp.getSettings();

                if (settings.isRankBoots()) {
                    LobbyPlugin.getInstance().getBackpackManager().setRankBoots(p);
                }

                p.getInventory().setItem(
                        6,
                        new ItemBuilder(Material.NAME_TAG, 1, 0).displayName("§c§lNicken §8» §7§oDeaktiviert").lore("§7§oKlicke zum aktivieren").create()
                );
            } else if (i.equals(HotbarItems.NICK_DISABLED)) {
                if (!cp.isNicked()) {
                    CoreSystem.getInstance().getChannelHandler().createSetRequest(p, "CMD", "nick");
                }

                LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);
                LobbySettings settings = lp.getSettings();

                if (settings.isRankBoots()) {
                    p.getInventory().setBoots(null);
                }

                p.getInventory().setItem(
                        6,
                        new ItemBuilder(Material.NAME_TAG, 1, 0).displayName("§a§lNicken §8» §7§oAktiviert").lore("§7§oKlicke zum deaktivieren").create()
                );
            }
        }
    }

}
