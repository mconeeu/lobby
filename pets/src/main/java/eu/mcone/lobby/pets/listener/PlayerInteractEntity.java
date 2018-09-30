/*
 * Copyright (c) 2017 - 2018 Dominik Lippl, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.pets.listener;

import eu.mcone.lobby.pets.LobbyPets;
import eu.mcone.lobby.pets.inventory.PetInteractInventory;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class PlayerInteractEntity implements Listener {

    @EventHandler
    public void on(PlayerInteractEntityEvent e) {
        Player p = e.getPlayer();

        if (LobbyPets.getInstance().hasPet(p)) {
            Entity entity = LobbyPets.getInstance().getEntity(p);

            if (e.getRightClicked().equals(entity)) {
                new PetInteractInventory(p, entity);
            }
        }
    }

}
