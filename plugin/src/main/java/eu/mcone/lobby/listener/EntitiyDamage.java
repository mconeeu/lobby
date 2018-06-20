/*
 * Copyright (c) 2017 - 2018 Dominik Lippl, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.listener;

import eu.mcone.lobby.LobbyPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntitiyDamage implements Listener{

    @EventHandler
    public void on(EntityDamageEvent e){
        if(e.getEntity() instanceof Player){
            Player p = (Player) e.getEntity();

            if(e.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
                e.setCancelled(true);
            }

            if(e.getCause() == EntityDamageEvent.DamageCause.DROWNING){
                e.setCancelled(true);
            }

            if(e.getCause() == EntityDamageEvent.DamageCause.LAVA){
                e.setCancelled(true);
            }

            if(e.getCause() == EntityDamageEvent.DamageCause.VOID){
                LobbyPlugin.getInstance().getWorld().teleport(p, "spawn");
            }
        }
    }

}
