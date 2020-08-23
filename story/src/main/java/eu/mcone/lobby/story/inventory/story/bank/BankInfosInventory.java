package eu.mcone.lobby.story.inventory.story.bank;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.items.LobbyItem;
import eu.mcone.lobby.api.player.LobbyPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;

public class BankInfosInventory extends CoreInventory {

    public static final LobbyItem[] FILE_ITEMS = {
            LobbyItem.NORMAL_FILES_1, LobbyItem.NORMAL_FILES_2, LobbyItem.NORMAL_FILES_3
    };

    public BankInfosInventory(Player p, LobbyItem found) {
        super("§fLager Kiste", p, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);
        List<LobbyItem> items = Arrays.asList(FILE_ITEMS);

        if (items.contains(found)) {
            LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);

            if (!lp.hasLobbyItem(LobbyItem.WORKER_FILE_1)) {
                setItem(InventorySlot.ROW_2_SLOT_5, LobbyItem.WORKER_FILE_1.getItemStack(), e -> {
                    lp.addLobbyItem(LobbyItem.WORKER_FILE_1);
                    Bukkit.getScheduler().runTaskLaterAsynchronously(LobbyPlugin.getInstance(), () -> {
                        p.sendMessage("§8[§7§l!§8] §cKnopf im Ohr §8» §fJohn§8 |§7 Perfekt du hast die Mitarbeiter Akten du kannst jetzt zurück kommen oder noch mehr wichtige Kisten finden und sie ausspähen das wird dir später noch helfen.");
                    }, 8L);
                    p.closeInventory();
                });
            } else if (!lp.hasLobbyItem(found)) {
                setItem(InventorySlot.ROW_2_SLOT_5, found.getItemStack(), e -> {
                    lp.addLobbyItem(found);
                    p.closeInventory();

                    Bukkit.getScheduler().runTaskLaterAsynchronously(LobbyPlugin.getInstance(), () -> {
                        items.remove(found);
                        for (LobbyItem item : items) {
                            if (!lp.hasLobbyItem(item)) {
                                p.sendMessage("§8[§7§l!§8] §cKnopf im Ohr §8» §fJohn§8 |§7 Perfekt. Suche weiter oder komm zurück!");
                                return;
                            }
                        }

                        p.sendMessage("§8[§7§l!§8] §cKnopf im Ohr §8» §fJohn§8 |§7 Super du hast noch mehr Daten gefunden komme jetzt zurück!");
                    }, 15L);
                });
            }

            openInventory();
        } else throw new IllegalStateException("Could not open BackInfosInventory given LobbyItem is not a \"NORMAL_FILES_*\" item!");
    }
}
