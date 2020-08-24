/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.story.inventory.story;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.item.Skull;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.story.progress.StoryProgress;
import eu.mcone.lobby.api.player.LobbyPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class StoryProgressInventory extends CoreInventory {

    public StoryProgressInventory(Player p) {
        super("§8» §3§lOneIsland-Story", p, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);

        int i = 0;
        int progressId = lp.getProgressId();

        for (StoryProgress storyProgress : StoryProgress.values()) {
            if (storyProgress.getId() <= progressId) {
                setItem(i,
                        new Skull(
                                storyProgress.getNpc().getSkin(),
                                1
                        ).toItemBuilder().displayName(storyProgress.getName()).lore(storyProgress.getDescription()).create()
                );
            } else {
                setItem(i, new ItemBuilder(Material.SKULL_ITEM, 1, 0).displayName("§7§l???").create());
            }
            i++;
        }

        setItem(InventorySlot.ROW_3_SLOT_9, BACK_ITEM, e -> new StoryOverviewInventory(player));
        openInventory();
    }
}


