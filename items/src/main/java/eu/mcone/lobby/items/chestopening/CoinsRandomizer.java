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
    private int coins;

    public CoinsRandomizer(Item item) {
        this.item = item;

        switch (item) {
            case COINS_20: coins = Random.randomInt(20, 50); break;
            case COINS_100: coins = Random.randomInt(100, 150); break;
            case COINS_250: coins = Random.randomInt(250, 350); break;
            case COINS_400: coins = Random.randomInt(400, 500); break;
        }
    }

    public ItemStack getItem() {
        return new ItemBuilder(item.getItemStack().getType(), 1, 0).displayName(item.getLevel().getChatColor()+"§l"+coins+" Coins").lore("§7Kategorie: §bCoins", "§7Seltenheit: "+item.getLevel().getDisplayname()).create();
    }

    public int getCoins() {
        return coins;
    }

}
