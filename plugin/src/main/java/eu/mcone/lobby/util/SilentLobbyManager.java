/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.util;

import eu.mcone.gameapi.api.GameAPI;
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

    private final List<Player> silent;

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
        LobbyPlugin.getInstance().getPlayerHiderManager().updateHider(p);
        LobbyPlugin.getInstance().getOfficeManager().updateOffice(p);

        LobbyPlugin.getInstance().getMessenger().send(p, "§7Du bist nun nicht mehr in der Privaten Lobby!");

        GameAPI.getInstance().getGamePlayer(p).setEffectsVisible(true);
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
        LobbyPlugin.getInstance().getMessenger().send(p, "§2Du bist in der §aPrivaten Lobby§2 gespawnt. Hier bist du vollkommen ungestört!");

        GameAPI.getInstance().getGamePlayer(p).setEffectsVisible(false);
        p.playSound(p.getLocation(), Sound.GLASS, 1, 1);
        p.playSound(p.getLocation(), Sound.EXPLODE, 1, 1);

        /* MUST BE THERE FOR THE RELOAD SECTION */
            p.getInventory().setItem(0, HotbarItems.LOBBY_HIDER_UNAVAILABLE);

        p.getInventory().setItem(2, null);
        if (Lobby.getSystem().getJumpNRunManager().isCurrentlyPlaying(p)) {
            p.getInventory().setItem(1, HotbarItems.LEAVE_PRIVATE_LOBBY);
        }
        p.playEffect(p.getLocation(), Effect.EXPLOSION_HUGE, 10);
        p.playEffect(p.getLocation(), Effect.EXPLOSION_LARGE, 10);
        p.playEffect(p.getLocation(), Effect.EXPLOSION_LARGE, 10);
        p.playEffect(p.getLocation(), Effect.VOID_FOG, 10);
    }

    @Override
    public void updateSilentLobby(Player p) {
        for (Player SilentPlayer : silent) {
            p.hidePlayer(SilentPlayer);
            SilentPlayer.hidePlayer(p);
        }
    }

    @Override
    public boolean isActivatedSilentHub(Player p) {
        return silent.contains(p);
    }

}

