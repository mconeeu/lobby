/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.games.pvp.catchgame;

import eu.mcone.coresystem.api.bukkit.world.CoreLocation;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.api.games.pvp.Catch;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.games.AbstractLobbyPvPGame;
import eu.mcone.lobby.games.listener.CatchListener;
import eu.mcone.lobby.games.scoreboard.CatchObjective;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

public class CatchLobbyGame extends AbstractLobbyPvPGame implements Catch {

    private static final Random TRAP_RANDOM = new Random();
    private static final Map<Location, Long> SPAWN_LOCATIONS = new HashMap<>();

    static {
        for (Map.Entry<String, CoreLocation> location : LobbyWorld.ONE_ISLAND.getWorld().getLocations().entrySet()) {
            if (location.getKey().startsWith("Catch-")) {
                SPAWN_LOCATIONS.put(location.getValue().bukkit(), (System.currentTimeMillis() / 1000) - 5);
            }
        }
        LobbyPlugin.getInstance().sendConsoleMessage("§2Loaded "+SPAWN_LOCATIONS.size()+" Locations for Catch");
    }

    @Getter
    private final ArrayList<Player> catcher;

    public CatchLobbyGame(LobbyPlugin plugin) {
        super("Catch", ChatColor.LIGHT_PURPLE, CatchObjective.class, "catch", "fangen");
        this.catcher = new ArrayList<>();

        plugin.registerEvents(new CatchListener(this));
        plugin.sendConsoleMessage("§aLoading LobbyGame Catch");
    }

    @Override
    public void onJoin(Player p, LobbyPlayer lp) {
        p.setGameMode(GameMode.ADVENTURE);
        p.setExp(1);
        setCatcherItems(p);
        p.setAllowFlight(false);
        lp.teleportAnimation(getRandomSpawn());

        if (catcher.isEmpty()) {
            p.setLevel(1);
            catcher.add(p);
            LobbyPlugin.getInstance().getMessenger().send(p, "§7Du bist ein §fFänger§7, warte bis ein §fandere Spieler §7beitritt und §ffange§7 ihn!");
        }

        LobbyPlugin.getInstance().getMessenger().send(p, "§7Fange einen §fSpieler §7mit einem §fGrünen Hut§7!");
    }

    @Override
    public void onQuit(Player p, LobbyPlayer lp) {
        if (catcher.contains(p)) {
            catcher.remove(p);

            if (catcher.isEmpty() && !playing.isEmpty()) {
                Player newCatcher = new ArrayList<>(playing).get(TRAP_RANDOM.nextInt(playing.size()));

                catcher.add(newCatcher);
                newCatcher.removePotionEffect(PotionEffectType.SPEED);
                newCatcher.setLevel(1);
                setCatcherItems(newCatcher);
                sendCatcherMessage(newCatcher);
            }
        }
    }

    public Location getRandomSpawn() {
        List<Location> locations = new ArrayList<>();

        for (Map.Entry<Location, Long> location : SPAWN_LOCATIONS.entrySet()) {
            if (((System.currentTimeMillis() / 1000) - location.getValue()) > 3) {
                locations.add(location.getKey());
            }
        }

        Location location = locations.get(TRAP_RANDOM.nextInt(locations.size()));
        SPAWN_LOCATIONS.put(location, System.currentTimeMillis() / 1000);

        return location;
    }

    public void setCatcherItems(Player p) {
        setDefaultItems(p);

        p.getInventory().setItem(0, CatchItem.CATCH_STICK);
        p.getInventory().setItem(2, CatchItem.CATCH_ROD);
        p.getInventory().setItem(1, CatchItem.CATCH_RUN_TRACKER);
    }

    public void setRunnerItems(Player p) {
        setDefaultItems(p);

        p.getInventory().setItem(0, CatchItem.CATCH_STICK_RUN);
        p.getInventory().setItem(1, CatchItem.CATCHER_TRACKER);
    }

    private void setDefaultItems(Player p) {
        p.getInventory().clear();
        p.removePotionEffect(PotionEffectType.SPEED);

        p.getInventory().setHelmet(CatchItem.HELMET);
        p.getInventory().setItem(8, CatchItem.LEAVE_CATCH_FIGHTING);
    }

    @Override
    public void playerLeaved(Player p) {
        super.playerLeaved(p);

        onQuit(p, LobbyPlugin.getInstance().getLobbyPlayer(p));
        reloadPlayerScoreboards();
    }

    private void sendCatcherMessage(Player p) {
        LobbyPlugin.getInstance().getMessenger().send(p, "§7Du bist nun §fFänger§7 ,weil der §fvorherige Fänger §7das Spiel §fverlassen§7 hat.");
    }

}

