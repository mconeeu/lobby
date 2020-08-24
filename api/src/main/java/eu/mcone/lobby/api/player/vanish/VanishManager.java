package eu.mcone.lobby.api.player.vanish;

import org.bukkit.entity.Player;

public interface VanishManager {

    void setVanishPlayerVisibility(Player p, VanishPlayerVisibility target);

    void setVanishPlayerVisibility(Player p, VanishPlayerVisibility target, boolean silent);

    VanishPlayerVisibility getVanishPlayerVisibility(Player p);

    void joinSilentLobby(Player p);

    void quitSilentLobby(Player p);

    boolean isInSilentLobby(Player p);

}
