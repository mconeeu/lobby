/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.api.games;

import org.bukkit.entity.Player;

import java.util.Set;

public interface LobbyGamesManager {

    Set<LobbyGame> getGames();

    <T extends LobbyGame> T getGame(Class<T> gameClass);

    LobbyGame getCurrentGame(Player p);

    boolean isPlaying(Player p);
}
