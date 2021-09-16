/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.scheduler;

import eu.mcone.lobby.Lobby;
import eu.mcone.lobby.api.player.LobbyPlayer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WorldRealTimeScheduler implements Runnable {

    @Override
    public void run() {
        for (LobbyPlayer lp : Lobby.getSystem().getOnlineLobbyPlayers()) {
            if (lp != null) {
                setCurrentRealTime(lp);
            }
        }
    }

    public static void setCurrentRealTime(LobbyPlayer lp) {
        if (lp.getSettings().isRealTime()) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HHmm");
            LocalDateTime date = LocalDateTime.now();
            long time = Long.parseLong(dtf.format(date) + "0");
            time -= 6000;
            if (time < 0) {
                time += 24000;
            }

            lp.bukkit().setPlayerTime(time, false);
        }
    }

}
