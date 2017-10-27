/*
 * Copyright (c) 2017 Dominik L., Rufus Maiwald, BamDev and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.events;

import eu.mcone.lobby.utils.LocationFactory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntitiyDamageEvent implements Listener{

    @EventHandler
    public void onDamage(EntityDamageEvent e){
        Player p = (Player) e.getEntity();
        if(e.getEntity() instanceof Player){
            if(e.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
                e.setCancelled(true);
            }

            if(e.getEntity() instanceof Player){
                if(e.getCause() == EntityDamageEvent.DamageCause.DROWNING){
                    e.setCancelled(true);
                }
            }

            if(e.getEntity() instanceof Player){
                if(e.getCause() == EntityDamageEvent.DamageCause.LAVA){
                    e.setCancelled(true);
                }
            }

            if(e.getEntity() instanceof Player){
                if(e.getCause() == EntityDamageEvent.DamageCause.VOID){
                    p.teleport(LocationFactory.getConfigLocation("Navigator-1", LocationFactory.cfg));
                }
            }
        }
    }

}
