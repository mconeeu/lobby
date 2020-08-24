package eu.mcone.lobby.story.office;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.api.story.office.OfficeType;
import eu.mcone.lobby.api.player.vanish.VanishPlayerVisibility;
import eu.mcone.lobby.api.story.office.OfficeManager;
import eu.mcone.lobby.api.player.HotbarItem;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.story.cmd.OfficeCMD;
import eu.mcone.lobby.story.listener.OfficeListener;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.entity.Player;

import java.util.*;


public class LobbyOfficeManager implements OfficeManager {

    private final Map<Player, Set<Player>> joinedPlayers;
    private final Map<Player, Set<Player>> invitedPlayers;

    public LobbyOfficeManager(LobbyPlugin plugin) {
        this.joinedPlayers = new HashMap<>();
        this.invitedPlayers = new HashMap<>();

        CoreSystem.getInstance().getVanishManager().registerVanishRule(10, (player, list) -> {
            for (Map.Entry<Player, Set<Player>> joinedEntry : LobbyOfficeManager.this.joinedPlayers.entrySet()) {
                if (!joinedEntry.getValue().contains(player)) {
                    list.removeAll(joinedEntry.getValue());
                }
            }
        });

        plugin.registerCommands(new OfficeCMD());
        plugin.registerEvents(new OfficeListener(this));
    }

    @Override
    public void joinOffice(Player p) {
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p.getUniqueId());
        OfficeType office = lp.getOffice();

