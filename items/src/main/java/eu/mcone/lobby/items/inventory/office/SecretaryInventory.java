package eu.mcone.lobby.items.inventory.office;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.player.LobbyPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class SecretaryInventory extends CoreInventory {

    public SecretaryInventory(Player p) {
        super("§8» §b§lSekretärin §8| §fMenü", p, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);
        CorePlayer cp = CoreSystem.getInstance().getCorePlayer(p);
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);


        setItem(InventorySlot.ROW_2_SLOT_5, new ItemBuilder(Material.BARRIER, 1, 0).displayName("§cBETA")
                .lore("§fDer Server befindet sich in der BETA", "§fdeswegen ist dieses Inventar", "", "§fnoch nicht fertig ausgearbeitet!")
                .create(), e -> {
            player.closeInventory();
            player.sendMessage("§cDer Server befindet sich in der BETA §cdeswegen ist dieses Inventar noch nicht fertig ausgearbeitet!");
        });


        openInventory();
    }
}
