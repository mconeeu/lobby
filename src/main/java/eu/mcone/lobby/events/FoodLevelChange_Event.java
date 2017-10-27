/*
 * Copyright (c) 2017 Dominik L., Rufus Maiwald, BamDev and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodLevelChange_Event implements Listener{

    @EventHandler
    public void onFeed(FoodLevelChangeEvent e) {
        Player p = (Player)e.getEntity();

        if ((p instanceof Player)) {
            e.setCancelled(true);
        }
    }

}
