package eu.mcone.lobby.scoreboard;

import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.lobby.trap.TrapManager;

public class CatchObjective extends LobbyObjective {

    private final TrapManager manager;

    public CatchObjective(TrapManager manager) {
        super("Lobby-Fangen");
        this.manager = manager;
    }

    @Override
    public void onRegister(CorePlayer player) {
        setDisplayName("§7§l⚔ §f§l§nFangen");

        setScore(8, "");
        setScore(7, "§8» §7Rolle:");
        if (player.bukkit().getLevel() == 1) {
            setScore(6, " §fFänger");
        } else {
            setScore(6, " §fLäufer");
        }
        setScore(5, "");
        setScore(4, "§8» §7Spieler:");
        setScore(3, " §f" + manager.getCatching().size());
        setScore(2, "");
        setScore(1, "§8»§7 Teamspeak:");
        setScore(0, " §f§ots.mcone.eu");
    }

    @Override
    public void onReload(CorePlayer player) {
        setScore(3, " §f" + manager.getCatching().size());
        if (player.bukkit().getLevel() == 1) {
            setScore(6, " §fFänger");
        } else {
            setScore(6, " §fLäufer");
        }
}

}
