/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.items.inventory.chestopening;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.item.Skull;
import eu.mcone.gameapi.api.backpack.BackpackItem;
import eu.mcone.gameapi.api.backpack.Category;
import eu.mcone.gameapi.api.backpack.Level;
import eu.mcone.gameapi.api.backpack.defaults.DefaultCategory;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.player.LobbyPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.*;

class ChestItemInventory extends CoreInventory {

    private static final String[] ALLOWED_CATEGORIES = new String[]{
            DefaultCategory.HAT.name(), DefaultCategory.GADGET.name(), DefaultCategory.OUTFIT.name(), DefaultCategory.TRAIL.name(), DefaultCategory.PET.name()
    };
    private static final Map<Level, Integer> PROBABILITIES;
    private static final Map<BackpackItem, Category> ALLOWED_ITEMS;

    static {
        Map<BackpackItem, Category> items = new HashMap<>();
        for (Map.Entry<Category, Set<BackpackItem>> categoryItems : LobbyPlugin.getInstance().getBackpackManager().getBackpackItems(ALLOWED_CATEGORIES).entrySet()) {
            for (BackpackItem item : categoryItems.getValue()) {
                items.put(item, categoryItems.getKey());
            }
        }
        ALLOWED_ITEMS = items;

        Map<Level, Integer> probabilities = new HashMap<>();
        int usualAmount = 0;
        for (BackpackItem item : ALLOWED_ITEMS.keySet()) {
            if (item.getLevel().equals(Level.USUAL)) {
                usualAmount++;
            }
        }
        for (Level level : Level.values()) {
            if (level.equals(Level.MYSTICAL)) break;

            probabilities.put(level, usualAmount);
            usualAmount -= usualAmount / 2;
        }
        PROBABILITIES = probabilities;
    }

    private final List<Integer> slots, fadeSlots;
    private final Map<Integer, BackpackItem> changedItems;
    private BukkitTask setAnimation;
    private BukkitTask fadeAnimation;

    ChestItemInventory(Player p) {
        super("§8Der Zufall entscheidet...", p, InventorySlot.ROW_6);
        this.slots = new ArrayList<>();
        this.fadeSlots = new ArrayList<>();
        this.changedItems = new HashMap<>();
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);

        List<BackpackItem> items = new ArrayList<>();

        for (Level level : new Level[]{Level.UNUSUAL, Level.USUAL, Level.LEGENDARY, Level.EPIC}) {
            List<BackpackItem> levelItems = new ArrayList<>();

            for (Map.Entry<BackpackItem, Category> categoryItem : ALLOWED_ITEMS.entrySet()) {
                if (categoryItem.getKey().getLevel().equals(level) && !lp.getGamePlayer().hasBackpackItem(categoryItem.getValue().getName(), categoryItem.getKey())) {
                    levelItems.add(categoryItem.getKey());
                }
            }

            for (int i = 0; i < PROBABILITIES.get(level); i++) {
                BackpackItem item = levelItems.size() > 0 ? levelItems.get(new Random().nextInt(levelItems.size())) : getRandomizedEmeraldsItem(level);
                levelItems.remove(item);

                items.add(item);
            }
        }

        for (int i = 0; i < 54; i++) {
            slots.add(i);
            fadeSlots.add(i);
            setItem(i, Skull.fromUrl("http://textures.minecraft.net/texture/5163dafac1d91a8c91db576caac784336791a6e18d8f7f62778fc47bf146b6", 1).toItemBuilder().displayName("§7§l???").create());
        }

