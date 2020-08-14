/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.api.games.jumpnrun;

import eu.mcone.lobby.api.games.LobbyGame;
import org.bukkit.entity.Player;

public interface JumpNRunGame extends LobbyGame {

    void startGame(Player p, eu.mcone.lobby.api.enums.JumpNRun jumpNRun);

    void setCheckpoint(Player p, int checkpoint);

    void tpToCheckpoint(Player p);

    void finishGame(Player p);
}
