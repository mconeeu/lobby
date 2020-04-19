package eu.mcone.lobby.api.player;

import org.bukkit.entity.Player;

public interface PlayerHiderManager {

    void hidePlayers(Player p);

    void showPlayers(Player p);

    void updateHider(Player p);

    boolean isHidden(Player p);
}
