/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.listener;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.event.MoneyChangeEvent;
import eu.mcone.coresystem.api.bukkit.event.PermissionChangeEvent;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.coresystem.api.bukkit.scoreboard.CoreObjective;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.items.LobbyItem;
import eu.mcone.lobby.api.player.HotbarItem;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.api.player.scoreboard.SidebarObjective;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.DisplaySlot;

public class PlayerUpdateListener implements Listener {

    @EventHandler
    public void on(MoneyChangeEvent e) {
        CorePlayer p = e.getPlayer();

        if (p != null) {
            CoreObjective objective = p.getScoreboard().getObjective(DisplaySlot.SIDEBAR);

            if (objective instanceof SidebarObjective) {
                p.getScoreboard().getObjective(DisplaySlot.SIDEBAR).reload();
            }
        }
    }

    @EventHandler
    public void on(PermissionChangeEvent e) {
        CorePlayer p = e.getPlayer();
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);

        p.bukkit().getInventory().setItem(2, null);
        p.bukkit().getInventory().setItem(6, null);

        if (!lp.hasLobbyItem(LobbyItem.BANKCARD_PREMIUM)) {
            if (p.hasPermission("mcone.premium")) {
                if (!lp.hasLobbyItem(LobbyItem.BANKCARD)) {
                    lp.addLobbyItem(LobbyItem.BANKCARD_PREMIUM);
                } else {
                    lp.removeLobbyItem(LobbyItem.BANKCARD);
                    lp.addLobbyItem(LobbyItem.BANKCARD_PREMIUM);
                }

            }
        } else {
            if (p.hasPermission("mcone.premium")) {
                if (lp.hasLobbyItem(LobbyItem.BANKCARD)) {
                    lp.removeLobbyItem(LobbyItem.BANKCARD);
                }
            } else {
                if (lp.hasLobbyItem(LobbyItem.BANKCARD_PREMIUM)) {
                    lp.removeLobbyItem(LobbyItem.BANKCARD_PREMIUM);
                }
            }
        }

        if (p.hasPermission("system.bungee.nick")) {
            p.bukkit().getInventory().setItem(6, CoreSystem.getInstance().getCorePlayer(p.bukkit()).isNicked() ? HotbarItem.NICK_ENABLED : HotbarItem.NICK_DISABLED);
        }

        LobbyPlugin.getInstance().getHotbarSettings().updateInventory(p.bukkit(), lp);

        if (e.getType().equals(PermissionChangeEvent.Type.GROUP_CHANGE)) {
            p.getScoreboard().getObjective(DisplaySlot.SIDEBAR).reload();
        }
    }

}
