/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.items.inventory.chest;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.gamemode.Gamemode;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.item.Skull;
import eu.mcone.gameapi.api.backpack.BackpackItem;
import eu.mcone.gameapi.api.backpack.Category;
import eu.mcone.gameapi.api.backpack.Level;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.player.LobbyPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

class ChestFinalInventory extends CoreInventory {

    ChestFinalInventory(Player p, Category category, BackpackItem item) {
        super("§8» §e§lDein Gewinn", p, InventorySlot.ROW_6, InventoryOption.FILL_EMPTY_SLOTS);
        LobbyPlayer lp = LobbyPlugin.getInstance().getGamePlayer(p);

        lp.removeChests(1);

        if (category != null && !category.getGamemode().equals(Gamemode.UNDEFINED)) {
            setItem(InventorySlot.ROW_1_SLOT_5, Skull.fromUrl("http://textures.minecraft.net/texture/2dadd755d08537352bf7a93e3bb7dd4d733121d39f2fb67073cd471f561194dd", 1).toItemBuilder()
                    .displayName("§e§lDein Gewinn:")
                    .lore("§7§oDu hast ein Item für den",
                            "§7§oSpielmodus: " + category.getGamemode(),
                            "§7§omit dem,",
                            "§7§oLevel " + item.getLevel().getDisplayname(),
                            "§7§ogewonnen!",
                            "",
                            "§7Du kannst dieses Item in dem jeweiligen Spielmodus",
                            "§7benutzen bzw. einlösen!")
                    .create()
            );
        } else {
            setItem(InventorySlot.ROW_1_SLOT_5, Skull.fromUrl("http://textures.minecraft.net/texture/2dadd755d08537352bf7a93e3bb7dd4d733121d39f2fb67073cd471f561194dd", 1).toItemBuilder()
                    .displayName("§e§lDein Gewinn:")
                    .lore("§7§oDu hast ein Item mit dem",
                            "§7§oLevel " + item.getLevel().getDisplayname(),
                            "§7§ogewonnen!").create());
        }

        setItem(InventorySlot.ROW_3_SLOT_3, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, item.getLevel().getGlasSubId()).displayName("§8//MCONE//").create());
        setItem(InventorySlot.ROW_3_SLOT_4, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, item.getLevel().getGlasSubId()).displayName("§8//MCONE//").create());
        setItem(InventorySlot.ROW_3_SLOT_5, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, item.getLevel().getGlasSubId()).displayName("§8//MCONE//").create());
        setItem(InventorySlot.ROW_3_SLOT_6, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, item.getLevel().getGlasSubId()).displayName("§8//MCONE//").create());
        setItem(InventorySlot.ROW_3_SLOT_7, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, item.getLevel().getGlasSubId()).displayName("§8//MCONE//").create());

        setItem(InventorySlot.ROW_4_SLOT_5, item.getItem());
        if (category == null) {
            CoreSystem.getInstance().getCorePlayer(p).addEmeralds(item.getSellPrice());
        } else {
            lp.addBackpackItem(category.getName(), item);
        }

        setItem(InventorySlot.ROW_5_SLOT_3, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, item.getLevel().getGlasSubId()).displayName("§8//MCONE//").create());
        setItem(InventorySlot.ROW_5_SLOT_4, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, item.getLevel().getGlasSubId()).displayName("§8//MCONE//").create());
        setItem(InventorySlot.ROW_5_SLOT_5, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, item.getLevel().getGlasSubId()).displayName("§8//MCONE//").create());
        setItem(InventorySlot.ROW_5_SLOT_6, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, item.getLevel().getGlasSubId()).displayName("§8//MCONE//").create());
        setItem(InventorySlot.ROW_5_SLOT_7, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, item.getLevel().getGlasSubId()).displayName("§8//MCONE//").create());

        setItem(InventorySlot.ROW_6_SLOT_1, new ItemBuilder(Material.CHEST, 1, 0).displayName("§aNoch eine Kiste öffnen").lore("§7§oDu hast noch " + lp.getChests() + " Kisten!").create(), e -> {
            if (lp.getChests() > 0) {
                new ChestItemInventory(p);
            } else {
                p.closeInventory();
                p.sendMessage("§8[§7§l!§8] §eChestOpening §8»§7 Du hast nicht genügend Kisten!");

            }
        });

        if (item.getLevel().equals(Level.EPIC) || item.getLevel().equals(Level.LEGENDARY)) {
            Bukkit.getServer().broadcastMessage(
                    "\n§8§m------§r §8[§7§l!§8] §eChestOpening§r §8§m------" +
                            "\n§8» §f" + item.getLevel().getDisplayname() + "er§7 Gewinn!" +
                            "\n§8» §7Spieler: §f" + p.getName() +
                            "\n§8» §7Item: §f" + item.getName() +
                            "\n§8§m------§r §8[§7§l!§8] §eChestOpening§r §8§m------"
            );
        }

        p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 1);
        p.playSound(p.getLocation(), Sound.ANVIL_BREAK, 1, 1);

        openInventory();
    }

}
