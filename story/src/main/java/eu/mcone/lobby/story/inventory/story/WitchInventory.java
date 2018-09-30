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

public class WitchInventory extends CoreInventory {

    public WitchInventory(Player p) {
        super("§5§lBeutel", p, InventorySlot.ROW_3, Option.FILL_EMPTY_SLOTS);
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p.getUniqueId());

        setItem(InventorySlot.ROW_2_SLOT_5, Item.MAGICDRINK.getItemStack(), e -> {
            if (lp.hasItem(Item.MAGICDRINK)) {
                if ((lp.getCorePlayer().getCoins() - Item.MAGICDRINK.getCoins()) >= 0) {
                    lp.getCorePlayer().removeCoins(Item.MAGICDRINK.getCoins());
                    lp.addItem(Item.MAGICDRINK);
                    p.sendMessage("§8[§7§l!§8] §cSecret §8» §7Du hast das Item §f" + Item.MAGICDRINK.getName() + " §7für §6§l" + Item.MAGICDRINK.getCoins() + " §6Coins §7erfolgreich gekauft!");
                } else {
                    p.sendMessage(CoreSystem.getInstance().getTranslationManager().get("lobby.prefix") + "Du hast nicht genügen §6§lCoins!");
                }
            } else {
                p.sendMessage(CoreSystem.getInstance().getTranslationManager().get("lobby.prefix") + "§4Du besitzt diese Item bereits!");
            }
            p.closeInventory();
        });

        openInventory();
    }

}



