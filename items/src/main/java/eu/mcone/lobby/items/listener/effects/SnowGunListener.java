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
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class SnowGunListener implements Listener {

    @EventHandler
    public void on(PlayerInteractEvent e) {
        if (e.hasItem() && e.getItem().equals(Item.SNOWGUN.getItemStack()) && (e.getAction().equals(Action.LEFT_CLICK_BLOCK) || e.getAction().equals(Action.LEFT_CLICK_AIR))) {
            Player p = e.getPlayer();

            Snowball snowball = p.getWorld().spawn(p.getEyeLocation(), Snowball.class);
            snowball.setVelocity(p.getPlayer().getLocation().getDirection());
            snowball.setShooter(p);

            p.getWorld().playEffect(snowball.getLocation(), Effect.LARGE_SMOKE, 10);

            if (p.hasPermission("lobby.silenthub")) {
                p.getInventory().setItem(3, null);
            } else {
                p.getInventory().setItem(2, null);
            }

            p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
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
                                        p.getInventory().setItem(3, Item.SNOWGUN.getItemStack());
                                    } else {
                                        p.getInventory().setItem(2, Item.SNOWGUN.getItemStack());
                                    }

                                }
                            }, 10);
                        }
                    }, 10);
                }
            }, 10);
        }
    }

    @EventHandler
    public void on(ProjectileHitEvent e) {
        if (e.getEntity().getShooter() instanceof Player && e.getEntityType().equals(EntityType.SNOWBALL)) {
            for (Player all : Bukkit.getOnlinePlayers()) {

                all.spigot().playEffect(e.getEntity().getLocation(), Effect.LAVA_POP, 1, 1, 1, 1, 1, 2, 50, 45);
                all.spigot().playEffect(e.getEntity().getLocation(), Effect.LARGE_SMOKE, 1, 1, 1, 1, 1, 2, 90, 55);
            }
        }
    }

}