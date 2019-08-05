package eu.mcone.lobby.items.inventory.office;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.gamesystem.api.game.player.GamePlayer;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.enums.Item;
import eu.mcone.lobby.api.player.LobbyPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

public class GoldOfficeInventory extends CoreInventory {

    GoldOfficeInventory(Player p) {
        super("§8» §d§lBüro §8| §fGold", p, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);
        CorePlayer cp = CoreSystem.getInstance().getCorePlayer(p);
        GamePlayer lp = LobbyPlugin.getInstance().getGamePlayer(p.getUniqueId());

        setItem(InventorySlot.ROW_3_SLOT_5, new ItemBuilder(Material.GOLD_INGOT, 1, 0).displayName("§aBüro kaufen").lore("§a§lBüro kosten 500 Emeralds").create(),
                e -> {
                    if (cp.getEmeralds() - 500 >= 0) {
                        cp.removeEmeralds(500);
                        cp.getScoreboard().getObjective(DisplaySlot.SIDEBAR).reload();
                        lp.addItem(Item.OFFICE_CARD_GOLD);

                        if (lp.hasItem(Item.OFFICE_CARD_BRONZE)) {
                            lp.removeItem(Item.OFFICE_CARD_BRONZE);
                        } else if (lp.hasItem(Item.OFFICE_CARD_SILVER)) {
                            lp.removeItem(Item.OFFICE_CARD_SILVER);
                        }

                        p.closeInventory();
                        p.sendMessage("§8[§7§l!§8] §cNPC §8» §fVerkäufer §8|§7 Bitte sehr ihre Büro Schlüsselkarte gehen sie zu mein Kolegen er bringt sie dann zu ihr Büro!");
                    } else {
                        p.closeInventory();
                        p.sendMessage("§8[§7§l!§8] §cNPC §8» §fVerkäufer §8|§4 Du hast zu wening Emeralds!");
                    }
                });

        setItem(InventorySlot.ROW_2_SLOT_3, new ItemBuilder(Material.TRIPWIRE_HOOK, 1, 0).displayName("§6§lVorteile:")
                .lore("§f- Du erhälst eine schöne Sekretärin", "§fdie dir viele Aufgaben nimmnt.", "§f- Du erhälst ein Schlaf Raum wo", "du dich ausruhen kannst", "§f- Du erhälst Sicherheits System", "§f- Du erhälst einen im Büro versteckten Geld Tresor.")
                .create());

        setItem(InventorySlot.ROW_2_SLOT_7, new ItemBuilder(Material.TRIPWIRE_HOOK, 1, 0).displayName("§6§lVorteile:")
                .lore("§f- Du erhälst ein Exklusives Büro wo du", "§fWo du Geheime Missionen starten kannst!", "§f- Du erhälst einen Bogenstand wo", "§fdu gute Bogen Skills erlernen kannst", "§f- Du erhälst einen Händler wie auf der Insel", "§fmit den gleichen Angeboten!")
                .create());

        openInventory();
    }
}

