/*
 * Copyright (c) 2017 Dominik L., BamDev, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.inventar;

import eu.mcone.lobby.utils.ItemManager;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

public class onProfile implements Listener{

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        Player p = e.getPlayer();
        try{
            if (((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK)) && (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§3§lProfil §8» §7§oAussehen / Effekte / Gadgets"))) {
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
        }catch (Exception e1){

        }
    }

}
