package eu.mcone.lobby.api.player.hotbar;

import eu.mcone.lobby.api.player.HotbarItem;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.api.player.hotbar.items.enums.HotbarItemEnum;
import eu.mcone.lobby.api.player.hotbar.items.enums.SlotAmountEnum;
import lombok.Getter;

@Getter
public enum HotbarGeneralCategorys {

    NAVIGATOR("Navigator", "§3§lNavigator §8» §7§oWähle einen Spielmodus", SlotAmountEnum.FIRST, true),
    LOBBY_CHANGER("Lobby-Chooser", "§3§lLobby-Wechsler §8» §7§oWähle deine Lobby", SlotAmountEnum.SECOND, true),

    BACKPACK("Story-Items", "§3§lRucksack §8» §7§oZeige deine gesammelten Items an", SlotAmountEnum.FIVE, true),

    PLAYER_HIDER("PlayerHider", null, SlotAmountEnum.EIGHT, false),
    PROFILE("Profil", HotbarItem.PROFILE_DISPLAY_NAME, SlotAmountEnum.NINE, true);

    private final String name, displayName;
    private final SlotAmountEnum defaultSlot;
    private final boolean canBeModified;

    HotbarGeneralCategorys(String name, String displayName, SlotAmountEnum defaultSlot, boolean canBeModified) {
        this.name = name;
        this.displayName = displayName;
        this.defaultSlot = defaultSlot;
        this.canBeModified = canBeModified;
    }

    public static HotbarGeneralCategorys getById(SlotAmountEnum slot) {
        for (HotbarGeneralCategorys cat : values()) {
            if (cat.defaultSlot.equals(slot)) {
                return cat;
            }
        }

        return null;
    }

    public HotbarItemEnum getDefaultItem() {
        for (HotbarItemEnum item : HotbarItemEnum.values()) {
            if (item.getCategorys().equals(this) && item.isMainItem()) {
                return item;
            }
        }

        return null;
    }

    public HotbarItemEnum getItem(LobbyPlayer player) {
        if (canBeModified) {
            return player.getSettings().calculateItems().get(this);
        } else return null;
    }

}
