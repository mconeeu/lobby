/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.items.chestopening;

import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.core.util.Random;
import eu.mcone.gamesystem.api.enums.Item;
import org.bukkit.inventory.ItemStack;

public class CoinsRandomizer {

    private Item item;
    private int emeralds;

    public CoinsRandomizer(Item item) {
        this.item = item;

        switch (item) {
            case EMERALDS_20:
                emeralds = Random.randomInt(20, 30);
                break;
            case EMERALDS_100:
                emeralds = Random.randomInt(80, 120);
                break;
            case EMERALDS_250:
                emeralds = Random.randomInt(150, 270);
                break;
            case EMERALDS_400:
                emeralds = Random.randomInt(300, 375);
                break;
        }
    }

    public ItemStack getItem() {
        return new ItemBuilder(item.getItemStack().getType(), 1, 0).displayName(item.getLevel().getChatColor() + "§l" + emeralds + " Coins").lore("§7Kategorie: §bEmeralds", "§7Seltenheit: " + item.getLevel().getDisplayname()).create();
    }

    public int getEmeralds() {
        return emeralds;
    }

}
