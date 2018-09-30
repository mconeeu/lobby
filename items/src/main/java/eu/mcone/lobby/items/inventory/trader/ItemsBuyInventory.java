/*
 * Copyright (c) 2017 - 2018 Dominik Lippl, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.items.inventory.trader;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.util.ItemBuilder;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.enums.Item;
import eu.mcone.lobby.api.player.LobbyPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashSet;

class ItemsBuyInventory extends CoreInventory {

    ItemsBuyInventory(Player p) {
        super("§8» §e§lHändler §8| §fItems kaufen", p, InventorySlot.ROW_4, Option.FILL_EMPTY_SLOTS);
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p.getUniqueId());

        setItem(InventorySlot.ROW_1_SLOT_3, new ItemBuilder(Material.IRON_DOOR, 1, 0).displayName("§7§l↩ Zurück").create(), e -> new TraderInventory(p));
        setItem(InventorySlot.ROW_1_SLOT_5, new ItemBuilder(Material.PAPER, 1, 0).displayName("§c§lItems kaufen").lore("§7§oKaufe hier Items mit Coins.", "§7§oDie Items stehen dir danach in", "§7§odeinem Rucksack zur Verfügung.").create());
        setItem(InventorySlot.ROW_1_SLOT_7, new ItemBuilder(Material.EMERALD, 1, 0).displayName("§a§lDein Kontostand").lore("§7§oDein Kontostand beträgt:", "§f§o"+lp.getCorePlayer().getCoins()+"§7§o Coins").create());

        int i = 19;
        for (Item item : Item.values()) {
            if (i == 26) i = 28;
            else if (i == 35) i = 37;

            if (new HashSet<>(Arrays.asList(Item.LOVEGUN, Item.EASTERGUN, Item.SNOWGUN, Item.ENDERGUN, Item.HEAD_GERMAN, Item.HEAD_MELON, Item.TRAIL_MUSIC)).contains(item)) {
                setItem(i, item.getItemStack(), e -> {
                    if (!lp.getItems().contains(item)) {
                        if ((lp.getCorePlayer().getCoins() - item.getCoins()) >= 0) {
                            lp.getCorePlayer().removeCoins(item.getCoins());
                            lp.addItem(item);
                            p.sendMessage("§8[§7§l!§8] §eHändler §8» §2Du hast das Item §a"+item.getName()+" §2für §f"+ item.getCoins() +" Coins §2erfolgreich gekauft!");
                        } else {
                            p.sendMessage(CoreSystem.getInstance().getTranslationManager().get("lobby.prefix") + "Du hast nicht genügen §6§lCoins!");
                        }
                        p.closeInventory();
                    } else {
                        p.sendMessage(CoreSystem.getInstance().getTranslationManager().get("lobby.prefix") + "§4Du besitzt diese Item bereits!");
                    }
                });
                i++;
            }
        }

        openInventory();
    }

}


