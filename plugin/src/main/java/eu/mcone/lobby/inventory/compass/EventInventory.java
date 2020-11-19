package eu.mcone.lobby.inventory.compass;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.facades.Sound;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.lobby.Lobby;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class EventInventory extends CoreInventory {

    public EventInventory(Player player) {
        super("§8» §3§lEvent", player, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);

        Bukkit.getScheduler().runTaskLater(Lobby.getSystem(), () -> {
            setItem(InventorySlot.ROW_2_SLOT_5, new ItemBuilder(Material.EMERALD, 1, 0)
                            .displayName("§fEvent §8| §fNEU")
                            .lore("§8» §f§nLinksklick§8 | §7§oBetreten")
                            .create(),

                    e -> {
                        CoreSystem.getInstance().getChannelHandler().createSetRequest(player, "CONNECT", "survival");
                        player.closeInventory();

                    });

            Sound.done(player);
        }, 2L);

        setItem(InventorySlot.ROW_3_SLOT_9, BACK_ITEM, e -> new MinigamesInventory(player));

        openInventory();
    }

}
