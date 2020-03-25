package eu.mcone.lobby.api.trap;

import org.bukkit.entity.Player;

public interface CatchManager {

    void setStart(Player p);

    void leave(Player p);

    boolean isCatching(Player p);

}
