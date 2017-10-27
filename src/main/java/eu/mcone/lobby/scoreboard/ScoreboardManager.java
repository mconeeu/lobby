/*
 * Copyright (c) 2017 Dominik L., Rufus Maiwald, BamDev and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.scoreboard;

import de.Dominik.BukkitCoreSystem.API.CoinsAPI;
import eu.mcone.lobby.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.HashMap;

public class ScoreboardManager {

    private static HashMap<Player, String> objektiveRang = new HashMap<>();
    private static HashMap<Player, String> objektiveCoins = new HashMap<>();

    public static void setScoreboard(Player p) {

        final Scoreboard board = p.getScoreboard();
        final Objective o = board.registerNewObjective("main", "Lobby");

        String objectiveRang = getObjectiveRang(p);
        String objectiveCoins = Integer.valueOf(CoinsAPI.getCoins(p)).toString();

        o.setDisplaySlot(DisplaySlot.SIDEBAR);
        o.setDisplayName("§7» §3§lMCONE.EU");

        o.getScore("§7Rang:").setScore(7);
        o.getScore(objectiveRang).setScore(6);
        o.getScore("§2 ").setScore(5);
        o.getScore("§7Coins").setScore(4);
        o.getScore("§o" + objectiveCoins).setScore(3);
        o.getScore("§1 ").setScore(2);
        o.getScore("§7Teamspeak").setScore(1);
        o.getScore("§f§omcone.eu").setScore(0);

        objektiveRang.put(p, objectiveRang);
        objektiveCoins.put(p, objectiveCoins);
        p.setScoreboard(board);
    }

    public static void updateScoreboardScheduler(){

        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable(){
            int i = 0;

            @Override
            public void run() {
                for(Player player : Bukkit.getOnlinePlayers()) {
                    Scoreboard s = player.getScoreboard();
                    Objective o = s.getObjective("main");

                    String oldRang = objektiveRang.get(player) != null ? objektiveRang.get(player) : getObjectiveRang(player);
                    String newRang = getObjectiveRang(player);

                    String oldCoins = objektiveCoins.get(player) != null ? objektiveCoins.get(player) : Integer.valueOf(CoinsAPI.getCoins(player)).toString();
                    String newCoins = Integer.valueOf(CoinsAPI.getCoins(player)).toString();

                    i++;
                    if(i == 1) {
                        o.getScoreboard().resetScores(oldRang);
                        o.getScoreboard().resetScores("§o" + oldCoins);
                        o.getScoreboard().resetScores("§7Teamspeak");
                        o.getScoreboard().resetScores("§7Website");
                        o.getScoreboard().resetScores("§bTwitter");
                        o.getScoreboard().resetScores("§cYouTube");
                        o.getScoreboard().resetScores("§f§omcone.eu");
                        o.getScoreboard().resetScores("§f§o@mconeeu");
                        o.getScoreboard().resetScores("§f§omcone.eu/yt");
                        o.getScore(newRang).setScore(6);
                        o.getScore("§o" + newCoins).setScore(3);
                        o.getScore("§7Teamspeak").setScore(1);
                        o.getScore("§f§omcone.eu").setScore(0);
                        player.setScoreboard(s);
                    }else if(i == 2) {
                        o.getScoreboard().resetScores(oldRang);
                        o.getScoreboard().resetScores("§o" + oldCoins);
                        o.getScoreboard().resetScores("§7Teamspeak");
                        o.getScoreboard().resetScores("§7Website");
                        o.getScoreboard().resetScores("§bTwitter");
                        o.getScoreboard().resetScores("§cYouTube");
                        o.getScoreboard().resetScores("§f§omcone.eu");
                        o.getScoreboard().resetScores("§f§o@mconeeu");
                        o.getScoreboard().resetScores("§f§omcone.eu/yt");
                        o.getScore(newRang).setScore(6);
                        o.getScore("§o" + newCoins).setScore(3);
                        o.getScore("§7Website").setScore(1);
                        o.getScore("§f§omcone.eu").setScore(0);
                        player.setScoreboard(s);
                    }else if(i == 3) {
                        o.getScoreboard().resetScores(oldRang);
                        o.getScoreboard().resetScores("§o" + oldCoins);
                        o.getScoreboard().resetScores("§7Teamspeak");
                        o.getScoreboard().resetScores("§7Website");
                        o.getScoreboard().resetScores("§bTwitter");
                        o.getScoreboard().resetScores("§cYouTube");
                        o.getScoreboard().resetScores("§f§omcone.eu");
                        o.getScoreboard().resetScores("§f§o@mconeeu");
                        o.getScoreboard().resetScores("§f§omcone.eu/yt");
                        o.getScore(newRang).setScore(6);
                        o.getScore("§o" + newCoins).setScore(3);
                        o.getScore("§bTwitter").setScore(1);
                        o.getScore("§f§o@mconeeu").setScore(0);
                        player.setScoreboard(s);
                    }else if(i == 4) {
                        o.getScoreboard().resetScores(oldRang);
                        o.getScoreboard().resetScores("§o" + oldCoins);
                        o.getScoreboard().resetScores("§7Teamspeak");
                        o.getScoreboard().resetScores("§7Website");
                        o.getScoreboard().resetScores("§bTwitter");
                        o.getScoreboard().resetScores("§cYouTube");
                        o.getScoreboard().resetScores("§f§omcone.eu");
                        o.getScoreboard().resetScores("§f§o@mconeeu");
                        o.getScoreboard().resetScores("§f§omcone.eu/yt");
                        o.getScore(newRang).setScore(6);
                        o.getScore("§o" + newCoins).setScore(3);
                        o.getScore("§cYouTube").setScore(1);
                        o.getScore("§f§omcone.eu/yt").setScore(0);
                        player.setScoreboard(s);
                        i = 0;
                    }

                    objektiveRang.put(player, newRang);
                    objektiveCoins.put(player, newCoins);
                }
            }

        }, 100, 100);
    }

    public static String getObjectiveRang(Player p){
        String rang;

        if (p.hasPermission("group.Admin")) {
            rang = "§c§oAdmin";
        } else if (p.hasPermission("group.Developer")) {
            rang = "§b§oDeveloper";
        } else if (p.hasPermission("group.Builder")) {
            rang = "§e§oBuilder";
        } else if (p.hasPermission("group.JrSupporter")) {
            rang = "§a§oJrSupporter";
        } else if (p.hasPermission("group.SrModerator")) {
            rang = "§2§oSrModerator";
        } else if (p.hasPermission("group.Moderator")) {
            rang = "§2§oModerator";
        } else if (p.hasPermission("group.Supporter")) {
            rang = "§a§oSupporter";
        } else if (p.hasPermission("group.YouTuber")) {
            rang = "§5§oYoutuber";
        }else if (p.hasPermission("group.Premium+")) {
            rang = "§6§oPremium+";
        }else if (p.hasPermission("group.Premium")) {
            rang = "§6§oPremium";
        }else if (p.hasPermission("group.Spieler")) {
            rang = "§f§oSpieler";
        }else if (p.hasPermission("group.Spielverderber")) {
            rang = "§8§oSpielverderber";
        } else {
            rang = "§4§oError";
        }

        return rang;
    }

}
