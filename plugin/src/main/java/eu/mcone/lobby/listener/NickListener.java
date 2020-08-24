/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.listener;

import eu.mcone.coresystem.api.bukkit.event.NickEvent;
import eu.mcone.coresystem.api.bukkit.event.UnnickEvent;
import eu.mcone.lobby.Lobby;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.player.HotbarItem;
import eu.mcone.lobby.api.player.LobbyPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NickListener implements Listener {

    @EventHandler
    public void onNick(NickEvent e) {
        Player p = e.getPlayer().bukkit();

        Lobby.getSystem().getStackingManager().unstack(p);
        Lobby.getSystem().getBackpackManager().unsetRankBoots(p);

        p.getInventory().setItem(6, HotbarItem.NICK_ENABLED);
        p.getInventory().setItem(
                8,
                HotbarItem.getProfile(e.getNick().getSkinInfo())
        );
    }

    @EventHandler
    public void onUnnick(UnnickEvent e) {
        Player p = e.getPlayer().bukkit();
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);

        Lobby.getSystem().getStackingManager().unstack(p);
        if (lp.getSettings().isRankBoots()) {
            LobbyPlugin.getInstance().getBackpackManager().setRankBoots(p);
        }

        p.getInventory().setItem(6, HotbarItem.NICK_DISABLED);
        p.getInventory().setItem(
                8,
                HotbarItem.getProfile(e.getPlayer().getSkin())
        );
    }

}
