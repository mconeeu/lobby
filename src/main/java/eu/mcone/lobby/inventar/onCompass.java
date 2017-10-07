/*
 * Copyright (c) 2017 Dominik L., BamDev, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.inventar;

import eu.mcone.lobby.Main;
import eu.mcone.lobby.utils.ItemManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

public class onCompass implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        Player p = e.getPlayer();

        try{
            if (((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK)) &&
                    (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§3§lNavigator §8» §7§oWähle einen Spielmodus"))) {
                Inventory inv = org.bukkit.Bukkit.createInventory(null, 54, "§8» §3Navigator");

                //Hellblau inv.setItem(0, ItemManager.createItem(Material.STAINED_GLASS_PANE, 3, 0, ""));
                //Grau inv.setItem(0, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, ""));
                //Dunkelblau inv.setItem(0, ItemManager.createItem(Material.STAINED_GLASS_PANE, 11, 0, ""));
                //Weiß inv.setItem(0, ItemManager.createItem(Material.STAINED_GLASS_PANE, 0, 0, ""));

                inv.setItem(0, ItemManager.createItem(Material.STAINED_GLASS_PANE, 3, 0, "§r"));
                inv.setItem(1, ItemManager.createItem(Material.STAINED_GLASS_PANE, 11, 0, "§r"));
                inv.setItem(2, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, "§r"));
                inv.setItem(3, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, "§r"));
                inv.setItem(4, ItemManager.createItem(Material.STAINED_GLASS_PANE, 0, 0, "§r"));
                inv.setItem(5, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, "§r"));
                inv.setItem(6, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, "§r"));
                inv.setItem(7, ItemManager.createItem(Material.STAINED_GLASS_PANE, 11, 0, "§r"));
                inv.setItem(8, ItemManager.createItem(Material.STAINED_GLASS_PANE, 3, 0, "§r"));
                inv.setItem(9, ItemManager.createItem(Material.STAINED_GLASS_PANE, 11, 0, "§r"));
                inv.setItem(10, ItemManager.createItem(Material.STAINED_GLASS_PANE, 11, 0, "§r"));

                inv.setItem(11, ItemManager.createItemLore(Material.getMaterial(Main.cfg.getInt("Lobby-Navigator-1-ItemID")), 0, 0, Main.cfg.getString("Lobby-Navigator-1-Name").replaceAll("&", "§").replaceAll(">>", "»"), Main.cfg.getString("Lobby-Navigator-1-Lore-1").replaceAll("&", "§").replaceAll(">>", "»"), Main.cfg.getString("Lobby-Navigator-1-Lore-2").replaceAll("&", "§").replaceAll(">>", "»"), Main.cfg.getString("Lobby-Navigator-1-Lore-3").replaceAll("&", "§").replaceAll(">>", "»"), Main.cfg.getString("Lobby-Navigator-1-Lore-4").replaceAll("&", "§").replaceAll(">>", "»")));

                inv.setItem(12, ItemManager.createItem(Material.STAINED_GLASS_PANE, 0, 0, "§r"));

                inv.setItem(13, ItemManager.createItemLore(Material.getMaterial(Main.cfg.getInt("Lobby-Navigator-2-ItemID")), 0, 0, Main.cfg.getString("Lobby-Navigator-2-Name").replaceAll("&", "§").replaceAll(">>", "»").replaceAll(">>", "»"), Main.cfg.getString("Lobby-Navigator-2-Lore-1").replaceAll("&", "§").replaceAll(">>", "»"), Main.cfg.getString("Lobby-Navigator-2-Lore-2").replaceAll("&", "§").replaceAll(">>", "»"), Main.cfg.getString("Lobby-Navigator-2-Lore-3").replaceAll("&", "§").replaceAll(">>", "»"), Main.cfg.getString("Lobby-Navigator-2-Lore-4").replaceAll("&", "§").replaceAll(">>", "»")));

                inv.setItem(14, ItemManager.createItem(Material.STAINED_GLASS_PANE, 0, 0, "§r"));

                inv.setItem(15, ItemManager.createItemLore(Material.getMaterial(Main.cfg.getInt("Lobby-Navigator-3-ItemID")), 0, 0, Main.cfg.getString("Lobby-Navigator-3-Name").replaceAll("&", "§").replaceAll(">>", "»").replaceAll(">>", "»"), Main.cfg.getString("Lobby-Navigator-3-Lore-1").replaceAll("&", "§").replaceAll(">>", "»"), Main.cfg.getString("Lobby-Navigator-3-Lore-2").replaceAll("&", "§").replaceAll(">>", "»"), Main.cfg.getString("Lobby-Navigator-3-Lore-3").replaceAll("&", "§").replaceAll(">>", "»"), Main.cfg.getString("Lobby-Navigator-3-Lore-4").replaceAll("&", "§").replaceAll(">>", "»")));

                inv.setItem(16, ItemManager.createItem(Material.STAINED_GLASS_PANE, 11, 0, "§r"));
                inv.setItem(17, ItemManager.createItem(Material.STAINED_GLASS_PANE, 11, 0, "§r"));
                inv.setItem(18, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, "§r"));
                inv.setItem(19, ItemManager.createItem(Material.STAINED_GLASS_PANE, 3, 0, "§r"));
                inv.setItem(20, ItemManager.createItem(Material.STAINED_GLASS_PANE, 0, 0, "§r"));

                inv.setItem(21, ItemManager.createItemLore(Material.getMaterial(Main.cfg.getInt("Lobby-Navigator-4-ItemID")), 0, 0, Main.cfg.getString("Lobby-Navigator-4-Name").replaceAll("&", "§").replaceAll(">>", "»"), Main.cfg.getString("Lobby-Navigator-4-Lore-1").replaceAll("&", "§").replaceAll(">>", "»"), Main.cfg.getString("Lobby-Navigator-4-Lore-2").replaceAll("&", "§").replaceAll(">>", "»"), Main.cfg.getString("Lobby-Navigator-4-Lore-3").replaceAll("&", "§").replaceAll(">>", "»"), Main.cfg.getString("Lobby-Navigator-4-Lore-4").replaceAll("&", "§").replaceAll(">>", "»")));

                inv.setItem(22, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, "§r"));

                inv.setItem(23, ItemManager.createItemLore(Material.getMaterial(Main.cfg.getInt("Lobby-Navigator-5-ItemID")), 0, 0, Main.cfg.getString("Lobby-Navigator-5-Name").replaceAll("&", "§").replaceAll(">>", "»"), Main.cfg.getString("Lobby-Navigator-5-Lore-1").replaceAll("&", "§").replaceAll(">>", "»"), Main.cfg.getString("Lobby-Navigator-5-Lore-2").replaceAll("&", "§").replaceAll(">>", "»"), Main.cfg.getString("Lobby-Navigator-5-Lore-3").replaceAll("&", "§").replaceAll(">>", "»"), Main.cfg.getString("Lobby-Navigator-5-Lore-4").replaceAll("&", "§").replaceAll(">>", "»")));

                inv.setItem(24, ItemManager.createItem(Material.STAINED_GLASS_PANE, 0, 0, "§r"));
                inv.setItem(25, ItemManager.createItem(Material.STAINED_GLASS_PANE, 3, 0, "§r"));
                inv.setItem(26, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, "§r"));
                inv.setItem(27, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, "§r"));
                inv.setItem(28, ItemManager.createItem(Material.STAINED_GLASS_PANE, 3, 0, "§r"));
                inv.setItem(29, ItemManager.createItem(Material.STAINED_GLASS_PANE, 0, 0, "§r"));
                inv.setItem(30, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, "§r"));

                inv.setItem(31, ItemManager.createItemLore(Material.getMaterial(Main.cfg.getInt("Lobby-Navigator-6-ItemID")), 0, 0, Main.cfg.getString("Lobby-Navigator-6-Name").replaceAll("&", "§").replaceAll(">>", "»"), Main.cfg.getString("Lobby-Navigator-6-Lore-1").replaceAll("&", "§").replaceAll(">>", "»"), Main.cfg.getString("Lobby-Navigator-6-Lore-2").replaceAll("&", "§").replaceAll(">>", "»"), Main.cfg.getString("Lobby-Navigator-6-Lore-3").replaceAll("&", "§").replaceAll(">>", "»"), Main.cfg.getString("Lobby-Navigator-6-Lore-4").replaceAll("&", "§").replaceAll(">>", "»")));

                inv.setItem(32, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, "§r"));
                inv.setItem(33, ItemManager.createItem(Material.STAINED_GLASS_PANE, 0, 0, "§r"));
                inv.setItem(34, ItemManager.createItem(Material.STAINED_GLASS_PANE, 3, 0, "§r"));
                inv.setItem(35, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, "§r"));

                inv.setItem(36, ItemManager.createItem(Material.STAINED_GLASS_PANE, 11, 0, "§r"));
                inv.setItem(37, ItemManager.createItem(Material.STAINED_GLASS_PANE, 11, 0, "§r"));

                inv.setItem(38, ItemManager.createItemLore(Material.getMaterial(Main.cfg.getInt("Lobby-Navigator-7-ItemID")), 0, 0, Main.cfg.getString("Lobby-Navigator-7-Name").replaceAll("&", "§").replaceAll(">>", "»"), Main.cfg.getString("Lobby-Navigator-7-Lore-1").replaceAll("&", "§").replaceAll(">>", "»"), Main.cfg.getString("Lobby-Navigator-7-Lore-2").replaceAll("&", "§").replaceAll(">>", "»"), Main.cfg.getString("Lobby-Navigator-7-Lore-1").replaceAll("&", "§").replaceAll(">>", "»"), Main.cfg.getString("Lobby-Navigator-7-Lore-4").replaceAll("&", "§").replaceAll(">>", "»")));

                inv.setItem(39, ItemManager.createItem(Material.STAINED_GLASS_PANE, 0, 0, "§r"));

                inv.setItem(40, ItemManager.createItemLore(Material.getMaterial(Main.cfg.getInt("Lobby-Navigator-8-ItemID")), 0, 0, Main.cfg.getString("Lobby-Navigator-8-Name").replaceAll("&", "§").replaceAll(">>", "»"), Main.cfg.getString("Lobby-Navigator-8-Lore-1").replaceAll("&", "§").replaceAll(">>", "»"), Main.cfg.getString("Lobby-Navigator-8-Lore-2").replaceAll("&", "§").replaceAll(">>", "»"), Main.cfg.getString("Lobby-Navigator-8-Lore-3").replaceAll("&", "§").replaceAll(">>", "»"), Main.cfg.getString("Lobby-Navigator-8-Lore-4").replaceAll("&", "§").replaceAll(">>", "»")));

                inv.setItem(41, ItemManager.createItem(Material.STAINED_GLASS_PANE, 0, 0, "§r"));

                inv.setItem(42, ItemManager.createItemLore(Material.getMaterial(Main.cfg.getInt("Lobby-Navigator-9-ItemID")), 0, 0, Main.cfg.getString("Lobby-Navigator-9-Name").replaceAll("&", "§").replaceAll(">>", "»"), Main.cfg.getString("Lobby-Navigator-9-Lore-1").replaceAll("&", "§").replaceAll(">>", "»"), Main.cfg.getString("Lobby-Navigator-9-Lore-2").replaceAll("&", "§").replaceAll(">>", "»"), Main.cfg.getString("Lobby-Navigator-9-Lore-3").replaceAll("&", "§").replaceAll(">>", "»"), Main.cfg.getString("Lobby-Navigator-9-Lore-4").replaceAll("&", "§").replaceAll(">>", "»")));

                inv.setItem(43, ItemManager.createItem(Material.STAINED_GLASS_PANE, 11, 0, "§r"));
                inv.setItem(44, ItemManager.createItem(Material.STAINED_GLASS_PANE, 11, 0, "§r"));

                inv.setItem(45, ItemManager.createItem(Material.STAINED_GLASS_PANE, 3, 0, "§r"));
                inv.setItem(46, ItemManager.createItem(Material.STAINED_GLASS_PANE, 11, 0, "§r"));
                inv.setItem(47, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, "§r"));
                inv.setItem(48, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, "§r"));
                inv.setItem(49, ItemManager.createItem(Material.STAINED_GLASS_PANE, 0, 0, "§r"));
                inv.setItem(50, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, "§r"));
                inv.setItem(51, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, "§r"));
                inv.setItem(52, ItemManager.createItem(Material.STAINED_GLASS_PANE, 11, 0, "§r"));
                inv.setItem(53, ItemManager.createItem(Material.STAINED_GLASS_PANE, 3, 0, "§r"));
                p.openInventory(inv);
            }
        }catch(Exception e1){

        }
    }

}
