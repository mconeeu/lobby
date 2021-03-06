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
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.api.items.LobbyCategory;
import eu.mcone.lobby.api.items.LobbyItem;
import eu.mcone.lobby.items.backpack.StoryBackpackInventoryListener;
import eu.mcone.lobby.items.command.ChestCMD;
import eu.mcone.lobby.items.command.LiveEventCMD;
import eu.mcone.lobby.items.listener.InventoryTriggerListener;
import eu.mcone.lobby.items.listener.effects.CompassListener;
import eu.mcone.lobby.items.listener.effects.MagicWandListener;
import eu.mcone.lobby.items.listener.effects.OneHitSwordListener;
import eu.mcone.lobby.items.manager.DailyShopManager;
import eu.mcone.lobby.items.manager.LobbyLiveEventManager;
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
    @Getter
    private LobbyLiveEventManager liveEventManager;

    @Override
    public void onEnable(LobbyPlugin plugin) {
        instance = this;
        dailyShopManager = new DailyShopManager();
        liveEventManager = new LobbyLiveEventManager();

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

            plugin.getBackpackManager().registerCategory(
                    new eu.mcone.gameapi.api.backpack.Category(
                            category.name(),
                            "",
                            false,
                            true,
                            category.getSorting(),
                            Gamemode.UNDEFINED,
                            category.getItem()
                    ),
                    items,
                    getInventoryListener(category)
            );
        }

        plugin.registerEvents(
                new OneHitSwordListener(),
                new MagicWandListener(),
                new CompassListener(),
                new InventoryTriggerListener()
        );

        plugin.registerCommands(
                new ChestCMD(),
                new LiveEventCMD()
        );

        final Location rewardBlock = new Location(LobbyWorld.ONE_ISLAND.getWorld().bukkit(), 46.5, 102D, -33.5);
        final Location rewardBlock2 = new Location(LobbyWorld.ONE_ISLAND.getWorld().bukkit(), -25.5, 100.8D, -113.4);
        Bukkit.getScheduler().runTaskTimerAsynchronously(LobbyPlugin.getInstance(), () -> {
            for (int i = 0; i < 6; i++) {
                LobbyWorld.ONE_ISLAND.getWorld().bukkit().playEffect(rewardBlock, Effect.LAVA_POP, 20);
                LobbyWorld.ONE_ISLAND.getWorld().bukkit().playEffect(rewardBlock2, Effect.LAVA_POP, 20);
            }
        }, 24, 24);
    }

    @Override
    public void onDisable(LobbyPlugin plugin) { }

    @Override
    public void reload(LobbyPlugin plugin) { }

    private static BackpackInventoryListener getInventoryListener(LobbyCategory category) {
        switch (category) {
            case STORY_ITEMS:
                return new StoryBackpackInventoryListener();
            default:
                return null;
        }
    }

}
