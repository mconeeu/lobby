/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.games.command;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.command.CorePlayerCommand;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.games.LobbyGame;
import eu.mcone.lobby.api.games.LobbyPvpGame;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.api.player.settings.LobbySettings;
import eu.mcone.lobby.games.LobbyGames;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class GameCMD extends CorePlayerCommand {

    public GameCMD() {
        super("game");
    }

    static {
        CoreSystem.getInstance().getCooldownSystem().setCustomCooldownFor(GameCMD.class, 550);
    }

    @Override
    public boolean onPlayerCommand(Player player, String[] args) {
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("joinme")) {
                if (player.hasPermission("lobby.game.joinme")) {
                    LobbyGame game = LobbyGames.getInstance().getCurrentGame(player);

                    if (game instanceof LobbyPvpGame) {
                        for (Player all : Bukkit.getOnlinePlayers()) {
                            if (all != player) {
                                LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(all);
                                LobbySettings settings = lp.getSettings();
                                if (settings.isLobbyGamesInvite()) {
                                    all.spigot().sendMessage(
                                            new ComponentBuilder("§8[§7!§8] §3LobbyGames §8» §fDer Spieler §n" + player.getName() + "§f spielt "+game.getName()+"!")
                                                    .append(" [Mitspielen]")
                                                    .color(ChatColor.GREEN)
                                                    .bold(true)
                                                    .event(new HoverEvent(
                                                            HoverEvent.Action.SHOW_TEXT,
                                                            new ComponentBuilder("§7§oKlicke hier, um mitzuspielen").create()
                                                    ))
                                                    .event(new ClickEvent(
                                                            ClickEvent.Action.RUN_COMMAND,
                                                            "/game join "+game.getShortNames()[0]
                                                    ))
                                                    .create()
                                    );
                                }
                            } else {
                                LobbyPlugin.getInstance().getMessenger().send(player, "§aDu hast eine Einladung gesendet!");
                            }
                        }
                    } else if (game != null) {
                        LobbyPlugin.getInstance().getMessenger().send(player, "§4In diesem Lobby Game kannst du keine Einladungen versenden!");
                    } else {
                        LobbyPlugin.getInstance().getMessenger().send(player, "§4Du bist in keinem Lobby Game!");
                    }

                    return true;
                }
            }
        } else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("join")) {
                for (LobbyGame game : LobbyGames.getInstance().getGames()) {
                    if (game instanceof LobbyPvpGame) {
                        for (String shortName : game.getShortNames()) {
                            if (shortName.equalsIgnoreCase(args[1])) {
                                ((LobbyPvpGame) game).joinGame(player);
                                return true;
                            }
                        }
                    }
                }

                LobbyPlugin.getInstance().getMessenger().send(player, "§4Dieses LobbyGame existiert nicht:§c OneHit, Fangen, GunGame");
                return true;
            }
        }

        if (player.hasPermission("lobby.game.joinme")) {
            LobbyPlugin.getInstance().getMessenger().send(player, "§4Bitte benutze; §c/game joinme §4oder§c /game join [<Lobby-Game>]");
        } else {
            LobbyPlugin.getInstance().getMessenger().send(player, "§4Bitte benutze; §c/game join [<Lobby-Game>]");
        }
        return false;
    }
}
