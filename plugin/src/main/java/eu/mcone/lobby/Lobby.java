/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.gamemode.Gamemode;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.npc.NPC;
import eu.mcone.coresystem.api.bukkit.npc.entity.PlayerNpc;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.coresystem.api.bukkit.world.BuildSystem;
import eu.mcone.coresystem.api.bukkit.world.CoreWorld;
import eu.mcone.coresystem.api.core.labymod.LabyModEmote;
import eu.mcone.gameapi.api.GamePlugin;
import eu.mcone.lobby.api.LobbyAddon;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.api.player.scoreboard.LobbyObjective;
import eu.mcone.lobby.api.player.settings.HotbarSettingsHandler;
import eu.mcone.lobby.api.player.sounds.PlayerSounds;
import eu.mcone.lobby.command.LobbyCMD;
import eu.mcone.lobby.games.LobbyGames;
import eu.mcone.lobby.games.command.GameCMD;
import eu.mcone.lobby.inventory.LobbyProfileInventory;
import eu.mcone.lobby.items.LobbyItems;
import eu.mcone.lobby.items.manager.LobbyLiveEventManager;
import eu.mcone.lobby.listener.*;
import eu.mcone.lobby.scheduler.NpcEmoteScheduler;
import eu.mcone.lobby.scheduler.WorldRealTimeScheduler;
import eu.mcone.lobby.story.LobbyStory;
import eu.mcone.lobby.util.LobbyVanishManager;
import eu.mcone.lobby.util.StackingManager;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.*;

public class Lobby extends LobbyPlugin {

    @Getter
    private static Lobby system;

    @Getter
    private BuildSystem buildSystem;
    @Getter
    private LobbyLiveEventManager liveEventManager;
    @Getter
    private PlayerSounds playerSounds;
    @Getter
    private HotbarSettingsHandler hotbarSettings;
    @Getter
    private LobbyVanishManager vanishManager;
    @Getter
    private StackingManager stackingManager;
    @Getter
    private Map<LobbyWorld, CoreWorld> worlds;

    @Getter
    private List<LobbyPlayer> players;

    public final static List<LobbyAddon> ADDONS = new ArrayList<>(Arrays.asList(
            new LobbyItems(), new LobbyStory(), new LobbyGames()
    ));

