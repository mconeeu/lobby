/*
 * Copyright (c) 2017 Dominik L., Rufus Maiwald, BamDev and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.command;

import eu.mcone.lobby.Main;
import de.Dominik.BukkitCoreSystem.util.LocationFactory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class set_CMD implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        Player p = (Player)sender;

        if (p.hasPermission("lobby.set.spawn") || p.hasPermission("lobby.set.*") || p.hasPermission("lobby.*")) {
            if (args.length != 1) {
                p.sendMessage(Main.config.getConfigValue("System-Prefix") + "§cBitte benutze /set <1, 2, 3, 4, 5, 6, 7, 8, 9>");
            }else if (args[0].equalsIgnoreCase("1")){
                p.sendMessage(Main.config.getConfigValue("System-Prefix") + "§aDu hast den 1 Point gesetzt!");
                LocationFactory.updateConfigLocation(p.getLocation(), Main.config, "Location-Navigator-1");
            }else if (args[0].equalsIgnoreCase("2")){
                p.sendMessage(Main.config.getConfigValue("System-Prefix") + "§aDu hast den 2 Point gesetzt!");
                LocationFactory.updateConfigLocation(p.getLocation(), Main.config, "Location-Navigator-2");
            }else if (args[0].equalsIgnoreCase("3")){
                p.sendMessage(Main.config.getConfigValue("System-Prefix") + "§aDu hast den 3 Point gesetzt!");
                LocationFactory.updateConfigLocation(p.getLocation(), Main.config, "Location-Navigator-3");
            }else if (args[0].equalsIgnoreCase("4")){
                p.sendMessage(Main.config.getConfigValue("System-Prefix") + "§aDu hast den 4 Point gesetzt!");
                LocationFactory.updateConfigLocation(p.getLocation(), Main.config, "Location-Navigator-4");
            }else if (args[0].equalsIgnoreCase("5")){
                p.sendMessage(Main.config.getConfigValue("System-Prefix") + "§aDu hast den 5 Point gesetzt!");
                LocationFactory.updateConfigLocation(p.getLocation(), Main.config, "Location-Navigator-5");
            }else if (args[0].equalsIgnoreCase("6")){
                p.sendMessage(Main.config.getConfigValue("System-Prefix") + "§aDu hast den 6 Point gesetzt!");
                LocationFactory.updateConfigLocation(p.getLocation(), Main.config, "Location-Navigator-6");
            }else if (args[0].equalsIgnoreCase("7")){
                p.sendMessage(Main.config.getConfigValue("System-Prefix") + "§aDu hast den 7 Point gesetzt!");
                LocationFactory.updateConfigLocation(p.getLocation(), Main.config, "Location-Navigator-7");
            }else if (args[0].equalsIgnoreCase("8")){
                p.sendMessage(Main.config.getConfigValue("System-Prefix") + "§aDu hast den 8 Point gesetzt!");
                LocationFactory.updateConfigLocation(p.getLocation(), Main.config, "Location-Navigator-8");
            }else if (args[0].equalsIgnoreCase("9")){
                p.sendMessage(Main.config.getConfigValue("System-Prefix") + "§aDu hast den 9 Point gesetzt!");
                LocationFactory.updateConfigLocation(p.getLocation(), Main.config, "Location-Navigator-9");
            }
        } else {
            p.sendMessage(Main.config.getConfigValue("System-Prefix") + "§4Du hast keine Berechtigung für diesen Befehl!");
        }
        return false;
    }

}
