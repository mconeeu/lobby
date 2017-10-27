/*
 * Copyright (c) 2017 Dominik L., Rufus Maiwald, BamDev and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.events;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlace_Event implements Listener{

    @EventHandler
    public void onPlace(BlockPlaceEvent e){
        Player p = e.getPlayer();

        if(p.getGameMode() == GameMode.CREATIVE){
            e.setCancelled(false);
        }else{
            e.setCancelled(true);

        }
    }

}
