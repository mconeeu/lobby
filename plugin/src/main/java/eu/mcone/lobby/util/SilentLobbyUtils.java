/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.util;

import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.player.HotbarItems;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
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

        LobbyPlugin.getInstance().getMessager().send(p, "§7Du bist nun nicht mehr in der Privaten Lobby!");

        p.playEffect(p.getLocation(), Effect.EXPLOSION_HUGE, 10);
        p.playEffect(p.getLocation(), Effect.EXPLOSION_LARGE, 10);
        p.getInventory().setItem(0, HotbarItems.HIDE_PLAYERS);
        p.playEffect(p.getLocation(), Effect.EXPLOSION_LARGE, 10);
        p.playSound(p.getLocation(), Sound.EXPLODE, 1, 1);
        p.playSound(p.getLocation(), Sound.GLASS, 1, 1);
    }


    public static void activateSilentLobby(Player p) {
        silent.add(p);
        for (Player all : Bukkit.getOnlinePlayers()) {
            all.hidePlayer(p);
            p.hidePlayer(all);
        }
        p.playSound(p.getLocation(), Sound.GLASS, 1, 1);
        p.playSound(p.getLocation(), Sound.EXPLODE, 1, 1);
        p.getInventory().setItem(0, new ItemBuilder(Material.INK_SACK, 1, 8).displayName("§7§lSpieler Verstecken §8» §7§oIn der Privaten Lobby deaktiviert").create());
        p.playEffect(p.getLocation(), Effect.EXPLOSION_HUGE, 10);
        p.playEffect(p.getLocation(), Effect.EXPLOSION_LARGE, 10);
        p.playEffect(p.getLocation(), Effect.EXPLOSION_LARGE, 10);
        p.playEffect(p.getLocation(), Effect.VOID_FOG, 10);
    }

    public static boolean isActivatedSilentHub(Player p) {
        return silent.contains(p);
    }

}

