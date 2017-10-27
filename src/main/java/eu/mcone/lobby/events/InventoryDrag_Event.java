/*
 * Copyright (c) 2017 Dominik L., Rufus Maiwald, BamDev and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.events;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryDragEvent;

public class InventoryDrag_Event implements Listener{

    @EventHandler
    public void onInventoryMove(InventoryDragEvent e){
        Player p = (Player)e.getWhoClicked();

        if(p.getGameMode().equals(GameMode.CREATIVE)){
            e.setCancelled(false);
        }else{
            e.setCancelled(true);
        }

        /*
        if(p.hasPermission("System.*") || p.hasPermission("Lobby.Build")){
            e.setCancelled(false);
        }else{
            e.setCancelled(true);
        }
        */
    }

}
