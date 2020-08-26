package eu.mcone.lobby.story.inventory.story;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.story.inventory.story.bank.BankSmallStoryProgressInventory;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class StoryOverviewInventory extends CoreInventory {

    public StoryOverviewInventory(Player player) {
        super("§8» §fWähle?", player, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);

        setItem(
                InventorySlot.ROW_2_SLOT_2,
                new ItemBuilder(Material.GRASS, 1, 0)
                        .displayName("§fOneIsland-Story")
                        .create(),
                e -> new StoryProgressInventory(player)
        );
        setItem(
                InventorySlot.ROW_2_SLOT_4,
                new ItemBuilder(Material.EMERALD_BLOCK, 1, 0)
                        .displayName("§fKleiner-Bankraub-Story")
                        .create(),
                e -> new BankSmallStoryProgressInventory(player)
        );
        setItem(
                InventorySlot.ROW_2_SLOT_6,
                new ItemBuilder(Material.YELLOW_FLOWER, 1, 0)
                        .displayName("§fTutorial-Story")
                        .create(),
                e -> new TutorialStoryProgressInventory(player)
        );
        setItem(
                InventorySlot.ROW_2_SLOT_8,
                new ItemBuilder(Material.CHEST, 1, 0)
                        .displayName("§fHändler-Story")
                        .lore("§cNicht Verfügbar")
                        .create(),
                e -> LobbyPlugin.getInstance().getPlayerSounds().playErrorSound(player)
        );

        setItem(InventorySlot.ROW_3_SLOT_9, BACK_ITEM, e -> Bukkit.dispatchCommand(player, "profile"));

        openInventory();
    }
}
