package eu.mcone.lobby.story.inventory.john;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.item.Skull;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.api.enums.BankProgress;
import eu.mcone.lobby.api.player.LobbyPlayer;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.UUID;

public class JohnBankRobberyInventory extends CoreInventory {

    public static UUID currentlyInBank = null;

    public JohnBankRobberyInventory(Player p) {
        super("§fJohn | Bank-Raub", p, InventorySlot.ROW_6, InventoryOption.FILL_EMPTY_SLOTS);
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p.getUniqueId());

        if (lp.getBankprogressId() == BankProgress.SMUGGLER.getId()) {

            setItem(InventorySlot.ROW_1_SLOT_5, Skull.fromUrl("http://textures.minecraft.net/texture/5163dafac1d91a8c91db576caac784336791a6e18d8f7f62778fc47bf146b6", 1).toItemBuilder()
                    .displayName("§d§lBank-Raub")
                    .lore("§7§oDu Raubst für John die One-Island Bank aus",
                            "§7§oDafür bekommst du dein gekauftes Büro zurück",
                            "§7§ound viele Coins!"
                    ).create());

            setItem(InventorySlot.ROW_4_SLOT_2, new ItemBuilder(Material.EMPTY_MAP, 1, 0).displayName("§fVorbereitung: Tunnel Karte").create(), e -> {
                p.closeInventory();
                p.sendMessage("§8[§7§l!§8] §cKnopf im Ohr §8» §fJohn§8|§7 Okay die erste Vorbereitung bis zum großen Bank Raub du musst zum Schmuggler er steht in der nähe vom Zoll und da musst du den Plan der Bank kaufen und du weißt kein Wort zu Polizei sonst bist du tot!");

            });

            setItem(InventorySlot.ROW_3_SLOT_2, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 14).displayName("§cNicht Abgeschlossen!").create());
            setItem(InventorySlot.ROW_5_SLOT_2, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 14).displayName("§cNicht Abgeschlossen!").create());

            setItem(InventorySlot.ROW_3_SLOT_5, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 14).displayName("§cNicht Freigeschaltet!").create());
            setItem(InventorySlot.ROW_4_SLOT_5, new ItemBuilder(Material.BARRIER, 1, 0).displayName("§cNicht Freigeschaltet!").create());
            setItem(InventorySlot.ROW_5_SLOT_5, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 14).displayName("§cNicht Freigeschaltet!").create());

            setItem(InventorySlot.ROW_3_SLOT_8, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 14).displayName("§cNicht Freigeschaltet!").create());
            setItem(InventorySlot.ROW_4_SLOT_8, new ItemBuilder(Material.BARRIER, 1, 0).displayName("§cNicht Freigeschaltet!").create());
            setItem(InventorySlot.ROW_5_SLOT_8, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 14).displayName("§cNicht Freigeschaltet!").create());

        }

        if (lp.getBankprogressId() == BankProgress.CUTTER.getId()) {

            setItem(InventorySlot.ROW_1_SLOT_5, Skull.fromUrl("http://textures.minecraft.net/texture/5163dafac1d91a8c91db576caac784336791a6e18d8f7f62778fc47bf146b6", 1).toItemBuilder()
                    .displayName("§d§lBank-Raub")
                    .lore("§7§oDu Raubst für John die One-Island Bank aus",
                            "§7§oDafür bekommst du dein gekauftes Büro zurück",
                            "§7§ound viele Coins!"
                    ).create());

            setItem(InventorySlot.ROW_4_SLOT_2, new ItemBuilder(Material.EMPTY_MAP, 1, 0).displayName("§fVorbereitung: Tunnel Karte").create());
            setItem(InventorySlot.ROW_3_SLOT_2, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 13).displayName("§aErledigt!").create());
            setItem(InventorySlot.ROW_5_SLOT_2, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 13).displayName("§aErledigt!").create());


            setItem(InventorySlot.ROW_3_SLOT_5, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 14).displayName("§cNicht Abgeschlossen!").create());

            setItem(InventorySlot.ROW_4_SLOT_5, new ItemBuilder(Material.EMPTY_MAP, 1, 0).displayName("§fVorbereitung: Tunnel Karte").create(), e -> {
                p.closeInventory();
                p.sendMessage("§8[§7§l!§8] §cKnopf im Ohr §8» §fJohn§8|§7 Okay die zweite Vorbereitung bis zum großen Bank Raub du musst zur Schneiderei Joguloa und dir da ein Bank Mitarbeiter Outfit besorgen!");

            });

            setItem(InventorySlot.ROW_5_SLOT_5, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 14).displayName("§cNicht Abgeschlossen!").create());


            setItem(InventorySlot.ROW_3_SLOT_8, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 14).displayName("§cNicht Freigeschaltet!").create());
            setItem(InventorySlot.ROW_4_SLOT_8, new ItemBuilder(Material.BARRIER, 1, 0).displayName("§cNicht Freigeschaltet!").create());
            setItem(InventorySlot.ROW_5_SLOT_8, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 14).displayName("§cNicht Freigeschaltet!").create());

        }


        if (lp.getBankprogressId() == BankProgress.SWORD.getId()) {

            setItem(InventorySlot.ROW_1_SLOT_5, Skull.fromUrl("http://textures.minecraft.net/texture/5163dafac1d91a8c91db576caac784336791a6e18d8f7f62778fc47bf146b6", 1).toItemBuilder()
                    .displayName("§d§lBank-Raub")
                    .lore("§7§oDu Raubst für John die One-Island Bank aus",
                            "§7§oDafür bekommst du dein gekauftes Büro zurück",
                            "§7§ound viele Coins!"
                    ).create());

            setItem(InventorySlot.ROW_4_SLOT_2, new ItemBuilder(Material.EMPTY_MAP, 1, 0).displayName("§fVorbereitung: Tunnel Karte").create());
            setItem(InventorySlot.ROW_3_SLOT_2, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 13).displayName("§aErledigt!").create());
            setItem(InventorySlot.ROW_5_SLOT_2, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 13).displayName("§aErledigt!").create());


            setItem(InventorySlot.ROW_3_SLOT_5, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 13).displayName("§aErledigt!").create());
            setItem(InventorySlot.ROW_4_SLOT_5, new ItemBuilder(Material.LEATHER_CHESTPLATE, 1, 0).displayName("§fVorbereitung: Bank Outift").create());
            setItem(InventorySlot.ROW_5_SLOT_5, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 13).displayName("§aErledigt!").create());


            setItem(InventorySlot.ROW_3_SLOT_8, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 14).displayName("§cNicht Abgeschlossen!").create());

            setItem(InventorySlot.ROW_4_SLOT_2, new ItemBuilder(Material.IRON_SWORD, 1, 0).displayName("§fVorbereitung: Sicherheits Schwert").create(), e -> {
                p.closeInventory();
                p.sendMessage("§8[§7§l!§8] §cKnopf im Ohr §8» §fJohn§8|§7 Okay die letzte Vorbereitungs Mission bis zum großen Bank Raub du musst zur Paradise Island reisen und da in die Waffenkammer der Polizei einbrechen um das Sicherheits Schwert zu klauen!");

            });

            setItem(InventorySlot.ROW_5_SLOT_8, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 14).displayName("§cNicht Abgeschlossen!").create());

        }

        if (lp.getBankprogressId() == BankProgress.BANK_ROBBERY_START.getId()) {
            setItem(InventorySlot.ROW_1_SLOT_1, Skull.fromUrl("http://textures.minecraft.net/texture/5163dafac1d91a8c91db576caac784336791a6e18d8f7f62778fc47bf146b6", 1).toItemBuilder()
                    .displayName("§d§lBank-Raub")
                    .lore("§7§oDu Raubst für John die One-Island Bank aus",
                            "§7§oDafür bekommst du dein gekauftes Büro zurück",
                            "§7§ound viele Coins!"
                    ).create());

            setItem(InventorySlot.ROW_4_SLOT_5, new ItemBuilder(Material.INK_SACK, 1, 10).displayName("§fBank Raub").create(), e -> {
                if (currentlyInBank == null) {
                    p.closeInventory();
                    p.sendMessage("§8[§7§l!§8] §cKnopf im Ohr §8» §fJohn§8|§7So jetzt geht es los begib dich zur Bank!");
                    lp.setBankProgress(BankProgress.BANK_ROBBERY_MIDDLE);

                    LobbyWorld.ONE_ISLAND.getWorld().teleportSilently(p, "bank-robbery");

                    p.getInventory().setBoots(ItemBuilder.createLeatherArmorItem(Material.LEATHER_BOOTS, Color.BLACK).create());
                    p.getInventory().setChestplate(ItemBuilder.createLeatherArmorItem(Material.LEATHER_CHESTPLATE, Color.BLACK).create());
                    p.getInventory().setLeggings(ItemBuilder.createLeatherArmorItem(Material.LEATHER_LEGGINGS, Color.GRAY).create());


                    currentlyInBank = p.getUniqueId();
                } else {
                    p.sendMessage("&cJemand macht bereits diese Mission, bitte warte kurz!");
                }
            });


            openInventory();
        }

    }
}



