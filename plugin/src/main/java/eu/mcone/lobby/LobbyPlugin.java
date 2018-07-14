/*
 * Copyright (c) 2017 - 2018 Dominik Lippl, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.coresystem.api.bukkit.world.BuildSystem;
import eu.mcone.coresystem.api.bukkit.world.CoreWorld;
import eu.mcone.lobby.api.Lobby;
import eu.mcone.lobby.listener.*;
import eu.mcone.lobby.util.SidebarObjective;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Material;

public class LobbyPlugin extends Lobby {

    @Getter
    private BuildSystem buildSystem;
    @Getter
    private CoreWorld world;

    @Override
    public void onEnable() {
        setInstance(this);

        world = CoreSystem.getInstance().getWorldManager().getWorld("Lobby");
        CoreSystem.getInstance().getTranslationManager().loadCategories(this);

        sendConsoleMessage("§aScoreboard-Scheduler wird gestartet");
        startScheduler();

        sendConsoleMessage("§aBuild-System witd initiiert");
        buildSystem = CoreSystem.getInstance().initialiseBuildSystem(BuildSystem.BuildEvent.BLOCK_BREAK, BuildSystem.BuildEvent.BLOCK_PLACE, BuildSystem.BuildEvent.INTERACT);
        buildSystem.addFilter(BuildSystem.BuildEvent.INTERACT, Material.STONE_BUTTON, Material.WOOD_BUTTON);

        sendConsoleMessage("§aBefehle und Events werden registriert...");
        CoreSystem.getInstance().enableSpawnCommand(world);
        registerEvents();

        sendConsoleMessage("§aVersion §f" + this.getDescription().getVersion() + "§a wurde aktiviert...");

        for (CorePlayer p : CoreSystem.getInstance().getOnlineCorePlayers()) {
            p.getScoreboard().setNewObjective(new SidebarObjective());
            PlayerJoin.setJoinItems(p.bukkit());
        }
    }

    @Override
    public void onDisable(){
        sendConsoleMessage("§cPlugin disabled!");
    }

    private void registerEvents() {
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerInteract(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new CoinsChange(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerFish(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerToggleFlight(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerDropItem(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new WeatherChange(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new InventoryClick(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerPickupItem(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new WeatherChange(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new FoodLevelChange(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new EntitiyDamage(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new EntityDamageByEntity(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerDeath(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerInteractEntity(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerAchievementAwarded(), this);
    }

    private void startScheduler() {
        Bukkit.getScheduler().runTaskTimer(this, SidebarObjective::updateLines, 50L, 100L);
    }

}
