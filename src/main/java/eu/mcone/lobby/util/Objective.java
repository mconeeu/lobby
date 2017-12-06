/*
 * Copyright (c) 2017 Dominik L., Rufus Maiwald, BamDev and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.util;

import eu.mcone.bukkitcoresystem.CoreSystem;
import eu.mcone.bukkitcoresystem.api.CoinsAPI;
import eu.mcone.bukkitcoresystem.player.CorePlayer;
import eu.mcone.bukkitcoresystem.scoreboard.ObjectiveHandler;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class Objective implements ObjectiveHandler {

    private static int i = 0;
    private Team rang;
    private Team coins;
    private Team line1;
    private Team line2;

    public Objective(CorePlayer p) {
        p.getScoreboard().setNewObjective(DisplaySlot.SIDEBAR, this, "Main", "Lobby");
    }

    @Override
    public void register(CorePlayer p, Scoreboard sb) {
        org.bukkit.scoreboard.Objective o = sb.getObjective(DisplaySlot.SIDEBAR);
        o.setDisplayName("§f§l§n"+p.getName());

        if (sb.getTeam("rang") != null) sb.getTeam("rang").unregister();
        if (sb.getTeam("coins") != null) sb.getTeam("coins").unregister();
        if (sb.getTeam("line1") != null) sb.getTeam("line1").unregister();
        if (sb.getTeam("line2") != null) sb.getTeam("line2").unregister();

        rang = sb.registerNewTeam("rang");
        rang.addEntry("§3");
        rang.setPrefix(p.getGroupName());

        coins = sb.registerNewTeam("coins");
        coins.addEntry("§5");
        coins.setPrefix("§o"+ CoinsAPI.getCoins(p.getUuid()));

        line1 = sb.registerNewTeam("line1");
        line1.addEntry("§7");
        line1.setPrefix("§7Teamspeak");

        line2 = sb.registerNewTeam("line2");
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

        p.bukkit().setScoreboard(sb);
    }

    @Override
    public void reload(CorePlayer p, Scoreboard sb) {
        if (i >= 4) i=0;
        i++;

        rang.setPrefix(p.getGroupName());
        coins.setPrefix("§o"+CoinsAPI.getCoins(p.getUuid()));

        p.bukkit().setScoreboard(sb);
    }

    public static void updateLines() {
        if (i >= 4) i=0;
        i++;

        for (CorePlayer p : CoreSystem.getOnlineCorePlayers()) {
            Scoreboard sb = p.bukkit().getScoreboard();

            if(i == 1) {
                sb.getTeam("line1").setPrefix("§7Teamspeak");
                sb.getTeam("line2").setPrefix("§f§omcone.eu");
            }else if(i == 2) {
                sb.getTeam("line1").setPrefix("§7Website");
                sb.getTeam("line2").setPrefix("§f§omcone.eu");
            }else if(i == 3) {
                sb.getTeam("line1").setPrefix("§bTwitter");
                sb.getTeam("line2").setPrefix("§f§o@mconeeu");
            }else if(i == 4) {
                sb.getTeam("line1").setPrefix("§cYouTube");
                sb.getTeam("line2").setPrefix("§f§omcone.eu/yt");
            } else {
                sb.getTeam("line1").setPrefix("§7Teamspeak");
                sb.getTeam("line2").setPrefix("§f§omcone.eu");
            }
        }
    }

}