        if (office != null) {
            joinOffice(p, p, office);
        } else {
            LobbyPlugin.getInstance().getMessenger().sendError(p, "Du hast kein Büro!");
        }
    }

    private void joinOffice(Player p, Player owner, OfficeType office) {
        LobbyPlugin.getInstance().getVanishManager().quitSilentLobby(p);
        LobbyPlugin.getInstance().getVanishManager().setVanishPlayerVisibility(p, VanishPlayerVisibility.EVERYBODY, false);

        if (joinedPlayers.containsKey(owner)) {
            joinedPlayers.get(owner).add(p);
        } else {
            joinedPlayers.put(owner, new HashSet<>(Collections.singleton(p)));
        }

        CoreSystem.getInstance().getVanishManager().recalculateVanishes();

        if (p.hasPermission("lobby.silenthub")) {
            p.getInventory().setItem(2, HotbarItem.SILENT_LOBBY_UNAVAILABLE_OFFICE_SILENTHUB);
            p.getInventory().setItem(3, null);
        } else {
            p.getInventory().setItem(2, null);
        }
        p.getInventory().setItem(0, HotbarItem.LOBBY_HIDER_UNAVAILABLE_OFFICE);

        office.teleport(p);
    }

    @Override
    public void quitOffice(Player player) {
        Player owner = null;

        for (Map.Entry<Player, Set<Player>> office : joinedPlayers.entrySet()) {
            if (office.getValue().contains(player)) {
                office.getValue().remove(player);
                owner = office.getKey();

                break;
            }
        }

        if (owner != null) {
            if (joinedPlayers.get(owner).size() <= 0) {
                joinedPlayers.remove(owner);
            }

            CoreSystem.getInstance().getVanishManager().recalculateVanishes();
        }

        LobbyPlugin.getInstance().getMessenger().sendInfo(player, "Du hast das Büro verlassen!");
        LobbyPlugin.getInstance().resetPlayerDataAndHotbarItems(player);
    }

    @Override
    public void inviteToOffice(Player owner, Player invited) {
        if (LobbyPlugin.getInstance().getLobbyPlayer(owner).hasOffice()) {
            if (invitedPlayers.containsKey(owner)) {
                invitedPlayers.get(owner).add(invited);
            } else {
                invitedPlayers.put(owner, new HashSet<>(Collections.singleton(invited)));
            }

            LobbyPlugin.getInstance().getMessenger().sendSuccess(owner, "Du hast ![" + invited.getName() + "] zu deinem Büro eingeladen!");
            LobbyPlugin.getInstance().getMessenger().send(
                    invited,
                    new ComponentBuilder("Der Spieler ")
                            .color(ChatColor.WHITE)
                            .append(owner.getName())
                            .color(ChatColor.WHITE).bold(true)
                            .append(" hat dich zu seinem Büro eingeladen")
                            .color(ChatColor.WHITE)
                            .append("\n")
                            .append("[Zum Büro telepotieren]")
                            .color(ChatColor.GREEN)
                            .bold(true)
                            .event(new HoverEvent(
                                    HoverEvent.Action.SHOW_TEXT,
                                    new ComponentBuilder("§7§oKlicke hier, um dich zum Büro zu teleportieren").create()
                            ))
                            .event(new ClickEvent(
                                    ClickEvent.Action.RUN_COMMAND,
                                    "/office " + owner.getName()
                            ))
                            .create()
            );
        } else {
            LobbyPlugin.getInstance().getMessenger().sendError(owner, "Du hast kein Büro, in das du jemand einladen kannst!");
        }
    }

    @Override
    public void acceptInvite(Player p, Player owner) {
        if (invitedPlayers.getOrDefault(owner, Collections.emptySet()).contains(p)) {
            LobbyPlayer lpi = LobbyPlugin.getInstance().getLobbyPlayer(owner.getUniqueId());
            OfficeType office = lpi.getOffice();

            invitedPlayers.get(owner).remove(p);

            if (office != null) {
                joinOffice(p, owner, office);
            } else {
                LobbyPlugin.getInstance().getMessenger().sendError(p, "Der Spieler !["+owner.getName()+"] hat kein Büro!");
            }
        } else {
            LobbyPlugin.getInstance().getMessenger().sendError(p, "Diese Einladung ist ungültig!");
        }
    }

    @Override
    public void kickFromOffice(Player owner, Player visitor) {
        if (joinedPlayers.containsKey(owner) && joinedPlayers.get(owner).contains(visitor)) {
            LobbyWorld.ONE_ISLAND.getWorld().teleportSilently(visitor, "spawn");
            LobbyPlugin.getInstance().getMessenger().sendInfo(visitor, "Du wurdest von !["+owner.getName()+"] aus dem Büro gekickt!");
        } else {
            LobbyPlugin.getInstance().getMessenger().sendError(visitor, "!["+visitor.getName()+"] ist nicht in deinem Büro!");
        }
    }

    @Override
    public void clearOffice(Player player) {
        if (joinedPlayers.containsKey(player)) {
            Set<Player> kick = joinedPlayers.get(player);
            for (Player visitor : kick) {
                if (!visitor.equals(player)) {
                    joinedPlayers.remove(visitor);
                }
            }
            CoreSystem.getInstance().getVanishManager().recalculateVanishes();

            for (Player visitor : kick) {
                if (!visitor.equals(player)) {
                    LobbyWorld.ONE_ISLAND.getWorld().teleportSilently(visitor, "spawn");
                    LobbyPlugin.getInstance().getMessenger().sendInfo(visitor, "Du wurdest von !["+player.getName()+"] aus dem Büro gekickt!");
                }
            }
        }
    }

    @Override
    public Set<Player> getPlayersInOffice(Player owner) {
        return joinedPlayers.getOrDefault(owner, Collections.emptySet());
    }

    @Override
    public Player getCurrentOfficeOwner(Player target) {
        for (Map.Entry<Player, Set<Player>> office : joinedPlayers.entrySet()) {
            if (office.getValue().contains(target)) {
                return office.getKey();
            }
        }

        return null;
    }

    @Override
    public boolean isInOffice(Player player) {
        return getCurrentOfficeOwner(player) != null;
    }

    @Override
    public boolean isInOwnOffice(Player player) {
        return getCurrentOfficeOwner(player).equals(player);
    }

    public void playerLeaved(Player player) {
        invitedPlayers.remove(player);

        for (Map.Entry<Player, Set<Player>> office : joinedPlayers.entrySet()) {
            if (office.getKey().equals(player)) {
                clearOffice(player);
                joinedPlayers.remove(player);
                return;
            } else {
                office.getValue().remove(player);
            }
        }
    }

}
