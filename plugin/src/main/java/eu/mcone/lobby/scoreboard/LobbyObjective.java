package eu.mcone.lobby.scoreboard;

import eu.mcone.coresystem.api.bukkit.scoreboard.CoreSidebarObjective;

public abstract class LobbyObjective extends CoreSidebarObjective {

    private static int i = 0;

    public LobbyObjective(String name) {
        super(name);
    }

   /* public static void updateLines() {
        if (i >= 4) i = 0;
        i++;

        for (final CorePlayer p : CoreSystem.getInstance().getOnlineCorePlayers()) {
            LobbyPlayer lobbyPlayer = LobbyPlugin.getInstance().getLobbyPlayer(p);
            if (lobbyPlayer.getSettings().isScoreboard()) {
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
    }  */

}
