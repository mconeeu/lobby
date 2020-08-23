/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.story.inventory.story.chapter1;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.items.LobbyItem;
import eu.mcone.lobby.api.player.LobbyPlayer;
import org.bukkit.entity.Player;

public class WitchInventory extends CoreInventory {

    public WitchInventory(Player p) {
        super("§5§lBeutel", p, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);

        setItem(InventorySlot.ROW_2_SLOT_5, LobbyItem.MAGICDRINK.getItemStack(), e -> {
            if (!lp.hasLobbyItem(LobbyItem.MAGICDRINK)) {
                lp.addLobbyItem(LobbyItem.MAGICDRINK);
                LobbyPlugin.getInstance().getMessenger().send(p, "§7Du hast den Zaubertrank aufgenommen!");
            } else {
                LobbyPlugin.getInstance().getMessenger().send(p, "§4Du besitzt diese Item bereits!");
            }

            p.closeInventory();
        });

        openInventory();
    }

}



