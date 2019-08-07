/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.story.inventory.story;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.gamesystem.api.enums.Item;
import eu.mcone.gamesystem.api.game.player.GamePlayer;
import eu.mcone.lobby.api.LobbyPlugin;
import org.bukkit.entity.Player;

public class WitchInventory extends CoreInventory {

    public WitchInventory(Player p) {
        super("§5§lBeutel", p, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);
        GamePlayer lp = LobbyPlugin.getInstance().getGamePlayer(p.getUniqueId());

        setItem(InventorySlot.ROW_2_SLOT_5, Item.MAGICDRINK.getItemStack(), e -> {
            if (!lp.hasItem(Item.MAGICDRINK)) {
                lp.addItem(Item.MAGICDRINK);
                LobbyPlugin.getInstance().getMessager().send(p, "§7Du hast den Zaubertrank aufgenommen!");
            } else {
                LobbyPlugin.getInstance().getMessager().send(p, "§4Du besitzt diese Item bereits!");
            }

            p.closeInventory();
        });

        openInventory();
    }

}



