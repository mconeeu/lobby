/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.gang.inventory;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.item.Skull;
import eu.mcone.lobby.api.gang.Gang;
import org.bukkit.Material;
import org.bukkit.entity.Player;

class GangPlayerInventory extends CoreInventory {

    GangPlayerInventory(Player p, Gang gang, String name, String uuid) {
        super(Gang.getPrefix(gang, uuid) + name + "§7 Einsttelungen", p, InventorySlot.ROW_3);

        setItem(InventorySlot.ROW_1_SLOT_5, new Skull(name, 1).toItemBuilder().displayName(Gang.getPrefix(gang, uuid) + name).create());

        setItem(InventorySlot.ROW_2_SLOT_3, new ItemBuilder(Material.BARRIER, 1, 0).displayName("§6Spieler rauswerfen").create(),
                e -> {
                    p.closeInventory();
                    gang.kickPlayer(p, name);
                    p.sendMessage(Gang.GANG_PREFIX + "Du hast " + name + "§7aus der Gang raus geworfen");
                });


        if (!gang.getMods().contains(uuid)) {
            setItem(InventorySlot.ROW_2_SLOT_7, new ItemBuilder(Material.FIREWORK, 1, 0).displayName("§aPromote").lore("§7§oPromote den Spieler zum §2Moderator").create(),
                    e -> {
                        gang.promotePlayer(p, name);
                        p.closeInventory();
                        p.sendMessage(Gang.GANG_PREFIX + "Du hast " + name + "§7 zum Moderator befördert");
                    }

            );


        } else {
            setItem(InventorySlot.ROW_3_SLOT_5, new ItemBuilder(Material.REDSTONE_BLOCK, 1, 0).displayName("§cDemote").lore("§7§oDemote den Moderator zum Mitglied").create(),
                    e -> {
                        gang.demotePlayer(p, name);
                        p.closeInventory();
                        p.sendMessage(Gang.GANG_PREFIX + "Du hast " + name + " §7zum Mitglied herrabgestuft");
                    }
            );
        }

        openInventory();
    }
}




