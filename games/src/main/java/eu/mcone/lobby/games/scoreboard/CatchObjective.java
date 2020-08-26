package eu.mcone.lobby.games.scoreboard;

import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.coresystem.api.bukkit.scoreboard.CoreSidebarObjectiveEntry;
import eu.mcone.lobby.games.pvp.catchgame.CatchLobbyGame;

public class CatchObjective extends LobbyGameObjective {

    public CatchObjective(CatchLobbyGame game) {
        super(game);
    }

    @Override
    protected void onRegister(CorePlayer corePlayer, CoreSidebarObjectiveEntry entry) {
        super.onRegister(corePlayer, entry);

        entry.setScore(8, "");
        entry.setScore(7, "§8» §7Rolle:");

        if (player.bukkit().getLevel() == 1) {
            entry.setScore(6, " §fFänger");
        } else {
            entry.setScore(6, " §fLäufer");
        }
    }

    @Override
    protected void onReload(CorePlayer corePlayer, CoreSidebarObjectiveEntry entry) {
        super.onReload(corePlayer, entry);
        if (player.bukkit().getLevel() == 1) {
            entry.setScore(6, " §fFänger");
        } else {
            entry.setScore(6, " §fLäufer");
        }
    }
}
