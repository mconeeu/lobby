package eu.mcone.lobby.games.scoreboard;

import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.coresystem.api.bukkit.scoreboard.CoreSidebarObjectiveEntry;
import eu.mcone.lobby.games.pvp.gungame.GunGameLobbyGame;

public class GungameObjective extends LobbyGameObjective {

    public GungameObjective(GunGameLobbyGame game) {
        super(game);
    }

    @Override
    protected void onRegister(CorePlayer corePlayer, CoreSidebarObjectiveEntry entry) {
        entry.setScore(8, "");
        entry.setScore(7, "§8» §7Level:");
        entry.setScore(6, " §f" + player.bukkit().getLevel());

    }

    @Override
    protected void onReload(CorePlayer corePlayer, CoreSidebarObjectiveEntry entry) {
        entry.setScore(6, " §f" + player.bukkit().getLevel());
    }
}
