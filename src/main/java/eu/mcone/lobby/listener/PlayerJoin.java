/*
 * Copyright (c) 2017 Dominik L., Rufus Maiwald, BamDev and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.listener;

import eu.mcone.bukkitcoresystem.CoreSystem;
import eu.mcone.bukkitcoresystem.player.CorePlayer;
import eu.mcone.bukkitcoresystem.util.ItemFactory;
import eu.mcone.bukkitcoresystem.util.LocationFactory;
import eu.mcone.lobby.Lobby;
import eu.mcone.lobby.util.Objective;
import eu.mcone.lobby.util.PlayerHider;
import eu.mcone.lobby.util.AntiLabymod;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;

public class PlayerJoin implements Listener {

    @EventHandler
    public void on(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        CorePlayer cp = CoreSystem.getCorePlayer(p);

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

        AntiLabymod.setLabySettings(p);
        cp.getScoreboard().setNewObjective(new Objective(cp));
        Lobby.getInstance().getHologramAPI().setHolograms(p);
        Lobby.getInstance().getTrailManager().loadAllowedTrails(p.getUniqueId());

        p.playEffect(p.getLocation(), org.bukkit.Effect.HAPPY_VILLAGER, 5);
        p.playSound(p.getLocation(), Sound.FIREWORK_TWINKLE, 2.0F, 5.0F);

        Location loc = LocationFactory.getConfigLocation(Lobby.config, "Location-Spawn");

        if (loc != null) {
            p.teleport(loc);
        } else if (p.hasPermission("group.admin")) {
            p.sendMessage(Lobby.config.getConfigValue("System-Prefix") + "§4Der Lobby Spawn ist noch nicht gesetzt!");
        }

        setJoinItems(p);
    }

    public static void setJoinItems(Player p) {
        p.getInventory().setItem(0, ItemFactory.createItem(Material.INK_SACK, 10, 1, "§3§lSpieler Verstecken §8» §7§oBlende alle anderen Spieler aus", true));
        p.getInventory().setItem(1, ItemFactory.createItem(Material.FISHING_ROD, 0, 1, "§3§lZauber-Angel §8» §7§oZiehe dich wohin du willst", true));
        p.getInventory().setItem(4, ItemFactory.createItem(Material.COMPASS, 0, 1, "§3§lNavigator §8» §7§oWähle einen Spielmodus", true));
        p.getInventory().setItem(7, ItemFactory.createItem(Material.FIREWORK, 0, 1, "§3§lGadgets §8» §7§oTrails / Boots / Gadgets", true));
        p.getInventory().setItem(8, ItemFactory.createSkullItem("§3§lProfil §8» §7§oEinstellungen / Stats / Freunde", p.getName(), 1, new ArrayList<>()));
    }

}
