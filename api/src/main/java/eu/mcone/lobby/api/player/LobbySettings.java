/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.api.player;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.spigotmc.event.player.PlayerSpawnLocationEvent;

@NoArgsConstructor
@Getter @Setter
@ToString
public class LobbySettings {

    private boolean allowTrading;
    private String spawnLocation = "SPAWN";

}
