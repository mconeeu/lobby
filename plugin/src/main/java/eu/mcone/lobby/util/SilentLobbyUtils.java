/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.util;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.lobby.api.LobbyPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SilentLobbyUtils {

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
        LobbyPlugin.getInstance().getMessager().send(p, "§7Du bist nun nicht mehr in der Privaten Lobby!");

        p.playEffect(p.getLocation(), Effect.EXPLOSION_HUGE, 10);
        p.playEffect(p.getLocation(), Effect.EXPLOSION_LARGE, 10);
    }


    public static void activateSilentLobby(Player p) {
        silent.add(p);
        for (Player all : Bukkit.getOnlinePlayers()) {
            all.hidePlayer(p);
            p.hidePlayer(all);
        }

        Bukkit.getScheduler().runTaskLater(
                LobbyPlugin.getInstance(),
                () -> CoreSystem.getInstance().createTablistInfo().header("§f§lMC ONE §3Minecraftnetzwerk §8» §7Private Lobby").footer("§7§oPublic Beta 5.0").send(p),
                40L
        );

        p.playEffect(p.getLocation(), Effect.EXPLOSION_HUGE, 10);
        p.playEffect(p.getLocation(), Effect.EXPLOSION_LARGE, 10);
        p.playEffect(p.getLocation(), Effect.VOID_FOG, 10);
    }

    public static boolean isActivatedSilentHub(Player p) {
        return silent.contains(p);
    }

}

