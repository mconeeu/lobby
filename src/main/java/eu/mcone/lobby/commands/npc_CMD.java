/*
 * Copyright (c) 2017 Dominik L., BamDev, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.commands;

import eu.mcone.lobby.utils.NPC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class npc_CMD implements CommandExecutor{

    private NPC npc;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        Player p = (Player)sender;
        if(cmd.getName().equalsIgnoreCase("npc")){
            if (args.length == 2) {
                if (args[0].equalsIgnoreCase("set")) {
                    npc = new NPC(p.getLocation(), args[1]);
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
        } else {
            return true;
        }
    }

}
