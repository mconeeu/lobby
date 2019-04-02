/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.inventory.ProfileInventoryModifier;
import eu.mcone.coresystem.api.bukkit.util.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.world.BuildSystem;
import eu.mcone.coresystem.api.bukkit.world.CoreWorld;
import eu.mcone.lobby.api.LobbyAddon;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.api.event.LobbyPlayerLoadedEvent;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.command.LobbyCMD;
import eu.mcone.lobby.gang.LobbyGang;
import eu.mcone.lobby.inventory.LobbySettingsInventory;
import eu.mcone.lobby.items.LobbyItems;
import eu.mcone.lobby.listener.*;
import eu.mcone.lobby.pets.LobbyPets;
import eu.mcone.lobby.story.LobbyStory;
import eu.mcone.lobby.util.SidebarObjective;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.*;

public class Lobby extends LobbyPlugin {

    @Getter
    private static Lobby instance;

    @Getter
    private BuildSystem buildSystem;
    @Getter
    private List<LobbyPlayer> players;
    @Getter
    private Map<LobbyWorld, CoreWorld> worlds;

    public final static List<LobbyAddon> ADDONS = new ArrayList<>(Arrays.asList(
            new LobbyGang(), new LobbyItems(), new LobbyPets(), new LobbyStory()
    ));

    @Override
    public void onEnable() {
        instance = this;

        players = new ArrayList<>();
        worlds = new HashMap<>();
        for (LobbyWorld w : LobbyWorld.values()) worlds.put(w, CoreSystem.getInstance().getWorldManager().getWorld(w.getName()));

        CoreSystem.getInstance().getTranslationManager().loadCategories(this);

        sendConsoleMessage("§aScoreboard-Scheduler wird gestartet");
        startScheduler();

        sendConsoleMessage("§aBuild-System witd initiiert");
        buildSystem = CoreSystem.getInstance().initialiseBuildSystem(BuildSystem.BuildEvent.BLOCK_BREAK, BuildSystem.BuildEvent.BLOCK_PLACE, BuildSystem.BuildEvent.INTERACT);
        buildSystem.addFilter(BuildSystem.BuildEvent.INTERACT, Material.STONE_BUTTON, Material.WOOD_BUTTON);

        sendConsoleMessage("§aBefehle und Events werden registriert...");
        CoreSystem.getInstance().enableSpawnCommand(getLobbyWorld(LobbyWorld.DIM_1));
        registerEventsAndCommands();

        CoreSystem.getInstance().setProfileInventorySize(InventorySlot.ROW_6);
        CoreSystem.getInstance().modifyProfileInventory(new ProfileInventoryModifier() {
            @Override
            public void onCreate(CoreInventory coreInventory, Player player) {
                coreInventory.setItem(InventorySlot.ROW_5_SLOT_5, new ItemBuilder(Material.CLAY_BALL, 1, 0).displayName("§fLobby Einstellungen").create(), e -> new LobbySettingsInventory(player));
            }
        });

        sendConsoleMessage("§aActivating AddOns...");
        for (LobbyAddon addon : ADDONS) {
            addon.onEnable();
        }

        sendConsoleMessage("§aVersion §f" + this.getDescription().getVersion() + "§a wurde aktiviert...");

        for (Player p : Bukkit.getOnlinePlayers()) {
            PlayerJoin.loadLobbyPlayer(p, LobbyPlayerLoadedEvent.Reason.RELOADED);
        }
    }

    @Override
    public void onDisable(){
        sendConsoleMessage("§cDeactivating AddOns...");
        for (LobbyAddon addon : ADDONS) {
            addon.onDisable();
        }

        sendConsoleMessage("§cPlugin disabled!");
    }

    private void registerEventsAndCommands() {
        registerEvents(
                new AsyncPlayerChat(),
                new PlayerInteract(),
                new MoneyChange(),
                new PlayerFish(),
                new PlayerToggleFlight(),
                new PlayerJoin(),
                new PlayerMove(),
                new PlayerDropItem(),
                new WeatherChange(),
                new InventoryClick(),
                new PlayerPickupItem(),
                new FoodLevelChange(),
                new EntitiyDamage(),
                new EntityDamageByEntity(),
                new PlayerDeath(),
                new PlayerInteractEntity(),
                new PlayerAchievementAwarded(),
                new LobbyPlayerLoaded()
        );
        registerCommands(
                new LobbyCMD()
        );
    }

    @Override
    public LobbyPlayer getLobbyPlayer(UUID uuid) {
        for (LobbyPlayer lp : players) {
            if (lp.getCorePlayer().getUuid().equals(uuid)) {
                return lp;
            }
        }
        return null;
    }

    @Override
    public LobbyPlayer getLobbyPlayer(String name) {
        for (LobbyPlayer lp : players) {
            if (lp.getCorePlayer().getName().equals(name)) {
                return lp;
            }
        }
        return null;
    }

    public Collection<LobbyPlayer> getLobbyPlayers() {
        return players;
    }

    @Override
    public void registerLobbyPlayer(LobbyPlayer lp) {
        players.add(lp);
    }

    @Override
    public CoreWorld getLobbyWorld(LobbyWorld world) {
        return worlds.get(world);
    }

    private void startScheduler() {
        Bukkit.getScheduler().runTaskTimer(this, SidebarObjective::updateLines, 50L, 100L);
    }

}
