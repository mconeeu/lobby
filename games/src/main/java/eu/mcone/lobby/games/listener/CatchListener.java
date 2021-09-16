/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.games.listener;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.facades.Msg;
import eu.mcone.coresystem.api.bukkit.facades.Sound;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.games.pvp.catchgame.CatchItem;
import eu.mcone.lobby.games.pvp.catchgame.CatchLobbyGame;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fish;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class CatchListener implements Listener {

    private final CatchLobbyGame game;

    @EventHandler
    public void onFish(PlayerFishEvent e) {
        Player p = e.getPlayer();
        Fish h = e.getHook();

        if (game.isPlaying(p)) {
            if ((e.getState().equals(PlayerFishEvent.State.IN_GROUND)
                    || e.getState().equals(PlayerFishEvent.State.CAUGHT_ENTITY)
                    || e.getState().equals(PlayerFishEvent.State.FAILED_ATTEMPT)
            ) && p.getItemInHand().equals(CatchItem.CATCH_ROD)
                    && (Bukkit.getWorld(e.getPlayer().getWorld().getName())
                    .getBlockAt(h.getLocation().getBlockX(), h.getLocation().getBlockY() - 1, h.getLocation().getBlockZ())
                    .getType() != Material.AIR)
            ) {
                if (p.getWorld()
                        .getBlockAt(h.getLocation().getBlockX(), h.getLocation().getBlockY() - 1, h.getLocation().getBlockZ())
                        .getType() != Material.STATIONARY_WATER
                ) {
                    Location lc = p.getLocation();
                    Location to = e.getHook().getLocation();

                    lc.setY(lc.getY() + 0.5D);
                    p.teleport(lc);

                    Sound.play(p, org.bukkit.Sound.ENDERDRAGON_WINGS);
                    double g = -0.07D;
                    double t = to.distance(lc);
                    double v_x = (1.0D + 0.07D * t) * (to.getX() - lc.getX()) / t;
                    double v_y = (1.0D + 0.03D * t) * (to.getY() - lc.getY()) / t - 0.5D * g * t;
                    double v_z = (1.0D + 0.07D * t) * (to.getZ() - lc.getZ()) / t;

                    Vector v = p.getVelocity();
                    v.setX(v_x);
                    v.setY(v_y);
                    v.setZ(v_z);
                    p.setVelocity(v);

                    p.getInventory().setItem(2, null);

                    Bukkit.getScheduler().runTaskLater(LobbyPlugin.getInstance(), () -> {
                        if (game.getCatcher().contains(p)) {
                            Sound.tick(p);

                            Bukkit.getScheduler().runTaskLater(LobbyPlugin.getInstance(), () -> {
                                if (game.getCatcher().contains(p)) {
                                    Sound.tick(p);

                                    Bukkit.getScheduler().runTaskLater(LobbyPlugin.getInstance(), () -> {
                                        if (game.getCatcher().contains(p)) {
                                             Sound.done(p);
                                            p.getInventory().setItem(2, CatchItem.CATCH_ROD);
                                        }
                                    }, 52);
                                }
                            }, 40);
                        }
                    }, 35);

                }
            }
        }
    }


    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (game.isPlaying(p)) {
            if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                ItemStack i = e.getItem();
                if ((i == null) || (!i.hasItemMeta()) || (!i.getItemMeta().hasDisplayName())) {
                    return;
                }

                if (i.equals(CatchItem.CATCHER_TRACKER)) {
                    if (!CoreSystem.getInstance().getCooldownSystem().addAndCheck(getClass(), p.getUniqueId()))
                        return;

                    if (game.getPlaying().size() > 1) {
                        Location nearest = getNearestCatchPlayerLocation(p);
                        p.setCompassTarget(nearest);
                        Msg.send(p, "§7Der Fänger ist §f" + ((int) p.getLocation().distance(nearest)) + " Blöcke §7entfernt!");
                    } else {
                        Msg.send(p, "§cDu bist der einzigste der momentan Fangen spielt!");
                    }
                } else if (i.equals(CatchItem.CATCH_RUN_TRACKER)) {
                    if (!CoreSystem.getInstance().getCooldownSystem().addAndCheck(getClass(), p.getUniqueId()))
                        return;

                    if (game.getPlaying().size() > 1) {
                        Location nearest = getNearestCatchPlayerLocation(p);
                        p.setCompassTarget(nearest);
                        Msg.send(p, "§7Der nächste Läufer ist §f" + ((int) p.getLocation().distance(nearest)) + " Blöcke §7entfernt!");
                    }
                } else if (i.equals(CatchItem.LEAVE_CATCH_FIGHTING)) {
                    game.quitGame(p);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();

            if (game.isPlaying(p)) {
                if (e.getDamager() instanceof Player) {
                    Player d = (Player) e.getDamager();

                    if (game.isPlaying(d)) {
                        if (d.getItemInHand().hasItemMeta() && d.getItemInHand().equals(CatchItem.CATCH_STICK)) {
                            p.setLevel(1);
                            p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 39, 4500, false, false));
                            p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 41, 4500, false, false));
                            p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 5, 4500, false, false));
                            game.getCatcher().add(p);
                            game.setCatcherItems(p);
                            LobbyPlugin.getInstance().getLobbyPlayer(p).reloadScoreboardIfEnabled();
                            Msg.send(p, "§7Du wurdest von §c" + d.getName() + "§7 gefangen.");
                            Msg.send(p, "§7Fange nun §fSpieler §7mit einem §fGrünen Hut§7!");
                            p.getLocation().getWorld().playSound(p.getLocation(), org.bukkit.Sound.NOTE_BASS_DRUM, 1.0F, 1.0F);

                            d.setLevel(0);
                            d.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));
                            d.getLocation().getWorld().playSound(d.getLocation(), org.bukkit.Sound.NOTE_PIANO, 1.0F, 1.0F);
                            d.getLocation().getWorld().playSound(d.getLocation(), org.bukkit.Sound.NOTE_PIANO, 1.0F, 1.0F);
                            game.getCatcher().remove(d);
                            game.setRunnerItems(d);
                            LobbyPlugin.getInstance().getLobbyPlayer(d).reloadScoreboardIfEnabled();
                            Msg.send(d, "§7Du hast §c" + p.getName() + "§7 gefangen!");

                            e.setCancelled(true);
                        }
                    }
                }
            }
        }
    }

    private Location getNearestCatchPlayerLocation(Player p) {
        Map<Double, Location> aroundPlayers = new HashMap<>();
        for (Entity ent : p.getNearbyEntities(100D, 25D, 100D)) {
            if (ent instanceof Player) {
                Player near = (Player) ent;
                if (game.getCatcher().contains(ent)) {
                    aroundPlayers.put(p.getLocation().distance(near.getLocation()), near.getLocation());
                }
            }
        }

        return Collections.min(aroundPlayers.entrySet(), Map.Entry.comparingByKey()).getValue();
    }

}

