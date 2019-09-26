/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.items.listener.effects;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.core.util.Random;
import eu.mcone.gamesystem.api.enums.Item;
import eu.mcone.gamesystem.api.game.player.GamePlayer;
import eu.mcone.lobby.api.LobbyPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class CoinBombListener implements Listener {

    @EventHandler
    public void on(PlayerInteractEvent e) {
        if (e.hasItem() && e.getItem().equals(Item.COINBOMB.getItemStack()) && (e.getAction().equals(Action.LEFT_CLICK_BLOCK) || e.getAction().equals(Action.LEFT_CLICK_AIR))) {
            Player p = e.getPlayer();
            GamePlayer lp = LobbyPlugin.getInstance().getGamePlayer(p.getUniqueId());

            if (Bukkit.getOnlinePlayers().size() != 1) {
                p.sendMessage("§aDu hast die Coin Bombe erfolgreich gezündet!");
                lp.removeItem(Item.COINBOMB);
                p.getInventory().remove(p.getItemInHand());
                for (Player players : Bukkit.getOnlinePlayers()) {


                    Bukkit.getScheduler().runTaskLaterAsynchronously(LobbyPlugin.getInstance(), () ->
                            players.sendMessage("§8[§7§l!§8] §fServer §8» §aEine Coin Bombe wurde von §e" + p.getName() + " §agezündet sie startet in §e3 Sekunden"), 20L);
                    players.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 80, 7));

                    Bukkit.getScheduler().runTaskLaterAsynchronously(LobbyPlugin.getInstance(), () ->
                            players.sendMessage("§8[§7§l!§8] §fServer §8» §aEine Coin Bombe wurde von §e" + p.getName() + " §agezündet sie startet in §e2 Sekunden"), 40L);

                    Bukkit.getScheduler().runTaskLaterAsynchronously(LobbyPlugin.getInstance(), () ->
                            players.sendMessage("§8[§7§l!§8] §fServer §8» §aEine Coin Bombe wurde von §e" + p.getName() + " §agezündet sie startet in §41 Sekunden"), 60L);

                    Bukkit.getScheduler().runTaskLaterAsynchronously(LobbyPlugin.getInstance(), () -> {

                        Vector v = new Vector(players.getLocation().getX(), players.getLocation().getY(), players.getLocation().getZ());
                        v.normalize();
                        v.setY(0.9D);
                        v.multiply(1.5D);

                        players.setVelocity(v);
                        players.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 1);
                        players.playEffect(p.getLocation(), Effect.LAVA_POP, 1);
                        players.playEffect(p.getLocation(), Effect.LAVA_POP, 1);
                        players.playEffect(p.getLocation(), Effect.LAVA_POP, 1);
                        players.playEffect(p.getLocation(), Effect.LAVA_POP, 2);


                        int random = Random.randomInt(2300, 8000);
                        int coinbomstartbrandom = Random.randomInt(5500, 12000);

                        if (!players.getName().equalsIgnoreCase(p.getName())) {
                            players.sendMessage("§8[§7§l!§8] §fServer §8» §fDie Coin Bombe ist §lexplodiert§f du bekommst §e§l" + random + " Coins!");
                            CoreSystem.getInstance().getCorePlayer(players).addCoins(random);
                        } else {
                            p.sendMessage("§8[§7§l!§8] §fServer §8» §fDie Coin Bombe ist §lexplodiert§f du bekommst §e§l" + coinbomstartbrandom + " Coins!");
                            CoreSystem.getInstance().getCorePlayer(p).addCoins(coinbomstartbrandom);
                        }

                        lp.removeItem(Item.COINBOMB);


                    }, 80L);
                }
            } else {
                LobbyPlugin.getInstance().getMessager().send(p, "§4Es müssen mindestens 2 Spieler Online sein!");
            }
        }
    }

}
