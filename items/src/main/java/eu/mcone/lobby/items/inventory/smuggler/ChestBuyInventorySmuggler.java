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

import java.util.Random;

class ChestBuyInventorySmuggler extends CoreInventory {

    private static final Random SMUGGLER_RANDOM = new Random();
    private static int getRandomNumberInRange(int min, int max) {
        return SMUGGLER_RANDOM.nextInt((max - min) + 1) + min;
    }

    ChestBuyInventorySmuggler(Player p) {
        super("§8» §7§lSchmuggler §8| §fKisten", p, 9 * 3, InventoryOption.FILL_EMPTY_SLOTS);
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);

        setItem(11 - 1, new ItemBuilder(Material.PAPER, 1, 0).displayName("§8» §7Du besitzt §f" + lp.getChests() + "§7 Kisten").create());

        setItem(13 - 1, new ItemBuilder(Material.CHEST, 3, 0).displayName("§c§l3 Kisten").lore("§7Kosten: §f§o129 Emeralds", "§cDu kannst aber auch weniger", "§c§lKisten§c erhalten", "", "§8» §f§nLinksklick§8 | §7§oKaufen").create(), e -> {
            if ((lp.getCorePlayer().getEmeralds() - 129) >= 0) {
                lp.getCorePlayer().removeEmeralds(129);

                int chest = getRandomNumberInRange(1, 3);
                lp.addChests(chest);
                if (chest == 1) {
                    p.sendMessage("§8[§7§l!§8] §7Schmuggler §8» §7Du hast nur §a" + chest + "§7 Kiste bekommen weil die anderen §akaputt §7gegangen sind!");
                } else if (chest < 3) {
                    p.sendMessage("§8[§7§l!§8] §7Schmuggler §8» §7Du hast nur §a" + chest + "§7 Kisten bekommen weil die anderen §akaputt §7gegangen sind!");
                } else {
                    p.sendMessage("§8[§7§l!§8] §7Schmuggler §8» §7Du hast " + chest + " Kisten gekauft!");
                }
            } else {
                p.sendMessage("§8[§7§l!§8] §7Schmuggler §8» §4Du hast nicht genügend Emeralds!");
            }
            p.closeInventory();
        });


        setItem(15 - 1, new ItemBuilder(Material.CHEST, 7, 0).displayName("§c§l7 Kisten").lore("§7Kosten: §f§o297 Emeralds", "§cDu kannst aber auch weniger", "§c§lKisten§c erhalten", "", "§8» §f§nLinksklick§8 | §7§oKaufen").create(), e -> {
            if ((lp.getCorePlayer().getEmeralds() - 297) >= 0) {
                lp.getCorePlayer().removeEmeralds(297);

                int chest = getRandomNumberInRange(2, 7);
                lp.addChests(chest);
                if (chest < 7) {
                    p.sendMessage("§8[§7§l!§8] §7Schmuggler §8» §7Du hast nur §a" + chest + "§7 Kisten bekommen weil die anderen §akaputt §7gegangen sind!");
                } else {
                    p.sendMessage("§8[§7§l!§8] §7Schmuggler §8» §7Du hast " + chest + " Kisten gekauft!");
                }
            } else {
                p.sendMessage("§8[§7§l!§8] §7Schmuggler §8» §4Du hast nicht genügend Emeralds");
            }
            p.closeInventory();
        });


        setItem(17 - 1, new ItemBuilder(Material.CHEST, 12, 0).displayName("§c§l12 Kisten").lore("§7Kosten: §f§o526 Emeralds", "§cDu kannst aber auch weniger", "§c§lKisten §cerhalten", "", "§8» §f§nLinksklick§8 | §7§oKaufen").create(), e -> {
            if ((lp.getCorePlayer().getCoins() - 526) >= 0) {
                lp.getCorePlayer().removeEmeralds(526);
                int chest = getRandomNumberInRange(5, 12);
                lp.addChests(chest);
                if (chest < 12) {
                    p.sendMessage("§8[§7§l!§8] §7Schmuggler §8» §7Du hast nur §a" + chest + "§7 Kisten bekommen weil die anderen §akaputt §7gegangen sind!");
                } else {
                    p.sendMessage("§8[§7§l!§8] §7Schmuggler §8» §7Du hast " + chest + " Kisten gekauft!");
                }
            } else {
                p.sendMessage("§8[§7§l!§8] §7Schmuggler §8» §4Du hast nicht genügend Emeralds");
            }
            p.closeInventory();
        });

        openInventory();
    }
}
