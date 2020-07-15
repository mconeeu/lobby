package eu.mcone.lobby.trap;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.world.CoreLocation;
import eu.mcone.gameapi.api.GameAPI;
import eu.mcone.lobby.Lobby;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.api.player.HotbarItems;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.listener.PlayerJoinListener;
import eu.mcone.lobby.listener.TrappingListener;
import eu.mcone.lobby.scoreboard.CatchObjective;
import eu.mcone.lobby.scoreboard.SidebarObjective;
import lombok.Getter;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.DisplaySlot;

import java.util.*;

public class TrapManager implements eu.mcone.lobby.api.trap.CatchManager {

    private static final Random TRAP_RANDOM = new Random();
    private static final Map<Location, Long> SPAWN_LOCATIONS = new HashMap<>();


    static {
        for (Map.Entry<String, CoreLocation> location : LobbyWorld.ONE_ISLAND.getWorld().getLocations().entrySet()) {
            if (location.getKey().startsWith("Catch-")) {
                SPAWN_LOCATIONS.put(location.getValue().bukkit(), (System.currentTimeMillis() / 1000) - 5);
            }
        }
    }

    @Getter
    private final ArrayList<Player> catching;
    @Getter
    private final ArrayList<Player> catcher;

    public TrapManager(Lobby plugin) {
        plugin.registerEvents(new TrappingListener(this));
        this.catching = new ArrayList<>();
        this.catcher = new ArrayList<>();
    }

    @Override
    public void setStart(Player p) {
        if (LobbyPlugin.getInstance().getPlayerHiderManager().isHidden(p)) {
            LobbyPlugin.getInstance().getPlayerHiderManager().showPlayers(p);
        }
        if (LobbyPlugin.getInstance().getSilentLobbyManager().isActivatedSilentHub(p)) {
            LobbyPlugin.getInstance().getSilentLobbyManager().deactivateSilentLobby(p);
        }
        if (!catching.contains(p)) {
            GameAPI.getInstance().getGamePlayer(p).setEffectsVisible(false);

            LobbyPlayer lobbyPlayer = LobbyPlugin.getInstance().getLobbyPlayer(p);
            if (lobbyPlayer.getSettings().isScoreboard()) {
                CoreSystem.getInstance().getCorePlayer(p.getUniqueId()).getScoreboard().setNewObjective(new CatchObjective(this));
            }

            CoreSystem.getInstance().getLabyModAPI().setCurrentServer(p, "MCONE-Fangen");
            p.setGameMode(GameMode.ADVENTURE);
            p.setExp(1);
            catching.add(p);
            p.setGameMode(GameMode.ADVENTURE);
            p.setAllowFlight(false);

            if (catching.size() <= 1) {
                LobbyPlugin.getInstance().getMessenger().send(p, "§7Du bist gerade der §f§oeinzigste§7, der §fFangen§7 spielt!");

                if (catcher.isEmpty()) {
                    p.setLevel(1);
                    catcher.add(p);
                    LobbyPlugin.getInstance().getMessenger().send(p, "§7Du bist ein §fFänger§7, warte bis ein §fandere Spieler §7beitritt und §ffange§7 ihn!");
                    LobbyPlugin.getInstance().getMessenger().send(p, "§7Fange einen §fSpieler §7mit einem §fGrünen Hut§7!");
                }
                 if (lobbyPlayer.getSettings().isScoreboard()) {
                    CoreSystem.getInstance().getCorePlayer(p).getScoreboard().getObjective(DisplaySlot.SIDEBAR).reload();
                }
            } else {
                LobbyPlugin.getInstance().getMessenger().send(p, "§7Es spielen gerade §f§o" + catching.size() + "§7 Spieler §fFangen§7!");
                for (Player all : Bukkit.getOnlinePlayers()) {
                    if (catching.contains(all))
                        CoreSystem.getInstance().getCorePlayer(all).getScoreboard().getObjective(DisplaySlot.SIDEBAR).reload();
                }

            }
            setCatchItems(p);
            LobbyPlugin.getInstance().getLobbyPlayer(p).teleportAnimation(getRandomSpawn());

            LobbyPlugin.getInstance().getBackpackManager().getPetHandler().despawnPet(p);
        } else {
            LobbyPlugin.getInstance().getMessenger().send(p, "§4Du bist bereits in diesem LobbyGame!");
        }
    }

