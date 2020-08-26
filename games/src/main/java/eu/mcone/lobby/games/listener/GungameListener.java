/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.games.listener;

import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.games.pvp.gungame.GunGameLobbyGame;
import eu.mcone.lobby.games.pvp.gungame.GunGameItem;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

@RequiredArgsConstructor
public class GungameListener implements Listener {

    private final GunGameLobbyGame game;

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        Player k = LobbyPlugin.getInstance().getDamageLogger().getKiller(p);

        if (game.isPlaying(p)) {
            e.getDrops().clear();
            e.setDeathMessage(null);
            p.spigot().respawn();

            setDiedLevel(p);
            p.setExp(1);
            p.setVelocity(new Vector(0, 0, 0));

            if (k != null && game.isPlaying(k)) {
                LobbyPlugin.getInstance().getMessenger().send(k, "§7Du hast §f" + p.getName() + " §7getötet §8[§a+2 Coins§8]");

                LobbyPlayer lk = LobbyPlugin.getInstance().getLobbyPlayer(k);
                lk.getCorePlayer().addCoins(2);
                LobbyPlugin.getInstance().getPlayerSounds().playSounds(k, Sound.LEVEL_UP);
                k.setExp(1);
                k.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 50, 3));
                k.setLevel(k.getLevel() + 1);
                game.setFightItems(k);
                LobbyPlugin.getInstance().getLobbyPlayer(k).reloadScoreboardIfEnabled();

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

            if (!game.isInSaveMode(p)) {
                Bukkit.getScheduler().runTask(LobbyPlugin.getInstance(), () -> game.setSaveMode(p));
            }
        }
    }


    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();

        if (game.isPlaying(p)) {
            if (p.getLocation().getBlock().getType().equals(Material.WATER) || p.getLocation().getBlock().getType().equals(Material.STATIONARY_WATER)) {
                if (p.getHealth() > 0) {
                    p.setHealth(0);
                    p.spigot().respawn();
                }
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

            if (i.equals(GunGameItem.LEAVE_GUNGAME_FIGHTING)) {
                game.quitGame(p);
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
                        if (game.isInSaveMode(k)) {
                            LobbyPlugin.getInstance().getMessenger().send(k, "§cDu darfst noch keine Spieler angreifen!");
                            e.setCancelled(true);
                            return;
                        }
                        if (game.isInSaveMode(p)) {
                            LobbyPlugin.getInstance().getMessenger().send(k, "§cDu darfst diesen Spieler noch nicht angreifen!");
                            e.setCancelled(true);
                            return;
                        }
                        e.setCancelled(false);
                    }
                }
            }
        }

    }

    private void setDiedLevel(Player p) {
        if (p.getLevel() != 0) {
            if (p.getLevel() <= 5) {
                p.setLevel(p.getLevel() - 1);
            } else if (p.getLevel() <= 12) {
                p.setLevel(p.getLevel() - 4);
            } else if (p.getLevel() <= 17) {
                p.setLevel(p.getLevel() - 6);
            } else if (p.getLevel() <= 23) {
                p.setLevel(p.getLevel() - 8);
            } else if (p.getLevel() <= 29) {
                p.setLevel(p.getLevel() - 10);
            } else if (p.getLevel() <= 35) {
                p.setLevel(p.getLevel() - 14);
            } else if (p.getLevel() <= 41) {
                p.setLevel(p.getLevel() - 18);
            } else if (p.getLevel() <= 50) {
                p.setLevel(p.getLevel() - 25);
            } else if (p.getLevel() <= 60) {
                p.setLevel(p.getLevel() - 29);
            }
        }
    }

}
