package eu.mcone.lobby.api.player.hotbar;

import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.api.player.hotbar.items.HotbarItem;
import eu.mcone.lobby.api.player.hotbar.items.HotbarSlot;
import lombok.Getter;

@Getter
public enum HotbarCategory {

    NAVIGATOR("Navigator", "§3§lNavigator §8» §7§oWähle einen Spielmodus", HotbarSlot.FIRST, true),
    LOBBY_CHANGER("Lobby-Chooser", "§3§lLobby-Wechsler §8» §7§oWähle deine Lobby", HotbarSlot.SECOND, true),

    BACKPACK("Story-Items", "§3§lRucksack §8» §7§oZeige deine gesammelten Items an", HotbarSlot.FIVE, true),

    PLAYER_HIDER("PlayerHider", null, HotbarSlot.EIGHT, false),
    PROFILE("Profil", eu.mcone.lobby.api.player.HotbarItem.PROFILE_DISPLAY_NAME, HotbarSlot.NINE, true);

    private final String name, displayName;
    private final HotbarSlot defaultSlot;
    private final boolean canBeModified;

    HotbarCategory(String name, String displayName, HotbarSlot defaultSlot, boolean canBeModified) {
        this.name = name;
        this.displayName = displayName;
        this.defaultSlot = defaultSlot;
        this.canBeModified = canBeModified;
    }

    public static HotbarCategory getById(HotbarSlot slot) {
        for (HotbarCategory cat : values()) {
            if (cat.defaultSlot.equals(slot)) {
                return cat;
            }
        }

        return null;
    }

    public HotbarItem getDefaultItem() {
        for (HotbarItem item : HotbarItem.values()) {
            if (item.getCategorys().equals(this) && item.isMainItem()) {
                return item;
            }
        }

        return null;
    }

    public HotbarItem getItem(LobbyPlayer player) {
        if (canBeModified) {
            return player.getSettings().calculateItems().get(this);
        } else return null;
    }

}
