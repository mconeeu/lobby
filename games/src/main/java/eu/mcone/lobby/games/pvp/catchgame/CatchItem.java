/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.games.pvp.catchgame;

import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

public class CatchItem {

    public static final ItemStack HELMET = ItemBuilder.createLeatherArmorItem(Material.LEATHER_HELMET, Color.GREEN)
            .unbreakable(true)
            .itemFlags(ItemFlag.HIDE_UNBREAKABLE)
            .create();

    public static final ItemStack CATCH_STICK = new ItemBuilder(Material.BLAZE_ROD, 1, 0)
            .unbreakable(true)
            .itemFlags(ItemFlag.HIDE_UNBREAKABLE)
            .displayName("§fFang-Stock")
            .create();


    public static final ItemStack CATCHER_TRACKER = new ItemBuilder(Material.COMPASS, 1, 0)
            .unbreakable(true)
            .itemFlags(ItemFlag.HIDE_UNBREAKABLE)
            .displayName("§cFänger Tracker")
            .create();

    public static final ItemStack CATCH_RUN_TRACKER = new ItemBuilder(Material.COMPASS, 1, 0)
            .unbreakable(true)
            .itemFlags(ItemFlag.HIDE_UNBREAKABLE)
            .displayName("§cLäufer Tracker")
            .create();


    public static final ItemStack CATCH_STICK_RUN = new ItemBuilder(Material.STICK, 1, 0)
            .unbreakable(true)
            .itemFlags(ItemFlag.HIDE_UNBREAKABLE)
            .displayName("§fLauf-Stock")
            .create();


    public static final ItemStack CATCH_ROD = new ItemBuilder(Material.FISHING_ROD, 1, 0)
            .displayName("§eCatch-Enterhacken")
            .unbreakable(true)
            .itemFlags(ItemFlag.HIDE_UNBREAKABLE)
            .create();

    public static final ItemStack LEAVE_CATCH_FIGHTING = new ItemBuilder(Material.IRON_DOOR, 1)
            .displayName("§4Verlassen")
            .create();

}
