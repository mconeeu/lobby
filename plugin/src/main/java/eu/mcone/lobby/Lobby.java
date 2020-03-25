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
import eu.mcone.lobby.api.LobbyAddon;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.command.LobbyCMD;
import eu.mcone.lobby.gang.LobbyGang;
import eu.mcone.lobby.inventory.LobbySettingsInventory;
import eu.mcone.lobby.items.LobbyItems;
import eu.mcone.lobby.jumpnrun.LobbyJumpNRunManager;
import eu.mcone.lobby.listener.*;
import eu.mcone.lobby.onehit.LobbyOneHitManager;
import eu.mcone.lobby.scoreboard.LobbyObjective;
import eu.mcone.lobby.story.LobbyStory;
import eu.mcone.lobby.trap.TrapManager;
import eu.mcone.lobby.util.PlayerHiderManager;
import eu.mcone.lobby.util.RealTimeUtil;
import eu.mcone.lobby.util.SilentLobbyManager;
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
    private LobbyOneHitManager oneHitManager;
    @Getter
    private TrapManager catchManager;
    @Getter
    private LobbyJumpNRunManager jumpNRunManager;
    @Getter
    private SilentLobbyManager silentLobbyManager;
    @Getter
    private PlayerHiderManager playerHiderManager;
    @Getter
    private Map<LobbyWorld, CoreWorld> worlds;

    @Getter
    private List<LobbyPlayer> players;

    public final static List<LobbyAddon> ADDONS = new ArrayList<>(Arrays.asList(
            new LobbyGang(), new LobbyItems(), new LobbyStory()
    ));

    @Override
    public void onGameEnable() {
        system = this;

        worlds = new HashMap<>();
        players = new ArrayList<>();

        for (LobbyWorld w : LobbyWorld.values())
            worlds.put(w, CoreSystem.getInstance().getWorldManager().getWorld(w.getName()));

        CoreSystem.getInstance().getTranslationManager().loadCategories(this);

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

        sendConsoleMessage("§aLoading OneHitManager...");
        oneHitManager = new LobbyOneHitManager(this);

        sendConsoleMessage("§aLoading CatchManager...");
        catchManager = new TrapManager(this);

        sendConsoleMessage("§aLoading JmpNRunManager...");
        jumpNRunManager = new LobbyJumpNRunManager(this);

        silentLobbyManager = new SilentLobbyManager();
        playerHiderManager = new PlayerHiderManager();

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
                new WeatherChangeListener(),
                new ItemHotbarChangeListener(),
                new DropPickupListener()
        );
        registerCommands(new LobbyCMD());
    }

    @Override
    public CoreWorld getLobbyWorld(LobbyWorld world) {
        return worlds.get(world);
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

    private void startScheduler() {
        Bukkit.getScheduler().runTaskTimer(this, LobbyObjective::updateLines, 50, 100);
        Bukkit.getScheduler().runTaskTimer(this, new RealTimeUtil(), 50, 20 * 60);
    }

}
