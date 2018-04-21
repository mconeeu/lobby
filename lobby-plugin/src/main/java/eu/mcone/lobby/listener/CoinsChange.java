/*
 * Copyright (c) 2017 - 2018 Dominik Lippl, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.listener;

import eu.mcone.coresystem.api.bukkit.event.CoinsChangeEvent;
import eu.mcone.coresystem.api.bukkit.player.BukkitCorePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.DisplaySlot;

public class CoinsChange implements Listener {

    @EventHandler
    public void on(CoinsChangeEvent e) {
        BukkitCorePlayer p = e.getPlayer();

        if (p != null) {
            p.getScoreboard().getObjective(DisplaySlot.SIDEBAR).reload();
        }
    }

}
