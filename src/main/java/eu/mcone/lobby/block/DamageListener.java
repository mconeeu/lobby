/*
 * Copyright (c) 2017 Dominik L., BamDev, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.block;

import eu.mcone.lobby.utils.Factory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DamageListener implements Listener {

    @EventHandler
    public void onAttack(EntityDamageByEntityEvent e){
        if(e.getEntity() instanceof Player){
            e.setCancelled(true);
        }
    }

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
                    p.teleport(Factory.getConfigLocation("Navigator-1", Factory.cfg));
                }
            }
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        Player p = e.getEntity();
        if ((e.getEntity() instanceof Player)) {
            e.setKeepInventory(true);
            e.setKeepLevel(true);
            e.setDeathMessage("");
            p.spigot().respawn();
        }
    }

}
