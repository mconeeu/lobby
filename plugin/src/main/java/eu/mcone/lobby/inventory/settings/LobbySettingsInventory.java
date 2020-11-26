/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.inventory.settings;

import eu.mcone.coresystem.api.bukkit.facades.Sound;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.lobby.inventory.LobbyProfileInventory;
import eu.mcone.lobby.inventory.settings.hotbar.LobbyHotbarSettingsInventory;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class LobbySettingsInventory extends CoreInventory {

    public LobbySettingsInventory(Player p) {
        super("§8» §c§lLobby Einstellungen", p, InventorySlot.ROW_4, InventoryOption.FILL_EMPTY_SLOTS);

        setItem(
                InventorySlot.ROW_2_SLOT_2,
                new ItemBuilder(Material.BOOK, 1, 0).displayName("§f§lPersönlich").create(),
                e -> {
                    new LobbyPersonalSettingsInventory(p);
                    Sound.click(p);
                });

        setItem(
                InventorySlot.ROW_2_SLOT_4,
                new ItemBuilder(Material.ENCHANTMENT_TABLE, 1, 0).displayName("§f§lInventar Sortierung").create(),
                e -> {
                    new LobbyHotbarSettingsInventory(p);
                    Sound.click(p);
                });

        setItem(
                InventorySlot.ROW_2_SLOT_6,
                new ItemBuilder(Material.IRON_SWORD, 1, 0).displayName("§f§lAnzeige").create(),
                e -> {
                    new LobbyMinigamesSettingsInventory(p);
                    Sound.click(p);
                });

        setItem(
                InventorySlot.ROW_2_SLOT_8,
                new ItemBuilder(Material.TRIPWIRE_HOOK, 1, 0).displayName("§f§lScoreboard-Widgets").create(),
                e -> {
                    new ScoreboardWidgetsInventory(p);
                    Sound.click(p);
                });

        setItem(InventorySlot.ROW_4_SLOT_5, BACK_ITEM, e -> {
            Sound.error(p);
            new LobbyProfileInventory(p);
        });

        openInventory();
    }
}

