package eu.mcone.lobby.games.scoreboard;

import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.coresystem.api.bukkit.scoreboard.CoreSidebarObjectiveEntry;
import eu.mcone.lobby.games.pvp.onehit.OneHitLobbyGame;

public class OneHitObjective extends LobbyGameObjective {

    public OneHitObjective(OneHitLobbyGame game) {
        super(game);
    }

    @Override
    protected void onRegister(CorePlayer corePlayer, CoreSidebarObjectiveEntry entry) {
        entry.setScore(8, "");
        entry.setScore(7, "§8» §7KillStreak:");
        entry.setScore(6, " §f" + player.bukkit().getLevel());
    }

    @Override
    protected void onReload(CorePlayer corePlayer, CoreSidebarObjectiveEntry coreSidebarObjectiveEntry) {
        coreSidebarObjectiveEntry.setScore(6, " §f" + player.bukkit().getLevel());
    }
}
