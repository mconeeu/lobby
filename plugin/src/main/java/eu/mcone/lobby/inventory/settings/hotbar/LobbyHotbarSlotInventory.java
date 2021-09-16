/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.inventory.settings.hotbar;

import eu.mcone.coresystem.api.bukkit.facades.Sound;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.api.player.hotbar.HotbarCategory;
import eu.mcone.lobby.api.player.hotbar.items.HotbarSlot;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class LobbyHotbarSlotInventory extends CoreInventory {

    public LobbyHotbarSlotInventory(Player p, HotbarCategory category) {
        super("§fWähle dein Slot aus:", p, InventorySlot.ROW_2, InventoryOption.FILL_EMPTY_SLOTS);
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);
        HotbarSlot currentSlot = lp.getSettings().calculateSlots().get(category);

        for (HotbarSlot slot : HotbarSlot.values()) {
            if (!currentSlot.equals(slot)) {
                setItem(
                        slot.getSlot(),
                        slot.getItemStack(),
                        e -> {
                            lp.getSettings().updateSlot(category, slot);
                            lp.saveData();

                            LobbyPlugin.getInstance().getHotbarSettings().updateInventory(p, lp);
                            Sound.click(p);

                            new LobbyHotbarSettingsInventory(p);
                        }
                );
            } else {
                setItem(
                        slot.getSlot(),
                        new ItemBuilder(Material.BARRIER).create()
                );
            }
        }

        setItem(InventorySlot.ROW_2_SLOT_9, BACK_ITEM, e ->
                new LobbyHotbarSettingsInventory(p));

        Sound.done(p);
        openInventory();
    }

}
