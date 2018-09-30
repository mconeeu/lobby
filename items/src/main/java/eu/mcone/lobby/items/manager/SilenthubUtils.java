/*
 * Copyright (c) 2017 - 2018 Dominik Lippl, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.items.manager;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SilenthubUtils {

    private static List<Player> silent = new ArrayList<>();

    public static void deactivateSilentLobby(Player p) {
        silent.remove(p);
        for (Player all : Bukkit.getOnlinePlayers()) {
            if (!silent.contains(all)) {
                all.showPlayer(p);
                p.showPlayer(all);
            }
        }

        CoreSystem.getInstance().createTablistInfo().header("§f§lMC ONE §3Minecraftnetzwerk §8» §7Lobby").footer("§7§oPublic Beta 5.0").send(p);
        p.sendMessage("§8[§7§l!§8] §cPrivate Lobby §8» §cDu bist nun nicht mehr in der Privaten Lobby");

        p.playEffect(p.getLocation(), Effect.EXPLOSION_HUGE, 10);
        p.playEffect(p.getLocation(), Effect.EXPLOSION_LARGE, 10);
    }


    public static void activateSilentLobby(Player p) {
        silent.add(p);
        for (Player all : Bukkit.getOnlinePlayers()) {
            all.hidePlayer(p);
            p.hidePlayer(all);
        }

        CoreSystem.getInstance().createTablistInfo().header("§f§lMC ONE §3Minecraftnetzwerk §8» §7Private Lobby").footer("§7§oPublic Beta 5.0").send(p);

        p.playEffect(p.getLocation(), Effect.EXPLOSION_HUGE, 10);
        p.playEffect(p.getLocation(), Effect.EXPLOSION_LARGE, 10);
        p.playEffect(p.getLocation(), Effect.VOID_FOG, 10);
    }

    public static boolean isActivatedSilentHub(Player p) {
        return silent.contains(p);
    }

}

