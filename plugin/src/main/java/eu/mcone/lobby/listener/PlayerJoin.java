/*
 * Copyright (c) 2017 - 2018 Dominik Lippl, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.listener;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.player.BukkitCorePlayer;
import eu.mcone.coresystem.api.bukkit.util.ItemBuilder;
import eu.mcone.lobby.LobbyPlugin;
import eu.mcone.lobby.util.Objective;
import eu.mcone.lobby.util.PlayerHider;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    @EventHandler
    public void on(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        BukkitCorePlayer cp = CoreSystem.getInstance().getCorePlayer(p);

        e.setJoinMessage(null);

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (PlayerHider.players.contains(p)) {
                p.hidePlayer(player);
            }
        }

        p.getInventory().clear();
        p.getInventory().setArmorContents(null);
        p.setGameMode(GameMode.ADVENTURE);
        p.getActivePotionEffects().clear();

        p.setMaxHealth(20);
        p.setHealth(20);
        p.setFoodLevel(20);

        cp.getScoreboard().setNewObjective(new Objective());

        p.playEffect(p.getLocation(), org.bukkit.Effect.HAPPY_VILLAGER, 5);
        p.playSound(p.getLocation(), Sound.FIREWORK_TWINKLE, 2.0F, 5.0F);

        LobbyPlugin.getInstance().getWorld().teleportSilently(p, "spawn");

        setJoinItems(p);
    }

    public static void setJoinItems(Player p) {
        p.getInventory().setItem(0, new ItemBuilder(Material.INK_SACK, 1, 10).displayName("§3§lSpieler Verstecken §8» §7§oBlende alle anderen Spieler aus").create());
        //p.getInventory().setItem(1, ItemFactory.createItem(Material.FISHING_ROD, 0, 1, "§3§lZauber-Angel §8» §7§oZiehe dich wohin du willst", true));
        p.getInventory().setItem(4, new ItemBuilder(Material.COMPASS, 1, 0).displayName("§3§lNavigator §8» §7§oWähle einen Spielmodus").create());
        //p.getInventory().setItem(7, new ItemBuilder(Material.FIREWORK, 1, 0).displayName("§3§lGadgets §8» §7§oTrails / Boots / Gadgets").create());
        p.getInventory().setItem(8, ItemBuilder.createSkullItem(p.getName(), 1).displayName("§3§lProfil §8» §7§oEinstellungen / Stats / Freunde").create());
    }

}
