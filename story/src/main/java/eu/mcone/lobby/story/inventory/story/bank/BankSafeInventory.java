package eu.mcone.lobby.story.inventory.story.bank;

import eu.mcone.coresystem.api.bukkit.facades.Msg;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.items.LobbyItem;
import eu.mcone.lobby.api.player.LobbyPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class BankSafeInventory extends CoreInventory {

    public BankSafeInventory(Player p) {
        super("§f§lTresorTruhe", p, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);

        setItem(InventorySlot.ROW_2_SLOT_5, LobbyItem.GOLD_BARDING.getItemStack(), e -> {
            if (!lp.hasLobbyItem(LobbyItem.GOLD_BARDING)) {
                lp.addLobbyItem(LobbyItem.GOLD_BARDING);
                Bukkit.getScheduler().runTaskLaterAsynchronously(LobbyPlugin.getInstance(), () -> {
                    p.sendMessage("§8[§7§l!§8] §cKnopf im Ohr §8» §fJohn§8 |§7 Perfekt du hast die Gold Barren gehe jetzt links zum alten Bank Ausgang trete dort auf eine Druckplatte um die alte Tür zu öffnen ");
                }, 30L);

            } else {
                Msg.send(p, "§4Du besitzt diese Item bereits!");
            }
            p.closeInventory();
        });

        openInventory();
    }
}
