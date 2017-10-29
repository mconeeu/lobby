/*
 * Copyright (c) 2017 Dominik L., Rufus Maiwald, BamDev and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.inventar;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import eu.mcone.lobby.Main;
import de.Dominik.BukkitCoreSystem.util.ItemManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import java.util.List;

public class Kompass_Interact {

    public Kompass_Interact(PlayerInteractEvent e, Player p) {
        Inventory inv = org.bukkit.Bukkit.createInventory(null, 54, "§8» §3Navigator");

        //Hellblau inv.setItem(0, ItemManager.createItem(Material.STAINED_GLASS_PANE, 3, 0, ""));
        //Grau inv.setItem(0, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, ""));
        //Dunkelblau inv.setItem(0, ItemManager.createItem(Material.STAINED_GLASS_PANE, 11, 0, ""));
        //Weiß inv.setItem(0, ItemManager.createItem(Material.STAINED_GLASS_PANE, 0, 0, ""));

        inv.setItem(0, ItemManager.createItem(Material.STAINED_GLASS_PANE, 3, 0, "§8//§oMCONE§8//"));
        inv.setItem(1, ItemManager.createItem(Material.STAINED_GLASS_PANE, 11, 0, "§8//§oMCONE§8//"));
        inv.setItem(2, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, "§8//§oMCONE§8//"));
        inv.setItem(3, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, "§8//§oMCONE§8//"));
        inv.setItem(4, ItemManager.createItem(Material.STAINED_GLASS_PANE, 0, 0, "§8//§oMCONE§8//"));
        inv.setItem(5, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, "§8//§oMCONE§8//"));
        inv.setItem(6, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, "§8//§oMCONE§8//"));
        inv.setItem(7, ItemManager.createItem(Material.STAINED_GLASS_PANE, 11, 0, "§8//§oMCONE§8//"));
        inv.setItem(8, ItemManager.createItem(Material.STAINED_GLASS_PANE, 3, 0, "§8//§oMCONE§8//"));
        inv.setItem(9, ItemManager.createItem(Material.STAINED_GLASS_PANE, 11, 0, "§8//§oMCONE§8//"));
        inv.setItem(10, ItemManager.createItem(Material.STAINED_GLASS_PANE, 11, 0, "§8//§oMCONE§8//"));

        inv.setItem(11, ItemManager.createItemLore(Material.getMaterial(getItemID("Navigator-1")), 0, 0, getItemname("Navigator-1"), getItemLore("Navigator-1")));

        inv.setItem(12, ItemManager.createItem(Material.STAINED_GLASS_PANE, 0, 0, "§8//§oMCONE§8//"));

        inv.setItem(13, ItemManager.createItemLore(Material.getMaterial(getItemID("Navigator-2")), 0, 0, getItemname("Navigator-2"), getItemLore("Navigator-2")));

        inv.setItem(14, ItemManager.createItem(Material.STAINED_GLASS_PANE, 0, 0, "§8//§oMCONE§8//"));

        inv.setItem(15, ItemManager.createItemLore(Material.getMaterial(getItemID("Navigator-3")), 0, 0, getItemname("Navigator-3"), getItemLore("Navigator-3")));

        inv.setItem(16, ItemManager.createItem(Material.STAINED_GLASS_PANE, 11, 0, "§8//§oMCONE§8//"));
        inv.setItem(17, ItemManager.createItem(Material.STAINED_GLASS_PANE, 11, 0, "§8//§oMCONE§8//"));
        inv.setItem(18, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, "§8//§oMCONE§8//"));
        inv.setItem(19, ItemManager.createItem(Material.STAINED_GLASS_PANE, 3, 0, "§8//§oMCONE§8//"));
        inv.setItem(20, ItemManager.createItem(Material.STAINED_GLASS_PANE, 0, 0, "§8//§oMCONE§8//"));

        inv.setItem(21, ItemManager.createItemLore(Material.getMaterial(getItemID("Navigator-4")), 0, 0, getItemname("Navigator-4"), getItemLore("Navigator-4")));

        inv.setItem(22, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, "§8//§oMCONE§8//"));

        inv.setItem(23, ItemManager.createItemLore(Material.getMaterial(getItemID("Navigator-5")), 0, 0, getItemname("Navigator-5"), getItemLore("Navigator-5")));

        inv.setItem(24, ItemManager.createItem(Material.STAINED_GLASS_PANE, 0, 0, "§8//§oMCONE§8//"));
        inv.setItem(25, ItemManager.createItem(Material.STAINED_GLASS_PANE, 3, 0, "§8//§oMCONE§8//"));
        inv.setItem(26, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, "§8//§oMCONE§8//"));
        inv.setItem(27, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, "§8//§oMCONE§8//"));
        inv.setItem(28, ItemManager.createItem(Material.STAINED_GLASS_PANE, 3, 0, "§8//§oMCONE§8//"));
        inv.setItem(29, ItemManager.createItem(Material.STAINED_GLASS_PANE, 0, 0, "§8//§oMCONE§8//"));
        inv.setItem(30, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, "§8//§oMCONE§8//"));

        inv.setItem(31, ItemManager.createItemLore(Material.getMaterial(getItemID("Navigator-6")), 0, 0, getItemname("Navigator-6"), getItemLore("Navigator-6")));

        inv.setItem(32, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, "§8//§oMCONE§8//"));
        inv.setItem(33, ItemManager.createItem(Material.STAINED_GLASS_PANE, 0, 0, "§8//§oMCONE§8//"));
        inv.setItem(34, ItemManager.createItem(Material.STAINED_GLASS_PANE, 3, 0, "§8//§oMCONE§8//"));
        inv.setItem(35, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, "§8//§oMCONE§8//"));

        inv.setItem(36, ItemManager.createItem(Material.STAINED_GLASS_PANE, 11, 0, "§8//§oMCONE§8//"));
        inv.setItem(37, ItemManager.createItem(Material.STAINED_GLASS_PANE, 11, 0, "§8//§oMCONE§8//"));

        inv.setItem(38, ItemManager.createItemLore(Material.getMaterial(getItemID("Navigator-7")), 0, 0, getItemname("Navigator-7"), getItemLore("Navigator-7")));

        inv.setItem(39, ItemManager.createItem(Material.STAINED_GLASS_PANE, 0, 0, "§8//§oMCONE§8//"));

        inv.setItem(40, ItemManager.createItemLore(Material.getMaterial(getItemID("Navigator-8")), 0, 0, getItemname("Navigator-8"), getItemLore("Navigator-8")));

        inv.setItem(41, ItemManager.createItem(Material.STAINED_GLASS_PANE, 0, 0, "§8//§oMCONE§8//"));

        inv.setItem(42, ItemManager.createItemLore(Material.getMaterial(getItemID("Navigator-9")), 0, 0, getItemname("Navigator-9"), getItemLore("Navigator-9")));

        inv.setItem(43, ItemManager.createItem(Material.STAINED_GLASS_PANE, 11, 0, "§8//§oMCONE§8//"));
        inv.setItem(44, ItemManager.createItem(Material.STAINED_GLASS_PANE, 11, 0, "§8//§oMCONE§8//"));

        inv.setItem(45, ItemManager.createItem(Material.STAINED_GLASS_PANE, 3, 0, "§8//§oMCONE§8//"));
        inv.setItem(46, ItemManager.createItem(Material.STAINED_GLASS_PANE, 11, 0, "§8//§oMCONE§8//"));
        inv.setItem(47, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, "§8//§oMCONE§8//"));
        inv.setItem(48, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, "§8//§oMCONE§8//"));
        inv.setItem(49, ItemManager.createItem(Material.STAINED_GLASS_PANE, 0, 0, "§8//§oMCONE§8//"));
        inv.setItem(50, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, "§8//§oMCONE§8//"));
        inv.setItem(51, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, "§8//§oMCONE§8//"));
        inv.setItem(52, ItemManager.createItem(Material.STAINED_GLASS_PANE, 11, 0, "§8//§oMCONE§8//"));
        inv.setItem(53, ItemManager.createItem(Material.STAINED_GLASS_PANE, 3, 0, "§8//§oMCONE§8//"));
        p.openInventory(inv);
    }

    private String getItemname(String configKey) {
        gsonResult rs = new Gson().fromJson(Main.config.getConfigValue(configKey), gsonResult.class);
        return rs.name;
    }

    private int getItemID(String configKey) {
        gsonResult rs = new Gson().fromJson(Main.config.getConfigValue(configKey), gsonResult.class);
        return rs.itemID;
    }

    private List getItemLore(String configKey) {
        gsonResult rs = new Gson().fromJson(Main.config.getConfigValue(configKey), gsonResult.class);
        return rs.lore;
    }

    private class gsonResult {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("ItemID")
        @Expose
        private Integer itemID;
        @SerializedName("Lore")
        @Expose
        private List<String> lore = null;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getItemID() {
            return itemID;
        }

        public void setItemID(Integer itemID) {
            this.itemID = itemID;
        }

        public List<String> getLore() {
            return lore;
        }

        public void setLore(List<String> lore) {
            this.lore = lore;
        }

    }
}
