/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.items.inventory.trader;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.enums.Item;
import eu.mcone.lobby.api.enums.Level;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.items.LobbyItems;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Date;

class DailyItemsInventory extends CoreInventory {

    DailyItemsInventory(Player p) {
        super("§8» §e§lHändler §8| §fTäglicher Shop", p, InventorySlot.ROW_6, InventoryOption.FILL_EMPTY_SLOTS);
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p.getUniqueId());

        setItem(InventorySlot.ROW_1_SLOT_2, new ItemBuilder(Material.IRON_DOOR, 1, 0).displayName("§7§l↩ Zurück").create(), e -> new TraderInventory(p));
        setItem(InventorySlot.ROW_1_SLOT_7, new ItemBuilder(Material.PAPER, 1, 0).displayName("§c§lItems kaufen").lore("§7§oKaufe hier Items mit Coins.", "§7§oDie Items stehen dir danach in", "§7§odeinem Rucksack zur Verfügung.").create());
        setItem(InventorySlot.ROW_1_SLOT_3, new ItemBuilder(Material.EMERALD, 1, 0).displayName("§a§lDein Kontostand").lore("§7§oDein Kontostand beträgt:", "§f§o" + lp.getCorePlayer().getCoins() + "§7§o Coins").create());
        setItem(InventorySlot.ROW_1_SLOT_5, new ItemBuilder(Material.WATCH, 1, 0).displayName("§f§lAktuallisierung").lore("§7§oDer Shop aktuallisiert", "§7§osich in: §f" + getUpdateDate()).create(), e -> updateInventory());

        int i = 19;
        for (Item item : LobbyItems.getInstance().getDailyShopManager().getDailyItems()) {
            if (i == 22) {
                i = 28;
            } else if (i == 31) {
                i = 37;
            }

            int i1 = 33;

            if (item.getLevel().equals(Level.UNUSUAL) || item.getLevel().equals(Level.USUAL)) {
                setItem(i, item.getItemStack(), e -> new DailyItemsBuyInventory(p, item));
            } else {
                setItem(i1, item.getItemStack(), e -> new DailyItemsBuyInventory(p, item));
            }

            i++;
        }

        openInventory();
    }

    private String getUpdateDate() {
        String date = "ERROR";
        long difference = LobbyItems.getInstance().getDailyShopManager().getRefill() - LobbyPlugin.getInstance().getLobbyConfig().parseConfig().getDailyShop();
        Date time = new Date(difference * 1000);
        if (time.getHours() >= 1) {
            date = "§f§l" + time.getHours() + " §7Stunden";
        } else if (time.getMinutes() >= 1) {
            date = "§f§l" + time.getMinutes() + " §7Minuten";
        } else if (time.getSeconds() >= 1) {
            date = "§f§l" + time.getSeconds() + " §7Sekunden";
        }

        return date;
    }

    private void updateInventory() {
        setItem(InventorySlot.ROW_1_SLOT_5, new ItemBuilder(Material.WATCH, 1, 0).displayName("§f§lAktuallisierung").lore("§7§oDer Shop aktuallisiert", "§7§osich in: §f" + getUpdateDate()).create(), e -> getPlayer().updateInventory());
        getPlayer().updateInventory();
    }
}


