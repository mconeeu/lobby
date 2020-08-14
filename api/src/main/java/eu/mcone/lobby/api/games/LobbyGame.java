/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.api.games;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public interface LobbyGame {

    String getName();

    ChatColor getColor();

    String[] getShortNames();

    boolean isPlaying(Player p);

    void quitGame(Player p);

}
