package eu.mcone.lobby.story.inventory.john;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.item.Skull;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.enums.bank.central.BankProgress;
import eu.mcone.lobby.api.player.LobbyPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class JohnBankRobberyCentralBank extends CoreInventory {


    public JohnBankRobberyCentralBank(Player p) {
        super("§fJohn | Central-Bank-Raub", p, InventorySlot.ROW_6, InventoryOption.FILL_EMPTY_SLOTS);
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);

        setItem(InventorySlot.ROW_1_SLOT_5, Skull.fromUrl("http://textures.minecraft.net/texture/5163dafac1d91a8c91db576caac784336791a6e18d8f7f62778fc47bf146b6", 1).toItemBuilder()
                .displayName("§d§lCentral-Bank-Raub")
                .lore("§7§oDu Raubst für John die Central One-Island Bank aus",
                        "§7§oDafür bekommst du dein gekauftes Büro",
                        "§7§onochmals zurück und viele Coins!"
                ).create());

        if (lp.getCentralbankprogressId() == BankProgress.SPY.getId()) {

            setItem(InventorySlot.ROW_4_SLOT_2, new ItemBuilder(Material.PRISMARINE_SHARD, 1, 0).displayName("§fVorbereitung: Ausspähen").create(), e -> {
                p.closeInventory();
                p.sendMessage("§8[§7§l!§8] §cKnopf im Ohr §8» §fJohn§8 |§7 Okay es geht los die erste Mission damit wir die Central Bank von OneIsland ausrauben können du musst ihn die Bank und dort wichtige Dokumente finden!");

            });

            setItem(InventorySlot.ROW_3_SLOT_2, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 14).displayName("§cNicht Abgeschlossen!").create());
            setItem(InventorySlot.ROW_5_SLOT_2, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 14).displayName("§cNicht Abgeschlossen!").create());

            //

            setItem(InventorySlot.ROW_3_SLOT_4, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 14).displayName("§cNicht Freigeschaltet!").create());
            setItem(InventorySlot.ROW_4_SLOT_4, new ItemBuilder(Material.BARRIER, 1, 0).displayName("§cNicht Freigeschaltet!").create());
            setItem(InventorySlot.ROW_5_SLOT_4, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 14).displayName("§cNicht Freigeschaltet!").create());

            setItem(InventorySlot.ROW_3_SLOT_5, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 14).displayName("§cNicht Freigeschaltet!").create());
            setItem(InventorySlot.ROW_4_SLOT_5, new ItemBuilder(Material.BARRIER, 1, 0).displayName("§cNicht Freigeschaltet!").create());
            setItem(InventorySlot.ROW_5_SLOT_5, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 14).displayName("§cNicht Freigeschaltet!").create());

            setItem(InventorySlot.ROW_3_SLOT_6, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 14).displayName("§cNicht Freigeschaltet!").create());
            setItem(InventorySlot.ROW_4_SLOT_6, new ItemBuilder(Material.BARRIER, 1, 0).displayName("§cNicht Freigeschaltet!").create());
            setItem(InventorySlot.ROW_5_SLOT_6, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 14).displayName("§cNicht Freigeschaltet!").create());

            //

            setItem(InventorySlot.ROW_3_SLOT_8, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 14).displayName("§cNicht Freigeschaltet!").create());
            setItem(InventorySlot.ROW_4_SLOT_8, new ItemBuilder(Material.BARRIER, 1, 0).displayName("§cNicht Freigeschaltet!").create());
            setItem(InventorySlot.ROW_5_SLOT_8, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 14).displayName("§cNicht Freigeschaltet!").create());

        } else if (lp.getCentralbankprogressId() == BankProgress.HACKER_TERMINAL.getId()) {

            setItem(InventorySlot.ROW_4_SLOT_2, new ItemBuilder(Material.PRISMARINE_SHARD, 1, 0).displayName("§fVorbereitung: Ausspähen").create());
            setItem(InventorySlot.ROW_3_SLOT_2, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 13).displayName("§aErledigt!").create());
            setItem(InventorySlot.ROW_5_SLOT_2, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 13).displayName("§aErledigt!").create());


            setItem(InventorySlot.ROW_3_SLOT_4, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 14).displayName("§cNicht Abgeschlossen!").create());


            setItem(InventorySlot.ROW_4_SLOT_4, new ItemBuilder(Material.HOPPER, 1, 0).displayName("§fVorbereitung: Hacker Terminal").create(), e -> {
                p.closeInventory();
                p.sendMessage("§8[§7§l!§8] §cKnopf im Ohr §8» §fJohn§8 |§7 Okay weiter geht es wir brauchen ein Hacker Terminal, weil in den Akten irgendetwas von einem Hacker Schutz drinn steht. Besorg dir eins bei meinem Freund Roger er wohnt in der Kirche der kennt sich damit aus!");


            });

            setItem(InventorySlot.ROW_5_SLOT_4, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 14).displayName("§cNicht Abgeschlossen!").create());

            setItem(InventorySlot.ROW_3_SLOT_5, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 14).displayName("§cNicht Freigeschaltet!").create());
            setItem(InventorySlot.ROW_4_SLOT_5, new ItemBuilder(Material.BARRIER, 1, 0).displayName("§cNicht Freigeschaltet!").create());
            setItem(InventorySlot.ROW_5_SLOT_5, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 14).displayName("§cNicht Freigeschaltet!").create());

            setItem(InventorySlot.ROW_3_SLOT_6, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 14).displayName("§cNicht Freigeschaltet!").create());
            setItem(InventorySlot.ROW_4_SLOT_6, new ItemBuilder(Material.BARRIER, 1, 0).displayName("§cNicht Freigeschaltet!").create());
            setItem(InventorySlot.ROW_5_SLOT_6, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 14).displayName("§cNicht Freigeschaltet!").create());


            setItem(InventorySlot.ROW_3_SLOT_8, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 14).displayName("§cNicht Freigeschaltet!").create());
            setItem(InventorySlot.ROW_4_SLOT_8, new ItemBuilder(Material.BARRIER, 1, 0).displayName("§cNicht Freigeschaltet!").create());
            setItem(InventorySlot.ROW_5_SLOT_8, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 14).displayName("§cNicht Freigeschaltet!").create());

        } else if (lp.getCentralbankprogressId() == BankProgress.CENTRAL_PLAN.getId()) {

            setItem(InventorySlot.ROW_4_SLOT_2, new ItemBuilder(Material.PRISMARINE_SHARD, 1, 0).displayName("§fVorbereitung: Ausspähen").create());
            setItem(InventorySlot.ROW_3_SLOT_2, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 13).displayName("§aErledigt!").create());
            setItem(InventorySlot.ROW_5_SLOT_2, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 13).displayName("§aErledigt!").create());


            setItem(InventorySlot.ROW_3_SLOT_4, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 13).displayName("§aErledigt!").create());
            setItem(InventorySlot.ROW_4_SLOT_4, new ItemBuilder(Material.HOPPER, 1, 0).displayName("§fVorbereitung: Hacker Terminal").create());
            setItem(InventorySlot.ROW_5_SLOT_4, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 13).displayName("§aErledigt!").create());


            setItem(InventorySlot.ROW_3_SLOT_5, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 14).displayName("§cNicht Abgeschlossen!").create());

            setItem(InventorySlot.ROW_4_SLOT_5, new ItemBuilder(Material.MAP, 1, 0).displayName("§fVorbereitung: Central Bank Plan").create(), e -> {


                p.closeInventory();
            });

            setItem(InventorySlot.ROW_5_SLOT_5, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 14).displayName("§cNicht Abgeschlossen!").create());

            setItem(InventorySlot.ROW_3_SLOT_6, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 14).displayName("§cNicht Freigeschaltet!").create());
            setItem(InventorySlot.ROW_4_SLOT_6, new ItemBuilder(Material.BARRIER, 1, 0).displayName("§cNicht Freigeschaltet!").create());
            setItem(InventorySlot.ROW_5_SLOT_6, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 14).displayName("§cNicht Freigeschaltet!").create());
            setItem(InventorySlot.ROW_3_SLOT_8, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 14).displayName("§cNicht Freigeschaltet!").create());
            setItem(InventorySlot.ROW_4_SLOT_8, new ItemBuilder(Material.BARRIER, 1, 0).displayName("§cNicht Freigeschaltet!").create());
            setItem(InventorySlot.ROW_5_SLOT_8, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 14).displayName("§cNicht Freigeschaltet!").create());


        } else if (lp.getCentralbankprogressId() == BankProgress.SWORD.getId()) {

            setItem(InventorySlot.ROW_4_SLOT_2, new ItemBuilder(Material.PRISMARINE_SHARD, 1, 0).displayName("§fVorbereitung: Ausspähen").create());
            setItem(InventorySlot.ROW_3_SLOT_2, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 13).displayName("§aErledigt!").create());
            setItem(InventorySlot.ROW_5_SLOT_2, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 13).displayName("§aErledigt!").create());

            setItem(InventorySlot.ROW_3_SLOT_4, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 13).displayName("§aErledigt!").create());
            setItem(InventorySlot.ROW_4_SLOT_4, new ItemBuilder(Material.HOPPER, 1, 0).displayName("§fVorbereitung: Hacker Terminal").create());
            setItem(InventorySlot.ROW_5_SLOT_4, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 13).displayName("§aErledigt!").create());

            setItem(InventorySlot.ROW_3_SLOT_5, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 13).displayName("§aErledigt!").create());
            setItem(InventorySlot.ROW_4_SLOT_5, new ItemBuilder(Material.MAP, 1, 0).displayName("§fVorbereitung: Central Bank Plan").create());
            setItem(InventorySlot.ROW_5_SLOT_5, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 13).displayName("§aErledigt!").create());

            ///

            setItem(InventorySlot.ROW_3_SLOT_6, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 14).displayName("§cNicht Freigeschaltet!").create());
            setItem(InventorySlot.ROW_4_SLOT_6, new ItemBuilder(Material.IRON_SWORD, 1, 0).displayName("§fVorbereitung: Erpressungs Schwert").create(), e -> {


                p.closeInventory();

            });
            setItem(InventorySlot.ROW_5_SLOT_6, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 14).displayName("§cNicht Freigeschaltet!").create());


            setItem(InventorySlot.ROW_3_SLOT_8, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 14).displayName("§cNicht Freigeschaltet!").create());
            setItem(InventorySlot.ROW_4_SLOT_8, new ItemBuilder(Material.BARRIER, 1, 0).displayName("§cNicht Freigeschaltet!").create());
            setItem(InventorySlot.ROW_5_SLOT_8, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 14).displayName("§cNicht Freigeschaltet!").create());

        } else if (lp.getCentralbankprogressId() == BankProgress.CUSTOMER.getId()) {
            setItem(InventorySlot.ROW_4_SLOT_2, new ItemBuilder(Material.PRISMARINE_SHARD, 1, 0).displayName("§fVorbereitung: Ausspähen").create());
            setItem(InventorySlot.ROW_3_SLOT_2, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 13).displayName("§aErledigt!").create());
            setItem(InventorySlot.ROW_5_SLOT_2, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 13).displayName("§aErledigt!").create());

            setItem(InventorySlot.ROW_3_SLOT_4, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 13).displayName("§aErledigt!").create());
            setItem(InventorySlot.ROW_4_SLOT_4, new ItemBuilder(Material.HOPPER, 1, 0).displayName("§fVorbereitung: Hacker Terminal").create());
            setItem(InventorySlot.ROW_5_SLOT_4, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 13).displayName("§aErledigt!").create());

            setItem(InventorySlot.ROW_3_SLOT_5, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 13).displayName("§aErledigt!").create());
            setItem(InventorySlot.ROW_4_SLOT_5, new ItemBuilder(Material.MAP, 1, 0).displayName("§fVorbereitung: Central Bank Plan").create());
            setItem(InventorySlot.ROW_5_SLOT_5, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 13).displayName("§aErledigt!").create());

            setItem(InventorySlot.ROW_3_SLOT_6, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 13).displayName("§aErledigt!").create());
            setItem(InventorySlot.ROW_4_SLOT_6, new ItemBuilder(Material.IRON_SWORD, 1, 0).displayName("§fVorbereitung: Erpressungs Schwert").create());
            setItem(InventorySlot.ROW_5_SLOT_6, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 13).displayName("§aErledigt!").create());

            ///

            setItem(InventorySlot.ROW_3_SLOT_8, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 14).displayName("§cNicht Freigeschaltet!").create());
            setItem(InventorySlot.ROW_4_SLOT_6, new ItemBuilder(Material.REDSTONE, 1, 0).displayName("§fVorbereitung: Mitarbeiter bedrohen").create(), e -> {
                p.closeInventory();

            });
            setItem(InventorySlot.ROW_5_SLOT_8, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 14).displayName("§cNicht Freigeschaltet!").create());

        } else if (lp.getCentralbankprogressId() == BankProgress.CHOOSE.getId()) {


        }
        openInventory();
    }
}