/*
 * Copyright (c) 2017 - 2018 Dominik Lippl, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.items.inventory.backpack;

import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.enums.Category;
import eu.mcone.lobby.api.enums.Item;
import eu.mcone.lobby.api.player.LobbyPlayer;
import org.bukkit.entity.Player;

public class MaterialInventory extends BackpackInventory {

    MaterialInventory(Player p, Integer site) {
        super(Category.MATERIAL, site, p);
    }

    @Override
    protected void setItems(Player p) {
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p.getUniqueId());

        if (!lp.hasItem(Item.MATERIAL_IRON_6)) {
            if (lp.hasItem(Item.MATERIAL_IRON_4) && lp.hasItem(Item.MATERIAL_IRON_2)) {
                lp.removeItem(Item.MATERIAL_IRON_4);
                lp.removeItem(Item.MATERIAL_IRON_2);

                lp.addItem(Item.MATERIAL_IRON_6);
            }
        }

        if (!lp.hasItem(Item.MATERIAL_IRON_10)) {
            if (lp.hasItem(Item.MATERIAL_IRON_6) && lp.hasItem(Item.MATERIAL_IRON_4)) {
                lp.removeItem(Item.MATERIAL_IRON_4);
                lp.removeItem(Item.MATERIAL_IRON_6);

                lp.addItem(Item.MATERIAL_IRON_10);
            }
        }

        if (!lp.hasItem(Item.MATERIAL_IRON_8)) {
            if (lp.hasItem(Item.MATERIAL_IRON_6) && lp.hasItem(Item.MATERIAL_IRON_2)) {
                lp.removeItem(Item.MATERIAL_IRON_2);
                lp.removeItem(Item.MATERIAL_IRON_6);

                lp.addItem(Item.MATERIAL_IRON_8);
            }
        }

        if (!lp.hasItem(Item.MATERIAL_IRON_10)) {
            if (lp.hasItem(Item.MATERIAL_IRON_8) && lp.hasItem(Item.MATERIAL_IRON_2)) {
                lp.removeItem(Item.MATERIAL_IRON_8);
                lp.removeItem(Item.MATERIAL_IRON_2);

                lp.addItem(Item.MATERIAL_IRON_10);
            }
        }

        for (Item item : Item.values()) {
            if (item.hasCategory() && item.getCategory().equals(Category.MATERIAL)) {
                if (lp.hasItem(item)) {
                    addItem(item.getItemStack(), e -> {});
                }
            }
        }

        openInventory();
    }
}
