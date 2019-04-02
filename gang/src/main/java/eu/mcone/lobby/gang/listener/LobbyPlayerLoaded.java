/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.gang.listener;

import eu.mcone.lobby.api.event.LobbyPlayerLoadedEvent;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.gang.LobbyGang;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class LobbyPlayerLoaded implements Listener {

    @EventHandler
    public void on(LobbyPlayerLoadedEvent e) {
        LobbyPlayer p = e.getPlayer();
        p.setGang(LobbyGang.getInstance().getPlayersGang(p.getCorePlayer().getUuid()));
    }

}
