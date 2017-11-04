/*
 * Copyright (c) 2017 Dominik L., Rufus Maiwald, BamDev and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.inventory;

import eu.mcone.lobby.Main;
import eu.mcone.lobby.trail.Trail;
import de.Dominik.BukkitCoreSystem.util.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class TrailsInventory {

    public TrailsInventory(Player p) {
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
        if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Trail.COOKIES.getName())){
            if (Main.trail.hasTrail(p, Trail.COOKIES)) {
                Main.trail.setTrail(p, Trail.COOKIES);
            } else {
                new TrailsBuyInventory(p, Trail.COOKIES);
            }
        }else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Trail.GLOW.getName())){
            if (Main.trail.hasTrail(p, Trail.GLOW)) {
                Main.trail.setTrail(p, Trail.GLOW);
            } else {
                new TrailsBuyInventory(p, Trail.GLOW);
            }
        }else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Trail.ENDER.getName())){
            if (Main.trail.hasTrail(p, Trail.ENDER)) {
                Main.trail.setTrail(p, Trail.ENDER);
            } else {
                new TrailsBuyInventory(p, Trail.ENDER);
            }
        }else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Trail.MUSIC.getName())){
            if (Main.trail.hasTrail(p, Trail.MUSIC)) {
                Main.trail.setTrail(p, Trail.MUSIC);
            } else {
                new TrailsBuyInventory(p, Trail.MUSIC);
            }
        }else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Trail.HEART.getName())){
            if (Main.trail.hasTrail(p, Trail.HEART)) {
                Main.trail.setTrail(p, Trail.HEART);
            } else {
                new TrailsBuyInventory(p, Trail.HEART);
            }
        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Trail.LAVA.getName())){
            if (Main.trail.hasTrail(p, Trail.LAVA)) {
                Main.trail.setTrail(p, Trail.LAVA);
            } else {
                new TrailsBuyInventory(p, Trail.LAVA);
            }
        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Trail.SNOW.getName())){
            if (Main.trail.hasTrail(p, Trail.SNOW)) {
                Main.trail.setTrail(p, Trail.SNOW);
            } else {
                new TrailsBuyInventory(p, Trail.SNOW);
            }
        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Trail.WATER.getName())){
            if (Main.trail.hasTrail(p, Trail.WATER)) {
                Main.trail.setTrail(p, Trail.WATER);
            } else {
                new TrailsBuyInventory(p, Trail.WATER);
            }
        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cTrail ablegen")){
            Main.trail.removeTrail(p);
        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7§l↩ Zurück")) {
            new ProfilInventory(p);
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
        }
    }
}
