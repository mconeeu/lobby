package eu.mcone.lobby.story.inventory.story;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.enums.BankProgress;
import eu.mcone.lobby.api.enums.Item;
import eu.mcone.lobby.api.player.LobbyPlayer;
import org.bukkit.entity.Player;

public class BankSaveInventory extends CoreInventory {

    public BankSaveInventory(Player p) {
        super("§f§lTresorTruhe", p, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p.getUniqueId());

        setItem(InventorySlot.ROW_2_SLOT_5, Item.GOLD_BARDING.getItemStack(), e -> {
            if (!lp.hasItem(Item.GOLD_BARDING)) {
                lp.addItem(Item.GOLD_BARDING);
                lp.setBankProgress(BankProgress.BANK_ROBBERY_END);
                p.sendMessage("§8[§7§l!§8] §cKnopf im Ohr §8» §fJohn§8|§7 Ohhh Perfekt du hast die 24 Gold Barren gehe jetzt links zum alten Bank Ausgang trete da einfach auf eine Eisen Platte");
            } else {
                LobbyPlugin.getInstance().getMessager().send(p, "§4Du besitzt diese Item bereits!");
            }
            p.closeInventory();
        });

        openInventory();
    }
}