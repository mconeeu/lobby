package eu.mcone.lobby.story.inventory.story.bank;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.enums.LobbyItem;
import eu.mcone.lobby.api.player.LobbyPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class BankInfosInventory3 extends CoreInventory {


    public BankInfosInventory3(Player p) {
        super("§fLager Kiste", p, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);

        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);

        if (!lp.hasLobbyItem(LobbyItem.WORKER_FILE_1)) {
            setItem(InventorySlot.ROW_2_SLOT_5, LobbyItem.WORKER_FILE_1.getItemStack(), e -> {
                lp.addLobbyItem(LobbyItem.WORKER_FILE_1);
                Bukkit.getScheduler().runTaskLaterAsynchronously(LobbyPlugin.getInstance(), () -> {
                    p.sendMessage("§8[§7§l!§8] §cKnopf im Ohr §8» §fJohn§8 |§7 Perfekt du hast die Mitarbeiter Akten du kannst jetzt zurück kommen oder noch mehr wichtige Kisten finden und sie ausspähen das wird dir später noch helfen.");
                }, 8L);
                p.closeInventory();
            });
        } else if (!lp.hasLobbyItem(LobbyItem.NORMAL_FILES_1)) {
            setItem(InventorySlot.ROW_2_SLOT_5, LobbyItem.NORMAL_FILES_1.getItemStack(), e -> {
                lp.addLobbyItem(LobbyItem.NORMAL_FILES_1);
                Bukkit.getScheduler().runTaskLaterAsynchronously(LobbyPlugin.getInstance(), () -> {
                    if (lp.hasLobbyItem(LobbyItem.NORMAL_FILES_2) && lp.hasLobbyItem(LobbyItem.NORMAL_FILES_3)) {
                        p.sendMessage("§8[§7§l!§8] §cKnopf im Ohr §8» §fJohn§8 |§7 Super du hast noch mehr Daten gefunden komme jetzt zurück!");
                    } else {
                        p.sendMessage("§8[§7§l!§8] §cKnopf im Ohr §8» §fJohn§8 |§7 Noch mehr Daten? Suche weiter oder komm zurück!");
                    }
                }, 15L);
                p.closeInventory();
            });

        }
        openInventory();
    }
}
