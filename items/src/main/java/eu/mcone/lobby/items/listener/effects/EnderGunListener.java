/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.items.listener.effects;

import org.bukkit.Effect;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class EnderGunListener implements Listener {

    @EventHandler
    public void on(PlayerInteractEvent e) {
        if (e.hasItem()/* && e.getItem().equals(Item.ENDERGUN.getItemStack())*/ && (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR))) {
            Player p = e.getPlayer();

            EnderPearl pearl = p.getWorld().spawn(p.getEyeLocation(), EnderPearl.class);
            pearl.setVelocity(p.getPlayer().getLocation().getDirection());
            pearl.setShooter(p);

            p.getWorld().playEffect(pearl.getLocation(), Effect.LARGE_SMOKE, 10);
        }
    }

    @EventHandler
    public void on(ProjectileHitEvent e) {
        if (e.getEntity().getShooter() instanceof Player && e.getEntityType().equals(EntityType.ENDER_PEARL)) {
            Player p = (Player) e.getEntity().getShooter();

            p.spigot().playEffect(e.getEntity().getLocation(), Effect.WITCH_MAGIC, 1, 1, 1, 1, 1, 2, 100, 100);
            p.spigot().playEffect(e.getEntity().getLocation(), Effect.FLAME, 1, 1, 1, 1, 1, 2, 100, 100);
        }
    }

}