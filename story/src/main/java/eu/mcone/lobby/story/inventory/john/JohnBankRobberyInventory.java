package eu.mcone.lobby.story.inventory.john;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.facades.Sound;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.item.Skull;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.api.items.LobbyItem;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.api.story.progress.StoryProgress;
import eu.mcone.lobby.api.story.progress.bank.BankRobberySmallProgress;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.UUID;

public class JohnBankRobberyInventory extends CoreInventory {

    public static UUID currentlyInBank = null;
    public static BukkitTask robberyTime;

    /*
     * TODO: make manager class for schedulers etc
     */

    public JohnBankRobberyInventory(Player p) {
        super("§fJohn | Bank-Raub", p, InventorySlot.ROW_6, InventoryOption.FILL_EMPTY_SLOTS);
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);

        if (lp.getBankprogressId() == BankRobberySmallProgress.SMUGGLER.getId()) {

            setItem(InventorySlot.ROW_1_SLOT_5, Skull.fromUrl("http://textures.minecraft.net/texture/5163dafac1d91a8c91db576caac784336791a6e18d8f7f62778fc47bf146b6", 1).toItemBuilder()
                    .displayName("§d§lBank-Raub")
                    .lore("§7§oDu Raubst für John die One-Island Bank aus",
                            "§7§oDafür bekommst du dein gekauftes Büro zurück",
                            "§7§ound viele Coins!"
                    ).create());

            setItem(InventorySlot.ROW_4_SLOT_2, new ItemBuilder(Material.MAP, 1, 0).displayName("§fVorbereitung: Tunnel Karte").create(), e -> {
                p.closeInventory();
                p.sendMessage("§8[§7§l!§8] §cKnopf im Ohr §8» §fJohn§8 |§7 Okay die erste Vorbereitungs Mission lautet Tunnel du musst zum Schmuggler er steht in der nähe vom Zoll dort muss du den Plan der Bank kaufen und du weißt kein Wort zur Polizei sonst bist du tot!");

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

        if (lp.getBankprogressId() == BankRobberySmallProgress.CUTTER.getId()) {

            setItem(InventorySlot.ROW_1_SLOT_5, Skull.fromUrl("http://textures.minecraft.net/texture/5163dafac1d91a8c91db576caac784336791a6e18d8f7f62778fc47bf146b6", 1).toItemBuilder()
                    .displayName("§d§lBank-Raub")
                    .lore("§7§oDu Raubst für John die One-Island Bank aus",
                            "§7§oDafür bekommst du dein gekauftes Büro zurück",
                            "§7§ound viele Coins!"
                    ).create());

            setItem(InventorySlot.ROW_4_SLOT_2, new ItemBuilder(Material.MAP, 1, 0).displayName("§fVorbereitung: Tunnel Karte").create());
            setItem(InventorySlot.ROW_3_SLOT_2, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 13).displayName("§aErledigt!").create());
            setItem(InventorySlot.ROW_5_SLOT_2, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 13).displayName("§aErledigt!").create());


            setItem(InventorySlot.ROW_3_SLOT_5, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 14).displayName("§cNicht Abgeschlossen!").create());


            setItem(InventorySlot.ROW_4_SLOT_5, new ItemBuilder(Material.LEATHER_CHESTPLATE, 1, 0).displayName("§fVorbereitung: Bank Outift").create(), e -> {
                p.closeInventory();
                p.sendMessage("§8[§7§l!§8] §cKnopf im Ohr §8» §fJohn§8 |§7 Okay die zweite Vorbereitungs Mission bis zum großen Bank Raub. Du musst zur Schneiderei Joguloa und dir da ein Bank Mitarbeiter Outfit besorgen, sie wohnt in einem großen Haus. Ich habe sie schon alles erzählt!");

            });

            setItem(InventorySlot.ROW_5_SLOT_5, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 14).displayName("§cNicht Abgeschlossen!").create());


