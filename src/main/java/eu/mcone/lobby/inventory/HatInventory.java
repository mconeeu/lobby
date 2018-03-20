/*
 * Copyright (c) 2017 - 2018 Dominik L., Rufus Maiwald, BamDev and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.inventory;

import eu.mcone.coresystem.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.bukkit.util.ItemFactory;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

class HatInventory extends CoreInventory {

    enum Hat {
        CHAINMAIL(4, ItemFactory.createItem(Material.CHAINMAIL_HELMET, 0, 1, "§8» §8§lKetten-Helm", true)),
        IRON(5, ItemFactory.createItem(Material.IRON_HELMET, 0, 1, "§8» §7§lEisen-Helm", true)),
        GOLD(6, ItemFactory.createItem(Material.IRON_HELMET, 0, 1, "§8» §7§lEisen-Helm", true)),
        DIAMOND(7, ItemFactory.createItem(Material.DIAMOND_HELMET, 0, 1, "§8» §b§lDiamant-Helm", true));

        private int slot;
        private ItemStack itemStack;

        Hat(int slot, ItemStack itemStack) {
            this.slot = slot;
            this.itemStack = itemStack;
        }

        public int getSlot() {
            return slot;
        }

        public ItemStack getItemStack() {
            return itemStack;
        }
    }

    HatInventory(Player p) {
        super("§8» §5Hüte", p, 9, Option.FILL_EMPTY_SLOTS);

        setItem(0, ItemFactory.createItem(Material.IRON_DOOR, 0, 1, "§7§l↩ Zurück", true), () -> {
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
            new GadgetsInventory(p);
        });
        setItem(1, ItemFactory.createItem(Material.BARRIER, 0, 1, "§8» §c§lHat entfernen", true), () -> {
            p.getInventory().setHelmet(null);
            p.playSound(p.getLocation(), Sound.ANVIL_BREAK, 1.0F, 1.0F);
            p.closeInventory();
        });

        for (Hat hat : Hat.values()) {
            setItem(hat.getSlot(), hat.getItemStack(), () -> {
                p.getInventory().setHelmet(hat.getItemStack());
                p.playSound(p.getLocation(), org.bukkit.Sound.ARROW_HIT, 1.0F, 1.0F);
                p.closeInventory();
            });
        }

        openInventory();
    }

}
