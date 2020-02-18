/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.api.player;

import eu.mcone.gameapi.api.player.GamePlayer;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.api.enums.BankProgress;
import eu.mcone.lobby.api.enums.JumpNRun;
import eu.mcone.lobby.api.enums.LobbyItem;
import eu.mcone.lobby.api.enums.Progress;
import eu.mcone.lobby.api.gang.Gang;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Map;

public class LobbyPlayer extends eu.mcone.coresystem.api.bukkit.player.plugin.GamePlayer<LobbyPlayerProfile> {

    @Getter
    private final GamePlayer gamePlayer;

    @Getter
    @Setter
    private Gang gang;
    @Getter
    private int chests, progressId, bankprogressId;
    @Getter
    @Setter
    private LobbySettings settings;
    @Getter
    @Setter
    private Map<String, Long> secrets;
    @Getter
    private Map<JumpNRun, Long> jumpnruns;

    public LobbyPlayer(GamePlayer gamePlayer) {
        super(gamePlayer.getCorePlayer());
        this.gamePlayer = gamePlayer;
    }

    public LobbyPlayerProfile reload() {
        LobbyPlayerProfile profile = super.reload();

        this.chests = profile.getChests();
        this.progressId = profile.getProgressId();
        this.bankprogressId = profile.getBankprogressId();
        this.settings = profile.getSettings();
        this.secrets = profile.getSecrets();
        this.jumpnruns = profile.getJumpnrunSet();

        return profile;
    }

    @Override
    protected LobbyPlayerProfile loadData() {
        return LobbyPlugin.getInstance().loadGameProfile(corePlayer.bukkit(), LobbyPlayerProfile.class);
    }

    @Override
    public void saveData() {
        LobbyPlugin.getInstance().saveGameProfile(new LobbyPlayerProfile(corePlayer.bukkit(), chests, progressId, bankprogressId, settings, secrets, jumpnruns));
    }

    public boolean isInGang() {
        return gang != null;
    }

    public void addChests(int amount) {
        chests += amount;
        saveData();
    }

    public void removeChests(int preAmount) {
        if (chests - preAmount < 0) {
            preAmount = chests;
            LobbyPlugin.getInstance().sendConsoleMessage("§7Tried to remove more coins than Player §f" + corePlayer.getName() + "§7 has! (" + chests + "-" + preAmount + ")");
        }

        final int amount = preAmount;
        this.chests -= amount;
        saveData();
    }

    public boolean hasLobbyItem(LobbyItem item) {
        return gamePlayer.hasBackpackItem(item.getCategory().name(), item.getId());
    }

    public void addLobbyItem(LobbyItem item) {
        gamePlayer.addBackpackItem(
                item.getCategory().name(),
                LobbyPlugin.getInstance().getBackpackManager().getBackpackItem(item.getCategory().name(), item.getId())
        );
    }

    public void removeLobbyItem(LobbyItem item) {
        gamePlayer.removeBackpackItem(
                item.getCategory().name(),
                LobbyPlugin.getInstance().getBackpackManager().getBackpackItem(item.getCategory().name(), item.getId())
        );
    }

    public void setProgress(Progress progress) {
        this.progressId = progress.getId();
        saveData();
    }

    public void setBankProgress(BankProgress bankprogress) {
        this.bankprogressId = bankprogress.getId();
        saveData();
    }

    public void setJumpnrunBestTime(JumpNRun jumpnrun, Long time) {
        jumpnruns.put(jumpnrun, time);
        saveData();
    }

    public long getBestJumpNRunTime(JumpNRun jumpNRun) {
        if (hasJumpnrunMade(jumpNRun)) {
            return jumpnruns.get(jumpNRun);
        } else {
            return -1;
        }
    }

    public void teleportAnimation(String location) {
        teleportAnimation(LobbyWorld.ONE_ISLAND.getWorld().getLocation(location));
    }

    public void teleportAnimation(Location location) {
        Player player = corePlayer.bukkit();
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(player);
        LobbySettings settings = lp.getSettings();

        if (settings.isAllowAnimation()) {
            for (Player all : Bukkit.getOnlinePlayers()) {
                all.spigot().playEffect(player.getLocation(), Effect.SMALL_SMOKE, 1, 1, 1, 1, 1, 3, 30, 15);
                all.hidePlayer(player);
            }

            player.setGameMode(GameMode.SPECTATOR);
            player.playSound(player.getLocation(), Sound.GLASS, 3, 2);


            player.playSound(player.getLocation(), Sound.CLICK, 3, 2);
            player.removePotionEffect(PotionEffectType.CONFUSION);
            player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, Integer.MAX_VALUE, 1));

