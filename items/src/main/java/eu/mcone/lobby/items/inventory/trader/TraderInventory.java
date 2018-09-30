/*
 * Copyright (c) 2017 - 2018 Dominik Lippl, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.items.inventory.trader;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class TraderInventory extends CoreInventory {

    public TraderInventory(Player p) {
        super("§8» §e§lHändler §8| §fMit Coins kaufen", p, InventorySlot.ROW_3, Option.FILL_EMPTY_SLOTS);

        setItem(InventorySlot.ROW_1_SLOT_5, ItemBuilder.createSkullItemFromURL("http://textures.minecraft.net/texture/5163dafac1d91a8c91db576caac784336791a6e18d8f7f62778fc47bf146b6", 1).displayName("§e§lHändler").lore("§7§oBeim Händler kannst ausgewählte", "§7§oItems für deinen Rucksack kaufen.", "§7§oDie meisten Items erhälst du", "§7§onur durch das Öffnen von Kisten!").create());

        setItem(InventorySlot.ROW_3_SLOT_3, new ItemBuilder(Material.ANVIL, 1, 0).displayName("§cItems kaufen").create(),
                e -> new ItemsBuyInventory(p));

        setItem(InventorySlot.ROW_3_SLOT_5, new ItemBuilder(Material.BOOK, 1, 0).displayName("§bFähre Tickets kaufen").create(),
                e -> new TicketBuyInventory(p));

        setItem(InventorySlot.ROW_3_SLOT_7, new ItemBuilder(Material.CHEST, 1, 0).displayName("§6Kisten kaufen").create(),
                e-> new ChestBuyInventory(p));

        openInventory();
    }
}

