/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.api;

import eu.mcone.coresystem.api.bukkit.CorePlugin;
import eu.mcone.coresystem.api.bukkit.world.CoreWorld;
import eu.mcone.lobby.api.player.LobbyPlayer;
import lombok.Getter;
import org.bukkit.ChatColor;

import java.util.UUID;

public abstract class LobbyPlugin extends CorePlugin {

    @Getter
    private static LobbyPlugin instance;

    protected LobbyPlugin() {
        super("lobby", ChatColor.DARK_AQUA, "lobby.prefix");
        setInstance(this);
    }

    public abstract void registerLobbyPlayer(LobbyPlayer lp);

    public abstract CoreWorld getLobbyWorld(LobbyWorld world);

    public abstract LobbyPlayer getLobbyPlayer(UUID uuid);

    public abstract LobbyPlayer getLobbyPlayer(String name);

    private static void setInstance(LobbyPlugin plugin) {
        if (instance == null) {
            instance = plugin;
        } else {
            throw new UnsupportedOperationException("LobbyPlugin instance is already created!");
        }
    }

}
