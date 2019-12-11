/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.listener;

import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.lobby.Lobby;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.player.HotbarItems;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.inventory.InteractionInventory;
import eu.mcone.lobby.onehit.LobbyOneHitManager;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@RequiredArgsConstructor
public class OneHitListener implements Listener {

    private final LobbyOneHitManager manager;

    @EventHandler
    public void onInteractEntity(PlayerInteractEntityEvent e) {
        Player p = e.getPlayer();

        if (!manager.isFighting(p)) {
            if (e.getRightClicked() instanceof Player) {
                Player clicked = (Player) e.getRightClicked();
                new InteractionInventory(p, clicked);
            }
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        Player k = p.getKiller();

        e.setKeepInventory(true);

        if (manager.isFighting(p) && manager.isFighting(k)) {
            if (k == null) {
                LobbyPlugin.getInstance().getMessager().send(p, "§cDu bist gestorben");
            } else {
                LobbyPlugin.getInstance().getMessager().send(k, "§7Du hast §f" + p.getDisplayName() + " §7getötet §8[§6+2 Coins§8]");
                LobbyPlayer lk = LobbyPlugin.getInstance().getGamePlayer(k);
                lk.getCorePlayer().addCoins(2);
                k.getInventory().setItem(7, new ItemBuilder(Material.ARROW, 1, 0).displayName("§bOneHit-Pfeil").create());
                k.getWorld().playSound(k.getLocation(), Sound.LEVEL_UP, 1, 1);

                LobbyPlugin.getInstance().getMessager().send(p, "§7Du wurdest von §f" + k.getDisplayName() + " §7getötet!");

            }
        }

        e.setDeathMessage("");
        p.spigot().respawn();
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        Player p = e.getPlayer();

        if (manager.isFighting(p)) {
            Bukkit.getScheduler().runTask(Lobby.getInstance(), () -> {
                manager.setOneHitFightItems(p);
                manager.teleportToRandomSpawn(p);
            });
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
    public void onCommand(org.bukkit.event.player.PlayerCommandPreprocessEvent e) {
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
            if (e.getDamager() instanceof Player) {
                Player p = (Player) e.getEntity();
                Player k = (Player) e.getDamager();

                if (manager.isFighting(p) && manager.isFighting(k)) {
                    if (e.getDamager() instanceof Arrow) {
                        p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 5, 4500, false, false));
                        p.setHealth(0);
                        e.setCancelled(false);
                    } else if (k.getItemInHand().hasItemMeta() && k.getItemInHand().equals(HotbarItems.ONEHIT_SWORD)) {
                        p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 5, 4500, false, false));
                        p.setHealth(0);
                        e.setCancelled(false);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        manager.playerLeaved(e.getPlayer());
    }

}
