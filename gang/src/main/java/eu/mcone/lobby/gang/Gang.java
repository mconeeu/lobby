/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.gang;

import com.mongodb.client.MongoCollection;
import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.lobby.api.gang.GangSettings;
import lombok.Getter;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

public class Gang implements eu.mcone.lobby.api.gang.Gang {

    final static MongoCollection<Document> COLLECTION = CoreSystem.getInstance().getMongoDB().getCollection("lobby_gangs");
    private final static int MAX_MEMBERS = 21;

    @Getter
    private String name;
    @Getter
    private final UUID uuid, leader;
    @Getter
    private final Map<String, String> members;
    @Getter
    private final List<String> mods, invites;
    @Getter
    private final GangSettings settings;

    Gang(UUID uuid, String name, UUID leader, Map<String, String> members, List<String> mods, List<String> invites, GangSettings settings) {
        this.name = name;
        this.uuid = uuid;
        this.leader = leader;
        this.members = members;
        this.mods = mods;
        this.invites = invites;
        this.settings = settings;
    }

    public void invitePlayer(Player p, String targetName) {
        if ((settings.isModsCanInvite() && mods.contains(p.getUniqueId().toString())) || p.getUniqueId().equals(leader)) {
            UUID targetUuid;
            Player t = Bukkit.getPlayer(targetName);

            if (t != null) {
                targetUuid = t.getUniqueId();
            } else {
                targetUuid = CoreSystem.getInstance().getPlayerUtils().fetchUuid(targetName);
            }

            if (targetUuid != null) {
                if (!invites.contains(targetUuid.toString())) {
                    invites.add(targetUuid.toString());
                    broadcast("§7" + p.getName() + " hat §f" + targetName + "§7 in die Gang eingeladen");

                    if (t != null) {
                        t.spigot().sendMessage(
                                new ComponentBuilder(GANG_PREFIX)
                                        .append("Du wurdest von ")
                                        .color(ChatColor.GRAY)
                                        .append(p.getName())
                                        .color(ChatColor.WHITE)
                                        .append(" in die Gang ")
                                        .color(ChatColor.GRAY)
                                        .append(name)
                                        .color(ChatColor.DARK_GREEN)
                                        .append(" eingeladen!\n")
                                        .color(ChatColor.GRAY)
                                        .append("[ANNEHMEN]")
                                        .color(ChatColor.GREEN)
                                        .event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/gang accept " + uuid.toString()))
                                        .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Ganganfrage akzeptieren").color(ChatColor.GRAY).italic(true).create()))
                                        .append(" ")
                                        .append("[ABLEHNEN]")
                                        .color(ChatColor.RED)
                                        .event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/gang decline " + uuid.toString()))
                                        .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Ganganfrage ablehnen").color(ChatColor.GRAY).italic(true).create()))
                                        .create()
                        );
                    }

                    COLLECTION.updateOne(
                            eq("uuid", uuid.toString()),
                            set("invites", invites)
                    );
                } else {
                    p.sendMessage(GANG_PREFIX + "§4Dieser Spieler wurde bereits in die Gang eingeladen!");
                }
            } else {
                p.sendMessage(GANG_PREFIX + "§4Der Spieler §c" + targetName + "§4 war noch nie auf MC ONE! Du kannst ihm deshalb keine Einladung schicken!");
            }
        } else {
            p.sendMessage(GANG_PREFIX + "§4Du hast keine Berechtigung um Spieler in die Gang einzuladen!");
        }
    }

