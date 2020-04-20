/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.listener;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.event.AfkEvent;
import eu.mcone.coresystem.api.bukkit.event.BuildModeChangeEvent;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.core.player.PlayerState;
import eu.mcone.lobby.Lobby;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.player.HotbarItems;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.onehit.LobbyOneHitManager;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
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
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.util.Vector;

import java.util.ArrayList;

@RequiredArgsConstructor
public class OneHitListener implements Listener {

    private final LobbyOneHitManager manager;
    public static ArrayList<Player> doubleJump = new ArrayList<>();

    @EventHandler
    public void onLevelChange(PlayerLevelChangeEvent e) {
        Player p = e.getPlayer();
        if (e.getNewLevel() != 0 && (e.getNewLevel() % 3) == 0) {

            for (Player player : Bukkit.getOnlinePlayers()) {
                if (LobbyPlugin.getInstance().getOneHitManager().isFighting(player)) {
                    CoreSystem.getInstance().getCorePlayer(p).getScoreboard().getObjective(DisplaySlot.SIDEBAR).reload();
                    player.getLocation().getWorld().playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
                    if (player != p) {
                        LobbyPlugin.getInstance().getMessenger().send(player, "§7Der Spieler §f" + p.getDisplayName() + " §7hat eine §e" + e.getNewLevel() + "§7er Killstreak!");
                    } else {
                        LobbyPlugin.getInstance().getMessenger().send(player, "§7Du hast eine §e" + e.getNewLevel() + "er Killstreak!");
                    }
                }
            }

        }
    }

    @EventHandler
    public void onAFK(AfkEvent e) {
        Player p = e.getPlayer();

        if (LobbyPlugin.getInstance().getOneHitManager().isFighting(p) || LobbyPlugin.getInstance().getJumpNRunManager().isJumping(p) || LobbyPlugin.getInstance().getCatchManager().isCatching(p)) {
            if (e.getState().equals(PlayerState.AFK)) {
                LobbyPlugin.getInstance().getJumpNRunManager().setCancel(p);
                LobbyPlugin.getInstance().getOneHitManager().leave(p);
                LobbyPlugin.getInstance().getCatchManager().leave(p);
                LobbyPlugin.getInstance().getMessenger().send(p, "§4Du wurdest automatisch von deiner Lobby Aktivität gekickt!");
            }
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        Player k = p.getKiller();

        e.setKeepInventory(true);
        p.setVelocity(new Vector(0, 0, 0));

        if (manager.isFighting(p) && manager.isFighting(k)) {
            p.setLevel(0);
            p.setExp(1);

            if (k == null) {
                LobbyPlugin.getInstance().getMessenger().send(p, "§cDu bist gestorben");
            } else {
                LobbyPlugin.getInstance().getMessenger().send(k, "§7Du hast §f" + p.getDisplayName() + " §7getötet §8[§a+2 Coins§8]");
                CoreSystem.getInstance().getCorePlayer(k).getScoreboard().getObjective(DisplaySlot.SIDEBAR).reload();
                CoreSystem.getInstance().getCorePlayer(p).getScoreboard().getObjective(DisplaySlot.SIDEBAR).reload();
                LobbyPlayer lk = LobbyPlugin.getInstance().getLobbyPlayer(k);
                lk.getCorePlayer().addCoins(2);
                k.getInventory().setItem(6, new ItemBuilder(Material.ARROW, 1, 0).displayName("§bOneHit-Pfeil").create());
                k.playSound(k.getLocation(), Sound.LEVEL_UP, 1, 1);
                k.setExp(1);
                p.setExp(1);
                k.setLevel(k.getLevel() + 1);

                LobbyPlugin.getInstance().getMessenger().send(p, "§7Du wurdest von §f" + k.getDisplayName() + " §7getötet!");
            }
        }

        e.setDeathMessage("");
        p.spigot().respawn();
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        if (manager.isFighting(p)) {
            p.setExp(1);
            e.setRespawnLocation(manager.getRandomSpawn());
            Bukkit.getScheduler().runTask(Lobby.getSystem(), () -> manager.setOneHitFightItems(p));
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

            if (i.equals(HotbarItems.LEAVE_ONEHIT_FIGHTING)) {
                manager.leave(p);
            }
        }
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e) {
        Player p = e.getPlayer();

        if (e.getMessage().equalsIgnoreCase("/spawn")) {
            if (manager.isFighting(p)) {
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


                if (manager.isFighting(p) && manager.isFighting(k)) {
                    if (
                            k.getItemInHand().hasItemMeta() && k.getItemInHand().equals(HotbarItems.ONEHIT_SWORD) || k.getItemInHand().hasItemMeta() && k.getItemInHand().equals(HotbarItems.STORY_ONEHIT_SWORD)) {
                        p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 5, 4500, false, false));
                        p.setHealth(0);
                        e.setCancelled(false);
                    }
                }
            } else if (e.getDamager() instanceof Arrow) {
                if (manager.isFighting(p)) {
                    p.setHealth(0);
                    p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 5, 4500, false, false));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 5, 4500, false, false));
                    e.setCancelled(false);
                }
            } else if (e.getDamager() instanceof Snowball) {
                if (manager.isFighting(p)) {
                    p.setHealth(0);
                    p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 5, 4500, false, false));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 5, 4500, false, false));
                    e.setCancelled(false);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBuildLeave(BuildModeChangeEvent e) {
        Player p = e.getPlayer();

        if (!e.isCanBuild() && manager.isFighting(p)) {
            p.setGameMode(GameMode.ADVENTURE);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        manager.playerLeaved(e.getPlayer());
    }

}
