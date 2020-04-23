package eu.mcone.lobby.api.gungame;

import org.bukkit.entity.Player;

public interface GungameManager {

    void setStart(Player p);

    void leave(Player p);

    boolean isFighting(Player p);

    void addSave(Player p);

    boolean isSave(Player p);

}
