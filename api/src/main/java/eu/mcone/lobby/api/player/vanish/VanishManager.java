package eu.mcone.lobby.api.player.vanish;

import org.bukkit.entity.Player;

import java.util.Set;

public interface VanishManager {

    boolean setVanishPlayerVisibility(Player p, VanishPlayerVisibility target);

    boolean setVanishPlayerVisibility(Player p, VanishPlayerVisibility target, boolean notify);

    VanishPlayerVisibility getVanishPlayerVisibility(Player p);

    void joinSilentLobby(Player p);

    void quitSilentLobby(Player p);

    boolean isInSilentLobby(Player p);

    Set<Player> getSilentLobbyPlayers();

    boolean isInOffice(Player p);
}
