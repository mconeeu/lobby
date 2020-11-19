package eu.mcone.lobby.inventory.settings;

import eu.mcone.coresystem.api.bukkit.facades.Sound;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.api.player.settings.LobbySettings;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class LobbyMinigamesSettingsInventory extends CoreInventory {

    public LobbyMinigamesSettingsInventory(Player p) {
        super("§8» §c§lLobby Games", p, InventorySlot.ROW_4, InventoryOption.FILL_EMPTY_SLOTS);
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);
        LobbySettings settings = lp.getSettings();


        setItem(InventorySlot.ROW_2_SLOT_2, new ItemBuilder(Material.PAPER, 1, 0).displayName("§f§lLobbyGames Einladungen").create());
        if (settings.isLobbyGamesInvite()) {
            setItem(InventorySlot.ROW_3_SLOT_2, new ItemBuilder(Material.INK_SACK, 1, 10).displayName("§a§lAktiviert").lore("§7§oAndere Spieler können dich", "§7§ozu LobbyGames einladen").create(), e -> {
                settings.setLobbyGamesInvite(false);
                setSettings(p, lp);
            });
        } else {
            setItem(InventorySlot.ROW_3_SLOT_2, new ItemBuilder(Material.INK_SACK, 1, 1).displayName("§c§lDeaktiviert").lore("§7§oAndere Spieler können dich nicht", "§7§ozu LobbyGames einladen").create(), e -> {
                settings.setLobbyGamesInvite(true);
                setSettings(p, lp);
            });
        }

        setItem(InventorySlot.ROW_2_SLOT_3, new ItemBuilder(Material.WATCH, 1, 0).displayName("§f§lTag-Nacht Zyklus").create());
        if (settings.isLobbyGamesInvite()) {
            setItem(InventorySlot.ROW_3_SLOT_3, new ItemBuilder(Material.BARRIER, 1, 10).displayName("§a§lAktiviert").lore("§7§o", "§7§o").create(), e -> {

            });
        } else {
            setItem(InventorySlot.ROW_3_SLOT_3, new ItemBuilder(Material.BARRIER, 1, 1).displayName("§c§lDeaktiviert").lore("§7§o", "§7§o").create(), e -> {
            });
        }

        setItem(InventorySlot.ROW_4_SLOT_9, BACK_ITEM, e ->
                new LobbySettingsInventory(p));

        openInventory();
    }

    private void setSettings(Player p, LobbyPlayer lp) {
        lp.saveData();
        Sound.click(p);
        new LobbyMinigamesSettingsInventory(p);
    }
}
