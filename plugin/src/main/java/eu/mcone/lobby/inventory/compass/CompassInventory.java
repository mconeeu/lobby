package eu.mcone.lobby.inventory.compass;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import org.bukkit.DyeColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class CompassInventory extends CoreInventory {

    public static final ItemStack SILVER_PLACEHOLDER = makePlaceholderItem(DyeColor.SILVER),
            CYAN_PLACEHOLDER = makePlaceholderItem(DyeColor.CYAN),
            LIGHT_BLUE_PLACEHOLDER = makePlaceholderItem(DyeColor.LIGHT_BLUE);

    public CompassInventory(String title, Player player, int size, InventoryOption... options) {
        super(title, player, size, options);
    }

}
