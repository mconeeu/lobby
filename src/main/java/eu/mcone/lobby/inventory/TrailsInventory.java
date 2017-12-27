/*
 * Copyright (c) 2017 Dominik L., Rufus Maiwald, BamDev and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.inventory;

import eu.mcone.bukkitcoresystem.util.ItemManager;
import eu.mcone.lobby.Main;
import eu.mcone.lobby.trail.Trail;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class TrailsInventory {

    TrailsInventory(Player p) {
        Inventory inv = Bukkit.createInventory(null, 18, "§8» §3Trails");

        for (int i = 0; i <= 17; i++) {
            inv.setItem(i, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 1, "§8//§oMCONE§8//", true));
        }

        inv.setItem(0, ItemManager.createItem(Material.BARRIER, 0, 1, "§8» §c§lTrail ablegen", true));
        Main.trail.setInvItem(inv, p, Trail.COOKIES, 2);
        Main.trail.setInvItem(inv, p, Trail.GLOW, 4);
        Main.trail.setInvItem(inv, p, Trail.ENDER, 6);
        Main.trail.setInvItem(inv, p, Trail.MUSIC, 8);
        Main.trail.setInvItem(inv, p, Trail.LAVA, 11);
        Main.trail.setInvItem(inv, p, Trail.HEART, 13);
        Main.trail.setInvItem(inv, p, Trail.WATER, 15);
        Main.trail.setInvItem(inv, p, Trail.SNOW, 17);

        inv.setItem(9, ItemManager.createItem(Material.IRON_DOOR, 0, 1, "§7§l↩ Zurück", true));

        p.openInventory(inv);
        p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
    }

    public static void click(InventoryClickEvent e, Player p) {
        for (Trail t : Trail.values()) {
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals(t.getName())) {
                if (Main.trail.hasTrail(p, t)) {
                    Main.trail.setTrail(p, t);
                } else {
                    new TrailsBuyInventory(p, t);
                }
                return;
            }
        }

        if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §c§lTrail ablegen")){
            Main.trail.removeTrail(p);
        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7§l↩ Zurück")) {
            new GadgetsInventory(p);
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
        }
    }
}
