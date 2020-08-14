/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.games;

import eu.mcone.lobby.api.LobbyAddon;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.games.LobbyGamesManager;
import eu.mcone.lobby.games.jumpnrun.JumpNRunLobbyGame;
import eu.mcone.lobby.games.listener.GeneralLobbyGameListener;
import eu.mcone.lobby.games.pvp.catchgame.CatchLobbyGame;
import eu.mcone.lobby.games.pvp.gungame.GunGameLobbyGame;
import eu.mcone.lobby.games.pvp.onehit.OneHitLobbyGame;
import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LobbyGames extends LobbyAddon implements LobbyGamesManager {

    @Getter
    private static LobbyGames instance;
    private final Set<AbstractLobbyGame> games = new HashSet<>();

    @Override
    public void onEnable(LobbyPlugin plugin) {
        instance = this;

        plugin.registerEvents(new GeneralLobbyGameListener(this));
        games.addAll(Arrays.asList(
                new OneHitLobbyGame(plugin), new CatchLobbyGame(plugin), new GunGameLobbyGame(plugin), new JumpNRunLobbyGame(plugin)
        ));
    }

    @Override
    public void onDisable(LobbyPlugin plugin) {
        for (AbstractLobbyGame game : games) {
            if (game instanceof AbstractLobbyPvPGame) {
                ((AbstractLobbyPvPGame) game).disable();
            }
        }
    }

    @Override
    public void reload(LobbyPlugin plugin) {}

    @Override
    public <T extends eu.mcone.lobby.api.games.LobbyGame> T getGame(Class<T> gameClass) {
        for (AbstractLobbyGame game : games) {
            if (game.getClass().isAssignableFrom(gameClass)) {
                return (T) game;
            }
        }

        return null;
    }

    @Override
    public eu.mcone.lobby.api.games.LobbyGame getCurrentGame(Player p) {
        for (AbstractLobbyGame game : games) {
            if (game.isPlaying(p)) {
                return game;
            }
        }

        return null;
    }

    @Override
    public boolean isPlaying(Player p) {
        return getCurrentGame(p) != null;
    }

    @Override
    public Set<eu.mcone.lobby.api.games.LobbyGame> getGames() {
        return new HashSet<>(games);
    }
}