            player.teleport(new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ(), player.getLocation().getPitch(),
                    (float) (player.getLocation().getYaw() + 90.0)));

            Bukkit.getScheduler().runTaskLater(LobbyPlugin.getInstance(), () -> {
                player.teleport(new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ(), player.getLocation().getPitch(),
                        (float) (player.getLocation().getYaw() + 100.0)).add(0, 20, 0));
                player.playSound(player.getLocation(), Sound.ENDERDRAGON_WINGS, 3, 2);
                player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 3, 2);
                player.setExp(0);

                Bukkit.getScheduler().runTaskLater(LobbyPlugin.getInstance(), () -> {
                    player.teleport(new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ(), player.getLocation().getPitch(),
                            (float) (player.getLocation().getYaw() + 100.0)).add(0, 25, 0));
                    player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 3, 2);

                    Bukkit.getScheduler().runTaskLater(LobbyPlugin.getInstance(), () -> {
                        player.teleport(new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ(), player.getLocation().getPitch(),
                                (float) (player.getLocation().getYaw() + 100.0)).add(0, 20, 0));
                        player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 3, 2);


                        ///////////////////
                        //BACK

                        Bukkit.getScheduler().runTaskLater(LobbyPlugin.getInstance(), () -> {
                            player.teleport(new Location(location.getWorld(), location.getX(), location.getY(), location.getZ(), location.getPitch(),
                                    (float) (location.getYaw() + 90.0)).add(0, 65, 0));

                            player.teleport(new Location(player.getLocation().getWorld(), player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ(), player.getLocation().getPitch(),
                                    (float) (player.getLocation().getYaw() + 90.0)));
                            player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 3, 3);

                            Bukkit.getScheduler().runTaskLater(LobbyPlugin.getInstance(), () -> {
                                player.teleport(new Location(player.getLocation().getWorld(), player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ(), player.getLocation().getPitch(),
                                        (float) (player.getLocation().getYaw() + 90.0)).subtract(0, 35, 0));
                                player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 3, 2);

                                player.teleport(new Location(player.getLocation().getWorld(), player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ(), player.getLocation().getPitch(),
                                        (float) (player.getLocation().getYaw() + 90.0)));
                                player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 3, 2);

                                Bukkit.getScheduler().runTaskLater(LobbyPlugin.getInstance(), () -> {
                                    player.teleport(new Location(player.getLocation().getWorld(), player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ(), player.getLocation().getPitch(),
                                            (float) (player.getLocation().getYaw() + 90)).subtract(0, 18, 0));

                                    player.teleport(new Location(player.getLocation().getWorld(), player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ(), player.getLocation().getPitch(),
                                            (float) (player.getLocation().getYaw() + 90.0)));
                                    player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 3, 2);


                                    Bukkit.getScheduler().runTaskLater(LobbyPlugin.getInstance(), () -> {
                                        player.setGameMode(GameMode.SURVIVAL);

                                        for (Player all : Bukkit.getOnlinePlayers()) {
                                            if ((!LobbyPlugin.getInstance().getPlayerHiderManager().isHidden(all) && !LobbyPlugin.getInstance().getPlayerHiderManager().isHidden(player))
                                                    && (!LobbyPlugin.getInstance().getSilentLobbyManager().isActivatedSilentHub(all) && !LobbyPlugin.getInstance().getSilentLobbyManager().isActivatedSilentHub(player))) {
                                                all.showPlayer(player);
                                            }
                                        }

                                        player.teleport(location);
                                        player.removePotionEffect(PotionEffectType.CONFUSION);
                                        player.playSound(player.getLocation(), Sound.FIREWORK_TWINKLE, 3, 2);
                                    }, 12);
                                }, 8);
                            }, 7);
                        }, 8);
                    }, 13);
                }, 12);
            }, 1);
        } else {
            player.teleport(location);
            player.playSound(player.getLocation(), Sound.GLASS, 3, 2);
        }
    }

    public boolean hasJumpnrunMade(JumpNRun jumpnrun) {
        return jumpnruns.containsKey(jumpnrun);
    }

    public boolean checkAndAddSecret(String name, long time) {
        if (secrets.containsKey(name)) {
            return false;
        } else {
            secrets.put(name, time);
            saveData();
            return true;
        }
    }

}
