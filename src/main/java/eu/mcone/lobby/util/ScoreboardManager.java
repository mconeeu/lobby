/*
 * Copyright (c) 2017 Dominik L., Rufus Maiwald, BamDev and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.util;

import de.Dominik.BukkitCoreSystem.api.CoinsAPI;
import eu.mcone.lobby.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.HashMap;
import java.util.UUID;

public class ScoreboardManager {

    private static int count = 0;

    public ScoreboardManager() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), () -> {
            if (count == 4) {
                count = 0;
            }
            count++;

            for(Player p : Bukkit.getOnlinePlayers()) {
                final Scoreboard board = p.getScoreboard();
                final Objective old = board.getObjective(DisplaySlot.SIDEBAR);

                setScoreboard(p, count);
                if (old != null) {
                    old.unregister();
                }
            }
        }, 100, 100);
    }

    public static void setScoreboard(Player p, int i) {

        final Scoreboard board = p.getScoreboard();
        final Objective o = board.registerNewObjective(UUID.randomUUID().toString().substring(0,16), "Lobby");

        o.setDisplaySlot(DisplaySlot.SIDEBAR);
        o.setDisplayName("§7» §3§lMCONE.EU");

        o.getScore("§7Rang:").setScore(7);
        o.getScore(getObjectiveRang(p)).setScore(6);
        o.getScore("§2 ").setScore(5);
        o.getScore("§7Coins").setScore(4);
        o.getScore("§o" + CoinsAPI.getCoins(p)).setScore(3);
        o.getScore("§1 ").setScore(2);

        if(i == 1) {
            o.getScore("§7Teamspeak").setScore(1);
            o.getScore("§f§omcone.eu").setScore(0);
        }else if(i == 2) {
            o.getScore("§7Website").setScore(1);
            o.getScore("§f§omcone.eu").setScore(0);
        }else if(i == 3) {
            o.getScore("§bTwitter").setScore(1);
            o.getScore("§f§o@mconeeu").setScore(0);
        }else if(i == 4) {
            o.getScore("§cYouTube").setScore(1);
            o.getScore("§f§omcone.eu/yt").setScore(0);
        } else {
            o.getScore("§7Teamspeak").setScore(1);
            o.getScore("§f§omcone.eu").setScore(0);
        }

        p.setScoreboard(board);
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
