/*
 * Copyright (c) 2017 Dominik L., Rufus Maiwald, BamDev and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.command;

import eu.mcone.lobby.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class holo_CMD implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (args.length >= 3) {
                if (args[0].equalsIgnoreCase("add")) {
                    StringBuilder line = new StringBuilder();
                    for (int i = 2; i < args.length; i++) {
                        line.append(args[i]).append(" ");
                    }

                    Main.holo.addHologram(args[1], p.getLocation(), line.toString());
                    p.sendMessage(Main.config.getConfigValue("System-Prefix") + "§2Hologramm §f"+args[1]+"§2 erfolgreich hinzugefügt!");
                    return true;
                }
            } else if (args.length == 2) {
                if (args[0].equalsIgnoreCase("remove")) {
                    Main.holo.removeHologram(args[1]);
                    p.sendMessage(Main.config.getConfigValue("System-Prefix") + "§2Hologramm §f"+args[1]+"§2 erfolgreich gelöscht!");
                    return true;
                }
            } else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("list")) {
                    StringBuilder result = new StringBuilder();
                    result.append(Main.config.getConfigValue("System-Prefix")).append("§7Diese Hologramme sind gerade geladen:\n");
                    int i = Main.holo.getHolograms().keySet().size();
                    for (String h : Main.holo.getHolograms().keySet()) {
                        result.append("§3").append(h);
                        if (i<=1) continue;
                        result.append("§7, ");
                        i--;
                    }
                    p.sendMessage(result.toString());
                    return true;
                } else if (args[0].equalsIgnoreCase("reload")) {
                    Main.holo.updateHolograms();
                    p.sendMessage(Main.config.getConfigValue("System-Prefix") + "§2Hologramme erfolgreich neu geladen!");
                }
            }
        }
        return true;
    }

}
