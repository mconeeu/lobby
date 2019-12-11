/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.listener;

import eu.mcone.lobby.api.enums.JumpNRun;
import eu.mcone.lobby.api.player.HotbarItems;
import eu.mcone.lobby.jumpnrun.LobbyJumpNRunManager;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

@RequiredArgsConstructor
public class JumpNRunListener implements Listener {

    private final LobbyJumpNRunManager manager;

    @EventHandler
    public void on(org.bukkit.event.player.PlayerCommandPreprocessEvent e) {
        Player p = e.getPlayer();

        if (e.getMessage().equalsIgnoreCase("/spawn")) {
            manager.setCancel(p);
        }
    }

    @EventHandler
    public void on(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            ItemStack i = e.getItem();
            if ((i == null) || (!i.hasItemMeta()) || (!i.getItemMeta().hasDisplayName())) {
                return;
            }

            if (i.equals(HotbarItems.LEAVE_JUMPNRUN)) {
                manager.setCancel(p);
            } else if (i.equals(HotbarItems.TO_CHECKPOINT)) {
                manager.tpToCheckpoint(p);
            }
        } else if (e.getAction().equals(Action.PHYSICAL) && e.getClickedBlock() != null) {
            Material clicked = e.getClickedBlock().getType();

            switch (clicked) {
                case GOLD_PLATE: {
                    Location loc = e.getClickedBlock().getLocation();

                    for (JumpNRun jumpnrun : JumpNRun.values()) {
                        Location start = jumpnrun.getStartPlateLocation();
                        Location end = jumpnrun.getEndPlateLocation();

                        if (start != null && loc.equals(jumpnrun.getStartPlateLocation())) {
                            manager.setStart(p, jumpnrun);
                        } else if (end != null && loc.equals(jumpnrun.getEndPlateLocation())) {
                            manager.setFinish(p);
                        }
                    }
                    break;
                }
                case IRON_PLATE: {
                    if (manager.isCurrentlyPlaying(p)) {
                        for (JumpNRun jumpNRun : JumpNRun.values()) {
                            for (int i = 0; i < jumpNRun.getCheckpoints().length; i++) {
                                if (jumpNRun.getCheckpoints()[i].equals(e.getClickedBlock().getLocation())) {
                                    manager.setCheckpoint(p, i + 1);
                                    return;
                                }
                            }
                        }
                    }
                    break;
                }
            }
        }
    }

}
