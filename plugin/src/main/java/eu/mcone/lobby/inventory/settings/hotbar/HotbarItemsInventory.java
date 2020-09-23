package eu.mcone.lobby.inventory.settings.hotbar;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.player.HotbarItem;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.api.player.hotbar.HotbarGeneralCategorys;
import eu.mcone.lobby.api.player.hotbar.items.enums.HotbarItemEnum;
import eu.mcone.lobby.inventory.settings.LobbySettingsInventory;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class HotbarItemsInventory extends CoreInventory {

    public HotbarItemsInventory(Player p, HotbarGeneralCategorys category) {
        super("§fWähle ein Item aus:", p, InventorySlot.ROW_4, InventoryOption.FILL_EMPTY_SLOTS);
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);

        int i = 0;

        for (HotbarItemEnum item : HotbarItemEnum.values()) {
            if (item.getCategorys().equals(category) && !item.equals(lp.getSettings().calculateItems().get(category))) {
                setItem(i, item.equals(HotbarItemEnum.SKULL) ? HotbarItem.getProfile(lp.getCorePlayer().getSkin()).create() : item.getItem().displayName(category.getDisplayName()).create(), e -> {
                    lp.getSettings().updateItem(category, item);
                    lp.saveData();

                    LobbyPlugin.getInstance().getHotbarSettings().updateInventory(p, lp);
                    LobbyPlugin.getInstance().getPlayerSounds().playSounds(p, Sound.CHICKEN_EGG_POP);

                    new LobbyHotbarSettingsInventory(p);
                });
                i++;
            }
        }

        setItem(InventorySlot.ROW_4_SLOT_9, BACK_ITEM, e ->
                new LobbySettingsInventory(p));

        LobbyPlugin.getInstance().getPlayerSounds().playSounds(player, Sound.NOTE_PLING);
        openInventory();
    }
}
