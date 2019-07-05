/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.items.listener.effects;

import eu.mcone.lobby.api.enums.Item;
import org.bukkit.Effect;
import org.bukkit.entity.Egg;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class EasterGunListener implements Listener {

    @EventHandler
    public void on(PlayerInteractEvent e) {
        if (e.hasItem() && e.getItem().equals(Item.EASTERGUN.getItemStack()) && (e.getAction().equals(Action.LEFT_CLICK_BLOCK) || e.getAction().equals(Action.LEFT_CLICK_AIR))) {
            Player p = e.getPlayer();

            Egg egg = p.getWorld().spawn(p.getEyeLocation(), Egg.class);
            egg.setVelocity(p.getPlayer().getLocation().getDirection().multiply(1.2));
            egg.setShooter(p);

            p.getWorld().playEffect(egg.getLocation(), Effect.LARGE_SMOKE, 10);
        }
    }

    @EventHandler
    public void on(ProjectileHitEvent e) {
        if (e.getEntity().getShooter() instanceof Player && e.getEntityType().equals(EntityType.EGG)) {
            Player p = (Player) e.getEntity().getShooter();

            p.spigot().playEffect(e.getEntity().getLocation(), Effect.LAVA_POP, 1, 1, 1, 1, 1, 2, 100, 100);
            p.spigot().playEffect(e.getEntity().getLocation(), Effect.LARGE_SMOKE, 1, 1, 1, 1, 1, 2, 100, 100);
        }
    }

}
