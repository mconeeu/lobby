/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.story.inventory.backpack;

import eu.mcone.gamesystem.api.enums.Category;
import eu.mcone.gamesystem.api.enums.Item;
import eu.mcone.gamesystem.api.game.player.GamePlayer;
import eu.mcone.gamesystem.api.lobby.backpack.BackpackInventory;
import eu.mcone.lobby.api.LobbyPlugin;
import org.bukkit.entity.Player;

public class StoryItemInventory extends BackpackInventory {

    public StoryItemInventory(Player p) {
        super(p);
        GamePlayer gp = LobbyPlugin.getInstance().getGamePlayer(p.getUniqueId());
        if (!gp.hasItem(Item.BANKCARD_PREMIUM)) {
            if (p.hasPermission("mcone.premium")) {
                if (!gp.hasItem(Item.BANKCARD)) {
                    gp.addItem(Item.BANKCARD_PREMIUM);
                } else {
                    gp.removeItem(Item.BANKCARD);
                    gp.addItem(Item.BANKCARD_PREMIUM);
                }

            } else {
                if (gp.hasItem(Item.BANKCARD_PREMIUM)) {
                    gp.removeItem(Item.BANKCARD_PREMIUM);
                }
            }
        } else {
            if (p.hasPermission("mcone.premium")) {
                if (gp.hasItem(Item.BANKCARD)) {
                    gp.removeItem(Item.BANKCARD);
                }
            }
        }
        for (Item item : Item.values()) {
            if (item.hasCategory() && item.getCategory().equals(Category.STORY_ITEMS) && playerHasItem(item)) {
                if (p.hasPermission("lobby.silenthub")) {
                    addItem(item.getItemStack(), e -> p.getInventory().setItem(3, item.getItemStack()));
                } else {
                    addItem(item.getItemStack(), e -> p.getInventory().setItem(2, item.getItemStack()));
                }
            }
        }
    }

}




