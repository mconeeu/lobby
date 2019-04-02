/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.listener;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerDropItem implements Listener{

    @EventHandler
    public void on(PlayerDropItemEvent e){
        Player p = e.getPlayer();

        if (p.getGameMode() == GameMode.CREATIVE){
            e.setCancelled(false);
        } else {
            e.setCancelled(true);
        }
    }

}
