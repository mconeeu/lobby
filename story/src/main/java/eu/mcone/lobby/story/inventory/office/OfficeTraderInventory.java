package eu.mcone.lobby.story.inventory.office;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.items.LobbyItem;
import eu.mcone.lobby.api.story.office.OfficeType;
import eu.mcone.lobby.api.player.LobbyPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class OfficeTraderInventory extends CoreInventory {

    public OfficeTraderInventory(Player p) {
        super("§8» §d§lVerkäufer §8| §fBüro", p, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);

        if (lp.hasLobbyItem(LobbyItem.OFFICE_CARD_BRONZE) || lp.hasLobbyItem(LobbyItem.OFFICE_CARD_SILVER)) {
            new UpgradeOfficeTrader(p);
        } else if (lp.hasLobbyItem(LobbyItem.OFFICE_CARD_GOLD)) {
            setItem(InventorySlot.ROW_2_SLOT_5, new ItemBuilder(Material.IRON_DOOR).displayName("§cDu kannst kein Büro mehr kaufen!").create());
            openInventory();
        } else {
            int i = InventorySlot.ROW_2_SLOT_3;
            for (OfficeType office : OfficeType.values()) {
                setItem(i, getItem(office), e -> new OfficeInventory(p, office));
                i += 2;
            }

            openInventory();
        }
    }

    static ItemStack getItem(OfficeType office) {
        return new ItemBuilder(office.getItem(), 1, 0)
                .displayName("§l"+office.getLabel()+" Büro")
                .lore(
                        "§fWelche Ihnhalte dieses Büro hat",
                        "§ferfahren sie wenn sie klicken",
                        "",
                        "§a§lDie Kosten liegen bei "+office.getEmeraldPrice()+" Emeralds"
                )
                .create();

    }

}





