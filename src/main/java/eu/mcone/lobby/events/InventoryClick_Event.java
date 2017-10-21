/*
 * Copyright (c) 2017 Dominik L., BamDev, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.events;

import eu.mcone.lobby.inventar.Hüte_Click;
import eu.mcone.lobby.inventar.Kompass_Click;
import eu.mcone.lobby.inventar.Profil_Click;
import eu.mcone.lobby.inventar.Trails_Click;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClick_Event implements Listener{

    @EventHandler
    public void onClick(InventoryClickEvent e){
        Player p = (Player)e.getWhoClicked();
        if (p.getGameMode().equals(GameMode.CREATIVE)) {
            e.setCancelled(false);
        } else {
            e.setCancelled(true);
        }

        try{
            //e.setCancelled(true);
            if (e.getInventory().getName().equals("§8» §5Hüte")) {
                new Hüte_Click(e, p);
            }
            if (e.getInventory().getName().equals("§8» §3Dein Profil")) {
                new Profil_Click(e, p);
            }
            if (e.getInventory().getName().equals("§8» §6Trails")){
                new Trails_Click(e, p);
            }
            if (e.getInventory().getName().equals("§8» §3Navigator")) {
                new Kompass_Click(e, p);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

}