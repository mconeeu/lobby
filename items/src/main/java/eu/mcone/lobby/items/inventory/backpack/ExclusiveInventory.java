/*
 * Copyright (c) 2017 - 2018 Dominik Lippl, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.items.inventory.backpack;

import eu.mcone.lobby.api.enums.Category;
import eu.mcone.lobby.api.enums.Item;
import org.bukkit.entity.Player;

public class ExclusiveInventory extends BackpackInventory {

    public ExclusiveInventory(Player p, Integer site) {
        super(Category.EXCLUSIVE, site, p);
    }

    @Override
    public void setItems(Player p) {
        for (Item item : Item.values()) {
            if (item.hasCategory() && item.getCategory().equals(Category.HAT) && playerHasItem(item)) {
                addItem(item.getItemStack(), e -> {
                    if (playerHasItem(item)) {
                        p.getInventory().setItem(6, item.getItemStack());
                    }
                });
            }
        }
    }

}
