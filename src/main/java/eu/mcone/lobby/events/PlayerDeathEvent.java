/*
 * Copyright (c) 2017 Dominik L., Rufus Maiwald, BamDev and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerDeathEvent implements Listener {

    @EventHandler
    public void onDeath(org.bukkit.event.entity.PlayerDeathEvent e){
        Player p = e.getEntity();
        if ((e.getEntity() instanceof Player)) {
            e.setKeepInventory(true);
            e.setKeepLevel(true);
            e.setDeathMessage("");
            p.spigot().respawn();
        }
    }

}
