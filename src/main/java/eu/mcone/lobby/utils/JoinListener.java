/*
 * Copyright (c) 2017 Dominik L., BamDev, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.utils;

import eu.mcone.lobby.block.AntiLabymod;
import eu.mcone.lobby.hologram.PlayerHoloListener;
import eu.mcone.lobby.inventar.InteractListener;
import eu.mcone.lobby.scoreboard.ScoreboardManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        e.setJoinMessage(null);
        Player p = e.getPlayer();

        for(Player all : Bukkit.getOnlinePlayers()){
            //PlayerHoloListener.HoloSkypvp(all);
            //PlayerHoloListener.HoloBedwars(all);
            //PlayerHoloListener.HoloKnockbackFFA(all);
            //PlayerHoloListener.HoloMinewar(all);
        }

        AntiLabymod.setLabySettings(p);
        p.getInventory().clear();
        p.setMaxHealth(20);
        p.setGameMode(GameMode.ADVENTURE);
        p.playEffect(p.getLocation(), org.bukkit.Effect.HAPPY_VILLAGER, 5);
        p.playSound(p.getLocation(), Sound.FIREWORK_TWINKLE, 2.0F, 5.0F);
        p.getInventory().clear();
        p.getInventory().setArmorContents(null);
        p.getActivePotionEffects().clear();
        p.setHealth(20);
        p.setFoodLevel(20);

        p.getInventory().clear();
        p.getInventory().setArmorContents(null);

        if (p.hasPermission("lobby.higherrank")) {
            p.getInventory().setItem(4, ItemManager.createItem(Material.COMPASS, 0, 0, "§3§lNavigator §8» §7§oWÃ¤hle einen Spielmodus"));
            p.getInventory().setItem(0, ItemManager.createItem(Material.BLAZE_ROD, 0, 0, "§3§lSpieler Verstecken §8» §7§oBlende alle anderen Spieler aus"));
            p.getInventory().setItem(7, ItemManager.createItem(Material.NETHER_STAR, 0, 0, "§3§lStats §8» §7§oStatistiken zu allen Spielmodi"));
            //p.getInventory().setItem(1, ItemManager.createItem(Material.FISHING_ROD, 0, 0, "§3§lZauber-Angel §8» §7§oZiehe dich wohin du willst"));
            p.getInventory().setItem(8, ItemManager.createItem(Material.CHEST, 0, 0, "§3§lProfil §8» §7§oAussehen / Effekte / Gadgets"));
        }else{
            p.getInventory().setItem(4, ItemManager.createItem(Material.COMPASS, 0, 0, "§3§lNavigator §8» §7§oWÃ¤hle einen Spielmodus"));
            p.getInventory().setItem(0, ItemManager.createItem(Material.BLAZE_ROD, 0, 0, "§3§lSpieler Verstecken §8» §7§oBlende alle anderen Spieler aus"));
            p.getInventory().setItem(7, ItemManager.createItem(Material.NETHER_STAR, 0, 0, "§3§lStats §8» §7§oStatistiken zu allen Spielmodi"));
            //p.getInventory().setItem(1, ItemManager.createItem(Material.FISHING_ROD, 0, 0, "§3§lZauber-Angel §8» §7§oZiehe dich wohin du willst"));
            p.getInventory().setItem(8, ItemManager.createItem(Material.CHEST, 0, 0, "§3§lProfil §8» §7§oAussehen / Effekte / Gadgets"));
        }
    }

    @EventHandler
    public void onJoin1(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        e.setJoinMessage(null);

        for (Player all : Bukkit.getOnlinePlayers()){
            ScoreboardManager.setScoreboard(all);

            if (InteractListener.players.contains(all)) {
                p.hidePlayer(all);
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        e.setQuitMessage(null);
        for (Player all : Bukkit.getOnlinePlayers()){
            ScoreboardManager.setScoreboard(all);
            PlayerHoloListener.HoloSkypvp(all);
        }
    }
}
