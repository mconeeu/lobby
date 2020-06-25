/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.items.inventory.trader;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.item.Skull;
import eu.mcone.gameapi.api.GamePlugin;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class TraderInventory extends CoreInventory {

    public TraderInventory(Player p) {
        super("§8» §e§lHändler", p, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);

        setItem(InventorySlot.ROW_1_SLOT_5, Skull.fromUrl("http://textures.minecraft.net/texture/5163dafac1d91a8c91db576caac784336791a6e18d8f7f62778fc47bf146b6", 1).toItemBuilder().displayName("§e§lHändler")
                .lore("§7§oBeim Händler kannst ausgewählte",
                        "§7§oItems für deinen Rucksack kaufen.",
                        "§7§oDie meisten Items erhälst du",
                        "§7§onur durch das Öffnen von Kisten!"
                ).create());

        setItem(InventorySlot.ROW_3_SLOT_2, new ItemBuilder(Material.ANVIL, 1, 0).displayName("§cTäglicher Shop").create(),
                e -> new DailyItemsInventory(p));

        setItem(InventorySlot.ROW_3_SLOT_4, new ItemBuilder(Material.PAPER, 1, 0).displayName("§bFähre Tickets kaufen").create(),
                e -> new TicketBuyInventory(p));

        setItem(InventorySlot.ROW_3_SLOT_6, new ItemBuilder(Material.CHEST, 1, 0).displayName("§6Kisten kaufen").create(),
                e -> new ChestBuyInventory(p));

        setItem(InventorySlot.ROW_3_SLOT_8, new ItemBuilder(Material.BOOK, 1, 0).displayName("§fOnePass").create(),
                e -> GamePlugin.getGamePlugin().getOnePassManager().openOnePassInventory(p));

        openInventory();
    }
}

