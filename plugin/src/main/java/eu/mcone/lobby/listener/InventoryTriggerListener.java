/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.listener;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.inventory.CompassInventory;
import eu.mcone.lobby.inventory.LobbyInventory;
import eu.mcone.lobby.util.PlayerHider;
import eu.mcone.lobby.util.SilentLobbyUtils;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryTriggerListener implements Listener{

    @EventHandler
    public void on(PlayerInteractEvent e){
        Player p = e.getPlayer();

        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            ItemStack i = p.getItemInHand();
            if ((i == null) || (!i.hasItemMeta()) || (!i.getItemMeta().hasDisplayName())) {
                return;
            }

            if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§3§lProfil §8» §7§oEinstellungen / Stats / Freunde")) {
                e.setCancelled(true);
                p.performCommand("profile");
            } else if (p.getItemInHand().getItemMeta().getDisplayName().equals("§3§lSpieler Verstecken §8» §7§oBlende alle anderen Spieler aus")) {
                e.setCancelled(true);
                PlayerHider.hidePlayers(p);
            } else if (p.getItemInHand().getItemMeta().getDisplayName().equals("§3§lSpieler Anzeigen §8» §7§oZeigt alle Spieler wieder an")) {
                e.setCancelled(true);
                PlayerHider.showPlayers(p);
            } else if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§3§lNavigator §8» §7§oWähle einen Spielmodus")) {
                e.setCancelled(true);
                new CompassInventory(p);
                p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
            } else if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6§lPrivate Lobby §8» §7§oBetrete deine eigene Private Lobby")) {
                if (!CoreSystem.getInstance().getCooldownSystem().addAndCheck(CoreSystem.getInstance(), this.getClass(), p.getUniqueId()))
                    return;

                if (SilentLobbyUtils.isActivatedSilentHub(p)) {
                    SilentLobbyUtils.deactivateSilentLobby(p);
                } else {
                    LobbyPlugin.getInstance().getMessager().send(p, "§2Du bist nun in der §aPrivaten Lobby§2. Hier bist du vollkommen ungestört!");
                    SilentLobbyUtils.activateSilentLobby(p);
                }
            } else if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§3§lLobby-Wechsler §8» §7§oWähle deine Lobby")) {
                e.setCancelled(true);
                new LobbyInventory(p);
            }
        }
    }
}
