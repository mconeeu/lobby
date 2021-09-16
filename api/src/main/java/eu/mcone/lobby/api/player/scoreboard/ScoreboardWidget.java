/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.api.player.scoreboard;

import eu.mcone.coresystem.api.bukkit.inventory.settings.Option;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.player.LobbyPlayer;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

@Getter
public enum ScoreboardWidget implements Option {

    COINS("COINS", 1, "§8»§7 Coins:", new ItemBuilder(Material.COMPASS).displayName("§b§lCoins"), lp -> " §b§o" + lp.getCorePlayer().getFormattedCoins()),
    ONLINE_TIME("ONLINE_TIME", 1, "§8»§7 Online Zeit:", new ItemBuilder(Material.WATCH).displayName("§f§lOnline Zeit"), lp -> " §d§o" + lp.getCorePlayer().getFormattedOnlinetime()),
    RANK("RANK", 1, "§8»§7 Rang:", new ItemBuilder(Material.GOLD_HELMET).displayName("§f§lRang"), lp -> " "+lp.getCorePlayer().getMainGroup().getLabel()),
    SECRETS("SECRETS", 2, "§8»§7 Secrets:", new ItemBuilder(Material.SIGN).displayName("§f§lSecrets"), lp -> "§f§o " + lp.getSecretsCount()),
    EMERALD("EMERALDS", 2, "§8»§7 Emeralds:", new ItemBuilder(Material.EMERALD).displayName("§a§lEmeralds"), lp -> "§a§o " + lp.getCorePlayer().getFormattedEmeralds()),
    ONLINE_PLAYER_LOBBY("LOBBYPLAYER", 2, "§8»§7 Lobby Spieler:", new ItemBuilder(Material.SULPHUR).displayName("§f§lAktive Lobby Spieler"), lp -> {
        int amount = LobbyPlugin.getInstance().getVanishManager().isInSilentLobby(lp.bukkit())
                ? 1
                : Bukkit.getOnlinePlayers().size() - LobbyPlugin.getInstance().getVanishManager().getSilentLobbyPlayers().size();
        return "§f§o " + amount;
    });

    public interface ScoreboardValueCalculator {
        String getValue(LobbyPlayer lp);
    }

    private final String name;
    private final String displayValue;
    private final int line;
    private final ItemBuilder itembuilder;
    private final ScoreboardValueCalculator calculator;

    ScoreboardWidget(String name, int line, String displayValue, ItemBuilder itembuilder, ScoreboardValueCalculator calculator) {
        this.name = name;
        this.line = line;
        this.displayValue = displayValue;
        this.itembuilder = itembuilder;
        this.calculator = calculator;
    }

    @Override
    public ItemStack getItem() {
        return itembuilder.lore("§7§oKlicke zum ändern.").create();
    }

    public static ScoreboardWidget[] getWidgets(int line) {
        List<ScoreboardWidget> widgets = new ArrayList<>();

        for (ScoreboardWidget widget : values()) {
            if (widget.line == line) {
                widgets.add(widget);
            }
        }

        return widgets.toArray(new ScoreboardWidget[0]);
    }

}
