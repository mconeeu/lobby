/*
 * Copyright (c) 2017 Dominik L., BamDev, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.inventar;

import eu.mcone.lobby.Main;
import eu.mcone.lobby.trail.Trails;
import eu.mcone.lobby.utils.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class Profil_Click {

    public Profil_Click(InventoryClickEvent e, Player p) {
        e.setCancelled(true);
        if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §5Hüte")) {

            Inventory inv = Bukkit.createInventory(null, 9, "§8» §5Hüte");
            inv.setItem(0, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, ""));
            inv.setItem(1, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, ""));
            inv.setItem(2, ItemManager.createItem(Material.CHAINMAIL_HELMET, 0, 0, "§8» §8Ketten-Helm"));
            inv.setItem(3, ItemManager.createItem(Material.DIAMOND_HELMET, 0, 0, "§8» §bDiamant-Helm"));
            inv.setItem(4, ItemManager.createItem(Material.IRON_HELMET, 0, 0, "§8» §7Eisen-Helm"));
            inv.setItem(5, ItemManager.createItem(Material.GOLD_HELMET, 0, 0, "§8» §eGold-Helm"));
            inv.setItem(6, ItemManager.createItem(Material.BARRIER, 0, 0, "§8» §4Hat entfernen"));
            inv.setItem(7, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, ""));
            inv.setItem(8, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, ""));

            p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);
            p.openInventory(inv);
        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §6Trails")) {
            e.setCancelled(true);
            Inventory inv1 = Bukkit.createInventory(null, 18, "§8» §6Trails");

            inv1.setItem(0, ItemManager.createItem(Material.BARRIER, 0, 0, "§cTrail ablegen"));
            inv1.setItem(1, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, ""));
            Main.trail.setInvItem(inv1, p, Trails.COOKIES, 2);
            inv1.setItem(3, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, ""));
            Main.trail.setInvItem(inv1, p, Trails.GLOW, 4);
            inv1.setItem(5, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, ""));
            Main.trail.setInvItem(inv1, p, Trails.ENDER, 6);
            inv1.setItem(7, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, ""));
            Main.trail.setInvItem(inv1, p, Trails.MUSIC, 8);
            inv1.setItem(9, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, ""));
            Main.trail.setInvItem(inv1, p, Trails.LAVA, 10);
            inv1.setItem(11, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, ""));
            Main.trail.setInvItem(inv1, p, Trails.HEART, 12);
            inv1.setItem(13, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, ""));
            Main.trail.setInvItem(inv1, p, Trails.WATER, 14);
            inv1.setItem(15, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, ""));
            Main.trail.setInvItem(inv1, p, Trails.SNOW, 16);
            inv1.setItem(17, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, ""));

            p.openInventory(inv1);
            p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §bGadgets")) {
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
            p.closeInventory();
            p.sendMessage(Main.config.getConfigValue("System-Prefix") + "§bGib uns Ideen für Gadgets! §7- §bTeamSpeak3-Server §7: §fMcOne.eu");
        } else {
            return;
        }
    }

}
