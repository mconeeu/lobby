package eu.mcone.lobby.items.inventory.office;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.enums.LobbyItem;
import eu.mcone.lobby.api.player.LobbyPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

public class BronzeOfficeInventory extends CoreInventory {

    BronzeOfficeInventory(Player p) {
        super("§8» §d§lBüro §8| §fBronze", p, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);
        CorePlayer cp = CoreSystem.getInstance().getCorePlayer(p);
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);

        setItem(InventorySlot.ROW_3_SLOT_5, new ItemBuilder(Material.CLAY_BRICK, 1, 0).displayName("§aBüro kaufen").lore("§a§lBüro kosten 150 Emeralds").create(),
                e -> {
                    if (cp.getEmeralds() - 150 >= 0) {
                        cp.removeEmeralds(150);
                        cp.getScoreboard().getObjective(DisplaySlot.SIDEBAR).reload();
                        lp.addLobbyItem(LobbyItem.OFFICE_CARD_BRONZE);

                        p.closeInventory();
                        p.sendMessage("§8[§7§l!§8] §cNPC §8» §fVerkäufer §8|§7 Bitte sehr ihre Büro Schlüsselkarte gehen sie zu mein Kolegen er bringt sie dann zu ihr Büro!");
                    } else {
                        p.closeInventory();
                        p.sendMessage("§8[§7§l!§8] §cNPC §8» §fVerkäufer §8|§4 Du hast zu wenig Emeralds!");
                    }
                });

        setItem(InventorySlot.ROW_2_SLOT_3, new ItemBuilder(Material.TRIPWIRE_HOOK, 1, 0).displayName("§6§lVorteile:")
                .lore("§f- Du erhälst eine schöne Sekretärin", "§fdie dir viele Aufgaben abnimmt.")
                .create());

        setItem(InventorySlot.ROW_2_SLOT_7, new ItemBuilder(Material.TRIPWIRE_HOOK, 1, 0).displayName("§6§lVorteile:")
                .lore("§f- Du erhälst ein Exklusives Büro wo du", "§fWo du Geheime Missionen starten kannst!")
                .create());


        openInventory();
    }
}

