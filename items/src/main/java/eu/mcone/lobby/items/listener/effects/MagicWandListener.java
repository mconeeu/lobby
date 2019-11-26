package eu.mcone.lobby.items.listener.effects;

import eu.mcone.gamesystem.api.enums.Item;
import eu.mcone.lobby.api.LobbyPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.Potion;

public class MagicWandListener implements Listener {

    @EventHandler
    public void on(PlayerInteractEvent e) {
        if (e.hasItem() && e.getItem().equals(Item.MAGICWAND.getItemStack()) && (e.getAction().equals(Action.LEFT_CLICK_BLOCK) || e.getAction().equals(Action.LEFT_CLICK_AIR))) {
            Player p = e.getPlayer();

            Arrow arrow = p.getWorld().spawn(p.getEyeLocation(), Arrow.class);
            arrow.setVelocity(p.getPlayer().getLocation().getDirection());
            arrow.setShooter(p);


            p.getWorld().playEffect(arrow.getLocation(), Effect.WITCH_MAGIC, 10);

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
                                        p.getInventory().setItem(3, Item.MAGICWAND.getItemStack());
                                    } else {
                                        p.getInventory().setItem(2, Item.MAGICWAND.getItemStack());
                                    }

                                }
                            }, 21);
                        }
                    }, 10);
                }
            }, 15);
        }
    }

    @EventHandler
    public void on(ProjectileHitEvent e) {
        if (e.getEntity().getShooter() instanceof Player && e.getEntityType().equals(EntityType.ARROW)) {
            for (Player all : Bukkit.getOnlinePlayers()) {
                all.spigot().playEffect(e.getEntity().getLocation(), Effect.SMALL_SMOKE, 1, 1, 1, 1, 1, 3, 25, 30);
                all.spigot().playEffect(e.getEntity().getLocation(), Effect.MAGIC_CRIT, 1, 1, 1, 1, 1, 3, 78, 45);
            }
        }
    }

}
