/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.games.pvp.onehit;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.npc.NPC;
import eu.mcone.coresystem.api.bukkit.facades.Msg;
import eu.mcone.coresystem.api.bukkit.world.CoreLocation;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.api.games.pvp.OneHit;
import eu.mcone.lobby.api.items.LobbyItem;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.games.AbstractLobbyPvPGame;
import eu.mcone.lobby.games.listener.OneHitListener;
import eu.mcone.lobby.games.scoreboard.OneHitObjective;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.*;

public class OneHitLobbyGame extends AbstractLobbyPvPGame implements OneHit {

    private static final Map<Location, Long> SPAWN_LOCATIONS = new HashMap<>();

    static {
        for (Map.Entry<String, CoreLocation> location : LobbyWorld.ONE_ISLAND.getWorld().getLocations().entrySet()) {
            if (location.getKey().startsWith("Onehit-")) {
                SPAWN_LOCATIONS.put(location.getValue().bukkit(), (System.currentTimeMillis() / 1000) - 5);
            }
        }
        LobbyPlugin.getInstance().sendConsoleMessage("§2Loaded "+SPAWN_LOCATIONS.size()+" Locations for OneHit");
    }

    public OneHitLobbyGame(LobbyPlugin plugin) {
        super("OneHit", ChatColor.WHITE, OneHitObjective.class, "onehit");

        plugin.registerEvents(new OneHitListener(this));
        plugin.sendConsoleMessage("§aLoading LobbyGame OneHit");
    }

    @Override
    protected void onJoin(Player p, LobbyPlayer lp) {
        CoreSystem.getInstance().createActionBar().message("§cTeaming verboten!").send(p);

        npcVisibility(p, false);

        p.setExp(1);
        p.setLevel(0);
        setOneHitFightItems(p);
        p.setGameMode(GameMode.ADVENTURE);
        p.setAllowFlight(false);
        lp.teleportAnimation(getRandomSpawn());

        Msg.send(p, "§7Töte alle §fSpieler §7mit einem §fRoten Hut§7!");
    }

    @Override
    protected void onQuit(Player p, LobbyPlayer lp) {
       npcVisibility(p, true);
    }

    private void npcVisibility(Player p, Boolean b) {
        for (NPC npc : CoreSystem.getInstance().getNpcManager().getNpcs()) {
            npc.toggleVisibility(p, b);
        }
    }

    public Location getRandomSpawn() {
        List<Location> locations = new ArrayList<>();

        for (Map.Entry<Location, Long> location : SPAWN_LOCATIONS.entrySet()) {
            if (((System.currentTimeMillis() / 1000) - location.getValue()) > 3) {
                locations.add(location.getKey());
            }
        }

        Location location = locations.get(new Random().nextInt(locations.size() - 1));
        SPAWN_LOCATIONS.put(location, System.currentTimeMillis() / 1000);

        return location;
    }

    public void setOneHitFightItems(Player p) {
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);

        p.getInventory().clear();
        p.setMaxHealth(2);
        p.setHealth(2);

        p.getInventory().setHelmet(OneHitItem.HELMET);

        if (lp.hasLobbyItem(LobbyItem.ONE_HIT_SWORD)) {
            p.getInventory().setItem(0, OneHitItem.STORY_ONEHIT_SWORD);
        } else {
            p.getInventory().setItem(0, OneHitItem.ONEHIT_SWORD);
        }

        p.getInventory().setItem(7, OneHitItem.ONEHIT_GADGET);
        p.getInventory().setItem(1, OneHitItem.ONEHIT_BOW);
        p.getInventory().setItem(6, OneHitItem.ONEHIT_ARROW);
        p.getInventory().setItem(8, OneHitItem.LEAVE_ONEHIT_FIGHTING);
    }

}






