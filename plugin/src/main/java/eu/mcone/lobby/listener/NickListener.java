/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.listener;

import eu.mcone.coresystem.api.bukkit.event.nick.NickEvent;
import eu.mcone.coresystem.api.bukkit.event.nick.UnnickEvent;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.core.player.Nick;
import eu.mcone.lobby.Lobby;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.player.HotbarItem;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.api.player.hotbar.HotbarGeneralCategorys;
import eu.mcone.lobby.api.player.hotbar.items.enums.HotbarItemEnum;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Objects;

public class NickListener implements Listener {

    @EventHandler
    public void onNick(NickEvent e) {
        Player p = e.getPlayer().bukkit();
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);

        if (lp != null) {
            p.getInventory().setItem(6, HotbarItem.NICK_ENABLED);
            onNickChange(p, lp, e.getNick());
        }
    }

    @EventHandler
    public void onUnnick(UnnickEvent e) {
        Player p = e.getPlayer().bukkit();

        if (p.isOnline()) {
            LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);

            p.getInventory().setItem(6, HotbarItem.NICK_DISABLED);
            onNickChange(p, lp, null);
        }
    }

    private void onNickChange(Player p, LobbyPlayer lp, Nick nick) {
        Lobby.getSystem().getStackingManager().unstack(p);
        Lobby.getSystem().getBackpackManager().setRankBoots(p);

        HotbarItemEnum hotbarItem = Objects.requireNonNull(HotbarGeneralCategorys.PROFILE.getItem(lp));
        ItemBuilder item = hotbarItem.isMainItem()
                ? HotbarItem.getProfile(nick == null ? lp.getCorePlayer().getSkin() : nick.getSkinInfo())
                : hotbarItem.getItem();

        p.getInventory().setItem(
                lp.getSettings().calculateSlots().get(HotbarGeneralCategorys.PROFILE).getSlot(),
                item.displayName(HotbarGeneralCategorys.PROFILE.getDisplayName()).create()
        );
    }
}
