package eu.mcone.lobby.items.inventory.trader;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.gameapi.api.backpack.defaults.DefaultItem;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.player.LobbyPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

public class DailyItemsBuyInventory extends CoreInventory {

    DailyItemsBuyInventory(Player p, DefaultItem toBuy) {
        super("§8» §e§lHändler §8| §fDailyShop Item kaufen", p, InventorySlot.ROW_4, InventoryOption.FILL_EMPTY_SLOTS);
        LobbyPlayer lp = LobbyPlugin.getInstance().getGamePlayer(p);
        CorePlayer cp = lp.getCorePlayer();

        setItem(InventorySlot.ROW_1_SLOT_5, toBuy.getItemStack());
        setItem(InventorySlot.ROW_3_SLOT_3, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 13).displayName("§aKaufen")
                .lore("§7Kaufe das Item " + toBuy.getName(),
                        "§7für §a§l" + toBuy.getBuyemeralds() + " §aEmeralds"
                ).create(), e -> {
            if ((cp.getEmeralds() - toBuy.getBuyemeralds()) >= 0) {
                cp.removeEmeralds(toBuy.getBuyemeralds());
                cp.getScoreboard().getObjective(DisplaySlot.SIDEBAR).reload();
                toBuy.add(lp);

                p.closeInventory();
                p.sendMessage("§8[§7§l!§8] §eHändler §8» §2Du hast das Item §a" + toBuy.getName() + " §2für §f" + toBuy.getBuyemeralds() + " Emeralds §2erfolgreich gekauft!");
            } else {
                LobbyPlugin.getInstance().getMessager().send(p, "Du hast nicht genügen §a§lEmeralds!");
            }
            p.closeInventory();
        });

        setItem(InventorySlot.ROW_3_SLOT_7, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 14).displayName("§cAbbrechen").create(), e -> new DailyItemsInventory(p));

        openInventory();
    }
}
