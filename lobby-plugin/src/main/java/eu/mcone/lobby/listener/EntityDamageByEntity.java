/*
 * Copyright (c) 2017 - 2018 Dominik Lippl, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.listener;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.lobby.LobbyPlugin;
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

                if (!LobbyPlugin.getInstance().getNpcManager().isNPC(p.getDisplayName())) {
                    ItemMeta meta = d.getItemInHand().getItemMeta();
                    if (meta != null && meta.getDisplayName().equalsIgnoreCase("§3§lProfil §8» §7§oEinstellungen / Stats / Freunde"))
                        CoreSystem.getInstance().getChannelHandler().sendPluginMessage(p, "CMD", "friend add " + p.getName());
                }
            }
        }

        e.setCancelled(true);
    }

}
