/*
 * Copyright (c) 2017 - 2018 Dominik L., Rufus Maiwald, BamDev and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.inventory;

import eu.mcone.coresystem.bukkit.util.ItemFactory;
import eu.mcone.coresystem.bukkit.util.LocationFactory;
import eu.mcone.lobby.Lobby;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class KompassInventory {

    enum Gamemode {
        SPAWN("spawn", "§f§lSpawn", new ArrayList<>(Arrays.asList("§7§oZurück zum Lobby Spawn.", "§7§oHier startet unser Lobby Rätsel", "", "§8» §f§nRechtsklick§8 | §7§oTeleportieren"))),
        BEDWARS("bedwars", "§c§lBedwars", new ArrayList<>(Arrays.asList("§7§oTöte deine Gegner nachdem du ihre Betten abgebaut hast", "§7§oum zu gewinnen", "", "§8» §f§nRechtsklick§8 | §7§oTeleportieren"))),
        SKYPVP("skypvp", "§9§lSkyPvP", new ArrayList<>(Arrays.asList("§7§oFinde deine Gegner auf einer Sky-Map und töte sie", "§7§oum Coins zu erhalten", "", "§8» §f§nRechtsklick§8 | §7§oTeleportieren"))),
        KNOCKIT("knockit", "§2§lKnockIT", new ArrayList<>(Arrays.asList("§7§oSchlage die Gegner von der Plattform um Coins", "§7§ozu erhalten","","§8» §f§nRechtsklick§8 | §7§oTeleportieren"))),
        MINEWAR("minewar", "§5§lMinewar", new ArrayList<>(Arrays.asList("§7§oGrabe dich unter der Erde zu deinen Gegner und rüste", "§7§odich aus um sie zu töten und zu gewinnen.", "", "§c§oBald wieder verfügbar"))),
        BUILD("build", "§e§lBuild", new ArrayList<>(Arrays.asList("§7§oCreative Server. Überzeuge uns von deinen Baukünsten", "§7§ound werde Builder im MC ONE Team!", "", "§8» §f§nRechtsklick§8 | §7§oTeleportieren")));

        String name;
        String label;
        List<String> lore;

        Gamemode(String name, String label, List<String> lore) {
            this.name = name;
            this.label = label;
            this.lore = lore;
        }

        public String getName() {
            return name;
        }
        public String getLabel() {
            return label;
        }
        public List<String> getLore() {
            return lore;
        }
    }

    public KompassInventory(Player p) {
        Inventory inv = org.bukkit.Bukkit.createInventory(null, 54, "§8» §3Navigator");

        inv.setItem(0, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 3, 1, "§8//§oMCONE§8//", true));
        inv.setItem(1, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 11, 1, "§8//§oMCONE§8//", true));
        inv.setItem(2, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 7, 1, "§8//§oMCONE§8//", true));
        inv.setItem(3, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 7, 1, "§8//§oMCONE§8//", true));
        inv.setItem(4, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 0, 1, "§8//§oMCONE§8//", true));
        inv.setItem(5, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 7, 1, "§8//§oMCONE§8//", true));
        inv.setItem(6, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 7, 1, "§8//§oMCONE§8//", true));
        inv.setItem(7, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 11, 1, "§8//§oMCONE§8//", true));
        inv.setItem(8, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 3, 1, "§8//§oMCONE§8//", true));
        inv.setItem(9, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 11, 1, "§8//§oMCONE§8//", true));
        inv.setItem(10, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 11, 1, "§8//§oMCONE§8//", true));
        inv.setItem(11, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 0, 1, "§8//§oMCONE§8//", true));
        inv.setItem(12, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 0, 1, "§8//§oMCONE§8//", true));

        inv.setItem(13, ItemFactory.createItem(Material.BED, 0, 1, Gamemode.BEDWARS.getLabel(), Gamemode.BEDWARS.getLore(), true));

        inv.setItem(14, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 0, 1, "§8//§oMCONE§8//", true));
        inv.setItem(15, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 0, 1, "§8//§oMCONE§8//", true));
        inv.setItem(16, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 11, 1, "§8//§oMCONE§8//", true));
        inv.setItem(17, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 11, 1, "§8//§oMCONE§8//", true));
        inv.setItem(18, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 7, 1, "§8//§oMCONE§8//", true));
        inv.setItem(19, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 3, 1, "§8//§oMCONE§8//", true));
        inv.setItem(20, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 0, 1, "§8//§oMCONE§8//", true));

        inv.setItem(21, ItemFactory.createItem(Material.DIAMOND_SWORD, 0, 1, Gamemode.SKYPVP.getLabel(), Gamemode.SKYPVP.getLore(), true));

        inv.setItem(22, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 7, 1, "§8//§oMCONE§8//", true));

        inv.setItem(23, ItemFactory.createEnchantedItem(Material.STICK, Enchantment.KNOCKBACK, 1, 0, 1, Gamemode.KNOCKIT.getLabel(), Gamemode.KNOCKIT.getLore(), true));

        inv.setItem(24, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 0, 1, "§8//§oMCONE§8//", true));
        inv.setItem(25, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 3, 1, "§8//§oMCONE§8//", true));
        inv.setItem(26, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 7, 1, "§8//§oMCONE§8//", true));
        inv.setItem(27, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 7, 1, "§8//§oMCONE§8//", true));
        inv.setItem(28, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 3, 1, "§8//§oMCONE§8//", true));
        inv.setItem(29, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 0, 1, "§8//§oMCONE§8//", true));
        inv.setItem(30, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 7, 1, "§8//§oMCONE§8//", true));

        inv.setItem(31, ItemFactory.createItem(Material.IRON_PICKAXE, 0, 1, Gamemode.MINEWAR.getLabel(), Gamemode.MINEWAR.getLore(), true));

        inv.setItem(32, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 7, 1, "§8//§oMCONE§8//", true));
        inv.setItem(33, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 0, 1, "§8//§oMCONE§8//", true));
        inv.setItem(34, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 3, 1, "§8//§oMCONE§8//", true));
        inv.setItem(35, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 7, 1, "§8//§oMCONE§8//", true));

        inv.setItem(36, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 11, 1, "§8//§oMCONE§8//", true));
        inv.setItem(37, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 11, 1, "§8//§oMCONE§8//", true));

        inv.setItem(38, ItemFactory.createItem(Material.BARRIER, 0, 1, "§7Comming Soon", new ArrayList<>(Collections.singletonList("§8§oAn diesem Spielmodus arbeiten wir noch...")), true));

        inv.setItem(39, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 0, 1, "§8//§oMCONE§8//", true));

        inv.setItem(40, ItemFactory.createItem(Material.NETHER_STAR, 0, 1, Gamemode.SPAWN.getLabel(), Gamemode.SPAWN.getLore(), true));

        inv.setItem(41, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 0, 1, "§8//§oMCONE§8//", true));

        inv.setItem(42, ItemFactory.createItem(Material.GRASS, 0, 1, Gamemode.BUILD.getLabel(), Gamemode.BUILD.getLore(), true));

        inv.setItem(43, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 11, 1, "§8//§oMCONE§8//", true));
        inv.setItem(44, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 11, 1, "§8//§oMCONE§8//", true));
        inv.setItem(45, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 3, 1, "§8//§oMCONE§8//", true));
        inv.setItem(46, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 11, 1, "§8//§oMCONE§8//", true));
        inv.setItem(47, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 7, 1, "§8//§oMCONE§8//", true));
        inv.setItem(48, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 7, 1, "§8//§oMCONE§8//", true));
        inv.setItem(49, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 0, 1, "§8//§oMCONE§8//", true));
        inv.setItem(50, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 7, 1, "§8//§oMCONE§8//", true));
        inv.setItem(51, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 7, 1, "§8//§oMCONE§8//", true));
        inv.setItem(52, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 11, 1, "§8//§oMCONE§8//", true));
        inv.setItem(53, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 3, 1, "§8//§oMCONE§8//", true));
        p.openInventory(inv);
    }

    public static void click(InventoryClickEvent e, Player p) {
        if ((e.getCurrentItem() == null) || !e.getCurrentItem().hasItemMeta() || e.getSlotType() == InventoryType.SlotType.OUTSIDE) {
            e.setCancelled(true);
        } else {
            for (Gamemode gm : Gamemode.values()) {
                if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(gm.getLabel())) {
                    Location loc = LocationFactory.getConfigLocation(Lobby.config, "Location-"+gm.getName());

                    if (loc != null) {
                        p.teleport(loc);
                        p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
                        p.playEffect(p.getLocation(), Effect.BLAZE_SHOOT, 1);
                    } else {
                        p.closeInventory();
                        p.sendMessage(Lobby.config.getConfigValue("System-Prefix") + "§4Dieser Spawn existiert nicht!");
                    }
                }
            }
        }

        e.setCancelled(true);
    }

}
