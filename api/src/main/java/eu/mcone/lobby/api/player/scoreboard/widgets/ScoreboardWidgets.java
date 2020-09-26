package eu.mcone.lobby.api.player.scoreboard.widgets;

import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import lombok.Getter;
import org.bukkit.Material;

@Getter
public enum ScoreboardWidgets {

    COINS("COINS", Line.LINE_ONE, "§8»§7 Coins:", new ItemBuilder(Material.COMPASS).displayName("§bCoins")),
    ONLINE_TIME("ONLINE_TIME", Line.LINE_ONE, "§8»§7 Online Zeit:", new ItemBuilder(Material.WATCH).displayName("§fOnline Zeit")),
    EMERALD("EMERALDS", Line.LINE_TWO, "§8»§7 Emeralds:", new ItemBuilder(Material.EMERALD).displayName("§aEmeralds")),
    RANK("RANK", Line.LINE_ONE, "§8»§7 Rang:", new ItemBuilder(Material.GOLD_HELMET).displayName("§fRang")),
    SECRETS("SECRETS", Line.LINE_ONE, "§8»§7 Secrets:", new ItemBuilder(Material.SIGN).displayName("§fSecrets")),
    ONLINE_PLAYER_LOBBY("LOBBYPLAYER", Line.LINE_TWO, "§8»§7 Lobby Spieler:", new ItemBuilder(Material.SULPHUR).displayName("§fAktive Lobby Spieler"));

    private final String name;
    private final String displayValue;
    private final Line line;
    private final ItemBuilder item;

    ScoreboardWidgets(String name, Line line, String displayValue, ItemBuilder item) {
        this.name = name;
        this.line = line;
        this.displayValue = displayValue;
        this.item = item;
    }
}
