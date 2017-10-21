/*
 * Copyright (c) 2017 Dominik L., BamDev, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.inventar;

import eu.mcone.lobby.utils.ItemManager;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

public class Profil_Interact {

    public Profil_Interact(PlayerInteractEvent e, Player p) {
        Inventory inv = org.bukkit.Bukkit.createInventory(null, 9, "§8» §3Dein Profil");

        inv.setItem(0, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, ""));
        inv.setItem(1, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, ""));
        inv.setItem(2, ItemManager.createItem(Material.SKULL_ITEM, 0, 0, "§8» §5Hüte"));
        inv.setItem(3, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, ""));
        inv.setItem(4, ItemManager.createItem(Material.GOLD_BOOTS, 0, 0, "§8» §6Trails"));
        inv.setItem(5, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, ""));
        inv.setItem(6, ItemManager.createItem(Material.GOLD_INGOT, 0, 0, "§8» §bGadgets"));
        inv.setItem(7, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, ""));
        inv.setItem(8, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, ""));

        p.playSound(p.getLocation(), Sound.CHEST_OPEN, 1.0F, 1.0F);
        p.openInventory(inv);
    }
}
