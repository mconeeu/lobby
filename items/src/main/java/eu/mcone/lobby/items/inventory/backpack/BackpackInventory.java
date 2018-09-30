/*
 * Copyright (c) 2017 - 2018 Dominik Lippl, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */


package eu.mcone.lobby.items.inventory.backpack;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.CoreItemEvent;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.util.ItemBuilder;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.enums.Category;
import eu.mcone.lobby.api.enums.Item;
import eu.mcone.lobby.api.player.LobbyPlayer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public abstract class BackpackInventory extends CoreInventory {

    private static Map<Category, Class<? extends BackpackInventory>> inventories = new HashMap<>();

    private List<Item> ownItems;
    private List<BackpackItem> items;
    private int site;

    protected BackpackInventory(Category category, int site, Player p) {
        super("§8» §3§lRucksack §8| §f"+category.getName(), p, InventorySlot.ROW_6);
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p.getUniqueId());

        this.ownItems = lp.getItems();
        this.items = new ArrayList<>();
        this.site = site;

        int start = 0, end = 5, x = 0;
        List<Integer> slots = new ArrayList<>(Arrays.asList(0, 9, 18, 27, 36, 45));
        List<Category> categories = new ArrayList<>(Arrays.asList(Category.values()));

        if (category.getId() > 4) {
            start = 5;
            end = 11;

            setItem(InventorySlot.ROW_6_SLOT_1, ItemBuilder.createSkullItemFromURL("http://textures.minecraft.net/texture/a156b31cbf8f774547dc3f9713a770ecc5c727d967cb0093f26546b920457387", 1).displayName("§f§lHoch").create(), e ->
                    openNewInventory(Category.STORY_ITEMS, p));
        } else {
            setItem(InventorySlot.ROW_6_SLOT_1, ItemBuilder.createSkullItemFromURL("http://textures.minecraft.net/texture/fe3d755cecbb13a39e8e9354823a9a02a01dce0aca68ffd42e3ea9a9d29e2df2", 1).displayName("§f§lRunter").create(), e ->
                    openNewInventory(Category.ANIMAL, p));
        }
        if (end > categories.size()) end = categories.size();

        for (int i = start; i < end; i++) {
            if (categories.size() >= i) {
                Category cat = categories.get(i);
                ItemStack categoryItem = cat.getItem().clone();

                if (cat.equals(category)) {
                    ItemMeta itemMeta = categoryItem.getItemMeta();
                    itemMeta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
                    itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

                    categoryItem.setItemMeta(itemMeta);
                }

                setItem(slots.get(x), categoryItem, e -> openNewInventory(cat, p));
                x++;
            } else {
                break;
            }
        }

        setItem(1, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
        setItem(2, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
        setItem(3, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
        setItem(4, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
        setItem(5, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
        setItem(6, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
        setItem(7, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
        setItem(8, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());

        setItem(10, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
        setItem(17, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());

        setItem(19, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
        setItem(26, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());

        setItem(28, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
        setItem(35, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());

        setItem(37, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
        setItem(38, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
        setItem(39, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
        setItem(40, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
        setItem(41, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
        setItem(42, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
        setItem(43, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
        setItem(44, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());

        setItem(46, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
        setItem(47, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
        setItem(48, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
        setItem(49, ItemBuilder.createSkullItemFromURL("http://textures.minecraft.net/texture/3ebf907494a935e955bfcadab81beafb90fb9be49c7026ba97d798d5f1a23", 1).displayName("§7Vorherige Seite").create(), e -> {
            if (site-1 < 0) {
                p.playSound(p.getLocation(), Sound.NOTE_BASS, 1, 1);
            } else {
                try {
                    getClass().getDeclaredConstructor(Player.class, Integer.class).newInstance(p, site-1);
                } catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException ex) {
                    ex.printStackTrace();
                }
            }
        });
        setItem(50, ItemBuilder.createSkullItemFromURL("http://textures.minecraft.net/texture/1b6f1a25b6bc199946472aedb370522584ff6f4e83221e5946bd2e41b5ca13b", 1).displayName("§7Nächste Seite").create(), e -> {
            if ((items.size()/18) < (site+1)) {
                p.playSound(p.getLocation(), Sound.NOTE_BASS, 1, 1);
            } else {
                try {
                    getClass().getDeclaredConstructor(Player.class, Integer.class).newInstance(p, site + 1);
                } catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException ex) {
                    ex.printStackTrace();
                }
            }
        });
        setItem(51, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
        setItem(52, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
        setItem(53, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());

        setItems(p);
        p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
        openInventory();
    }

    public static void registerBackpackInventory(Category category, Class<? extends BackpackInventory> inventory) {
        inventories.put(category, inventory);
    }

    public static void openNewInventory(Category cat, Player p) {
        try {
            inventories.get(cat).getDeclaredConstructor(Player.class, Integer.class).newInstance(p, 0);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | NullPointerException e) {
            System.err.println("Inventory for Category " + cat + " could be found or opened: " + e.getMessage());
        }
    }

    protected void addItem(ItemStack item, CoreItemEvent event) {
        items.add(new BackpackItem(item, event));
    }

    @Override
    public void openInventory() {
        int i = 11;
        for (int itemId = site*18; itemId < site*18+18; itemId++) {
            if (i == 17) i = 20;
            else if (i == 26) i = 29;

            if (itemId <= items.size()-1){
                BackpackItem item = items.get(itemId);
                setItem(i, item.getItemStack(), item.getItemEvent());

                i++;
            } else {
                break;
            }
        }

        getPlayer().openInventory(getInventory());
    }

    @AllArgsConstructor
    @Getter @Setter
    private class BackpackItem {
        private ItemStack itemStack;
        private CoreItemEvent itemEvent;
    }

    protected boolean playerHasItem(Item item) {
        return ownItems.contains(item) || getPlayer().hasPermission("group.admin");
    }

    protected abstract void setItems(Player p);

}