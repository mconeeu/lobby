/*
 * Copyright (c) 2017 Dominik L., Rufus Maiwald, BamDev and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.scoreboard;

import de.Dominik.BukkitCoreSystem.api.CoinsAPI;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;

import java.util.HashMap;

import static de.Dominik.BukkitCoreSystem.util.ScoreboardManager.getObjectiveRang;

public class Scoreboard {

    private Player p;
    private org.bukkit.scoreboard.Scoreboard sb;
    private Objective o;
    private HashMap<String, Integer> scores;

    public Scoreboard(Player p) {
        this.p = p;
        this.scores = new HashMap<>();
        ScoreboardManager.scoreboards.add(this);

        Objective old = p.getScoreboard().getObjective(DisplaySlot.SIDEBAR);
        if (old != null) {
            old.unregister();
        }

        this.sb = p.getScoreboard();
        this.o = sb.registerNewObjective("Main", "Lobby");

        this.o.setDisplaySlot(DisplaySlot.SIDEBAR);
        this.o.setDisplayName("§f§l§n"+p.getName());

        this.o.getScore("§1").setScore(12);
        this.o.getScore("§8» §3§lMCONE.EU").setScore(11);
        this.o.getScore("§7§oDein Nummer 1").setScore(10);
        this.o.getScore("§7§oMinecraftnetzwerk").setScore(9);
        this.o.getScore("§2").setScore(8);
        this.o.getScore("§7Rang:").setScore(7);
        this.scores.put(getObjectiveRang(p), 6);
        this.o.getScore("§3").setScore(5);
        this.o.getScore("§7Coins").setScore(4);
        this.scores.put("§o" + CoinsAPI.getCoins(p), 3);
        this.o.getScore("§4").setScore(2);
        this.scores.put("§7Teamspeak", 1);
        this.scores.put("§f§omcone.eu", 0);

        for (HashMap.Entry<String, Integer> entry : this.scores.entrySet()) {
            String s = entry.getKey();
            int score = entry.getValue();

            this.o.getScore(s).setScore(score);
        }

        p.setScoreboard(this.sb);
    }

    void updateScoreboard(int i) {
        String rang = getObjectiveRang(p);
        int coins = CoinsAPI.getCoins(p);

        for (HashMap.Entry<String, Integer> entry : this.scores.entrySet()) {
            String s = entry.getKey();
            this.sb.resetScores(s);
        }
        this.scores = new HashMap<>();

        this.scores.put(rang, 6);
        this.scores.put("§o" + coins, 3);

        if(i == 1) {
            this.scores.put("§7Teamspeak", 1);
            this.scores.put("§f§omcone.eu", 0);
        }else if(i == 2) {
            this.scores.put("§7Website", 1);
            this.scores.put("§f§omcone.eu", 0);
        }else if(i == 3) {
            this.scores.put("§bTwitter", 1);
            this.scores.put("§f§o@mconeeu", 0);
        }else if(i == 4) {
            this.scores.put("§cYouTube", 1);
            this.scores.put("§f§omcone.eu/yt", 0);
        } else {
            this.scores.put("§7Teamspeak", 1);
            this.scores.put("§f§omcone.eu", 0);
        }

        for (HashMap.Entry<String, Integer> entry : this.scores.entrySet()) {
            String s = entry.getKey();
            int score = entry.getValue();

            this.o.getScore(s).setScore(score);
        }

        this.p.setScoreboard(sb);
    }

}
