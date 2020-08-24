/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.api.player.settings;

import eu.mcone.lobby.api.player.settings.JoinPlayerVisibility;
import eu.mcone.lobby.api.player.settings.SpawnPoint;
import eu.mcone.lobby.api.player.settings.SpawnVillage;
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
            hotbarChangeSound = true, lobbyGamesInvite = true, scoreboard = true;
    private SpawnPoint spawnPoint = SpawnPoint.SPAWN;
    private SpawnVillage spawnVillage = SpawnVillage.RAISEN;
    private JoinPlayerVisibility joinPlayerVisibility = JoinPlayerVisibility.NO_PREFERENCE;

}