    public void acceptInvite(Player p) {
        if (invites.contains(p.getUniqueId().toString())) {
            if (members.size()+1 <= MAX_MEMBERS) {
                members.put(p.getName(), p.getUniqueId().toString());
                invites.remove(p.getUniqueId().toString());

                p.sendMessage(GANG_PREFIX + "§2Du bist erfolgreich der Gang §a" + name + "§2 beigetreten!");
                broadcast("§7Der Spieler §f" + p.getName() + "§7 ist erfolgreich der Gang beigetreten!", p.getUniqueId());

                COLLECTION.updateOne(
                        eq("uuid", uuid.toString()),
                        combine(
                                set("members", members),
                                set("invites", invites)
                        )
                );
            } else {
                p.sendMessage(GANG_PREFIX + "§4Du kannst der Gang nicht beitreten, da sie bereits voll ist!");
            }
        } else {
            p.sendMessage(GANG_PREFIX + "§4Du wurdest nicht in diese Gang eingeladen!");
        }
    }

    public void declineInvite(Player p) {
        if (invites.contains(p.getUniqueId().toString())) {
            invites.remove(p.getUniqueId().toString());
            p.sendMessage(GANG_PREFIX + "§2Du hast die Gang-Einladung erfolgreich abgelehnt");

            COLLECTION.updateOne(
                    eq("uuid", uuid.toString()),
                    set("invites", invites)
            );
        }
    }

    public void promotePlayer(Player p, String targetName) {
        if (p.getUniqueId().equals(leader)) {
            if (members.containsKey(targetName)) {
                String targetUuid = members.get(targetName);

                if (!mods.contains(targetUuid)) {
                    mods.add(targetUuid);
                    Player t = Bukkit.getPlayer(UUID.fromString(targetUuid));

                    if (t != null)
                        t.sendMessage(GANG_PREFIX + "§7Du wurdest von §f" + p.getName() + "§7 zum Gang-Moderator promotet!");
                    p.sendMessage(GANG_PREFIX + "§2Du hast den Spieler §a" + targetName + "§2 erfolgreich zum Moderator promotet!");
                    broadcast("§7" + p.getName() + " hat §f" + targetName + "§7 zum Gang-Moderator promotet!", UUID.fromString(targetUuid), p.getUniqueId());

                    COLLECTION.updateOne(
                            eq("uuid", uuid.toString()),
                            set("mods", mods)
                    );
                } else {
                    p.sendMessage(GANG_PREFIX + "§4Dieser Spieler ist bereits ein Moderator!");
                }
            } else {
                p.sendMessage(GANG_PREFIX + "§4Dieser Spieler befindet sich nicht in deiner Gang!");
            }
        } else {
            p.sendMessage(GANG_PREFIX + "§4Nur der §cPräsident§4 kann Spieler promoten!");
        }
    }

    public void demotePlayer(Player p, String targetName) {
        if (p.getUniqueId().equals(leader)) {
            if (members.containsKey(targetName)) {
                String targetUuid = members.get(targetName);

                if (mods.contains(targetUuid)) {
                    mods.remove(targetUuid);
                    Player t = Bukkit.getPlayer(UUID.fromString(targetUuid));

                    if (t != null)
                        t.sendMessage(GANG_PREFIX + "§7Du wurdest von §f" + p.getName() + "§7 zum Gang-Member demotet!");
                    p.sendMessage(GANG_PREFIX + "§2Du hast den Spieler §a" + targetName + "§2 erfolgreich zum Member demotet!");
                    broadcast("§7" + p.getName() + " hat §f" + targetName + "§7 zum Gang-Moderator promotet!", UUID.fromString(targetUuid), p.getUniqueId());

                    COLLECTION.updateOne(
                            eq("uuid", uuid.toString()),
                            combine(
                                    set("mods", mods),
                                    set("members", members)
                            )
                    );
                } else {
                    p.sendMessage(GANG_PREFIX + "§4Dieser Spieler ist kein Moderator!");
                }
            } else {
                p.sendMessage(GANG_PREFIX + "§4Dieser Spieler befindet sich nicht in deiner Gang!");
            }
        } else {
            p.sendMessage(GANG_PREFIX + "§4Nur der §cPräsident§4 kann Spieler demoten!");
        }
    }

