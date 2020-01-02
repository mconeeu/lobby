package eu.mcone.lobby.scoreboard;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.coresystem.api.bukkit.scoreboard.CoreSidebarObjective;
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
            final CoreSidebarObjective o = (CoreSidebarObjective) p.getScoreboard().getObjective(DisplaySlot.SIDEBAR);

            if (o != null) {
                if (o instanceof SidebarObjective) {
                    o.setDisplayName("§f§l§n" + p.bukkit().getName());
                }

                if (i == 1) {
                    o.setScore(1, "§8»§7 Teamspeak:");
                    o.setScore(0, " §f§ots.mcone.eu");
                } else if (i == 2) {
                    o.setScore(1, "§8»§7 Website:");
                    o.setScore(0, " §f§omcone.eu");
                } else if (i == 3) {
                    o.setScore(1, "§8»§7 Twitter:");
                    o.setScore(0, " §b§o@mconeeu");
                } else if (i == 4) {
                    o.setScore(1, "§8»§7 YouTube:");
                    o.setScore(0, " §c§oyt.mcone.eu");
                } else {
                    o.setScore(1, "§8»§7 Teamspeak:");
                    o.setScore(0, " §f§ots.mcone.eu");
                }
            }
        }
    }

    @Override
    public void unregister() {
        super.unregister();
        objective.unregister();
    }
}
