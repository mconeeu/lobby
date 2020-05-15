/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.scoreboard;

import eu.mcone.coresystem.api.bukkit.player.CorePlayer;

public class SidebarObjective extends LobbyObjective {

    public SidebarObjective() {
        super("Lobby-Main");
    }

    @Override
    public void onRegister(CorePlayer player) {
        setDisplayName("§f§l§n"+player.bukkit().getName());

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
        setDisplayName("§f§l§n" + player.bukkit().getName());
        setScore(6, " §b§o"+ player.getFormattedCoins());
        setScore(3, " §a§o"+ player.getEmeralds());
    }

}
