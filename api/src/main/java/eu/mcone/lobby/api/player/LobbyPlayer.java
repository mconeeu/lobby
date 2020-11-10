/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.api.player;

import eu.mcone.gameapi.api.player.GamePlayer;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.api.games.jumpnrun.JumpNRun;
import eu.mcone.lobby.api.items.LobbyItem;
import eu.mcone.lobby.api.player.settings.LobbySettings;
import eu.mcone.lobby.api.story.office.OfficeType;
import eu.mcone.lobby.api.story.progress.StoryProgress;
import eu.mcone.lobby.api.story.progress.TraderStoryProgress;
import eu.mcone.lobby.api.story.progress.TutorialStoryProgress;
import eu.mcone.lobby.api.story.progress.bank.BankRobberySmallProgress;
import eu.mcone.lobby.api.story.progress.bank.central.BankProgress;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
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

    public int removeChests(int amount) {
        if ((chests - amount) < 0) {
            amount = chests;
        }

        this.chests -= amount;
        saveData();

        return amount;
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

    public void setTutorialStoryProgress(TutorialStoryProgress tutorialStoryProgress) {
        this.tutorialStoryProgressId = tutorialStoryProgress.getId();
        saveData();
    }

    public void setTraderStoryProgress(TraderStoryProgress traderStoryProgress) {
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
        TeleportUtil.teleportWithAnimation(bukkit(), location);
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

    public int getSecretsCount() {
        return secrets.size();
    }

    public OfficeType getOffice() {
        for (OfficeType office : OfficeType.values()) {
            if (hasLobbyItem(office.getOfficeCard())) {
                return office;
            }
        }

        return null;
    }

    public boolean hasOffice() {
        return getOffice() != null;
    }

}
