/*
 * Copyright (c) 2017 Dominik L., Rufus Maiwald, BamDev and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.listener;

import eu.mcone.bukkitcoresystem.channel.PluginMessage;
import eu.mcone.bukkitcoresystem.npc.NPC;
import eu.mcone.lobby.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.meta.ItemMeta;

public class EntityDamageByEntity implements Listener{

    @EventHandler
    public void on(EntityDamageByEntityEvent e){
        if(e.getEntity() instanceof Player){
            Player p = (Player) e.getEntity();

            if (e.getDamager() instanceof Player) {
                Player d = (Player) e.getDamager();

                if (!Main.npc.isNPC(p.getDisplayName())) {
                    ItemMeta meta = d.getItemInHand().getItemMeta();
                    if (meta != null && meta.getDisplayName().equalsIgnoreCase("§3§lProfil §8» §7§oEinstellungen / Stats / Freunde"))
                        new PluginMessage("CMD", "friend add " + p.getName(), d);
                }
            }
        }

        e.setCancelled(true);
    }

}
