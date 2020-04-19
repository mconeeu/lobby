package eu.mcone.lobby.listener;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.event.BuildModeChangeEvent;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.player.HotbarItems;
import eu.mcone.lobby.inventory.InteractionInventory;
import eu.mcone.lobby.trap.TrapManager;
import lombok.RequiredArgsConstructor;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fish;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.util.Vector;

@RequiredArgsConstructor
public class TrappingListener implements Listener {

    private final TrapManager manager;


    @EventHandler
    public void onFish(PlayerFishEvent e) {
        Player p = e.getPlayer();

        Fish h = e.getHook();

        if (LobbyPlugin.getInstance().getCatchManager().isCatching(p)) {
            if (((e.getState().equals(PlayerFishEvent.State.IN_GROUND)) ||
                    (e.getState().equals(PlayerFishEvent.State.CAUGHT_ENTITY)) ||
                    (e.getState().equals(PlayerFishEvent.State.FAILED_ATTEMPT))) &&
                    (p.getItemInHand().equals(HotbarItems.CATCH_ROD)) &&
                    (Bukkit.getWorld(e.getPlayer().getWorld().getName())
                            .getBlockAt(h.getLocation().getBlockX(), h.getLocation().getBlockY() - 1,
                                    h.getLocation().getBlockZ())
                            .getType() != Material.AIR)) {
                if (Bukkit.getWorld(e.getPlayer().getWorld().getName())
                        .getBlockAt(h.getLocation().getBlockX(), h.getLocation().getBlockY() - 1,
                                h.getLocation().getBlockZ())
                        .getType() != Material.STATIONARY_WATER) {
                    Location lc = p.getLocation();
                    Location to = e.getHook().getLocation();

                    lc.setY(lc.getY() + 0.5D);
                    p.teleport(lc);

                    p.playSound(p.getLocation(), Sound.ENDERDRAGON_WINGS, 3, 3);
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
                        if (manager.getCatcher().contains(p)) {
                            p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
                        }
                        Bukkit.getScheduler().runTaskLater(LobbyPlugin.getInstance(), () -> {
                            if (manager.getCatcher().contains(p)) {
                                p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
                            }
                            Bukkit.getScheduler().runTaskLater(LobbyPlugin.getInstance(), () -> {
                                if (manager.getCatcher().contains(p)) {
                                    p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
                                    p.getInventory().setItem(2, HotbarItems.CATCH_ROD);
                                }
                            }, 52);
                        }, 40);
                    }, 35);

                }
            }
        }
    }


    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInteractEntity(PlayerInteractEntityEvent e) {
        Player p = e.getPlayer();

            if (e.getRightClicked() instanceof Player) {
                Player clicked = (Player) e.getRightClicked();
                if (!manager.isCatching(p)) {
                new InteractionInventory(p, clicked);
            }
        }
    }


    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            ItemStack i = e.getItem();
            if ((i == null) || (!i.hasItemMeta()) || (!i.getItemMeta().hasDisplayName())) {
                return;
            }
            if (manager.isCatching(p)) {
                if (i.equals(HotbarItems.CATCHER_TRACKER)) {
                    for (Entity ent : p.getNearbyEntities(100D, 25D, 100D)) {
                        if (ent instanceof Player) {
                            Player near = (Player) ent;
                            if (manager.getCatcher().contains(ent)) {
                                p.setCompassTarget(near.getLocation());
                                LobbyPlugin.getInstance().getMessenger().send(p, "§7Der Fänger ist §f" + ((int) p.getLocation().distance(near.getLocation())) + " Blöcke §7entfernt!");
                            }
                        }
                    }
                } else if (i.equals(HotbarItems.CATCH_RUN_TRACKER)) {
                    for (Entity ent : p.getNearbyEntities(100D, 25D, 100D)) {
                        if (ent instanceof Player) {
                            Player near = (Player) ent;
                            if (manager.getCatching().size() == 1) {
                                LobbyPlugin.getInstance().getMessenger().send(p, "§cDu bist der einzigste der momentan Fangen spielt!");
                                return;
                            } else if (manager.getCatching().contains(ent)) {
                                p.setCompassTarget(near.getLocation());
                                LobbyPlugin.getInstance().getMessenger().send(p, "§7Der nächste Läufer ist §f" + ((int) p.getLocation().distance(near.getLocation())) + " Blöcke §7entfernt!");
                            }
                        }
                    }
                }

                if (i.equals(HotbarItems.LEAVE_CATCH_FIGHTING)) {
                    manager.leave(p);
                }

            }
        }
    }

    @EventHandler
    public void onLevelChange(PlayerLevelChangeEvent e) {
        Player p = e.getPlayer();
        if (e.getNewLevel() != 0 && (e.getNewLevel() % 3) == 0) {

            for (Player player : Bukkit.getOnlinePlayers()) {
                if (LobbyPlugin.getInstance().getCatchManager().isCatching(player)) {
                    CoreSystem.getInstance().getCorePlayer(p).getScoreboard().getObjective(DisplaySlot.SIDEBAR).reload();
                    player.getLocation().getWorld().playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
                }
            }
        }
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e) {
        Player p = e.getPlayer();

        if (e.getMessage().equalsIgnoreCase("/spawn")) {
            if (manager.isCatching(p)) {
                manager.leave(p);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();

            if (e.getDamager() instanceof Player) {
                Player k = (Player) e.getDamager();

                if (manager.isCatching(p) && manager.isCatching(k)) {
                    if (k.getItemInHand().hasItemMeta() && k.getItemInHand().equals(HotbarItems.CATCH_STICK)) {
                        p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 39, 4500, false, false));
                        p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 41, 4500, false, false));
                        p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 5, 4500, false, false));
                        manager.getCatcher().remove(k);
                        manager.getCatcher().add(p);
                        LobbyPlugin.getInstance().getMessenger().send(p, "§7Du wurdest von §c" + k.getName() + "§7 gefangen.");
                        LobbyPlugin.getInstance().getMessenger().send(p, "§7Fange nun §fSpieler §7mit einem §fGrünen Hut§7!");

                        LobbyPlugin.getInstance().getMessenger().send(k, "§7Du hast §c" + p.getName() + "§7 gefangen!");
                        k.setLevel(0);
                        k.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));
                        k.getLocation().getWorld().playSound(k.getLocation(), Sound.NOTE_PIANO, 1.0F, 1.0F);
                        k.getLocation().getWorld().playSound(k.getLocation(), Sound.NOTE_PIANO, 1.0F, 1.0F);

                        manager.setCatchItems(p);
                        manager.setCatchItems(k);

                        p.setLevel(1);

                        p.getLocation().getWorld().playSound(p.getLocation(), Sound.NOTE_BASS_DRUM, 1.0F, 1.0F);
                        p.removePotionEffect(PotionEffectType.SPEED);
                        p.getActivePotionEffects().clear();

                        CoreSystem.getInstance().getCorePlayer(k).getScoreboard().getObjective(DisplaySlot.SIDEBAR).reload();
                        CoreSystem.getInstance().getCorePlayer(p).getScoreboard().getObjective(DisplaySlot.SIDEBAR).reload();

                        e.setCancelled(false);
                    }
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBuildLeave(BuildModeChangeEvent e) {
        Player p = e.getPlayer();

        if (!e.isCanBuild() && manager.isCatching(p)) {
            p.setGameMode(GameMode.ADVENTURE);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        manager.playerLeaved(e.getPlayer());
    }

}

