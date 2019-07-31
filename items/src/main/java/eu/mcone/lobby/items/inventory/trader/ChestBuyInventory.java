/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.items.inventory.trader;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.player.LobbyPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;

class ChestBuyInventory extends CoreInventory {

    ChestBuyInventory(Player p) {
        super("§8» §e§lHändler §8| §fKisten kaufen", p, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);
        CorePlayer cp = CoreSystem.getInstance().getCorePlayer(p);
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p.getUniqueId());

        setItem(InventorySlot.ROW_2_SLOT_2, new ItemBuilder(Material.PAPER, 1, 0).displayName("§8» §7Du besitzt §f" +lp.getChests() + "§7 Kisten").create());

        setItem(InventorySlot.ROW_2_SLOT_4, new ItemBuilder(Material.CHEST, 1, 0).displayName("§c§l1 Kiste").lore("§7Kosten: §f§o50 Emeralds", "", "§8» §f§nLinksklick§8 | §7§oKaufen").create(), e -> {
            if (cp.getEmeralds() - 50 >= 0) {
                cp.removeEmeralds(50);
                lp.addChests(1);

                p.sendMessage("§8[§7§l!§8] §eHändler §8» §aDu hast 1 Kiste gekauft!");
            } else {
                p.sendMessage("§8[§7§l!§8] §eHändler §8» §4Du hast nicht genügend Emeralds!");
            }
            p.closeInventory();
        });


        setItem(InventorySlot.ROW_2_SLOT_6, new ItemBuilder(Material.CHEST, 5, 0).displayName("§c§l5 Kisten").lore("§7Kosten: §f§o250 Emeralds!", "", "§8» §f§nLinksklick§8 | §7§oKaufen").create(), e -> {
            if (cp.getEmeralds() - 250 >= 0) {
                cp.removeEmeralds(250);
                lp.addChests(5);

                p.sendMessage("§8[§7§l!§8] §eHändler §8» §aDu hast 5 Kisten gekauft!");
            } else {
                p.sendMessage("§8[§7§l!§8] §eHändler §8» §4Du hast nicht genügend Emeralds!");
            }
            p.closeInventory();
        });


        setItem(InventorySlot.ROW_2_SLOT_8, new ItemBuilder(Material.CHEST, 10, 0).displayName("§c§l10 Kisten").lore("§7Kosten: §f§o500 Emeralds", "", "§8» §f§nLinksklick§8 | §7§oKaufen").create(), e -> {
            if (cp.getCoins() - 500 >= 0) {
                cp.removeCoins(500);
                lp.addChests(10);

                p.sendMessage("§8[§7§l!§8] §eHändler §8» §aDu hast 10 Kisten gekauft!");
            } else {
                p.sendMessage("§8[§7§l!§8] §eHändler §8» §4Du hast nicht genügend Emeralds");
            }
            p.closeInventory();
        });

        openInventory();
    }
}
