/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.api;

import lombok.Getter;

public enum LobbyWorld {

    DIM_1("Lobby"),
    DIM_2("Lobby-Dimension2"),
    DIM_3("Lobby-Dimension3"),
    DIM_4("Lobby-Dimension4");

    @Getter
    private String name;

    LobbyWorld(String name) {
        this.name = name;
    }

}
