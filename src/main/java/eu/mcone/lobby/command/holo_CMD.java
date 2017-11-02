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

public class holo_CMD implements CommandExecutor{

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        Player p = (Player)sender;
        if(cmd.getName().equalsIgnoreCase("setholo")){
            if(args.length == 1){
                if(args[0].equalsIgnoreCase("skypvp") || args[0].equalsIgnoreCase("minewar") || args[0].equalsIgnoreCase("knockbackffa") || args[0].equalsIgnoreCase("bedwars")){
                    if(p.hasPermission("system.admin")){
                        p.sendMessage(Main.config.getConfigValue("System-Prefix") + "§7Du hast das Hologramm für denn Spielmodus §3" + args[0] + " §7gesetzt");
                        LocationFactory.updateConfigLocation(p.getLocation(), Main.config, "Location-Holo-" + args[0]);
                    }
                }
            }
        }
        return true;
    }

}
