package eu.mcone.lobby.items.listener.effects;

import eu.mcone.gamesystem.api.enums.Item;
import eu.mcone.lobby.api.LobbyPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Collection;

public class SplashPotionListener implements Listener {

    public static ArrayList<Player> Splahpotioneffect = new ArrayList<>();

    @EventHandler
    public void on(PlayerInteractEvent e) {
        if (e.hasItem() && e.getItem().equals(Item.SPLASH_POTION.getItemStack()) && (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR))) {
            Player p = e.getPlayer();


            Bukkit.getScheduler().scheduleSyncDelayedTask(LobbyPlugin.getInstance(), new Runnable() {

                @Override
                public void run() {
                    p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
                    p.playSound(p.getLocation(), Sound.GLASS, 1, 1);
                    p.playSound(p.getLocation(), Sound.SPLASH2, 2, 1);
                    p.getActivePotionEffects().clear();
                    p.removePotionEffect(PotionEffectType.INVISIBILITY);
                    p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 320, 1));
                    Splahpotioneffect.add(p);

                    World w = p.getWorld();

                    w.playEffect(p.getLocation(), Effect.FLAME, 3);
                    w.playEffect(p.getLocation(), Effect.FLAME, 1);
                    w.playEffect(p.getLocation(), Effect.WITCH_MAGIC, 5);

                    Bukkit.getScheduler().scheduleSyncDelayedTask(LobbyPlugin.getInstance(), new Runnable() {

                        @Override
                        public void run() {
                            p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
                 /*           if (p.hasPermission("lobby.silenthub")) {
                                p.getInventory().setItem(3, Item.SPLASH_POTION.getItemStack());
                            } else {
                                p.getInventory().setItem(2, Item.SPLASH_POTION.getItemStack());
                            }

                  */

                            Bukkit.getScheduler().scheduleSyncDelayedTask(LobbyPlugin.getInstance(), new Runnable() {

                                @Override
                                public void run() {
                                    p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);

                                    if (p.hasPermission("lobby.silenthub")) {
                                        p.getInventory().setItem(3, Item.SPLASH_POTION.getItemStack());

                                        if (Splahpotioneffect.contains(p)) {
                                            Splahpotioneffect.remove(p);
                                        }
                                        p.getWorld().playEffect(p.getLocation(), Effect.LARGE_SMOKE, 10);
                                        p.getWorld().playEffect(p.getLocation(), Effect.FLAME, 10);
                                        p.getWorld().playEffect(p.getLocation(), Effect.ENDER_SIGNAL, 10);
                                        p.spigot().playEffect(p.getLocation(), Effect.FLAME, 1, 1, 1, 1, 1, 2, 100, 100);
                                        p.spigot().playEffect(p.getLocation(), Effect.ENDER_SIGNAL, 1, 1, 1, 1, 1, 2, 100, 100);

                                    } else {
                                        p.getInventory().setItem(2, Item.SPLASH_POTION.getItemStack());
                                        p.getWorld().playEffect(p.getLocation(), Effect.LARGE_SMOKE, 10);
                                        p.getWorld().playEffect(p.getLocation(), Effect.FLAME, 10);
                                        p.getWorld().playEffect(p.getLocation(), Effect.ENDER_SIGNAL, 10);
                                        p.spigot().playEffect(p.getLocation(), Effect.FLAME, 1, 1, 1, 1, 1, 1, 100, 100);
                                        p.spigot().playEffect(p.getLocation(), Effect.ENDER_SIGNAL, 1, 1, 1, 1, 1, 2, 100, 100);
                                        if (Splahpotioneffect.contains(p)) {
                                            Splahpotioneffect.remove(p);
                                        }
                                    }


                                }
                            }, 220);
                        }
                    }, 95);
                }
            }, 4);
        }
    }

    @EventHandler
    public void on(PlayerMoveEvent e) {
        Player p = e.getPlayer();

        if (Splahpotioneffect.contains(p)) {
            p.getLocation().getWorld().playEffect(p.getLocation(), Effect.SPELL, 9);
            p.getLocation().getWorld().playEffect(p.getLocation(), Effect.SPELL, 9);
            p.getLocation().getWorld().playEffect(p.getLocation(), Effect.SPELL, 9);
            p.getLocation().getWorld().playEffect(p.getLocation(), Effect.SPELL, 9);
            p.getLocation().getWorld().playEffect(p.getLocation(), Effect.SPELL, 2);
            p.getLocation().getWorld().playEffect(p.getLocation(), Effect.FIREWORKS_SPARK, 5);


        }
    }

    @EventHandler
    public void onProjectileLaunch(ProjectileLaunchEvent e) {
        Projectile projectile = e.getEntity();

        if (projectile instanceof ThrownPotion) {
            ThrownPotion pot = (ThrownPotion) projectile;
            Collection<PotionEffect> effects = pot.getEffects();
            for (PotionEffect p : effects) {
                if (p.getType().equals(PotionEffectType.INVISIBILITY)) {
                    projectile.getLocation().getWorld().playEffect(projectile.getLocation(), Effect.POTION_BREAK, 5);
                    projectile.getLocation().getWorld().playEffect(projectile.getLocation(), Effect.POTION_SWIRL, 5);
                    e.setCancelled(true);
                    break;
                }
            }
        }
    }

}

