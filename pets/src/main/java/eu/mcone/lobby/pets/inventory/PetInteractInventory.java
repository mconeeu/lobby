/*
 * Copyright (c) 2017 - 2018 Dominik Lippl, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.pets.inventory;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.util.ItemBuilder;
import eu.mcone.lobby.pets.LobbyPets;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class PetInteractInventory extends CoreInventory {

    public PetInteractInventory(Player p, Entity entity) {
        super("§8» §b§l"+entity.getCustomName(), p, InventorySlot.ROW_3, Option.FILL_EMPTY_SLOTS);

        setItem(InventorySlot.ROW_2_SLOT_3, new ItemBuilder(Material.BARRIER, 1, 0).displayName("§c§lTier in den Stall schicken").lore("§7§oDein Tier verschindet. Du kannst", "§7§oes jederzeit wieder über", "§7§odeinen Rucksack zurückholen.", "", "§8» §f§nLinksklick§8 | §7§oAusblenden").create(), e -> {
                LobbyPets.getInstance().despawnPet(p);
                p.closeInventory();
        });
        setItem(InventorySlot.ROW_2_SLOT_5, new ItemBuilder(Material.NAME_TAG, 1, 0).displayName("§f§lTier umbennen").lore("§7§oDu kannst Tier beliebig oft umbennen!", "§7§oBitte beachte, dass der Name", "§7§onur 16 Zeichen lang sein darf!", "", "§8» §f§nLinksklick§8 | §7§oUmbennen").create(),
                e -> LobbyPets.getInstance().renamePet(p, ""));
        setItem(InventorySlot.ROW_2_SLOT_7, new ItemBuilder(Material.SADDLE, 1, 0).displayName("§e§lTier reiten").lore("§7§oKlicke, um auf deinem Haustier zu reiten!", "§7§oDu bist mit ihm etwas schneller als normal!", "", "§8» §f§nLinksklick§8 | §7§oReiten").create(), e -> {
            LobbyPets.getInstance().ride(p);
            p.closeInventory();
        });

        openInventory();
    }

}
