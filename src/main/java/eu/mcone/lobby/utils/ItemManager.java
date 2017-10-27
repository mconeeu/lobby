/*
 * Copyright (c) 2017 Dominik L., Rufus Maiwald, BamDev and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.utils;

import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.block.Skull;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Arrays;
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

    public static ItemStack createSkullItem(String displayname, String owner, String[] lore) {
        ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
        SkullMeta meta = (SkullMeta) item.getItemMeta();

        meta.setOwner(owner);
        meta.setDisplayName(displayname);
        meta.setLore(new ArrayList<>(Arrays.asList(lore)));
        item.setItemMeta(meta);

        return item;
    }

}
