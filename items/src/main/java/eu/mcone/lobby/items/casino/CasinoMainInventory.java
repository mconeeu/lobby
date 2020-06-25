package eu.mcone.lobby.items.casino;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class CasinoMainInventory extends CoreInventory {


    public CasinoMainInventory(Player p) {
        super("§8» §f§lCasino", p, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);

        /*
        setItem(InventorySlot.ROW_2_SLOT_5, new ItemBuilder(Material.TRIPWIRE_HOOK, 1).displayName("§f§lZahlen erraten").create(), e -> {
            if (!NumbersChooseInventory.isInGame.contains(p)) {
                NumbersChooseInventory.isInGame.add(p);

                Bukkit.getScheduler().runTaskLater(LobbyPlugin.getInstance(), () -> {
                    if (NumbersChooseInventory.isInGame.contains(p)) {
                        LobbyPlugin.getInstance().getMessenger().send(p, "§4Dein Casino Spiel wurde abgebrochen, weil du zu lange gebraucht hast deine Coins wurden nicht abgebucht!");
                        player.closeInventory();
                    }
                    NumbersChooseInventory.isInGame.remove(p);
                    NumbersChooseInventory.Game.remove(p);
                    NumbersChooseInventory.chooseMoney.remove(p);
                }, 200);

                new MoneyChooseInventory(p);
            } else {
                LobbyPlugin.getInstance().getMessenger().send(p, "§4Bitte warte kurz..");
            }
        });
        */

        setItem(InventorySlot.ROW_2_SLOT_5, new ItemBuilder(Material.BARRIER, 1).displayName("§cWartungen...").create(), e -> {
           p.closeInventory();
           p.sendMessage("§4Diese Funktion befindet sich momentan in §cWartungen§4!");
        });

        openInventory();
    }
}
