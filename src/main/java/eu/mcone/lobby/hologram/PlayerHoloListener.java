/*
 * Copyright (c) 2017 Dominik L., Rufus Maiwald, BamDev and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.hologram;

import eu.mcone.lobby.Main;
import de.Dominik.BukkitCoreSystem.util.LocationFactory;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import static de.Dominik.BukkitCoreSystem.Main.*;

public class PlayerHoloListener {

    public static void HoloSkypvp(final Player p) {
        final Location loc = LocationFactory.getXYZConfigLocation(Main.config, "Location-Holo-Skypvp");

        final String[] Text = { "§7» §9§lSkyPvP",
                "§7Stats von §f" + p.getName(),
                "§r",
                "§7Platz: §f" + statsSkypvp.getUserRanking(p.getName()),
                "§7Kills: §f" + statsSkypvp.getKills(p.getUniqueId().toString(), p.getName()),
                "§7Tode: §f" + statsSkypvp.getDeaths(p.getUniqueId().toString(), p.getName())};
        final Hologram holo = new Hologram(Text, loc);
        holo.showPlayer(p);
    }

    public static void HoloMinewar(final Player p) {
        final Location loc = LocationFactory.getXYZConfigLocation(Main.config, "Location-Holo-Minewar");

        final String[] Text = { "§7» §2§lMinewar",
                "§7Stats von §f" + p.getName(),
                "§r",
                "§7Platz: §f" + statsMinewar.getUserRanking(p.getName()),
                "§7Kills: §f" + statsMinewar.getKills(p.getUniqueId().toString(), p.getName()),
                "§7Tode: §f" + statsMinewar.getDeaths(p.getUniqueId().toString(), p.getName()),
                "§r",
                "§7Wins: §f" + statsMinewar.getWins(p.getUniqueId().toString(), p.getName()),
                "§7Lose: §f" + statsMinewar.getLoses(p.getUniqueId().toString(), p.getName())};
        final Hologram holo = new Hologram(Text, loc);
        holo.showPlayer(p);
    }

    public static void HoloBedwars(final Player p) {
        final Location loc = LocationFactory.getXYZConfigLocation(Main.config, "Location-Holo-Bedwars");

        final String[] Text = { "§7» §c§lBedwars",
                "§7Stats von §f" + p.getName(),
                "§r",
                "§7Platz: §f" + statsBedwars.getUserRanking(p.getName()),
                "§7Kills: §f" + statsBedwars.getKills(p.getUniqueId().toString(), p.getName()),
                "§7Tode: §f" + statsBedwars.getDeaths(p.getUniqueId().toString(), p.getName()),
                "§r",
                "§7Wins: §f" + statsBedwars.getWins(p.getUniqueId().toString(), p.getName()),
                "§7Lose: §f" + statsBedwars.getLoses(p.getUniqueId().toString(), p.getName())};
        final Hologram holo = new Hologram(Text, loc);
        holo.showPlayer(p);
    }

    public static void HoloKnockbackFFA(final Player p) {
        final Location loc = LocationFactory.getXYZConfigLocation(Main.config, "Location-Holo-Knockbackffa");

        final String[] Text = { "§7» §e§lKnockFFA",
                "§7Stats von §f" + p.getName(),
                "§r",
                "§7Platz: §f" + statsKnockbackffa.getUserRanking(p.getName()),
                "§7Kills: §f" + statsKnockbackffa.getKills(p.getUniqueId().toString(), p.getName()),
                "§7Tode: §f" + statsKnockbackffa.getDeaths(p.getUniqueId().toString(), p.getName())};
        final Hologram holo = new Hologram(Text, loc);
        holo.showPlayer(p);
    }


}
