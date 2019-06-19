/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.inventory;

import eu.mcone.coresystem.api.bukkit.gamemode.Gamemode;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.api.enums.Progress;
import eu.mcone.lobby.api.player.LobbyPlayer;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;

public class CompassInventory extends CoreInventory {

    public CompassInventory(Player p) {
        super("§8» §3§lNavigator", p, InventorySlot.ROW_5);

        setItem(InventorySlot.ROW_1_SLOT_1, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 11).displayName("§8//§oMCONE§8//").create());
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


        setItem(InventorySlot.ROW_1_SLOT_3, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 0).displayName("§8//§oMCONE§8//").create());
        setItem(InventorySlot.ROW_2_SLOT_2, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 0).displayName("§8//§oMCONE§8//").create());

        setItem(InventorySlot.ROW_5_SLOT_3, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 0).displayName("§8//§oMCONE§8//").create());
        setItem(InventorySlot.ROW_4_SLOT_2, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 0).displayName("§8//§oMCONE§8//").create());


        setItem(InventorySlot.ROW_1_SLOT_7, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 0).displayName("§8//§oMCONE§8//").create());
        setItem(InventorySlot.ROW_2_SLOT_8, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 0).displayName("§8//§oMCONE§8//").create());

        setItem(InventorySlot.ROW_4_SLOT_8, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 0).displayName("§8//§oMCONE§8//").create());
        setItem(InventorySlot.ROW_5_SLOT_7, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 0).displayName("§8//§oMCONE§8//").create());


        setItem(InventorySlot.ROW_2_SLOT_5, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 3).displayName("§8//§oMCONE§8//").create());
        setItem(InventorySlot.ROW_3_SLOT_4, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 3).displayName("§8//§oMCONE§8//").create());
        setItem(InventorySlot.ROW_4_SLOT_5, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 3).displayName("§8//§oMCONE§8//").create());
        setItem(InventorySlot.ROW_3_SLOT_6, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 3).displayName("§8//§oMCONE§8//").create());


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


        setItem(InventorySlot.ROW_3_SLOT_1, new ItemBuilder(Material.EMERALD, 1, 0)
                        .displayName("§eHändler")
                        .lore("§7§oKaufe dir coole Items oder", "§7seltene Truhen für", "das Chest-Opening")
                        .create(),
                e -> LobbyWorld.ONE_ISLAND.getWorld().teleport(p, "merchant")
        );

        setItem(InventorySlot.ROW_3_SLOT_1, new ItemBuilder(Material.DIAMOND_SWORD, 1, 0)
                        .displayName(Gamemode.SKYPVP.getLabel())
                        .lore("§7§oFinde deine Gegner auf einer Sky-Map und töte sie", "§7§oum Coins zu erhalten", "", "§8» §f§nLinksklick§8 | §7§oTeleportieren")
                        .create(),
                e -> LobbyWorld.ONE_ISLAND.getWorld().teleport(p, "skypvp")
        );

        setItem(InventorySlot.ROW_2_SLOT_6, new ItemBuilder(Material.IRON_PICKAXE, 1, 0)
                        .displayName("§bCitybuild")
                        .lore("§7§oCitybuild Server. Erbaue dein eigenes Grundstück", "§7§ound werde einer der Reichsten Spieler", "auf dem Server!", "", "§8» §f§nLinksklick§8 | §7§oTeleportieren")
                        .create(),
                e -> LobbyWorld.ONE_ISLAND.getWorld().teleport(p, "build")
        );

        setItem(InventorySlot.ROW_3_SLOT_3, new ItemBuilder(Material.IRON_AXE, 1, 0)
                        .displayName(Gamemode.BUILD.getLabel())
                        .lore("§7§oBuild Server. Überzeuge uns von deinen Baukünsten", "§7§ound werde Builder im MC ONE Team!", "", "§8» §f§nLinksklick§8 | §7§oTeleportieren")
                        .create(),
                e -> LobbyWorld.ONE_ISLAND.getWorld().teleport(p, "build")
        );


        setItem(InventorySlot.ROW_4_SLOT_4, new ItemBuilder(Material.CHEST, 1, 0)
                        .displayName("§eChest-Opening")
                        .lore("§7§oÖffne Kisten und gewinne coole Items", "", "§8» §f§nLinksklick§8 | §7§oTeleportieren")
                        .create(),
                e -> LobbyWorld.ONE_ISLAND.getWorld().teleport(p, "chest-opening")
        );

        setItem(InventorySlot.ROW_4_SLOT_6, new ItemBuilder(Material.GOLD_INGOT, 1, 0)
                        .displayName("§aBank")
                        .lore("§7§oEröffne ein Konto", "und hole dir jeden Tag", "coole Belohnungen ab", "", "§8» §f§nLinksklick§8 | §7§oTeleportieren")
                        .create(),
                e -> LobbyWorld.ONE_ISLAND.getWorld().teleport(p, "bank")
        );

        setItem(InventorySlot.ROW_3_SLOT_5, new ItemBuilder(Material.NETHER_STAR, 1, 0)
                        .displayName("&f&lSpawn")
                        .lore("§7§oZurück zum Lobby Spawn.", "§7§oHier startet unser Lobby Rätsel", "", "§8» §f§nLinksklick§8 | §7§oTeleportieren")
                        .create(),
                e -> LobbyWorld.ONE_ISLAND.getWorld().teleport(p, "spawn")
        );


        setItem(InventorySlot.ROW_3_SLOT_7, new ItemBuilder(Material.STICK, 1, 0)
                        .displayName(Gamemode.KNOCKIT.getLabel())
                        .enchantment(Enchantment.KNOCKBACK, 1)
                        .lore("§7§oSchlage die Gegner von der Plattform um Coins", "§7§ozu erhalten", "", "§8» §f§nLinksklick§8 | §7§oTeleportieren")
                        .itemFlags(ItemFlag.HIDE_ENCHANTS)
                        .create(),
                e -> LobbyWorld.ONE_ISLAND.getWorld().teleport(p, "knockit")
        );


        setItem(InventorySlot.ROW_3_SLOT_9, new ItemBuilder(Material.BOOK, 1, 0)
                        .displayName("§3Die Story").lore("§7§oSpiele die Story")
                        .lore("§7§ound kassiere coole Items und viele Coins!", "", "§7§oTeleportiere zum Story NPC", "§8» §f§nLinksklick§8 | §7§oTeleportieren")
                        .create(),
                e -> {
                    LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p.getUniqueId());

                    if (lp.getProgressId() == 0) {
                        LobbyWorld.ONE_ISLAND.getWorld().teleport(p, "storyspawn");
                    } else {
                        LobbyPlugin.getInstance().getMessager().send(p, "§2Du wurdest zu dem Story NPC teleportiert, mit dem du zuletzt interagiert hast!");
                        p.teleport(Progress.getProgressByID(lp.getProgressId()).getNpc().getData().getLocation().bukkit());
                    }
                });

        openInventory();
    }

}
