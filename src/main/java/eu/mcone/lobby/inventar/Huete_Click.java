/*
 * Copyright (c) 2017 Dominik L., BamDev, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.inventar;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

public class Huete_Click {

    public Huete_Click(InventoryClickEvent e, Player p) {
        if ((e.getCurrentItem() == null) || !e.getCurrentItem().hasItemMeta() || e.getSlotType() == InventoryType.SlotType.OUTSIDE) {
            e.setCancelled(false);
        } else if (!e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §4Hat entfernen")) {
            p.getInventory().setHelmet(e.getCurrentItem());
            p.playSound(p.getLocation(), org.bukkit.Sound.ARROW_HIT, 1.0F, 1.0F);
            p.closeInventory();

        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §4Hat entfernen")) {
            p.getInventory().setHelmet(null);
            p.playSound(p.getLocation(), org.bukkit.Sound.ANVIL_BREAK, 1.0F, 1.0F);
            p.closeInventory();
        }

    }
}
