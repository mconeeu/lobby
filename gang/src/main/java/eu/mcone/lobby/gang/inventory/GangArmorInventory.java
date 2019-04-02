/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.gang.inventory;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class GangArmorInventory extends CoreInventory {

    GangArmorInventory(Player p) {
        super("§8» §3§lRucksack §8| §fAusgerüstet Kamp Items", p, InventorySlot.ROW_6, Option.FILL_EMPTY_SLOTS);

        setItem(InventorySlot.ROW_1_SLOT_5, ItemBuilder.createSkullItemFromURL("http://textures.minecraft.net/texture/5163dafac1d91a8c91db576caac784336791a6e18d8f7f62778fc47bf146b6", 1).displayName("§d§lkamp einstellung").lore("§7§oHier kannst du deine Gang PVP fights einstellen").create());

        setItem(InventorySlot.ROW_6_SLOT_9, new ItemBuilder(Material.IRON_DOOR, 1, 0).displayName("§7§l↩ Zurück").create(), e -> new GangInventory(p));

        openInventory();
    }
}
