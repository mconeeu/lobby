/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.api;

import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.coresystem.api.bukkit.world.CoreWorld;
import eu.mcone.gameapi.api.GamePlugin;
import eu.mcone.gameapi.api.Option;
import eu.mcone.lobby.api.liveevent.LiveEventManager;
import eu.mcone.lobby.api.office.OfficeManager;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.api.player.PlayerHiderManager;
import eu.mcone.lobby.api.player.SilentLobbyManager;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.UUID;

public abstract class LobbyPlugin extends GamePlugin {

    @Getter
    private static LobbyPlugin instance;

    protected LobbyPlugin() {
        super(
                "lobby",
                ChatColor.DARK_AQUA,
                "lobby.prefix",
                "https://d48ec02dd8e14cc7b3b5f9bf938d14d0@o267551.ingest.sentry.io/5355225?stacktrace.app.packages=eu.mcone.lobby",
                Option.BACKPACK_MANAGER_REGISTER_ALL_DEFAULT_CATEGORIES
        );
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

    public abstract <T extends LobbyAddon> T getAddon(Class<? extends T> addonClass);

    public abstract LiveEventManager getLiveEventManager();

    public abstract OfficeManager getOfficeManager();

    public abstract SilentLobbyManager getSilentLobbyManager();

    public abstract PlayerHiderManager getPlayerHiderManager();

    public abstract LobbyPlayer getLobbyPlayer(CorePlayer cp);

    public abstract LobbyPlayer getLobbyPlayer(Player p);

    public abstract LobbyPlayer getLobbyPlayer(UUID uuid);

    public abstract LobbyPlayer getLobbyPlayer(String name);

    public abstract Collection<LobbyPlayer> getOnlineLobbyPlayers();

    public abstract void resetPlayerDataAndHotbarItems(Player p);

}
