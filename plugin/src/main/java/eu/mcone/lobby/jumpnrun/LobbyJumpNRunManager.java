/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.jumpnrun;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.coresystem.api.bukkit.util.CoreTitle;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.api.enums.JumpNRun;
import eu.mcone.lobby.api.jumpnrun.JumpNRunManager;
import eu.mcone.lobby.api.player.HotbarItems;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.listener.JumpNRunListener;
import eu.mcone.lobby.listener.PlayerJoinListener;
import eu.mcone.lobby.scoreboard.SidebarObjective;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import java.util.TimeZone;

public class LobbyJumpNRunManager implements JumpNRunManager {

    public static final CoreTitle title = CoreSystem.getInstance().createTitle().fadeIn(1).fadeOut(1).stay(3).title("§cNicht überspringen!").subTitle("§4Du wurdest zurück telepotiert");

    @Getter
    private final Set<JumpNRunPlayer> currentlyPlaying = new HashSet<>();

    public LobbyJumpNRunManager(LobbyPlugin plugin) {
        plugin.registerEvents(new JumpNRunListener(this));

        Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, () -> {
            for (JumpNRunPlayer jnrPlayer : currentlyPlaying) {
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
    public void setStart(Player p, JumpNRun jumpNRun) {
        if (LobbyPlugin.getInstance().getOneHitManager().isFighting(p) || LobbyPlugin.getInstance().getCatchManager().isCatching(p) || LobbyPlugin.getInstance().getGungameManager().isFighting(p)) {
            LobbyPlugin.getInstance().getMessenger().send(p, "§4Du darfst im moment keine Jump and Runs spielen, weil du gerade ein Lobbygame spielst!");
            LobbyWorld.ONE_ISLAND.getWorld().teleport(p, "spawn");
            return;
        }
        if (!isCurrentlyPlaying(p)) {

            currentlyPlaying.add(new JumpNRunPlayer(p, jumpNRun, System.currentTimeMillis() / 1000));


            CorePlayer corePlayer = CoreSystem.getInstance().getCorePlayer(p);
            LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(corePlayer.getUuid());

            jumpandrunItems(p);
            LobbyPlugin.getInstance().getBackpackManager().getPetHandler().despawnPet(p);
            LobbyWorld.ONE_ISLAND.getWorld().teleport(p, jumpNRun.getStartLocation());

            if (LobbyPlugin.getInstance().getOneHitManager().isFighting(p)) {
                LobbyPlugin.getInstance().getOneHitManager().leave(p);
                jumpandrunItems(p);
                LobbyPlugin.getInstance().getBackpackManager().getPetHandler().despawnPet(p);
                LobbyWorld.ONE_ISLAND.getWorld().teleport(p, jumpNRun.getStartLocation());
                if (!lp.hasJumpnrunMade(jumpNRun)) {
                    p.sendMessage("§8[§7§l!§8] §fJump and Run §8» §7Du spielst nun das §e" + jumpNRun.getJumpandrunname() + "§7 §7Jump and Run. Zum §cbeenden §7die §fEisentür §7klicken!");
                } else {
                    p.sendMessage("§8[§7§l!§8] §fJump and Run §8» §cDu hast dieses Jump and Run bereits gespielt kannst es aber trotzdem wiederholen. Deshalb erhälst du auch keine Belohnung mehr!");
                }

            } else if (!lp.hasJumpnrunMade(jumpNRun)) {
                p.sendMessage("§8[§7§l!§8] §fJump and Run §8» §7Du spielst nun das §e" + jumpNRun.getJumpandrunname() + "§7 §7Jump and Run. Zum §cbeenden §7die §fEisentür §7klicken!");
            } else {
                p.sendMessage("§8[§7§l!§8] §fJump and Run §8» §cDu hast dieses Jump and Run bereits gespielt kannst es aber trotzdem wiederholen. Deshalb erhälst du auch keine Belohnung mehr!");
            }
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
                title.send(p);
                if (jnrPlayer.getCheckpoint() <= 1) {
                    LobbyWorld.ONE_ISLAND.getWorld().teleport(p, jnrPlayer.getJumpNRun().getStartLocation());
                } else {
                    p.teleport(jnrPlayer.getJumpNRun().getCheckpoints()[jnrPlayer.getCheckpoint() - 1]);
                }
            }
        }
    }

    public void tpToCheckpoint(Player p) {
        JumpNRunPlayer jnrPlayer = getCurrentlyPlaying(p);

        if (jnrPlayer != null) {
            if (jnrPlayer.getCheckpoint() == 0) {
                LobbyWorld.ONE_ISLAND.getWorld().teleport(p, jnrPlayer.getJumpNRun().getStartLocation());
            } else {
                p.teleport(jnrPlayer.getJumpNRun().getCheckpoints()[jnrPlayer.getCheckpoint() - 1]);
            }
        }
    }

    @Override
    public void setCancel(Player p) {
        if (isCurrentlyPlaying(p)) {
            LobbyWorld.ONE_ISLAND.getWorld().teleportSilently(p, "spawn");
            PlayerJoinListener.setLobbyItems(p);
            removePlaying(p);
            LobbyPlugin.getInstance().getMessenger().send(p, "§cDu hast das Jump and Run §oerfolgreich §cbeendet!");

        }
    }

    @Override
    public boolean isJumping(Player p) {
        return currentlyPlaying.contains(p);
    }

    @Override
    public void setFinish(Player p) {
        CorePlayer corePlayer = CoreSystem.getInstance().getCorePlayer(p);
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(corePlayer.getUuid());

        if (isCurrentlyPlaying(p)) {
            JumpNRunPlayer jnrPlayer = getCurrentlyPlaying(p);
            JumpNRun jumpNRun = jnrPlayer.getJumpNRun();
            int checkpoint = jnrPlayer.getCheckpoint();
            long bestTime = lp.getBestJumpNRunTime(jumpNRun);
            long time = System.currentTimeMillis() / 1000 - jnrPlayer.getTime();

            Calendar calendarTime = Calendar.getInstance(TimeZone.getTimeZone("CEST"));
            calendarTime.setTimeInMillis(time * 1000);

            if (checkpoint == jumpNRun.getCheckpoints().length) {
                if (lp.hasJumpnrunMade(jumpNRun)) {
                    LobbyPlugin.getInstance().getMessenger().send(p, "§2Du hast das Jump and Run §aerfolgreich§2 wiederholt! Du hast §f"
                            + (calendarTime.get(Calendar.HOUR) > 0 ? " " + calendarTime.get(Calendar.HOUR) + " Stunden" : "")
                            + (calendarTime.get(Calendar.MINUTE) > 0 ? " " + calendarTime.get(Calendar.MINUTE) + " Minuten" : "")
                            + (calendarTime.get(Calendar.SECOND) > 0 ? " " + calendarTime.get(Calendar.SECOND) + " Sekunden" : "")
                            + "§2 gebraucht!");


                    Calendar calendarBestTime = Calendar.getInstance(TimeZone.getTimeZone("CEST"));
                    calendarBestTime.setTimeInMillis(bestTime * 1000);

                    if (time < bestTime && bestTime != -1) {
                        LobbyPlugin.getInstance().getMessenger().send(p, "§aDu hast deinen alten Rekord von §7§o"
                                + (calendarBestTime.get(Calendar.HOUR) > 0 ? " " + calendarBestTime.get(Calendar.HOUR) + " Stunden" : "")
                                + (calendarBestTime.get(Calendar.MINUTE) > 0 ? " " + calendarBestTime.get(Calendar.MINUTE) + " Minuten" : "")
                                + (calendarBestTime.get(Calendar.SECOND) > 0 ? " " + calendarBestTime.get(Calendar.SECOND) + " Sekunden" : "")
                                + "§a geknackt! §oHerzlichen Glückwunsch");
                    } else if (bestTime != -1) {
                        LobbyPlugin.getInstance().getMessenger().send(p, "§7Dein Rekord ist weiterhin §f§o"
                                + (calendarBestTime.get(Calendar.HOUR) > 0 ? " " + calendarBestTime.get(Calendar.HOUR) + " Stunden" : "")
                                + (calendarBestTime.get(Calendar.MINUTE) > 0 ? " " + calendarBestTime.get(Calendar.MINUTE) + " Minuten" : "")
                                + (calendarBestTime.get(Calendar.SECOND) > 0 ? " " + calendarBestTime.get(Calendar.SECOND) + " Sekunden" : "")
                                + "§7!");
                    }
                } else {
                    corePlayer.addCoins(100);
                    LobbyPlugin.getInstance().getMessenger().send(p, "§2Du hast das Jump and Run §aerfolgreich §2zum erten Mal fertig gespielt! Du hast §f"
                            + (calendarTime.get(Calendar.HOUR) > 0 ? " " + calendarTime.get(Calendar.HOUR) + " Stunden" : "")
                            + (calendarTime.get(Calendar.MINUTE) > 0 ? " " + calendarTime.get(Calendar.MINUTE) + " Minuten" : "")
                            + (calendarTime.get(Calendar.SECOND) > 0 ? " " + calendarTime.get(Calendar.SECOND) + " Sekunden" : "")
                            + "§2 gebraucht! §8[§a+100 Coins§8]");
                }

                PlayerJoinListener.setLobbyItems(p);
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


    public void jumpandrunItems(Player p) {
        p.getInventory().clear();

        p.setGameMode(GameMode.ADVENTURE);
        p.getActivePotionEffects().clear();
        p.removePotionEffect(PotionEffectType.INVISIBILITY);

        p.setMaxHealth(20);
        p.setHealth(20);
        p.setFoodLevel(20);

        if (p.hasPermission("lobby.silenthub")) {
            p.getInventory().setItem(1, HotbarItems.PRIVATE_LOBBY);
        } else if (!LobbyPlugin.getInstance().getPlayerHiderManager().isHidden(p)) {
            p.getInventory().setItem(0, HotbarItems.HIDE_PLAYERS);
        } else {
            p.getInventory().setItem(0, HotbarItems.SHOW_PLAYERS);
        }

        if (LobbyPlugin.getInstance().getSilentLobbyManager().isActivatedSilentHub(p)) {
            p.getInventory().setItem(1, HotbarItems.LEAVE_PRIVATE_LOBBY);
            p.getInventory().setItem(0, HotbarItems.LOBBY_HIDER_UNAVAILABLE);
        } else if (LobbyPlugin.getInstance().getPlayerHiderManager().isHidden(p)) {
            p.getInventory().setItem(0, HotbarItems.SHOW_PLAYERS);
        } else {
            p.getInventory().setItem(0, HotbarItems.HIDE_PLAYERS);
        }

        p.getInventory().setItem(8, HotbarItems.LEAVE_JUMPNRUN);
        p.getInventory().setItem(7, HotbarItems.TO_CHECKPOINT);

    }

    private JumpNRunPlayer getCurrentlyPlaying(Player p) {
        for (JumpNRunPlayer player : currentlyPlaying) {
            if (player.getPlayer().equals(p)) {
                return player;
            }
        }
        return null;
    }

    public boolean isCurrentlyPlaying(Player p) {
        return getCurrentlyPlaying(p) != null;
    }

    private void removePlaying(Player p) {
        LobbyPlayer lobbyPlayer = LobbyPlugin.getInstance().getLobbyPlayer(p);
        if (lobbyPlayer.getSettings().isScoreboard()) {
            lobbyPlayer.getCorePlayer().getScoreboard().setNewObjective(new SidebarObjective());
        }

        p.setGameMode(GameMode.SURVIVAL);

        if (getCurrentlyPlaying() != null) {
            currentlyPlaying.remove(getCurrentlyPlaying(p));
        }

    }

}

