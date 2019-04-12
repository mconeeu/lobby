/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.listener;

import eu.mcone.coresystem.api.bukkit.event.NpcInteractEvent;
import eu.mcone.coresystem.api.bukkit.npc.entity.PlayerNpc;
import eu.mcone.lobby.inventory.ServerInventory;
import net.minecraft.server.v1_8_R3.PacketPlayInUseEntity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NpcListener implements Listener {

    @EventHandler
    public void onNpcInteract(NpcInteractEvent e) {
        if (e.getNpc().getData().getType().equals(EntityType.PLAYER) && e.getAction().equals(PacketPlayInUseEntity.EnumEntityUseAction.INTERACT)) {
            PlayerNpc npc = (PlayerNpc) e.getNpc();

            if (npc.getData().getDisplayname().contains("§")) {
                ServerInventory.Gamemode sm = ServerInventory.Gamemode.getGamemodeByNpcName(npc.getData().getDisplayname());

                if (sm != null) {
                    new ServerInventory(e.getPlayer(), sm);
                }
            }
        }
    }

}
