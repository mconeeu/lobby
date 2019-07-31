package eu.mcone.lobby.items.inventory.backpack;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.enums.Category;
import eu.mcone.lobby.api.enums.Item;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.items.LobbyItems;
import eu.mcone.lobby.items.manager.OutfitFactory;
import javafx.scene.layout.BackgroundPosition;
import org.bukkit.entity.Player;

public class IngameInventory extends BackpackInventory {

    public IngameInventory(Player p) {
        super(Category.INGAME, p);

        for (Item item : Item.values()) {
            if (item.hasCategory() && item.getCategory().equals(Category.INGAME) && playerHasItem(item)) {
                addItem(item.getItemStack(), e -> {
                    if (playerHasItem(item)) {
                        //  p.getInventory().setItem(3, item.getItemStack());
                    }
                });
            }
        }
    }
}


