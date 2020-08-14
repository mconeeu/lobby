/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.games.pvp.onehit;

import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

public class OneHitItem {

    public static final ItemStack HELMET = ItemBuilder
            .createLeatherArmorItem(Material.LEATHER_HELMET, Color.RED)
            .create();

    public static final ItemStack ONEHIT_SWORD = new ItemBuilder(Material.IRON_SWORD, 1, 0)
            .unbreakable(true)
            .itemFlags(ItemFlag.HIDE_UNBREAKABLE)
            .displayName("§fOneHit-Schwert")
            .create();

    public static final ItemStack STORY_ONEHIT_SWORD = new ItemBuilder(Material.DIAMOND_SWORD, 1, 0)
            .unbreakable(true)
            .itemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ENCHANTS)
            .displayName("§fOneHit-Schwert")
            .enchantment(Enchantment.DAMAGE_ALL, 1)
            .create();

    public static final ItemStack ONEHIT_BOW = new ItemBuilder(Material.BOW, 1, 0)
            .unbreakable(true)
            .itemFlags(ItemFlag.HIDE_UNBREAKABLE)
            .displayName("§cOneHit-Bogen")
            .create();

    public static final ItemStack ONEHIT_GADGET = new ItemBuilder(Material.CHEST, 1, 0)
            .displayName("§eGadgets")
            .create();

    public static final ItemStack ONEHIT_ARROW = new ItemBuilder(Material.ARROW, 1, 0)
            .displayName("§bOneHit-Pfeil")
            .create();

    public static final ItemStack ONEHIT_SNOWBALL = new ItemBuilder(Material.SNOW_BALL, 1, 0)
            .displayName("§fOneHit-Schneball")
            .create();

    public static final ItemStack LEAVE_ONEHIT_FIGHTING = new ItemBuilder(Material.IRON_DOOR, 1)
            .displayName("§4Verlassen")
            .create();

}
