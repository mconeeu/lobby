/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.items;

import eu.mcone.coresystem.api.bukkit.config.CoreJsonConfig;
import eu.mcone.lobby.api.LobbyAddon;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.enums.Category;
import eu.mcone.lobby.items.command.ChestCMD;
import eu.mcone.lobby.items.inventory.backpack.*;
import eu.mcone.lobby.items.listener.InventoryTriggerListener;
import eu.mcone.lobby.items.listener.LobbyPlayerLoadedListener;
import eu.mcone.lobby.items.listener.NpcInteractListener;
import eu.mcone.lobby.items.listener.effects.*;
import eu.mcone.lobby.items.manager.TrailManager;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;

public class LobbyItems extends LobbyAddon {

    @Getter
    private static LobbyItems instance;
    @Getter
    private TrailManager trailManager;

    @Override
    public void onEnable() {
        instance = this;
        trailManager = new TrailManager();

        BackpackInventory.registerBackpackInventory(Category.TRAIL, TrailInventory.class);
        BackpackInventory.registerBackpackInventory(Category.GADGET, GadgetInventory.class);
        BackpackInventory.registerBackpackInventory(Category.HAT, HatsInventory.class);
        BackpackInventory.registerBackpackInventory(Category.OUTFITS, OutfitInventory.class);
        BackpackInventory.registerBackpackInventory(Category.ARMOR, ArmorInventory.class);
        BackpackInventory.registerBackpackInventory(Category.MATERIAL, MaterialInventory.class);
        BackpackInventory.registerBackpackInventory(Category.EXCLUSIVE, ExclusiveInventory.class);

        LobbyPlugin.getInstance().registerEvents(
                new BombListener(),
                new CoinBombListener(),
                new EasterGunListener(),
                //new EnderGunListener(),
                new LoveGunListener(),
                new OneHitSwordListener(),
                new SnowGunListener(),
                new InventoryTriggerListener(),
                new LobbyPlayerLoadedListener(),
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
    public void onDisable() {
        trailManager.shutdown();
    }

    @Override
    public void reload() {}

}
