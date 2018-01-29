/*
 * Copyright (c) 2017 Dominik L., Rufus Maiwald, BamDev and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.inventory;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import eu.mcone.bukkitcoresystem.util.ItemFactory;
import eu.mcone.bukkitcoresystem.util.LocationFactory;
import eu.mcone.lobby.Lobby;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import java.util.List;

public class KompassInventory {

    public KompassInventory(Player p) {
        Inventory inv = org.bukkit.Bukkit.createInventory(null, 54, "§8» §3Navigator");

        inv.setItem(0, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 3, 1, "§8//§oMCONE§8//", true));
        inv.setItem(1, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 11, 1, "§8//§oMCONE§8//", true));
        inv.setItem(2, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 7, 1, "§8//§oMCONE§8//", true));
        inv.setItem(3, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 7, 1, "§8//§oMCONE§8//", true));
        inv.setItem(4, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 0, 1, "§8//§oMCONE§8//", true));
        inv.setItem(5, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 7, 1, "§8//§oMCONE§8//", true));
        inv.setItem(6, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 7, 1, "§8//§oMCONE§8//", true));
        inv.setItem(7, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 11, 1, "§8//§oMCONE§8//", true));
        inv.setItem(8, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 3, 1, "§8//§oMCONE§8//", true));
        inv.setItem(9, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 11, 1, "§8//§oMCONE§8//", true));
        inv.setItem(10, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 11, 1, "§8//§oMCONE§8//", true));

        inv.setItem(11, ItemFactory.createItem(Material.getMaterial(getItemID("Navigator-1")), 0, 1, getItemname("Navigator-1"), getItemLore("Navigator-1"), true));

        inv.setItem(12, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 0, 1, "§8//§oMCONE§8//", true));

        inv.setItem(13, ItemFactory.createItem(Material.getMaterial(getItemID("Navigator-2")), 0, 1, getItemname("Navigator-2"), getItemLore("Navigator-2"), true));

        inv.setItem(14, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 0, 1, "§8//§oMCONE§8//", true));

        inv.setItem(15, ItemFactory.createItem(Material.getMaterial(getItemID("Navigator-3")), 0, 1, getItemname("Navigator-3"), getItemLore("Navigator-3"), true));

        inv.setItem(16, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 11, 1, "§8//§oMCONE§8//", true));
        inv.setItem(17, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 11, 1, "§8//§oMCONE§8//", true));
        inv.setItem(18, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 7, 1, "§8//§oMCONE§8//", true));
        inv.setItem(19, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 3, 1, "§8//§oMCONE§8//", true));
        inv.setItem(20, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 0, 1, "§8//§oMCONE§8//", true));

        inv.setItem(21, ItemFactory.createItem(Material.getMaterial(getItemID("Navigator-4")), 0, 1, getItemname("Navigator-4"), getItemLore("Navigator-4"), true));

        inv.setItem(22, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 7, 1, "§8//§oMCONE§8//", true));

        inv.setItem(23, ItemFactory.createItem(Material.getMaterial(getItemID("Navigator-5")), 0, 1, getItemname("Navigator-5"), getItemLore("Navigator-5"), true));

        inv.setItem(24, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 0, 1, "§8//§oMCONE§8//", true));
        inv.setItem(25, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 3, 1, "§8//§oMCONE§8//", true));
        inv.setItem(26, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 7, 1, "§8//§oMCONE§8//", true));
        inv.setItem(27, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 7, 1, "§8//§oMCONE§8//", true));
        inv.setItem(28, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 3, 1, "§8//§oMCONE§8//", true));
        inv.setItem(29, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 0, 1, "§8//§oMCONE§8//", true));
        inv.setItem(30, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 7, 1, "§8//§oMCONE§8//", true));

        inv.setItem(31, ItemFactory.createItem(Material.getMaterial(getItemID("Navigator-6")), 0, 1, getItemname("Navigator-6"), getItemLore("Navigator-6"), true));

        inv.setItem(32, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 7, 1, "§8//§oMCONE§8//", true));
        inv.setItem(33, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 0, 1, "§8//§oMCONE§8//", true));
        inv.setItem(34, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 3, 1, "§8//§oMCONE§8//", true));
        inv.setItem(35, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 7, 1, "§8//§oMCONE§8//", true));

        inv.setItem(36, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 11, 1, "§8//§oMCONE§8//", true));
        inv.setItem(37, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 11, 1, "§8//§oMCONE§8//", true));

        inv.setItem(38, ItemFactory.createItem(Material.getMaterial(getItemID("Navigator-7")), 0, 1, getItemname("Navigator-7"), getItemLore("Navigator-7"), true));

        inv.setItem(39, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 0, 1, "§8//§oMCONE§8//", true));

        inv.setItem(40, ItemFactory.createItem(Material.getMaterial(getItemID("Navigator-8")), 0, 1, getItemname("Navigator-8"), getItemLore("Navigator-8"), true));

        inv.setItem(41, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 0, 1, "§8//§oMCONE§8//", true));

        inv.setItem(42, ItemFactory.createItem(Material.getMaterial(getItemID("Navigator-9")), 0, 1, getItemname("Navigator-9"), getItemLore("Navigator-9"), true));

        inv.setItem(43, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 11, 1, "§8//§oMCONE§8//", true));
        inv.setItem(44, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 11, 1, "§8//§oMCONE§8//", true));

        inv.setItem(45, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 3, 1, "§8//§oMCONE§8//", true));
        inv.setItem(46, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 11, 1, "§8//§oMCONE§8//", true));
        inv.setItem(47, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 7, 1, "§8//§oMCONE§8//", true));
        inv.setItem(48, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 7, 1, "§8//§oMCONE§8//", true));
        inv.setItem(49, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 0, 1, "§8//§oMCONE§8//", true));
        inv.setItem(50, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 7, 1, "§8//§oMCONE§8//", true));
        inv.setItem(51, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 7, 1, "§8//§oMCONE§8//", true));
        inv.setItem(52, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 11, 1, "§8//§oMCONE§8//", true));
        inv.setItem(53, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 3, 1, "§8//§oMCONE§8//", true));
        p.openInventory(inv);
    }

    public static void click(InventoryClickEvent e, Player p) {
        if ((e.getCurrentItem() == null) || !e.getCurrentItem().hasItemMeta() || e.getSlotType() == InventoryType.SlotType.OUTSIDE) {
            e.setCancelled(true);
        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(getItemname("Navigator-1"))) {
            teleportToSpawn(p, "Location-Navigator-1");

        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(getItemname("Navigator-2"))) {
            teleportToSpawn(p, "Location-Navigator-2");

        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(getItemname("Navigator-3"))) {
            teleportToSpawn(p, "Location-Navigator-3");

        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(getItemname("Navigator-4"))) {
            teleportToSpawn(p, "Location-Navigator-4");

        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(getItemname("Navigator-5"))) {
            teleportToSpawn(p, "Location-Navigator-5");

        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(getItemname("Navigator-6"))) {
            teleportToSpawn(p, "Location-Navigator-6");

        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(getItemname("Navigator-7"))) {
            teleportToSpawn(p, "Location-Navigator-7");

        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(getItemname("Navigator-8"))) {
            teleportToSpawn(p, "Location-Spawn");

        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(getItemname("Navigator-9"))) {
            teleportToSpawn(p, "Location-Navigator-9");

        } else {
            e.setCancelled(true);
        }
    }

    private static void teleportToSpawn(Player p, String configKey) {
        Location loc = LocationFactory.getConfigLocation(Lobby.config, configKey);

        if (loc != null) {
            p.teleport(loc);
            p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
            p.playEffect(p.getLocation(), Effect.BLAZE_SHOOT, 1);
        } else {
            p.closeInventory();
            p.sendMessage(Lobby.config.getConfigValue("System-Prefix") + "§4Dieser Spawn existiert nicht!");
        }
    }

    private static String getItemname(String configKey) {
        gsonResult rs = new Gson().fromJson(Lobby.config.getConfigValue(configKey), gsonResult.class);
        return rs.getName();
    }

    private static int getItemID(String configKey) {
        gsonResult rs = new Gson().fromJson(Lobby.config.getConfigValue(configKey), gsonResult.class);
        return rs.getItemID();
    }

    private static List<String> getItemLore(String configKey) {
        gsonResult rs = new Gson().fromJson(Lobby.config.getConfigValue(configKey), gsonResult.class);
        return rs.getLore();
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
