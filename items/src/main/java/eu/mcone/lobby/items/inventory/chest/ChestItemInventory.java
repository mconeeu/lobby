/*
 * Copyright (c) 2017 - 2018 Dominik Lippl, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.items.inventory.chest;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.util.ItemBuilder;
import eu.mcone.coresystem.api.core.util.Random;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.enums.Category;
import eu.mcone.lobby.api.enums.Item;
import eu.mcone.lobby.api.enums.Level;
import eu.mcone.lobby.api.player.LobbyPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryView;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;

class ChestItemInventory extends CoreInventory {

    private int changedItems = 0;
    private List<Integer> slots, fadeSlots;
    private BukkitTask setAnimation;
    private BukkitTask fadeAnimation;

    ChestItemInventory(Player p) {
        super("§8Der Zufall entscheidet...", p, InventorySlot.ROW_6);
        this.slots = new ArrayList<>();
        this.fadeSlots = new ArrayList<>();
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p.getUniqueId());

        List<Item> items = new ArrayList<>();

        for (Level level : Level.values()) {
            List<Item> lvlItems = new ArrayList<>();

            for (Item i : Item.values()) {
                if (i.getLevel().equals(level) && !lp.getItems().contains(i)) {
                    if (i.hasCategory() && (i.getCategory().equals(Category.STORY_ITEMS) || i.getCategory().equals(Category.ARMOR) || i.getCategory().equals(Category.EXCLUSIVE))) continue;
                    lvlItems.add(i);
                }
            }

            if (level.getItemAmount() >= 54) {
                int itemAmount = 54;

                for (int i = 0; i < itemAmount; i++) {
                    items.add(lvlItems.get(Random.randomInt(0, lvlItems.size() - 1)));
                }
            } else {
                for (int i = 0; i < level.getItemAmount(); i++) {
                    items.set(Random.randomInt(0, 53), lvlItems.get(Random.randomInt(0, lvlItems.size() - 1)));
                }
            }
        }

        for (int i = 0; i < 54; i++) {
            slots.add(i);
            fadeSlots.add(i);
            setItem(i, ItemBuilder.createSkullItemFromURL("http://textures.minecraft.net/texture/5163dafac1d91a8c91db576caac784336791a6e18d8f7f62778fc47bf146b6", 1).displayName("§7§l???").create());
        }

        setAnimation = Bukkit.getScheduler().runTaskTimerAsynchronously(LobbyPlugin.getInstance(), () -> {
            final int slot = slots.get(Random.randomInt(0, slots.size()-1));
            slots.remove((Integer) slot);

            final Item item = items.get(Random.randomInt(0, items.size()-1));
            final InventoryView inv = p.getOpenInventory();

            if (inv.getTitle().equals(getInventory().getTitle())) {
                inv.setItem(slot, item.getItemStack());
                p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
                changedItems++;

                if (changedItems == 11) {
                    fadeAnimation = Bukkit.getScheduler().runTaskTimerAsynchronously(LobbyPlugin.getInstance(), () -> {
                        if (fadeSlots.size() < 1) {
                            Bukkit.getScheduler().runTaskLater(LobbyPlugin.getInstance(), () -> new ChestFinalInventory(p, item), 20);
                            fadeAnimation.cancel();
                            return;
                        }

                        final int index = Random.randomInt(0, fadeSlots.size() - 1);
                        final int slotInt = fadeSlots.get(index);
                        final InventoryView inv1 = p.getOpenInventory();

                        if (inv1.getTitle().equals(getInventory().getTitle())) {
                            p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
                            fadeSlots.remove(index);

                            if (slotInt != slot)
                                inv1.setItem(slotInt, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, item.getLevel().getGlasSubId()).displayName(item.getLevel().getDisplayname()).create());
                        } else {
                            p.sendMessage("§8[§7§l!§8] §eChestOpening §8  §4Du hast das ChestOpening abgebrochen!");
                            fadeAnimation.cancel();
                        }
                    }, 20, 1);
                    setAnimation.cancel();
                }
            } else {
                p.sendMessage("§8[§7§l!§8] §eChestOpening §8  §4Du hast das ChestOpening abgebrochen!");
                setAnimation.cancel();
            }
        }, 10, 10);

        openInventory();
    }

}
