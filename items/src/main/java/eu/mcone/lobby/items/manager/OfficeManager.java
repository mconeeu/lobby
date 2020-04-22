package eu.mcone.lobby.items.manager;

import eu.mcone.gameapi.api.GameAPI;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.api.enums.LobbyItem;
import eu.mcone.lobby.api.player.HotbarItems;
import eu.mcone.lobby.api.player.LobbyPlayer;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class OfficeManager {

    private static final Set<Player> VANISHED = new HashSet<>();
    public static final List<Player> ISTOGETHEROFFICE = new ArrayList<>();

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

    public static void getOfficeFromOther(Player player, Player other) {
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(player.getUniqueId());

        if (lp.hasLobbyItem(LobbyItem.OFFICE_CARD_BRONZE)) {
            LobbyWorld.OFFICE.getWorld().teleport(other, OfficeType.BRONZE_OFFICE.getSpawnLocation());
        } else if (lp.hasLobbyItem(LobbyItem.OFFICE_CARD_SILVER)) {
            LobbyWorld.OFFICE.getWorld().teleport(other, OfficeType.SILVER_OFFICE.getSpawnLocation());
        } else if (lp.hasLobbyItem(LobbyItem.OFFICE_CARD_GOLD)) {
            LobbyWorld.OFFICE.getWorld().teleport(other, OfficeType.GOLD_OFFICE.getSpawnLocation());
        }
    }


    public static void joinOtherOffice(Player player, Player other) {
        if (LobbyPlugin.getInstance().getSilentLobbyManager().isActivatedSilentHub(player)) {
            LobbyPlugin.getInstance().getSilentLobbyManager().deactivateSilentLobby(player);
            GameAPI.getInstance().getGamePlayer(player).setEffectsVisible(false);
        }
        if (LobbyPlugin.getInstance().getSilentLobbyManager().isActivatedSilentHub(other)) {
            LobbyPlugin.getInstance().getSilentLobbyManager().deactivateSilentLobby(other);
        }

        if (LobbyPlugin.getInstance().getPlayerHiderManager().isHidden(player)) {
            LobbyPlugin.getInstance().getPlayerHiderManager().showPlayers(player);
        }
        if (LobbyPlugin.getInstance().getPlayerHiderManager().isHidden(other)) {
            LobbyPlugin.getInstance().getPlayerHiderManager().showPlayers(other);
        }


        getOfficeFromOther(player, other);

        GameAPI.getInstance().getGamePlayer(other).setEffectsVisible(false);

        ISTOGETHEROFFICE.add(player);
        ISTOGETHEROFFICE.add(other);

        VANISHED.add(other);
        VANISHED.add(player);


        if (other.hasPermission("lobby.silenthub")) {
            other.getInventory().setItem(2, HotbarItems.LOBBY_HIDER_UNAVAILABLE_OFFICE_SILENTHUB);
            other.getInventory().setItem(3, null);
        } else {
            other.getInventory().setItem(2, null);
        }
        if (player.hasPermission("lobby.silenthub")) {
            player.getInventory().setItem(2, HotbarItems.LOBBY_HIDER_UNAVAILABLE_OFFICE_SILENTHUB);
        }

        other.getInventory().setItem(0, HotbarItems.LOBBY_HIDER_UNAVAILABLE_OFFICE);
        player.getInventory().setItem(0, HotbarItems.LOBBY_HIDER_UNAVAILABLE_OFFICE);

        for (Player all : Bukkit.getOnlinePlayers()) {
            other.hidePlayer(all);
            player.hidePlayer(all);
        }


        other.showPlayer(player);
        player.showPlayer(other);


    }

    public static void joinOffice(Player player) {
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(player.getUniqueId());
        if (!lp.hasLobbyItem(LobbyItem.OFFICE_CARD_BRONZE) && !lp.hasLobbyItem(LobbyItem.OFFICE_CARD_SILVER) && !lp.hasLobbyItem(LobbyItem.OFFICE_CARD_GOLD)) {
            LobbyPlugin.getInstance().getMessenger().send(player, "§4Du hast kein Büro!");
            return;
        }

        if (LobbyPlugin.getInstance().getPlayerHiderManager().isHidden(player)) {
            LobbyPlugin.getInstance().getPlayerHiderManager().showPlayers(player);
        }

        updateOffice(player);
        LobbyPlugin.getInstance().getPlayerHiderManager().updateHider(player);


        if (player.hasPermission("lobby.silenthub")) {
            player.getInventory().setItem(2, HotbarItems.LOBBY_HIDER_UNAVAILABLE_OFFICE_SILENTHUB);
            player.getInventory().setItem(3, null);
        } else {
            player.getInventory().setItem(2, null);
        }

        player.getInventory().setItem(0, HotbarItems.LOBBY_HIDER_UNAVAILABLE_OFFICE);


        getOffice(player);
        if (!LobbyPlugin.getInstance().getSilentLobbyManager().isActivatedSilentHub(player)) {
            vanishPlayer(player);
            GameAPI.getInstance().getGamePlayer(player).setEffectsVisible(false);
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
                GameAPI.getInstance().getGamePlayer(player).setEffectsVisible(true);
                player.getInventory().setItem(2, HotbarItems.PRIVATE_LOBBY);
            }
            return;
        }
        GameAPI.getInstance().getGamePlayer(player).setEffectsVisible(true);
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
