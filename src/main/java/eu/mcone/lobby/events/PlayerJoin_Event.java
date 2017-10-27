/*
 * Copyright (c) 2017 Dominik L., Rufus Maiwald, BamDev and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.events;

import eu.mcone.lobby.Main;
import eu.mcone.lobby.inventar.Hider_Interact;
import eu.mcone.lobby.utils.AntiLabymod;
import eu.mcone.lobby.scoreboard.ScoreboardManager;
import eu.mcone.lobby.utils.ItemManager;
import org.bukkit.*;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;

public class PlayerJoin_Event implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        e.setJoinMessage(null);

        for (Player player : Bukkit.getOnlinePlayers()) {
            ScoreboardManager.setScoreboard(player);

            if (Hider_Interact.players.contains(p)) {
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

        File file = new File("plugins//MCONE-Lobby//spawns.yml");
        if (file.exists() && !file.isDirectory()) {
            YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
            String w = cfg.getString("Spawn.WeltName");
            double x = cfg.getDouble("Spawn.X");
            double y = cfg.getDouble("Spawn.Y");
            double z = cfg.getDouble("Spawn.Z");
            double yaw = cfg.getDouble("Spawn.Yaw");
            double pitch = cfg.getDouble("Spawn.Pitch");
            Location loc = new Location(Bukkit.getWorld(w), x, y, z);
            loc.setYaw((float) yaw);
            loc.setPitch((float) pitch);

            p.teleport(loc);
        } else {
            if (p.hasPermission("group.admin") || p.hasPermission("group.developer")) {
                p.sendMessage(Main.config.getConfigValue("System-Prefix") + "§4Der Lobby Spawn ist noch nicht gesetzt!");
            }
        }

        p.getInventory().clear();
        p.getInventory().setArmorContents(null);

        p.getInventory().setItem(4, ItemManager.createItem(Material.COMPASS, 0, 0, "§3§lNavigator §8» §7§oWähle einen Spielmodus"));
        p.getInventory().setItem(0, ItemManager.createItem(Material.INK_SACK, 10, 0, "§3§lSpieler Verstecken §8» §7§oBlende alle anderen Spieler aus"));
        p.getInventory().setItem(7, ItemManager.createItem(Material.NETHER_STAR, 0, 0, "§3§lStats §8» §7§oStatistiken zu allen Spielmodi"));
        p.getInventory().setItem(1, ItemManager.createItem(Material.FISHING_ROD, 0, 0, "§3§lZauber-Angel §8» §7§oZiehe dich wohin du willst"));
        p.getInventory().setItem(8, ItemManager.createSkullItem("§3§lProfil §8» §7§oAussehen / Effekte / Gadgets", p.getName(), new String[]{""}));
    }

}
