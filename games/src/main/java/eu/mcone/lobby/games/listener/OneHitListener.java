/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.games.listener;

import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.games.inventory.OneHitGadgetInventory;
import eu.mcone.lobby.games.pvp.onehit.OneHitLobbyGame;
import eu.mcone.lobby.games.pvp.onehit.OneHitItem;
import fr.neatmonster.nocheatplus.checks.CheckType;
import fr.neatmonster.nocheatplus.hooks.NCPExemptionManager;
import lombok.RequiredArgsConstructor;
import org.bukkit.*;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class OneHitListener implements Listener {

    private static final List<UUID> DJ_PLAYERS = new ArrayList<>();
    private final OneHitLobbyGame game;

    @EventHandler
    public void onLevelChange(PlayerLevelChangeEvent e) {
        Player p = e.getPlayer();

        if (game.isPlaying(p)) {
            if (e.getNewLevel() != 0 && (e.getNewLevel() % 3) == 0) {
                for (Player player : game.getPlaying()) {
                    LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);
                    lp.reloadScoreboardIfEnabled();

                    player.getLocation().getWorld().playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
                    if (player != p) {
                        LobbyPlugin.getInstance().getMessenger().send(player, "§7Der Spieler §f" + p.getName() + " §7hat eine §e" + e.getNewLevel() + "§7er Killstreak!");
                    } else {
                        LobbyPlugin.getInstance().getMessenger().send(player, "§7Du hast eine §e" + e.getNewLevel() + "er Killstreak!");
                    }
                }
            }
        }
    }

    @EventHandler
    public void onToggleFlight(PlayerToggleFlightEvent e) {
        Player p = e.getPlayer();

        if (p.getGameMode().equals(GameMode.SURVIVAL)) {
            e.setCancelled(true);
            return;
        }


        if (game.isPlaying(p)) {
            e.setCancelled(true);
            DJ_PLAYERS.add(p.getUniqueId());

            if (Bukkit.getPluginManager().getPlugin("NoCheatPlus") != null) {
                NCPExemptionManager.exemptPermanently(p, CheckType.MOVING_SURVIVALFLY);
                Bukkit.getScheduler().runTaskLaterAsynchronously(LobbyPlugin.getInstance(), () -> NCPExemptionManager.unexempt(p), 3 * 20);
            }

            p.setAllowFlight(false);
            p.setFlying(false);

            Vector vec = p.getLocation().getDirection().normalize();
            vec = vec.setY(Math.max(0.4000000059604645D, vec.getY())).multiply(1.5F);
            p.setVelocity(vec);

            LobbyPlugin.getInstance().getPlayerSounds().playSounds(p, Sound.ENDERMAN_TELEPORT);
            p.playEffect(p.getLocation(), Effect.BLAZE_SHOOT, 10);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();

        if (p.getGameMode().equals(GameMode.SURVIVAL)) {
            return;
        }

        if (game.isPlaying(p) && DJ_PLAYERS.contains(p.getUniqueId()) && !p.getLocation().add(0, -1, 0).getBlock().getType().equals(Material.AIR)) {
            p.setFlying(false);

            if (Bukkit.getPluginManager().getPlugin("NoCheatPlus") != null)
                NCPExemptionManager.unexempt(p);
            DJ_PLAYERS.remove(p.getUniqueId());
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        Player k = p.getKiller();

        if (game.isPlaying(p)) {
            e.getDrops().clear();
            e.setDeathMessage(null);
            p.spigot().respawn();

            LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);
            lp.reloadScoreboardIfEnabled();
            p.setGameMode(GameMode.ADVENTURE);
            p.setLevel(0);
            p.setExp(1);
            p.setVelocity(new Vector(0, 0, 0));

            if (k != null && game.isPlaying(k)) {
                LobbyPlugin.getInstance().getMessenger().send(k, "§7Du hast §f" + p.getName() + " §7getötet §8[§a+2 Coins§8]");

                LobbyPlayer lpk = LobbyPlugin.getInstance().getLobbyPlayer(k);
                lpk.reloadScoreboardIfEnabled();
                lpk.getCorePlayer().addCoins(2);
                LobbyPlugin.getInstance().getPlayerSounds().playSounds(k, Sound.LEVEL_UP);
                k.getInventory().setItem(6, OneHitItem.ONEHIT_ARROW);
                k.setLevel(k.getLevel() + 1);
                k.setExp(1);

                LobbyPlugin.getInstance().getMessenger().send(p, "§7Du wurdest von §f" + k.getName() + " §7getötet!");
            } else {
                LobbyPlugin.getInstance().getMessenger().send(p, "§cDu bist gestorben");
            }
        }
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        Player p = e.getPlayer();

        if (game.isPlaying(p)) {
            e.setRespawnLocation(game.getRandomSpawn());
            Bukkit.getScheduler().runTask(LobbyPlugin.getInstance(), () -> game.setOneHitFightItems(p));
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

                if (i.equals(OneHitItem.LEAVE_ONEHIT_FIGHTING)) {
                    game.quitGame(p);
                } else if (i.equals(OneHitItem.ONEHIT_GADGET)) {
                    new OneHitGadgetInventory(p, game);
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
                    Player k = (Player) e.getDamager();

                    if (game.isPlaying(k)) {
                        if (
                                k.getItemInHand().hasItemMeta()
                                        && (k.getItemInHand().equals(OneHitItem.ONEHIT_SWORD) || k.getItemInHand().equals(OneHitItem.STORY_ONEHIT_SWORD))
                        ) {
                            p.setHealth(0);
                            p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 5, 4500, false, false));
                            e.setCancelled(false);
                        }
                    }
                } else if (e.getDamager() instanceof Arrow) {
                    ProjectileSource shooter = ((Arrow) e.getDamager()).getShooter();

                    if (shooter instanceof Player && !shooter.equals(p) && game.isPlaying((Player) shooter)) {
                        p.setHealth(0);
                        p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 5, 4500, false, false));
                        p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 5, 4500, false, false));
                        e.setCancelled(false);
                    }
                } else if (e.getDamager() instanceof Snowball) {
                    p.setHealth(0);
                    p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 5, 4500, false, false));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 5, 4500, false, false));
                    e.setCancelled(false);
                }
            }
        }
    }

}
