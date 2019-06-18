/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.items.inventory.backpack;

import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.enums.Category;
import eu.mcone.lobby.api.enums.Item;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class HatsInventory extends BackpackInventory {

    public HatsInventory(Player p, Integer site) {
        super(Category.HAT, site, p);
    }

    protected void setItems(Player p) {
        for (Item item : Item.values()) {
            if (item.hasCategory() && item.getCategory().equals(Category.HAT) && playerHasItem(item)) {
                addItem(item.getItemStack(), e -> {
                    p.getInventory().setHelmet(item.getItemStack());
                    p.closeInventory();
                    p.sendMessage("§8[§7§l!§8] §fRucksack §8» §7Du hast den Kopf "+item.getName()+"§7 aufgesetzt!");
                });
            }
        }

        setItem(InventorySlot.ROW_6_SLOT_8, new ItemBuilder(Material.BARRIER).displayName("§c§lKopf absetzen").lore("§7§oFalls du einen deiner Köpfe", "§7§oaufgesetzt hast, kannst Du ihn", "§7§ohiermit absetzen.").create(), e -> {
            p.getInventory().setHelmet(null);
            LobbyPlugin.getInstance().getMessager().send(p, "§2Du hast deinen Kopf erfolgreich abgesetzt!");
        });
    }

}

