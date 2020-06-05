/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.gang.command;

import eu.mcone.coresystem.api.bukkit.command.CorePlayerCommand;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.gang.Gang;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.gang.LobbyGang;
import org.bukkit.entity.Player;

import java.util.UUID;

public class GangCMD extends CorePlayerCommand {

    private final LobbyGang api;

    public GangCMD(LobbyGang api) {
        super("gang", null, "g");
        this.api = api;
    }

    @Override
    public boolean onPlayerCommand(Player p, String[] args) {
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);

        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("msg") || args[0].equalsIgnoreCase("chat")) {
                Gang gang = lp.getGang();

                if (gang != null) {
                    StringBuilder builder = new StringBuilder();

                    for (int i = 1; i < args.length; i++) {
                        builder.append(args[i]);
                        if (i < args.length - 1) builder.append(" ");
                    }

                    gang.broadcast(Gang.getPrefix(gang, p.getUniqueId().toString()) + p.getName() + " §8» §7" + builder.toString());
                } else {
                    p.sendMessage(Gang.GANG_PREFIX + "§4Du bist in keiner Gang!");
                }
            } else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("reload")) {
                    if (p.hasPermission("lobby.adventure.gang.reload")) {
                        api.reload();
                    }

                    return true;
                }
            } else if (args.length == 2) {
                String cursor = args[1];

                if (args[0].equalsIgnoreCase("invite")) {
                    Gang gang = lp.getGang();

                    if (gang != null) {
                        gang.invitePlayer(p, args[1]);
                    } else {
                        p.sendMessage(Gang.GANG_PREFIX + "§4Du bist in keiner Gang!");
                    }

                    return true;
                } else if (args[0].equalsIgnoreCase("kick")) {
                    Gang gang = lp.getGang();

                    if (gang != null) {
                        gang.kickPlayer(p, args[1]);
                    }

                    return true;
                } else if (args[0].equalsIgnoreCase("promote")) {
                    Gang gang = lp.getGang();

                    if (gang != null) {
                        gang.promotePlayer(p, args[1]);
                    } else {
                        p.sendMessage(Gang.GANG_PREFIX + "§4Du bist in keiner Gang!");
                    }

                    return true;
                } else if (args[0].equalsIgnoreCase("demote")) {
                    Gang gang = lp.getGang();

                    if (gang != null) {
                        gang.demotePlayer(p, args[1]);
                    } else {
                        p.sendMessage(Gang.GANG_PREFIX + "§4Du bist in keiner Gang!");
                    }

                    return true;
                } else if (args[0].equalsIgnoreCase("changeName")) {
                    Gang gang = lp.getGang();

                    if (gang != null) {
                        gang.changeName(p, args[1]);
                    } else {
                        p.sendMessage(Gang.GANG_PREFIX + "§4Du bist in keiner Gang!");
                    }

                    return true;
                } else if (args[0].equalsIgnoreCase("accept")) {
                    if (!lp.isInGang()) {
                        if (cursor != null && api.notifyIfGangNotExists(UUID.fromString(cursor), p)) {
                            api.getGang(UUID.fromString(args[1])).acceptInvite(p);
                        }
                    } else {
                        p.sendMessage(Gang.GANG_PREFIX + "§4Du bist bereits in einer Gang!");
                    }

                    return true;
                } else if (args[0].equalsIgnoreCase("decline")) {
                    if (!lp.isInGang()) {
                        if (cursor != null && api.notifyIfGangNotExists(UUID.fromString(cursor), p)) {
                            api.getGang(UUID.fromString(args[1])).declineInvite(p);
                        }
                    } else {
                        p.sendMessage(Gang.GANG_PREFIX + "§4Du bist bereits in einer Gang");
                    }

                    return true;
                }
            }
        }
        return true;
    }

}
