/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.games.command;

import eu.mcone.coresystem.api.bukkit.command.CorePlayerCommand;
import eu.mcone.coresystem.api.bukkit.facades.Msg;
import eu.mcone.lobby.api.games.pvp.GunGame;
import eu.mcone.lobby.games.LobbyGames;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class GunGameTeamCMD extends CorePlayerCommand {

    private static final HashMap<Player, Player> Team = new HashMap<>();
    private static final ArrayList<Player> isInviting = new ArrayList<>();

    public GunGameTeamCMD() {
        super("team");
    }

    @Override
    public boolean onPlayerCommand(Player player, String[] args) {
        if (!Team.containsKey(player) && !Team.containsValue(player)) {
            if (LobbyGames.getInstance().getCurrentGame(player) instanceof GunGame) {
                if (args.length == 2) {
                    if (args[0].equalsIgnoreCase("add")) {
                        Player target = Bukkit.getPlayer(args[1]);
                        if (target != null) {
                            if (!target.getName().equalsIgnoreCase(player.getName())) {
                                if (LobbyGames.getInstance().getCurrentGame(target) instanceof GunGame) {
                                    if (!Team.containsValue(target) && !Team.containsKey(target)) {
                                        Msg.send(player, "§7Du hast §f" + target.getName() + "§7 zu deinem Team eingeladen");

                                        isInviting.add(player);

                                        target.spigot().sendMessage(
                                                new ComponentBuilder("")
                                                        .append("§fDer Spieler §l" + player.getName() + " §fhat dich zu seinem Gungame Team eingeladen")
                                                        .append("\n")
                                                        .append("[Annehmen]")
                                                        .color(ChatColor.GREEN)
                                                        .bold(true)
                                                        .event(new HoverEvent(
                                                                HoverEvent.Action.SHOW_TEXT,
                                                                new ComponentBuilder("§7§oKlicke hier, um dich zum Büro zu teleportieren").create()
                                                        ))
                                                        .event(new ClickEvent(
                                                                ClickEvent.Action.RUN_COMMAND,
                                                                "/team accept" + player.getName()
                                                        ))
                                                        .create()
                                        );

                                    } else {
                                        Msg.send(player, "§4Der Spieler ist bereits in einem anderen Team");
                                    }
                                } else {
                                    Msg.send(player, "§4Der Spieler spielt nicht Gungame!");
                                }
                            } else {
                                Msg.send(player, "§4Du kannst dich nicht selbst einladen!");
                            }
                        } else {
                            Msg.send(player, "§4Der Spieler ist nicht Online!");
                        }
                    } else if (args[0].equalsIgnoreCase("remove")) {

                    } else if (args[0].equalsIgnoreCase("accept")) {
                        Player target = Bukkit.getPlayer(args[1]);
                        if (target != null) {
                            if (!target.getName().equalsIgnoreCase(player.getName())) {
                                if (LobbyGames.getInstance().getCurrentGame(target) instanceof GunGame) {
                                    if (!Team.containsValue(target) && !Team.containsKey(target)) {
                                        if (isInviting.contains(target)) {
                                            isInviting.remove(target);

                                            Team.put(target, player);
                                            Msg.send(player, "§7Du hast das Team von §f" + target.getName() + "§7 betreten!");
                                            Msg.send(target, "§7Der Spieler §f" + player.getName() + "§7 hat dein Team betreten!");

                                        } else {
                                            Msg.send(player, "§4Der Spieler hat dich nicht eingeladen!");
                                        }
                                    } else {
                                        Msg.send(player, "§4Der Spieler ist bereits in einem anderen Team");
                                    }
                                } else {
                                    Msg.send(player, "§4Der Spieler spielt nicht Gungame!");
                                }
                            } else {
                                Msg.send(player, "§4Du kannst dich nicht selbst annehmen!");
                            }
                        } else {
                            Msg.send(player, "§4Der Spieler ist nicht Online!");
                        }
                    } else {
                        Msg.send(player, "§cBitte benutze:§4 /team");
                    }
                } else {
                    Msg.send(player, "§cBitte benutze:§4 /team");
                }
            } else {
                Msg.send(player, "§4Du kannst diesen Command nur in Gungame nutzen!");
            }
        } else {
            Msg.send(player, "§cDu bist bereits in einem Team!");
        }

        return false;
    }
}
