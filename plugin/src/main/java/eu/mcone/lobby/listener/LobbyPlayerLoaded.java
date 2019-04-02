/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.listener;

import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.event.LobbyPlayerLoadedEvent;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.util.SilentLobbyUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class LobbyPlayerLoaded implements Listener {

    @EventHandler
    public void on(LobbyPlayerLoadedEvent e) {
        LobbyPlayer p = e.getPlayer();
        Player bp = p.bukkit();

        if (e.getReason().equals(LobbyPlayerLoadedEvent.Reason.JOINED) && p.getSettings().isSilentHubActivatedOnJoin()) {
            Bukkit.getScheduler().runTask(LobbyPlugin.getInstance(), () -> SilentLobbyUtils.activateSilentLobby(bp));
            LobbyPlugin.getInstance().getMessager().send(bp, "§2Du bist in der §aPrivaten Lobby§2 gespawnt. Hier bist du vollkommen ungestört!");
        }
    }

}
