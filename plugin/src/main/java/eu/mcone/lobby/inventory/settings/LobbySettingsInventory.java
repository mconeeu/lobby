/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.inventory.settings;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.inventory.LobbyProfileInventory;
import eu.mcone.lobby.inventory.settings.hotbar.LobbyHotbarSettingsInventory;
import eu.mcone.lobby.inventory.settings.scoreboard.ScoreboardWidgetsInventory;
import eu.mcone.lobby.inventory.settings.shortcuts.ShortCutsInventory;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class LobbySettingsInventory extends CoreInventory {

    public LobbySettingsInventory(Player p) {
        super("§8» §c§lLobby Einstellungen", p, InventorySlot.ROW_5, InventoryOption.FILL_EMPTY_SLOTS);
        setItem(InventorySlot.ROW_2_SLOT_3, new ItemBuilder(Material.GRASS, 1, 0).displayName("§f§lWelt").create(), inventoryClickEvent -> {
            new LobbyWorldSettingsInventory(p);
            LobbyPlugin.getInstance().getPlayerSounds().playSounds(p, Sound.CHICKEN_EGG_POP);
        });

        setItem(InventorySlot.ROW_2_SLOT_7, new ItemBuilder(Material.BOOK, 1, 0).displayName("§f§lPersönlich").create(), inventoryClickEvent -> {
            new LobbyPersonalSettingsInventory(p);
            LobbyPlugin.getInstance().getPlayerSounds().playSounds(p, Sound.CHICKEN_EGG_POP);
        });

        setItem(InventorySlot.ROW_4_SLOT_2, new ItemBuilder(Material.PAPER, 1, 0).displayName("§f§lShortcuts").create(), inventoryClickEvent -> {
            new ShortCutsInventory(p);
            LobbyPlugin.getInstance().getPlayerSounds().playSounds(p, Sound.CHICKEN_EGG_POP);
        });

        setItem(InventorySlot.ROW_4_SLOT_4, new ItemBuilder(Material.ENCHANTMENT_TABLE, 1, 0).displayName("§f§lInventar Sortierung").create(), inventoryClickEvent -> {
            new LobbyHotbarSettingsInventory(p);
            LobbyPlugin.getInstance().getPlayerSounds().playSounds(p, Sound.CHICKEN_EGG_POP);
        });

        setItem(InventorySlot.ROW_4_SLOT_6, new ItemBuilder(Material.IRON_SWORD, 1, 0).displayName("§f§lLobby-Games").create(), inventoryClickEvent -> {
            new LobbyMinigamesSettingsInventory(p);
            LobbyPlugin.getInstance().getPlayerSounds().playSounds(p, Sound.CHICKEN_EGG_POP);
        });

        setItem(InventorySlot.ROW_4_SLOT_8, new ItemBuilder(Material.TRIPWIRE_HOOK, 1, 0).displayName("§f§lScoreboard-Widgets").create(), inventoryClickEvent -> {
            new ScoreboardWidgetsInventory(p);
            LobbyPlugin.getInstance().getPlayerSounds().playSounds(p, Sound.CHICKEN_EGG_POP);
        });

        setItem(InventorySlot.ROW_5_SLOT_9, BACK_ITEM, e -> new LobbyProfileInventory(p));

        openInventory();
    }
}

