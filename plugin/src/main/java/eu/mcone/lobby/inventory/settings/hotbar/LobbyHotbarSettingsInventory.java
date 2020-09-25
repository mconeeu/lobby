package eu.mcone.lobby.inventory.settings.hotbar;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.inventory.category.CategoryInventory;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.player.HotbarItem;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.api.player.hotbar.HotbarGeneralCategorys;
import eu.mcone.lobby.api.player.hotbar.items.enums.SlotAmountEnum;
import eu.mcone.lobby.inventory.settings.LobbySettingsInventory;
import eu.mcone.lobby.inventory.settings.hotbar.slot.LobbyHotbarSlotInventory;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class LobbyHotbarSettingsInventory extends CoreInventory {

    public LobbyHotbarSettingsInventory(Player p) {
        super("§8» §c§lHotbar Items", p, InventorySlot.ROW_5, InventoryOption.FILL_EMPTY_SLOTS);
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);
        CorePlayer cp = lp.getCorePlayer();

        int i = InventorySlot.ROW_1_SLOT_2;

        for (SlotAmountEnum slot : SlotAmountEnum.values()) {
            HotbarGeneralCategorys category = getCategory(slot, lp);

            if (category.equals(HotbarGeneralCategorys.PLAYER_HIDER)) {
                setItem(i, LobbyPlugin.getInstance().getVanishManager().getVanishPlayerVisibility(p).getItem());
            } else {
                setItem(
                        i, (category.equals(HotbarGeneralCategorys.PROFILE)
                                ? HotbarItem.getProfile(cp.getSkin())
                                : category.getDefaultItem().getItem()
                        ).displayName(category.getDisplayName()).create()
                );
            }

            setItem(i + 9 + 9, slot.getItemStack(), e -> new LobbyHotbarSlotInventory(p, category));

            setItem(
                    i + 9 + 9 + 9,
                    getModifyItem(category, p, cp, lp),
                    !category.equals(HotbarGeneralCategorys.PLAYER_HIDER) ? e -> new HotbarItemsInventory(p, category) : null
            );

            if (i == InventorySlot.ROW_1_SLOT_3 || i == InventorySlot.ROW_1_SLOT_5) {
                i += 2;
            } else {
                i++;
            }
        }

        setItem(InventorySlot.ROW_5_SLOT_1, ItemBuilder.wrap(CategoryInventory.REFRESH_ITEM).displayName("§cZurücksetzen").lore("§7§oSetze alle Hotbar", "§7§oSettings zurück!").create(), e -> {
            lp.getSettings().resetSlotsAndItems();
            lp.saveData();

            LobbyPlugin.getInstance().getHotbarSettings().updateInventory(p, lp);
            p.closeInventory();
            LobbyPlugin.getInstance().getMessenger().sendSuccess(p, "Deine ![Items und Slots] wurden erfolgreich zurückgesetzt");
        });

        setItem(InventorySlot.ROW_5_SLOT_9, BACK_ITEM, e ->
                new LobbySettingsInventory(p));

        openInventory();
    }

    private HotbarGeneralCategorys getCategory(SlotAmountEnum slot, LobbyPlayer lp) {
        for (Map.Entry<HotbarGeneralCategorys, SlotAmountEnum> entry : lp.getSettings().calculateSlots().entrySet()) {
            if (entry.getValue().equals(slot)) {
                return entry.getKey();
            }
        }

        return null;
    }

    private ItemStack getModifyItem(HotbarGeneralCategorys category, Player p, CorePlayer cp, LobbyPlayer lp) {
        if (category.equals(HotbarGeneralCategorys.PROFILE) && category.getItem(lp).isMainItem()) {
            return HotbarItem.getProfile(cp.getSkin()).displayName(category.getDisplayName()).create();
        } else if (category.equals(HotbarGeneralCategorys.PLAYER_HIDER)) {
            return new ItemBuilder(Material.BARRIER).displayName("§c✗").lore("§7§oDu kannst dieses Item", "§7§onicht modifizieren!", "§7§oDu kannst nur", "§7§oDen Slot wechseln!").create();
        } else {
            return category.getItem(lp).getItem().displayName(category.getDisplayName()).create();
        }
    }

}
