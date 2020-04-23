/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.api;

import eu.mcone.coresystem.api.bukkit.world.CoreWorld;
import lombok.Getter;

public enum LobbyWorld {

    ONE_ISLAND("Lobby-OneIsland"),
    OFFICE("Lobby-Office"),
    PARADISE_ISLAND("Lobby-ParadiseIsland"),
    CAVE("Lobby-Cave"),
    DESTROYED_PARADISE_ISLAND("Lobby-Destroyed-ParadiseIsland"),
    GUNGAME("gungame-1");

    @Getter
    private String name;

    LobbyWorld(String name) {
        this.name = name;
    }

    public CoreWorld getWorld() {
        return LobbyPlugin.getInstance().getLobbyWorld(this);
    }

}
