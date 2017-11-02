/*
 * Copyright (c) 2017 Dominik L., Rufus Maiwald, BamDev and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.inventar;

import eu.mcone.lobby.Main;
import eu.mcone.lobby.trail.Trail;
import de.Dominik.BukkitCoreSystem.util.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Trails_Click {

    public Trails_Click(InventoryClickEvent e, Player p) {
        if ((e.getCurrentItem() == null) || !e.getCurrentItem().hasItemMeta() || e.getSlotType() == InventoryType.SlotType.OUTSIDE) {
            e.setCancelled(true);
        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Trail.COOKIES.getName())){
            if (Main.trail.hasTrail(p, Trail.COOKIES)) {
                Main.trail.setTrail(p, Trail.COOKIES);
            } else {
                setBuyInventory(p, Trail.COOKIES);
            }
        }else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Trail.GLOW.getName())){
            if (Main.trail.hasTrail(p, Trail.GLOW)) {
                Main.trail.setTrail(p, Trail.GLOW);
            } else {
                setBuyInventory(p, Trail.GLOW);
            }
        }else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Trail.ENDER.getName())){
            if (Main.trail.hasTrail(p, Trail.ENDER)) {
                Main.trail.setTrail(p, Trail.ENDER);
            } else {
                setBuyInventory(p, Trail.ENDER);
            }
        }else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Trail.MUSIC.getName())){
            if (Main.trail.hasTrail(p, Trail.MUSIC)) {
                Main.trail.setTrail(p, Trail.MUSIC);
            } else {
                setBuyInventory(p, Trail.MUSIC);
            }
        }else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Trail.HEART.getName())){
            if (Main.trail.hasTrail(p, Trail.HEART)) {
                Main.trail.setTrail(p, Trail.HEART);
            } else {
                setBuyInventory(p, Trail.HEART);
            }
        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Trail.LAVA.getName())){
            if (Main.trail.hasTrail(p, Trail.LAVA)) {
                Main.trail.setTrail(p, Trail.LAVA);
            } else {
                setBuyInventory(p, Trail.LAVA);
            }
        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Trail.SNOW.getName())){
            if (Main.trail.hasTrail(p, Trail.SNOW)) {
                Main.trail.setTrail(p, Trail.SNOW);
            } else {
                setBuyInventory(p, Trail.SNOW);
            }
        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Trail.WATER.getName())){
            if (Main.trail.hasTrail(p, Trail.WATER)) {
                Main.trail.setTrail(p, Trail.WATER);
            } else {
                setBuyInventory(p, Trail.WATER);
            }
        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cTrail ablegen")){
            Main.trail.removeTrail(p);
        }
    }

    private void setBuyInventory(Player p, Trail trail) {
        Inventory inv = Bukkit.createInventory(null, 27, "§8» §6Trail kaufen");

        for (int i = 0; i <= 26; i++) {
            inv.setItem(i, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, "§8//§oMCONE§8//", true));
        }
        inv.setItem(4, ItemManager.createItem(trail.getItem(), 0, 0, trail.getName(), new ArrayList<>(Arrays.asList("", "§7§oKostet: §f§o" + trail.getCoins() + " Coins")), true));
        inv.setItem(21, ItemManager.createItem(Material.STAINED_GLASS_PANE, 5, 0, "§a§lTrail kaufen", new ArrayList<>(Arrays.asList("", "§8» §a§nRechtsklick§8 | §7§oKaufen")), true));
        inv.setItem(23, ItemManager.createItem(Material.STAINED_GLASS_PANE, 14, 0, "§c§lAbbrechen", new ArrayList<>(Arrays.asList("", "§8» §c§nRechtsklick§8 | §7§oAbbrechen")), true));

        p.openInventory(inv);
        p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
    }
}
