/*
 * Copyright (c) 2017 Dominik L., Rufus Maiwald, BamDev and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.hologram;

import eu.mcone.lobby.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class HologramManager {

    private HashMap<String, Hologram> holograms;

    public HologramManager() {
//        String[] twitter = {"§b§l@mconeeu"};
//        this.holograms.put("twitter", new Hologram(twitter, new Location(Bukkit.getWorld(Main.config.getConfigValue("System-WorldName")), x, y, z)));
//
//        String[] fb = {"§9§l@mconeeu"};
//        this.holograms.put("fb", new Hologram(fb, new Location(Bukkit.getWorld(Main.config.getConfigValue("System-WorldName")), x, y, z)));
//
//        String[] yt = {"§c§lmcone.eu/yt"};
//        this.holograms.put("yt", new Hologram(yt, new Location(Bukkit.getWorld(Main.config.getConfigValue("System-WorldName")), x, y, z)));
//
//        String[] www = {"§7§lmcone.eu"};
//        this.holograms.put("www", new Hologram(www, new Location(Bukkit.getWorld(Main.config.getConfigValue("System-WorldName")), x, y, z)));
    }

    public void showHolograms(Player p) {
        for (Hologram hologram : this.holograms.values()) {
            hologram.showPlayer(p);
        }
    }
}
