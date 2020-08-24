/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.games.pvp.gungame;

import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class GunGameItem {

    public static final ItemStack LEAVE_GUNGAME_FIGHTING = new ItemBuilder(Material.IRON_DOOR, 1)
            .displayName("§4Verlassen")
            .create();

    public static final ItemStack HELMET = ItemBuilder
            .createLeatherArmorItem(Material.LEATHER_HELMET, Color.BLUE)
            .create();

    public static final ItemStack SAVE_HELMET = ItemBuilder
            .createLeatherArmorItem(Material.LEATHER_HELMET, Color.BLUE)
            .create();

    public static final ItemStack SAVE_CHESTPLATE = ItemBuilder
            .createLeatherArmorItem(Material.LEATHER_CHESTPLATE, Color.RED)
            .create();

    public static final ItemStack SAVE_LEGGINGS = ItemBuilder
            .createLeatherArmorItem(Material.LEATHER_LEGGINGS, Color.RED)
            .create();

    public static final ItemStack SAVE_BOOTS = ItemBuilder
            .createLeatherArmorItem(Material.LEATHER_BOOTS, Color.RED)
            .create();


}
