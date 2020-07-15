package eu.mcone.lobby.scoreboard;

import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.coresystem.api.bukkit.scoreboard.CoreSidebarObjectiveEntry;
import eu.mcone.lobby.trap.TrapManager;

public class CatchObjective extends LobbyObjective {

    private final TrapManager manager;

    public CatchObjective(TrapManager manager) {
        super("Lobby-Fangen");
        this.manager = manager;
    }


    @Override
    protected void onRegister(CorePlayer corePlayer, CoreSidebarObjectiveEntry entry) {
        entry.setTitle("§7§l⚔ §f§l§nFangen");

        entry.setScore(8, "");
        entry.setScore(7, "§8» §7Rolle:");
        if (player.bukkit().getLevel() == 1) {
            entry.setScore(6, " §fFänger");
        } else {
            entry.setScore(6, " §fLäufer");
        }
        entry.setScore(5, "");
        entry.setScore(4, "§8» §7Spieler:");
        entry.setScore(3, " §f" + manager.getCatching().size());
        entry.setScore(2, "");
        entry.setScore(1, "§8»§7 Teamspeak:");
        entry.setScore(0, " §f§ots.mcone.eu");
    }

    @Override
    protected void onReload(CorePlayer corePlayer, CoreSidebarObjectiveEntry entry) {
        entry.setScore(3, " §f" + manager.getCatching().size());
        if (player.bukkit().getLevel() == 1) {
            entry.setScore(6, " §fFänger");
        } else {
            entry.setScore(6, " §fLäufer");
        }
    }
}
