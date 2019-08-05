package eu.mcone.lobby.story.inventory.story;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.enums.Item;
import eu.mcone.lobby.api.player.LobbyPlayer;
import org.bukkit.entity.Player;

public class WoolInventory extends CoreInventory {

    public WoolInventory(Player p) {
        super("§f§lWolle Packet", p, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p.getUniqueId());

        setItem(InventorySlot.ROW_2_SLOT_5, Item.WHITE_WOOL.getItemStack(), e -> {
            if (!lp.hasItem(Item.WHITE_WOOL)) {
                lp.addItem(Item.WHITE_WOOL);
                p.sendMessage("§8[§7§l!§8] §cKnopf im Ohr §8» §fJohn§8|§7 Wieso hast du denn jetzt Wolle? Besorge das Bank Outfit!");
            } else {
                LobbyPlugin.getInstance().getMessager().send(p, "§4Du besitzt diese Item bereits!");
            }
            p.closeInventory();
        });

        openInventory();
    }
}
