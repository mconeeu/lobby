package eu.mcone.lobby.scoreboard;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.coresystem.api.bukkit.scoreboard.CoreObjective;
import eu.mcone.coresystem.api.bukkit.scoreboard.CoreSidebarObjective;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.onehit.OneHitManager;
import eu.mcone.lobby.onehit.LobbyOneHitManager;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class OneHitObjective extends LobbyObjective {

    private final LobbyOneHitManager manager;

    public OneHitObjective(LobbyOneHitManager manager) {
        super("Lobby-OneHit");
        this.manager = manager;
    }

    @Override
    public void onRegister(CorePlayer player) {
        setDisplayName("§7§l⚔ §f§l§nOneHit");

        setScore(8, "");
        setScore(7, "§8» §7KillStreak:");
        setScore(6, " §f" + player.bukkit().getLevel());
        setScore(5, "");
        setScore(4, "§8» §7Spieler:");
        setScore(3, " §f" + manager.getFighting().size());
        setScore(2, "");
        setScore(1, "§8»§7 Teamspeak:");
        setScore(0, " §f§ots.mcone.eu");
    }

    @Override
    public void onReload(CorePlayer player) {
        setScore(3, " §f" + manager.getFighting().size());
        setScore(6, " §f" + player.bukkit().getLevel());
    }

}
