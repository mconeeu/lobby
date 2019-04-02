/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.items.inventory.bank;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.coresystem.api.bukkit.util.ItemBuilder;
import org.bukkit.entity.Player;

public class BankMenInventory extends CoreInventory {

    public BankMenInventory(Player p) {
        super("§8» §d§lBänker §8| §fTägliche Gutschrift", p, InventorySlot.ROW_3, Option.FILL_EMPTY_SLOTS);
        CorePlayer cp = CoreSystem.getInstance().getCorePlayer(p);

        setItem(InventorySlot.ROW_1_SLOT_1, ItemBuilder.createSkullItemFromURL("http://textures.minecraft.net/texture/5163dafac1d91a8c91db576caac784336791a6e18d8f7f62778fc47bf146b6", 1).displayName("§d§lBänker").lore("§7§oBeim Bänker kannst du dein Bank Konto", "§7§oVerwalten für das passende Kleingeld.", "§7§oDie meisten Items erhälst du").create());

        setItem(InventorySlot.ROW_2_SLOT_5, ItemBuilder.createSkullItemFromURL("http://textures.minecraft.net/texture/f5612dc7b86d71afc1197301c15fd979e9f39e7b1f41d8f1ebdf8115576e2e", 1).displayName("§d§lHole dir deine Belohnung ab").create(),
        e -> {

            p.closeInventory();
            p.sendMessage("Haha");
        });


        openInventory();
    }
}

