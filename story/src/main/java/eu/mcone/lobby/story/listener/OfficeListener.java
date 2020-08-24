package eu.mcone.lobby.story.listener;

import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.story.office.LobbyOfficeManager;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerQuitEvent;

@RequiredArgsConstructor
public class OfficeListener implements Listener {

    private final LobbyOfficeManager manager;

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent e) {
        Player player = e.getPlayer();

        if (e.getFrom().equals(LobbyWorld.OFFICE.getWorld().bukkit())) {
            manager.quitOffice(player);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        manager.playerLeaved(e.getPlayer());
    }

}
