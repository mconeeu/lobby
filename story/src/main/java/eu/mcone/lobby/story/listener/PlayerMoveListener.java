package eu.mcone.lobby.story.listener;

import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.api.enums.BankProgress;
import eu.mcone.lobby.api.player.LobbyPlayer;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class PlayerMoveListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player player = e.getPlayer();

        Location robbery_entrance = LobbyPlugin.getInstance().getLobbyWorld(LobbyWorld.ONE_ISLAND).getLocation("robbery-entrance");

        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(player);
        if (lp.getBankprogressId() != BankProgress.BANK_ROBBERY_MIDDLE.getId()) {
            if (
                    player.getLocation().distance(robbery_entrance) <= 1) {
                Vector v1 = player.getLocation().getDirection().multiply(0.8).setX(0.8);
                player.setVelocity(v1);
            }
        }
    }
}
