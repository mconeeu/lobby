/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.api.scoreboard;

import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.coresystem.api.bukkit.scoreboard.CoreSidebarObjectiveEntry;

public class SidebarObjective extends LobbyObjective {

    public SidebarObjective() {
        super("Lobby-Main");
    }

    @Override
    protected void onRegister(CorePlayer corePlayer, CoreSidebarObjectiveEntry entry) {
        setDisplayName("§f§l§n"+player.bukkit().getName());

        entry.setScore(12, "");
        entry.setScore(11, "§8» §3§lMCONE.EU");
        entry.setScore(10, "§f§o Dein Nummer 1");
        entry.setScore(9, "§f§o Netzwerk");
        entry.setScore(8, "");
        entry.setScore(7, "§8»§7 Coins:");
        entry.setScore(6, " §b§o"+ player.getFormattedCoins());
        entry.setScore(5, "");
        entry.setScore(4, "§8»§7 Emeralds:");
        entry.setScore(3, " §a§o"+ player.getEmeralds());
        entry.setScore(2, "");
        entry.setScore(1, "§8»§7 Teamspeak:");
        entry.setScore(0, " §f§ots.mcone.eu");
    }

    @Override
    protected void onReload(CorePlayer corePlayer, CoreSidebarObjectiveEntry coreSidebarObjectiveEntry) {
        setDisplayName("§f§l§n" + player.bukkit().getName());
        coreSidebarObjectiveEntry.setScore(6, " §b§o"+ player.getFormattedCoins());
        coreSidebarObjectiveEntry.setScore(3, " §a§o"+ player.getEmeralds());
    }
}
