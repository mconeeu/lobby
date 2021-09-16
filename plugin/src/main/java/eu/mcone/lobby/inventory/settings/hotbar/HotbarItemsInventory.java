package eu.mcone.lobby.inventory.settings.hotbar;

import eu.mcone.coresystem.api.bukkit.facades.Sound;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.api.player.hotbar.HotbarCategory;
import eu.mcone.lobby.api.player.hotbar.items.HotbarItem;
import org.bukkit.entity.Player;

public class HotbarItemsInventory extends CoreInventory {

    public HotbarItemsInventory(Player p, HotbarCategory category) {
        super("§fWähle ein Item aus:", p, InventorySlot.ROW_4, InventoryOption.FILL_EMPTY_SLOTS);
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);

        int i = 0;

        for (HotbarItem item : HotbarItem.values()) {
            if (item.getCategorys().equals(category) && !item.equals(lp.getSettings().calculateItems().get(category))) {
                setItem(i, item.equals(HotbarItem.SKULL) ? eu.mcone.lobby.api.player.HotbarItem.getProfile(lp.getCorePlayer().getSkin()).create() : item.getItem().displayName(category.getDisplayName()).create(), e -> {
                    lp.getSettings().updateItem(category, item);
                    lp.saveData();

                    LobbyPlugin.getInstance().getHotbarSettings().updateInventory(p, lp);
                    Sound.click(p);

                    new LobbyHotbarSettingsInventory(p);
                });
                i++;
            }
        }

        setItem(InventorySlot.ROW_4_SLOT_9, BACK_ITEM, e ->
                new LobbyHotbarSettingsInventory(p));

        openInventory();
    }
}
