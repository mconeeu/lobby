/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.listener;

import eu.mcone.coresystem.api.bukkit.event.NickEvent;
import eu.mcone.coresystem.api.bukkit.event.UnnickEvent;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.lobby.Lobby;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.player.HotbarItem;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.api.player.hotbar.HotbarGeneralCategorys;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NickListener implements Listener {

    @EventHandler
    public void onNick(NickEvent e) {
        Player p = e.getPlayer().bukkit();
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);

        updateInv(p, lp, true, e);

    }

    @EventHandler
    public void onUnnick(UnnickEvent e) {
        Player p = e.getPlayer().bukkit();
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);

        updateInv(p, lp, false, null);
    }

    private void updateInv(Player p, LobbyPlayer lp, boolean nick, NickEvent nickEvent) {
        for (HotbarGeneralCategorys cat : HotbarGeneralCategorys.values()) {
            int slot = lp.getSettings().calculateSlots().get(cat).getSlot();

            Lobby.getSystem().getStackingManager().unstack(p);

            if (nick) {
                Lobby.getSystem().getBackpackManager().unsetRankBoots(p);
                p.getInventory().setItem(6, HotbarItem.NICK_ENABLED);
            } else {
                p.getInventory().setItem(6, HotbarItem.NICK_DISABLED);
                if (lp.getSettings().isRankBoots()) {
                    LobbyPlugin.getInstance().getBackpackManager().setRankBoots(p);
                }
            }

            if (!cat.equals(HotbarGeneralCategorys.PLAYER_HIDER)) {
                ItemBuilder item = cat.equals(HotbarGeneralCategorys.PROFILE) && cat.getItem(lp).isMainItem() ? HotbarItem.getProfile(lp.getCorePlayer().getSkin()) : cat.getItem(lp).getItem();
                item.displayName(cat.getDisplayName());

                p.getInventory().setItem(slot, item.create());

                if (cat.equals(HotbarGeneralCategorys.PROFILE)) {
                    if (cat.getItem(lp).isMainItem()) {
                        if (nickEvent != null) {
                            p.getInventory().setItem(
                                    slot,
                                    HotbarItem.getProfile(nickEvent.getNick().getSkinInfo()).create()
                            );
                        }
                    }
                }
            } else {
                if (LobbyPlugin.getInstance().getVanishManager().isInSilentLobby(p)) {
                    p.getInventory().setItem(slot, HotbarItem.LOBBY_HIDER_UNAVAILABLE_SILENT_LOBBY);
                } else {
                    p.getInventory().setItem(slot, LobbyPlugin.getInstance().getVanishManager().getVanishPlayerVisibility(p).getItem());
                }
            }
        }
    }

}
