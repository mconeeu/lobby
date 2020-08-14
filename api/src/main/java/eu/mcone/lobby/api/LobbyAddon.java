/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.api;

public abstract class LobbyAddon {

    public abstract void onEnable(LobbyPlugin plugin);

    public abstract void onDisable(LobbyPlugin plugin);

    public abstract void reload(LobbyPlugin plugin);

}
