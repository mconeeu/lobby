/*
 * Copyright (c) 2017 Dominik L., Rufus Maiwald, BamDev and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.scoreboard;

import eu.mcone.lobby.Main;
import org.bukkit.Bukkit;

import java.util.ArrayList;

public class ScoreboardManager {

    private static int count = 0;
    static ArrayList<eu.mcone.lobby.scoreboard.Scoreboard> scoreboards = new ArrayList<>();

    public ScoreboardManager() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), () -> {
            if (count == 4) {
                count = 0;
            }
            count++;

            for(Scoreboard sb : scoreboards) {
                sb.updateScoreboard(count);
            }
        }, 100, 100);
    }

}
