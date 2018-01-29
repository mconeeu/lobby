/*
 * Copyright (c) 2017 Dominik L., Rufus Maiwald, BamDev and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.util;

import eu.mcone.bukkitcoresystem.CoreSystem;
import eu.mcone.bukkitcoresystem.api.CoinsAPI;
import eu.mcone.bukkitcoresystem.player.CorePlayer;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class Objective extends eu.mcone.bukkitcoresystem.scoreboard.Objective {

    private static int i = 0;

    public Objective(CorePlayer p) {
        super(p, DisplaySlot.SIDEBAR, "Main", "Lobby");
    }

    @Override
    public void register() {
        org.bukkit.scoreboard.Objective o = bukkit();
        Scoreboard sb = getScoreboard();
        CorePlayer p = getPlayer();

        o.setDisplayName("§f§l§n"+p.bukkit().getDisplayName());

        if (sb.getTeam("rang") != null) sb.getTeam("rang").unregister();
        if (sb.getTeam("coins") != null) sb.getTeam("coins").unregister();
        if (sb.getTeam("line1") != null) sb.getTeam("line1").unregister();
        if (sb.getTeam("line2") != null) sb.getTeam("line2").unregister();

        Team rang = sb.registerNewTeam("rang");
        rang.addEntry("§3");
        rang.setPrefix(p.getGroup().getLabel());

        Team coins = sb.registerNewTeam("coins");
        coins.addEntry("§5");
        coins.setPrefix("§o"+ CoinsAPI.getCoins(p.getUuid()));

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
        o.getScore("§7Coins:").setScore(4);
        o.getScore("§5").setScore(3);
        o.getScore("§6").setScore(2);
        o.getScore("§7").setScore(1);
        o.getScore("§8").setScore(0);

        p.bukkit().setScoreboard(sb);
    }

    @Override
    public void reload() {
        Scoreboard sb = getScoreboard();
        CorePlayer p = getPlayer();

        sb.getTeam("rang").setPrefix(p.getGroup().getLabel());
        sb.getTeam("coins").setPrefix("§o"+CoinsAPI.getCoins(p.getUuid()));

        p.bukkit().setScoreboard(sb);
    }

    public static void updateLines() {
        if (i >= 4) i=0;
        i++;

        for (final CorePlayer p : CoreSystem.getOnlineCorePlayers()) {
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
