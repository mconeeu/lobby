/*
 * Copyright (c) 2017 - 2018 Dominik Lippl, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.story.inventory.story;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.enums.Item;
import eu.mcone.lobby.api.player.LobbyPlayer;
import org.bukkit.entity.Player;

public class EndInventory extends CoreInventory {

    public EndInventory(Player p) {
        super("§5§lEnde", p, InventorySlot.ROW_3, CoreInventory.Option.FILL_EMPTY_SLOTS);
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p.getUniqueId());

        setItem(InventorySlot.ROW_2_SLOT_5, Item.ONE_HIT_SWORD.getItemStack(), e -> {
            if (!lp.hasItem(Item.ONE_HIT_SWORD)) {
                lp.addItem(Item.ONE_HIT_SWORD);
                p.sendMessage("§8[§7§l!§8] §cSecret §8» §7Du has das One hit Sword bekommen");
            } else {
                p.sendMessage(CoreSystem.getInstance().getTranslationManager().get("lobby.prefix") + "§4Du besitzt diese Item bereits!");
            }
            p.closeInventory();
        });

        openInventory();
    }

}
