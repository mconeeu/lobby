/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.api;

import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.coresystem.api.bukkit.world.CoreWorld;
import eu.mcone.gameapi.api.GamePlugin;
import eu.mcone.gameapi.api.Option;
import eu.mcone.lobby.api.jumpnrun.JumpNRunManager;
import eu.mcone.lobby.api.onehit.OneHitManager;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.api.player.PlayerHiderManager;
import eu.mcone.lobby.api.player.SilentLobbyManager;
import eu.mcone.lobby.api.trap.CatchManager;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.UUID;

public abstract class LobbyPlugin extends GamePlugin {

    @Getter
    private static LobbyPlugin instance;

    protected LobbyPlugin() {
        super("lobby", ChatColor.DARK_AQUA, "lobby.prefix", Option.BACKPACK_MANAGER_REGISTER_ALL_DEFAULT_CATEGORIES, Option.BACKPACK_MANAGER_USE_RANK_BOOTS);
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

    public abstract CatchManager getCatchManager();

    public abstract JumpNRunManager getJumpNRunManager();

    public abstract SilentLobbyManager getSilentLobbyManager();

    public abstract PlayerHiderManager getPlayerHiderManager();

    public abstract LobbyPlayer getLobbyPlayer(CorePlayer cp);

    public abstract LobbyPlayer getLobbyPlayer(Player p);

    public abstract LobbyPlayer getLobbyPlayer(UUID uuid);

    public abstract LobbyPlayer getLobbyPlayer(String name);

    public abstract Collection<LobbyPlayer> getOnlineLobbyPlayers();

}
