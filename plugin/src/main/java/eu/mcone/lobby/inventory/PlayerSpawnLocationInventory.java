package eu.mcone.lobby.inventory;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.enums.Item;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.util.PlayerSpawnLocation;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

public class PlayerSpawnLocationInventory extends CoreInventory {

    public PlayerSpawnLocationInventory(Player player) {
        super("§8» §c§lLobby Einstellung", player, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);
        createInventory();
        openInventory();
    }

    private void createInventory() {
        LobbyPlayer lobbyPlayer = LobbyPlugin.getInstance().getLobbyPlayer(player.getUniqueId());
        PlayerSpawnLocation playerSpawnLocation = PlayerSpawnLocation.valueOf(lobbyPlayer.getSettings().getSpawnLocation());

        int i = InventorySlot.ROW_2_SLOT_2;

        for (PlayerSpawnLocation locations : PlayerSpawnLocation.values()) {
            if (locations.equals(playerSpawnLocation)) {
                if (playerSpawnLocation == PlayerSpawnLocation.OFFICE) {
                    if (lobbyPlayer.hasItem(Item.OFFICE_CARD_BRONZE) || lobbyPlayer.hasItem(Item.OFFICE_CARD_SILVER) || lobbyPlayer.hasItem(Item.OFFICE_CARD_GOLD)) {
                        setItem(i, new ItemBuilder(playerSpawnLocation.getMaterial()).displayName(playerSpawnLocation.getDisplayname()).lore("§7Spawne bei deinem Büro").create());
                    } else {
                        setItem(i, new ItemBuilder(Material.BARRIER).displayName(playerSpawnLocation.getDisplayname()).lore("§7Du musst dir zuerst ein Büro kaufen").create());
                    }
                } else {
                    setItem(i, enchantItem(new ItemBuilder(locations.getMaterial()).displayName(locations.getDisplayname())));
                }
            } else {
                setItem(i, new ItemBuilder(locations.getMaterial()).displayName(locations.getDisplayname()).create(), e -> {
                    lobbyPlayer.getSettings().setSpawnLocation(locations.toString());
                    createInventory();
                });
            }

            i = +2;
        }

        player.updateInventory();
    }

    private ItemStack enchantItem(ItemBuilder itemBuilder) {
        return itemBuilder.enchantment(Enchantment.DURABILITY, 1).itemFlags(ItemFlag.HIDE_ENCHANTS).create();
    }
}
