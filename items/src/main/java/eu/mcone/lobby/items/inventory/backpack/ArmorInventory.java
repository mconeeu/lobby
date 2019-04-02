/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.items.inventory.backpack;

import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.enums.Category;
import eu.mcone.lobby.api.enums.Item;
import eu.mcone.lobby.api.player.LobbyPlayer;
import org.bukkit.entity.Player;

public class ArmorInventory extends BackpackInventory {

    ArmorInventory(Player p, Integer site) {
        super(Category.ARMOR, site, p);
    }

    @Override
    protected void setItems(Player p) {
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p.getUniqueId());

        for (Item item : Item.values()) {
            if (item.hasCategory() && item.getCategory().equals(Category.ARMOR)) {
                if (lp.hasItem(item)) {
                    addItem(item.getItemStack(), e -> {

                    });
                }
            }
        }
    }
}
