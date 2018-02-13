/*
 * Copyright (c) 2017 - 2018 Dominik L., Rufus Maiwald, BamDev and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.inventory;

import eu.mcone.coresystem.bukkit.util.ItemFactory;
import eu.mcone.lobby.Lobby;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class GadgetsInventory {

    public GadgetsInventory(Player p) {
        Inventory inv = org.bukkit.Bukkit.createInventory(null, 9, "§8» §3Lobby Gadgets");

        for (int i = 0; i <= 8; i++) {
            inv.setItem(i, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 7, 1, "§8//§oMCONE§8//", true));
        }
        inv.setItem(2, ItemFactory.createItem(Material.GOLD_HELMET, 0, 1, "§8» §5Hüte", true));
        inv.setItem(4, ItemFactory.createItem(Material.GOLD_BOOTS, 0, 1, "§8» §3Trails", true));
        inv.setItem(6, ItemFactory.createItem(Material.GOLD_INGOT, 0, 1, "§8» §bGadgets", true));

        p.openInventory(inv);
    }

    public static void click(InventoryClickEvent e, Player p) {
        if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §5Hüte")) {
            new HueteInventory(p);
            p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §3Trails")) {
            new TrailsInventory(p);
            p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §bGadgets")) {
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
            p.closeInventory();
            p.sendMessage(Lobby.config.getConfigValue("System-Prefix") + "§2Gib uns Ideen für Gadgets! §8- §7TeamSpeak: §fmcone.eu");
        }
    }
}
