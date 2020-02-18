/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.gang;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.lobby.api.LobbyAddon;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.gang.GangSettings;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.gang.command.GangCMD;
import eu.mcone.lobby.gang.listener.LobbyPlayerLoadedListener;
import eu.mcone.lobby.gang.listener.NpcInteractListener;
import lombok.Getter;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class LobbyGang extends LobbyAddon {

    @Getter
    private static LobbyGang instance;
    @Getter
    private List<Gang> gangs;

    @Override
    public void onEnable() {
        instance = this;

        this.gangs = new ArrayList<>();
        LobbyPlugin.getInstance().registerCommands(new GangCMD(this));
        LobbyPlugin.getInstance().registerEvents(
                new LobbyPlayerLoadedListener(),
                new NpcInteractListener()
        );

        reload();
    }

    @Override
    public void onDisable() {}

    @Override
    public void reload() {
        gangs.clear();

        for (Document entry : CoreSystem.getInstance().getMongoDB().getCollection("lobby_gangs").find()) {
            gangs.add(new Gang(
                    UUID.fromString(entry.getString("uuid")),
                    entry.getString("name"),
                    UUID.fromString(entry.getString("leader")),
                    entry.get("members", new HashMap<>()),
                    entry.get("mods", new ArrayList<>()),
                    entry.get("invites", new ArrayList<>()),
                    CoreSystem.getInstance().getGson().fromJson(entry.get("settings", Document.class).toJson(), GangSettings.class)
            ));
        }
    }

    public void createGang(final Player leader) {
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(leader.getUniqueId());

        if (lp.isInGang()) {
            leader.sendMessage(Gang.GANG_PREFIX + "§4Du bist bereits in einer anderen Gang!");
            return;
        }

        Gang result = new Gang(
                UUID.randomUUID(),
                leader.getName()+"\'s Gang",
                leader.getUniqueId(),
                new HashMap<String, String>(){{
                    put(leader.getName(),leader.getUniqueId().toString());
                }},
                new ArrayList<>(),
                new ArrayList<>(),
                new GangSettings()
        );

        gangs.add(result);
        lp.setGang(result);

        Bukkit.getScheduler().runTaskAsynchronously(LobbyPlugin.getInstance(), () -> Gang.COLLECTION.insertOne(
                new Document("uuid", result.getUuid().toString())
                        .append("name", result.getName())
                        .append("leader", result.getLeader().toString())
                        .append("members", result.getMembers())
                        .append("mods", result.getMods())
                        .append("invites", result.getInvites())
                        .append("settings", Document.parse(CoreSystem.getInstance().getGson().toJson(result.getSettings(), GangSettings.class)))
        ));
    }

    void removeGang(Gang gang) {
        gangs.remove(gang);
    }

    public boolean notifyIfGangNotExists(UUID gangUuid, Player target) {
        Gang gang = getGang(gangUuid);

        if (gang != null) {
            return true;
        } else {
            target.sendMessage(Gang.GANG_PREFIX + "§4Diese Gang existiert nicht!");
            return false;
        }
    }

    public Gang getPlayersGang(UUID uuid) {
        for (Gang gang : gangs) {
            if (gang.getMembers().containsValue(uuid.toString())) {
                return gang;
            }
        }

        return null;
    }

    public Gang getGang(UUID uuid) {
        for (Gang gang : gangs) {
            if (gang.getUuid().equals(uuid)) {
                return gang;
            }
        }

        return null;
    }
}
