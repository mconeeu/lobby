/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.items.inventory.trader;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.util.ItemBuilder;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.player.LobbyPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;

class ChestBuyInventory extends CoreInventory {

    ChestBuyInventory(Player p) {
        super("§8» §e§lHändler §8| §fKisten kaufen", p, InventorySlot.ROW_3, Option.FILL_EMPTY_SLOTS);
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p.getUniqueId());

        setItem(InventorySlot.ROW_2_SLOT_2, new ItemBuilder(Material.PAPER, 1, 0).displayName("§8» §7Du besitzt §f" +lp.getChests() + "§7 Kisten").create());

        setItem(InventorySlot.ROW_2_SLOT_4, new ItemBuilder(Material.CHEST, 1, 0).displayName("§c§l1 Kiste").lore("§7Kosten: §f§o100 Coins", "", "§8» §f§nLinksklick§8 | §7§oKaufen").create(), e -> {
            if (lp.getCorePlayer().getCoins() - 100 >= 0) {
                lp.getCorePlayer().removeCoins(100);
                lp.addChests(1);

                p.sendMessage("§8[§7§l!§8] §eHändler §8» §aDu hast 1 Kiste gekauft!");
            } else {
                p.sendMessage("§8[§7§l!§8] §eHändler §8» §4Du hast nicht genügend Coins");
            }
            p.closeInventory();
        });


        setItem(InventorySlot.ROW_2_SLOT_6, new ItemBuilder(Material.CHEST, 5, 0).displayName("§c§l5 Kisten").lore("§7Kosten: §f§o475 Coins", "", "§8» §f§nLinksklick§8 | §7§oKaufen").create(), e -> {
            if (lp.getCorePlayer().getCoins() - 475 >= 0) {
                p.sendMessage("§8[§7§l!§8] §eHändler §8» §aDu hast 5 Kisten gekauft!");

                lp.getCorePlayer().removeCoins(475);
                lp.addChests(5);
            } else {
                p.sendMessage("§8[§7§l!§8] §eHändler §8» §4Du hast nicht genügend Coins");
            }
            p.closeInventory();
        });


        setItem(InventorySlot.ROW_2_SLOT_8, new ItemBuilder(Material.CHEST, 10, 0).displayName("§c§l10 Kisten").lore("§7Kosten: §f§o950 Coins", "", "§8» §f§nLinksklick§8 | §7§oKaufen").create(), e -> {
            if (lp.getCorePlayer().getCoins() - 950 >= 0) {
                p.sendMessage("§8[§7§l!§8] §eHändler §8» §aDu hast 10 Kisten gekauft!");

                lp.getCorePlayer().removeCoins(950);
                lp.addChests(10);
            } else {
                p.sendMessage("§8[§7§l!§8] §eHändler §8» §4Du hast nicht genügend Coins");
            }
            p.closeInventory();
        });

        openInventory();
    }
}
