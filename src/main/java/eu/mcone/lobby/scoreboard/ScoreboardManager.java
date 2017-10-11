/*
 * Copyright (c) 2017 Dominik L., BamDev, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
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
import org.bukkit.scoreboard.Team;

public class ScoreboardManager {

    public static void setScoreboard(Player p) {

        org.bukkit.scoreboard.ScoreboardManager sm = Bukkit.getScoreboardManager();
        final Scoreboard board = sm.getNewScoreboard();
        final Objective o = board.getObjective("McOne.eu") != null ? board.getObjective("McOne.eu") : board.registerNewObjective("McOne.eu", "Lobby");

        o.setDisplaySlot(DisplaySlot.SIDEBAR);
        o.setDisplayName("§7» §3§lMCONE.EU");

        o.getScore("§7Rang:").setScore(7);


        if (p.hasPermission("group.Admin")) {
            o.getScore("§c§oAdmin").setScore(6);
        } else if (p.hasPermission("group.Developer")) {
            o.getScore("§b§oDeveloper").setScore(6);
        } else if (p.hasPermission("group.Builder")) {
            o.getScore("§e§oBuilder").setScore(6);
        } else if (p.hasPermission("group.JrSupporter")) {
            o.getScore("§a§oJrSupporter").setScore(6);
        } else if (p.hasPermission("group.SrModerator")) {
            o.getScore("§2§oSrModerator").setScore(6);
        } else if (p.hasPermission("group.Moderator")) {
            o.getScore("§2§oModerator").setScore(6);
        } else if (p.hasPermission("group.Supporter")) {
            o.getScore("§a§oSupporter").setScore(6);
        } else if (p.hasPermission("group.YouTuber")) {
            o.getScore("§5§oYoutuber").setScore(6);
        }else if (p.hasPermission("group.Premium+")) {
            o.getScore("§6§oPremium+").setScore(6);
        }else if (p.hasPermission("group.Premium")) {
            o.getScore("§6§oPremium").setScore(6);
        }else if (p.hasPermission("group.Spieler")) {
            o.getScore("§f§oSpieler").setScore(6);
        }else if (p.hasPermission("group.Spielverderber")) {
            o.getScore("§8§oSpielverderber").setScore(6);
        }

        o.getScore("§3 ").setScore(5);
        o.getScore("§7Coins").setScore(4);
        o.getScore("§o" + CoinsAPI.getCoins(p)).setScore(3);
        o.getScore("").setScore(2);
        o.getScore("§7Teamspeak").setScore(1);
        o.getScore("§f§oMcOne.eu").setScore(0);

        p.setScoreboard(board);


        Team SV = board.registerNewTeam("01300SV");
        Team Spieler = board.registerNewTeam("01200SPIELER");
        Team Premium = board.registerNewTeam("01100PREMIUM");
        Team PremiumPlus = board.registerNewTeam("01000PREMIUMPLUS");
        Team YouTuber = board.registerNewTeam("00900YT");
        Team Team = board.registerNewTeam("00800TEAM");
        Team Supporter = board.registerNewTeam("00700SUP");
        Team Moderator = board.registerNewTeam("00400MOD");
        Team SrModerator = board.registerNewTeam("00300SRMOD");
        Team JrBuilder = board.registerNewTeam("00600JRSUPP");
        Team Builder = board.registerNewTeam("00500BUILDER");
        Team Developer = board.registerNewTeam("00200DEV");
        Team Admin = board.registerNewTeam("00100ADMIN");

        /**
         * [TablistManager] SetPrefix
         */

        Admin.setPrefix(Main.cfg.getString("Lobby-Tablist-Admin").replaceAll("&", "§").replaceAll(">>", "»"));
        Developer.setPrefix(Main.cfg.getString("Lobby-Tablist-Developer").replaceAll("&", "§").replaceAll(">>", "»"));
        Builder.setPrefix(Main.cfg.getString("Lobby-Tablist-Builder").replaceAll("&", "§").replaceAll(">>", "»"));
        JrBuilder.setPrefix(Main.cfg.getString("Lobby-Tablist-JrSupporter").replaceAll("&", "§").replaceAll(">>", "»"));
        SrModerator.setPrefix(Main.cfg.getString("Lobby-Tablist-SrModerator").replaceAll("&", "§").replaceAll(">>", "»"));
        Moderator.setPrefix(Main.cfg.getString("Lobby-Tablist-Moderator").replaceAll("&", "§").replaceAll(">>", "»"));
        Supporter.setPrefix(Main.cfg.getString("Lobby-Tablist-Supporter").replaceAll("&", "§").replaceAll(">>", "»"));
        Team.setPrefix(Main.cfg.getString("Lobby-Tablist-Team").replaceAll("&", "§").replaceAll(">>", "»"));
        YouTuber.setPrefix(Main.cfg.getString("Lobby-Tablist-YouTuber").replaceAll("&", "§").replaceAll(">>", "»"));
        PremiumPlus.setPrefix(Main.cfg.getString("Lobby-Tablist-Premium+").replaceAll("&", "§").replaceAll(">>", "»"));
        Premium.setPrefix(Main.cfg.getString("Lobby-Tablist-Premium").replaceAll("&", "§").replaceAll(">>", "»"));
        Spieler.setPrefix(Main.cfg.getString("Lobby-Tablist-Spieler").replaceAll("&", "§").replaceAll(">>", "»"));
        SV.setPrefix(Main.cfg.getString("Lobby-Tablist-Spielverderber").replaceAll("&", "§").replaceAll(">>", "»"));

        Bukkit.getOnlinePlayers().forEach(Player ->{
            if(Player.hasPermission("group.Admin")){
                Admin.addEntry(Player.getName());
            }else if(Player.hasPermission("group.Developer")){
                Developer.addEntry(Player.getName());
            }else if(Player.hasPermission("group.Builder")){
                Builder.addEntry(Player.getName());
            }else if(Player.hasPermission("group.JrSupporter")){
                JrBuilder.addEntry(Player.getName());
            }else if(Player.hasPermission("group.SrModerator")){
                SrModerator.addEntry(Player.getName());
            }else if(Player.hasPermission("group.Moderator")){
                Moderator.addEntry(Player.getName());
            }else if(Player.hasPermission("group.Supporter")){
                Supporter.addEntry(Player.getName());
            }else if(Player.hasPermission("group.Team")){
                Team.addEntry(Player.getName());
            }else if(Player.hasPermission("group.YouTuber")){
                YouTuber.addEntry(Player.getName());
            }else if(Player.hasPermission("group.Premium+")){
                PremiumPlus.addEntry(Player.getName());
            }else if(Player.hasPermission("group.Premium")){
                Premium.addEntry(Player.getName());
            }else if(Player.hasPermission("group.Spieler")){
                Spieler.addEntry(Player.getName());
            }else if(Player.hasPermission("group.Spielverderber")){
                SV.addEntry(Player.getName());
            }
            p.setScoreboard(board);
        });
    }

    public static void UpdateScoreboardScheduler(){

        for(Player all : Bukkit.getOnlinePlayers()) {
            Scoreboard s = all.getScoreboard();
            Objective o = s.getObjective("McOne.eu");

            int SchedulerID;

            SchedulerID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable(){
                int i = 0;

                @Override
                public void run() {
                    i++;
                    if(i == 1) {
                        o.getScoreboard().resetScores("§7Teamspeak");
                        o.getScoreboard().resetScores("§7Website");
                        o.getScoreboard().resetScores("§f§oMcOne.eu");
                        o.getScore("§3 ").setScore(5);
                        o.getScore("§7Coins").setScore(4);
                        o.getScore("§o" + CoinsAPI.getCoins(all)).setScore(3);
                        o.getScore("").setScore(2);
                        o.getScore("§7Website").setScore(1);
                        o.getScore("§f§oMcOne.eu").setScore(0);
                        all.setScoreboard(s);
                    }else if(i == 2) {
                        o.getScoreboard().resetScores("§7Website");
                        o.getScoreboard().resetScores("§7Teamspeak");
                        o.getScoreboard().resetScores("§f§oMcOne.eu");
                        o.getScore("§3 ").setScore(5);
                        o.getScore("§7Coins").setScore(4);
                        o.getScore("§o" + CoinsAPI.getCoins(all)).setScore(3);
                        o.getScore("").setScore(2);
                        o.getScore("§7Teamspeak").setScore(1);
                        o.getScore("§f§oMcOne.eu").setScore(0);
                        all.setScoreboard(s);
                    }else {
                        i = 0;
                    }
                }

            }, 1000, 1000);
        }
    }

}
