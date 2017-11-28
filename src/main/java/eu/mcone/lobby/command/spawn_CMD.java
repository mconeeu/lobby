/*
 * Copyright (c) 2017 Dominik L., Rufus Maiwald, BamDev and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.command;

import eu.mcone.lobby.Main;
import de.Dominik.BukkitCoreSystem.util.LocationFactory;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class spawn_CMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (args.length == 0) {
                Location loc = LocationFactory.getConfigLocation(Main.config, "Location-Spawn");

                if (loc != null) {
                    p.teleport(loc);
                    p.sendMessage(Main.config.getConfigValue("System-Prefix") + "§2Du wurdest zum Spawn teleportiert");
                } else {
                    p.sendMessage(Main.config.getConfigValue("System-Prefix") + "§4Du kannst gerade nicht zum Spawn teleportiert werden.");
                }
                return true;
            } else if (args.length == 2 && args[0].equalsIgnoreCase("set")) {
                if (p.hasPermission("lobby.set.spawn")) {
                    if (args[1].equals("main")) {
                        LocationFactory.updateConfigLocation(p.getLocation(), Main.config, "Location-Spawn");
                    } else if (args[0].equalsIgnoreCase("1")) {
                        LocationFactory.updateConfigLocation(p.getLocation(), Main.config, "Location-Navigator-1");
                    } else if (args[0].equalsIgnoreCase("2")) {
                        LocationFactory.updateConfigLocation(p.getLocation(), Main.config, "Location-Navigator-2");
                    } else if (args[0].equalsIgnoreCase("3")) {
                        LocationFactory.updateConfigLocation(p.getLocation(), Main.config, "Location-Navigator-3");
                    } else if (args[0].equalsIgnoreCase("4")) {
                        LocationFactory.updateConfigLocation(p.getLocation(), Main.config, "Location-Navigator-4");
                    } else if (args[0].equalsIgnoreCase("5")) {
                        LocationFactory.updateConfigLocation(p.getLocation(), Main.config, "Location-Navigator-5");
                    } else if (args[0].equalsIgnoreCase("6")) {
                        LocationFactory.updateConfigLocation(p.getLocation(), Main.config, "Location-Navigator-6");
                    } else if (args[0].equalsIgnoreCase("7")) {
                        LocationFactory.updateConfigLocation(p.getLocation(), Main.config, "Location-Navigator-7");
                    } else if (args[0].equalsIgnoreCase("8")) {
                        LocationFactory.updateConfigLocation(p.getLocation(), Main.config, "Location-Navigator-8");
                    } else if (args[0].equalsIgnoreCase("9")) {
                        LocationFactory.updateConfigLocation(p.getLocation(), Main.config, "Location-Navigator-9");
                    } else {
                        p.sendMessage("§4Bitte benutze: §c/spawn set <main | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9>");
                        return true;
                    }

                    p.sendMessage(Main.config.getConfigValue("System-Prefix") + "§2Der Spawn wurde erfolgreich gesetzt!");
                    return true;
                }
            }

            p.sendMessage(Main.config.getConfigValue("System-Prefix") + "§4Benutze §c/spawn §4um dich zum Spawn zu teleportieren");
        }
        return true;
    }
}
