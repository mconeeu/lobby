/*
 * Copyright (c) 2017 Dominik L., Rufus Maiwald, BamDev and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.event;

import eu.mcone.bukkitcoresystem.messager.PluginMessage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.meta.ItemMeta;

public class EntityDamageByEntity_Event implements Listener{

    @EventHandler
    public void on(EntityDamageByEntityEvent e){
        System.out.println("new EntityDamageByEntityEvent");
        if(e.getEntity() instanceof Player){
            System.out.println("Entity instanceof Player");
            Player p = (Player) e.getEntity();

            if (e.getDamager() instanceof Player) {
                Player d = (Player) e.getDamager();

                System.out.println("Damager instanceof Player");
                ItemMeta meta = d.getItemInHand().getItemMeta();
                if (meta != null)
                    if (meta.getDisplayName().equalsIgnoreCase("§3§lProfil §8» §7§oEinstellungen / Stats / Freunde")) PluginMessage.send("CMD" ,"friend add "+p.getName(), d);
            }
        }

        e.setCancelled(true);
    }

}