    public void kickPlayer(Player p, String targetName) {
        if ((settings.isModsCanKick() && mods.contains(p.getUniqueId().toString())) || p.getUniqueId().equals(leader)) {
            if (members.containsKey(targetName)) {
                String targetUuid = members.get(targetName);

                members.remove(targetName);
                COLLECTION.updateOne(
                        eq("uuid", uuid.toString()),
                        set("members", members)
                );

                if (mods.contains(targetUuid)) {
                    mods.remove(targetUuid);
                    COLLECTION.updateOne(
                            eq("uuid", uuid.toString()),
                            set("mods", mods)
                    );
                }

                Player t = Bukkit.getPlayer(UUID.fromString(targetUuid));

                if (t != null)
                    t.sendMessage(GANG_PREFIX + "§7Du wurdest von §f" + p.getName() + "§7 aus der Gang gekickt!");
                p.sendMessage(GANG_PREFIX + "§2Du hast den Spieler §a" + targetName + "§2 erfolgreich aus der Gang gekickt!");
                broadcast("§7" + p.getName() + " hat §f" + targetName + "§7 aus der Gang gekickt!", UUID.fromString(targetUuid), p.getUniqueId());
            } else {
                p.sendMessage(GANG_PREFIX + "§4Dieser Spieler befindet sich nicht in deiner Gang!");
            }
        } else {
            p.sendMessage(GANG_PREFIX + "§4Du hast keine Berechtigung um einen Spieler aus der Gang zu kicken!");
        }
    }

    public void changeName(Player p, String newName) {
        if (p.getUniqueId().equals(leader)) {
            this.name = newName;
            COLLECTION.updateOne(
                    eq("uuid", uuid.toString()),
                    set("name", newName)
            );

            p.sendMessage(GANG_PREFIX + "§2Du hast den Namen der Gang erfolgreich zu §a" + newName + "§2 geändert!");
            broadcast("§7" + p.getName() + " hat den Gang-Namen zu §f" + newName + "§7 geändert!", p.getUniqueId());
        } else {
            p.sendMessage(GANG_PREFIX + "§4Nur der Leader kann den Namen der Gang ändern!");
        }
    }

    public void leaveGang(Player p) {
        if (members.containsValue(p.getUniqueId().toString())) {
            if (leader.equals(p.getUniqueId())) {
                deleteGang(p);
            } else {
                mods.remove(p.getUniqueId().toString());
                members.remove(p.getName(), p.getUniqueId().toString());

                COLLECTION.updateOne(
                        eq("uuid", uuid.toString()),
                        combine(
                                set("mods", mods),
                                set("members", members)
                        )
                );

                p.sendMessage(GANG_PREFIX + "§2Du hast die Gang erfolgreich verlassen!");
                broadcast("§7Der Spieler §f"+p.getName()+"§7 hat die Gang verlassen!", p.getUniqueId());
            }
        } else {
            p.sendMessage(GANG_PREFIX + "§4Du bist nicht mehr in dieser Gang!");
        }
    }

    public void deleteGang(Player p) {
        if (leader.equals(p.getUniqueId())) {
            LobbyGang.getInstance().removeGang(this);
            COLLECTION.deleteOne(eq("uuid", uuid.toString()));

            p.sendMessage(LobbyGang.getInstance().getGangs().toString());
            p.sendMessage(GANG_PREFIX + "§2Die Gang wurde erfolgreich gelöscht.");
            broadcast("§4Die Gang §c" + name + "§4 wurde von "+p.getName()+" aufgelöst.", p.getUniqueId());
        } else {
            p.sendMessage(GANG_PREFIX + "§4Nur der Leader kann die Gang auflösen!");
        }
    }

    public void broadcast(String message, UUID... skip) {
        for (String uuidString : members.values()) {
            UUID uuid = UUID.fromString(uuidString);
            if (Arrays.asList(skip).contains(uuid)) continue;

            Player p = Bukkit.getPlayer(uuid);
            if (p != null) p.sendMessage(GANG_PREFIX + message);
        }
    }

}
