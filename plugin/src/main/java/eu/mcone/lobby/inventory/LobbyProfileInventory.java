package eu.mcone.lobby.inventory;

import eu.mcone.coresystem.api.bukkit.facades.Sound;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.lobby.inventory.settings.LobbySettingsInventory;
import eu.mcone.lobby.story.inventory.SecretsInventory;
import eu.mcone.lobby.story.inventory.story.StoryOverviewInventory;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class LobbyProfileInventory extends CoreInventory {

    public LobbyProfileInventory(Player player) {
        super("§8» §fWähle?", player, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);

        setItem(
                InventorySlot.ROW_2_SLOT_3,
                new ItemBuilder(Material.CLAY_BALL, 1, 0).displayName("§3§lLobby Einstellungen").lore("§7§oVerwalte deine", "§7§oLobbyeinstellungen", "", "§8» §f§nLinksklick§8 | §7§oÖffnen").create(),
                e -> {
                    Sound.click(player);
                    new LobbySettingsInventory(player);
                }
        );

        setItem(
                InventorySlot.ROW_2_SLOT_5,
                new ItemBuilder(Material.BOOK, 1, 0).displayName("§c§lStory Fortschritt").lore("§7§oHier siehst Du welche Kapitel", "§7§oDu bereits bestanden hast", "", "§8» §f§nLinksklick§8 | §7§oAnsehen").create(),
                e -> {
                    Sound.click(player);
                    new StoryOverviewInventory(player);
                }
        );

        setItem(
                InventorySlot.ROW_2_SLOT_7,
                new ItemBuilder(Material.SIGN, 1, 0).displayName("§6§lSecrets").lore("§7§oHier siehst du wie viele Secrets,", "§7§odie Du bereits gefunden hast", "", "§8» §f§nLinksklick§8 | §7§oAnsehen").create(),
                e -> {
                    Sound.click(player);
                    new SecretsInventory(player);
                }
        );

        setItem(InventorySlot.ROW_3_SLOT_9, BACK_ITEM, e -> Bukkit.dispatchCommand(player, "profile"));

        openInventory();
    }
}
