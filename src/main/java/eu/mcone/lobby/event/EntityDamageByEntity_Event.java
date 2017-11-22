/*
 * Copyright (c) 2017 Dominik L., Rufus Maiwald, BamDev and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.event;

import de.Dominik.BukkitCoreSystem.messager.PluginMessage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageByEntity_Event implements Listener{

    @EventHandler
    public void on(EntityDamageByEntityEvent e){
        if(e.getEntity() instanceof Player){
            e.setCancelled(true);

            if (e.getDamager() instanceof Player) {
                Player p = (Player) e.getEntity();
                Player d = (Player) e.getDamager();

                if (d.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§3§lProfil §8» §7§oEinstellungen / Stats / Freunde")) new PluginMessage("CMD" ,"friend add "+p.getName(), d);
            }
        }
    }

}