    @Override
    public void onGameEnable() {
        system = this;

        worlds = new HashMap<>();
        players = new ArrayList<>();

        for (LobbyWorld w : LobbyWorld.values())
            worlds.put(w, CoreSystem.getInstance().getWorldManager().getWorld(w.getName()));

        GamePlugin.getGamePlugin().getBackpackManager().setGadgetSlotProvider(p -> 2);

        sendConsoleMessage("§aStarting Scoreboard-Scheduler...");
        startScheduler();

        sendConsoleMessage("§aInitializing Build-System...");
        buildSystem = CoreSystem.getInstance().initialiseBuildSystem(BuildSystem.BuildEvent.BLOCK_BREAK, BuildSystem.BuildEvent.BLOCK_PLACE, BuildSystem.BuildEvent.INTERACT);
        buildSystem.addFilter(BuildSystem.BuildEvent.INTERACT, Material.STONE_BUTTON.getId(), Material.WOOD_BUTTON.getId(), Material.ENDER_PORTAL_FRAME.getId(), Material.IRON_PLATE.getId(), Material.GOLD_PLATE.getId());

        sendConsoleMessage("§aRegistering Events & Commands...");
        CoreSystem.getInstance().enableSpawnCommand(this, getLobbyWorld(LobbyWorld.ONE_ISLAND), 0);
        registerEventsAndCommands();

        CoreSystem.getInstance().setProfileInventorySize(InventorySlot.ROW_6);
        CoreSystem.getInstance().modifyProfileInventory((coreInventory, player) -> {
            coreInventory.setItem(
                    InventorySlot.ROW_5_SLOT_3,
                    new ItemBuilder(Material.GRASS, 1, 0).displayName("§3§lLobby").lore("§7§oWähle zwischen Lobbyeinstellungen", "§7§oSecrets oder dein Story Fortschritt", "", "§8» §f§nLinksklick§8 | §7§oAnzeigen").create(),
                    e -> new LobbyProfileInventory(player)
            );

            coreInventory.setItem(
                    InventorySlot.ROW_5_SLOT_7,
                    new ItemBuilder(Material.CHEST, 1, 0).displayName("§e§lModifizierte Inventare").lore("§7§oModifiziere Shop-Inventare aus", "§7§oallen Spielmodi", "", "§8» §f§nLinksklick§8 | §7§oAnzeigen").create(),
                    e -> getInventoryModificationManager().openGamemodeModificationInventory(player)
            );
        });

        sendConsoleMessage("§aActivating AddOns...");
        for (LobbyAddon addon : ADDONS) {
            addon.onEnable(this);
        }

        sendConsoleMessage("§aLoading LiveEventManager...");
        liveEventManager = new LobbyLiveEventManager();

        sendConsoleMessage("§aLoading Player Sounds...");
        playerSounds = new PlayerSounds();

        sendConsoleMessage("§aLoading Lobby Hotbar...");
        hotbarSettings = new HotbarSettingsHandler();

        sendConsoleMessage("§aLoading VanishManager...");
        vanishManager = new LobbyVanishManager(this);

        sendConsoleMessage("§aLoading StackingManager...");
        stackingManager = new StackingManager(this);

        getDamageLogger();
        sendConsoleMessage("§aVersion §f" + this.getDescription().getVersion() + "§a enabled...");

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
    public void onGameDisable() {
        sendConsoleMessage("§cDeactivating AddOns...");
        for (LobbyAddon addon : ADDONS) {
            addon.onDisable(this);
        }
        for (LobbyPlayer lp : getOnlineLobbyPlayers()) {
            lp.saveData();
        }

        sendConsoleMessage("§cPlugin disabled!");
    }

    private void registerEventsAndCommands() {
        registerEvents(
                new ChatListener(),
                new EntitiyDamageListener(),
                new GeneralPlayerListener(),
                new InventoryTriggerListener(),
                new NpcListener(),
                new PlayerJoinListener(),
                new PlayerUpdateListener(),
                new WeatherChangeListener(),
                new NickListener()
        );
        registerCommands(
                new LobbyCMD(),
                new GameCMD()
        );
    }

    @Override
    public CoreWorld getLobbyWorld(LobbyWorld world) {
        return worlds.get(world);
    }

    @Override
    public <T extends LobbyAddon> T getAddon(Class<? extends T> addonClass) {
        for (LobbyAddon addon : ADDONS) {
            if (addon.getClass().isAssignableFrom(addonClass)) {
                return (T) addon;
            }
        }

        return null;
    }

    @Override
    public LobbyPlayer getLobbyPlayer(CorePlayer cp) {
        return getLobbyPlayer(cp.getUuid());
    }

    @Override
    public LobbyPlayer getLobbyPlayer(Player p) {
        return getLobbyPlayer(p.getUniqueId());
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

    @Override
    public Collection<LobbyPlayer> getOnlineLobbyPlayers() {
        return new ArrayList<>(players);
    }

    public void registerLobbyPlayer(LobbyPlayer lp) {
        players.add(lp);
    }

    public void unregisterLobbyPlayer(LobbyPlayer lp) {
        players.remove(lp);
    }

    @Override
    public void resetPlayerDataAndHotbarItems(Player p) {
        PlayerJoinListener.resetPlayerDataAndHotbarItems(p);
    }

    private void startScheduler() {
        Bukkit.getScheduler().runTaskTimer(this, LobbyObjective::updateLines, 50, 100);
        Bukkit.getScheduler().runTaskTimer(this, new WorldRealTimeScheduler(), 50, 20 * 60);
        Bukkit.getScheduler().runTaskTimer(this, new NpcEmoteScheduler(), 50, 20 * 5);
    }

}
