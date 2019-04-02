/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.items.inventory.backpack;

import eu.mcone.lobby.api.enums.Category;
import eu.mcone.lobby.api.enums.Item;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashSet;

public class GadgetInventory extends BackpackInventory {

    public GadgetInventory(Player p, Integer site) {
        super(Category.GADGET, site, p);
    }

    @Override
    protected void setItems(Player p) {
        for (Item item : Item.values()) {
            if (new HashSet<>(Arrays.asList(Item.LOVEGUN, Item.ENDERGUN, Item.SNOWGUN, Item.EASTERGUN, Item.ENDERGUN, Item.COINBOMB, Item.BOMB)).contains(item) && playerHasItem(item)) {
                addItem(item.getItemStack(), e -> {
                    if (playerHasItem(item)) {
                        p.getInventory().setItem(2, item.getItemStack());
                    }
                });
            }
        }
    }

}

