/*
 * Copyright (c) 2017 - 2018 Dominik Lippl, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.util;

import eu.mcone.coresystem.api.bukkit.util.ItemBuilder;
import eu.mcone.lobby.LobbyPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.HashMap;

public class PlayerHider {

    public static ArrayList<Player> players = new ArrayList<>();
    private static HashMap<String, Long> time = new HashMap<>();

    public static void hidePlayers(Player p) {
        if (time.containsKey(p.getName())){
            long diff = (System.currentTimeMillis() - time.get(p.getName())) / 10L / 60L;
            int cooldown = 1;
            if (diff < cooldown){
                p.sendMessage(LobbyPlugin.getInstance().getConf().getConfigValue("System-Prefix") + "§7Du musst kurz warte um den Player hider wieder benutzen zu können");
                return;
            }
        }

        for (Player all : Bukkit.getOnlinePlayers()){
            p.hidePlayer(all);
        }

        players.add(p);

        p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 0));
        p.playSound(p.getLocation(), Sound.LAVA_POP, 1.0F, 1.0F);
        p.playEffect(p.getLocation(), Effect.FIREWORKS_SPARK, 1);
        p.getInventory().setItem(0, new ItemBuilder(Material.INK_SACK, 1, 2).displayName("§3§lSpieler Anzeigen §8» §7§oZeigt alle Spieler wieder an").create());
        p.sendMessage(LobbyPlugin.getInstance().getConf().getConfigValue("System-Prefix") + "§7Du siehst nun §ckeine §7Spieler mehr.");
        time.put(p.getName(), System.currentTimeMillis());
    }

    public static void showPlayers(Player p) {
        for (Player all : Bukkit.getOnlinePlayers()){
            p.showPlayer(all);
        }

        players.remove(p);

        p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 0));
        p.playSound(p.getLocation(), Sound.LAVA_POP, 1.0F, 1.0F);
        p.playEffect(p.getLocation(), Effect.FIREWORKS_SPARK, 1);
        p.getInventory().setItem(0, new ItemBuilder(Material.INK_SACK, 1, 10).displayName("§3§lSpieler Verstecken §8» §7§oBlende alle anderen Spieler aus").create());
        p.sendMessage(LobbyPlugin.getInstance().getConf().getConfigValue("System-Prefix") + "§7Du siehst nun §aalle §7Spieler wieder.");
        time.put(p.getName(), System.currentTimeMillis());
    }
}