            setItem(InventorySlot.ROW_3_SLOT_8, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 14).displayName("§cNicht Freigeschaltet!").create());
            setItem(InventorySlot.ROW_4_SLOT_8, new ItemBuilder(Material.BARRIER, 1, 0).displayName("§cNicht Freigeschaltet!").create());
            setItem(InventorySlot.ROW_5_SLOT_8, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 14).displayName("§cNicht Freigeschaltet!").create());

        }


        if (lp.getBankprogressId() == BankRobberySmallProgress.SWORD.getId()) {

            setItem(InventorySlot.ROW_1_SLOT_5, Skull.fromUrl("http://textures.minecraft.net/texture/5163dafac1d91a8c91db576caac784336791a6e18d8f7f62778fc47bf146b6", 1).toItemBuilder()
                    .displayName("§d§lBank-Raub")
                    .lore("§7§oDu Raubst für John die One-Island Bank aus",
                            "§7§oDafür bekommst du dein gekauftes Büro zurück",
                            "§7§ound viele Coins!"
                    ).create());

            setItem(InventorySlot.ROW_4_SLOT_2, new ItemBuilder(Material.MAP, 1, 0).displayName("§fVorbereitung: Tunnel Karte").create());
            setItem(InventorySlot.ROW_3_SLOT_2, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 13).displayName("§aErledigt!").create());
            setItem(InventorySlot.ROW_5_SLOT_2, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 13).displayName("§aErledigt!").create());


            setItem(InventorySlot.ROW_3_SLOT_5, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 13).displayName("§aErledigt!").create());
            setItem(InventorySlot.ROW_4_SLOT_5, new ItemBuilder(Material.LEATHER_CHESTPLATE, 1, 0).displayName("§fVorbereitung: Bank Outift").create());
            setItem(InventorySlot.ROW_5_SLOT_5, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 13).displayName("§aErledigt!").create());


            setItem(InventorySlot.ROW_3_SLOT_8, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 14).displayName("§cNicht Abgeschlossen!").create());

            setItem(InventorySlot.ROW_4_SLOT_8, new ItemBuilder(Material.IRON_SWORD, 1, 0).displayName("§fVorbereitung: Sicherheits Schwert").create(), e -> {
                p.closeInventory();
                if (lp.getProgressId() < StoryProgress.MARVIN_KILL.getId() || lp.getProgressId() >= StoryProgress.EDWARD_LABOR_START.getId()) {
                    p.sendMessage("§8[§7§l!§8] §cKnopf im Ohr §8» §fJohn§8 |§7 Okay jetzt musst du zur Paradise Island reisen und da in die Waffenkammer der Polizei einbrechen um das Sicherheits Schwert zu klauen!");
                } else {
                    LobbyPlugin.getInstance().getMessenger().send(p, "§4Du musst zuerst die One-Island Story weiterführen um die Mission starten zu können!");
                }
            });

            setItem(InventorySlot.ROW_5_SLOT_8, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 14).displayName("§cNicht Abgeschlossen!").create());

        }

        if (lp.getBankprogressId() == BankRobberySmallProgress.BANK_ROBBERY_START.getId()) {
            setItem(InventorySlot.ROW_1_SLOT_1, Skull.fromUrl("http://textures.minecraft.net/texture/5163dafac1d91a8c91db576caac784336791a6e18d8f7f62778fc47bf146b6", 1).toItemBuilder()
                    .displayName("§d§lBank-Raub")
                    .lore("§7§oDu Raubst für John die One-Island Bank aus",
                            "§7§oDafür bekommst du dein gekauftes Büro zurück",
                            "§7§ound viele Coins!"
                    ).create());

            setItem(InventorySlot.ROW_5_SLOT_5, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 13).displayName("§a§r//MCONE//").create());
            setItem(InventorySlot.ROW_4_SLOT_4, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 13).displayName("§a§r//MCONE//").create());
            setItem(InventorySlot.ROW_4_SLOT_6, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 13).displayName("§a§r//MCONE//").create());
            setItem(InventorySlot.ROW_3_SLOT_5, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 13).displayName("§a§r//MCONE//").create());

            setItem(InventorySlot.ROW_4_SLOT_5, new ItemBuilder(Material.INK_SACK, 1, 10).displayName("§fBank Raub").lore("§aZum §lStarten §aklicken").create(), e -> {
                if (currentlyInBank == null) {
                    p.closeInventory();
                    p.sendMessage("§8[§7§l!§8] §cKnopf im Ohr §8» §fJohn§8 |§7 So jetzt geht es los begib dich zur Bank. Gehe bis nach hinten durch und öffne die Eisen Tür, du hast nicht lange Zeit!");
                    lp.setBankProgress(BankRobberySmallProgress.BANK_ROBBERY_MIDDLE);
                    LobbyWorld.ONE_ISLAND.getWorld().getNPC("JohnEnd").toggleVisibility(p, true);

                    LobbyWorld.ONE_ISLAND.getWorld().teleportSilently(p, "bank-robbery");

                    CoreSystem.getInstance().getLabyModAPI().setCurrentServer(p, "MCONE-Banküberfall");

                    p.getInventory().setBoots(ItemBuilder.createLeatherArmorItem(Material.LEATHER_BOOTS, Color.BLACK).create());
                    p.getInventory().setChestplate(ItemBuilder.createLeatherArmorItem(Material.LEATHER_CHESTPLATE, Color.BLACK).create());
                    p.getInventory().setLeggings(ItemBuilder.createLeatherArmorItem(Material.LEATHER_LEGGINGS, Color.GRAY).create());


                    currentlyInBank = p.getUniqueId();
                    robberyTime = Bukkit.getScheduler().runTaskLater(LobbyPlugin.getGamePlugin(), () -> {
                        p.sendMessage("§8[§7§l!§8] §cKnopf im Ohr §8» §fJohn§8 | §7Die Polizei wurde alamiert sie ist in §f30 Sekunden §7da und der §lRaub §7ist gescheitert!");
                        robberyTime = Bukkit.getScheduler().runTaskLater(LobbyPlugin.getGamePlugin(), () -> {
                            robberyTime = Bukkit.getScheduler().runTaskLater(LobbyPlugin.getGamePlugin(), () -> {
                                p.sendMessage("§8[§7§l!§8] §cKnopf im Ohr §8» §fJohn§8 | §7Du hast noch 3 Sekunden");
                                robberyTime = Bukkit.getScheduler().runTaskLater(LobbyPlugin.getGamePlugin(), () -> {
                                    p.sendMessage("§8[§7§l!§8] §cKnopf im Ohr §8» §fJohn§8 | §7Du hast noch 2 Sekunden");
                                    robberyTime = Bukkit.getScheduler().runTaskLater(LobbyPlugin.getGamePlugin(), () -> {
                                        p.sendMessage("§8[§7§l!§8] §cKnopf im Ohr §8» §fJohn§8 | §7Du hast nur noch §f1 Sekunde");
                                        robberyTime = Bukkit.getScheduler().runTaskLater(LobbyPlugin.getGamePlugin(), () -> {
                                            currentlyInBank = null;
                                            p.getInventory().setArmorContents(null);

                                            lp.setBankProgress(BankRobberySmallProgress.BANK_ROBBERY_START);
                                            if (lp.hasLobbyItem(LobbyItem.GOLD_BARDING)) {
                                                lp.removeLobbyItem(LobbyItem.GOLD_BARDING);
                                            }

                                            p.getInventory().setItem(2, null);

                                            LobbyWorld.ONE_ISLAND.getWorld().teleportSilently(p, "office-entrance");
                                            Sound.error(player);
                                            Sound.play(p, org.bukkit.Sound.EXPLODE);
                                            CoreSystem.getInstance().createTitle().fadeIn(1).stay(3).title("§cBank Raub").subTitle("§4gescheitert").fadeOut(1).send(p);
                                            p.sendMessage("§8[§7§l!§8] §cKnopf im Ohr §8» §fJohn§8 | §7Die §fMission §7ist gescheitert. Du hast zu §flange gebraucht§7!");

                                        }, 20);
                                    }, 20);
                                }, 20);
                            }, 20);
                        }, 540);
                    }, 450);


                } else {
                    p.closeInventory();
                    p.sendMessage("§8[§7§l!§8] §cKnopf im Ohr §8» §fJohn§8 |§7 Es §füberfällt momentan schon einer die §fBank§7, bitte warte kurz!");
                }
            });


        }
        openInventory();
    }
}



