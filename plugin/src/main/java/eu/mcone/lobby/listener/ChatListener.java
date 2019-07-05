/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.listener;

import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.util.SilentLobbyUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler (priority = EventPriority.LOW)
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();

        if (SilentLobbyUtils.isActivatedSilentHub(p)) {
            e.setCancelled(true);
            LobbyPlugin.getInstance().getMessager().send(p, "§cDu kannst in der Silent Lobby keine Chat Nachrichten senden oder empfangen.");
        }
    }

}
