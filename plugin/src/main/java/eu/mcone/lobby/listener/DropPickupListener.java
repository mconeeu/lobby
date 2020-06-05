package eu.mcone.lobby.listener;

import eu.mcone.lobby.Lobby;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class DropPickupListener implements Listener {

    @EventHandler
    public void on(PlayerPickupItemEvent event) {
        Player p = event.getPlayer();
        event.setCancelled(
                !Lobby.getSystem().getBuildSystem().hasBuildModeEnabled(p)
        );
    }

}
