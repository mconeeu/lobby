/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.listener;

import eu.mcone.coresystem.api.bukkit.event.npc.NpcInteractEvent;
import eu.mcone.coresystem.api.bukkit.gamemode.Gamemode;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.inventory.ServerInventory;
import eu.mcone.lobby.onehit.OneHitManager;
import net.minecraft.server.v1_8_R3.PacketPlayInUseEntity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class NpcListener implements Listener {

    @EventHandler
    public void onNpcInteract(NpcInteractEvent e) {
        if (e.getNpc().getData().getType().equals(EntityType.PLAYER) && e.getAction().equals(PacketPlayInUseEntity.EnumEntityUseAction.INTERACT)) {
            Gamemode gamemode = null;

            for (Gamemode gm : Gamemode.values()) {
                if (gm.getName().equalsIgnoreCase(e.getNpc().getData().getName())) {
                    gamemode = gm;
                }
            }

            if (gamemode != null) {
                new ServerInventory(e.getPlayer(), gamemode);
            }
        }
    }

}
