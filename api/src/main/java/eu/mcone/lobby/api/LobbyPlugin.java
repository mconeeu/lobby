/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.api;

import eu.mcone.coresystem.api.bukkit.world.CoreWorld;
import eu.mcone.gameapi.api.GamePlugin;
import eu.mcone.gameapi.api.Option;
import eu.mcone.lobby.api.jumpnrun.JumpNRunManager;
import eu.mcone.lobby.api.onehit.OneHitManager;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.api.player.PlayerHiderManager;
import eu.mcone.lobby.api.player.SilentLobbyManager;
import lombok.Getter;
import org.bukkit.ChatColor;

public abstract class LobbyPlugin extends GamePlugin<LobbyPlayer> {

    @Getter
    private static LobbyPlugin instance;

    protected LobbyPlugin() {
        super("lobby", ChatColor.DARK_AQUA, "lobby.prefix", Option.BACKPACK_MANAGER_REGISTER_ALL_DEFAULT_CATEGORIES);
        setInstance(this);
    }

    private static void setInstance(LobbyPlugin plugin) {
        if (instance == null) {
            instance = plugin;
        } else {
            throw new UnsupportedOperationException("LobbyPlugin instance is already created!");
        }
    }

    public abstract CoreWorld getLobbyWorld(LobbyWorld world);

    public abstract OneHitManager getOneHitManager();

    public abstract JumpNRunManager getJumpNRunManager();

    public abstract SilentLobbyManager getSilentLobbyManager();

    public abstract PlayerHiderManager getPlayerHiderManager();

}
