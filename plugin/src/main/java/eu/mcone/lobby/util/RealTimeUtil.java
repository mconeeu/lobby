package eu.mcone.lobby.util;

import eu.mcone.lobby.Lobby;
import eu.mcone.lobby.api.player.LobbyPlayer;

import java.time.LocalDateTime;

public class RealTimeUtil implements Runnable {

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
            LocalDateTime date = LocalDateTime.now();
            //   lp.bukkit().setPlayerTime((long) (date.getHour() * 1000 + ((double)date.getMinute()/60)*1000 + (date.getSecond() /60)*100), false);
            lp.bukkit().setPlayerTime((long) (((double) ((date.getHour() * 60) + date.getMinute())) * (24000D / 1440D) - 6000), false);
        }
    }

}
