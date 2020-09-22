package eu.mcone.lobby.inventory.settings.hotbar;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.api.player.hotbar.HotbarGeneralCategorys;
import eu.mcone.lobby.api.player.hotbar.items.HotbarItemEnum;
import eu.mcone.lobby.inventory.settings.LobbySettingsInventory;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class HotbarItemsInventory extends CoreInventory {

    public HotbarItemsInventory(Player p, HotbarGeneralCategorys categorys) {
        super("§fWähle ein Item aus:", p, InventorySlot.ROW_4, InventoryOption.FILL_EMPTY_SLOTS);
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);

        int i = 0;

        for (HotbarItemEnum hotbarItemEnum : HotbarItemEnum.values()) {
            if (hotbarItemEnum.getCategorys().equals(categorys)) {
                setItem(i, hotbarItemEnum.getItem().displayName("").create(), e -> {

                    if (categorys.equals(HotbarGeneralCategorys.NAVIGATOR)) {
                        lp.getSettings().setCompassItem(hotbarItemEnum);
                    } else if (categorys.equals(HotbarGeneralCategorys.BACKPACK)) {
                        lp.getSettings().setBackpackItem(hotbarItemEnum);
                    } else if (categorys.equals(HotbarGeneralCategorys.LOBBY_CHOOSER)) {
                        lp.getSettings().setLobbySwitcherItem(hotbarItemEnum);
                    } else if (categorys.equals(HotbarGeneralCategorys.PROFILE)) {
                        lp.getSettings().setProfileItem(hotbarItemEnum);
                    }

                    setSettings(p, lp);

                });
                i++;
            }
        }
        LobbyPlugin.getInstance().getPlayerSounds().playSounds(player, Sound.NOTE_PLING);


        setItem(InventorySlot.ROW_4_SLOT_9, BACK_ITEM, e ->
                new LobbySettingsInventory(p));

        openInventory();
    }

    private void setSettings(Player p, LobbyPlayer lp) {
        lp.saveData();
        LobbyPlugin.getInstance().getHotbarSettings().updateInventory(p, lp);
        LobbyPlugin.getInstance().getPlayerSounds().playSounds(p, Sound.CHICKEN_EGG_POP);
        new LobbyHotbarSettingsInventory(p);
    }
}
