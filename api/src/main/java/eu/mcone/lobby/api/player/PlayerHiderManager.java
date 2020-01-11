package eu.mcone.lobby.api.player;

import org.bukkit.entity.Player;

public interface PlayerHiderManager {

    void hidePlayers(Player p);

    void showPlayers(Player p);

    void playerJoined(Player j);

    boolean isHidden(Player p);
}
