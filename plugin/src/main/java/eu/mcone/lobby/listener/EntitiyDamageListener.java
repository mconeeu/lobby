/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.listener;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.onehit.OneHitManager;
import javafx.scene.layout.Priority;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class EntitiyDamageListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (!OneHitManager.isFighting.contains(p)) {
                e.setCancelled(true);
            }

            if (e.getCause() == EntityDamageEvent.DamageCause.VOID) {
                LobbyWorld.ONE_ISLAND.getWorld().teleport(p, "spawn");
            } else if (e.getCause().equals(EntityDamageEvent.DamageCause.FIRE) || e.getCause().equals(EntityDamageEvent.DamageCause.FIRE_TICK)) {
                p.setFireTicks(0);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player) {
            if (e.getDamager() instanceof Player) {
                Player p = (Player) e.getEntity();
                Player k = (Player) e.getDamager();
                ItemMeta meta = ((Player) e.getDamager()).getItemInHand().getItemMeta();


                if (OneHitManager.isFighting.contains(p) && OneHitManager.isFighting.contains(k)) {
                    if (e.getDamager() instanceof Arrow) {
                        p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 5, 4500, false, false));
                        p.setHealth(0);

                        return;
                    } else if (k.getItemInHand().hasItemMeta() && k.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§fOneHit-Schwert")) {
                         p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 5, 4500, false, false));
                        p.setHealth(0);
                        return;
                    }
                }

                if (meta != null && meta.getDisplayName().equalsIgnoreCase("§3§lProfil §8» §7§oEinstellungen / Stats / Freunde"))
                    CoreSystem.getInstance().getChannelHandler().sendPluginMessage(p, "CMD", "friend add " + p.getName());

                e.setCancelled(true);
            }
        }
    }

}
