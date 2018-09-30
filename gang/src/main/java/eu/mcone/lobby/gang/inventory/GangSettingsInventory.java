/*
 * Copyright (c) 2017 - 2018 Dominik Lippl, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.gang.inventory;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.lobby.api.gang.Gang;
import org.bukkit.entity.Player;

class GangSettingsInventory extends CoreInventory {

   GangSettingsInventory(Player p, Gang gang) {
       super("§7Gang Settings", p, InventorySlot.ROW_4, Option.FILL_EMPTY_SLOTS);



       openInventory();
   }

}
