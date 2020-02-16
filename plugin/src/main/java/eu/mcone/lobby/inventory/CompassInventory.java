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
import eu.mcone.coresystem.api.bukkit.npc.entity.PlayerNpc;
import eu.mcone.coresystem.api.bukkit.scoreboard.CoreObjective;
import eu.mcone.lobby.Lobby;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.api.enums.Progress;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.items.manager.OfficeManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

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


        Bukkit.getScheduler().runTaskLater(Lobby.getInstance(), () -> {
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


            Bukkit.getScheduler().runTaskLater(Lobby.getInstance(), () -> {
                p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);

                setItem(InventorySlot.ROW_2_SLOT_5, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 3).displayName("§8//§oMCONE§8//").create());
                setItem(InventorySlot.ROW_3_SLOT_4, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 3).displayName("§8//§oMCONE§8//").create());
                setItem(InventorySlot.ROW_4_SLOT_5, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 3).displayName("§8//§oMCONE§8//").create());
                setItem(InventorySlot.ROW_3_SLOT_6, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 3).displayName("§8//§oMCONE§8//").create());

                openInventory();


                Bukkit.getScheduler().runTaskLater(Lobby.getInstance(), () -> {
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


                    Bukkit.getScheduler().runTaskLater(Lobby.getInstance(), () -> {
                        p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);

                        setItem(InventorySlot.ROW_3_SLOT_5, new ItemBuilder(Material.NETHER_STAR, 1, 0)
                                        .displayName("§f§lSpawn")
                                        .lore("§7§oZurück zum Lobby Spawn.", "", "§8» §f§nLinksklick§8 | §7§oTeleportieren")
                                        .create(),
                                e -> {
                                    player.closeInventory();
                                    LobbyPlugin.getInstance().getGamePlayer(p).teleportAnimation("Spawn");

                                });


                        setItem(InventorySlot.ROW_2_SLOT_4, new ItemBuilder(Gamemode.SKYPVP.getItem(), 1, 0)
                                        .displayName(Gamemode.SKYPVP.getLabel())
                                        .lore("§7§oFinde deine Gegner auf einer Sky-Map", "§7§ound töte sie, um Coins zu erhalten!", "", "§8» §f§nLinksklick§8 | §7§oTeleportieren")
                                        .create(),
                                e -> {
                                    player.closeInventory();
                                    LobbyPlugin.getInstance().getGamePlayer(p).teleportAnimation("skypvp");

                                });

                        setItem(InventorySlot.ROW_2_SLOT_6, new ItemBuilder(Gamemode.CITYBUILD.getItem(), 1, 0)
                                        .displayName(Gamemode.CITYBUILD.getLabel())
                                        .lore("§7§oErbaue dein eigenes Grundstück", "§7§ound werde einer der Reichsten Spieler", "§7§oauf dem Server!", "", "§8» §f§nLinksklick§8 | §7§oTeleportieren")
                                        .create(),
                                e -> {
                                    player.closeInventory();
                                    LobbyPlugin.getInstance().getGamePlayer(p).teleportAnimation("build");

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
                                    LobbyPlugin.getInstance().getGamePlayer(p).teleportAnimation("build");

                                });

                        setItem(InventorySlot.ROW_3_SLOT_7, new ItemBuilder(Gamemode.KNOCKIT.getItem(), 1, 0)
                                        .displayName(Gamemode.KNOCKIT.getLabel())
                                        .enchantment(Enchantment.KNOCKBACK, 1)
                                        .lore("§7§oSchlage die Gegner von der Plattform um Coins", "§7§ozu erhalten!", "", "§8» §f§nLinksklick§8 | §7§oTeleportieren")
                                        .itemFlags(ItemFlag.HIDE_ENCHANTS)
                                        .create(),
                                e -> {
                                    player.closeInventory();
                                    LobbyPlugin.getInstance().getGamePlayer(p).teleportAnimation("knockit");

                                });


                        setItem(InventorySlot.ROW_4_SLOT_1, new ItemBuilder(Material.EMERALD, 1, 0)
                                        .displayName("§eHändler")
                                        .lore("§7§oKaufe dir coole Items oder seltene Truhen", "§7§ofür das Chest-Opening", "", "§8» §f§nLinksklick§8 | §7§oTeleportieren")
                                        .create(),
                                e -> {
                                    player.closeInventory();
                                    LobbyPlugin.getInstance().getGamePlayer(p).teleportAnimation("merchant");

                                });

                        setItem(InventorySlot.ROW_2_SLOT_1, new ItemBuilder(Material.DIAMOND, 1, 0)
                                        .displayName("§eAnKäufer")
                                        .lore("§7§oVerkaufe deine gekauften coolen Items", "§7§oDu bekommst sogar ein paar Emeralds zurück!", "", "§8» §f§nLinksklick§8 | §7§oTeleportieren")
                                        .create(),
                                e -> {
                                    player.closeInventory();

                                    PlayerNpc playernpc_vendor = ((PlayerNpc) CoreSystem.getInstance().getNpcManager().getNPC(CoreSystem.getInstance().getWorldManager().getWorld("Lobby-OneIsland"), "vendor"));

                                    if (playernpc_vendor.getData().getTempLocation() != null) {
                                        LobbyPlugin.getInstance().getGamePlayer(p).teleportAnimation(playernpc_vendor.getData().getTempLocation().bukkit());
                                    } else {
                                        LobbyPlugin.getInstance().getGamePlayer(p).teleportAnimation(playernpc_vendor.getData().getTempLocation().bukkit());
                                    }


                                });

                        setItem(InventorySlot.ROW_4_SLOT_4, new ItemBuilder(Material.CHEST, 1, 0)
                                        .displayName("§eChest-Opening")
                                        .lore("§7§oÖffne Kisten und gewinne coole Items!", "", "§8» §f§nLinksklick§8 | §7§oTeleportieren")
                                        .create(),
                                e -> {
                                    player.closeInventory();
                                    LobbyPlugin.getInstance().getGamePlayer(p).teleportAnimation("chest-opening");

                                });

                        setItem(InventorySlot.ROW_4_SLOT_6, new ItemBuilder(Material.GOLD_INGOT, 1, 0)
                                        .displayName("§aBank")
                                        .lore("§7§oEröffne ein Konto und hole Dir", "§7§ojeden Tag coole Belohnungen ab!", "", "§8» §f§nLinksklick§8 | §7§oTeleportieren")
                                        .create(),

                                e -> {
                                    player.closeInventory();
                                    LobbyPlugin.getInstance().getGamePlayer(p).teleportAnimation("bank");

                                });
                        setItem(InventorySlot.ROW_2_SLOT_9, new ItemBuilder(Material.REDSTONE, 1, 0)
                                        .displayName("§dBüro")
                                        .lore("§7§oKaufe dir ein tolles Büro", "§7§ooder Besuche dein Büro", "", "§8» §f§nLinksklick§8 | §7§oTeleportieren")
                                        .create(),

                                e -> {
                                    player.closeInventory();
                                    LobbyPlugin.getInstance().getGamePlayer(p).teleportAnimation("office-entrance");

                                });

                        setItem(InventorySlot.ROW_5_SLOT_3, new ItemBuilder(Material.DIAMOND_BOOTS, 1, 0)
                                        .displayName("§bJumpAndRun §8| §fLobbygame")
                                        .lore("§7§oBesuche die JumpAndRun Tafel", "§7§oUm alle Jump and Runs zu sehen", "§7§oUnd um sie zu starten", "", "§8» §f§nLinksklick§8 | §7§oTeleportieren")
                                        .create(),

                                e -> {
                                    player.closeInventory();
                                    LobbyPlugin.getInstance().getGamePlayer(p).teleportAnimation("jumpandrun-board");

                                });

                        setItem(InventorySlot.ROW_5_SLOT_7, new ItemBuilder(Material.IRON_SWORD, 1, 0)
                                        .displayName("§cOne-Hit §8| §fLobbygame")
                                        .lore("§7§oSpiele ein bekannten Modi", "§7§omit deinen Freunden auf der Lobby", "", "§8» §f§nLinksklick§8 | §7§oSpielen")
                                        .create(),

                                e -> {


                                    p.closeInventory();
                                    LobbyPlugin.getInstance().getOneHitManager().setStart(p);
                                });


                        setItem(InventorySlot.ROW_4_SLOT_9, new ItemBuilder(Material.BOOK, 1, 0).displayName("§3Die Story").lore("§7§oSpiele die Story und erhalte", "§7§ocoole Items und viele Coins!", "", "§7§oTeleportiere zum Story NPC", "§8» §f§nLinksklick§8 | §7§oTeleportieren").create(), e -> {
                            LobbyPlayer lp = LobbyPlugin.getInstance().getGamePlayer(p);

                            if (lp.getProgressId() == 0) {
                                LobbyPlugin.getInstance().getGamePlayer(p).teleportAnimation("storyspawn");
                                p.closeInventory();
                            } else {
                                LobbyPlugin.getInstance().getMessager().send(p, "§2Du wurdest in der nähe des letzten Story-NPC teleportiert, mit dem du zuletzt interagiert hast!");
                                LobbyPlugin.getInstance().getGamePlayer(p).teleportAnimation(Progress.getProgressByID(lp.getProgressId()).getNpc().getData().getLocation().bukkit());
                            }
                        });

                        openInventory();
                    }, 2L);
                }, 2L);
            }, 2L);
        }, 2L);
    }

}
