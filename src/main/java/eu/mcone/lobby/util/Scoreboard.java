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
import org.bukkit.scoreboard.Team;

import java.util.Collection;

import static de.Dominik.BukkitCoreSystem.util.Scoreboard.getObjectiveRang;

public class Scoreboard {

    public static void setObjective(Player p) {
        Objective old = p.getScoreboard().getObjective(DisplaySlot.SIDEBAR);
        if (old != null) {
            old.unregister();
        }

        org.bukkit.scoreboard.Scoreboard sb = p.getScoreboard();
        Objective o = sb.registerNewObjective("Lobby", "Main");

        o.setDisplaySlot(DisplaySlot.SIDEBAR);
        o.setDisplayName("§f§l§n"+p.getName());

        if (sb.getTeam("rang") != null) sb.getTeam("rang").unregister();
        if (sb.getTeam("coins") != null) sb.getTeam("coins").unregister();
        if (sb.getTeam("line1") != null) sb.getTeam("line1").unregister();
        if (sb.getTeam("line2") != null) sb.getTeam("line2").unregister();

        Team rang = sb.registerNewTeam("rang");
        rang.addEntry("§3");
        rang.setPrefix(getObjectiveRang(p));

        Team coins = sb.registerNewTeam("coins");
        coins.addEntry("§5");
        coins.setPrefix("§o"+CoinsAPI.getCoins(p));

        Team line1 = sb.registerNewTeam("line1");
        line1.addEntry("§7");
        line1.setPrefix("§7Teamspeak");

        Team line2 = sb.registerNewTeam("line2");
        line2.addEntry("§8");
        line2.setPrefix("§f§omcone.eu");

        o.getScore("§1").setScore(12);
        o.getScore("§8» §3§lMCONE.EU").setScore(11);
        o.getScore("§7§oDein Nummer 1").setScore(10);
        o.getScore("§7§oMinecraftnetzwerk").setScore(9);
        o.getScore("§2").setScore(8);
        o.getScore("§7Rang:").setScore(7);
        o.getScore("§3").setScore(6);
        o.getScore("§4").setScore(5);
        o.getScore("§7Coins").setScore(4);
        o.getScore("§5").setScore(3);
        o.getScore("§6").setScore(2);
        o.getScore("§7").setScore(1);
        o.getScore("§8").setScore(0);

        p.setScoreboard(sb);
    }

    private static int i = 0;

    public static void startUpdateScoreboardScheduler() {
        Bukkit.getScheduler().runTaskTimer(Main.getInstance(), () -> {
            if (i == 4) {
                i = 0;
            }
            i++;

            Collection<? extends Player> online = Bukkit.getOnlinePlayers();

            Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> {
                for(Player p : online) {
                    org.bukkit.scoreboard.Scoreboard sb = p.getScoreboard();

                    sb.getTeam("rang").setPrefix(getObjectiveRang(p));
                    sb.getTeam("coins").setPrefix("§o"+CoinsAPI.getCoins(p));

                    Team line1 = sb.getTeam("line1");
                    Team line2 = sb.getTeam("line2");

                    if(i == 1) {
                        line1.setPrefix("§7Teamspeak");
                        line2.setPrefix("§f§omcone.eu");
                    }else if(i == 2) {
                        line1.setPrefix("§7Website");
                        line2.setPrefix("§f§omcone.eu");
                    }else if(i == 3) {
                        line1.setPrefix("§bTwitter");
                        line2.setPrefix("§f§o@mconeeu");
                    }else if(i == 4) {
                        line1.setPrefix("§cYouTube");
                        line2.setPrefix("§f§omcone.eu/yt");
                    } else {
                        line1.setPrefix("§7Teamspeak");
                        line2.setPrefix("§f§omcone.eu");
                    }

                    p.setScoreboard(sb);
                }
            });
        }, 50L, 100L);
    }

}
