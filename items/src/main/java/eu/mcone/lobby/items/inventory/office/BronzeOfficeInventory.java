package eu.mcone.lobby.items.inventory.office;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.enums.Item;
import eu.mcone.lobby.api.player.LobbyPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class BronzeOfficeInventory extends CoreInventory {

    public BronzeOfficeInventory(Player p) {
        super("§8» §d§lBüro §8| §fBronze", p, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);
        CorePlayer cp = CoreSystem.getInstance().getCorePlayer(p);
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p.getUniqueId());


        setItem(InventorySlot.ROW_3_SLOT_5, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 5).displayName("§aBüro kaufen").lore("§a§lBüro kosten 150 Emeralds").create(),
                e -> {

                    if (cp.getEmeralds() - 150 >= 0) {
                        cp.removeEmeralds(150);
                        lp.removeItem(Item.OFFICE_CARD_2);
                        
                        lp.addItem(Item.OFFICE_CARD_1);
                    } else {

                        p.closeInventory();
                        p.sendMessage("§8[§7§l!§8] §cNPC §8» §fVerkäufer §8|§4 Du hast zu wening Emeralds!");
                    }

                    p.closeInventory();

                    p.sendMessage("§8[§7§l!§8] §cNPC §8» §fVerkäufer §8|§7 Bitte sehr ihre Büro Schlüsselkarte gehen sie zu mein Kollegen er bringt sie dann zu ihr Büro!");
                });


        setItem(InventorySlot.ROW_2_SLOT_4, new ItemBuilder(Material.TRIPWIRE_HOOK, 1, 0).displayName("§6§lVorteile:")
                .lore("§f- Du erhälst eine schöne Sekretärin", "§fdie dir viele Aufgaben nimmnt.")
                .create());

        setItem(InventorySlot.ROW_2_SLOT_6, new ItemBuilder(Material.TRIPWIRE_HOOK, 1, 0).displayName("§6§lVorteile:")
                .lore("§f- Du erhälst ein Exklusives Büro wo du", "§fWo du Geheime Missionen starten kannst!")
                .create());


        openInventory();
    }
}

