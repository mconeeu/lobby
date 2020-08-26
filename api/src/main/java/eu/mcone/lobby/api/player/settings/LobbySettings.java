/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.api.player.settings;

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
            hotbarChangeSound = true, lobbyGamesInvite = true, scoreboard = true, doNotDisturb = false;
    private SpawnPoint spawnPoint = SpawnPoint.SPAWN;
    private SpawnVillage spawnVillage = SpawnVillage.RAISEN;
    private JoinPlayerVisibility joinPlayerVisibility = JoinPlayerVisibility.NO_PREFERENCE;
    private SoundManager errorSound = SoundManager.NOTE_SNARE_DRUM;
    private SoundManager navigatorSound = SoundManager.ORB_PICKUP;

}
