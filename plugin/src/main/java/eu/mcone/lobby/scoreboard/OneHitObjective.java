package eu.mcone.lobby.scoreboard;

import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.coresystem.api.bukkit.scoreboard.CoreSidebarObjectiveEntry;
import eu.mcone.lobby.onehit.LobbyOneHitManager;

public class OneHitObjective extends LobbyObjective {

    private final LobbyOneHitManager manager;

    public OneHitObjective(LobbyOneHitManager manager) {
        super("Lobby-OneHit");
        this.manager = manager;
    }

    @Override
    protected void onRegister(CorePlayer corePlayer, CoreSidebarObjectiveEntry entry) {
        setDisplayName("§7§l⚔ §f§l§nOneHit");

        entry.setScore(8, "");
        entry.setScore(7, "§8» §7KillStreak:");
        entry.setScore(6, " §f" + player.bukkit().getLevel());
        entry.setScore(5, "");
        entry.setScore(4, "§8» §7Spieler:");
        entry.setScore(3, " §f" + manager.getFighting().size());
        entry.setScore(2, "");
        entry.setScore(1, "§8»§7 Teamspeak:");
        entry.setScore(0, " §f§ots.mcone.eu");
    }

    @Override
    protected void onReload(CorePlayer corePlayer, CoreSidebarObjectiveEntry coreSidebarObjectiveEntry) {
        coreSidebarObjectiveEntry.setScore(3, " §f" + manager.getFighting().size());
        coreSidebarObjectiveEntry.setScore(6, " §f" + player.bukkit().getLevel());
    }
}
