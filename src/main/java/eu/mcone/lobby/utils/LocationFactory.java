/*
 * Copyright (c) 2017 Dominik L., BamDev, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class LocationFactory {

    public static File cfgFile = new File("plugins//McOne-Lobby//spawns.yml");
    public static YamlConfiguration cfg = YamlConfiguration.loadConfiguration(cfgFile);

    public static void createConfigLocation(Location loc, String path, File file, YamlConfiguration cfg){

        cfg.set(path + ".World", loc.getWorld().getName());
        cfg.set(path + ".X", loc.getX());
        cfg.set(path + ".y", loc.getY());
        cfg.set(path + ".Z", loc.getZ());
        cfg.set(path + ".Yaw", loc.getYaw());
        cfg.set(path + ".Pitch", loc.getPitch());
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Location getConfigLocation(String path, YamlConfiguration cfg){

        World w = Bukkit.getWorld(cfg.getString(path + ".World"));
        double x = cfg.getDouble(path + ".X");
        double y = cfg.getDouble(path + ".y");
        double z = cfg.getDouble(path + ".Z");
        float yaw = (float) cfg.getDouble(path + ".Yaw");
        float pitch = (float) cfg.getDouble(path + ".Pitch");

        return new Location(w, x, y, z, yaw, pitch);
    }

}
