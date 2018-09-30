/*
 * Copyright (c) 2017 - 2018 Dominik Lippl, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.items.inventory.backpack;

import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.util.ItemBuilder;
import eu.mcone.lobby.api.enums.Category;
import eu.mcone.lobby.api.enums.Item;
import eu.mcone.lobby.items.manager.OutfitFactory;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class OutfitInventory extends BackpackInventory {

    public OutfitInventory(Player p, Integer site) {
        super(Category.OUTFITS, site, p);
    }

    @Override
    protected void setItems(Player p) {
        for (Item item : Item.values()) {
            if (item.hasCategory() && item.getCategory().equals(Category.OUTFITS) && playerHasItem(item)) {
                addItem(item.getItemStack(), e -> {
                    OutfitFactory.setOutfit(p, item);
                    p.closeInventory();
                    p.sendMessage("§8[§7§l!§8] §fRucksack §8» §7Du hast das Outfit " + item.getName() + "§7 angezogen!");
                });
            }
        }

        setItem(InventorySlot.ROW_6_SLOT_8, new ItemBuilder(Material.BARRIER).displayName("§c§lOutfit ausziehen").lore("§7§oFalls du eines deiner Outfits", "§7§oangezogen hast, kannst Du es", "§7§ohiermit ausziehen.").create(), e -> {
            p.getInventory().setArmorContents(null);
            p.sendMessage("§8[§7§l!§8] §fRucksack §8» §7Du hast dein Outfit erfolgreich ausgezogen!");
            p.closeInventory();
        });
    }

}
