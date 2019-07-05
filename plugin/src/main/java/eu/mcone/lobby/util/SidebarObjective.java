/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.util;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.coresystem.api.bukkit.scoreboard.CoreSidebarObjective;
import org.bukkit.scoreboard.DisplaySlot;

public class SidebarObjective extends CoreSidebarObjective {

    private static int i = 0;

    public SidebarObjective() {
        super("Lobby-Main");
    }

    @Override
    public void onRegister(CorePlayer player) {
        setDisplayName("§f§l§n"+player.bukkit().getDisplayName());

        setScore(12, "");
        setScore(11, "§8» §3§lMCONE.EU");
        setScore(10, "§f§oDein Nummer 1");
        setScore(9, "§f§oNetzwerk");
        setScore(8, "");
        setScore(7, "§8»§7 Coins:");
        setScore(6, " §b§o"+ player.getFormattedCoins());
        setScore(5, "");
        setScore(4, "§8»§7 Emeralds:");
        setScore(3, " §a§o"+ player.getEmeralds());
        setScore(2, "");
        setScore(1, "§8»§7 Teamspeak:");
        setScore(0, " §f§ots.mcone.eu");
    }

    @Override
    public void onReload(CorePlayer player) {
        setDisplayName("§f§l§n" + player.bukkit().getDisplayName());
        setScore(6, " §b§o"+ player.getFormattedCoins());
        setScore(3, " §a§o"+ player.getEmeralds());
    }

    public static void updateLines() {
        if (i >= 4) i=0;
        i++;

        for (final CorePlayer p : CoreSystem.getInstance().getOnlineCorePlayers()) {
            final CoreSidebarObjective o = (SidebarObjective) p.getScoreboard().getObjective(DisplaySlot.SIDEBAR);

            if (o != null) {
                o.setDisplayName("§f§l§n" + p.bukkit().getName());

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

}
