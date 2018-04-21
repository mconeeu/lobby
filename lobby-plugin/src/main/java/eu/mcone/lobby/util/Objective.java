/*
 * Copyright (c) 2017 - 2018 Dominik Lippl, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.util;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.player.BukkitCorePlayer;
import eu.mcone.coresystem.api.bukkit.scoreboard.CoreObjective;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class Objective extends CoreObjective {

    private static int i = 0;

    public Objective() {
        super(DisplaySlot.SIDEBAR, "Main", "Lobby");
    }

    @Override
    public void register() {
        objective.setDisplayName("§f§l§n"+player.bukkit().getDisplayName());

        if (scoreboard.getTeam("rang") != null) scoreboard.getTeam("rang").unregister();
        if (scoreboard.getTeam("coins") != null) scoreboard.getTeam("coins").unregister();
        if (scoreboard.getTeam("line1") != null) scoreboard.getTeam("line1").unregister();
        if (scoreboard.getTeam("line2") != null) scoreboard.getTeam("line2").unregister();

        Team rang = scoreboard.registerNewTeam("rang");
        rang.addEntry("§3");
        rang.setPrefix(player.getMainGroup().getLabel());

        Team coins = scoreboard.registerNewTeam("coins");
        coins.addEntry("§5");
        coins.setPrefix("§o"+ CoreSystem.getInstance().getCoinsAPI().getCoins(player.getUuid()));

        Team line1 = scoreboard.registerNewTeam("line1");
        line1.addEntry("§7");
        line1.setPrefix("§7Teamspeak");

        Team line2 = scoreboard.registerNewTeam("line2");
        line2.addEntry("§8");
        line2.setPrefix("§f§omcone.eu");

        objective.getScore("§1").setScore(12);
        objective.getScore("§8» §3§lMCONE.EU").setScore(11);
        objective.getScore("§7§oDein Nummer 1").setScore(10);
        objective.getScore("§7§oMinecraftnetzwerk").setScore(9);
        objective.getScore("§2").setScore(8);
        objective.getScore("§7Rang:").setScore(7);
        objective.getScore("§3").setScore(6);
        objective.getScore("§4").setScore(5);
        objective.getScore("§7Coins:").setScore(4);
        objective.getScore("§5").setScore(3);
        objective.getScore("§6").setScore(2);
        objective.getScore("§7").setScore(1);
        objective.getScore("§8").setScore(0);

        player.bukkit().setScoreboard(scoreboard);
    }

    @Override
    public void reload() {
        scoreboard.getTeam("rang").setPrefix(player.getMainGroup().getLabel());
        scoreboard.getTeam("coins").setPrefix("§o"+CoreSystem.getInstance().getCoinsAPI().getCoins(player.getUuid()));

        player.bukkit().setScoreboard(scoreboard);
    }

    public static void updateLines() {
        if (i >= 4) i=0;
        i++;

        for (final BukkitCorePlayer p : CoreSystem.getInstance().getOnlineCorePlayers()) {
            final Scoreboard sb = p.bukkit().getScoreboard();
            sb.getObjective(DisplaySlot.SIDEBAR).setDisplayName("§f§l§n"+p.bukkit().getDisplayName());

            if (i == 1) {
                sb.getTeam("line1").setPrefix("§7Teamspeak:");
                sb.getTeam("line2").setPrefix("§f§omcone.eu");
            } else if (i == 2) {
                sb.getTeam("line1").setPrefix("§7Website:");
                sb.getTeam("line2").setPrefix("§f§omcone.eu");
            } else if (i == 3) {
                sb.getTeam("line1").setPrefix("§bTwitter:");
                sb.getTeam("line2").setPrefix("§f§o@mconeeu");
            } else if (i == 4) {
                sb.getTeam("line1").setPrefix("§cYouTube:");
                sb.getTeam("line2").setPrefix("§f§omcone.eu/yt");
            } else {
                sb.getTeam("line1").setPrefix("§7Teamspeak");
                sb.getTeam("line2").setPrefix("§f§omcone.eu");
            }
        }
    }

}
