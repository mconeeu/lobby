/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.util;

import eu.mcone.lobby.Lobby;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.player.HotbarItems;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SilentLobbyManager implements eu.mcone.lobby.api.player.SilentLobbyManager {

    private List<Player> silent;

    public SilentLobbyManager() {
        this.silent = new ArrayList<>();
    }

    @Override
    public void deactivateSilentLobby(Player p) {
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
        p.getInventory().setItem(Lobby.getSystem().getJumpNRunManager().isCurrentlyPlaying(p) ? 1 : 2, HotbarItems.PRIVATE_LOBBY);
        p.getInventory().setItem(0, HotbarItems.HIDE_PLAYERS);
        p.playEffect(p.getLocation(), Effect.EXPLOSION_LARGE, 10);
        p.playSound(p.getLocation(), Sound.EXPLODE, 1, 1);
        p.playSound(p.getLocation(), Sound.GLASS, 1, 1);
    }

    @Override
    public void activateSilentLobby(Player p) {
        silent.add(p);
        for (Player all : Bukkit.getOnlinePlayers()) {
            all.hidePlayer(p);
            p.hidePlayer(all);
        }
        p.playSound(p.getLocation(), Sound.GLASS, 1, 1);
        p.playSound(p.getLocation(), Sound.EXPLODE, 1, 1);
        p.getInventory().setItem(0, HotbarItems.LOBBY_HIDER_UNAVAILABLE);
        p.getInventory().setItem(Lobby.getSystem().getJumpNRunManager().isCurrentlyPlaying(p) ? 1 : 2, HotbarItems.LEAVE_PRIVATE_LOBBY);
        p.playEffect(p.getLocation(), Effect.EXPLOSION_HUGE, 10);
        p.playEffect(p.getLocation(), Effect.EXPLOSION_LARGE, 10);
        p.playEffect(p.getLocation(), Effect.EXPLOSION_LARGE, 10);
        p.playEffect(p.getLocation(), Effect.VOID_FOG, 10);
    }

    @Override
    public boolean isActivatedSilentHub(Player p) {
        return silent.contains(p);
    }

}

