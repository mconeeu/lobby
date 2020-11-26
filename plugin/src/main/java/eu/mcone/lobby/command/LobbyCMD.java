/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.command;

import eu.mcone.coresystem.api.bukkit.command.CoreCommand;
import eu.mcone.lobby.Lobby;
import eu.mcone.lobby.api.LobbyAddon;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.player.LobbyPlayer;
import org.bukkit.command.CommandSender;

public class LobbyCMD extends CoreCommand {

    public LobbyCMD() {
        super("lobbyplugin", "lobby.admin", "lp");
    }

    @Override
    public boolean onCommand(CommandSender sender, String[] args) {
        if (args.length == 1 && (args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("rl"))) {
            for (LobbyPlayer lp : Lobby.getSystem().getOnlineLobbyPlayers()) {
                lp.reload();
            }
            for (LobbyAddon addon : Lobby.ADDONS) {
                addon.reload(LobbyPlugin.getInstance());
            }

            return true;
        } else if (args.length == 2 && (args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("rl"))) {
            String name = args[1];

            for (LobbyAddon addon : Lobby.ADDONS) {
                if (addon.getClass().getSimpleName().equalsIgnoreCase(name)) {
                    addon.reload(LobbyPlugin.getInstance());
                    return true;
                }
            }

            Lobby.getSystem().getMessenger().send(sender, "§4Ein Addon mit dem Name §c" + name + "§4 ist nicht geladen!");
            return false;
        }

        Lobby.getSystem().getMessenger().send(sender, "§4Bitte benutze; §c/lobby reload [<addon>]");
        return false;
    }

}
