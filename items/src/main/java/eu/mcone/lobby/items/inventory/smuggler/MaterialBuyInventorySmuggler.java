/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.items.inventory.smuggler;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.enums.Item;
import eu.mcone.lobby.api.player.LobbyPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;

class MaterialBuyInventorySmuggler extends CoreInventory {

    MaterialBuyInventorySmuggler(Player p) {
        super("§8» §7§lSchmugler", p, InventorySlot.ROW_4, InventoryOption.FILL_EMPTY_SLOTS);
        LobbyPlayer lp = LobbyPlugin.getInstance().getGamePlayer(p);

        setItem(InventorySlot.ROW_1_SLOT_3, new ItemBuilder(Material.IRON_DOOR, 1, 0).displayName("§7§l↩ Zurück").create(), e -> new SmugglerInventory(p));
        setItem(InventorySlot.ROW_1_SLOT_5, new ItemBuilder(Material.PAPER, 1, 0).displayName("§c§lMaterialien kaufen").lore("§7§oKaufe hier Materialen mit Coins.", "§7§oDie Items stehen dir danach in", "§7§odeinem Rucksack zur Verfügung.").create());
        setItem(InventorySlot.ROW_1_SLOT_7, new ItemBuilder(Material.EMERALD, 1, 0).displayName("§a§lDein Kontostand").lore("§7§oDein Kontostand beträgt:", "§f§o" + lp.getCorePlayer().getCoins() + "§7§o Coins").create());


        //DIAMOND
        setItem(InventorySlot.ROW_3_SLOT_5, Item.MATERIAL_DIAMOND_4.getItemStack(), e -> {
            if (!Item.MATERIAL_DIAMOND_4.has(lp)) {
                Item.MATERIAL_DIAMOND_4.add(lp);
                p.sendMessage("§8[§7§l!§8] §fServer §8» §7Du hast ein 4 Diamanten bekommen");
            } else {
                p.sendMessage("§8[§7§l!§8] §fServer §8» §4Du besitzt dieses Item bereits!");
            }
            p.closeInventory();
        });


        //IRON
        setItem(InventorySlot.ROW_3_SLOT_6, Item.MATERIAL_IRON_2.getItemStack(), e -> {
            if (!Item.MATERIAL_IRON_2.has(lp)) {
                Item.MATERIAL_IRON_2.add(lp);
                p.sendMessage("§8[§7§l!§8] §fServer §8» §7Du hast ein 2 Eisen bekommen");
            } else {
                p.sendMessage("§8[§7§l!§8] §fServer §8» §4Du besitzt diese Item bereits!");
            }
            p.closeInventory();
        });


        setItem(InventorySlot.ROW_3_SLOT_7, Item.MATERIAL_IRON_4.getItemStack(), e -> {
            if (!Item.MATERIAL_IRON_4.has(lp)) {
                Item.MATERIAL_IRON_4.add(lp);
                p.sendMessage("§8[§7§l!§8] §fServer §8» §7Du hast ein 4 Eisen bekommen");
            } else {
                p.sendMessage("§8[§7§l!§8] §fServer §8» §4Du besitzt diese Item bereits!");
            }
            p.closeInventory();
        });


        setItem(InventorySlot.ROW_3_SLOT_8, Item.MATERIAL_IRON_6.getItemStack(), e -> {
            if (!Item.MATERIAL_IRON_6.has(lp)) {
                Item.MATERIAL_IRON_6.add(lp);
                p.sendMessage("§8[§7§l!§8] §fServer §8» §7Du hast ein 6 Eisen bekommen");
            } else {
                p.sendMessage("§8[§7§l!§8] §fServer §8» §4Du besitzt diese Item bereits!");
            }
            p.closeInventory();
        });


// 2, 4, 6, 8, 10


// 2 + 4 = 6
// 2 + 6 = 8
// 2 + 8 = 10
// 4 + 6 = 10


        openInventory();
    }
}

