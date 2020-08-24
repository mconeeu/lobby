package eu.mcone.lobby.story.inventory.story.bank;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.story.progress.bank.BankRobberySmallProgress;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.story.inventory.story.StoryOverviewInventory;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class BankSmallStoryProgressInventory extends CoreInventory {

    public BankSmallStoryProgressInventory(Player player) {
        super("§8» §3§lKleiner-Bankraub-Story", player, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);

        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(player);

        int i = 0;
        int progressId = lp.getTutorialStoryProgressId();

        for (BankRobberySmallProgress bankRobberySmallProgress : BankRobberySmallProgress.values()) {
            if (bankRobberySmallProgress.getId() <= progressId) {
                setItem(i,
                        new ItemBuilder(Material.DIAMOND).displayName(bankRobberySmallProgress.getName()).lore(bankRobberySmallProgress.getDescription()).create());
            } else {
                setItem(i, new ItemBuilder(Material.SKULL_ITEM, 1, 0).displayName("§7§l???").create());
            }
            i++;
        }

        setItem(InventorySlot.ROW_3_SLOT_9, BACK_ITEM, e -> new StoryOverviewInventory(player));

        openInventory();
    }
}
