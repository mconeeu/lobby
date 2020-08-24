/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.api.games;

import org.bukkit.entity.Player;

import java.util.Set;

public interface LobbyPvpGame extends LobbyGame {

    Set<Player> getPlaying();

    void joinGame(Player p);

    void quitGame(Player p);

}
