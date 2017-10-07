/*
 * Copyright (c) 2017 Dominik L., BamDev, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.hologram;

import eu.mcone.lobby.Main;
import eu.mcone.lobby.utils.Factory;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class PlayerHoloListener {

    public static void HoloSkypvp(final Player p) {
        WorldServer world = ((CraftWorld) Bukkit.getWorld("world")).getHandle();
        double x = Factory.cfg.getDouble("Holo.skypvp" + ".X");
        double y = Factory.cfg.getDouble("Holo.skypvp" + ".y");
        double z = Factory.cfg.getDouble("Holo.skypvp" + ".Z");
        final Location loc = new Location(Bukkit.getWorld(Main.cfg.getString("Lobby-WorldName")), x, y, z);

        final String[] Text = { "§7» §9§lSkyPvP",
                "§7Stats von §f" + p.getName(),
                "§r",
                "§7Platz: §f" + StatsSkypvp.getUserRanking(p.getName()),
                "§7Kills: §f" + StatsSkypvp.getKills(p.getUniqueId().toString(), p.getName()),
                "§7Tode: §f" + StatsSkypvp.getDeaths(p.getUniqueId().toString(), p.getName())};
        final PlayerHolograms holo = new PlayerHolograms(Text, loc);
        holo.showPlayer(p);
    }

    public static void HoloMinewar(final Player p) {
        WorldServer world = ((CraftWorld) Bukkit.getWorld("world")).getHandle();
        double x = Factory.cfg.getDouble("Holo.minewar" + ".X");
        double y = Factory.cfg.getDouble("Holo.minewar" + ".y");
        double z = Factory.cfg.getDouble("Holo.minewar" + ".Z");
        final Location loc = new Location(Bukkit.getWorld(Main.cfg.getString("Lobby-WorldName")), x, y, z);

        final String[] Text = { "§7» §2§lMinewar",
                "§7Stats von §f" + p.getName(),
                "§r",
                "§7Platz: §f" + StatsMinewar.getUserRanking(p.getName()),
                "§7Kills: §f" + StatsMinewar.getKills(p.getUniqueId().toString(), p.getName()),
                "§7Tode: §f" + StatsMinewar.getDeaths(p.getUniqueId().toString(), p.getName()),
                "§r",
                "§7Wins: §f" + StatsMinewar.getWins(p.getUniqueId().toString(), p.getName()),
                "§7Lose: §f" + StatsMinewar.getLoses(p.getUniqueId().toString(), p.getName())};
        final PlayerHolograms holo = new PlayerHolograms(Text, loc);
        holo.showPlayer(p);
    }

    public static void HoloBedwars(final Player p) {
        WorldServer world = ((CraftWorld) Bukkit.getWorld("world")).getHandle();
        double x = Factory.cfg.getDouble("Holo.bedwars" + ".X");
        double y = Factory.cfg.getDouble("Holo.bedwars" + ".y");
        double z = Factory.cfg.getDouble("Holo.bedwars" + ".Z");
        final Location loc = new Location(Bukkit.getWorld(Main.cfg.getString("Lobby-WorldName")), x, y, z);

        final String[] Text = { "§7» §c§lBedwars",
                "§7Stats von §f" + p.getName(),
                "§r",
                "§7Platz: §f" + StatsBedwars.getUserRanking(p.getName()),
                "§7Kills: §f" + StatsBedwars.getKills(p.getUniqueId().toString(), p.getName()),
                "§7Tode: §f" + StatsBedwars.getDeaths(p.getUniqueId().toString(), p.getName()),
                "§r",
                "§7Wins: §f" + StatsBedwars.getWins(p.getUniqueId().toString(), p.getName()),
                "§7Lose: §f" + StatsBedwars.getLoses(p.getUniqueId().toString(), p.getName())};
        final PlayerHolograms holo = new PlayerHolograms(Text, loc);
        holo.showPlayer(p);
    }

    public static void HoloKnockbackFFA(final Player p) {
        WorldServer world = ((CraftWorld) Bukkit.getWorld("world")).getHandle();
        double x = Factory.cfg.getDouble("Holo.knockbackffa" + ".X");
        double y = Factory.cfg.getDouble("Holo.knockbackffa" + ".y");
        double z = Factory.cfg.getDouble("Holo.knockbackffa" + ".Z");
        final Location loc = new Location(Bukkit.getWorld(Main.cfg.getString("Lobby-WorldName")), x, y, z);

        final String[] Text = { "§7» §e§lKnockFFA",
                "§7Stats von §f" + p.getName(),
                "§r",
                "§7Platz: §f" + StatsKnockFFA.getUserRanking(p.getName()),
                "§7Kills: §f" + StatsKnockFFA.getKills(p.getUniqueId().toString(), p.getName()),
                "§7Tode: §f" + StatsKnockFFA.getDeaths(p.getUniqueId().toString(), p.getName())};
        final PlayerHolograms holo = new PlayerHolograms(Text, loc);
        holo.showPlayer(p);
    }


}
