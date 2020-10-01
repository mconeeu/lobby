/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.api.player.settings;

import eu.mcone.lobby.api.player.hotbar.HotbarGeneralCategorys;
import eu.mcone.lobby.api.player.hotbar.items.enums.HotbarItemEnum;
import eu.mcone.lobby.api.player.hotbar.items.enums.SlotAmountEnum;
import eu.mcone.lobby.api.player.scoreboard.widgets.ScoreboardWidgets;
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

    private Map<String, HotbarItemEnum> items = new HashMap<>();
    private Map<String, SlotAmountEnum> slots = new HashMap<>();

    private ScoreboardWidgets scoreboardWidgetsFirstLine = ScoreboardWidgets.COINS;
    private ScoreboardWidgets scoreboardWidgetsSecondLine = ScoreboardWidgets.EMERALD;

    private transient Map<HotbarGeneralCategorys, HotbarItemEnum> itemsMap;
    private transient Map<HotbarGeneralCategorys, SlotAmountEnum> slotsMap;

    public LobbySettings() {
        for (HotbarGeneralCategorys cat : HotbarGeneralCategorys.values()) {
            slots.put(cat.toString(), cat.getDefaultSlot());
        }
        for (HotbarGeneralCategorys item : HotbarGeneralCategorys.values()) {
            if (item.isCanBeModified()) {
                items.put(item.toString(), item.getDefaultItem());
            }
        }
    }

    public void updateSlot(HotbarGeneralCategorys category, SlotAmountEnum slot) {
        SlotAmountEnum slotBefore = slotsMap.get(category);
        HotbarGeneralCategorys newSlotItem = getCategoryOfSlot(slot);

        if (slotsMap == null) {
            calculateSlots();
        }

        this.slotsMap.put(category, slot);
        this.slots.put(category.toString(), slot);

        this.slotsMap.put(newSlotItem, slotBefore);
        this.slots.put(newSlotItem.toString(), slotBefore);
    }

    public void updateItem(HotbarGeneralCategorys category, HotbarItemEnum slot) {
        if (category.isCanBeModified()) {
            if (itemsMap == null) {
                calculateItems();
            }

            items.put(category.toString(), slot);
            itemsMap.put(category, slot);
        } else throw new IllegalArgumentException("This Item cannot be modified!");
    }

    public Map<HotbarGeneralCategorys, HotbarItemEnum> calculateItems() {
        if (itemsMap == null) {
            itemsMap = new HashMap<>();
            for (Map.Entry<String, HotbarItemEnum> entry : items.entrySet()) {
                itemsMap.put(HotbarGeneralCategorys.valueOf(entry.getKey()), entry.getValue());
            }
        }

        return itemsMap;
    }

    public Map<HotbarGeneralCategorys, SlotAmountEnum> calculateSlots() {
        if (slotsMap == null) {
            slotsMap = new HashMap<>();
            for (Map.Entry<String, SlotAmountEnum> entry : slots.entrySet()) {
                slotsMap.put(HotbarGeneralCategorys.valueOf(entry.getKey()), entry.getValue());
            }
        }

        return slotsMap;
    }

    public HotbarGeneralCategorys getCategoryOfSlot(SlotAmountEnum slot) {
        for (Map.Entry<HotbarGeneralCategorys, SlotAmountEnum> entry : slotsMap.entrySet()) {
            if (entry.getValue().equals(slot)) {
                return entry.getKey();
            }
        }

        return null;
    }

    public void resetSlotsAndItems() {
        slots.clear();
        for (HotbarGeneralCategorys cat : HotbarGeneralCategorys.values()) {
            slots.put(cat.toString(), cat.getDefaultSlot());
        }

        if (slotsMap != null) {
            slotsMap.clear();
            for (HotbarGeneralCategorys cat : HotbarGeneralCategorys.values()) {
                slotsMap.put(cat, cat.getDefaultSlot());
            }
        }


        items.clear();
        for (HotbarGeneralCategorys item : HotbarGeneralCategorys.values()) {
            if (item.isCanBeModified()) {
                items.put(item.toString(), item.getDefaultItem());
            }
        }

        if (itemsMap != null) {
            itemsMap.clear();
            for (HotbarGeneralCategorys item : HotbarGeneralCategorys.values()) {
                if (item.isCanBeModified()) {
                    itemsMap.put(item, item.getDefaultItem());
                }
            }
        }
    }

}
