/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.listener;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
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

                if (!CoreSystem.getInstance().getNpcManager().isNPC(p.getDisplayName())) {
                    ItemMeta meta = d.getItemInHand().getItemMeta();
                    if (meta != null && meta.getDisplayName().equalsIgnoreCase("§3§lProfil §8» §7§oEinstellungen / Stats / Freunde"))
                        CoreSystem.getInstance().getChannelHandler().sendPluginMessage(p, "CMD", "friend add " + p.getName());
                }
            }
        }

        e.setCancelled(true);
    }

}
