package eu.mcone.lobby.story.listener;

import eu.mcone.coresystem.api.bukkit.npc.NPC;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.enums.Progress;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class WorldChangeListener implements Listener {

    @EventHandler
    public void on(PlayerChangedWorldEvent e) {
        for (Progress progress : Progress.values()) {
            NPC npc = progress.getNpc();
            npc.toggleVisibility(e.getPlayer(), false);
        }

        LobbyPlayerLoadedListener.spawnStoryNpcs(LobbyPlugin.getInstance().getGamePlayer(e.getPlayer().getUniqueId()));
    }

}
