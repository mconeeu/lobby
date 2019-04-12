/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.listener;

import eu.mcone.coresystem.api.bukkit.event.MoneyChangeEvent;
import eu.mcone.coresystem.api.bukkit.event.PermissionChangeEvent;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.DisplaySlot;

public class PlayerUpdateListener implements Listener {

    @EventHandler
    public void on(MoneyChangeEvent e) {
        CorePlayer p = e.getPlayer();

        if (p != null) {
            p.getScoreboard().getObjective(DisplaySlot.SIDEBAR).reload();
        }
    }

    @EventHandler
    public void on(PermissionChangeEvent e) {
        CorePlayer p = e.getPlayer();

        if (e.getKind().equals(PermissionChangeEvent.Kind.GROUP_CHANGE) && p != null) {
            p.getScoreboard().getObjective(DisplaySlot.SIDEBAR).reload();
        }
    }

}
