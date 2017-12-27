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
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.Arrays;

public class TrailsBuyInventory {

    TrailsBuyInventory(Player p, Trail trail) {
        Inventory inv = Bukkit.createInventory(null, 27, "§8» §6Trail kaufen");

        for (int i = 0; i <= 26; i++) {
            inv.setItem(i, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 1, "§8//§oMCONE§8//", true));
        }
        inv.setItem(4, ItemManager.createItem(trail.getItem(), 0, 1, trail.getName(), new ArrayList<>(Arrays.asList("", "§7§oKostet: §f§o" + trail.getCoins() + " Coins")), true));
        inv.setItem(21, ItemManager.createItem(Material.STAINED_GLASS_PANE, 5, 1, "§a§lTrail kaufen", new ArrayList<>(Arrays.asList("", "§8» §a§nRechtsklick§8 | §7§oKaufen")), true));
        inv.setItem(23, ItemManager.createItem(Material.STAINED_GLASS_PANE, 14, 1, "§c§lAbbrechen", new ArrayList<>(Arrays.asList("", "§8» §c§nRechtsklick§8 | §7§oAbbrechen")), true));

        p.openInventory(inv);
        p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
    }

    public static void click(InventoryClickEvent e, Player p) {
        if ((e.getCurrentItem() == null) || !e.getCurrentItem().hasItemMeta() || e.getSlotType() == InventoryType.SlotType.OUTSIDE) {
            e.setCancelled(true);
        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§a§lTrail kaufen")){
            Main.trail.buyTrail(p, Trail.getTrailbyName(e.getInventory().getItem(4).getItemMeta().getDisplayName()));
        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§c§lAbbrechen")){
            p.closeInventory();
        }
    }

}
