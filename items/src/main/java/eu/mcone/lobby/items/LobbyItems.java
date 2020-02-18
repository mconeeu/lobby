/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.items;

import eu.mcone.coresystem.api.bukkit.gamemode.Gamemode;
import eu.mcone.gameapi.api.backpack.BackpackInventoryListener;
import eu.mcone.gameapi.api.backpack.BackpackItem;
import eu.mcone.lobby.api.LobbyAddon;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.enums.LobbyCategory;
import eu.mcone.lobby.api.enums.LobbyItem;
import eu.mcone.lobby.items.backpack.StoryBackpackInventoryListener;
import eu.mcone.lobby.items.command.ChestCMD;
import eu.mcone.lobby.items.listener.InventoryTriggerListener;
import eu.mcone.lobby.items.listener.NpcInteractListener;
import eu.mcone.lobby.items.listener.effects.MagicWandListener;
import eu.mcone.lobby.items.listener.effects.OneHitSwordListener;
import eu.mcone.lobby.items.manager.DailyShopManager;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;

import java.util.HashSet;
import java.util.Set;

public class LobbyItems extends LobbyAddon {

    @Getter
    private static LobbyItems instance;
    @Getter
    private DailyShopManager dailyShopManager;

    @Override
    public void onEnable() {
        instance = this;
        dailyShopManager = new DailyShopManager();

        for (LobbyCategory category : LobbyCategory.values()) {
            Set<BackpackItem> items = new HashSet<>();

            for (LobbyItem item : LobbyItem.values()) {
                if (item.getCategory() != null && item.getCategory().equals(category)) {
                    items.add(new eu.mcone.gameapi.api.backpack.BackpackItem(
                            item.getId(),
                            item.getName(),
                            item.getLevel(),
                            item.getItemStack(),
                            false,
                            false,
                            false,
                            item.getBuyPrice(),
                            item.getSellPrice()
                    ));
                }
            }

            LobbyPlugin.getInstance().getBackpackManager().registerCategory(
                    new eu.mcone.gameapi.api.backpack.Category(
                            category.name(),
                            "",
                            false,
                            //Dont show ECONOMY category in BackpackInventory
                            true,
                            category.getSorting(),
                            Gamemode.UNDEFINED,
                            category.getItem()
                    ),
                    items,
                    getInventoryListener(category)
            );
        }

        LobbyPlugin.getInstance().registerEvents(
                new OneHitSwordListener(),
                new MagicWandListener(),
                new InventoryTriggerListener(),
                new NpcInteractListener()
        );

        LobbyPlugin.getInstance().registerCommands(new ChestCMD());

        final Location rewardBlock = new Location(Bukkit.getWorld("Lobby-OneIsland"), 46.5, 102D, -33.5);
        Bukkit.getScheduler().runTaskTimerAsynchronously(LobbyPlugin.getInstance(), () -> {
            for (int i = 0; i < 6; i++) {
                Bukkit.getWorld("Lobby-OneIsland").playEffect(rewardBlock, Effect.LAVA_POP, 20);
            }
        }, 24, 24);

        reload();
    }

    @Override
    public void onDisable() {}

    @Override
    public void reload() {}

    private static BackpackInventoryListener getInventoryListener(LobbyCategory category) {
        switch (category) {
            case STORY_ITEMS: return new StoryBackpackInventoryListener();
            default: return null;
        }
    }

}
