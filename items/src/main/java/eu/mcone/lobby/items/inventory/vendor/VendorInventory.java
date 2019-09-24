/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.items.inventory.vendor;

import eu.mcone.coresystem.api.bukkit.inventory.category.StaticClassCategoryInventory;
import eu.mcone.gamesystem.api.GameTemplate;
import eu.mcone.gamesystem.api.enums.Category;
import eu.mcone.gamesystem.api.enums.Item;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public abstract class VendorInventory extends StaticClassCategoryInventory {

    private static final HashSet<Category> categories = new HashSet<>(Arrays.asList(

            Category.ANIMAL,
            Category.GADGET,
            Category.HAT,
            Category.OUTFITS,
            Category.TRAIL,
            Category.INGAME
    ));

    private static final Map<Category, Class<? extends VendorInventory>> inventories = new HashMap<>();
    private final List<Item> ownItems;

    public VendorInventory(Player p) {
        super("§8» §3§lItems verkaufen", p);
        this.ownItems = GameTemplate.getInstance().getGamePlayer(p.getUniqueId()).getItems();

        for (Category cat : categories) {
            addCategoryWithInventoryClass(cat.getItem(), inventories.get(cat));
        }
    }

    protected boolean playerHasItem(Item item) {
        return ownItems.contains(item);
    }

    public static void registerVendorInventory(Category category, Class<? extends VendorInventory> inventory) {
        inventories.put(category, inventory);
    }

    public static void openNewInventory(Category category, Player player) {
        try {
            inventories.get(category).getConstructor(Player.class).newInstance(player).openInventory();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

}
