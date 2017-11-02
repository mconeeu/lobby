/*
 * Copyright (c) 2017 Dominik L., Rufus Maiwald, BamDev and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.inventar;

import de.Dominik.BukkitCoreSystem.util.ItemManager;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

public class Profil_Interact {

    public Profil_Interact(Player p) {
        Inventory inv = org.bukkit.Bukkit.createInventory(null, 9, "§8» §3Dein Profil");

        for (int i = 0; i <= 8; i++) {
            inv.setItem(i, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 1, "§8//§oMCONE§8//", true));
        }
        inv.setItem(2, ItemManager.createItem(Material.SKULL_ITEM, 0, 1, "§8» §5Hüte", true));
        inv.setItem(4, ItemManager.createItem(Material.GOLD_BOOTS, 0, 1, "§8» §3Trails", true));
        inv.setItem(6, ItemManager.createItem(Material.GOLD_INGOT, 0, 1, "§8» §bGadgets", true));

        p.playSound(p.getLocation(), Sound.CHEST_OPEN, 1.0F, 1.0F);
        p.openInventory(inv);
    }
}
