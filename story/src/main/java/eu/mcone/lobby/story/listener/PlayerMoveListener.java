package eu.mcone.lobby.story.listener;

import eu.mcone.coresystem.api.bukkit.facades.Msg;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.api.story.progress.bank.BankRobberySmallProgress;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player player = e.getPlayer();

        Location robbery_entrance = LobbyPlugin.getInstance().getLobbyWorld(LobbyWorld.ONE_ISLAND).getLocation("robbery-entrance");

        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(player);
        if (lp != null && lp.getBankprogressId() != BankRobberySmallProgress.BANK_ROBBERY_MIDDLE.getId()) {
            if (player.getWorld().equals(robbery_entrance.getWorld()) && player.getLocation().distance(robbery_entrance) <= 1) {
                LobbyPlugin.getInstance().getLobbyWorld(LobbyWorld.ONE_ISLAND).teleportSilently(player, "bank");
                Msg.send(player, "§4Du wurdest aus der Bank geworfen, weil du zu nahe an der Hinter Tür warst.");
            }
        }
    }
}
