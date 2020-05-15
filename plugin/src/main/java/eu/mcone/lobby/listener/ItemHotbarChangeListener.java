package eu.mcone.lobby.listener;

import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.player.LobbyPlayer;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;

public class ItemHotbarChangeListener implements Listener {

    @EventHandler
    public void on(PlayerItemHeldEvent e) {
        Player player = e.getPlayer();
        LobbyPlayer lobbyPlayer = LobbyPlugin.getInstance().getLobbyPlayer(player);

        if (lobbyPlayer.getSettings().isHotbarChangeSound()) {
            player.playSound(e.getPlayer().getLocation(), Sound.ITEM_PICKUP, 0.5F, 1);
        }
    }
}
