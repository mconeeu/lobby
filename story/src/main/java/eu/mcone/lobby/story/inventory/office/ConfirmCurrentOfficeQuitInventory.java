/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.story.inventory.office;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class ConfirmCurrentOfficeQuitInventory extends CoreInventory {

    public ConfirmCurrentOfficeQuitInventory(Player invited, Player owner, Runnable runnable) {
        super("§c§lBüro wechseln?", invited, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);

        setItem(
                InventorySlot.ROW_1_SLOT_5,
                new ItemBuilder(Material.TNT)
                        .displayName("§c§lAktuelles Büro verlassen")
                        .lore("§7§oUm die Einladung anzunehmen,", "§7§omusst du dein aktuelles Büro", "§7§overlassen!")
                        .create()
        );

        setItem(
                InventorySlot.ROW_3_SLOT_4,
                new ItemBuilder(Material.INK_SACK, 1, DyeColor.RED.getDyeData())
                        .displayName("§c§lAbbrechen")
                        .lore("§7§oEinladung ignorieren")
                        .create(),
                e -> invited.closeInventory()
        );
        setItem(
                InventorySlot.ROW_3_SLOT_6,
                new ItemBuilder(Material.INK_SACK, 1, DyeColor.LIME.getDyeData())
                        .displayName("§a§lBüro wechseln")
                        .lore("§7§oDu wechselst in das Büro", "§7§ovon §f§o"+owner.getName())
                        .create(),
                e -> runnable.run()
        );

        openInventory();
    }

}
