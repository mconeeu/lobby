/*
 * Copyright (c) 2017 - 2018 Dominik Lippl, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.api.player;

import com.mongodb.client.MongoCollection;
import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.enums.Item;
import eu.mcone.lobby.api.enums.Progress;
import eu.mcone.lobby.api.gang.Gang;
import lombok.Getter;
import lombok.Setter;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.inc;
import static com.mongodb.client.model.Updates.set;

public class LobbyPlayer {

    @Getter
    private final CorePlayer corePlayer;

    @Getter
    @Setter
    private Gang gang;
    @Getter
    private List<Item> items;
    @Getter
    private int chests, progressId;
    @Getter @Setter
    private LobbySettings settings;

    public LobbyPlayer(CorePlayer corePlayer) {
        this.corePlayer = corePlayer;
        reload();
    }

    public void reload() {
        Document profileEntry = CoreSystem.getInstance().getMongoDB().getCollection("lobby_profile").find(eq("uuid", corePlayer.getUuid().toString())).first();
        if (profileEntry != null) {
            this.items = new ArrayList<>();
            for (int id : profileEntry.get("items", new ArrayList<Integer>())) {
                items.add(Item.getItemByID(id));
            }

            this.chests = profileEntry.getInteger("chests");
            this.progressId = profileEntry.getInteger("progress");
            this.settings = CoreSystem.getInstance().getGson().fromJson(
                    profileEntry.get("settings", Document.class).toJson(),
                    LobbySettings.class
            );
        } else {
            this.items = new ArrayList<>();
            this.chests = 0;
            this.progressId = 0;
            this.settings = new LobbySettings();

            CoreSystem.getInstance().getMongoDB().getCollection("lobby_profile").insertOne(
                    new Document("uuid", corePlayer.getUuid().toString())
                            .append("items", items)
                            .append("chests", chests)
                            .append("progress", progressId)
                            .append("settings", settings)
            );
        }

        LobbyPlugin.getInstance().registerLobbyPlayer(this);
    }

    public boolean isInGang() {
        return gang != null;
    }

    public void addItem(Item item) {
        if (!items.contains(item)) {
            items.add(item);

            Bukkit.getScheduler().runTaskAsynchronously(LobbyPlugin.getInstance(), () ->
                    CoreSystem.getInstance().getMongoDB().getCollection("lobby_profile").updateOne(
                            eq("uuid", corePlayer.getUuid().toString()),
                            set("items", items)
                    )
            );
        }
    }

    public void removeItem(Item item) {
        if (items.contains(item)) {
            items.remove(item);

            Bukkit.getScheduler().runTaskAsynchronously(LobbyPlugin.getInstance(), () ->
                    CoreSystem.getInstance().getMongoDB().getCollection("lobby_profile").updateOne(
                            eq("uuid", corePlayer.getUuid().toString()),
                            set("items", items)
                    )
            );
        }
    }

    public void buyItem(Player p, Item item) {
        if (!hasItem(item)) {
            CorePlayer cp = CoreSystem.getInstance().getCorePlayer(p);

            if ((cp.getCoins() - item.getCoins()) >= 0) {
                cp.removeCoins(item.getCoins());
                addItem(item);

                p.closeInventory();
                p.sendMessage(CoreSystem.getInstance().getTranslationManager().get("lobby.prefix") + "§2Du hast erfolgreich das Item " + item.getName() + "§2 gekauft!");
                p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 1);
            } else {
                p.closeInventory();
                p.sendMessage(CoreSystem.getInstance().getTranslationManager().get("lobby.prefix") + "§4Du hast nicht genügend Coins!");
                p.playSound(p.getLocation(), Sound.NOTE_BASS, 1, 1);
            }
        } else {
            p.closeInventory();
            p.sendMessage(CoreSystem.getInstance().getTranslationManager().get("lobby.prefix") + "§4Du besitzt dieses Item bereits!");
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1, 1);
        }
    }

    public void addChests(int amount) {
        chests += amount;

        Bukkit.getScheduler().runTaskAsynchronously(LobbyPlugin.getInstance(), () -> {
            MongoCollection<Document> chestsCollection = CoreSystem.getInstance().getMongoDB().getCollection("lobby_profile");
            if (chestsCollection.find(eq("uuid", corePlayer.getUuid().toString())).first() != null) {
                CoreSystem.getInstance().getMongoDB().getCollection("lobby_profile").updateOne(eq("uuid", corePlayer.getUuid().toString()), inc("chests", amount));
            } else {
                CoreSystem.getInstance().getMongoDB().getCollection("lobby_profile").insertOne(new Document("uuid", corePlayer.getUuid().toString()).append("chests", amount));
            }
        });
    }

    public void removeChests(int preAmount) {
        if (chests - preAmount < 0) {
            preAmount = chests;
            LobbyPlugin.getInstance().sendConsoleMessage("§7Tried to remove more coins than Player §f" + corePlayer.getName() + "§7 has! (" + chests + "-" + preAmount + ")");
        }

        final int amount = preAmount;
        this.chests -= amount;

        Bukkit.getScheduler().runTaskAsynchronously(LobbyPlugin.getInstance(), () ->
                CoreSystem.getInstance().getMongoDB().getCollection("lobby_profile").updateOne(eq("uuid", corePlayer.getUuid().toString()), inc("chests", -amount)));
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

        Bukkit.getScheduler().runTaskAsynchronously(LobbyPlugin.getInstance(), () ->
                CoreSystem.getInstance().getMongoDB().getCollection("lobby_profile").updateOne(eq("uuid", corePlayer.getUuid().toString()), set("progress", progress)));
    }

    public void updateSettings() {
        Bukkit.getScheduler().runTaskAsynchronously(LobbyPlugin.getInstance(), () ->
                CoreSystem.getInstance().getMongoDB().getCollection("lobby_profile").updateOne(
                        eq("uuid", corePlayer.getUuid().toString()),
                        set("settings", Document.parse(CoreSystem.getInstance().getGson().toJson(settings, LobbySettings.class)))
                )
        );
    }

    public Player bukkit() {
        return Bukkit.getPlayer(corePlayer.getUuid());
    }

}
