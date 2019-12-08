/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.story.inventory.story;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.enums.Item;
import eu.mcone.lobby.api.player.LobbyPlayer;
import org.bukkit.entity.Player;

public class EndInventory extends CoreInventory {

    public EndInventory(Player p) {
        super("§5§lEnde", p, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);
        LobbyPlayer lp = LobbyPlugin.getInstance().getGamePlayer(p);

        setItem(InventorySlot.ROW_2_SLOT_5, Item.ONE_HIT_SWORD.getItemStack(), e -> {
            if (!Item.ONE_HIT_SWORD.has(lp)) {
                Item.ONE_HIT_SWORD.add(lp);
                LobbyPlugin.getInstance().getMessager().send(p, "Du hast das One Hit Sword aufgenommen!");
            } else {
                LobbyPlugin.getInstance().getMessager().send(p, "§4Du besitzt diese Item bereits!");
            }
            p.closeInventory();
        });

        openInventory();
    }

}