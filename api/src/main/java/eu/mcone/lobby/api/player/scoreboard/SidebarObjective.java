/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.api.player.scoreboard;

import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.coresystem.api.bukkit.scoreboard.CoreSidebarObjectiveEntry;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.api.player.scoreboard.widgets.Line;
import eu.mcone.lobby.api.player.scoreboard.widgets.ScoreboardWidgets;
import org.bukkit.Bukkit;

public class SidebarObjective extends LobbyObjective {

    public SidebarObjective() {
        super("Lobby-Main");
    }

    @Override
    protected void onRegister(CorePlayer corePlayer, CoreSidebarObjectiveEntry entry) {
        setDisplayName("§f§l§n" + player.bukkit().getName());

        entry.setScore(12, "");
        entry.setScore(11, "§8» §3§lMCONE.EU");
        entry.setScore(10, "§f§o Dein Nummer 1");
        entry.setScore(9, "§f§o Netzwerk");
        entry.setScore(8, "");
        onReload(corePlayer, entry);
        entry.setScore(5, "");
        entry.setScore(2, "");
        entry.setScore(1, "§8»§7 Teamspeak:");
        entry.setScore(0, " §f§ots.mcone.eu");
    }

    @Override
    protected void onReload(CorePlayer corePlayer, CoreSidebarObjectiveEntry entry) {
        setDisplayName("§f§l§n" + player.bukkit().getName());

        LobbyPlayer lobbyPlayer = LobbyPlugin.getInstance().getLobbyPlayer(player);


        for (ScoreboardWidgets scoreboardWidgets : ScoreboardWidgets.values()) {
            if (scoreboardWidgets.getLine().equals(Line.LINE_ONE)) {
                if (lobbyPlayer.getSettings().getScoreboardWidgetsFirstLine().equals(ScoreboardWidgets.COINS)) {
                    entry.setScore(scoreboardWidgets.getLine().getDisplayKey(), ScoreboardWidgets.COINS.getDisplayValue());
                    entry.setScore(scoreboardWidgets.getLine().getDisplayValue(), " §b§o" + player.getFormattedCoins());
                } else if (lobbyPlayer.getSettings().getScoreboardWidgetsFirstLine().equals(ScoreboardWidgets.ONLINE_TIME)) {
                    entry.setScore(scoreboardWidgets.getLine().getDisplayKey(), ScoreboardWidgets.ONLINE_TIME.getDisplayValue());
                    double onlinetime = Math.floor(((double) corePlayer.getOnlinetime() / 60 / 60) * 100) / 100;
                    entry.setScore(scoreboardWidgets.getLine().getDisplayValue(), " §d§o" + onlinetime + "h");
                } else if (lobbyPlayer.getSettings().getScoreboardWidgetsFirstLine().equals(ScoreboardWidgets.RANK)) {
                    entry.setScore(scoreboardWidgets.getLine().getDisplayKey(), ScoreboardWidgets.RANK.getDisplayValue());
                    entry.setScore(scoreboardWidgets.getLine().getDisplayValue(), corePlayer.getMainGroup().getColor() + " " + corePlayer.getMainGroup().getName());
                }
            } else {
                if (lobbyPlayer.getSettings().getScoreboardWidgetsSecondLine().equals(ScoreboardWidgets.EMERALD)) {
                    entry.setScore(scoreboardWidgets.getLine().getDisplayKey(), ScoreboardWidgets.EMERALD.getDisplayValue());
                    entry.setScore(scoreboardWidgets.getLine().getDisplayValue(), " §a§o" + player.getFormattedEmeralds());
                } else if (lobbyPlayer.getSettings().getScoreboardWidgetsSecondLine().equals(ScoreboardWidgets.SECRETS)) {
                    entry.setScore(scoreboardWidgets.getLine().getDisplayKey(), ScoreboardWidgets.SECRETS.getDisplayValue());
                    entry.setScore(scoreboardWidgets.getLine().getDisplayValue(), "§f§o" + lobbyPlayer.getSecretsCount());
                } else if (lobbyPlayer.getSettings().getScoreboardWidgetsSecondLine().equals(ScoreboardWidgets.ONLINE_PLAYER_LOBBY)) {
                    entry.setScore(scoreboardWidgets.getLine().getDisplayKey(), ScoreboardWidgets.ONLINE_PLAYER_LOBBY.getDisplayValue());

                    if (LobbyPlugin.getInstance().getVanishManager().isInSilentLobby(player.bukkit())) {
                        int amount = 1;
                        entry.setScore(scoreboardWidgets.getLine().getDisplayValue(), "§f§o" + amount);
                    } else {
                        int amount = Bukkit.getOnlinePlayers().size() - LobbyPlugin.getInstance().getVanishManager().getSilentLobbyPlayers().size();
                        entry.setScore(scoreboardWidgets.getLine().getDisplayValue(), "§f§o" + amount);
                    }
                }
            }
        }
    }
}
