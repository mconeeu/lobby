/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.listener;

import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.LobbyWorld;
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
                LobbyPlugin.getInstance().getLobbyWorld(LobbyWorld.DIM_1).teleport(p, "spawn");
            }
        }
    }

}
