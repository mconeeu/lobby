/*
 * Copyright (c) 2017 - 2018 Dominik Lippl, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.pets.listener;

import eu.mcone.lobby.pets.LobbyPets;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMove implements Listener {

    @EventHandler
    public void on(PlayerMoveEvent e) {
        Player p = e.getPlayer();

        if (LobbyPets.getInstance().hasPet(p)) {
            LobbyPets.getInstance().followPlayer(p);
        }
    }

}
