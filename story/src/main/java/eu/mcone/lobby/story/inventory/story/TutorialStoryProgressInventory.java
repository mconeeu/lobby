package eu.mcone.lobby.story.inventory.story;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.item.Skull;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.enums.TutorialStory;
import eu.mcone.lobby.api.player.LobbyPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class TutorialStoryProgressInventory extends CoreInventory {

    public TutorialStoryProgressInventory(Player player) {
        super("§8» §3§lTutorial-Story", player, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);

        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(player);

        int i = 0;
        int progressId = lp.getTutorialStoryProgressId();

        for (TutorialStory tutorialStory : TutorialStory.values()) {
            if (tutorialStory.getId() <= progressId) {
                setItem(i,
                        new Skull(
                                tutorialStory.getNpc().getSkin(),
                                1
                        ).toItemBuilder().displayName(tutorialStory.getName()).lore(tutorialStory.getDescription()).create()
                );
            } else {
                setItem(i, new ItemBuilder(Material.SKULL_ITEM, 1, 0).displayName("§7§l???").create());
            }
            i++;
        }

        setItem(InventorySlot.ROW_3_SLOT_9, new ItemBuilder(Material.IRON_DOOR, 1, 0).displayName("§7§l↩ Zurück").create(), e -> new ProgressesInventory(player));

        openInventory();
    }
}
