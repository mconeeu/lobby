/*
 * Copyright (c) 2017 - 2018 Dominik Lippl, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.story.inventory.backpack;

import eu.mcone.lobby.api.enums.Category;
import eu.mcone.lobby.api.enums.Item;
import eu.mcone.lobby.items.inventory.backpack.BackpackInventory;
import org.bukkit.entity.Player;

public class StoryItemInventory extends BackpackInventory {

    public StoryItemInventory(Player p, Integer site) {
        super(Category.STORY_ITEMS, site, p);
    }

    @Override
    public void setItems(Player p) {
        for (Item item : Item.values()) {
            if (item.hasCategory() && item.getCategory().equals(Category.STORY_ITEMS) && playerHasItem(item)) {
                addItem(item.getItemStack(), e -> p.getInventory().setItem(2, item.getItemStack()));
            }
        }
    }

}



