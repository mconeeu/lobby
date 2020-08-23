package eu.mcone.lobby.api.story.office;

import org.bukkit.entity.Player;

import java.util.Set;

public interface OfficeManager {

    void joinOffice(Player player);

    void quitOffice(Player player);

    void inviteToOffice(Player owner, Player invited);

    void acceptInvite(Player player, Player invitor);

    void kickFromOffice(Player owner, Player visitor);

    void clearOffice(Player player);

    Set<Player> getPlayersInOffice(Player owner);

    Player getCurrentOfficeOwner(Player target);

    boolean isInOffice(Player player);

    boolean isInOwnOffice(Player player);

}
