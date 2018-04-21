/*
 * Copyright (c) 2017 - 2018 Dominik Lippl, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerDeath implements Listener {

    @EventHandler
    public void on(org.bukkit.event.entity.PlayerDeathEvent e){
        Player p = e.getEntity();

        e.setKeepInventory(true);
        e.setKeepLevel(true);
        e.setDeathMessage("");
        p.spigot().respawn();
    }

}
