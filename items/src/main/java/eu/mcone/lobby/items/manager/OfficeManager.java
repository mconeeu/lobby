package eu.mcone.lobby.items.manager;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.scoreboard.CoreScoreboard;
import eu.mcone.coresystem.api.bukkit.scoreboard.MainScoreboard;
import eu.mcone.gamesystem.api.enums.Item;
import eu.mcone.gamesystem.api.game.player.GamePlayer;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.LobbyWorld;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;


public class OfficeManager {

    private static List<Player> VANISHED = new ArrayList<>();

    public static void getOffice(Player player) {
        GamePlayer lp = LobbyPlugin.getInstance().getGamePlayer(player.getUniqueId());

        if (lp.hasItem(Item.OFFICE_CARD_BRONZE)) {
            vanishPlayer(player);
            LobbyWorld.OFFICE.getWorld().teleport(player, OfficeType.BRONZE_OFFICE.getSpawnLocation());
        } else if (lp.hasItem(Item.OFFICE_CARD_SILVER)) {
            vanishPlayer(player);
            LobbyWorld.OFFICE.getWorld().teleport(player, OfficeType.SILVER_OFFICE.getSpawnLocation());
        } else if (lp.hasItem(Item.OFFICE_CARD_GOLD)) {
            vanishPlayer(player);
            LobbyWorld.OFFICE.getWorld().teleport(player, OfficeType.GOLD_OFFICE.getSpawnLocation());
        }
    }

    public static void vanishPlayer(Player player) {
        if (!VANISHED.contains(player)) {
            for (Player all : Bukkit.getOnlinePlayers()) {
                player.hidePlayer(all);
            }

            CoreScoreboard sb = CoreSystem.getInstance().getCorePlayer(player).getScoreboard();

            if (sb instanceof MainScoreboard) {
                sb.reload();
            }

            VANISHED.add(player);
        }
    }

    public static void unVanishPlayer(Player player) {
        if (VANISHED.contains(player)) {
            for (Player all : Bukkit.getOnlinePlayers()) {
                player.showPlayer(all);
            }

            CoreScoreboard sb = CoreSystem.getInstance().getCorePlayer(player).getScoreboard();

            if (sb instanceof MainScoreboard) {
                sb.reload();
            }

            VANISHED.remove(player);
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
