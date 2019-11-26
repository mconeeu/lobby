/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.items;

import eu.mcone.gamesystem.api.enums.Category;
import eu.mcone.lobby.api.LobbyAddon;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.items.command.*;
import eu.mcone.lobby.items.inventory.vendor.*;
import eu.mcone.lobby.items.listener.InventoryTriggerListener;
import eu.mcone.lobby.items.listener.LobbyPlayerLoadedListener;
import eu.mcone.lobby.items.listener.NpcInteractListener;
import eu.mcone.lobby.items.listener.effects.*;
import eu.mcone.lobby.items.manager.DailyShopManager;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;

public class LobbyItems extends LobbyAddon {

    @Getter
    private static LobbyItems instance;
    @Getter
    private DailyShopManager dailyShopManager;

    @Override
    public void onEnable() {
        instance = this;
        dailyShopManager = new DailyShopManager();

        LobbyPlugin.getInstance().registerEvents(
                new BombListener(),
                new CoinBombListener(),
                new EasterGunListener(),
                //new EnderGunListener(),
                new LoveGunListener(),
                new OneHitSwordListener(),
                new SnowGunListener(),
                new MagicWandListener(),
                new InventoryTriggerListener(),
                new LobbyPlayerLoadedListener(),
                new NpcInteractListener(),
                new CobwebGub(),
                new EnderPearlListener(),
                new SplashPotionListener()
        );

        VendorInventory.registerVendorInventory(Category.TRAIL, TrailInventory.class);
        VendorInventory.registerVendorInventory(Category.GADGET, GadgetInventory.class);
        VendorInventory.registerVendorInventory(Category.OUTFITS, OutfitInventory.class);
        VendorInventory.registerVendorInventory(Category.ANIMAL, AnimalInventory.class);
        VendorInventory.registerVendorInventory(Category.INGAME, IngameInventory.class);
        VendorInventory.registerVendorInventory(Category.HAT, HatsInventory.class);


        LobbyPlugin.getInstance().registerCommands(new ChestCMD());
        LobbyPlugin.getInstance().registerCommands(new ItemCMD());

        final Location rewardBlock = new Location(Bukkit.getWorld("Lobby-OneIsland"), 46.5, 102D, -33.5);
        Bukkit.getScheduler().runTaskTimerAsynchronously(LobbyPlugin.getInstance(), () -> {
            for (int i = 0; i < 6; i++) {
                Bukkit.getWorld("Lobby-OneIsland").playEffect(rewardBlock, Effect.LAVA_POP, 20);
            }
        }, 24, 24);

        reload();
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void reload() {
    }

}
