/*
 * Copyright (c) 2017 Dominik L., Rufus Maiwald, BamDev and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.command;

import eu.mcone.lobby.util.NPC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class npc_CMD implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("npc")) {
            if (p.hasPermission("lobby.set.npc") || p.hasPermission("lobby.set.*") || p.hasPermission("lobby.*")) {
                if (args.length == 2) {
                    if (args[0].equalsIgnoreCase("set")) {
                        new NPC(p.getLocation(), args[1]);
                        NPC.set(p, args[1]);
                        return true;
                    } else if (args[0].equalsIgnoreCase("unset")) {
                        NPC.unset(p, args[1]);
                        return true;
                    } else {
                        p.sendMessage("unknown arg");
                        return true;
                    }
                } else {
                    p.sendMessage("args.length != 1 || 2");
                    return true;
                }
            }
        }
        return true;
    }

}
