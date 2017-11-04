/*
 * Copyright (c) 2017 Dominik L., Rufus Maiwald, BamDev and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.event;

import eu.mcone.lobby.Main;
import eu.mcone.lobby.util.PlayerHider;
import eu.mcone.lobby.util.AntiLabymod;
import de.Dominik.BukkitCoreSystem.util.LocationFactory;
import eu.mcone.lobby.util.ScoreboardManager;
import de.Dominik.BukkitCoreSystem.util.ItemManager;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;

public class PlayerJoin_Event implements Listener {

    @EventHandler
    public void on(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        e.setJoinMessage(null);

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (PlayerHider.players.contains(p)) {
                p.hidePlayer(player);
            }
        }

/*
        for(Player all : Bukkit.getOnlinePlayers()){
            PlayerHoloListener.HoloSkypvp(all);
            PlayerHoloListener.HoloBedwars(all);
            PlayerHoloListener.HoloKnockbackFFA(all);
            PlayerHoloListener.HoloMinewar(all);
        }
*/

        p.getInventory().clear();
        p.setMaxHealth(20);
        p.setGameMode(GameMode.ADVENTURE);
        p.getInventory().clear();
        p.getInventory().setArmorContents(null);
        p.getActivePotionEffects().clear();

        p.setHealth(20);
        p.setFoodLevel(20);

        AntiLabymod.setLabySettings(p);

        ScoreboardManager.setScoreboard(p, 1);

        p.playEffect(p.getLocation(), org.bukkit.Effect.HAPPY_VILLAGER, 5);
        p.playSound(p.getLocation(), Sound.FIREWORK_TWINKLE, 2.0F, 5.0F);


        Location loc = LocationFactory.getConfigLocation(Main.config, "Location-Spawn");

        if (loc != null) {
            p.teleport(loc);
        } else if (p.hasPermission("group.admin")) {
            p.sendMessage(Main.config.getConfigValue("System-Prefix") + "§4Der Lobby Spawn ist noch nicht gesetzt!");
        }



        p.getInventory().setItem(4, ItemManager.createItem(Material.COMPASS, 0, 1, "§3§lNavigator §8» §7§oWähle einen Spielmodus", true));
        p.getInventory().setItem(0, ItemManager.createItem(Material.INK_SACK, 10, 1, "§3§lSpieler Verstecken §8» §7§oBlende alle anderen Spieler aus", true));
        p.getInventory().setItem(7, ItemManager.createItem(Material.NETHER_STAR, 0, 1, "§3§lStats §8» §7§oStatistiken zu allen Spielmodi", true));
        p.getInventory().setItem(1, ItemManager.createItem(Material.FISHING_ROD, 0, 1, "§3§lZauber-Angel §8» §7§oZiehe dich wohin du willst", true));
        p.getInventory().setItem(8, ItemManager.createSkullItem("§3§lProfil §8» §7§oAussehen / Effekte / Gadgets", p.getName(), 1, new ArrayList<>()));
    }

}
