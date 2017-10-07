/*
 * Copyright (c) 2017 Dominik L., BamDev, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.utils;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;

public class Items {

    public static ItemStack createItem(Material material, int subid, String displayname, int anzahl){
        ItemStack item = new ItemStack(material, 1, (short) subid);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayname);
        item.setAmount(anzahl);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack createItemWithLore(Material material, int subid, String displayname, String lore){
        ItemStack item = new ItemStack(material, 1, (short) subid);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayname);
        ArrayList<String> lore1 = new ArrayList<>();
        lore1.add(lore);
        meta.setLore(lore1);
        item.setItemMeta(meta);
        return item;
    }
    public static ItemStack createItemWith2Lores(Material material, int subid, String displayname, String lore, String lore2){
        ItemStack item = new ItemStack(material, 1, (short) subid);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayname);
        ArrayList<String> lore1 = new ArrayList<>();
        lore1.add(lore);
        lore1.add(lore2);
        meta.setLore(lore1);
        item.setItemMeta(meta);
        return item;

    }
    public static ItemStack createItemWith3Lores(Material material, int subid, String displayname, String lore, String lore2, String lore3){
        ItemStack item = new ItemStack(material, 1, (short) subid);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayname);
        ArrayList<String> lore1 = new ArrayList<>();
        lore1.add(lore);
        lore1.add(lore2);
        lore1.add(lore3);
        meta.setLore(lore1);
        item.setItemMeta(meta);
        return item;

    }

    public static ItemStack setItemWith3Lores(Material material, int subid, String displayname, String lore, String lore2, String lore3, int slot, Inventory inv){

        ItemStack item = new ItemStack(material, 1, (short) subid);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayname);
        ArrayList<String> lore1 = new ArrayList<>();
        lore1.add(lore);
        lore1.add(lore2);
        lore1.add(lore3);
        meta.setLore(lore1);
        item.setItemMeta(meta);


        inv.setItem(slot, item);


        return item;

    }
    public static ItemStack setItemWith2Lores(Material material, int subid, String displayname, String lore, String lore2, int slot, Inventory inv){

        ItemStack item = new ItemStack(material, 1, (short) subid);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayname);
        ArrayList<String> lore1 = new ArrayList<>();
        lore1.add(lore);
        lore1.add(lore2);

        meta.setLore(lore1);
        item.setItemMeta(meta);


        inv.setItem(slot, item);


        return item;

    }
    public static ItemStack setItemWith1Lore(Material material, int subid, String displayname, String lore, int slot, Inventory inv){

        ItemStack item = new ItemStack(material, 1, (short) subid);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayname);
        ArrayList<String> lore1 = new ArrayList<>();
        lore1.add(lore);
        meta.setLore(lore1);
        item.setItemMeta(meta);


        inv.setItem(slot, item);


        return item;

    }

    public static ItemStack createItemWith2LoresandAmount(Material material, int subid, String displayname, String lore, String lore2, int anzahl){
        ItemStack item = new ItemStack(material, 1, (short) subid);
        ItemMeta meta = item.getItemMeta();
        item.setAmount(anzahl);
        meta.setDisplayName(displayname);
        ArrayList<String> lore1 = new ArrayList<>();
        lore1.add(lore);
        lore1.add(lore2);
        meta.setLore(lore1);
        item.setItemMeta(meta);
        return item;

    }

    public static ItemStack getSkull(String name){
        @SuppressWarnings("deprecation")
        ItemStack skull = new ItemStack(397, 1, (short) 3);
        SkullMeta meta = (SkullMeta)skull.getItemMeta();
        meta.setDisplayName(name);
        meta.setOwner(name);
        skull.setItemMeta(meta);
        return skull;

    }

    public static ItemStack createHead(String owner, String displayname, String lore){

        @SuppressWarnings("deprecation")
        ItemStack item = new ItemStack (397, 1, (short) 3);
        SkullMeta meta = (SkullMeta)item.getItemMeta();
        meta.setDisplayName(displayname);
        meta.setOwner(owner);
        ArrayList<String> lore1 = new ArrayList<>();
        lore1.add(lore);
        meta.setLore(lore1);
        item.setItemMeta(meta);
        return item;


    }

    public static ItemStack createLeatherBoots(Color color, String displayname ){

        ItemStack item = new ItemStack(Material.LEATHER_BOOTS, 1);
        LeatherArmorMeta meta = (LeatherArmorMeta)item.getItemMeta();
        meta.setDisplayName(displayname);
        meta.setColor(color);
        item.setItemMeta(meta);
        return item;

    }

}
