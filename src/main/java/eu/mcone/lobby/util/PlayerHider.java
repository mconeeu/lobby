/*
 * Copyright (c) 2017 Dominik L., Rufus Maiwald, BamDev and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.util;

import eu.mcone.lobby.Main;
import de.Dominik.BukkitCoreSystem.util.ItemManager;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.HashMap;

public class PlayerHider {

    public static ArrayList<Player> players = new ArrayList<>();
    private static HashMap<String, Long> zeit = new HashMap<>();
    private static int Cooldown = 0;

    public static void hidePlayers(Player p) {
        if (zeit.containsKey(p.getName())){
            long diff = (System.currentTimeMillis() - (Long) zeit.get(p.getName())) / 10L / 60L;
            Cooldown = (1);
            if (diff < Cooldown){
                p.sendMessage(Main.config.getConfigValue("System-Prefix") + "§7Du musst kurz warte um den Player hider wieder benutzen zu können");
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
        p.getInventory().setItem(0, ItemManager.createItem(Material.INK_SACK, 2, 1, "§3§lSpieler Anzeigen §8» §7§oZeigt alle Spieler wieder an", true));
        p.sendMessage(Main.config.getConfigValue("System-Prefix") + "§7Du siehst nun §ckeine §7Spieler mehr.");
        zeit.put(p.getName(), System.currentTimeMillis());
    }

    public static void showPlayers(Player p) {
        for (Player all : Bukkit.getOnlinePlayers()){
            p.showPlayer(all);
        }

        players.remove(p);

        p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 0));
        p.playSound(p.getLocation(), Sound.LAVA_POP, 1.0F, 1.0F);
        p.playEffect(p.getLocation(), Effect.FIREWORKS_SPARK, 1);
        p.getInventory().setItem(0, ItemManager.createItem(Material.INK_SACK, 10, 1, "§3§lSpieler Verstecken §8» §7§oBlende alle anderen Spieler aus", true));
        p.sendMessage(Main.config.getConfigValue("System-Prefix") + "§7Du siehst nun §aalle §7Spieler wieder.");
        zeit.put(p.getName(), System.currentTimeMillis());
    }
}
