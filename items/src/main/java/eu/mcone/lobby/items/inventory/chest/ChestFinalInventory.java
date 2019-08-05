/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.items.inventory.chest;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.item.Skull;
import eu.mcone.gamesystem.api.game.player.GamePlayer;
import eu.mcone.gamesystem.api.lobby.cards.ItemCard;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.enums.Item;
import eu.mcone.lobby.api.enums.Level;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.items.chestopening.CoinsRandomizer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashSet;
import java.util.UUID;

class ChestFinalInventory extends CoreInventory {

    ChestFinalInventory(Player p, UUID uuid) {
        super("§8» §e§lDein Gewinn", p, InventorySlot.ROW_6, InventoryOption.FILL_EMPTY_SLOTS);
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p.getUniqueId());
        GamePlayer gamePlayer = LobbyPlugin.getInstance().getGamePlayer(p.getUniqueId());

        Level level = Level.UNUSUAL;
        Item item = ChestItemInventory.items.getOrDefault(uuid, null);
        ItemCard itemCard = ChestItemInventory.itemCards.getOrDefault(uuid, null);

        if (item != null) {
            level = item.getLevel();
        } else if (itemCard != null) {
            level = itemCard.getLevel();
        }

        lp.removeChests(1);

        if (itemCard != null) {
            setItem(InventorySlot.ROW_1_SLOT_5, Skull.fromUrl("http://textures.minecraft.net/texture/2dadd755d08537352bf7a93e3bb7dd4d733121d39f2fb67073cd471f561194dd", 1).toItemBuilder()
                    .displayName("§e§lDein Gewinn:")
                    .lore("§7§oDu hast ein Itemkarte für den",
                            "§7§oSpielmodus: " + itemCard.getGamemode(),
                            "§7§omit dem,",
                            "§7§oLevel " + level.getDisplayname(),
                            "§7§ogewonnen!",
                            "",
                            "§7Du kannst diese Itemkarte beim Händler in der Lobby einlösen,",
                            "§7und diese im anschluss daran,",
                            "§7in dem jeweilligen Spielmodus benutzen!").create());
        } else {
            setItem(InventorySlot.ROW_1_SLOT_5, Skull.fromUrl("http://textures.minecraft.net/texture/2dadd755d08537352bf7a93e3bb7dd4d733121d39f2fb67073cd471f561194dd", 1).toItemBuilder()
                    .displayName("§e§lDein Gewinn:")
                    .lore("§7§oDu hast ein Item mit dem",
                            "§7§oLevel " + level.getDisplayname(),
                            "§7§ogewonnen!").create());
        }

        setItem(InventorySlot.ROW_3_SLOT_3, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, level.getGlasSubId()).create());
        setItem(InventorySlot.ROW_3_SLOT_4, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, level.getGlasSubId()).create());
        setItem(InventorySlot.ROW_3_SLOT_5, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, level.getGlasSubId()).create());
        setItem(InventorySlot.ROW_3_SLOT_6, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, level.getGlasSubId()).create());
        setItem(InventorySlot.ROW_3_SLOT_7, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, level.getGlasSubId()).create());

        if (item != null) {
            if (new HashSet<>(Arrays.asList(Item.COINS_20, Item.COINS_100, Item.COINS_250, Item.COINS_400)).contains(item)) {
                CoinsRandomizer randomizer = new CoinsRandomizer(item);
                CoreSystem.getInstance().getCorePlayer(p).addCoins(randomizer.getCoins());

                setItem(InventorySlot.ROW_4_SLOT_5, randomizer.getItem());
            } else {
                setItem(InventorySlot.ROW_4_SLOT_5, item.getItemStack());
                gamePlayer.addItem(item);
            }
        } else if (itemCard != null) {
            setItem(InventorySlot.ROW_4_SLOT_5, itemCard.getItemCardBuilder().createStack());
            gamePlayer.addItemCard(itemCard);
        }


        setItem(InventorySlot.ROW_5_SLOT_3, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, level.getGlasSubId()).create());
        setItem(InventorySlot.ROW_5_SLOT_4, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, level.getGlasSubId()).create());
        setItem(InventorySlot.ROW_5_SLOT_5, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, level.getGlasSubId()).create());
        setItem(InventorySlot.ROW_5_SLOT_6, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, level.getGlasSubId()).create());
        setItem(InventorySlot.ROW_5_SLOT_7, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, level.getGlasSubId()).create());
        setItem(InventorySlot.ROW_6_SLOT_1, new ItemBuilder(Material.CHEST, 1, 0)
                .displayName("§aNoch eine Kiste öffnen")
                .lore("§7§oDu hast noch " + lp.getChests() + " Kisten!")
                .create(), e ->

        {
            if (lp.getChests() > 0) {
                new ChestItemInventory(p);
            } else {
                p.closeInventory();
                p.sendMessage("§8[§7§l!§8] §eChestOpening §8»§7 Du hast nicht genügend Kisten!");

            }
        });

        if (level.equals(Level.EPIC) || level.equals(Level.LEGENDARY)) {
            Bukkit.getServer().broadcastMessage(
                    "\n§8§m------§r §8[§7§l!§8] §eChestOpening§r §8§m------" +
                            "\n§8» §f" + level.getDisplayname() + "er§7 Gewinn!" +
                            "\n§8» §7Spieler: §f" + p.getName() +
                            "\n§8» §7Item: §f" + "Test" +
                            "\n§8§m------§r §8[§7§l!§8] §eChestOpening§r §8§m------"
            );
        }

        p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 1);
        p.playSound(p.getLocation(), Sound.ANVIL_BREAK, 1, 1);

        openInventory();
    }

}
