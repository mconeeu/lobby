package eu.mcone.lobby.story.listener;

import eu.mcone.lobby.story.LobbyStory;
import eu.mcone.lobby.story.jumpnrun.JumpAndRunManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerCommandPreprocessEvent implements Listener {


    @EventHandler
    public void on(org.bukkit.event.player.PlayerCommandPreprocessEvent e) {
        Player p = e.getPlayer();

        if (e.getMessage().equalsIgnoreCase("/spawn")) {
            LobbyStory.getInstance().getJumpAndRunManager().setCancel(p);
        }
    }


}

