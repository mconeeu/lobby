package eu.mcone.lobby.story.inventory.office;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.story.office.OfficeType;
import eu.mcone.lobby.api.player.LobbyPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class OfficeInventory extends CoreInventory {

    OfficeInventory(Player p, OfficeType office) {
        super("§8» §d§lBüro §8| "+office.getLabel(), p, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);
        CorePlayer cp = CoreSystem.getInstance().getCorePlayer(p);
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);

        setItem(InventorySlot.ROW_3_SLOT_5, new ItemBuilder(Material.IRON_INGOT, 1, 0).displayName("§a§lBüro kaufen").lore("§2Büro kostet §a"+office.getEmeraldPrice()+" Emeralds").create(),
                e -> {
                    if (cp.getEmeralds() - office.getEmeraldPrice() >= 0) {
                        cp.removeEmeralds(office.getEmeraldPrice());
                        lp.addLobbyItem(office.getOfficeCard());

                        for (OfficeType type : OfficeType.values()) {
                            if (!type.equals(office) && lp.hasLobbyItem(type.getOfficeCard())) {
                                lp.removeLobbyItem(type.getOfficeCard());
                            }
                        }

                        p.sendMessage("§8[§7§l!§8] §cNPC §8» §fVerkäufer §8|§7 Bitte sehr ihre Büro Schlüsselkarte gehen sie zu mein Kollegen er bringt sie dann zu ihr Büro!");
                    } else {
                        p.sendMessage("§8[§7§l!§8] §cNPC §8» §fVerkäufer §8|§4 Du hast zu wenig Emeralds!");
                    }

                    p.closeInventory();
                });

        setItem(InventorySlot.ROW_2_SLOT_3, new ItemBuilder(Material.TRIPWIRE_HOOK, 1, 0).displayName("§6§lVorteile:")
                .lore("§f- Du erhälst eine schöne Sekretärin", "§fdie dir viele Aufgaben nimmnt.", "§f- Du erhälst ein Schlaf Raum wo", "§fdu dich ausruhen kannst")
                .create());

        setItem(InventorySlot.ROW_2_SLOT_7, new ItemBuilder(Material.TRIPWIRE_HOOK, 1, 0).displayName("§6§lVorteile:")
                .lore("§f- Du erhälst ein Exklusives Büro wo du", "§fGeheime Missionen starten kannst!", "§f- Du erhälst einen Bogenstand wo", "§fdu gute Bogen Skills erlernen kannst.")
                .create());


        openInventory();
    }
}

