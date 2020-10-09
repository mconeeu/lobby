/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.games.jumpnrun;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.coresystem.api.bukkit.util.CoreTitle;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.api.games.jumpnrun.JumpNRun;
import eu.mcone.lobby.api.games.jumpnrun.JumpNRunGame;
import eu.mcone.lobby.api.player.HotbarItem;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.games.AbstractLobbyGame;
import eu.mcone.lobby.games.LobbyGames;
import eu.mcone.lobby.games.listener.JumpNRunListener;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

public class JumpNRunLobbyGame extends AbstractLobbyGame implements JumpNRunGame {

    public static final CoreTitle SKIP_WARN_TITLE = CoreSystem.getInstance().createTitle()
            .fadeIn(1)
            .fadeOut(1)
            .stay(3)
            .title("§cNicht überspringen!")
            .subTitle("§4Du wurdest zurück telepotiert");

    @Getter
    protected final Set<JumpNRunPlayer> currentlyPlaying = new HashSet<>();

    public JumpNRunLobbyGame(LobbyPlugin plugin) {
        super("Jump'n'Run", ChatColor.GREEN, "jumpnrun", "jumpandrun", "jump");

        plugin.registerEvents(new JumpNRunListener(this));
        plugin.sendConsoleMessage("§aLoading LobbyGame JumpNRun");

        Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, () -> {
            for (JumpNRunPlayer jnrPlayer : JumpNRunLobbyGame.this.getCurrentlyPlaying()) {
                Calendar calendarTime = Calendar.getInstance(TimeZone.getTimeZone("CEST"));
                calendarTime.setTimeInMillis(System.currentTimeMillis() - (jnrPlayer.getTime() * 1000));

                CoreSystem.getInstance().createActionBar().message("§7§oDeine Zeit:§f"
                        + (calendarTime.get(Calendar.HOUR) > 0 ? " " + calendarTime.get(Calendar.HOUR) + "h" : "")
                        + (calendarTime.get(Calendar.MINUTE) > 0 ? " " + calendarTime.get(Calendar.MINUTE) + "m" : "")
                        + (calendarTime.get(Calendar.SECOND) > 0 ? " " + calendarTime.get(Calendar.SECOND) + "s" : ""))
                        .send(jnrPlayer.getPlayer());
            }
        }, 80, 20);
    }

    @Override
    public void startGame(Player p, JumpNRun jumpNRun) {
        eu.mcone.lobby.api.games.LobbyGame game = LobbyGames.getInstance().getCurrentGame(p);

        if (game == null) {
            currentlyPlaying.add(new JumpNRunPlayer(p, jumpNRun, System.currentTimeMillis() / 1000));

            CorePlayer corePlayer = CoreSystem.getInstance().getCorePlayer(p);
            LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(corePlayer.getUuid());

            setJumpNRunItems(p);
            LobbyPlugin.getInstance().getBackpackManager().getPetHandler().despawnPet(p);
            LobbyWorld.ONE_ISLAND.getWorld().teleport(p, jumpNRun.getStartLocation());

            if (!lp.hasJumpnrunMade(jumpNRun)) {
                sendMessage(p, "§7Du spielst nun das §e" + jumpNRun.getJumpandrunname() + "§7 Jump'n'Run. Zum §cbeenden §7die §fEisentür §7klicken!");
            } else {
                sendMessage(p, "§7Du hast dieses Jump'n'Run bereits gespielt und erhälst keine Belohnungen mehr!");
            }
        } else {
            sendMessage(p, "§4Du spielst bereits §c" + game.getName());
        }
    }

    @Override
    public void setCheckpoint(Player p, int checkpoint) {
        JumpNRunPlayer jnrPlayer = getCurrentlyPlaying(p);

        if (jnrPlayer != null) {
            if (checkpoint <= jnrPlayer.getCheckpoint() + 1) {
                if (checkpoint == jnrPlayer.getCheckpoint() + 1) {
                    jnrPlayer.setCheckpoint(checkpoint);
                    LobbyPlugin.getInstance().getMessenger().send(p, "§2Du hast den §a" + checkpoint + ". Checkpoint§2 erreicht!");
                }
            } else {
                LobbyPlugin.getInstance().getMessenger().send(p, "§4Du hast einen Checkpoint übersprungen und wurdest zu deinem letzten Checkpoint telepotiert!");
                SKIP_WARN_TITLE.send(p);
                if (jnrPlayer.getCheckpoint() <= 1) {
                    LobbyWorld.ONE_ISLAND.getWorld().teleport(p, jnrPlayer.getJumpNRun().getStartLocation());
                } else {
                    p.teleport(jnrPlayer.getJumpNRun().getCheckpoints()[jnrPlayer.getCheckpoint() - 1]);
                }
            }
        }
    }

    @Override
    public void tpToCheckpoint(Player p) {
        JumpNRunPlayer jnrPlayer = getCurrentlyPlaying(p);

        if (jnrPlayer != null) {
            if (jnrPlayer.getCheckpoint() == 0) {
                LobbyWorld.ONE_ISLAND.getWorld().teleportSilently(p, jnrPlayer.getJumpNRun().getStartLocation());
            } else {
                p.teleport(jnrPlayer.getJumpNRun().getCheckpoints()[jnrPlayer.getCheckpoint() - 1]);
            }
        }
    }

    @Override
    public void quitGame(Player p) {
        if (isPlaying(p)) {
            removePlaying(p);

            LobbyPlugin.getInstance().resetPlayerDataAndHotbarItems(p);
            LobbyWorld.ONE_ISLAND.getWorld().teleportSilently(p, "spawn");
            LobbyPlugin.getInstance().getMessenger().send(p, "§cDu hast das Jump and Run §oabgebrochen!");
        }
    }

    @Override
    public void finishGame(Player p) {
        CorePlayer corePlayer = CoreSystem.getInstance().getCorePlayer(p);
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(corePlayer.getUuid());

        if (isPlaying(p)) {
            JumpNRunPlayer jnrPlayer = getCurrentlyPlaying(p);
            JumpNRun jumpNRun = Objects.requireNonNull(jnrPlayer).getJumpNRun();
            int checkpoint = jnrPlayer.getCheckpoint();
            long bestTime = lp.getBestJumpNRunTime(jumpNRun);
            long time = System.currentTimeMillis() / 1000 - jnrPlayer.getTime();

            Calendar calendarTime = Calendar.getInstance(TimeZone.getTimeZone("CEST"));
            calendarTime.setTimeInMillis(time * 1000);

            if (checkpoint == jumpNRun.getCheckpoints().length) {
                if (lp.hasJumpnrunMade(jumpNRun)) {
                    LobbyPlugin.getInstance().getMessenger().send(p,
                            "§2Du hast das Jump and Run §aerfolgreich§2 wiederholt! " +
                                    "Du hast §f" + getTimeString(calendarTime) + "§2 gebraucht!"
                    );

                    Calendar calendarBestTime = Calendar.getInstance(TimeZone.getTimeZone("CEST"));
                    calendarBestTime.setTimeInMillis(bestTime * 1000);

                    if (time < bestTime && bestTime != -1) {
                        LobbyPlugin.getInstance().getMessenger().send(p,
                                "§aDu hast deinen alten Rekord von §7§o" + getTimeString(calendarBestTime) + "§a geknackt! " +
                                        "§oHerzlichen Glückwunsch"
                        );
                    } else if (bestTime != -1) {
                        LobbyPlugin.getInstance().getMessenger().send(p, "§7Dein Rekord ist weiterhin §f§o" + getTimeString(calendarBestTime) + "§7!");
                    }
                } else {
                    corePlayer.addCoins(100);
                    LobbyPlugin.getInstance().getMessenger().send(p,
                            "§2Du hast das Jump and Run §aerfolgreich §2zum erten Mal fertig gespielt! " +
                                    "Du hast §f" + getTimeString(calendarTime) + "§2 gebraucht! §8[§a+100 Coins§8]"
                    );
                }

                lp.resetDataAndHotbarItems();
                LobbyWorld.ONE_ISLAND.getWorld().teleportSilently(p, "spawn");
                lp.setJumpnrunBestTime(jumpNRun, time < bestTime || bestTime == -1 ? time : bestTime);
                removePlaying(p);
            } else {
                LobbyPlugin.getInstance().getMessenger().send(p, "§4Du hast einen Checkpoint übersprungen und wurdest zu deinem letzten Checkpoint telepotiert!");

                if (checkpoint == 0) {
                    p.teleport(jumpNRun.getStartPlateLocation());
                } else {
                    p.teleport(jumpNRun.getCheckpoints()[checkpoint - 1]);
                }
            }
        }
    }

    private void setJumpNRunItems(Player p) {
        p.getInventory().clear();

        p.setGameMode(GameMode.ADVENTURE);
        p.getActivePotionEffects().clear();
        p.removePotionEffect(PotionEffectType.INVISIBILITY);

        p.setMaxHealth(20);
        p.setHealth(20);
        p.setFoodLevel(20);

        p.getInventory().setItem(0, LobbyPlugin.getInstance().getVanishManager().getVanishPlayerVisibility(p).getItem());
        if (p.hasPermission("lobby.silenthub")) {
            if (LobbyPlugin.getInstance().getVanishManager().isInSilentLobby(p)) {
                p.getInventory().setItem(1, HotbarItem.SILENT_LOBBY_QUIT);
                p.getInventory().setItem(0, HotbarItem.LOBBY_HIDER_UNAVAILABLE_SILENT_LOBBY);
            } else {
                p.getInventory().setItem(1, HotbarItem.SILENT_LOBBY_JOIN);
            }
        }

        p.getInventory().setItem(8, HotbarItem.LEAVE_JUMPNRUN);
        p.getInventory().setItem(7, HotbarItem.TO_CHECKPOINT);
    }

    private JumpNRunPlayer getCurrentlyPlaying(Player p) {
        for (JumpNRunPlayer player : currentlyPlaying) {
            if (player.getPlayer().equals(p)) {
                return player;
            }
        }
        return null;
    }

    @Override
    public boolean isPlaying(Player p) {
        return getCurrentlyPlaying(p) != null;
    }

    @Override
    public void playerLeaved(Player p) {
        removePlaying(p);
    }

    private void removePlaying(Player p) {
        if (getCurrentlyPlaying() != null) {
            currentlyPlaying.remove(getCurrentlyPlaying(p));
        }
    }

    private String getTimeString(Calendar calendar) {
        return (calendar.get(Calendar.HOUR) > 0 ? " " + calendar.get(Calendar.HOUR) + " Stunden" : "")
                + (calendar.get(Calendar.MINUTE) > 0 ? " " + calendar.get(Calendar.MINUTE) + " Minuten" : "")
                + (calendar.get(Calendar.SECOND) > 0 ? " " + calendar.get(Calendar.SECOND) + " Sekunden" : "");
    }

}

