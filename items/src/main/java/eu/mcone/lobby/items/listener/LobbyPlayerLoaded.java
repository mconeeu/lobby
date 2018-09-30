/*
 * Copyright (c) 2017 - 2018 Dominik Lippl, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.items.listener;

import eu.mcone.coresystem.api.bukkit.util.ItemBuilder;
import eu.mcone.lobby.api.event.LobbyPlayerLoadedEvent;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.items.manager.SilenthubUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class LobbyPlayerLoaded implements Listener {

    @EventHandler
    public void on(LobbyPlayerLoadedEvent e) {
        LobbyPlayer p = e.getPlayer();
        Player bp = p.bukkit();

        bp.getInventory().setItem(1, new ItemBuilder(Material.STORAGE_MINECART, 1, 0).displayName("§3§lRucksack §8» §7§oZeige deine gesammelten Items an").create());

        if(e.getReason().equals(LobbyPlayerLoadedEvent.Reason.JOINED) && p.getSettings().isSilentHubActivatedOnJoin()) {
            SilenthubUtils.activateSilentLobby(p.bukkit());
            bp.sendMessage("§8[§7§l!§8] §cPrivate Lobby §8» §eDu bist in der Privaten Lobby. Hier bist du völlig ungestört!");
        }

        if (bp.hasPermission("lobby.silenthub")) {
            bp.getInventory().setItem(7, new ItemBuilder(Material.TNT, 1, 0).displayName("§6§lPrivate Lobby §8» §7§oBetrete deine eigene Private Lobby").create());
        }
    }

}
