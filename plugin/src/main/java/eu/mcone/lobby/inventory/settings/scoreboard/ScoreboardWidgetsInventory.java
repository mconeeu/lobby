package eu.mcone.lobby.inventory.settings.scoreboard;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.api.player.scoreboard.widgets.ScoreboardWidgets;
import eu.mcone.lobby.api.player.scoreboard.SidebarObjective;
import eu.mcone.lobby.api.player.settings.LobbySettings;
import eu.mcone.lobby.inventory.settings.LobbySettingsInventory;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

public class ScoreboardWidgetsInventory extends CoreInventory {

    public ScoreboardWidgetsInventory(Player p) {
        super("§8» §c§lScoreboard-Widgets", p, InventorySlot.ROW_4, InventoryOption.FILL_EMPTY_SLOTS);
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);
        LobbySettings settings = lp.getSettings();

        setItem(InventorySlot.ROW_2_SLOT_3, new ItemBuilder(Material.SIGN, 1, 0).displayName("§f§l1 Reihe").create());
        setItem(InventorySlot.ROW_3_SLOT_3, settings.getScoreboardWidgetsFirstLine().getItem().create(), e -> {
            switch (settings.getScoreboardWidgetsFirstLine()) {
                case COINS: {
                    settings.setScoreboardWidgetsFirstLine(ScoreboardWidgets.ONLINE_TIME);
                    setSettings(p, lp);
                    break;
                }
                case ONLINE_TIME: {
                    settings.setScoreboardWidgetsFirstLine(ScoreboardWidgets.RANK);
                    setSettings(p, lp);
                    break;
                }
                case RANK: {
                    settings.setScoreboardWidgetsFirstLine(ScoreboardWidgets.COINS);
                    setSettings(p, lp);
                    break;
                }
            }
        });

        setItem(InventorySlot.ROW_2_SLOT_5, new ItemBuilder(Material.SIGN, 1, 0).displayName("§f§lScoreboard anzeigen").create());
        if (settings.isScoreboard()) {
            setItem(InventorySlot.ROW_3_SLOT_5, new ItemBuilder(Material.INK_SACK, 1, 10).displayName("§a§lAktiviert").lore("§7§oKlicke um nicht mehr das Scoreboard", "§7§oauf der rechten Seite zu sehen").create(), e -> {
                settings.setScoreboard(false);
                CoreSystem.getInstance().getCorePlayer(p).getScoreboard().getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
                setSettings(p, lp);
            });
        } else {
            setItem(InventorySlot.ROW_3_SLOT_5, new ItemBuilder(Material.INK_SACK, 1, 1).displayName("§c§lDeaktiviert").lore("§7§oKlicke um das Scoreboard", "§7§oauf der rechte Seite zu sehen").create(), e -> {
                CorePlayer cp = CoreSystem.getInstance().getCorePlayer(p);
                settings.setScoreboard(true);
                cp.getScoreboard().setNewObjective(new SidebarObjective());
                setSettings(p, lp);
            });
        }

        setItem(InventorySlot.ROW_2_SLOT_7, new ItemBuilder(Material.SIGN, 1, 0).displayName("§f§l2 Reihe").create());
        setItem(InventorySlot.ROW_3_SLOT_7, settings.getScoreboardWidgetsSecondLine().getItem().create(), e -> {
            switch (settings.getScoreboardWidgetsSecondLine()) {
                case EMERALD: {
                    settings.setScoreboardWidgetsSecondLine(ScoreboardWidgets.ONLINE_PLAYER_LOBBY);
                    setSettings(p, lp);
                    break;
                }
                case ONLINE_PLAYER_LOBBY: {
                    settings.setScoreboardWidgetsSecondLine(ScoreboardWidgets.SECRETS);
                    setSettings(p, lp);
                    break;
                }
                case SECRETS: {
                    settings.setScoreboardWidgetsSecondLine(ScoreboardWidgets.EMERALD);
                    setSettings(p, lp);
                    break;
                }
            }
        });


        setItem(InventorySlot.ROW_4_SLOT_9, BACK_ITEM, e ->
                new LobbySettingsInventory(p));

        openInventory();
    }

    private void setSettings(Player p, LobbyPlayer lp) {
        lp.saveData();
        LobbyPlugin.getInstance().getPlayerSounds().playSounds(p, Sound.CHICKEN_EGG_POP);
        new ScoreboardWidgetsInventory(p);
        lp.getCorePlayer().getScoreboard().getObjective(DisplaySlot.SIDEBAR).reload();
    }

}
