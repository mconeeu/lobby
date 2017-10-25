/*
 * Copyright (c) 2017 Dominik L., BamDev, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.events;

import eu.mcone.lobby.inventar.Hider_Interact;
import eu.mcone.lobby.inventar.Kompass_Interact;
import eu.mcone.lobby.inventar.Profil_Interact;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteract_Event implements Listener{

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        Player p = e.getPlayer();

        try{
            if (((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK))) {
                if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§3§lProfil §8» §7§oAussehen / Effekte / Gadgets")) {
                    new Profil_Interact(p);
                } else if (p.getItemInHand().getItemMeta().getDisplayName().equals("§3§lSpieler Verstecken §8» §7§oBlende alle anderen Spieler aus")) {
                    Hider_Interact.hidePlayers(p);
                } else if (p.getItemInHand().getItemMeta().getDisplayName().equals("§3§lSpieler Anzeigen §8» §7§oZeigt alle Spieler wieder an")) {
                    Hider_Interact.showPlayers(p);
                } else if (p.getItemInHand().getItemMeta().getDisplayName().equals("§3§lStats §8» §7§oStatistiken zu allen Spielmodi")) {
                    p.performCommand("stats");
                } else if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§3§lNavigator §8» §7§oWähle einen Spielmodus")) {
                    new Kompass_Interact(e, p);
                }
            }
        }catch(Exception e1){
            e1.printStackTrace();
        }
    }
}
