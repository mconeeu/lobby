package eu.mcone.lobby.story.listener;

import eu.mcone.lobby.api.LobbyPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class WorldChangeListener implements Listener {

    @EventHandler
    public void on(PlayerChangedWorldEvent e) {
        GeneralPlayerListener.spawnStoryNpcs(
                LobbyPlugin.getInstance().getLobbyPlayer(e.getPlayer().getUniqueId())
        );
    }

}
