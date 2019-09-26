/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.items.listener.effects;

import eu.mcone.gamesystem.api.enums.Item;
import eu.mcone.lobby.api.LobbyPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class EnderPearlListener implements Listener {

    @EventHandler
    public void on(PlayerInteractEvent e) {
        if (e.hasItem() && e.getItem().equals(Item.ENDERPEARL.getItemStack()) && (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR))) {
            Player p = e.getPlayer();

            if (p.hasPermission("lobby.silenthub")) {
                p.getInventory().setItem(3, null);
            } else {
                p.getInventory().setItem(2, null);
            }

            p.getWorld().playEffect(p.getLocation(), Effect.LARGE_SMOKE, 10);

            p.playSound(p.getLocation(), Sound.CLICK, 1, 1);

            Bukkit.getScheduler().scheduleSyncDelayedTask(LobbyPlugin.getInstance(), new Runnable() {

                @Override
                public void run() {
                    p.playSound(p.getLocation(), Sound.CLICK, 1, 1);

                    Bukkit.getScheduler().scheduleSyncDelayedTask(LobbyPlugin.getInstance(), new Runnable() {

                        @Override
                        public void run() {
                            p.playSound(p.getLocation(), Sound.CLICK, 1, 1);

                            Bukkit.getScheduler().scheduleSyncDelayedTask(LobbyPlugin.getInstance(), new Runnable() {

                                @Override
                                public void run() {
                                    p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);

                                    if (p.hasPermission("lobby.silenthub")) {
                                        p.getInventory().setItem(3, Item.ENDERPEARL.getItemStack());
                                        p.getWorld().playEffect(p.getLocation(), Effect.LARGE_SMOKE, 10);
                                        p.spigot().playEffect(p.getLocation(), Effect.FLAME, 1, 1, 1, 1, 1, 2, 100, 100);
                                        p.spigot().playEffect(p.getLocation(), Effect.ENDER_SIGNAL, 1, 1, 1, 1, 1, 2, 100, 100);

                                    } else {
                                        p.getInventory().setItem(2, Item.ENDERPEARL.getItemStack());
                                        p.getWorld().playEffect(p.getLocation(), Effect.LARGE_SMOKE, 10);
                                        p.spigot().playEffect(p.getLocation(), Effect.FLAME, 1, 1, 1, 1, 1, 2, 100, 100);
                                        p.spigot().playEffect(p.getLocation(), Effect.ENDER_SIGNAL, 1, 1, 1, 1, 1, 2, 100, 100);

                                    }


                                }
                            }, 15);
                        }
                    }, 10);
                }
            }, 10);
        }
    }

    @EventHandler
    public void on(ProjectileHitEvent e) {
        if (e.getEntity().getShooter() instanceof Player && e.getEntityType().equals(EntityType.ENDER_PEARL)) {
            Player p = (Player) e.getEntity().getShooter();


            p.spigot().playEffect(e.getEntity().getLocation(), Effect.WITCH_MAGIC, 1, 1, 1, 1, 1, 2, 100, 100);
            p.spigot().playEffect(e.getEntity().getLocation(), Effect.FLAME, 1, 1, 1, 1, 1, 4, 100, 100);
        }
    }

}