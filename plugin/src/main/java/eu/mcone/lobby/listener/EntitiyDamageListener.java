/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.listener;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.api.player.HotbarItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.inventory.meta.ItemMeta;

public class EntitiyDamageListener implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onEntityDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();

            if (e.getCause().equals(EntityDamageEvent.DamageCause.VOID)) {
                LobbyWorld.ONE_ISLAND.getWorld().teleport(p, "spawn");
            } else if (e.getCause().equals(EntityDamageEvent.DamageCause.FIRE) || e.getCause().equals(EntityDamageEvent.DamageCause.FIRE_TICK)) {
                p.setFireTicks(0);
            }

            e.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player) {
            if (e.getDamager() instanceof Player) {
                Player p = (Player) e.getEntity();
                ItemMeta meta = ((Player) e.getDamager()).getItemInHand().getItemMeta();

                if (meta != null) {
                    if (((Player) e.getDamager()).getItemInHand().hasItemMeta()) {
                        if (meta.getDisplayName() != null) {
                            if (meta.getDisplayName().equalsIgnoreCase(HotbarItem.PROFILE_DISPLAY_NAME)) {
                                CoreSystem.getInstance().getChannelHandler().sendPluginMessage(p, "CMD", "friend add " + p.getName());
                            }
                        }
                    }
                }
            }
        }

        e.setCancelled(true);
    }

    @EventHandler
    public void on(EntityExplodeEvent e) {
        e.blockList().clear();
    }

}
