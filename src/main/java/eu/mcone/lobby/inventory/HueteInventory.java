/*
 * Copyright (c) 2017 Dominik L., Rufus Maiwald, BamDev and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.inventory;

import eu.mcone.bukkitcoresystem.util.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class HueteInventory {

    HueteInventory(Player p) {
        Inventory inv = Bukkit.createInventory(null, 9, "§8» §5Hüte");

        for (int i = 0; i <= 8; i++) {
            inv.setItem(i, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 1, "§8//§oMCONE§8//", true));
        }

        inv.setItem(0, ItemManager.createItem(Material.IRON_DOOR, 0, 1, "§7§l↩ Zurück", true));
        inv.setItem(1, ItemManager.createItem(Material.BARRIER, 0, 1, "§8» §c§lHat entfernen", true));

        inv.setItem(4, ItemManager.createItem(Material.CHAINMAIL_HELMET, 0, 1, "§8» §8§lKetten-Helm", true));
        inv.setItem(5, ItemManager.createItem(Material.DIAMOND_HELMET, 0, 1, "§8» §b§lDiamant-Helm", true));
        inv.setItem(6, ItemManager.createItem(Material.IRON_HELMET, 0, 1, "§8» §7§lEisen-Helm", true));
        inv.setItem(7, ItemManager.createItem(Material.GOLD_HELMET, 0, 1, "§8» §e§lGold-Helm", true));

        p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);
        p.openInventory(inv);
    }

    public static void click(InventoryClickEvent e, Player p) {
        if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7§l↩ Zurück")) {
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
            new GadgetsInventory(p);
        } else if (!e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §c§lHat entfernen")) {
            p.getInventory().setHelmet(e.getCurrentItem());
            p.playSound(p.getLocation(), org.bukkit.Sound.ARROW_HIT, 1.0F, 1.0F);
            p.closeInventory();
        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §c§lHat entfernen")) {
            p.getInventory().setHelmet(null);
            p.playSound(p.getLocation(), org.bukkit.Sound.ANVIL_BREAK, 1.0F, 1.0F);
            p.closeInventory();
        }

    }
}
