/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.items.inventory.chest;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.player.LobbyPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class ChestOpeningInventory extends CoreInventory {

    public ChestOpeningInventory(Player p) {
        super("§8» §e§lChestOpening §8| §fGewinne Items", p, InventorySlot.ROW_3, Option.FILL_EMPTY_SLOTS);
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p.getUniqueId());

        setItem(InventorySlot.ROW_2_SLOT_5, new ItemBuilder(Material.CHEST, 1, 0).displayName("§a§lEine Kiste öffnen").lore("§7§oDu hast noch §f§o" + lp.getChests() + "§7§o Kisten", "", "§8» §f§nLinksklick§8 | §7§oÖffnen").create(), e -> {
            if (lp.getChests() > 0) {
                new ChestItemInventory(p);
            } else {
                p.sendMessage("§8[§7§l!§8] §eChestOpening §8»§7 Du hast nicht genügend Kisten!");
                p.closeInventory();
            }
        });

        openInventory();
    }

}

