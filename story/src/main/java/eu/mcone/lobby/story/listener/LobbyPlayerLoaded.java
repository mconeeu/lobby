/*
 * Copyright (c) 2017 - 2018 Dominik Lippl, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.story.listener;

import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.api.event.LobbyPlayerLoadedEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class LobbyPlayerLoaded implements Listener {

    @EventHandler
    public void on(LobbyPlayerLoadedEvent e) {
        if (e.getReason().equals(LobbyPlayerLoadedEvent.Reason.JOINED) && e.getPlayer().getProgressId() <= 1) {
            LobbyPlugin.getInstance().getLobbyWorld(LobbyWorld.DIM_1).teleportSilently(e.getPlayer().bukkit(), "Storyspawn");
        }
    }

}
