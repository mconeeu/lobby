package eu.mcone.lobby.items.inventory.vendor;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.gamesystem.api.enums.Category;
import eu.mcone.gamesystem.api.enums.Item;
import eu.mcone.gamesystem.api.game.player.GamePlayer;
import eu.mcone.gamesystem.api.lobby.backpack.BackpackInventory;
import eu.mcone.lobby.api.LobbyPlugin;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class VendorSellInventory extends CoreInventory {

    VendorSellInventory(Player p, Item toSell) {
        super("§8» §e§lKäufer §8| §fItem", p, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);
        GamePlayer lobbyPlayer = LobbyPlugin.getInstance().getGamePlayer(p.getUniqueId());
        CorePlayer corePlayer = CoreSystem.getInstance().getCorePlayer(player.getUniqueId());

        setItem(InventorySlot.ROW_1_SLOT_5, toSell.getItemStack());
        setItem(InventorySlot.ROW_2_SLOT_3, new ItemBuilder(Material.STAINED_GLASS, 1, 13).displayName("§aVerkaufen")
                .lore("§7Verkaufe das Item " + toSell.getName(),
                        "§7für §a§l" + toSell.getSellemeralds() + " §aEmeralds"
                ).create(), e -> {

            lobbyPlayer.removeItem(toSell);
            corePlayer.addEmeralds(toSell.getSellemeralds());
            player.sendMessage("§8[§7§l!§8] §eKäufer §8» §2Du hast das Item §a" + toSell.getName() + " §2für §f" + toSell.getSellemeralds() + " Emeralds §2erfolgreich §2verkauft!");
            BackpackInventory.openNewInventory(Category.GADGET, p);
        });

        setItem(InventorySlot.ROW_2_SLOT_7, new ItemBuilder(Material.STAINED_GLASS, 1, 14).displayName("§cAbbrechen").create(), e -> VendorInventory.openNewInventory(toSell.getCategory(), p));

        openInventory();
    }
}
