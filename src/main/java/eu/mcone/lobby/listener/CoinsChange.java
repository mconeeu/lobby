/*
 * Copyright (c) 2017 Dominik L., Rufus Maiwald, BamDev and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.listener;

import eu.mcone.bukkitcoresystem.event.CoinsChangeEvent;
import eu.mcone.bukkitcoresystem.player.CorePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.DisplaySlot;

public class CoinsChange implements Listener {

    @EventHandler
    public void on(CoinsChangeEvent e) {
        CorePlayer p = e.getPlayer();

        if (p != null) {
            p.getScoreboard().getObjective(DisplaySlot.SIDEBAR).reload();
        }
    }

}
