/*
 * Copyright (c) 2017 - 2018 Dominik L., Rufus Maiwald, BamDev and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.inventory;

import eu.mcone.coresystem.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.bukkit.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

class HatInventory extends CoreInventory {

    enum Hat {
        CHAINMAIL(4, new ItemBuilder(Material.CHAINMAIL_HELMET, 1, 0).displayName("§8» §8§lKetten-Helm").create()),
        IRON(5, new ItemBuilder(Material.IRON_HELMET, 1, 0).displayName("§8» §7§lEisen-Helm").create()),
        GOLD(6, new ItemBuilder(Material.IRON_HELMET, 1, 0).displayName("§8» §7§lEisen-Helm").create()),
        DIAMOND(7, new ItemBuilder(Material.DIAMOND_HELMET, 1, 0).displayName("§8» §b§lDiamant-Helm").create());

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

        setItem(0, new ItemBuilder(Material.IRON_DOOR, 1, 0).displayName("§7§l↩ Zurück").create(), () -> {
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
            new GadgetsInventory(p);
        });
        setItem(1, new ItemBuilder(Material.BARRIER, 1, 0).displayName("§8» §c§lHat entfernen").create(), () -> {
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
