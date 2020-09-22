/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.api.player.settings;

import eu.mcone.lobby.api.player.hotbar.HotbarGeneralCategorys;
import eu.mcone.lobby.api.player.hotbar.items.HotbarItemEnum;
import eu.mcone.lobby.api.player.scoreboard.widgets.ScoreboardWidgets;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class LobbySettings {

    private boolean allowTrading = true, allowAnimation = true, realTime = true,
            rankBoots = true, inventoryAnimation = true, isStacking = true,
            hotbarChangeSound = true, lobbyGamesInvite = true, scoreboard = true, doNotDisturb = false, hotbarSilentHub = false;
    private SpawnPoint spawnPoint = SpawnPoint.SPAWN;
    private SpawnVillage spawnVillage = SpawnVillage.RAISEN;
    private JoinPlayerVisibility joinPlayerVisibility = JoinPlayerVisibility.NO_PREFERENCE;
    private SoundManager errorSound = SoundManager.NOTE_SNARE_DRUM;
    private SoundManager navigatorSound = SoundManager.ORB_PICKUP;

    private int compassSlot =  HotbarGeneralCategorys.NAVIGATOR.getSlot();
    private int lobbySwitcherSlot = HotbarGeneralCategorys.LOBBY_CHOOSER.getSlot();
    private int silentlobbySlot =  HotbarGeneralCategorys.SILENTLOBBY.getSlot();
    private int backpackSlot =  HotbarGeneralCategorys.BACKPACK.getSlot();
    private int nickSlot =  HotbarGeneralCategorys.NICK.getSlot();
    private int playerHiderSlot =  HotbarGeneralCategorys.PLAYER_HIDER.getSlot();
    private int profileSlot =  HotbarGeneralCategorys.PROFILE.getSlot();

    private HotbarItemEnum compassItem = HotbarItemEnum.COMPASS;
    private HotbarItemEnum lobbySwitcherItem = HotbarItemEnum.NETHER_STAR;
    private HotbarItemEnum backpackItem = HotbarItemEnum.MINECART_CHEST;
    private HotbarItemEnum profileItem = HotbarItemEnum.SKULL;

    private ScoreboardWidgets scoreboardWidgetsFirstLine = ScoreboardWidgets.COINS;
    private ScoreboardWidgets scoreboardWidgetsSecondLine = ScoreboardWidgets.EMERALD;

}
