/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.listener;

import eu.mcone.coresystem.api.bukkit.event.NickEvent;
import eu.mcone.coresystem.api.bukkit.event.UnnickEvent;
import eu.mcone.coresystem.api.bukkit.item.Skull;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.player.HotbarItem;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.api.player.LobbySettings;
import eu.mcone.lobby.inventory.InteractionInventory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NickListener implements Listener {

    @EventHandler
    public void onNick(NickEvent e) {
        Player p = e.getPlayer().bukkit();
        if (InteractionInventory.stacking.containsKey(p)) {
            Player isStacked = InteractionInventory.stacking.get(p);
            InteractionInventory.stacking.remove(p, isStacked);
            p.eject();

            LobbyPlugin.getInstance().getMessenger().send(p, "§4" + isStacked.getName() + " ist nun nicht mehr auf deinem Kopf");
            LobbyPlugin.getInstance().getMessenger().send(isStacked, "§4" + p.getName() + " hat dich fallen gelassen!");

        }


        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);

        if (lp != null) {
            LobbySettings settings = lp.getSettings();
            if (settings.isRankBoots()) {
                p.getInventory().setBoots(null);
            }
        }

        p.getInventory().setItem(6, HotbarItem.NICK_ACTIVATED);

        p.getInventory().setItem(
                8,
                Skull.fromMojangValue(e.getNick().getSkinInfo().getValue(), 1)
                        .toItemBuilder()
                        .displayName("§3§lProfil §8» §7§oEinstellungen / Stats / Freunde")
                        .create()
        );

        reloadManager(p);
    }

    @EventHandler
    public void onUnnick(UnnickEvent e) {
        Player p = e.getPlayer().bukkit();
        if (InteractionInventory.stacking.containsKey(p)) {
            Player isStacked = InteractionInventory.stacking.get(p);
            InteractionInventory.stacking.remove(p, isStacked);
            p.eject();

            LobbyPlugin.getInstance().getMessenger().send(p, "§4" + isStacked.getName() + " ist nun nicht mehr auf deinem Kopf");
            LobbyPlugin.getInstance().getMessenger().send(isStacked, "§4" + p.getName() + " hat dich fallen gelassen!");

        }

        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);
        LobbySettings settings = lp.getSettings();

        p.getInventory().setItem(6, HotbarItem.NICK_DISABLED);

        p.getInventory().setItem(
                8,
                Skull.fromMojangValue(e.getPlayer().getSkin().getValue(), 1)
                        .toItemBuilder()
                        .displayName("§3§lProfil §8» §7§oEinstellungen / Stats / Freunde")
                        .create()
        );

        reloadManager(p);

        if (settings.isRankBoots()) {
            LobbyPlugin.getInstance().getBackpackManager().setRankBoots(p);
        }
    }

    private static void reloadManager(Player p) {
        LobbyPlugin.getInstance().getPlayerHiderManager().updateHider(p);
        LobbyPlugin.getInstance().getSilentLobbyManager().updateSilentLobby(p);
        LobbyPlugin.getInstance().getOfficeManager().updateOffice(p);
    }

}
