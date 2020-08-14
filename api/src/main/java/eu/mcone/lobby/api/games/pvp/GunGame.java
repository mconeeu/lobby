/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.api.games.pvp;

import eu.mcone.lobby.api.games.LobbyPvpGame;
import org.bukkit.entity.Player;

public interface GunGame extends LobbyPvpGame {

    void setSaveMode(Player p);

    boolean isInSaveMode(Player p);

}
