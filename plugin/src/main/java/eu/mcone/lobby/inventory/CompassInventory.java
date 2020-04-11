/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.inventory;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.gamemode.Gamemode;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.item.Skull;
import eu.mcone.lobby.Lobby;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.enums.Progress;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.items.manager.OfficeManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;

public class CompassInventory extends CoreInventory {

    static {
        CoreSystem.getInstance().getCooldownSystem().setCustomCooldownFor(CompassInventory.class, 3);
    }

    public CompassInventory(Player p) {
        super("§8» §3§lNavigator", p, InventorySlot.ROW_5, InventoryOption.FILL_EMPTY_SLOTS);

        if (!CoreSystem.getInstance().getCooldownSystem().addAndCheck(CoreSystem.getInstance(), this.getClass(), p.getUniqueId()))
            return;

        p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);

        setItem(InventorySlot.ROW_1_SLOT_1, CoreInventory.PLACEHOLDER_ITEM, e -> CoreSystem.getInstance().getChannelHandler().createSetRequest(p, "CONNECT", "Survival"));
        setItem(InventorySlot.ROW_1_SLOT_2, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 11).displayName("§8//§oMCONE§8//").create());
        setItem(InventorySlot.ROW_2_SLOT_1, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 11).displayName("§8//§oMCONE§8//").create());

        setItem(InventorySlot.ROW_1_SLOT_9, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 11).displayName("§8//§oMCONE§8//").create());
        setItem(InventorySlot.ROW_1_SLOT_8, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 11).displayName("§8//§oMCONE§8//").create());
        setItem(InventorySlot.ROW_2_SLOT_9, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 11).displayName("§8//§oMCONE§8//").create());


        setItem(InventorySlot.ROW_4_SLOT_1, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 11).displayName("§8//§oMCONE§8//").create());
        setItem(InventorySlot.ROW_5_SLOT_1, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 11).displayName("§8//§oMCONE§8//").create());
        setItem(InventorySlot.ROW_5_SLOT_2, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 11).displayName("§8//§oMCONE§8//").create());

        setItem(InventorySlot.ROW_4_SLOT_9, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 11).displayName("§8//§oMCONE§8//").create());
        setItem(InventorySlot.ROW_5_SLOT_9, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 11).displayName("§8//§oMCONE§8//").create());
        setItem(InventorySlot.ROW_5_SLOT_8, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 11).displayName("§8//§oMCONE§8//").create());

        openInventory();


        Bukkit.getScheduler().runTaskLater(Lobby.getSystem(), () -> {
            p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);

            setItem(InventorySlot.ROW_1_SLOT_3, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 0).displayName("§8//§oMCONE§8//").create());
            setItem(InventorySlot.ROW_2_SLOT_2, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 0).displayName("§8//§oMCONE§8//").create());

            setItem(InventorySlot.ROW_5_SLOT_3, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 0).displayName("§8//§oMCONE§8//").create());
            setItem(InventorySlot.ROW_4_SLOT_2, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 0).displayName("§8//§oMCONE§8//").create());


            setItem(InventorySlot.ROW_1_SLOT_7, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 0).displayName("§8//§oMCONE§8//").create());
            setItem(InventorySlot.ROW_2_SLOT_8, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 0).displayName("§8//§oMCONE§8//").create());

            setItem(InventorySlot.ROW_4_SLOT_8, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 0).displayName("§8//§oMCONE§8//").create());
            setItem(InventorySlot.ROW_5_SLOT_7, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 0).displayName("§8//§oMCONE§8//").create());

            openInventory();


            Bukkit.getScheduler().runTaskLater(Lobby.getSystem(), () -> {
                p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);

                setItem(InventorySlot.ROW_2_SLOT_5, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 3).displayName("§8//§oMCONE§8//").create());
                setItem(InventorySlot.ROW_3_SLOT_4, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 3).displayName("§8//§oMCONE§8//").create());
                setItem(InventorySlot.ROW_4_SLOT_5, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 3).displayName("§8//§oMCONE§8//").create());
                setItem(InventorySlot.ROW_3_SLOT_6, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 3).displayName("§8//§oMCONE§8//").create());

                openInventory();


                Bukkit.getScheduler().runTaskLater(Lobby.getSystem(), () -> {
                    p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
                    setItem(InventorySlot.ROW_1_SLOT_4, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
                    setItem(InventorySlot.ROW_1_SLOT_5, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
                    setItem(InventorySlot.ROW_1_SLOT_6, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());


                    setItem(InventorySlot.ROW_5_SLOT_4, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
                    setItem(InventorySlot.ROW_5_SLOT_5, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
                    setItem(InventorySlot.ROW_5_SLOT_6, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());

                    setItem(InventorySlot.ROW_2_SLOT_3, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
                    setItem(InventorySlot.ROW_3_SLOT_2, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
                    setItem(InventorySlot.ROW_4_SLOT_3, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());


                    setItem(InventorySlot.ROW_2_SLOT_7, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
                    setItem(InventorySlot.ROW_3_SLOT_8, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
                    setItem(InventorySlot.ROW_4_SLOT_7, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());

                    openInventory();


                    Bukkit.getScheduler().runTaskLater(Lobby.getSystem(), () -> {
                        p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);

                        setItem(InventorySlot.ROW_3_SLOT_5, new ItemBuilder(Material.NETHER_STAR, 1, 0)
                                        .displayName("§f§lSpawn")
                                        .lore("§7§oZurück zum Lobby Spawn.", "", "§8» §f§nLinksklick§8 | §7§oTeleportieren")
                                        .create(),
                                e -> {
                                    player.closeInventory();
                                    LobbyPlugin.getInstance().getLobbyPlayer(p).teleportAnimation("Spawn");

                                });


                        setItem(InventorySlot.ROW_2_SLOT_4, new ItemBuilder(Gamemode.SKYPVP.getItem(), 1, 0)
                                        .displayName(Gamemode.SKYPVP.getLabel())
                                        .lore("§7§oFinde deine Gegner auf einer Sky-Map", "§7§ound töte sie, um Coins zu erhalten!", "", "§8» §f§nLinksklick§8 | §7§oTeleportieren")
                                        .create(),
                                e -> {
                                    player.closeInventory();
                                    LobbyPlugin.getInstance().getLobbyPlayer(p).teleportAnimation("skypvp");

                                });

                        setItem(InventorySlot.ROW_1_SLOT_7, new ItemBuilder(Material.REDSTONE_COMPARATOR, 1, 0)
                                        .displayName("§eReplay")
                                        .lore("§7§oSchaue dir mit deinen Freunden", "§7§odeinen alten Spielrunden an!", "", "§8» §f§nLinksklick§8 | §7§oTeleportieren")
                                        .create(),
                                e -> {
                                    player.closeInventory();
                                    CoreSystem.getInstance().getChannelHandler().createSetRequest(p, "CONNECT", "replay");

                                });

                        setItem(InventorySlot.ROW_1_SLOT_3, new Skull(p.getName(), 1).toItemBuilder()
                                        .displayName("§5Festival / Community")
                                        .lore("§7§oTreffe dich mit Freunden auf dem ", "§7§oFestival/Community und", "§7§oreise mit einem gültigen Ticket hin!", "", "§8» §f§nLinksklick§8 | §7§oTeleportieren")
                                        .create(),
                                e -> {
                                    player.closeInventory();
                                    LobbyPlugin.getInstance().getLobbyPlayer(p).teleportAnimation("community");

                                });

                        setItem(InventorySlot.ROW_2_SLOT_6, new ItemBuilder(Gamemode.BEDWARS.getItem(), 1, 0)
                                        .displayName(Gamemode.BEDWARS.getLabel())
                                        .lore("§7§oBaue Betten von anderen Spielern ab", "§7§ound start mit Kits durch!", "", "§8» §f§nLinksklick§8 | §7§oTeleportieren")
                                        .create(),
                                e -> {
                                    player.closeInventory();
                                    LobbyPlugin.getInstance().getLobbyPlayer(p).teleportAnimation("bedwars");

                                    if (p.getWorld().getName().equalsIgnoreCase("Office")) {
                                        OfficeManager.unVanishPlayer(p);
                                    }
                                }
                        );


                        setItem(InventorySlot.ROW_4_SLOT_6, new ItemBuilder(Gamemode.MINEWAR.getItem(), 1, 0)
                                        .displayName(Gamemode.MINEWAR.getLabel())
                                        .lore("§7§oBaue Erze ab und erhalte", "§7§ocoole Items und töte Gegner!", "", "§8» §f§nLinksklick§8 | §7§oTeleportieren")
                                        .create(),
                                e -> {
                                    player.closeInventory();
                                    LobbyPlugin.getInstance().getLobbyPlayer(p).teleportAnimation("minewar");

                                    if (p.getWorld().getName().equalsIgnoreCase("Office")) {
                                        OfficeManager.unVanishPlayer(p);
                                    }
                                }
                        );

                        setItem(InventorySlot.ROW_3_SLOT_3, new ItemBuilder(Gamemode.BUILD.getItem(), 1, 0)
                                        .displayName(Gamemode.BUILD.getLabel())
                                        .lore("§7§oBuild Server. Überzeuge uns von deinen Baukünsten", "§7§ound werde Builder im MC ONE Team!", "", "§8» §f§nLinksklick§8 | §7§oTeleportieren")
                                        .create(),
                                e -> {
                                    player.closeInventory();
                                    LobbyPlugin.getInstance().getLobbyPlayer(p).teleportAnimation("build");

                                });

                        setItem(InventorySlot.ROW_3_SLOT_7, new ItemBuilder(Gamemode.KNOCKIT.getItem(), 1, 0)
                                        .displayName(Gamemode.KNOCKIT.getLabel())
                                        .enchantment(Enchantment.KNOCKBACK, 1)
                                        .lore("§7§oSchlage die Gegner von der Plattform um Coins", "§7§ozu erhalten!", "", "§8» §f§nLinksklick§8 | §7§oTeleportieren")
                                        .itemFlags(ItemFlag.HIDE_ENCHANTS)
                                        .create(),
                                e -> {
                                    player.closeInventory();
                                    LobbyPlugin.getInstance().getLobbyPlayer(p).teleportAnimation("knockit");

                                });


                        setItem(InventorySlot.ROW_4_SLOT_4, new ItemBuilder(Material.DEAD_BUSH, 1, 0)
                                        .displayName("§aTrashwars")
                                        .lore("§7§oKämpfe um den Sieg mit einem Müll", "§7§oInventar auf einer Insel voller Gegner", "", "§8» §f§nLinksklick§8 | §7§oTeleportieren")
                                        .create(),
                                e -> {
                                    player.closeInventory();
                                    LobbyPlugin.getInstance().getLobbyPlayer(p).teleportAnimation("trashwars");

                                });


                        setItem(InventorySlot.ROW_5_SLOT_1, new ItemBuilder(Material.EMERALD, 1, 0)
                                        .displayName("§eHändler")
                                        .lore("§7§oKaufe dir coole Items oder seltene Truhen", "§7§ofür das Chest-Opening", "", "§8» §f§nLinksklick§8 | §7§oTeleportieren")
                                        .create(),
                                e -> {
                                    player.closeInventory();
                                    LobbyPlugin.getInstance().getLobbyPlayer(p).teleportAnimation("merchant");

                                });

                        ///

                        setItem(InventorySlot.ROW_5_SLOT_9, new ItemBuilder(Material.DIAMOND, 1, 0)
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

                        setItem(InventorySlot.ROW_3_SLOT_1, new ItemBuilder(Material.CHEST, 1, 0)
                                        .displayName("§eChest-Opening")
                                        .lore("§7§oÖffne Kisten und gewinne coole Items!", "", "§8» §f§nLinksklick§8 | §7§oTeleportieren")
                                        .create(),
                                e -> {
                                    player.closeInventory();
                                    LobbyPlugin.getInstance().getLobbyPlayer(p).teleportAnimation("chest-opening");

                                });

                        setItem(InventorySlot.ROW_1_SLOT_5, new ItemBuilder(Material.GOLD_INGOT, 1, 0)
                                        .displayName("§aBank")
                                        .lore("§7§oEröffne ein Konto und hole Dir", "§7§ojeden Tag coole Belohnungen ab!", "", "§8» §f§nLinksklick§8 | §7§oTeleportieren")
                                        .create(),

                                e -> {
                                    player.closeInventory();
                                    LobbyPlugin.getInstance().getLobbyPlayer(p).teleportAnimation("bank");

                                });
                        setItem(InventorySlot.ROW_1_SLOT_9, new ItemBuilder(Material.REDSTONE, 1, 0)
                                        .displayName("§dBüro")
                                        .lore("§7§oKaufe dir ein tolles Büro", "§7§ooder Besuche dein Büro", "", "§8» §f§nLinksklick§8 | §7§oTeleportieren")
                                        .create(),

                                e -> {
                                    player.closeInventory();
                                    LobbyPlugin.getInstance().getLobbyPlayer(p).teleportAnimation("office-entrance");

                                });

                        setItem(InventorySlot.ROW_5_SLOT_3, new ItemBuilder(Material.DIAMOND_BOOTS, 1, 0)
                                        .displayName("§bJumpAndRun §8| §fLobbygame")
                                        .lore("§7§oBesuche die JumpAndRun Tafel", "§7§oUm alle Jump and Runs zu sehen", "§7§oUnd um sie zu starten", "", "§8» §f§nLinksklick§8 | §7§oTeleportieren")
                                        .create(),

                                e -> {
                                    player.closeInventory();
                                    LobbyPlugin.getInstance().getLobbyPlayer(p).teleportAnimation("jumpandrun-board");

                                });

                        setItem(InventorySlot.ROW_5_SLOT_5, new ItemBuilder(Material.STICK, 1, 0)
                                        .displayName("§eFangen §8| §fLobbygame")
                                        .lore("§7§oFange andere Spieler", "§7§ooder laufe vor anderen weg", "", "§8» §f§nLinksklick§8 | §7§oSpielen")
                                        .create(),

                                e -> {


                                    p.closeInventory();
                                    LobbyPlugin.getInstance().getCatchManager().setStart(p);
                                });


                        setItem(InventorySlot.ROW_5_SLOT_7, new ItemBuilder(Material.IRON_SWORD, 1, 0)
                                        .displayName("§cOne-Hit §8| §fLobbygame")
                                        .lore("§7§oSpiele ein bekannten Modi", "§7§omit deinen Freunden auf der Lobby", "", "§8» §f§nLinksklick§8 | §7§oSpielen")
                                        .create(),

                                e -> {


                                    p.closeInventory();
                                    LobbyPlugin.getInstance().getOneHitManager().setStart(p);
                                });


                        setItem(InventorySlot.ROW_3_SLOT_9, new ItemBuilder(Material.PUMPKIN, 1, 0)
                                        .displayName("§7§lSchugler")
                                        .lore("§7§oKaufe dir illegale Ware", "§7§owie zum Beispiel seltene Kisten", "§7§ofür das Chest-Opening", "", "§8» §f§nLinksklick§8 | §7§oTeleportieren")
                                        .create(),

                                e -> {
                                    player.closeInventory();
                                    LobbyPlugin.getInstance().getLobbyPlayer(p).teleportAnimation("smuggler");

                                });

                        setItem(InventorySlot.ROW_1_SLOT_1, new ItemBuilder(Material.BOOK, 1, 0).displayName("§3Die Story").lore("§7§oSpiele die Story und erhalte", "§7§ocoole Items und viele Coins!", "", "§7§oTeleportiere zum Story NPC", "§8» §f§nLinksklick§8 | §7§oTeleportieren").create(), e -> {
                            LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);

                            if (lp.getProgressId() == 0) {
                                LobbyPlugin.getInstance().getLobbyPlayer(p).teleportAnimation("storyspawn");
                                p.closeInventory();
                            } else {
                                LobbyPlugin.getInstance().getMessager().send(p, "§2Du wurdest in der nähe des letzten Story-NPC teleportiert, mit dem du zuletzt interagiert hast!");
                                LobbyPlugin.getInstance().getLobbyPlayer(p).teleportAnimation(Progress.getProgressByID(lp.getProgressId()).getNpc().getData().getLocation().bukkit());
                            }
                        });

                        openInventory();
                    }, 2L);
                }, 2L);
            }, 2L);
        }, 2L);
    }

}
