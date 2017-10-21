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
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.HashMap;

public class Hider_Interact {

    public static ArrayList<Player> players = new ArrayList<>();
    private static HashMap<String, Long> zeit = new HashMap<>();
    private static int Cooldown = 0;


    public static void hidePlayers(PlayerInteractEvent e, Player p) {
        if (zeit.containsKey(p.getName())){
            long diff = (System.currentTimeMillis() - (Long) zeit.get(p.getName())) / 10L / 60L;
            Cooldown = (1);
            if (diff < Cooldown){
                p.sendMessage(Main.config.getConfigValue("System-Prefix") + "§7Du musst kurz warte um den Player hider wieder benutzen zu können");
                return;
            }
        }

        if (p.hasPermission("lobby.hide")){

            for (Player all : Bukkit.getOnlinePlayers()){
                p.hidePlayer(all);
                players.add(p);
            }

            p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 0));
            p.playSound(p.getLocation(), Sound.LAVA_POP, 1.0F, 1.0F);
            p.playEffect(p.getLocation(), Effect.FIREWORKS_SPARK, 1);
            p.getInventory().setItem(0, ItemManager.createItem(Material.STICK, 0, 0, "§3§lSpieler Anzeigen §8» §7§oZeigt alle Spieler wieder an"));
            p.sendMessage(Main.config.getConfigValue("System-Prefix") + "§7Du siehst nun §c§lkeine §7Spieler mehr§8.");
            zeit.put(p.getName(), System.currentTimeMillis());

        }else{
            p.sendMessage(Main.config.getConfigValue("System-Prefix") + "§4Du hast keine Berechtigung für diesen Befehl!");
        }
    }

    public static void showPlayers(PlayerInteractEvent e, Player p) {
        if (p.hasPermission("lobby.hide")){

            for (Player all : Bukkit.getOnlinePlayers()){
                p.showPlayer(all);
                players.remove(p);
            }

            p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 0));
            p.playSound(p.getLocation(), Sound.LAVA_POP, 1.0F, 1.0F);
            p.playEffect(p.getLocation(), Effect.FIREWORKS_SPARK, 1);
            p.getInventory().setItem(0, ItemManager.createItem(Material.BLAZE_ROD, 0, 0, "§3§lSpieler Verstecken §8» §7§oBlende alle anderen Spieler aus"));
            p.sendMessage(Main.config.getConfigValue("System-Prefix") + "§7Du siehst nun §a§lalle §7Spieler wieder§8.");
            zeit.put(p.getName(), System.currentTimeMillis());

        }else{
            p.sendMessage(Main.config.getConfigValue("System-Prefix") + "§4Du hast keine Berechtigung für diesen Befehl!");
        }
    }
}
