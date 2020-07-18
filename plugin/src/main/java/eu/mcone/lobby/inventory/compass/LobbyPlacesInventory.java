package eu.mcone.lobby.inventory.compass;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.lobby.Lobby;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.api.enums.StoryProgress;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.api.player.SpawnVillage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public class LobbyPlacesInventory extends CoreInventory {

    static {
        CoreSystem.getInstance().getCooldownSystem().setCustomCooldownFor(LobbyPlacesInventory.class, 3);
    }

    public LobbyPlacesInventory(Player p) {
        super("§8» §3§lLobby-Orte", p, InventorySlot.ROW_5, InventoryOption.FILL_EMPTY_SLOTS);
        if (!CoreSystem.getInstance().getCooldownSystem().addAndCheck(CoreSystem.getInstance(), this.getClass(), p.getUniqueId()))
            return;

        p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);

        setItem(InventorySlot.ROW_5_SLOT_1, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 8).displayName("§8//§oMCONE§8//").create());
        setItem(InventorySlot.ROW_5_SLOT_2, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 8).displayName("§8//§oMCONE§8//").create());

        setItem(InventorySlot.ROW_5_SLOT_4, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 8).displayName("§8//§oMCONE§8//").create());
        setItem(InventorySlot.ROW_5_SLOT_6, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 8).displayName("§8//§oMCONE§8//").create());


        setItem(InventorySlot.ROW_5_SLOT_8, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 8).displayName("§8//§oMCONE§8//").create());
        setItem(InventorySlot.ROW_5_SLOT_9, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 8).displayName("§8//§oMCONE§8//").create());


        setItem(InventorySlot.ROW_4_SLOT_1, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());

        setItem(InventorySlot.ROW_4_SLOT_2, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
        setItem(InventorySlot.ROW_4_SLOT_3, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
        setItem(InventorySlot.ROW_4_SLOT_4, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
        setItem(InventorySlot.ROW_4_SLOT_5, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());

        setItem(InventorySlot.ROW_4_SLOT_6, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
        setItem(InventorySlot.ROW_4_SLOT_7, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
        setItem(InventorySlot.ROW_4_SLOT_8, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
        setItem(InventorySlot.ROW_4_SLOT_9, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());


        openInventory();

        Bukkit.getScheduler().runTaskLater(Lobby.getSystem(), () -> {
            p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);

            setItem(InventorySlot.ROW_1_SLOT_4, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 9).displayName("§8//§oMCONE§8//").create());
            setItem(InventorySlot.ROW_1_SLOT_5, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 9).displayName("§8//§oMCONE§8//").create());
            setItem(InventorySlot.ROW_1_SLOT_6, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 9).displayName("§8//§oMCONE§8//").create());

            setItem(InventorySlot.ROW_3_SLOT_4, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 9).displayName("§8//§oMCONE§8//").create());
            setItem(InventorySlot.ROW_3_SLOT_5, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 9).displayName("§8//§oMCONE§8//").create());
            setItem(InventorySlot.ROW_3_SLOT_6, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 9).displayName("§8//§oMCONE§8//").create());

            setItem(InventorySlot.ROW_2_SLOT_4, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 9).displayName("§8//§oMCONE§8//").create());
            setItem(InventorySlot.ROW_2_SLOT_6, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 9).displayName("§8//§oMCONE§8//").create());

            setItem(InventorySlot.ROW_2_SLOT_2, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 3).displayName("§8//§oMCONE§8//").create());
            setItem(InventorySlot.ROW_2_SLOT_8, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 3).displayName("§8//§oMCONE§8//").create());

            Bukkit.getScheduler().runTaskLater(Lobby.getSystem(), () -> {
                p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
                setItem(InventorySlot.ROW_1_SLOT_1, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 9).displayName("§8//§oMCONE§8//").create());
                setItem(InventorySlot.ROW_3_SLOT_1, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 9).displayName("§8//§oMCONE§8//").create());

                setItem(InventorySlot.ROW_2_SLOT_1, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 3).displayName("§8//§oMCONE§8//").create());

                setItem(InventorySlot.ROW_1_SLOT_9, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 9).displayName("§8//§oMCONE§8//").create());
                setItem(InventorySlot.ROW_2_SLOT_9, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 3).displayName("§8//§oMCONE§8//").create());

                setItem(InventorySlot.ROW_3_SLOT_9, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 9).displayName("§8//§oMCONE§8//").create());

                Bukkit.getScheduler().runTaskLater(Lobby.getSystem(), () -> {
                    p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
                    setItem(InventorySlot.ROW_2_SLOT_2, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 3).displayName("§8//§oMCONE§8//").create());
                    setItem(InventorySlot.ROW_2_SLOT_8, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 3).displayName("§8//§oMCONE§8//").create());
                    setItem(InventorySlot.ROW_1_SLOT_3, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 3).displayName("§8//§oMCONE§8//").create());
                    setItem(InventorySlot.ROW_1_SLOT_7, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 3).displayName("§8//§oMCONE§8//").create());
                    setItem(InventorySlot.ROW_3_SLOT_3, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 3).displayName("§8//§oMCONE§8//").create());
                    setItem(InventorySlot.ROW_3_SLOT_7, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 3).displayName("§8//§oMCONE§8//").create());

                    Bukkit.getScheduler().runTaskLater(Lobby.getSystem(), () -> {
                        p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
                        Bukkit.getScheduler().runTaskLater(Lobby.getSystem(), () -> {
                            setItem(InventorySlot.ROW_2_SLOT_5, new ItemBuilder(Material.BOOK, 1, 0).displayName("§3Die Story").lore("§7§oSpiele die Story und erhalte", "§7§ocoole Items und viele Coins!", "", "§7§oTeleportiere zum Story NPC", "§8» §f§nLinksklick§8 | §7§oTeleportieren").create(), e -> {
                                LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);

                                if (lp.getProgressId() == 0) {
                                    LobbyPlugin.getInstance().getLobbyPlayer(p).teleportAnimation("storyspawn");
                                    p.closeInventory();
                                } else {
                                    LobbyPlugin.getInstance().getMessenger().send(p, "§2Du wurdest in der nähe des letzten Story-NPC teleportiert, mit dem du zuletzt interagiert hast!");
                                    LobbyPlugin.getInstance().getLobbyPlayer(p).teleportAnimation(StoryProgress.getProgressByID(lp.getProgressId()).getNpc().getData().getLocation().bukkit());
                                }
                            });

                        }, 2L);


                        setItem(InventorySlot.ROW_2_SLOT_3, new ItemBuilder(Material.PUMPKIN, 1, 0)
                                        .displayName("§7§lSchmuggler")
                                        .lore("§7§oKaufe dir illegale Ware", "§7§owie zum Beispiel seltene Kisten", "§7§ofür das Chest-Opening", "", "§8» §f§nLinksklick§8 | §7§oTeleportieren")
                                        .create(),

                                e -> {
                                    player.closeInventory();
                                    LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);
                                    if (lp.getSettings().getSpawnVillage().equals(SpawnVillage.VILLAGE_2)) {
                                        LobbyPlugin.getInstance().getLobbyPlayer(p).teleportAnimation("smuggler2");
                                    } else if (lp.getSettings().getSpawnVillage().equals(SpawnVillage.VILLAGE_1)) {
                                        LobbyPlugin.getInstance().getLobbyPlayer(p).teleportAnimation("smuggler");
                                    } else {
                                        LobbyPlugin.getInstance().getLobbyPlayer(p).teleportAnimation("smuggler");
                                    }

                                });


                        setItem(InventorySlot.ROW_2_SLOT_8, new ItemBuilder(Material.PAPER, 1, 0)
                                        .displayName("§f§lCasino")
                                        .lore("§7§oSpiele Glücksspiele", "§7§ound gewinne das 9x Fache", "§7§odeines Einsatzes", "", "§8» §f§nLinksklick§8 | §7§oTeleportieren")
                                        .create(),

                                e -> {
                                    player.closeInventory();
                                    LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);
                                    LobbyPlugin.getInstance().getLobbyPlayer(p).teleportAnimation("casino");
                                });


                        setItem(InventorySlot.ROW_3_SLOT_2, new ItemBuilder(Material.EMERALD, 1, 0)
                                        .displayName("§eHändler")
                                        .lore("§7§oKaufe dir coole Items oder seltene Truhen", "§7§ofür das Chest-Opening", "", "§8» §f§nLinksklick§8 | §7§oTeleportieren")
                                        .create(),
                                e -> {
                                    player.closeInventory();

                                    LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);
                                    if (lp.getSettings().getSpawnVillage().equals(SpawnVillage.VILLAGE_2)) {
                                        LobbyPlugin.getInstance().getLobbyPlayer(p).teleportAnimation("merchant2");
                                    } else if (lp.getSettings().getSpawnVillage().equals(SpawnVillage.VILLAGE_1)) {
                                        LobbyPlugin.getInstance().getLobbyPlayer(p).teleportAnimation("merchant");
                                    } else {
                                        LobbyPlugin.getInstance().getLobbyPlayer(p).teleportAnimation("merchant");
                                    }

                                });

                        ///

                        setItem(InventorySlot.ROW_2_SLOT_7, new ItemBuilder(Material.DIAMOND, 1, 0)
                                        .displayName("§eAnkäufer")
                                        .lore("§7§oVerkaufe deine gekauften coolen Items", "§7§oDu bekommst sogar ein paar Emeralds zurück!", "", "§8» §f§nLinksklick§8 | §7§oTeleportieren")
                                        .create(),
                                e -> {
                                    player.closeInventory();

                                    LobbyPlugin.getInstance().getLobbyPlayer(p).teleportAnimation(
                                            CoreSystem.getInstance().getNpcManager().getNPC(
                                                    CoreSystem.getInstance().getWorldManager().getWorld("Lobby-OneIsland"), "vendor"
                                            ).getLocation()
                                    );
                                });

                        setItem(InventorySlot.ROW_1_SLOT_2, new ItemBuilder(Material.CHEST, 1, 0)
                                        .displayName("§eChest-Opening")
                                        .lore("§7§oÖffne Kisten und gewinne coole Items!", "", "§8» §f§nLinksklick§8 | §7§oTeleportieren")
                                        .create(),
                                e -> {
                                    player.closeInventory();
                                    LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);
                                    if (lp.getSettings().getSpawnVillage().equals(SpawnVillage.VILLAGE_2)) {
                                        LobbyWorld.ONE_ISLAND.getWorld().teleport(p, "chest-opening2");
                                    } else if (lp.getSettings().getSpawnVillage().equals(SpawnVillage.VILLAGE_1)) {
                                        LobbyWorld.ONE_ISLAND.getWorld().teleport(p, "chest-opening");
                                    } else {
                                        LobbyWorld.ONE_ISLAND.getWorld().teleport(p, "chest-opening");
                                    }

                                });

                        setItem(InventorySlot.ROW_3_SLOT_8, new ItemBuilder(Material.GOLD_INGOT, 1, 0)
                                        .displayName("§aBank")
                                        .lore("§7§oEröffne ein Konto und hole Dir", "§7§ojeden Tag coole Belohnungen ab!", "", "§8» §f§nLinksklick§8 | §7§oTeleportieren")
                                        .create(),

                                e -> {
                                    player.closeInventory();
                                    LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);
                                    if (lp.getSettings().getSpawnVillage().equals(SpawnVillage.VILLAGE_2)) {
                                        LobbyPlugin.getInstance().getLobbyPlayer(p).teleportAnimation("bank2");
                                    } else if (lp.getSettings().getSpawnVillage().equals(SpawnVillage.VILLAGE_1)) {
                                        LobbyPlugin.getInstance().getLobbyPlayer(p).teleportAnimation("bank");
                                    } else {
                                        LobbyPlugin.getInstance().getLobbyPlayer(p).teleportAnimation("bank2");
                                    }

                                });
                        setItem(InventorySlot.ROW_1_SLOT_8, new ItemBuilder(Material.REDSTONE, 1, 0)
                                        .displayName("§dBüro")
                                        .lore("§7§oKaufe dir ein tolles Büro", "§7§ooder Besuche dein Büro", "", "§8» §f§nLinksklick§8 | §7§oTeleportieren", "§8» §f§nRechtslick§8 | §7§oSchnellbeitritt")
                                        .create(),

                                e -> {
                                    if (e.getClick().equals(ClickType.RIGHT)) {
                                        player.closeInventory();
                                        LobbyPlugin.getInstance().getOfficeManager().joinOffice(p);
                                    } else if (e.getClick().equals(ClickType.LEFT)) {
                                        player.closeInventory();
                                        LobbyPlugin.getInstance().getLobbyPlayer(p).teleportAnimation("office-entrance");
                                    }
                                });

                        Bukkit.getScheduler().runTaskLater(Lobby.getSystem(), () -> {

                            p.playSound(p.getLocation(), Sound.CLICK, 1, 1);

                            setItem(InventorySlot.ROW_5_SLOT_3, new ItemBuilder(Material.IRON_SWORD, 1, 0)
                                            .displayName("§fLobby-Games")
                                            .lore("§7§oSpiele in der Lobby kleinere Minigames", "§7§owie zum Beispiel Fangen", "", "§8» §f§nLinksklick§8 | §7§oÖffnen")
                                            .create(),
                                    e -> {
                                        new LobbyGamesInventory(p);
                                        player.playSound(p.getLocation(), Sound.NOTE_STICKS, 1, 1);
                                    });

                            setItem(InventorySlot.ROW_5_SLOT_5, new ItemBuilder(Material.EMERALD, 1, 0)
                                            .displayName("§fEvents")
                                            .lore("§7§oSpiele ein Community Event", "§7§omit deinen Freunden!", "", "§8» §f§nLinksklick§8 | §7§oÖffnen")
                                            .create(),
                                    e -> {
                                        new EventInventory(p);
                                        player.playSound(p.getLocation(), Sound.NOTE_STICKS, 1, 1);
                                    });

                            setItem(InventorySlot.ROW_5_SLOT_7, new ItemBuilder(Material.NETHER_STAR, 1, 0)
                                            .displayName("§fMinigames")
                                            .lore("§7§oSchaue dir unsere Spielmodi an", "§7§ound telepotiere dich zu ihnen!", "", "§8» §f§nLinksklick§8 | §7§oÖffnen")
                                            .create(),
                                    e -> {
                                        player.playSound(p.getLocation(), Sound.NOTE_STICKS, 1, 1);
                                        new MinigamesInventory(p);

                                    });

                        }, 1L);

                    }, 2L);
                }, 2L);
            }, 2L);
        }, 2L);
    }

}

