/*
 * Copyright (c) 2017 - 2018 Dominik Lippl, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.api;

import eu.mcone.coresystem.api.bukkit.hologram.HologramManager;
import eu.mcone.coresystem.api.bukkit.npc.NpcManager;
import eu.mcone.coresystem.api.bukkit.world.BuildSystem;
import eu.mcone.coresystem.api.bukkit.world.LocationManager;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class Lobby extends JavaPlugin {

    @Getter
    private static Lobby instance;

    public Lobby() {}

    protected void setInstance(Lobby instance) {
        if (Lobby.instance != null) {
            System.err.println("LobbyPlugin instance cannot be set twice!");
        } else {
            Lobby.instance = instance;
        }
    }

    public abstract HologramManager getHologramManager();

    public abstract NpcManager getNpcManager();

    public abstract BuildSystem getBuildSystem();

    public abstract LocationManager getLocationManager();

}
