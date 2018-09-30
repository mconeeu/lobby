/*
 * Copyright (c) 2017 - 2018 Dominik Lippl, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.api.gang;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface Gang {

    String GANG_PREFIX = CoreSystem.getInstance().getTranslationManager().get("lobby.adventure.prefix.gang");

    String getName();

    UUID getUuid();

    UUID getLeader();

    Map<String, String> getMembers();

    List<String> getMods();

    List<String> getInvites();

    GangSettings getSettings();

    void invitePlayer(Player player, String targetName);

    void acceptInvite(Player player);

    void declineInvite(Player player);

    void promotePlayer(Player player, String targetName);

    void demotePlayer(Player player, String targetName);

    void kickPlayer(Player player, String targetName);

    void changeName(Player player, String newName);

    void leaveGang(Player player);

    void deleteGang(Player player);

    void broadcast(String message, UUID... skip);

    static String getPrefix(Gang gang, String uuid) {
        if (gang.getLeader().toString().equals(uuid)) {
            return "§8[§cPräsident§8] §7";
        } else if (gang.getMods().contains(uuid)) {
            return "§8[§9Moderator§8] §7";
        } else {
            return "§8[§7Mitglied§8] §7";
        }
    }

}
