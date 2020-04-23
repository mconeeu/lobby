package eu.mcone.lobby.scoreboard;

import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.lobby.gungame.LobbyGungameManager;

public class GungameObjective extends LobbyObjective {

    private final LobbyGungameManager manager;

    public GungameObjective(LobbyGungameManager manager) {
        super("Lobby-Gungame");
        this.manager = manager;
    }

    @Override
    public void onRegister(CorePlayer player) {
        setDisplayName("§7§l⚔ §f§l§nGungame");

        setScore(8, "");
        setScore(7, "§8» §7Level:");
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
