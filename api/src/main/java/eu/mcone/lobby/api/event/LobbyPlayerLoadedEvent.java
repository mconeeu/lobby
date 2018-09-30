/*
 * Copyright (c) 2017 - 2018 Dominik Lippl, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.api.event;

import eu.mcone.lobby.api.player.LobbyPlayer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

@AllArgsConstructor
@Getter
public final class LobbyPlayerLoadedEvent extends Event {

    @Getter
    private final static HandlerList handlerList = new HandlerList();

    private LobbyPlayer player;
    private Reason reason;

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public enum Reason {
        JOINED, RELOADED
    }

}
