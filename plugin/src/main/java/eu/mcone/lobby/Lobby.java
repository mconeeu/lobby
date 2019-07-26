/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.config.CoreJsonConfig;
import eu.mcone.coresystem.api.bukkit.gamemode.Gamemode;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.npc.NPC;
import eu.mcone.coresystem.api.bukkit.npc.entity.PlayerNpc;
import eu.mcone.coresystem.api.bukkit.world.BuildSystem;
import eu.mcone.coresystem.api.bukkit.world.CoreWorld;
import eu.mcone.coresystem.api.core.labymod.LabyModEmote;
import eu.mcone.lobby.api.LobbyAddon;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.api.config.LobbyConfig;
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

    private CoreJsonConfig<LobbyConfig> lobbyConfig;

    public final static List<LobbyAddon> ADDONS = new ArrayList<>(Arrays.asList(
            new LobbyGang(), new LobbyItems(), new LobbyPets(), new LobbyStory()
    ));

    @Override
    public void onEnable() {
        instance = this;

        players = new ArrayList<>();
        worlds = new HashMap<>();

        lobbyConfig = new CoreJsonConfig<>(this, LobbyConfig.class, "lobby.json");
        lobbyConfig.save();

        for (LobbyWorld w : LobbyWorld.values())
            worlds.put(w, CoreSystem.getInstance().getWorldManager().getWorld(w.getName()));

        CoreSystem.getInstance().getTranslationManager().loadCategories(this);

        sendConsoleMessage("§aStarting Scoreboard-Scheduler...");
        startScheduler();

        sendConsoleMessage("§aInitializing Build-System...");
        buildSystem = CoreSystem.getInstance().initialiseBuildSystem(BuildSystem.BuildEvent.BLOCK_BREAK, BuildSystem.BuildEvent.BLOCK_PLACE, BuildSystem.BuildEvent.INTERACT);
        buildSystem.addFilter(BuildSystem.BuildEvent.INTERACT, Material.STONE_BUTTON.getId(), Material.WOOD_BUTTON.getId(), Material.ENDER_PORTAL_FRAME.getId());

        sendConsoleMessage("§aRegistering Events & Commands...");
        CoreSystem.getInstance().enableSpawnCommand(this, getLobbyWorld(LobbyWorld.ONE_ISLAND), 0);
        registerEventsAndCommands();

        CoreSystem.getInstance().setProfileInventorySize(InventorySlot.ROW_6);
        CoreSystem.getInstance().modifyProfileInventory((coreInventory, player) -> {
            coreInventory.setItem(
                    InventorySlot.ROW_5_SLOT_8,
                    new ItemBuilder(Material.CLAY_BALL, 1, 0).displayName("§3§lLobby Einstellungen").lore("§7§oVerwalte deine", "§7§oLobbyeinstellungen", "", "§8» §f§nLinksklick§8 | §7§oÖffnen").create(),
                    e -> new LobbySettingsInventory(player)
            );
            coreInventory.setItem(
                    InventorySlot.ROW_5_SLOT_6,
                    new ItemBuilder(Material.CHEST, 1, 0).displayName("§e§lModifizierte Inventare").lore("§7§oModifiziere Shop-Inventare aus", "§7§oallen Spielmodi", "", "§8» §f§nLinksklick§8 | §7§oAnzeigen").create(),
                    e -> getInventoryModificationManager().openGamemodeModificationInventory(player)
            );
        });

        sendConsoleMessage("§aActivating AddOns...");
        for (LobbyAddon addon : ADDONS) {
            addon.onEnable();
        }

        sendConsoleMessage("§aVersion §f" + this.getDescription().getVersion() + "§a enabled...");

        for (Player p : Bukkit.getOnlinePlayers()) {
            PlayerJoinListener.loadLobbyPlayer(p, LobbyPlayerLoadedEvent.Reason.RELOADED);
        }
        if (Bukkit.getOnlinePlayers().size() > 0) {
            for (Gamemode gm : Gamemode.values()) {
                NPC npc = LobbyWorld.ONE_ISLAND.getWorld().getNPC(gm.getName().toLowerCase());

                if (npc != null) {
                    ((PlayerNpc) npc).playLabymodEmote(LabyModEmote.INFINITY_DAB);
                }
            }
        }
    }

    @Override
    public void onDisable() {
        sendConsoleMessage("§cDeactivating AddOns...");
        for (LobbyAddon addon : ADDONS) {
            addon.onDisable();
        }

        sendConsoleMessage("§cPlugin disabled!");
    }

    private void registerEventsAndCommands() {
        registerEvents(
                new ChatListener(),
                new DoubleJumpListener(),
                new EntitiyDamageListener(),
                new FishingRodListener(),
                new GeneralPlayerListener(),
                new InventoryTriggerListener(),
                new NpcListener(),
                new PlayerJoinListener(),
                new PlayerUpdateListener(),
                new WeatherChangeListener()
        );
        registerCommands(new LobbyCMD());
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
    public CoreJsonConfig<LobbyConfig> getLobbyConfig() {
        return lobbyConfig;
    }

    @Override
    public void registerLobbyPlayer(LobbyPlayer lp) {
        players.add(lp);
    }

    @Override
    public void unregisterLobbyPlayer(LobbyPlayer lp) {
        players.remove(lp);
    }

    @Override
    public CoreWorld getLobbyWorld(LobbyWorld world) {
        return worlds.get(world);
    }

    private void startScheduler() {
        Bukkit.getScheduler().runTaskTimer(this, SidebarObjective::updateLines, 50L, 100L);
    }

}
