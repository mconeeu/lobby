/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.games;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.gameapi.api.GameAPI;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.api.games.LobbyPvpGame;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.api.player.scoreboard.SidebarObjective;
import eu.mcone.lobby.api.player.vanish.VanishPlayerVisibility;
import eu.mcone.lobby.games.scoreboard.LobbyGameObjective;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractLobbyPvPGame extends AbstractLobbyGame implements LobbyPvpGame {

    private final Class<? extends LobbyGameObjective> objective;
    @Getter
    protected final Set<Player> playing;

    protected AbstractLobbyPvPGame(String name, ChatColor color, String... shortNames) {
        this(name, color, null, shortNames);
    }

    protected AbstractLobbyPvPGame(String name, ChatColor color, Class<? extends LobbyGameObjective> objective, String... shortNames) {
        super(name, color, shortNames);
        this.objective = objective;

        this.playing = new HashSet<>();
    }

    @Override
    public void joinGame(Player p) {
        eu.mcone.lobby.api.games.LobbyGame game = LobbyGames.getInstance().getCurrentGame(p);

        if (game == null) {
            playing.add(p);
            LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);

            LobbyPlugin.getInstance().getVanishManager().setVanishPlayerVisibility(p, VanishPlayerVisibility.EVERYBODY);
            LobbyPlugin.getInstance().getVanishManager().quitSilentLobby(p);
            LobbyPlugin.getInstance().getBackpackManager().getPetHandler().despawnPet(p);
            GameAPI.getInstance().getGamePlayer(p).setEffectsVisible(false);

            if (lp.getSettings().isScoreboard() && objective != null) {
                try {
                    LobbyGameObjective objective = this.objective.getConstructor(getClass()).newInstance(this);
                    CoreSystem.getInstance().getCorePlayer(p.getUniqueId()).getScoreboard().setNewObjective(objective);
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }

            if (playing.size() <= 1) {
                sendMessage(p, "Du bist gerade der §f§oeinzigste§7, der §fOneHit§7 spielt!");
            } else {
                sendMessage(p, "Es spielen gerade §f§o" + playing.size() + " Spieler§f " + color + name + "§7!");
                reloadPlayerScoreboards();
            }

            onJoin(p, lp);
            CoreSystem.getInstance().getLabyModAPI().setCurrentServer(p, "MCONE-" + name);
        } else {
            sendMessage(p, "§4Du spielst bereits §c"+game.getName());
        }
    }

    @Override
    public void quitGame(Player p) {
        if (isPlaying(p)) {
            playing.remove(p);
            LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);

            p.getInventory().clear();
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1, 1);

            LobbyWorld.ONE_ISLAND.getWorld().teleportSilently(p, "spawn");
            lp.resetDataAndHotbarItems();

            GameAPI.getInstance().getGamePlayer(p).resetEffectsVisible();

            if (lp.getSettings().isRankBoots()) {
                LobbyPlugin.getInstance().getBackpackManager().setRankBoots(p);
            }

            CoreSystem.getInstance().getLabyModAPI().setCurrentServer(p, "MCONE-Lobby");

            reloadPlayerScoreboards();
            if (lp.getSettings().isScoreboard()) {
                lp.getCorePlayer().getScoreboard().setNewObjective(new SidebarObjective());
            }

            onQuit(p, lp);
            LobbyPlugin.getInstance().getMessenger().send(p, "§7Du hast das Spiel verlassen!");
        }
    }

    public void disable() {
        for (Player player : playing) {
            quitGame(player);
        }
    }

    protected abstract void onJoin(Player player, LobbyPlayer lp);

    protected abstract void onQuit(Player player, LobbyPlayer lp);

    public boolean isPlaying(Player p) {
        return playing.contains(p);
    }

    public void playerLeaved(Player p) {
        playing.remove(p);
    }

    protected void reloadPlayerScoreboards() {
        for (Player player : playing) {
            CorePlayer cp = CoreSystem.getInstance().getCorePlayer(player);

            if (cp.getScoreboard().getObjective(DisplaySlot.SIDEBAR) != null) {
                CoreSystem.getInstance().getCorePlayer(player).getScoreboard().getObjective(DisplaySlot.SIDEBAR).reload();
            }
        }
    }

}
