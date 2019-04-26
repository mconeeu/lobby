/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.story;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.npc.NPC;
import eu.mcone.coresystem.api.bukkit.npc.enums.NpcVisibilityMode;
import eu.mcone.coresystem.api.bukkit.util.ItemBuilder;
import eu.mcone.lobby.api.LobbyAddon;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.enums.Category;
import eu.mcone.lobby.api.enums.Progress;
import eu.mcone.lobby.items.inventory.backpack.BackpackInventory;
import eu.mcone.lobby.story.inventory.backpack.StoryItemInventory;
import eu.mcone.lobby.story.inventory.story.ProgressInventory;
import eu.mcone.lobby.story.listener.InventoryTriggerListener;
import eu.mcone.lobby.story.listener.LobbyPlayerLoadedListener;
import eu.mcone.lobby.story.listener.NpcListener;
import eu.mcone.lobby.story.listener.SecretSignsListener;
import lombok.Getter;
import org.bukkit.Material;

public class LobbyStory extends LobbyAddon {

    @Getter
    private static LobbyStory instance;

    @Override
    public void onEnable() {
        instance = this;

        BackpackInventory.registerBackpackInventory(Category.STORY_ITEMS, StoryItemInventory.class);

        LobbyPlugin.getInstance().registerEvents(
                new LobbyPlayerLoadedListener(),
                new NpcListener(),
                new InventoryTriggerListener(),
                new SecretSignsListener()
        );

        CoreSystem.getInstance().modifyProfileInventory((coreInventory, player) -> {
            coreInventory.setItem(
                    InventorySlot.ROW_5_SLOT_3,
                    new ItemBuilder(Material.BOOK, 1, 0).displayName("§c§lStory Fortschritt").lore("§7§oHier siehst Du welche Kapitel", "§7§oDu bereits bestanden hast", "", "§8» §f§nLinksklick§8 | §7§oAnsehen").create(),
                    e -> new ProgressInventory(player)
            );
            coreInventory.setItem(
                    InventorySlot.ROW_5_SLOT_7,
                    new ItemBuilder(Material.SIGN, 1, 0).displayName("§6§lSecrets").lore("§7§oHier siehst du alle Secrets,", "§7§odie Du bereits gefunden hast", "", "§4Comming Soon").create()
            );
        });

        reload();
    }

    @Override
    public void reload() {
        for (Progress progress : Progress.values()) {
            NPC npc = progress.getNpc();
            npc.toggleNpcVisibility(NpcVisibilityMode.WHITELIST);
        }
    }

    @Override
    public void onDisable() {}

}
