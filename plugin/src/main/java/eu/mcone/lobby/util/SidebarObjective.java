/*
 * Copyright (c) 2017 - 2018 Dominik Lippl, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
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
        setScore(10, "§7§oDein Nummer 1");
        setScore(9, "§7§oMinecraftnetzwerk");
        setScore(8, "");
        setScore(7, "§7Rang:");
        setScore(6, player.getMainGroup().getLabel());
        setScore(5, "");
        setScore(4, "§7Coins:");
        setScore(3, "§o"+ player.getCoins());
        setScore(2, "");
        setScore(1, "§7Teamspeak");
        setScore(0, "§f§omcone.eu");
    }

    @Override
    public void onReload(CorePlayer player) {
        setScore(6, player.getMainGroup().getLabel());
        setScore(3, "§o"+player.getCoins());
    }

    public static void updateLines() {
        if (i >= 4) i=0;
        i++;

        for (final CorePlayer p : CoreSystem.getInstance().getOnlineCorePlayers()) {
            final CoreSidebarObjective o = (SidebarObjective) p.getScoreboard().getObjective(DisplaySlot.SIDEBAR);

            if (o != null) {
                o.setDisplayName("§f§l§n" + p.bukkit().getName());

                if (i == 1) {
                    o.setScore(1, "§7Teamspeak:");
                    o.setScore(0, "§f§omcone.eu");
                } else if (i == 2) {
                    o.setScore(1, "§7Website:");
                    o.setScore(0, "§f§omcone.eu");
                } else if (i == 3) {
                    o.setScore(1, "§bTwitter:");
                    o.setScore(0, "§f§o@mconeeu");
                } else if (i == 4) {
                    o.setScore(1, "§cYouTube:");
                    o.setScore(0, "§f§omcone.eu/yt");
                } else {
                    o.setScore(1, "§7Teamspeak");
                    o.setScore(0, "§f§omcone.eu");
                }
            }
        }
    }

}
