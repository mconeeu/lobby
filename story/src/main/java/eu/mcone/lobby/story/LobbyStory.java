/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.story;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.inventory.ProfileInventoryModifier;
import eu.mcone.coresystem.api.bukkit.util.ItemBuilder;
import eu.mcone.lobby.api.LobbyAddon;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.enums.Category;
import eu.mcone.lobby.items.inventory.backpack.BackpackInventory;
import eu.mcone.lobby.story.inventory.backpack.StoryItemInventory;
import eu.mcone.lobby.story.inventory.story.ProgressInventory;
import eu.mcone.lobby.story.listener.LobbyPlayerLoaded;
import eu.mcone.lobby.story.listener.NpcInteract;
import eu.mcone.lobby.story.listener.PlayerInteract;
import eu.mcone.lobby.story.listener.SignChange;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class LobbyStory extends LobbyAddon {

    @Getter
    private static LobbyStory instance;

    @Override
    public void onEnable() {
        instance = this;

        BackpackInventory.registerBackpackInventory(Category.STORY_ITEMS, StoryItemInventory.class);

        LobbyPlugin.getInstance().registerEvents(
                new LobbyPlayerLoaded(),
                new NpcInteract(),
                new PlayerInteract(),
                new SignChange()
        );

        CoreSystem.getInstance().modifyProfileInventory(new ProfileInventoryModifier() {
            @Override
            public void onCreate(CoreInventory coreInventory, Player player) {
                coreInventory.setItem(InventorySlot.ROW_5_SLOT_3, new ItemBuilder(Material.BOOK, 1, 0).displayName("§c§lStory Fortschritt").lore("§7§oHier siehst Du welche Kapitel", "§7§oDu bereits bestanden hast", "", "§8» §f§nLinksklick§8 | §7§oAnsehen").create(), e -> new ProgressInventory(player));
                coreInventory.setItem(InventorySlot.ROW_5_SLOT_7, new ItemBuilder(Material.SIGN, 1, 0).displayName("§6§lSecrets").lore("§7§oHier findest du alle Secrets,", "§7§odie Du bereits gefunden hast", "", "§4Comming Soon").create());
            }
        });

        reload();
    }

    @Override
    public void reload() {}

    @Override
    public void onDisable() {}

}
