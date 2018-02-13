/*
 * Copyright (c) 2017 - 2018 Dominik L., Rufus Maiwald, BamDev and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.command;

import eu.mcone.coresystem.bukkit.CoreSystem;
import eu.mcone.coresystem.bukkit.util.LocationFactory;
import eu.mcone.lobby.Lobby;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (!CoreSystem.getInstance().getCooldownSystem().canExecute(this.getClass(), p)) return true;
            CoreSystem.getInstance().getCooldownSystem().addPlayer(p.getUniqueId(), this.getClass());

            if (args.length == 0) {
                Location loc = LocationFactory.getConfigLocation(Lobby.config, "Location-spawn");

                if (loc != null) {
                    p.sendMessage(Lobby.config.getConfigValue("System-Prefix") + "§2Du wirst zum Spawn teleportiert");
                    p.teleport(loc);
                } else {
                    p.sendMessage(Lobby.config.getConfigValue("System-Prefix") + "§4Du kannst gerade nicht zum Spawn teleportiert werden.");
                }
                return true;
            } else if (args.length == 2 && args[0].equalsIgnoreCase("set")) {
                if (p.hasPermission("lobby.set.spawn")) {
                    if (args[1].equalsIgnoreCase("spawn")) {
                        LocationFactory.updateConfigLocation(p.getLocation(), Lobby.config, "Location-spawn");
                    } else if (args[1].equalsIgnoreCase("bedwars")) {
                        LocationFactory.updateConfigLocation(p.getLocation(), Lobby.config, "Location-bedwars");
                    } else if (args[1].equalsIgnoreCase("skypvp")) {
                        LocationFactory.updateConfigLocation(p.getLocation(), Lobby.config, "Location-skypvp");
                    } else if (args[1].equalsIgnoreCase("knockit")) {
                        LocationFactory.updateConfigLocation(p.getLocation(), Lobby.config, "Location-knockit");
                    } else if (args[1].equalsIgnoreCase("minewar")) {
                        LocationFactory.updateConfigLocation(p.getLocation(), Lobby.config, "Location-minewar");
                    } else if (args[1].equalsIgnoreCase("build")) {
                        LocationFactory.updateConfigLocation(p.getLocation(), Lobby.config, "Location-build");
                    } else {
                        p.sendMessage("§4Bitte benutze: §c/spawn set <spawn | bedwars | skypvp | knockit | minewar | build>");
                        return true;
                    }

                    p.sendMessage(Lobby.config.getConfigValue("System-Prefix") + "§2Der Spawn wurde erfolgreich gesetzt!");
                    return true;
                }
            }

            p.sendMessage(Lobby.config.getConfigValue("System-Prefix") + "§4Benutze §c/spawn §4um dich zum Spawn zu teleportieren");
        } else {
            Bukkit.getConsoleSender().sendMessage(Lobby.config.getConfigValue("System-Prefix") + "§4Dieser Befehl kann nur von einem Spieler ausgeführt werden!");
        }
        return true;
    }
}
