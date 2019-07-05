/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.items.inventory.backpack;

import eu.mcone.lobby.api.enums.Category;
import eu.mcone.lobby.api.enums.Item;
import org.bukkit.entity.Player;

public class ExclusiveInventory extends BackpackInventory {

    public ExclusiveInventory(Player p) {
        super(Category.EXCLUSIVE, p);

        for (Item item : Item.values()) {
            if (item.hasCategory()) {
                addItem(item.getItemStack(), e -> {
                    if (playerHasItem(item)) {
                        p.getInventory().setItem(3, item.getItemStack());
                    }
                });
            }
        }
    }

}
