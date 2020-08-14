/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.games.scoreboard;

import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.coresystem.api.bukkit.scoreboard.CoreSidebarObjectiveEntry;
import eu.mcone.lobby.api.scoreboard.LobbyObjective;
import eu.mcone.lobby.games.AbstractLobbyPvPGame;

public class LobbyGameObjective extends LobbyObjective {

    private final AbstractLobbyPvPGame game;

    public LobbyGameObjective(AbstractLobbyPvPGame game) {
        super("Game-"+game.getName());
        this.game = game;
    }

    @Override
    protected void onRegister(CorePlayer corePlayer, CoreSidebarObjectiveEntry entry) {
        entry.setTitle("§7§l⚔ "+game.getColor()+"§l§nFangen");

        entry.setScore(5, "");
        entry.setScore(4, "§8» §7Spieler:");
        entry.setScore(3, " §f" + game.getPlaying().size());
        entry.setScore(2, "");
        entry.setScore(1, "§8»§7 Teamspeak:");
        entry.setScore(0, " §f§ots.mcone.eu");
    }

    @Override
    protected void onReload(CorePlayer corePlayer, CoreSidebarObjectiveEntry entry) {
        entry.setScore(3, " §f" + game.getPlaying().size());
    }

}
