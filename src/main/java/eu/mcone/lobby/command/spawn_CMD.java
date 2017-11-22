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
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player p = (Player) commandSender;

        if (args.length == 0) {
            Location loc = LocationFactory.getConfigLocation(Main.config, "Location-Spawn");

            if (loc != null) {
                p.teleport(loc);
                p.sendMessage(Main.config.getConfigValue("System-Prefix") + "§2Du wurdest zum Spawn teleportiert");
            } else {
                p.sendMessage(Main.config.getConfigValue("System-Prefix") + "§4Du kannst gerade nicht zum Spawn teleportiert werden.");
            }
        } else if ((args.length == 1) && args[0].equals("set")) {
            if (p.hasPermission("lobby.set.spawn") || p.hasPermission("lobby.set.*") || p.hasPermission("lobby.*")) {
                LocationFactory.updateConfigLocation(p.getLocation(), Main.config, "Location-Spawn");
                p.sendMessage(Main.config.getConfigValue("System-Prefix") + "§2Der Spawn wurde erfolgreich gesetzt!");
            } else {
                p.sendMessage(Main.config.getConfigValue("System-Prefix") + "§4Du hast keine Berechtigung für diesen Befehl!");
            }
        } else {
            p.sendMessage(Main.config.getConfigValue("System-Prefix") + "§4Benutze /spawn um dich zum Spawn zu teleportieren");
        }

        return true;
    }
}
