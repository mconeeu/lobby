/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.api.player;

import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.gameapi.api.player.GameAPIPlayer;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.enums.BankProgress;
import eu.mcone.lobby.api.enums.JumpNRun;
import eu.mcone.lobby.api.enums.Progress;
import eu.mcone.lobby.api.gang.Gang;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

public class LobbyPlayer extends GameAPIPlayer<LobbyPlayerProfile> {

    @Getter @Setter
    private Gang gang;
    @Getter
    private int chests, progressId, bankprogressId;
    @Getter @Setter
    private LobbySettings settings;
    @Getter
    @Setter
    private Map<String, Long> secrets;
    @Getter
    private Map<JumpNRun, Long> jumpnruns;

    public LobbyPlayer(CorePlayer corePlayer) {
        super(LobbyPlugin.getPlugin(), corePlayer);
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
        super.saveData();
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
