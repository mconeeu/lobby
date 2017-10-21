/*
 * Copyright (c) 2017 Dominik L., BamDev, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.inventar;

import eu.mcone.lobby.Main;
import eu.mcone.lobby.utils.ItemManager;
import eu.mcone.lobby.utils.Items;
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
            inv1.setItem(0, Items.createItem(Material.BARRIER, 0, "§cTrail ablegen", 1));
            inv1.setItem(1, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, ""));

            if (p.hasPermission("lobby.cookietrail") || p.hasPermission("lobby.Premiumplus") || p.hasPermission("lobby.*")) {
                inv1.setItem(2, ItemManager.createItemLore(Material.COOKIE, 0, 0, "§5CookieTrail", "§r", "§7Du besitzt dieses Item", "§aSchon œ”", ""));
            } else {
                inv1.setItem(2, ItemManager.createItemLore(Material.COOKIE, 0, 0, "§5CookieTrail", "§r", "§7Du besitzt dieses Item", "§cNicht œ˜", ""));
            }

            inv1.setItem(3, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, ""));

            if (p.hasPermission("lobby.glowtrail") || p.hasPermission("lobby.Premiumplus") || p.hasPermission("lobby.*")) {
                inv1.setItem(4, ItemManager.createItemLore(Material.GOLD_INGOT, 0, 0, "§6GlowTrail", "§r", "§7Du besitzt dieses Item", "§aSchon œ”", ""));
            } else {
                inv1.setItem(4, ItemManager.createItemLore(Material.GOLD_INGOT, 0, 0, "§6GlowTrail", "§r", "§7Du besitzt dieses Item", "§cNicht œ˜", ""));
            }

            inv1.setItem(5, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, ""));

            if (p.hasPermission("lobby.endertrail") || p.hasPermission("lobby.Premiumplus") || p.hasPermission("lobby.*")) {
                inv1.setItem(6, ItemManager.createItemLore(Material.ENDER_PEARL, 0, 0, "§5EnderTrail", "§r", "§7Du besitzt dieses Item", "§aSchon œ”", ""));
            } else {
                inv1.setItem(6, ItemManager.createItemLore(Material.ENDER_PEARL, 0, 0, "§5EnderTrail", "§r", "§7Du besitzt dieses Item", "§cNicht œ˜", ""));
            }

            inv1.setItem(7, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, ""));

            if (p.hasPermission("lobby.musiktrail") || p.hasPermission("lobby.Premiumplus") || p.hasPermission("lobby.*")) {
                inv1.setItem(8, ItemManager.createItemLore(Material.JUKEBOX, 0, 0, "§aMusikTrail", "§r", "§7Du besitzt dieses Item", "§aSchon œ”", ""));
            } else {
                inv1.setItem(8, ItemManager.createItemLore(Material.JUKEBOX, 0, 0, "§aMusikTrail", "§r", "§7Du besitzt dieses Item", "§cNicht œ˜", ""));
            }

            inv1.setItem(9, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, ""));

            if (p.hasPermission("lobby.lavatrail") || p.hasPermission("lobby.Premiumplus") || p.hasPermission("lobby.*")) {
                inv1.setItem(10, ItemManager.createItemLore(Material.LAVA_BUCKET, 0, 0, "§cLavaTrail", "§r", "§7Du besitzt dieses Item", "§aSchon œ”", ""));
            } else {
                inv1.setItem(10, ItemManager.createItemLore(Material.LAVA_BUCKET, 0, 0, "§cLavaTrail", "§r", "§7Du besitzt dieses Item", "§cNicht œ˜", ""));
            }

            inv1.setItem(11, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, ""));

            if (p.hasPermission("lobby.hearttrail") || p.hasPermission("lobby.Premiumplus") || p.hasPermission("lobby.*")) {
                inv1.setItem(12, ItemManager.createItemLore(Material.REDSTONE, 0, 0, "§aHeartTrail", "§r", "§7Du besitzt dieses Item", "§aSchon œ”", ""));
            } else {
                inv1.setItem(12, ItemManager.createItemLore(Material.REDSTONE, 0, 0, "§aHeartTrail", "§r", "§7Du besitzt dieses Item", "§cNicht œ˜", ""));
            }

            inv1.setItem(13, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, ""));

            if (p.hasPermission("lobby.watertrail") || p.hasPermission("lobby.Premiumplus") || p.hasPermission("lobby.*")) {
                inv1.setItem(14, ItemManager.createItemLore(Material.WATER_BUCKET, 0, 0, "§9WaterTrail", "§r", "§7Du besitzt dieses Item", "§aSchon œ”", ""));
            } else {
                inv1.setItem(14, ItemManager.createItemLore(Material.WATER_BUCKET, 0, 0, "§9WaterTrail", "§r", "§7Du besitzt dieses Item", "§cNicht œ˜", ""));
            }

            inv1.setItem(15, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, ""));

            if (p.hasPermission("lobby.snowtrail") || p.hasPermission("lobby.Premiumplus") || p.hasPermission("lobby.*")) {
                inv1.setItem(16, ItemManager.createItemLore(Material.SNOW_BALL, 0, 0, "§fSnowTrail", "§r", "§7Du besitzt dieses Item", "§aSchon œ”", ""));
            } else {
                inv1.setItem(16, ItemManager.createItemLore(Material.SNOW_BALL, 0, 0, "§fSnowTrail", "§r", "§7Du besitzt dieses Item", "§cNicht œ˜", ""));
            }

            inv1.setItem(17, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, ""));

            p.openInventory(inv1);
            p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §bGadgets")) {
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
            p.closeInventory();
            p.sendMessage(Main.config.getConfigValue("System-Prefix") + "§bGib uns Ideen für Gadgets! §7- §bTeamSpeak3-Server §7: §fMcOne.eu");
        }
    }

}
