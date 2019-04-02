/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.listener;

import eu.mcone.lobby.inventory.InteractionInventory;
import eu.mcone.lobby.inventory.ServerInventory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class PlayerInteractEntity implements Listener {

    @EventHandler
    public void on(PlayerInteractEntityEvent e) {
        Player p = e.getPlayer();

        if (e.getRightClicked() instanceof Player) {
            Player clicked = (Player) e.getRightClicked();

            if (clicked.getDisplayName().contains("§")) {
                ServerInventory.Gamemode sm = ServerInventory.Gamemode.getGamemodeByNpcName(clicked.getDisplayName());
                if (sm != null) {
                    new ServerInventory(p, sm);
                }
            } else {
                new InteractionInventory(p, clicked);
            }
        }
    }

}
