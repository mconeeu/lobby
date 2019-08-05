/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.api;

import eu.mcone.coresystem.api.bukkit.gamemode.Gamemode;
import eu.mcone.coresystem.api.bukkit.world.CoreWorld;
import eu.mcone.gamesystem.api.GameTemplate;
import eu.mcone.lobby.api.player.LobbyPlayer;
import lombok.Getter;
import org.bukkit.ChatColor;

import java.util.Collection;
import java.util.UUID;

public abstract class LobbyPlugin extends GameTemplate {

    @Getter
    private static LobbyPlugin instance;

    protected LobbyPlugin() {
        super("lobby", Gamemode.UNDEFINED, ChatColor.DARK_AQUA, "lobby.prefix", GameSystemOptions.USE_BACKPACK, GameSystemOptions.USE_ITEM_CARDS);
        setInstance(this);
    }

    public abstract void registerLobbyPlayer(LobbyPlayer lp);

    public abstract void unregisterLobbyPlayer(LobbyPlayer lp);

    public abstract CoreWorld getLobbyWorld(LobbyWorld world);

    public abstract LobbyPlayer getLobbyPlayer(UUID uuid);

    public abstract LobbyPlayer getLobbyPlayer(String name);

    public abstract Collection<LobbyPlayer> getLobbyPlayers();

    private static void setInstance(LobbyPlugin plugin) {
        if (instance == null) {
            instance = plugin;
        } else {
            throw new UnsupportedOperationException("LobbyPlugin instance is already created!");
        }
    }

}
