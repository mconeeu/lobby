/*
 * Copyright (c) 2017 - 2018 Dominik Lippl, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.items;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.lobby.api.LobbyAddon;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.enums.Category;
import eu.mcone.lobby.items.inventory.backpack.*;
import eu.mcone.lobby.items.listener.AsyncPlayerChat;
import eu.mcone.lobby.items.listener.LobbyPlayerLoaded;
import eu.mcone.lobby.items.listener.NpcInteract;
import eu.mcone.lobby.items.listener.PlayerInteract;
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

        this.trailManager = new TrailManager();

        BackpackInventory.registerBackpackInventory(Category.TRAIL, TrailInventory.class);
        BackpackInventory.registerBackpackInventory(Category.GADGET, GadgetInventory.class);
        BackpackInventory.registerBackpackInventory(Category.HAT, HatsInventory.class);
        BackpackInventory.registerBackpackInventory(Category.OUTFITS, OutfitInventory.class);
        BackpackInventory.registerBackpackInventory(Category.ARMOR, ArmorInventory.class);
        BackpackInventory.registerBackpackInventory(Category.MATERIAL, MaterialInventory.class);
        BackpackInventory.registerBackpackInventory(Category.EXCLUSIVE, ExclusiveInventory.class);

        LobbyPlugin.getInstance().registerEvents(
                new Bomb(),
                new CoinBomb(),
                new EasterGun(),
                new EnderGun(),
                new LoveGun(),
                new OneHitSword(),
                new SnowGun(),
                new AsyncPlayerChat(),
                new LobbyPlayerLoaded(),
                new NpcInteract(),
                new PlayerInteract()
        );

        CoreSystem.getInstance().setPlayerChatDisabled(true);

        final Location rewardBlock = new Location(Bukkit.getWorld("Lobby"), 46.5, 102D, -33.5);
        Bukkit.getScheduler().runTaskTimerAsynchronously(LobbyPlugin.getInstance(), () -> {
            Bukkit.getWorld("Lobby").playEffect(rewardBlock, Effect.LAVA_POP, 20);
            Bukkit.getWorld("Lobby").playEffect(rewardBlock, Effect.LAVA_POP, 20);
            Bukkit.getWorld("Lobby").playEffect(rewardBlock, Effect.LAVA_POP, 20);
            Bukkit.getWorld("Lobby").playEffect(rewardBlock, Effect.LAVA_POP, 20);
            Bukkit.getWorld("Lobby").playEffect(rewardBlock, Effect.LAVA_POP, 20);
            Bukkit.getWorld("Lobby").playEffect(rewardBlock, Effect.LAVA_POP, 20);
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
