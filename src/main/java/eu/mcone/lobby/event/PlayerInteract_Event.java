/*
 * Copyright (c) 2017 Dominik L., Rufus Maiwald, BamDev and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.event;

import eu.mcone.lobby.util.PlayerHider;
import eu.mcone.lobby.inventory.KompassInventory;
import eu.mcone.lobby.inventory.ProfilInventory;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerInteract_Event implements Listener{

    @EventHandler
    public void on(PlayerInteractEvent e){
        Player p = e.getPlayer();

        ItemStack i = p.getItemInHand();
        if ((i == null) || (!i.hasItemMeta()) || (!i.getItemMeta().hasDisplayName())) {
            return;
        } else if ((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK)) {
            if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§3§lProfil §8» §7§oAussehen / Effekte / Gadgets")) {
                new ProfilInventory(p);
                p.playSound(p.getLocation(), Sound.CHEST_OPEN, 1.0F, 1.0F);
            } else if (p.getItemInHand().getItemMeta().getDisplayName().equals("§3§lSpieler Verstecken §8» §7§oBlende alle anderen Spieler aus")) {
                PlayerHider.hidePlayers(p);
            } else if (p.getItemInHand().getItemMeta().getDisplayName().equals("§3§lSpieler Anzeigen §8» §7§oZeigt alle Spieler wieder an")) {
                PlayerHider.showPlayers(p);
            } else if (p.getItemInHand().getItemMeta().getDisplayName().equals("§3§lStats §8» §7§oStatistiken zu allen Spielmodi")) {
                p.performCommand("stats");
            } else if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§3§lNavigator §8» §7§oWähle einen Spielmodus")) {
                new KompassInventory(p);
                p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
            }
        }
    }
}
