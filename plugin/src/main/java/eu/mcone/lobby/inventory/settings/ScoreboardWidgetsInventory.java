package eu.mcone.lobby.inventory.settings;

import eu.mcone.coresystem.api.bukkit.facades.Sound;
import eu.mcone.coresystem.api.bukkit.inventory.settings.Setting;
import eu.mcone.coresystem.api.bukkit.inventory.settings.SettingsInventory;
import eu.mcone.coresystem.api.bukkit.inventory.settings.settings.BooleanSetting;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.coresystem.api.bukkit.scoreboard.CoreObjective;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.api.player.scoreboard.ScoreboardWidget;
import eu.mcone.lobby.api.player.scoreboard.SidebarObjective;
import eu.mcone.lobby.api.player.settings.LobbySettings;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

public class ScoreboardWidgetsInventory extends SettingsInventory {

    public ScoreboardWidgetsInventory(Player p) {
        super("§8» §c§lScoreboard-Widgets", p, e -> new LobbySettingsInventory(p));
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);
        LobbySettings settings = lp.getSettings();

        addSetting(
                new BooleanSetting(new ItemBuilder(Material.SIGN, 1, 0).displayName("§f§lScoreboard anzeigen").create(), null)
                        .onChoose((player, result) -> {
                            settings.setScoreboard(result);
                            setSettings(p, lp);

                            CorePlayer cp = lp.getCorePlayer();
                            if (result) {
                                cp.getScoreboard().setNewObjective(new SidebarObjective());
                            } else {
                                cp.getScoreboard().getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
                            }
                        })
                        .setEnabledDescription("§7§oKlicke um nicht mehr das Scoreboard", "§7§oauf der rechten Seite zu sehen")
                        .setDisabledDescription("§7§oKlicke um das Scoreboard", "§7§oauf der rechte Seite zu sehen")
                        .optionFinder(player -> settings.isScoreboard())
                        .create()
        );

        addSetting(new Setting<>(
                new ItemBuilder(Material.SIGN, 1, 0).displayName("§f§l1. Reihe").create(),
                (player, result) -> {
                    settings.setScoreboardWidgetFirstLine(result);
                    setSettings(p, lp);
                },
                player -> settings.getScoreboardWidgetFirstLine(),
                ScoreboardWidget.getWidgets(1)
        ));

        addSetting(new Setting<>(
                new ItemBuilder(Material.SIGN, 1, 0).displayName("§f§l2. Reihe").create(),
                (player, result) -> {
                    settings.setScoreboardWidgetSecondLine(result);
                    setSettings(p, lp);
                },
                player -> settings.getScoreboardWidgetSecondLine(),
                ScoreboardWidget.getWidgets(2)
        ));

        openInventory();
    }

    private void setSettings(Player p, LobbyPlayer lp) {
        lp.saveData();
        Sound.click(p);

        CoreObjective objective = lp.getCorePlayer().getScoreboard().getObjective(DisplaySlot.SIDEBAR);
        if (objective != null) {
            objective.reload();
        }
    }

}