        setAnimation = Bukkit.getScheduler().runTaskTimerAsynchronously(LobbyPlugin.getInstance(), () -> {
            final int randomIndex = new Random().nextInt(slots.size() - 1);
            final int slot = slots.get(randomIndex);
            slots.remove(randomIndex);

            final int item = new Random().nextInt(items.size());
            final BackpackItem backpackItem = items.get(item);
            getInventory().setItem(slot, items.get(item).getItem());
            items.remove(item);

          LobbyPlugin.getInstance().getPlayerSounds().playSounds(p, Sound.CHICKEN_EGG_POP);
            changedItems.put(slot, backpackItem);

            if (!p.getOpenInventory().getTitle().equals(getInventory().getTitle())) {
                openInventory();
            }

            if (changedItems.size() >= 11) {
                final Map.Entry<Integer, BackpackItem> wonItem = getWonItem(changedItems);

                if (wonItem != null) {
                    fadeSlots.remove(wonItem.getKey());

                    fadeAnimation = Bukkit.getScheduler().runTaskTimerAsynchronously(LobbyPlugin.getInstance(), () -> {
                        if (fadeSlots.size() < 1) {
                            Bukkit.getScheduler().runTaskLater(LobbyPlugin.getInstance(), () -> new ChestFinalInventory(p, !wonItem.getValue().getName().contains(" Emerald") ? ALLOWED_ITEMS.get(wonItem.getValue()) : null, wonItem.getValue()), 20);
                            fadeAnimation.cancel();
                            return;
                        }

                        final int index = fadeSlots.size() > 1 ? new Random().nextInt(fadeSlots.size() - 1) : 0;
                        final int slotInt = fadeSlots.get(index);

                        if (!p.getOpenInventory().getTitle().equals(getInventory().getTitle())) {
                            openInventory();
                        }

                         LobbyPlugin.getInstance().getPlayerSounds().playSounds(p, Sound.ORB_PICKUP);
                        fadeSlots.remove(index);
                        getInventory().setItem(slotInt, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, wonItem.getValue().getLevel().getGlasSubId()).create());
                    }, 20, 1);

                    setAnimation.cancel();
                } else {
                    throw new IllegalStateException("WonItem cannot be null!");
                }
            }
        }, 10, 10);

        openInventory();
    }

    private static BackpackItem getRandomizedEmeraldsItem(Level level) {
        switch (level) {
            case USUAL: return new BackpackItem(-1, "10+ Emeralds", Level.USUAL, new ItemBuilder(Material.GOLD_NUGGET, 1, 0).displayName("§7§lEmerald-Loot ").lore("§7Kategorie: §bEmeralds", "§7Seltenheit: §7Gewöhnlich").create(), true, false, true, 0, eu.mcone.coresystem.api.core.util.Random.randomInt(10, 20));
            case UNUSUAL: return new BackpackItem(-1, "20+ Emeralds", Level.USUAL, new ItemBuilder(Material.GOLD_INGOT, 1, 0).displayName("§7§lEmerald-Loot ").lore("§7Kategorie: §bEmeralds", "§7Seltenheit: §3Ungewöhnlich").create(), true, false, true, 0,  eu.mcone.coresystem.api.core.util.Random.randomInt(20, 35));
            case LEGENDARY: return new BackpackItem(-1, "50+ Emeralds", Level.USUAL, new ItemBuilder(Material.GOLD_BLOCK, 1, 0).displayName("§7§lEmerald-Loot ").lore("§7Kategorie: §bEmeralds", "§7Seltenheit: §5Episch").create(), true, false, true, 0,  eu.mcone.coresystem.api.core.util.Random.randomInt(50, 75));
            case EPIC: return new BackpackItem(-1, "100+ Emeralds", Level.USUAL, new ItemBuilder(Material.DIAMOND_BLOCK, 1, 0).displayName("§7§lEmerald-Loot ").lore("§7Kategorie: §bEmeralds", "§7Seltenheit: §6Legendär").create(), true, false, true, 0,  eu.mcone.coresystem.api.core.util.Random.randomInt(100, 150));
            default: return null;
        }
    }

    private static Map.Entry<Integer, BackpackItem> getWonItem(Map<Integer, BackpackItem> changedItems) {
        final int randomIndex = new Random().nextInt(changedItems.size() - 1);
        Map.Entry<Integer, BackpackItem> wonItem = null;

        int i = 0;
        for (Map.Entry<Integer, BackpackItem> entry : changedItems.entrySet()) {
            if (i == randomIndex) {
                return entry;
            }
            i++;
        }

        return null;
    }

}
