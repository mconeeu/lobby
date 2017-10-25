/*
 * Copyright (c) 2017 Dominik L., BamDev, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.hologram;

import eu.mcone.lobby.Main;
import eu.mcone.lobby.utils.LocationFactory;
import net.minecraft.server.v1_8_R3.WorldServer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.entity.Player;

import static de.Dominik.BukkitCoreSystem.Main.Main.*;

public class PlayerHoloListener {

    public static void HoloSkypvp(final Player p) {
        WorldServer world = ((CraftWorld) Bukkit.getWorld("world")).getHandle();
        double x = LocationFactory.cfg.getDouble("Holo.skypvp" + ".X");
        double y = LocationFactory.cfg.getDouble("Holo.skypvp" + ".y");
        double z = LocationFactory.cfg.getDouble("Holo.skypvp" + ".Z");
        final Location loc = new Location(Bukkit.getWorld(Main.config.getConfigValue("System-WorldName")), x, y, z);

        final String[] Text = { "§7» §9§lSkyPvP",
                "§7Stats von §f" + p.getName(),
                "§r",
                "§7Platz: §f" + statsSkypvp.getUserRanking(p.getName()),
                "§7Kills: §f" + statsSkypvp.getKills(p.getUniqueId().toString(), p.getName()),
                "§7Tode: §f" + statsSkypvp.getDeaths(p.getUniqueId().toString(), p.getName())};
        final PlayerHolograms holo = new PlayerHolograms(Text, loc);
        holo.showPlayer(p);
    }

    public static void HoloMinewar(final Player p) {
        WorldServer world = ((CraftWorld) Bukkit.getWorld("world")).getHandle();
        double x = LocationFactory.cfg.getDouble("Holo.minewar" + ".X");
        double y = LocationFactory.cfg.getDouble("Holo.minewar" + ".y");
        double z = LocationFactory.cfg.getDouble("Holo.minewar" + ".Z");
        final Location loc = new Location(Bukkit.getWorld(Main.config.getConfigValue("System-WorldName")), x, y, z);

        final String[] Text = { "§7» §2§lMinewar",
                "§7Stats von §f" + p.getName(),
                "§r",
                "§7Platz: §f" + statsMinewar.getUserRanking(p.getName()),
                "§7Kills: §f" + statsMinewar.getKills(p.getUniqueId().toString(), p.getName()),
                "§7Tode: §f" + statsMinewar.getDeaths(p.getUniqueId().toString(), p.getName()),
                "§r",
                "§7Wins: §f" + statsMinewar.getWins(p.getUniqueId().toString(), p.getName()),
                "§7Lose: §f" + statsMinewar.getLoses(p.getUniqueId().toString(), p.getName())};
        final PlayerHolograms holo = new PlayerHolograms(Text, loc);
        holo.showPlayer(p);
    }

    public static void HoloBedwars(final Player p) {
        WorldServer world = ((CraftWorld) Bukkit.getWorld("world")).getHandle();
        double x = LocationFactory.cfg.getDouble("Holo.bedwars" + ".X");
        double y = LocationFactory.cfg.getDouble("Holo.bedwars" + ".y");
        double z = LocationFactory.cfg.getDouble("Holo.bedwars" + ".Z");
        final Location loc = new Location(Bukkit.getWorld(Main.config.getConfigValue("System-WorldName")), x, y, z);

        final String[] Text = { "§7» §c§lBedwars",
                "§7Stats von §f" + p.getName(),
                "§r",
                "§7Platz: §f" + statsBedwars.getUserRanking(p.getName()),
                "§7Kills: §f" + statsBedwars.getKills(p.getUniqueId().toString(), p.getName()),
                "§7Tode: §f" + statsBedwars.getDeaths(p.getUniqueId().toString(), p.getName()),
                "§r",
                "§7Wins: §f" + statsBedwars.getWins(p.getUniqueId().toString(), p.getName()),
                "§7Lose: §f" + statsBedwars.getLoses(p.getUniqueId().toString(), p.getName())};
        final PlayerHolograms holo = new PlayerHolograms(Text, loc);
        holo.showPlayer(p);
    }

    public static void HoloKnockbackFFA(final Player p) {
        WorldServer world = ((CraftWorld) Bukkit.getWorld("world")).getHandle();
        double x = LocationFactory.cfg.getDouble("Holo.knockbackffa" + ".X");
        double y = LocationFactory.cfg.getDouble("Holo.knockbackffa" + ".y");
        double z = LocationFactory.cfg.getDouble("Holo.knockbackffa" + ".Z");
        final Location loc = new Location(Bukkit.getWorld(Main.config.getConfigValue("System-WorldName")), x, y, z);

        final String[] Text = { "§7» §e§lKnockFFA",
                "§7Stats von §f" + p.getName(),
                "§r",
                "§7Platz: §f" + statsKnockbackffa.getUserRanking(p.getName()),
                "§7Kills: §f" + statsKnockbackffa.getKills(p.getUniqueId().toString(), p.getName()),
                "§7Tode: §f" + statsKnockbackffa.getDeaths(p.getUniqueId().toString(), p.getName())};
        final PlayerHolograms holo = new PlayerHolograms(Text, loc);
        holo.showPlayer(p);
    }


}
