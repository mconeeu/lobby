package eu.mcone.lobby.command;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.command.CorePlayerCommand;
import eu.mcone.lobby.Lobby;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.api.player.LobbySettings;
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
                    if (LobbyPlugin.getInstance().getOneHitManager().isFighting(player)) {
                        for (Player all : Bukkit.getOnlinePlayers()) {
                            if (all != player) {
                                LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(all);
                                LobbySettings settings = lp.getSettings();
                                if (settings.isLobbyGamesInvite()) {
                                    all.spigot().sendMessage(
                                            new ComponentBuilder("§8[§7!§8] §3LobbyGames §8» §fDer Spieler §a" + player.getName() + " §fspielt OneHit!")
                                                    .append(" [Mitspielen]")
                                                    .color(ChatColor.GREEN)
                                                    .bold(true)
                                                    .event(new HoverEvent(
                                                            HoverEvent.Action.SHOW_TEXT,
                                                            new ComponentBuilder("§7§oKlicke hier, um mitzuspielen").create()
                                                    ))
                                                    .event(new ClickEvent(
                                                            ClickEvent.Action.RUN_COMMAND,
                                                            "/game join OneHit"
                                                    ))
                                                    .create()
                                    );
                                }
                            } else {
                                LobbyPlugin.getInstance().getMessenger().send(player, "§aDu hast eine Einladung gesendet!");
                            }
                        }
                    } else if (LobbyPlugin.getInstance().getCatchManager().isCatching(player)) {
                        for (Player all : Bukkit.getOnlinePlayers()) {
                            if (all != player) {
                                LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(all);
                                LobbySettings settings = lp.getSettings();

                                if (settings.isLobbyGamesInvite()) {
                                    all.spigot().sendMessage(
                                            new ComponentBuilder("§8[§7!§8] §3LobbyGames §8» §fDer Spieler §a" + player.getName() + " §fspielt Fangen!")
                                                    .append(" [Mitspielen]")
                                                    .color(ChatColor.GREEN)
                                                    .bold(true)
                                                    .event(new HoverEvent(
                                                            HoverEvent.Action.SHOW_TEXT,
                                                            new ComponentBuilder("§7§oKlicke hier, um mitzuspielen").create()
                                                    ))
                                                    .event(new ClickEvent(
                                                            ClickEvent.Action.RUN_COMMAND,
                                                            "/game join Fangen"
                                                    ))
                                                    .create()
                                    );
                                }
                            } else {
                                LobbyPlugin.getInstance().getMessenger().send(player, "§aDu hast eine Einladung gesendet!");
                            }
                        }
                    } else if (LobbyPlugin.getInstance().getGungameManager().isFighting(player)) {
                        for (Player all : Bukkit.getOnlinePlayers()) {
                            if (all != player) {
                                LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(all);
                                LobbySettings settings = lp.getSettings();
                                if (settings.isLobbyGamesInvite()) {
                                    all.spigot().sendMessage(
                                            new ComponentBuilder("§8[§7!§8] §3LobbyGames §8» §fDer Spieler §a" + player.getName() + " §fspielt Gungame!")
                                                    .append(" [Mitspielen]")
                                                    .color(ChatColor.GREEN)
                                                    .bold(true)
                                                    .event(new HoverEvent(
                                                            HoverEvent.Action.SHOW_TEXT,
                                                            new ComponentBuilder("§7§oKlicke hier, um mitzuspielen").create()
                                                    ))
                                                    .event(new ClickEvent(
                                                            ClickEvent.Action.RUN_COMMAND,
                                                            "/game join GunGame"
                                                    ))
                                                    .create()
                                    );
                                }
                            } else {
                                LobbyPlugin.getInstance().getMessenger().send(player, "§aDu hast eine Einladung gesendet!");
                            }
                        }
                    } else if (LobbyPlugin.getInstance().getJumpNRunManager().isJumping(player)) {
                        LobbyPlugin.getInstance().getMessenger().send(player, "§4In diesem Lobby Game kannst du keine Einladungen versenden!");
                    } else {
                        LobbyPlugin.getInstance().getMessenger().send(player, "§4Du bist in keinem Lobby Game");
                    }
                } else {
                    Lobby.getSystem().getMessenger().send(player, "§4Bitte benutze; §c/game join [<Lobby-Game>]");
                }
            }
        } else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("join")) {
                if (args[1].equalsIgnoreCase("onehit")) {
                    LobbyPlugin.getInstance().getOneHitManager().setStart(player);
                } else if (args[1].equalsIgnoreCase("fangen")) {
                    LobbyPlugin.getInstance().getCatchManager().setStart(player);
                } else if (args[1].equalsIgnoreCase("gungame")) {
                    LobbyPlugin.getInstance().getGungameManager().setStart(player);
                } else {
                    Lobby.getSystem().getMessenger().send(player, "§4Dieses LobbyGame existiert nicht:§c OneHit, Fangen, GunGame");
                }
            } else {
                if (player.hasPermission("lobby.game.joinme")) {
                    Lobby.getSystem().getMessenger().send(player, "§4Bitte benutze; §c/game joinme §4oder§c /game join [<Lobby-Game>]");
                } else {
                    Lobby.getSystem().getMessenger().send(player, "§4Bitte benutze; §c/game join [<Lobby-Game>]");
                }
            }
        } else {
            if (player.hasPermission("lobby.game.joinme")) {
                Lobby.getSystem().getMessenger().send(player, "§4Bitte benutze; §c/game joinme §4oder§c /game join [<Lobby-Game>]");
            } else {
                Lobby.getSystem().getMessenger().send(player, "§4Bitte benutze; §c/game join [<Lobby-Game>]");
            }
        }


        return false;
    }
}
