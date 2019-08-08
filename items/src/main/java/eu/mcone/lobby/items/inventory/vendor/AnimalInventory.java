/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.items.inventory.vendor;

import eu.mcone.gamesystem.api.enums.Category;
import eu.mcone.gamesystem.api.enums.Item;
import org.bukkit.entity.Player;

public class AnimalInventory extends VendorInventory {

    public AnimalInventory(Player p) {
        super(p);

        for (Item item : Item.values()) {
            if (item.hasCategory() && item.getCategory().equals(Category.ANIMAL) && playerHasItem(item)) {
                addItem(item.getItemStack(), e -> {
                    new VendorSellInventory(p, item);

                });
            }
        }
    }

}
