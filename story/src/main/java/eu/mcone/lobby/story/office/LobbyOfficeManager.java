package eu.mcone.lobby.story.office;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.facades.Msg;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.api.player.vanish.VanishPlayerVisibility;
import eu.mcone.lobby.api.story.office.OfficeManager;
import eu.mcone.lobby.api.story.office.OfficeType;
import eu.mcone.lobby.story.cmd.OfficeCMD;
import eu.mcone.lobby.story.inventory.office.ConfirmCurrentOfficeQuitInventory;
import eu.mcone.lobby.story.inventory.office.ConfirmSilentLobbyQuitInventory;
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

        CoreSystem.getInstance().getVanishManager().registerVanishRule(10, (player, playerCanSee) -> {
            for (Map.Entry<Player, Set<Player>> office : joinedPlayers.entrySet()) {
                if (office.getKey().equals(player) || office.getValue().contains(player)) {
                    playerCanSee.removeIf(p -> !office.getValue().contains(p));
                    return;
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
            Msg.sendError(p, "Du hast kein B??ro!");
        }
    }

    private void joinOffice(Player p, Player owner, OfficeType office) {
        if (!LobbyPlugin.getInstance().getVanishManager().isInSilentLobby(p)) {
            if (LobbyPlugin.getInstance().getVanishManager().setVanishPlayerVisibility(p, VanishPlayerVisibility.EVERYBODY, false)) {
                Msg.sendInfo(p, "Deine Spielersichtbarkeit wurde auf ![Alle Sichtbar] gestellt, da du einem B??ro beigetreten bist!");
            }
        }

        if (joinedPlayers.containsKey(owner)) {
            joinedPlayers.get(owner).add(p);
        } else {
            joinedPlayers.put(owner, new HashSet<>(Collections.singleton(p)));
        }

        CoreSystem.getInstance().getVanishManager().recalculateVanishes();

        LobbyPlugin.getInstance().getHotbarSettings().updateInventory(p, LobbyPlugin.getInstance().getLobbyPlayer(p));

        p.getInventory().setItem(3, null);
        p.getInventory().setItem(2, null);

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

        Msg.sendInfo(player, "Du hast das B??ro verlassen!");
        LobbyPlugin.getInstance().resetPlayerDataAndHotbarItems(player);
    }

    @Override
    public void inviteToOffice(Player owner, Player invited) {
        if (LobbyPlugin.getInstance().getLobbyPlayer(owner).hasOffice()) {
            if (!LobbyPlugin.getInstance().getVanishManager().isInSilentLobby(owner)) {
                if (invitedPlayers.containsKey(owner)) {
                    invitedPlayers.get(owner).add(invited);
                } else {
                    invitedPlayers.put(owner, new HashSet<>(Collections.singleton(invited)));
                }

                Msg.sendSuccess(owner, "Du hast ![" + invited.getName() + "] zu deinem B??ro eingeladen!");
                Msg.send(
                        invited,
                        new ComponentBuilder("Der Spieler ")
                                .color(ChatColor.WHITE)
                                .append(owner.getName())
                                .color(ChatColor.WHITE).underlined(true)
                                .append(" hat dich zu seinem B??ro eingeladen").underlined(false)
                                .color(ChatColor.WHITE)
                                .append("\n")
                                .append("[Zum B??ro telepotieren]")
                                .color(ChatColor.GREEN)
                                .bold(true)
                                .event(new HoverEvent(
                                        HoverEvent.Action.SHOW_TEXT,
                                        new ComponentBuilder("??7??oKlicke hier, um dich zum B??ro zu teleportieren").create()
                                ))
                                .event(new ClickEvent(
                                        ClickEvent.Action.RUN_COMMAND,
                                        "/office " + owner.getName()
                                ))
                                .create()
                );
            } else {
                Msg.sendError(owner, "??4Du kannst keine Spieler in dein B??ro einladen, da du in der SilentLobby bist!");
            }
        } else {
            Msg.sendError(owner, "Du hast kein B??ro, in das du jemand einladen kannst!");
        }
    }

    @Override
    public void acceptInvite(Player p, Player owner) {
        if (invitedPlayers.getOrDefault(owner, Collections.emptySet()).contains(p)) {
            LobbyPlayer lpi = LobbyPlugin.getInstance().getLobbyPlayer(owner.getUniqueId());
            OfficeType office = lpi.getOffice();

            invitedPlayers.get(owner).remove(p);

            if (office != null) {
                if (!LobbyPlugin.getInstance().getVanishManager().isInSilentLobby(owner)) {
                    Runnable checkSilentLobby = () -> {
                        if (LobbyPlugin.getInstance().getVanishManager().isInSilentLobby(p)) {
                            new ConfirmSilentLobbyQuitInventory(owner, p, ConfirmSilentLobbyQuitInventory.Target.GUEST, () -> {
                                LobbyPlugin.getInstance().getVanishManager().quitSilentLobby(p);
                                joinOffice(p, owner, office);
                            });
                        } else {
                            joinOffice(p, owner, office);
                        }
                    };

                    if (isInOffice(p)) {
                        new ConfirmCurrentOfficeQuitInventory(p, owner, () -> {
                            quitOffice(p);
                            checkSilentLobby.run();
                        });
                    } else {
                        checkSilentLobby.run();
                    }
                } else {
                    Msg.sendError(p, "Du kannst die B??ro Einladung von !["+owner.getName()+"] nicht annehmen, da er in der SilentLobby ist!");
                }
            } else {
                Msg.sendError(p, "Der Spieler ![" + owner.getName() + "] hat kein B??ro!");
            }
        } else {
            Msg.sendError(p, "Diese Einladung ist ung??ltig!");
        }
    }

    @Override
    public void kickFromOffice(Player owner, Player visitor) {
        if (joinedPlayers.containsKey(owner) && joinedPlayers.get(owner).contains(visitor)) {
            LobbyWorld.ONE_ISLAND.getWorld().teleportSilently(visitor, "spawn");
            Msg.sendInfo(visitor, "Du wurdest von ![" + owner.getName() + "] aus dem B??ro gekickt!");
        } else {
            Msg.sendError(visitor, "![" + visitor.getName() + "] ist nicht in deinem B??ro!");
        }
    }

    @Override
    public void clearOffice(Player player) {
        if (joinedPlayers.containsKey(player)) {
            Set<Player> kick = joinedPlayers.get(player);
            for (Player visitor : kick) {
                if (!visitor.equals(player)) {
                    LobbyWorld.ONE_ISLAND.getWorld().teleportSilently(visitor, "spawn");
                    Msg.sendInfo(visitor, "Du wurdest von ![" + player.getName() + "] aus dem B??ro gekickt!");
                }
            }

            kick.clear();
            CoreSystem.getInstance().getVanishManager().recalculateVanishes();
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

        if (joinedPlayers.containsKey(player)) {
            clearOffice(player);
            joinedPlayers.remove(player);
        } else {
            for (Map.Entry<Player, Set<Player>> office : joinedPlayers.entrySet()) {
                office.getValue().remove(player);
            }
        }
    }

}
