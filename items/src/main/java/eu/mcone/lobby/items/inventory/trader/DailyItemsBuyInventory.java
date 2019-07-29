package eu.mcone.lobby.items.inventory.trader;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.enums.Item;
import eu.mcone.lobby.api.player.LobbyPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

public class DailyItemsBuyInventory extends CoreInventory {

    DailyItemsBuyInventory(Player player, Item toBuy) {
        super("§8» §e§lHändler §8| §fkaufen", player, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);
        LobbyPlayer lobbyPlayer = LobbyPlugin.getInstance().getLobbyPlayer(player.getUniqueId());
        CorePlayer corePlayer = CoreSystem.getInstance().getCorePlayer(player.getUniqueId());

        setItem(InventorySlot.ROW_1_SLOT_5, toBuy.getItemStack());
        setItem(InventorySlot.ROW_2_SLOT_3, new ItemBuilder(Material.STAINED_GLASS, 1, 13).displayName("§aKaufen")
                .lore("§7Kaufe das Item " + toBuy.getName(),
                        "§7für §a§l" + toBuy.getEmeralds() + " §aEmeralds"
                ).create(), e -> {
            if (!lobbyPlayer.getItems().contains(toBuy)) {
                if ((corePlayer.getEmeralds() - toBuy.getEmeralds()) >= 0) {
                    corePlayer.removeEmeralds(toBuy.getEmeralds());
                    corePlayer.getScoreboard().getObjective(DisplaySlot.SIDEBAR).reload();
                    lobbyPlayer.addItem(toBuy);

                    player.closeInventory();
                    player.sendMessage("§8[§7§l!§8] §eHändler §8» §2Du hast das Item §a" + toBuy.getName() + " §2für §f" + toBuy.getEmeralds() + " Emeralds §2erfolgreich gekauft!");
                } else {
                    LobbyPlugin.getInstance().getMessager().send(player, "Du hast nicht genügen §a§lEmeralds!");
                }
                player.closeInventory();
            } else {
                LobbyPlugin.getInstance().getMessager().send(player, "§4Du besitzt diese Item bereits!");
            }
        });

        setItem(InventorySlot.ROW_2_SLOT_7, new ItemBuilder(Material.STAINED_GLASS, 1, 14).displayName("§cAbbrechen").create(), e -> new DailyItemsInventory(player));

        openInventory();
    }
}
