/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.items.inventory.trader;

import eu.mcone.coresystem.api.bukkit.facades.Sound;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.items.LobbyItem;
import eu.mcone.lobby.api.player.LobbyPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;

class TicketBuyInventory extends CoreInventory {

    TicketBuyInventory(Player p) {
        super("§8» §e§lHändler §8| §bTickets", p, InventorySlot.ROW_4, InventoryOption.FILL_EMPTY_SLOTS);
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);

        setItem(InventorySlot.ROW_1_SLOT_3, BACK_ITEM, e -> new TraderInventory(p));
        setItem(InventorySlot.ROW_1_SLOT_5, new ItemBuilder(Material.PAPER, 1, 0).displayName("§c§lItems kaufen").lore("§7§oKaufe hier Tickets mit Coins.", "§7§oDie Items stehen dir danach in", "§7§odeinem Rucksack zur Verfügung.").create());
        setItem(InventorySlot.ROW_1_SLOT_7, new ItemBuilder(Material.EMERALD, 1, 0).displayName("§a§lDein Kontostand").lore("§7§oDein Kontostand beträgt:", "§f§o" + lp.getCorePlayer().getCoins() + "§7§o Coins").create());


        setItem(InventorySlot.ROW_3_SLOT_5, LobbyItem.BOAT_PASS.getItemStack(), e -> {
            if (!lp.hasLobbyItem(LobbyItem.BOAT_PASS)) {
                lp.addLobbyItem(LobbyItem.BOAT_PASS);
                p.sendMessage("§8[§7§l!§8] §fServer §8» §7Du hast ein Ticket bekommen");
            } else {
                Sound.error(player);
                p.sendMessage( "§8[§7§l!§8] §fServer §8» §4Du besitzt diese Item bereits!");
            }
            p.closeInventory();
        });

        openInventory();
    }
}
