/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.api.player;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.coresystem.api.bukkit.player.plugin.GamePlayer;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.enums.BankProgress;
import eu.mcone.lobby.api.enums.Item;
import eu.mcone.lobby.api.enums.Progress;
import eu.mcone.lobby.api.gang.Gang;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;

public class LobbyPlayer extends GamePlayer<LobbyPlayerProfile> {

    @Getter
    @Setter
    private Gang gang;
    @Getter
    private List<Item> items;
    @Getter
    private int chests, progressId, bankprogressId;
    @Getter
    @Setter
    private LobbySettings settings;
    @Getter
    @Setter
    private Map<String, Long> secrets;

    public LobbyPlayer(CorePlayer corePlayer) {
        super(corePlayer);
    }

    public LobbyPlayerProfile reload() {
        LobbyPlayerProfile profile = super.reload();

        this.items = profile.getItemList();
        this.chests = profile.getChests();
        this.progressId = profile.getProgressId();
        this.bankprogressId = profile.getBankprogressId();
        this.settings = profile.getSettings();
        this.secrets = profile.getSecrets();
        LobbyPlugin.getInstance().registerLobbyPlayer(this);

        return profile;
    }

    @Override
    protected LobbyPlayerProfile loadData() {
        return LobbyPlugin.getInstance().loadGameProfile(corePlayer.bukkit(), LobbyPlayerProfile.class);
    }

    @Override
    public void saveData() {
        LobbyPlugin.getInstance().saveGameProfile(new LobbyPlayerProfile(corePlayer.bukkit(), items, chests, progressId, bankprogressId, settings, secrets));
    }

    public boolean isInGang() {
        return gang != null;
    }

    public void addItem(Item item) {
        if (!items.contains(item)) {
            items.add(item);
            saveData();
        }
    }

    public void removeItem(Item item) {
        if (items.contains(item)) {
            items.remove(item);
            saveData();
        }
    }

    public void buyItem(Player p, Item item) {
        if (!hasItem(item)) {
            CorePlayer cp = CoreSystem.getInstance().getCorePlayer(p);

            if ((cp.getCoins() - item.getEmeralds()) >= 0) {
                cp.removeCoins(item.getEmeralds());
                addItem(item);

                p.closeInventory();
                LobbyPlugin.getInstance().getMessager().send(p, "§2Du hast erfolgreich das Item " + item.getName() + "§2 gekauft!");
                p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 1);
            } else {
                p.closeInventory();
                LobbyPlugin.getInstance().getMessager().send(p, "§4Du hast nicht genügend Coins!");
                p.playSound(p.getLocation(), Sound.NOTE_BASS, 1, 1);
            }
        } else {
            p.closeInventory();
            LobbyPlugin.getInstance().getMessager().send(p, "§4Du besitzt dieses Item bereits!");
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1, 1);
        }
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

    public boolean hasItem(Item i) {
        return items.contains(i);
    }

    private void addItemTemporary(Item i) {
        if (!items.contains(i)) {
            items.add(i);
        }
    }

    private void removeItemTemporary(Item i) {
        items.remove(i);
    }

    public void setProgress(Progress progress) {
        this.progressId = progress.getId();
        saveData();
    }

    public void setBankProgress(BankProgress bankprogress) {
        this.progressId = bankprogress.getId();
        saveData();
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
