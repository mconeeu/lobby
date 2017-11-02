/*
 * Copyright (c) 2017 Dominik L., Rufus Maiwald, BamDev and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.event;

import eu.mcone.lobby.inventar.*;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClick_Event implements Listener{

    @EventHandler
    public void on(InventoryClickEvent e){
        Player p = (Player)e.getWhoClicked();
        if (p.getGameMode().equals(GameMode.CREATIVE)) {
            e.setCancelled(false);
        } else {
            e.setCancelled(true);
            if (e.getInventory().getName().equals("§8» §5Hüte")) {
                new Huete_Click(e, p);
            } else if (e.getInventory().getName().equals("§8» §3Dein Profil")) {
                new Profil_Click(e, p);
            } else if (e.getInventory().getName().equals("§8» §3Trails")){
                new Trails_Click(e, p);
            } else if (e.getInventory().getName().equals("§8» §3Navigator")) {
                new Kompass_Click(e, p);
            } else if (e.getInventory().getName().equals("§8» §6Trail kaufen")) {
                new Trails_Buy_Click(e, p);
            }
        }
    }

}