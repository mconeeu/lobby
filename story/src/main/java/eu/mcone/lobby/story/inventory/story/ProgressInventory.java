/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.story.inventory.story;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.util.ItemBuilder;
import eu.mcone.coresystem.api.core.exception.CoreException;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.enums.Progress;
import eu.mcone.lobby.api.player.LobbyPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class ProgressInventory extends CoreInventory {

    public ProgressInventory(Player p) {
        super("§8» §3§lStory §8| §fFortschritt", p, InventorySlot.ROW_3, Option.FILL_EMPTY_SLOTS);
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p.getUniqueId());

        int i = 0;
        int progressId = lp.getProgressId();

        for (Progress progress : Progress.values()) {
            if (progress.getId() <= progressId) {
                try {
                    setItem(i,
                            ItemBuilder.createSkullItem(
                                    CoreSystem.getInstance().getPlayerUtils().getSkinFromSkinDatabase(progress.getSkinName()),
                                    1
                            ).displayName(progress.getName()).lore(progress.getDescription()).create()
                    );
                } catch (CoreException e) {
                    e.printStackTrace();
                }
            } else {
                setItem(i, new ItemBuilder(Material.SKULL_ITEM, 1, 0).displayName("§7§l???").create());
            }
            i++;
        }

        setItem(InventorySlot.ROW_3_SLOT_9, new ItemBuilder(Material.IRON_DOOR, 1, 0).displayName("§7§l↩ Zurück").create(), e -> Bukkit.dispatchCommand(p, "/profile"));

        openInventory();
    }
}


