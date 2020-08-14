/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.api.player;

import eu.mcone.gameapi.api.player.GamePlayer;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.api.enums.*;
import eu.mcone.lobby.api.enums.bank.BankRobberySmallProgress;
import eu.mcone.lobby.api.enums.bank.central.BankProgress;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.DisplaySlot;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

public class LobbyPlayer extends eu.mcone.coresystem.api.bukkit.player.plugin.GamePlayer<LobbyPlayerProfile> {

    @Getter
    private final GamePlayer gamePlayer;

    @Getter
    private int chests, progressId, bankprogressId, centralbankprogressId, tutorialStoryProgressId, traderStoryProgressID;
    @Getter
    private Date dailyReward;
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
        this.centralbankprogressId = profile.getCentralbankprogressId();
        this.tutorialStoryProgressId = profile.getTuturialStoryId();
        this.traderStoryProgressID = profile.getTraderStoryProgressID();
        this.dailyReward = profile.getDailyReward();
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
        LobbyPlugin.getInstance().saveGameProfile(new LobbyPlayerProfile(corePlayer.bukkit(), chests, progressId, bankprogressId, centralbankprogressId, tutorialStoryProgressId, traderStoryProgressID, dailyReward, settings, secrets, jumpnruns));
    }

    public void reloadScoreboardIfEnabled() {
        if (settings.isScoreboard() && getCorePlayer().getScoreboard().getObjective(DisplaySlot.SIDEBAR) != null) {
            getCorePlayer().getScoreboard().getObjective(DisplaySlot.SIDEBAR).reload();
        }
    }

    public void resetDataAndHotbarItems() {
        LobbyPlugin.getInstance().resetPlayerDataAndHotbarItems(bukkit());
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

    public void setProgress(StoryProgress storyProgress) {
        this.progressId = storyProgress.getId();
        saveData();
    }

    public void setBankProgress(BankRobberySmallProgress bankprogress) {
        this.bankprogressId = bankprogress.getId();
        saveData();
    }

    public void setCentralBankProgress(BankProgress bankprogress) {
        this.centralbankprogressId = bankprogress.getId();
        saveData();
    }

    public void setTutorialStoryProgress(TutorialStory tutorialStoryProgress) {
        this.tutorialStoryProgressId = tutorialStoryProgress.getId();
        saveData();
    }

    public void setTraderStoryProgress(TraderProgress traderStoryProgress) {
        this.traderStoryProgressID = traderStoryProgress.getId();
        saveData();
    }

    public void setDailyReward() {
        this.dailyReward = Calendar.getInstance(TimeZone.getTimeZone("Europe/Berlin")).getTime();
        saveData();
    }

    public Date getLastDailyRewardDate() {
        if (dailyReward != null) {
            return dailyReward;
        } else {
            return new Date(System.currentTimeMillis());
        }
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

        if (location != null) {
            if (settings.isAllowAnimation() && !corePlayer.getWorld().equals(LobbyWorld.OFFICE.getWorld()) && !corePlayer.getWorld().equals(LobbyWorld.CAVE.getWorld())) {
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
        } else {
            LobbyPlugin.getInstance().getMessenger().send(player, "§4Dieser Ort ist momentan nicht erreichbar!");
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

    public int getSecrets() {
        return secrets.size();
    }

}
