/*
 * Copyright (c) 2017 Dominik L., Rufus Maiwald, BamDev and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.listener;

import eu.mcone.lobby.inventory.*;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

public class InventoryClick implements Listener{

    @EventHandler
    public void on(InventoryClickEvent e){
        Player p = (Player)e.getWhoClicked();

        if (p.getGameMode().equals(GameMode.CREATIVE)) {
            e.setCancelled(false);
        } else if ((e.getCurrentItem() == null) || !e.getCurrentItem().hasItemMeta() || e.getSlotType() == InventoryType.SlotType.OUTSIDE) {
            e.setCancelled(true);
        } else {
            e.setCancelled(true);
            if (e.getInventory().getName().equals("§8» §5Hüte")) {
                HueteInventory.click(e, p);
            } else if (e.getInventory().getName().equals("§8» §3Lobby Gadgets")) {
                GadgetsInventory.click(e, p);
            } else if (e.getInventory().getName().equals("§8» §3Trails")){
                TrailsInventory.click(e, p);
            } else if (e.getInventory().getName().equals("§8» §3Navigator")) {
                KompassInventory.click(e, p);
            } else if (e.getInventory().getName().equals("§8» §6Trail kaufen")) {
                TrailsBuyInventory.click(e, p);
            } else if (e.getInventory().getName().equals("§8» §3Interaktionsmenü")) {
                InteractionInventory.click(e, p);
            }
        }
    }

}