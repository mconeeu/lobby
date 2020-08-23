package eu.mcone.lobby.story.inventory.office;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.story.office.OfficeType;
import eu.mcone.lobby.api.player.LobbyPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class UpgradeOfficeTrader extends CoreInventory {

    UpgradeOfficeTrader(Player p) {
        super("§8» §d§lUpgrade §8| §fBüro", p, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);

        int i = InventorySlot.ROW_2_SLOT_5;
        boolean add = false;
        for (OfficeType office : OfficeType.values()) {
            if (lp.hasLobbyItem(office.getOfficeCard())) {
                add = true;
            } else if (add) {
                setItem(i, OfficeTraderInventory.getItem(office), e -> new OfficeInventory(p, office));
                i += 2;
            }
        }

        if (!add) {
            throw new IllegalStateException("Could not open UpgradeOfficeTraderInventory! Player "+p.getName()+" does not have any office!");
        } else if (i == InventorySlot.ROW_2_SLOT_5) {
            setItem(
                    InventorySlot.ROW_2_SLOT_5,
                    new ItemBuilder(Material.IRON_DOOR)
                            .displayName("§cDu kannst dein Büro nicht mehr upgraden!")
                            .create()
            );
        }

        openInventory();
    }
}
