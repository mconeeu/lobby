package eu.mcone.lobby.inventory.settings.hotbar.slot;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.api.player.hotbar.HotbarGeneralCategorys;
import eu.mcone.lobby.api.player.hotbar.items.enums.SlotAmountEnum;
import eu.mcone.lobby.inventory.settings.LobbySettingsInventory;
import eu.mcone.lobby.inventory.settings.hotbar.LobbyHotbarSettingsInventory;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class LobbyHotbarSlotInventory extends CoreInventory {

    public LobbyHotbarSlotInventory(Player p, HotbarGeneralCategorys category) {
        super("§fWähle dein Slot aus:", p, InventorySlot.ROW_2, InventoryOption.FILL_EMPTY_SLOTS);
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);
        SlotAmountEnum currentSlot = lp.getSettings().calculateSlots().get(category);

        for (SlotAmountEnum slot : SlotAmountEnum.values()) {
            if (!currentSlot.equals(slot)) {
                setItem(
                        slot.getSlot(),
                        slot.getItemStack(),
                        e -> {
                            lp.getSettings().updateSlot(category, slot);
                            lp.saveData();

                            LobbyPlugin.getInstance().getHotbarSettings().updateInventory(p, lp);
                            LobbyPlugin.getInstance().getPlayerSounds().playSounds(p, Sound.CHICKEN_EGG_POP);

                            new LobbyHotbarSettingsInventory(p);
                        }
                );
            } else {
                setItem(
                        slot.getSlot(),
                        new ItemBuilder(Material.BARRIER).create()
                );
            }
        }

        setItem(InventorySlot.ROW_2_SLOT_9, BACK_ITEM, e ->
                new LobbySettingsInventory(p));

        LobbyPlugin.getInstance().getPlayerSounds().playSounds(player, Sound.NOTE_PLING);
        openInventory();
    }

}
