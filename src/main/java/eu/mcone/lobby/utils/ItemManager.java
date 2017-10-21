/*
 * Copyright (c) 2017 Dominik L., BamDev, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {

    public static ItemStack createItem(Material material, int subid, int i, String displayname){
        ItemStack item = new ItemStack(material, 1, (short)subid);
        ItemMeta mitem = item.getItemMeta();
        mitem.setDisplayName(displayname);
        item.setItemMeta(mitem);

        return item;
    }

    public static ItemStack createItemLore(Material material, int subid, int i, String displayname, String lore1, String lore2, String lore3, String lore4){
        ArrayList<String> lore = new ArrayList<>();
        ItemStack item = new ItemStack(material, 1, (short)subid);
        ItemMeta mitem = item.getItemMeta();
        lore.add(lore1);
        lore.add(lore2);
        lore.add(lore3);
        lore.add(lore4);
        mitem.setLore(lore);
        mitem.setDisplayName(displayname);
        item.setItemMeta(mitem);

        return item;
    }

    public static ItemStack createItemLore(Material material, int subid, int i, String displayname, List lore){
        ItemStack item = new ItemStack(material, 1, (short)subid);
        ItemMeta mitem = item.getItemMeta();

        mitem.setLore(lore);
        mitem.setDisplayName(displayname);
        item.setItemMeta(mitem);

        return item;
    }

}
