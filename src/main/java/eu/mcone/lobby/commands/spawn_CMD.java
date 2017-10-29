/*
 * Copyright (c) 2017 Dominik L., Rufus Maiwald, BamDev and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.commands;

import eu.mcone.lobby.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class spawn_CMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player p = (Player) commandSender;

        if (args.length == 0) {
            File file = new File("plugins//MCONE-Lobby//spawns.yml");

            if(file.exists() && !file.isDirectory()) {
                YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
                String w = cfg.getString("Spawn.WeltName");
                double x = cfg.getDouble("Spawn.X");
                double y = cfg.getDouble("Spawn.Y");
                double z = cfg.getDouble("Spawn.Z");
                double yaw = cfg.getDouble("Spawn.Yaw");
                double pitch = cfg.getDouble("Spawn.Pitch");
                Location loc = new Location(Bukkit.getWorld(w), x, y, z);
                loc.setYaw((float) yaw);
                loc.setPitch((float) pitch);

                p.sendMessage(Main.config.getConfigValue("Prefix") + "§2Du wirst zum Spawn teleportiert!");
                p.teleport(loc);
            } else {
                p.sendMessage(Main.config.getConfigValue("System-Prefix") + "§4Du kannst gerade nicht zum Spawn teleportiert werden.");
            }
        } else if ((args.length == 1) && args[0].equals("set")) {
            if (p.hasPermission("group.admin") || p.hasPermission("group.developer")) {
                File file = new File("plugins//Lobby//spawns.yml");
                YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
                p.sendMessage(Main.config.getConfigValue("Prefix") + "§7Der Spawn wurde gesetzt!");
                cfg.set("Spawn.X", p.getLocation().getX());
                cfg.set("Spawn.Y", p.getLocation().getY());
                cfg.set("Spawn.Z", p.getLocation().getZ());
                cfg.set("Spawn.Yaw", p.getLocation().getYaw());
                cfg.set("Spawn.Pitch", p.getLocation().getPitch());
                cfg.set("Spawn.WeltName", p.getLocation().getWorld().getName());

                try {
                    cfg.save(file);
                }
                catch (Exception e1) {
                    p.sendMessage(Main.config.getConfigValue("System-Prefix") + "§4Der Spawn konnte nicht gesetzte werden. Plugin-Error");
                }
            } else {
                p.sendMessage(Main.config.getConfigValue("System-Prefix") + "§4Du hast keine Berechtigung für diesen Befehl!");
            }
        } else {
            p.sendMessage(Main.config.getConfigValue("System-Prefix") + "§4Benutze /spawn um dich zum Spawn zu teleportieren");
        }

        return true;
    }
}
