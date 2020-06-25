package eu.mcone.lobby.story.inventory.story;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.item.Skull;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.enums.TraderProgress;
import eu.mcone.lobby.api.player.LobbyPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class TraderStoryProgressInventory extends CoreInventory {

    public TraderStoryProgressInventory(Player player) {
        super("§8» §3§lHändler-Story", player, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);

        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(player);

        int i = 0;
        int progressId = lp.getTraderStoryProgressID();

        for (
                TraderProgress traderProgress : TraderProgress.values()) {
            if (traderProgress.getId() <= progressId) {
                setItem(i,
                        new Skull(
                                traderProgress.getNpc().getSkin(),
                                1
                        ).toItemBuilder().displayName(traderProgress.getName()).lore(traderProgress.getDescription()).create()
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
