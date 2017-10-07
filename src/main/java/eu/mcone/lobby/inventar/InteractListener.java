/*
 * Copyright (c) 2017 Dominik L., BamDev, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.inventar;

import eu.mcone.lobby.Main;
import eu.mcone.lobby.utils.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.HashMap;

public class InteractListener implements Listener{

    public static ArrayList<Player> players = new ArrayList();
    HashMap<String, Long> zeit = new HashMap();
    int Cooldown = 0;


    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        Player p = e.getPlayer();
        if (this.zeit.containsKey(p.getName())){
            long diff = (System.currentTimeMillis() - ((Long)this.zeit.get(p.getName())).longValue()) / 10L / 60L;
            this.Cooldown = (1);
            if (diff < this.Cooldown){
                p.sendMessage(Main.prefix + "§7Du musst kurz warte um denn Player hieder wieder benutzen zu können");
                return;
            }
        }

        try{
            if ((e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) || (e.getAction().equals(Action.RIGHT_CLICK_AIR))){
                if (p.getItemInHand().getItemMeta().getDisplayName().equals("§3§lSpieler Verstecken §8» §7§oBlende alle anderen Spieler aus")){
                    if (p.hasPermission("lobby.hide")){

                        for (Player all : Bukkit.getOnlinePlayers()){
                            p.hidePlayer(all);
                            players.add(p);
                        }

                        p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 0));
                        p.playSound(p.getLocation(), Sound.LAVA_POP, 1.0F, 1.0F);
                        p.playEffect(p.getLocation(), Effect.FIREWORKS_SPARK, 1);
                        p.getInventory().setItem(0, ItemManager.createItem(Material.STICK, 0, 0, "§3§lSpieler Anzeigen §8» §7§oZeigt alle Spieler wieder an"));
                        p.sendMessage(Main.prefix + "§7Du siehst nun §c§lkeine §7Spieler mehr§8.");
                        this.zeit.put(p.getName(), Long.valueOf(System.currentTimeMillis()));

                    }else{
                        p.sendMessage(Main.noperm);
                    }

                }else if (p.getItemInHand().getItemMeta().getDisplayName().equals("§3§lSpieler Anzeigen §8» §7§oZeigt alle Spieler wieder an")){
                    if (p.hasPermission("lobby.hide")){

                        for (Player all : Bukkit.getOnlinePlayers()){
                            p.showPlayer(all);
                            players.remove(p);
                        }

                        p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 0));
                        p.playSound(p.getLocation(), Sound.LAVA_POP, 1.0F, 1.0F);
                        p.playEffect(p.getLocation(), Effect.FIREWORKS_SPARK, 1);
                        p.getInventory().setItem(0, ItemManager.createItem(Material.BLAZE_ROD, 0, 0, "§3§lSpieler Verstecken §8» §7§oBlende alle anderen Spieler aus"));
                        p.sendMessage(Main.prefix + "§7Du siehst nun §a§lalle §7Spieler wieder§8.");
                        this.zeit.put(p.getName(), Long.valueOf(System.currentTimeMillis()));

                    }else{
                        p.sendMessage(Main.noperm);
                    }
                }
            }
            if (p.getItemInHand().getItemMeta().getDisplayName().equals("§3§lStats §8» §7§oStatistiken zu allen Spielmodi")){
                p.performCommand("stats");
            }
        }
        catch (Exception e1) {
            e1.getMessage();
        }
    }
}
