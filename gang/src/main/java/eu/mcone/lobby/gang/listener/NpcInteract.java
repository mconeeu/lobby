/*
 * Copyright (c) 2017 - 2018 Dominik Lippl, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.gang.listener;

import eu.mcone.coresystem.api.bukkit.event.NpcInteractEvent;
import eu.mcone.lobby.gang.inventory.GangInventory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NpcInteract implements Listener {

    @EventHandler
    public void on(NpcInteractEvent e) {
        Player p = e.getPlayer();

        if (e.getAction().equals(NpcInteractEvent.Action.RIGHT_CLICK)) {
            switch (e.getNpc().getData().getName()) {
                case "Gang":
                    new GangInventory(p);
                    break;
            }
        }
    }

}
