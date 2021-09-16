/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.games.listener;

import eu.mcone.coresystem.api.bukkit.event.BuildModeChangeEvent;
import eu.mcone.coresystem.api.bukkit.event.player.AfkEvent;
import eu.mcone.coresystem.api.bukkit.facades.Msg;
import eu.mcone.coresystem.api.core.player.PlayerState;
import eu.mcone.lobby.api.games.LobbyGame;
import eu.mcone.lobby.games.AbstractLobbyGame;
import eu.mcone.lobby.games.LobbyGames;
import lombok.RequiredArgsConstructor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerQuitEvent;

@RequiredArgsConstructor
public class GeneralLobbyGameListener implements Listener {

    private final LobbyGames manager;

    @EventHandler
    public void onAfk(AfkEvent e) {
        Player p = e.getPlayer();

        if (LobbyGames.getInstance().isPlaying(p)) {
            if (e.getState().equals(PlayerState.AFK)) {
                LobbyGames.getInstance().getCurrentGame(p).quitGame(p);
                Msg.send(p, "§4Du wurdest automatisch von deiner Lobby Aktivität gekickt!");
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        for (LobbyGame game : manager.getGames()) {
            ((AbstractLobbyGame) game).playerLeaved(e.getPlayer());
        }
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e) {
        Player p = e.getPlayer();

        if (e.getMessage().equalsIgnoreCase("/spawn")) {
            for (LobbyGame game : manager.getGames()) {
                if (game.isPlaying(p)) {
                    ((AbstractLobbyGame) game).quitGame(p);
                }
            }
        } else if (e.getMessage().equalsIgnoreCase("/profil") || e.getMessage().equalsIgnoreCase("/profile")) {
            if (manager.isPlaying(p)) {
                e.setCancelled(true);
                Msg.sendError(p, "Du darfst diesen Befehl nicht in einem Lobby Game ausführen!");
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBuildLeave(BuildModeChangeEvent e) {
        Player p = e.getPlayer();

        if (!e.isCanBuild() && manager.isPlaying(p)) {
            p.setGameMode(GameMode.ADVENTURE);
        }
    }

}
