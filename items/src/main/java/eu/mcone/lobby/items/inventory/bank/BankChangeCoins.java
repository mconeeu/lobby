package eu.mcone.lobby.items.inventory.bank;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.lobby.api.LobbyPlugin;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

class BankChangeCoins extends CoreInventory {

    /*
     * 100 Emeralds -> 5$
     * 280 Emeralds -> 15$
     * 450 Emeralds -> 25$
     */

    BankChangeCoins(Player p) {
        super("§8» §d§lBänker §8| §fBetrag", p, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);
        CorePlayer cp = CoreSystem.getInstance().getCorePlayer(p);

        setItem(InventorySlot.ROW_2_SLOT_3,
                new ItemBuilder(Material.EMERALD, 1, 0)
                        .displayName("§a§l100 Emeralds")
                        .lore(
                                "§f§n25.000 Coinss§f entsprechen §n100 Emeralds",
                                "",
                                "§8» §f§nLinksklick§8 | §7§oBestätigen")
                        .create(), e -> {
                    if (cp.getCoins() - 25000 >= 0) {
                        cp.removeCoins(25000);
                        cp.addEmeralds(100);
                        cp.getScoreboard().getObjective(DisplaySlot.SIDEBAR).reload();

                        LobbyPlugin.getInstance().getMessager().send(p, "Du has erfolgreich §f25.000 Coins§7 in §f100 Emeralds §fumgetauscht");
                        new BankMenInventory(p);
                    } else {
                        LobbyPlugin.getInstance().getMessager().send(p, "§cDu hast nicht genügend Coins!");
                        player.closeInventory();
                    }
                }
        );

        setItem(InventorySlot.ROW_2_SLOT_5,
                new ItemBuilder(Material.EMERALD, 1, 0)
                        .displayName("§a§l280 Emeralds")
                        .lore(
                                "§f§n75.000 Coins§f entsprechen §n280 Emeralds",
                                "",
                                "§8» §f§nLinksklick§8 | §7§oBestätigen")
                        .create(), e -> {
                    if (cp.getCoins() - 75000 >= 0) {
                        cp.removeCoins(75000);
                        cp.addEmeralds(280);
                        cp.getScoreboard().getObjective(DisplaySlot.SIDEBAR).reload();

                        LobbyPlugin.getInstance().getMessager().send(p, "Du has erfolgreich §f75.000 Coins§7 in §f280 Emeralds §fumgetauscht");
                        new BankMenInventory(p);
                    } else {
                        LobbyPlugin.getInstance().getMessager().send(p, "§cDu hast nicht genügend Coins!");
                        player.closeInventory();
                    }
                }
        );

        setItem(InventorySlot.ROW_2_SLOT_7,
                new ItemBuilder(Material.EMERALD, 1, 0)
                        .displayName("§a§l450 Emeralds")
                        .lore(
                                "§f§n165.000 Coins§f entsprechen §n450 Emeralds",
                                "",
                                "§8» §f§nLinksklick§8 | §7§oBestätigen")
                        .create(), e -> {
                    if (cp.getCoins() - 165000 >= 0) {
                        cp.removeCoins(165000);
                        cp.addEmeralds(450);
                        cp.getScoreboard().getObjective(DisplaySlot.SIDEBAR).reload();

                        LobbyPlugin.getInstance().getMessager().send(p, "Du has erfolgreich §f165.000 Coins§7 in §f450 Emeralds §fumgetauscht");
                        new BankMenInventory(p);
                    } else {
                        LobbyPlugin.getInstance().getMessager().send(p, "§cDu hast nicht genügend Coins!");
                        player.closeInventory();
                    }
                }
        );

        setItem(InventorySlot.ROW_3_SLOT_9,
                new ItemBuilder(Material.IRON_DOOR, 1, 0).displayName("§7Zurück").create(),
                e -> new BankMenInventory(p)
        );

        openInventory();
    }
}
