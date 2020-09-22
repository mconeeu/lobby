package eu.mcone.lobby.api.player.hotbar;

import lombok.Getter;

@Getter
public enum HotbarGeneralCategorys {

    NAVIGATOR("Navigator", 0, 0),
    LOBBY_CHOOSER("Lobby-Chooser", 1, 0),
    SILENTLOBBY("SilentHub",2, 0),

    //TODO: SPECIAL ITEM 2 / 3
    //FALLBACK WHEN SILENTLOBBY
    GADGET("Gadgets",2, 3),

    BACKPACK("Story-Items", 4, 0),
    NICK("Nick", 6, 0),
    PLAYER_HIDER("PlayerHider",7, 0),
    PROFILE("Profile", 8, 0);

    private final int slot;
    private final int fallback;
    private final String name;

    HotbarGeneralCategorys(String name, int slot, int fallback) {
        this.name = name;
        this.slot = slot;
        this.fallback = fallback;
    }
}
