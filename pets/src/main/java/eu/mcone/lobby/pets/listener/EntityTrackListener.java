/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.pets.listener;

import eu.mcone.lobby.pets.LobbyPets;
import eu.mcone.lobby.pets.inventory.PetInteractInventory;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class EntityTrackListener implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();

        if (LobbyPets.getInstance().hasPet(p)) {
            LobbyPets.getInstance().followPlayer(p);
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEntityEvent e) {
        Player p = e.getPlayer();

        if (LobbyPets.getInstance().hasPet(p)) {
            Entity entity = LobbyPets.getInstance().getEntity(p);

            if (e.getRightClicked().equals(entity)) {
                new PetInteractInventory(p, entity);
            }
        }
    }

    @EventHandler
    public void onProjectileLaunch(ProjectileLaunchEvent e) {
        if (e.getEntityType().equals(EntityType.WITHER_SKULL)) {
            e.setCancelled(true);
        }
    }

}
