/*
 * Copyright (c) 2017 Dominik L., BamDev, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.block;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class ItemListener implements Listener {

    @EventHandler
    public void onPickUp(PlayerPickupItemEvent e) {
        Player p = e.getPlayer();

        if (p.getGameMode() == GameMode.CREATIVE) {
            e.setCancelled(false);
        } else {
            e.setCancelled(true);

        }
    }

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

    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
        Player p = e.getPlayer();

        if (p.getGameMode() == GameMode.CREATIVE){
            e.setCancelled(false);
        } else {
            e.setCancelled(true);

        }
    }

}
