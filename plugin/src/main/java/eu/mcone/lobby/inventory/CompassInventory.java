/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.inventory;

import eu.mcone.coresystem.api.bukkit.gamemode.Gamemode;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.lobby.api.LobbyWorld;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;

public class CompassInventory extends CoreInventory {

    public CompassInventory(Player p) {
        super("§8» §3§lNavigator", p, 54);

        setItem(0, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 3).displayName("§8//§oMCONE§8//").create());
        setItem(1, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 11).displayName("§8//§oMCONE§8//").create());
        setItem(2, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
        setItem(3, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
        setItem(4, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 0).displayName("§8//§oMCONE§8//").create());
        setItem(5, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
        setItem(6, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
        setItem(7, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 11).displayName("§8//§oMCONE§8//").create());
        setItem(8, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 3).displayName("§8//§oMCONE§8//").create());
        setItem(9, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 11).displayName("§8//§oMCONE§8//").create());
        setItem(10, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 11).displayName("§8//§oMCONE§8//").create());
        setItem(11, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 0).displayName("§8//§oMCONE§8//").create());
        setItem(12, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 0).displayName("§8//§oMCONE§8//").create());

        setItem(13, new ItemBuilder(Material.BED, 1, 0)
                        .displayName(Gamemode.BEDWARS.getLabel())
                        .lore("§7§oTöte deine Gegner nachdem du ihre Betten abgebaut hast", "§7§oum zu gewinnen", "", "§8» §f§nLinksklick§8 | §7§oTeleportieren")
                        .create(),
                e -> LobbyWorld.ONE_ISLAND.getWorld().teleport(p, "bedwars")
        );

        setItem(14, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 0).displayName("§8//§oMCONE§8//").create());
        setItem(15, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 0).displayName("§8//§oMCONE§8//").create());
        setItem(16, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 11).displayName("§8//§oMCONE§8//").create());
        setItem(17, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 11).displayName("§8//§oMCONE§8//").create());
        setItem(18, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
        setItem(19, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 3).displayName("§8//§oMCONE§8//").create());
        setItem(20, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 0).displayName("§8//§oMCONE§8//").create());

        setItem(21, new ItemBuilder(Material.DIAMOND_SWORD, 1, 0)
                        .displayName(Gamemode.SKYPVP.getLabel())
                        .lore("§7§oFinde deine Gegner auf einer Sky-Map und töte sie", "§7§oum Coins zu erhalten", "", "§8» §f§nLinksklick§8 | §7§oTeleportieren")
                        .itemFlags(ItemFlag.HIDE_ATTRIBUTES)
                        .create(),
                e -> LobbyWorld.ONE_ISLAND.getWorld().teleport(p, "skypvp")
        );

        setItem(22, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());

        setItem(23, new ItemBuilder(Material.STICK, 1, 0)
                        .enchantment(Enchantment.KNOCKBACK, 1)
                        .displayName(Gamemode.KNOCKIT.getLabel())
                        .lore("§7§oSchlage die Gegner von der Plattform um Coins", "§7§ozu erhalten", "", "§8» §f§nLinksklick§8 | §7§oTeleportieren")
                        .itemFlags(ItemFlag.HIDE_ENCHANTS)
                        .create(),
                e -> LobbyWorld.ONE_ISLAND.getWorld().teleport(p, "knockit")
        );

        setItem(24, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 0).displayName("§8//§oMCONE§8//").create());
        setItem(25, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 3).displayName("§8//§oMCONE§8//").create());
        setItem(26, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
        setItem(27, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
        setItem(28, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 3).displayName("§8//§oMCONE§8//").create());
        setItem(29, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 0).displayName("§8//§oMCONE§8//").create());
        setItem(30, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());

        setItem(31, new ItemBuilder(Material.IRON_PICKAXE, 1, 0)
                        .displayName(Gamemode.MINEWAR.getLabel())
                        .lore("§7§oGrabe dich unter der Erde zu deinen Gegner und rüste", "§7§odich aus um sie zu töten und zu gewinnen.", "", "§c§oBald wieder verfügbar")
                        .itemFlags(ItemFlag.HIDE_ATTRIBUTES)
                        .create(),
                e -> LobbyWorld.ONE_ISLAND.getWorld().teleport(p, "minewar")
        );

        setItem(32, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
        setItem(33, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 0).displayName("§8//§oMCONE§8//").create());
        setItem(34, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 3).displayName("§8//§oMCONE§8//").create());
        setItem(35, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());

        setItem(36, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 11).displayName("§8//§oMCONE§8//").create());
        setItem(37, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 11).displayName("§8//§oMCONE§8//").create());

        setItem(38, new ItemBuilder(Material.BARRIER, 1, 0)
                .displayName("§7Comming Soon")
                .lore("§8§oAn diesem Spielmodus arbeiten wir noch..")
                .create()
        );

        setItem(39, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 0).displayName("§8//§oMCONE§8//").create());

        setItem(40, new ItemBuilder(Material.NETHER_STAR, 1, 0)
                        .displayName("§f§lSpawn")
                        .lore("§7§oZurück zum Lobby Spawn.", "§7§oHier startet unser Lobby Rätsel", "", "§8» §f§nLinksklick§8 | §7§oTeleportieren")
                        .create(),
                e -> LobbyWorld.ONE_ISLAND.getWorld().teleport(p, "spawn")
        );

        setItem(41, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 0).displayName("§8//§oMCONE§8//").create());

        setItem(42, new ItemBuilder(Material.GRASS, 1, 0)
                        .displayName(Gamemode.BUILD.getLabel())
                        .lore("§7§oCreative Server. Überzeuge uns von deinen Baukünsten", "§7§ound werde Builder im MC ONE Team!", "", "§8» §f§nLinksklick§8 | §7§oTeleportieren")
                        .create(),
                e -> LobbyWorld.ONE_ISLAND.getWorld().teleport(p, "build")
        );

        setItem(43, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 11).displayName("§8//§oMCONE§8//").create());
        setItem(44, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 11).displayName("§8//§oMCONE§8//").create());


        setItem(45, new ItemBuilder(Material.CHEST, 1, 0)
                .displayName("§eChest-Opening")
                .lore("§7§oÖffne Kisten und gewinne coole Items", "", "§8» §f§nLinksklick§8 | §7§oTeleportieren")
                .create(),
            e -> LobbyWorld.ONE_ISLAND.getWorld().teleport(p, "chest-opening")

                );


        setItem(46, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 11).displayName("§8//§oMCONE§8//").create());
        setItem(47, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
        setItem(48, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
        setItem(49, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 0).displayName("§8//§oMCONE§8//").create());
        setItem(50, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
        setItem(51, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
        setItem(52, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 11).displayName("§8//§oMCONE§8//").create());


        setItem(53, new ItemBuilder(Material.BOOK, 1, 0)
                .displayName("§3Die Story").lore("§7§oSpiele die Story")
                .lore("§7§ound kassiere coole Items und viele Coins!", "", "§8» §f§nLinksklick§8 | §7§oTeleportieren")
                .create(),
        e -> LobbyWorld.ONE_ISLAND.getWorld().teleport(p, "storyspawn")

                );

        openInventory();
    }

}
