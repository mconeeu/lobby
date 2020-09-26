package eu.mcone.lobby.inventory.settings.shortcuts;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.lobby.inventory.settings.LobbySettingsInventory;
import org.bukkit.entity.Player;

public class ShortCutsInventory extends CoreInventory {

    public ShortCutsInventory(Player p) {
        super("§8» §c§lShortcuts", p, InventorySlot.ROW_4, InventoryOption.FILL_EMPTY_SLOTS);






        setItem(InventorySlot.ROW_4_SLOT_9, BACK_ITEM, e ->
                new LobbySettingsInventory(p));

        openInventory();

    }
}
