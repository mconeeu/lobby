/*
 * Copyright (c) 2017 Dominik L., BamDev, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.inventar;

import eu.mcone.lobby.Main;
import eu.mcone.lobby.trail.Trail;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

public class Trails_Buy_Click {

    public Trails_Buy_Click(InventoryClickEvent e, Player p) {
        if ((e.getCurrentItem() == null) || !e.getCurrentItem().hasItemMeta() || e.getSlotType() == InventoryType.SlotType.OUTSIDE) {
            e.setCancelled(false);
        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§a§lTrail kaufen")){
            Main.trail.buyTrail(p, Trail.getTrailbyName(e.getInventory().getItem(4).getItemMeta().getDisplayName()));
        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§c§lAbbrechen")){
            p.closeInventory();
        }
    }
}
