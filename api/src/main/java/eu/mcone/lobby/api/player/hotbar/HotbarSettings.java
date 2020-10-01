package eu.mcone.lobby.api.player.hotbar;

import eu.mcone.lobby.api.player.LobbyPlayer;
import org.bukkit.entity.Player;

public interface HotbarSettings {

    void updateInventory(Player p, LobbyPlayer lp);
}
