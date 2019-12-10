package eu.mcone.lobby.listener;

import eu.mcone.lobby.onehit.OneHitManager;
import eu.mcone.lobby.story.LobbyStory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerCommandPreprocessEvent implements Listener {


    @EventHandler
    public void on(org.bukkit.event.player.PlayerCommandPreprocessEvent e) {
        Player p = e.getPlayer();

        if (e.getMessage().equalsIgnoreCase("/spawn")) {
            if (OneHitManager.isFighting.contains(p)) {
                OneHitManager.cancelTask(p);
            }
        }
    }


}

