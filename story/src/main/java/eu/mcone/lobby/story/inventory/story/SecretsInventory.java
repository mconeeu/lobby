package eu.mcone.lobby.story.inventory.story;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.player.LobbyPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class SecretsInventory extends CoreInventory {

    public SecretsInventory(Player player) {
        super("§8» §3§lSecrets", player, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(player);


        setItem(InventorySlot.ROW_2_SLOT_5, new ItemBuilder(Material.SIGN).displayName("§fDu hast").lore("§e" + lp.getSecrets() + "§e Secrets §fgefunden!").create());

        setItem(InventorySlot.ROW_3_SLOT_9, new ItemBuilder(Material.IRON_DOOR, 1, 0).displayName("§7§l↩ Zurück").create(), e -> Bukkit.dispatchCommand(player, "profile"));

        openInventory();
    }
}
