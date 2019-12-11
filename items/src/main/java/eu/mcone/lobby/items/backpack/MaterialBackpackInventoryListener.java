/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.items.backpack;

import eu.mcone.coresystem.api.bukkit.inventory.category.CategoryInventory;
import eu.mcone.gameapi.api.backpack.BackpackInventoryListener;
import eu.mcone.gameapi.api.backpack.BackpackItem;
import eu.mcone.gameapi.api.backpack.Category;
import eu.mcone.gameapi.api.player.GameAPIPlayer;
import eu.mcone.lobby.api.enums.LobbyItem;
import org.bukkit.entity.Player;

import java.util.Set;

public class MaterialBackpackInventoryListener extends BackpackInventoryListener {

    @Override
    public void setBackpackItems(CategoryInventory inv, Category category, Set<BackpackItem> categoryItems, GameAPIPlayer<?> gp, Player p) {
        if (!LobbyItem.MATERIAL_IRON_6.has(gp)) {
            if (LobbyItem.MATERIAL_IRON_4.has(gp) && LobbyItem.MATERIAL_IRON_2.has(gp)) {
                LobbyItem.MATERIAL_IRON_4.remove(gp);
                LobbyItem.MATERIAL_IRON_2.remove(gp);

                LobbyItem.MATERIAL_IRON_6.add(gp);
            }
        }

        if (!LobbyItem.MATERIAL_IRON_10.has(gp)) {
            if (LobbyItem.MATERIAL_IRON_6.has(gp) && LobbyItem.MATERIAL_IRON_4.has(gp)) {
                LobbyItem.MATERIAL_IRON_4.remove(gp);
                LobbyItem.MATERIAL_IRON_6.remove(gp);

                LobbyItem.MATERIAL_IRON_10.add(gp);
            }
        }

        if (!LobbyItem.MATERIAL_IRON_8.has(gp)) {
            if (LobbyItem.MATERIAL_IRON_6.has(gp) && LobbyItem.MATERIAL_IRON_2.has(gp)) {
                LobbyItem.MATERIAL_IRON_2.remove(gp);
                LobbyItem.MATERIAL_IRON_6.remove(gp);

                LobbyItem.MATERIAL_IRON_8.add(gp);
            }
        }

        if (!LobbyItem.MATERIAL_IRON_10.has(gp)) {
            if (LobbyItem.MATERIAL_IRON_8.has(gp) && LobbyItem.MATERIAL_IRON_2.has(gp)) {
                LobbyItem.MATERIAL_IRON_8.remove(gp);
                LobbyItem.MATERIAL_IRON_2.remove(gp);

                LobbyItem.MATERIAL_IRON_10.add(gp);
            }
        }

        for (BackpackItem item : categoryItems) {
            if (gp.hasBackpackItem(category.getName(), item)) {
                inv.addItem(item.getItem());
            }
        }
    }
}
