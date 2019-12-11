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

public class StoryBackpackInventoryListener extends BackpackInventoryListener {

    @Override
    public void setBackpackItems(CategoryInventory inv, Category category, Set<BackpackItem> categoryItems, GameAPIPlayer<?> gp, Player p) {
        if (!LobbyItem.BANKCARD_PREMIUM.has(gp)) {
            if (p.hasPermission("mcone.premium")) {
                if (!LobbyItem.BANKCARD.has(gp)) {
                    LobbyItem.BANKCARD_PREMIUM.add(gp);
                } else {
                    LobbyItem.BANKCARD.remove(gp);
                    LobbyItem.BANKCARD_PREMIUM.add(gp);
                }

            } else {
                if (LobbyItem.BANKCARD_PREMIUM.has(gp)) {
                    LobbyItem.BANKCARD_PREMIUM.remove(gp);
                }
            }
        } else {
            if (p.hasPermission("mcone.premium")) {
                if (LobbyItem.BANKCARD.has(gp)) {
                    LobbyItem.BANKCARD.remove(gp);
                }
            }
        }

        for (BackpackItem item : categoryItems) {
            if (gp.hasBackpackItem(category.getName(), item)) {
                if (p.hasPermission("lobby.silenthub")) {
                    inv.addItem(item.getItem(), e -> p.getInventory().setItem(3, item.getItem()));
                } else {
                    inv.addItem(item.getItem(), e -> p.getInventory().setItem(2, item.getItem()));
                }
            }
        }
    }
}
