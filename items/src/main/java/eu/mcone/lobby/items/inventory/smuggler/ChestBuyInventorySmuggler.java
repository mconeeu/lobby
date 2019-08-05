/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.items.inventory.smuggler;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.player.LobbyPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;

class ChestBuyInventorySmuggler extends CoreInventory {

    ChestBuyInventorySmuggler(Player p) {
        super("§8» §7§lSchmuggler §8| §fKisten", p, 9 * 3, InventoryOption.FILL_EMPTY_SLOTS);
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p.getUniqueId());

        setItem(11 - 1, new ItemBuilder(Material.PAPER, 1, 0).displayName("§8» §7Du besitzt §f" + lp.getChests() + "§7 Kisten").create());

        setItem(13 - 1, new ItemBuilder(Material.CHEST, 3, 0).displayName("§c§l3 Kiste").lore("§7Kosten: §f§o139 Emeralds", "", "§8» §f§nLinksklick§8 | §7§oKaufen").create(), e -> {
            if ((lp.getCorePlayer().getEmeralds() - 139) >= 0) {
                lp.getCorePlayer().removeEmeralds(139);
                lp.addChests(3);

                p.sendMessage("§8[§7§l!§8] §7Schmuggler §8» §aDu hast 3 Kisten gekauft!");
            } else {
                p.sendMessage("§8[§7§l!§8] §7Schmuggler §8» §4Du hast nicht genügend Emeralds!");
            }
            p.closeInventory();
        });


        setItem(15 - 1, new ItemBuilder(Material.CHEST, 7, 0).displayName("§c§l7 Kisten").lore("§7Kosten: §f§o342 Emeralds", "", "§8» §f§nLinksklick§8 | §7§oKaufen").create(), e -> {
            if ((lp.getCorePlayer().getEmeralds() - 342) >= 0) {
                p.sendMessage("§8[§7§l!§8] §7Schmuggler §8» §aDu hast 7 Kisten gekauft!");

                lp.getCorePlayer().removeEmeralds(342);
                lp.addChests(7);
            } else {
                p.sendMessage("§8[§7§l!§8] §7Schmuggler §8» §4Du hast nicht genügend Emeralds");
            }
            p.closeInventory();
        });


        setItem(17 - 1, new ItemBuilder(Material.CHEST, 12, 0).displayName("§c§l12 Kisten").lore("§7Kosten: §f§o916 Emeralds", "", "§8» §f§nLinksklick§8 | §7§oKaufen").create(), e -> {
            if ((lp.getCorePlayer().getCoins() - 586) >= 0) {
                p.sendMessage("§8[§7§l!§8] §7Schmuggler §8» §aDu hast 12 Kisten gekauft!");

                lp.getCorePlayer().removeCoins(586);
                lp.addChests(12);
            } else {
                p.sendMessage("§8[§7§l!§8] §7Schmuggler §8» §4Du hast nicht genügend Emeralds");
            }
            p.closeInventory();
        });

        openInventory();
    }
}
