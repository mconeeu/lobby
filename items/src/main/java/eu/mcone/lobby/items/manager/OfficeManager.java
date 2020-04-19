package eu.mcone.lobby.items.manager;

import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.api.enums.LobbyItem;
import eu.mcone.lobby.api.player.HotbarItems;
import eu.mcone.lobby.api.player.LobbyPlayer;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;


public class OfficeManager {

    private static List<Player> VANISHED = new ArrayList<>();

    public static void getOffice(Player player) {
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(player.getUniqueId());

        if (lp.hasLobbyItem(LobbyItem.OFFICE_CARD_BRONZE)) {
            LobbyWorld.OFFICE.getWorld().teleport(player, OfficeType.BRONZE_OFFICE.getSpawnLocation());
        } else if (lp.hasLobbyItem(LobbyItem.OFFICE_CARD_SILVER)) {
            LobbyWorld.OFFICE.getWorld().teleport(player, OfficeType.SILVER_OFFICE.getSpawnLocation());
        } else if (lp.hasLobbyItem(LobbyItem.OFFICE_CARD_GOLD)) {
            LobbyWorld.OFFICE.getWorld().teleport(player, OfficeType.GOLD_OFFICE.getSpawnLocation());
        }
    }

    public static void joinOffice(Player player) {
        if (player.hasPermission("lobby.silenthub")) {
            player.getInventory().setItem(2, HotbarItems.LOBBY_HIDER_UNAVAILABLE_OFFICE);
        }

        if (LobbyPlugin.getInstance().getPlayerHiderManager().isHidden(player)) {
            LobbyPlugin.getInstance().getPlayerHiderManager().showPlayers(player);
        }
        player.getInventory().setItem(0, HotbarItems.LOBBY_HIDER_UNAVAILABLE_OFFICE);


        getOffice(player);
        if (!LobbyPlugin.getInstance().getSilentLobbyManager().isActivatedSilentHub(player)) {
            vanishPlayer(player);
        }

    }


    public static void quitOffice(Player player) {
        unVanishPlayer(player);

        player.getInventory().setItem(0, HotbarItems.HIDE_PLAYERS);

        if (player.hasPermission("lobby.silenthub")) {
            if (LobbyPlugin.getInstance().getSilentLobbyManager().isActivatedSilentHub(player)) {
                player.getInventory().setItem(2, HotbarItems.LEAVE_PRIVATE_LOBBY);
                player.getInventory().setItem(0, HotbarItems.LOBBY_HIDER_UNAVAILABLE);
            } else {
                player.getInventory().setItem(2, HotbarItems.PRIVATE_LOBBY);
            }
        }
    }

    public static void vanishPlayer(Player player) {
        if (!VANISHED.contains(player)) {
            for (Player all : Bukkit.getOnlinePlayers()) {
                player.hidePlayer(all);
            }

            VANISHED.add(player);
        }
    }

    public static void unVanishPlayer(Player player) {
        if (VANISHED.contains(player)) {
            for (Player all : Bukkit.getOnlinePlayers()) {
                player.showPlayer(all);
            }

            VANISHED.remove(player);
        }
    }


    public static void updateOffice(Player player) {
        for (Player p : VANISHED) {
            p.hidePlayer(player);
        }
    }

    public enum OfficeType {
        BRONZE_OFFICE("bronzeOffice"),
        SILVER_OFFICE("silverOffice"),
        GOLD_OFFICE("goldOffice");

        @Getter
        private String spawnLocation;

        OfficeType(final String spawnLocation) {
            this.spawnLocation = spawnLocation;
        }
    }
}
