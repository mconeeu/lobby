package eu.mcone.lobby.api.player;

import org.bukkit.entity.Player;

public interface SilentLobbyManager {
    void deactivateSilentLobby(Player p);

    void activateSilentLobby(Player p);

    boolean isActivatedSilentHub(Player p);
}
