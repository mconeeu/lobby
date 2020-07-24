package eu.mcone.lobby.scoreboard;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.coresystem.api.bukkit.scoreboard.CoreSidebarObjective;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.player.LobbyPlayer;
import org.bukkit.scoreboard.DisplaySlot;

public abstract class LobbyObjective extends CoreSidebarObjective {

    private static int i = 0;

    public LobbyObjective(String name) {
        super(name);
    }

    public static void updateLines() {
        if (i >= 4) i = 0;
        i++;

        for (final CorePlayer p : CoreSystem.getInstance().getOnlineCorePlayers()) {
            LobbyPlayer lobbyPlayer = LobbyPlugin.getInstance().getLobbyPlayer(p);
            if (lobbyPlayer.getSettings().isScoreboard()) {
                final CoreSidebarObjective o = (CoreSidebarObjective) p.getScoreboard().getObjective(DisplaySlot.SIDEBAR);

                if (o != null) {
                    o.update(entry -> {
                        if (o instanceof SidebarObjective) {
                            entry.setTitle("§f§l§n" + p.bukkit().getName());
                        }

                        if (i == 1) {
                            entry.setScore(1, "§8»§7 Teamspeak:");
                            entry.setScore(0, " §f§ots.mcone.eu");
                        } else if (i == 2) {
                            entry.setScore(1, "§8»§7 Website:");
                            entry.setScore(0, " §f§omcone.eu");
                        } else if (i == 3) {
                            entry.setScore(1, "§8»§7 Twitter:");
                            entry.setScore(0, " §b§o@mconeeu");
                        } else if (i == 4) {
                            entry.setScore(1, "§8»§7 YouTube:");
                            entry.setScore(0, " §c§oyt.mcone.eu");
                        } else {
                            entry.setScore(1, "§8»§7 Teamspeak:");
                            entry.setScore(0, " §f§ots.mcone.eu");
                        }
                    });
                }
            }
        }
    }
}
