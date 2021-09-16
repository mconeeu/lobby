/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.api.player.settings;

import eu.mcone.lobby.api.player.hotbar.HotbarCategory;
import eu.mcone.lobby.api.player.hotbar.items.HotbarItem;
import eu.mcone.lobby.api.player.hotbar.items.HotbarSlot;
import eu.mcone.lobby.api.player.scoreboard.ScoreboardWidget;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ToString
public class LobbySettings {

    private boolean allowTrading = true, allowAnimation = true, realTime = true,
            rankBoots = true, inventoryAnimation = true, isStacking = true,
            hotbarChangeSound = true, lobbyGamesInvite = true, scoreboard = true, doNotDisturb = false;
    private SpawnPoint spawnPoint = SpawnPoint.SPAWN;
    private SpawnVillage spawnVillage = SpawnVillage.RAISEN;
    private JoinPlayerVisibility joinPlayerVisibility = JoinPlayerVisibility.NO_PREFERENCE;
    private SoundManager errorSound = SoundManager.NOTE_SNARE_DRUM;
    private SoundManager navigatorSound = SoundManager.ORB_PICKUP;

    private Map<String, HotbarItem> items = new HashMap<>();
    private Map<String, HotbarSlot> slots = new HashMap<>();

    private ScoreboardWidget scoreboardWidgetFirstLine = ScoreboardWidget.getWidgets(1)[0];
    private ScoreboardWidget scoreboardWidgetSecondLine = ScoreboardWidget.getWidgets(2)[0];

    private transient Map<HotbarCategory, HotbarItem> itemsMap;
    private transient Map<HotbarCategory, HotbarSlot> slotsMap;

    public LobbySettings() {
        for (HotbarCategory cat : HotbarCategory.values()) {
            slots.put(cat.toString(), cat.getDefaultSlot());
        }
        for (HotbarCategory item : HotbarCategory.values()) {
            if (item.isCanBeModified()) {
                items.put(item.toString(), item.getDefaultItem());
            }
        }
    }

    public void updateSlot(HotbarCategory category, HotbarSlot slot) {
        HotbarSlot slotBefore = slotsMap.get(category);
        HotbarCategory newSlotItem = getCategoryOfSlot(slot);

        if (slotsMap == null) {
            calculateSlots();
        }

        this.slotsMap.put(category, slot);
        this.slots.put(category.toString(), slot);

        this.slotsMap.put(newSlotItem, slotBefore);
        this.slots.put(newSlotItem.toString(), slotBefore);
    }

    public void updateItem(HotbarCategory category, HotbarItem slot) {
        if (category.isCanBeModified()) {
            if (itemsMap == null) {
                calculateItems();
            }

            items.put(category.toString(), slot);
            itemsMap.put(category, slot);
        } else throw new IllegalArgumentException("This Item cannot be modified!");
    }

    public Map<HotbarCategory, HotbarItem> calculateItems() {
        if (itemsMap == null) {
            itemsMap = new HashMap<>();
            for (Map.Entry<String, HotbarItem> entry : items.entrySet()) {
                itemsMap.put(HotbarCategory.valueOf(entry.getKey()), entry.getValue());
            }
        }

        return itemsMap;
    }

    public Map<HotbarCategory, HotbarSlot> calculateSlots() {
        if (slotsMap == null) {
            slotsMap = new HashMap<>();
            for (Map.Entry<String, HotbarSlot> entry : slots.entrySet()) {
                slotsMap.put(HotbarCategory.valueOf(entry.getKey()), entry.getValue());
            }
        }

        return slotsMap;
    }

    public HotbarCategory getCategoryOfSlot(HotbarSlot slot) {
        for (Map.Entry<HotbarCategory, HotbarSlot> entry : slotsMap.entrySet()) {
            if (entry.getValue().equals(slot)) {
                return entry.getKey();
            }
        }

        return null;
    }

    public void resetSlotsAndItems() {
        slots.clear();
        for (HotbarCategory cat : HotbarCategory.values()) {
            slots.put(cat.toString(), cat.getDefaultSlot());
        }

        if (slotsMap != null) {
            slotsMap.clear();
            for (HotbarCategory cat : HotbarCategory.values()) {
                slotsMap.put(cat, cat.getDefaultSlot());
            }
        }


        items.clear();
        for (HotbarCategory item : HotbarCategory.values()) {
            if (item.isCanBeModified()) {
                items.put(item.toString(), item.getDefaultItem());
            }
        }

        if (itemsMap != null) {
            itemsMap.clear();
            for (HotbarCategory item : HotbarCategory.values()) {
                if (item.isCanBeModified()) {
                    itemsMap.put(item, item.getDefaultItem());
                }
            }
        }
    }

}
