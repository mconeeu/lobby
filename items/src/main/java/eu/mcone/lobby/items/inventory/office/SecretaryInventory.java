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

public class SecretaryInventory extends CoreInventory {

    public SecretaryInventory(Player p) {
        super("§8» §b§lSekretärin §8| §fMenü", p, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);
        CorePlayer cp = CoreSystem.getInstance().getCorePlayer(p);
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p.getUniqueId());

        if (lp.hasItem(Item.OFFICE_CARD_BRONZE)) {


            setItem(InventorySlot.ROW_2_SLOT_5, new ItemBuilder(Material.SKULL, 1, 0).displayName("§f§lFreunde einladen")
                    .lore("§7Lade deine Freunde in das Büro ein", "§7und plane deine nächsten Missionen")
                    .create(), e -> {

            });


        }

        if (lp.hasItem(Item.OFFICE_CARD_SILVER)) {


            setItem(InventorySlot.ROW_2_SLOT_3, new ItemBuilder(Material.SKULL, 1, 0).displayName("§f§lFreunde einladen")
                    .lore("§7Lade deine Freunde in das Büro ein", "§7und plane deine nächsten Missionen")
                    .create(), e -> {

            });

            setItem(InventorySlot.ROW_2_SLOT_7, new ItemBuilder(Material.SKULL, 1, 0).displayName("§f§lCoins Umwandeln")
                    .lore("§7Wandel deine Coins in", "§7Emeralds um mit mehr Profit")
                    .create(), e -> {

            });


        }

        if (lp.hasItem(Item.OFFICE_CARD_GOLD)) {


            setItem(InventorySlot.ROW_2_SLOT_3, new ItemBuilder(Material.SKULL, 1, 0).displayName("§f§lFreunde einladen")
                    .lore("§7Lade deine Freunde in das Büro ein", "§7und plane deine nächsten Missionen")
                    .create(), e -> {

            });

            setItem(InventorySlot.ROW_2_SLOT_5, new ItemBuilder(Material.SKULL, 1, 0).displayName("§f§lCoins Umwandeln")
                    .lore("§7Wandel deine Coins in", "§7Emeralds um mit mehr Profit")
                    .create(), e -> {

            });

            setItem(InventorySlot.ROW_2_SLOT_7, new ItemBuilder(Material.COAL, 1, 0).displayName("§f§lGang Einstellungen")
                    .lore("§7Lade deine Freunde in das Büro ein", "§7und plane deine nächsten Missionen")
                    .create(), e -> {

            });


        }


        setItem(InventorySlot.ROW_2_SLOT_3, new ItemBuilder(Material.BED, 1, 0).displayName("§6§lBronze Büro")
                .lore("§fWelche Ihnhalte dieses Büro hat", "§ferfahren sie wenn sie klicken", "", "§a§LDie Kosten liegen bei 150 Emeralds")
                .create(), e -> {
            new BronzeOfficeInventory(p);
        });

        setItem(InventorySlot.ROW_2_SLOT_5, new ItemBuilder(Material.BED, 1, 0).displayName("§6§lSilver Büro")
                .lore("§fWelche Ihnhalte dieses Büro hat", "§ferfahren sie wenn sie klicken", "", "§a§LDie Kosten liegen bei 250 Emeralds")
                .create(), e -> {
            new SilverOfficeInventory(p);
        });

        setItem(InventorySlot.ROW_2_SLOT_7, new ItemBuilder(Material.BED, 1, 0).displayName("§6§lGold Büro")
                .lore("§fWelche Ihnhalte dieses Büro hat", "§ferfahren sie wenn sie klicken", "", "§a§LDie Kosten liegen bei 500 Emeralds")
                .create(), e -> {
            new GoldOfficeInventory(p);
        });


        openInventory();
    }
}