    @Override
    public void leave(Player p) {
        if (catching.contains(p)) {
            catching.remove(p);
            if (catcher.contains(p)) {
                if (catching.isEmpty()) {
                    catcher.remove(p);
                } else if (catching.size() == 1) {
                    for (Player random : catching) {
                        catcher.remove(p);
                        catcher.add(random);
                        setCatchItems(random);
                        LobbyPlugin.getInstance().getMessenger().send(random, "§7Du bist nun §fFänger§7 ,weil der §fvorherige Fänger §7das Spiel §fverlassen§7 hat.");
                        p.removePotionEffect(PotionEffectType.SPEED);
                        p.getInventory().clear();
                        random.setLevel(1);
                    }
                } else {
                    catcher.remove(p);
                    Player random = catching.get(TRAP_RANDOM.nextInt(catching.size()));
                    catcher.add(random);
                    setCatchItems(random);
                    LobbyPlugin.getInstance().getMessenger().send(random, "§7Du bist nun §fFänger§7 ,weil der §fvorherige Fänger §7das Spiel §fverlassen§7 hat.");
                    p.removePotionEffect(PotionEffectType.SPEED);
                    p.getInventory().clear();
                    random.setLevel(1);
                }
            }

            CoreSystem.getInstance().getLabyModAPI().setCurrentServer(p, "Lobby");

            p.removePotionEffect(PotionEffectType.SPEED);
            p.getInventory().clear();
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1, 1);
            LobbyWorld.ONE_ISLAND.getWorld().teleportSilently(p, "spawn");
            PlayerJoinListener.setLobbyItems(p);
            p.getActivePotionEffects().clear();
            p.setLevel(0);
            p.setExp(0);
            p.setGameMode(GameMode.SURVIVAL);
            GameAPI.getInstance().getGamePlayer(p).setEffectsVisible(true);

            LobbyPlugin.getInstance().getMessenger().send(p, "§7Du hast das Spiel verlassen!");

            LobbyPlayer lobbyPlayer = LobbyPlugin.getInstance().getLobbyPlayer(p);
            if (lobbyPlayer.getSettings().isScoreboard()) {
                lobbyPlayer.getCorePlayer().getScoreboard().setNewObjective(new SidebarObjective());
            }

            for (Player player : catching) {
                CoreSystem.getInstance().getCorePlayer(player).getScoreboard().getObjective(DisplaySlot.SIDEBAR).reload();
            }
        }
    }

    @Override
    public boolean isCatching(Player p) {
        return catching.contains(p);
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

    public void setCatchItems(Player p) {
        p.removePotionEffect(PotionEffectType.SPEED);

        p.getInventory().clear();
        p.getActivePotionEffects().clear();

        p.getInventory().setHelmet(ItemBuilder.createLeatherArmorItem(Material.LEATHER_HELMET, Color.GREEN).unbreakable(true).itemFlags(ItemFlag.HIDE_UNBREAKABLE).create());


        if (catcher.contains(p)) {
            p.getInventory().setItem(0, HotbarItems.CATCH_STICK);
            p.getInventory().setItem(2, HotbarItems.CATCH_ROD);
            p.getInventory().setItem(1, HotbarItems.CATCH_RUN_TRACKER);
        } else {
            p.getInventory().setItem(0, HotbarItems.CATCH_STICK_RUN);
            p.getInventory().setItem(1, HotbarItems.CATCHER_TRACKER);
            p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
        }

        p.getInventory().setItem(8, HotbarItems.LEAVE_CATCH_FIGHTING);
    }


    public void playerLeaved(Player p) {
        if (catching.contains(p)) {
            catching.remove(p);

            LobbyPlayer lobbyPlayer = LobbyPlugin.getInstance().getLobbyPlayer(p);
            if (lobbyPlayer.getSettings().isScoreboard()) {
                lobbyPlayer.getCorePlayer().getScoreboard().setNewObjective(new SidebarObjective());
            }

            if (catcher.contains(p)) {
                if (catching.isEmpty()) {
                    catcher.remove(p);
                } else if (catching.size() == 1) {
                    for (Player random : catching) {
                        catcher.remove(p);
                        catcher.add(random);
                        setCatchItems(random);
                        LobbyPlugin.getInstance().getMessenger().send(random, "§7Du bist nun §fFänger§7 ,weil der §fvorherige Fänger §7das Spiel §fverlassen§7 hat.");
                        random.setLevel(1);
                        CoreSystem.getInstance().getCorePlayer(random).getScoreboard().getObjective(DisplaySlot.SIDEBAR).reload();
                    }
                } else {
                    catcher.remove(p);
                    Player random = catching.get(TRAP_RANDOM.nextInt(catching.size()));
                    catcher.add(random);
                    setCatchItems(random);
                    random.setLevel(1);
                    LobbyPlugin.getInstance().getMessenger().send(random, "§7Du bist nun §fFänger§7 ,weil der §fvorherige Fänger §7das Spiel §fverlassen§7 hat.");
                    CoreSystem.getInstance().getCorePlayer(random).getScoreboard().getObjective(DisplaySlot.SIDEBAR).reload();
                }
            }
        }
    }
}

