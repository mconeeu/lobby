/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.items.listener;

import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.api.enums.LobbyCategory;
import eu.mcone.lobby.api.player.HotbarItems;
import eu.mcone.lobby.items.inventory.chestopening.ChestOpeningInventory;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryTriggerListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                Material clicked = e.getClickedBlock().getType();

                switch (clicked) {
                    case ENDER_PORTAL_FRAME: {
                        if (LobbyWorld.ONE_ISLAND.getWorld().getBlockLocation("chestopening").equals(e.getClickedBlock().getLocation())) {
                            new ChestOpeningInventory(p);
                            return;
                        }
                    }
                }
            }

            ItemStack i = e.getItem();
            if ((i == null) || (!i.hasItemMeta()) || (!i.getItemMeta().hasDisplayName())) {
                return;
            }

            if (e.getItem().equals(HotbarItems.BACKPACK)) {
                LobbyPlugin.getInstance().getBackpackManager().openBackpackInventory(LobbyCategory.STORY_ITEMS.name(), p);
            }
        }
    }

}
