/*
 * Copyright (c) 2017 Dominik L., Rufus Maiwald, BamDev and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.inventory;

import eu.mcone.bukkitcoresystem.api.StateAPI;
import eu.mcone.bukkitcoresystem.channel.PluginMessage;
import eu.mcone.bukkitcoresystem.util.ItemManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.Arrays;

public class ServerInventory {

    public enum Spielmodus {
        BEDWARS("BW", "§c§lBedwars", "§8» §c§lBedwars", Material.BED),
        SKYPVP("SP", "§9§lSkyPvP", "§8» §9§lSkyPvP", Material.DIAMOND_SWORD),
        KNOCKIT("KI", "§a§lKnockIt", "§8» §a§lKnockIt", Material.STICK),
        BUILD("BU", "§e§lBuild", "§8» §e§lBuild", Material.GRASS),
        MINEWAR("MW", "§5§lMinewar", "§8» §5§lMinewar", Material.IRON_PICKAXE);

        private String id, name, npcName;
        private Material item;

        Spielmodus(String id, String name, String npcName, Material item) {
            this.id = id;
            this.name = name;
            this.npcName = npcName;
            this.item = item;
        }

        public static Spielmodus getSpielmodusByNpcName(String npcName) {
            for (Spielmodus sm : values()) {
                if (sm.getNpcName().equals(npcName)) {
                    return sm;
                }
            }

            return null;
        }

        public String getNpcName() {
            return npcName;
        }

        public String getName() {
            return name;
        }

        public String getId() {
            return id;
        }

        public Material getItem() {
            return item;
        }

    }

    public ServerInventory(Player p, Spielmodus modus) {
        new PluginMessage(p, "SERVERS", "list", modus.toString());
    }

    public static void create(Player p, String modus, String servers) {
        Spielmodus spielmodus = Spielmodus.valueOf(modus);
        Inventory inv = org.bukkit.Bukkit.createInventory(null, 54, spielmodus.getNpcName()+" §8| §8Server");

        for (int i = 0; i <= 17; i++) {
            inv.setItem(i, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 1, "§8//§oMCONE§8//", true));
        }
        for (int i = 45; i <= 53; i++) {
            inv.setItem(i, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 1, "§8//§oMCONE§8//", true));
        }
        inv.setItem(4, ItemManager.createItem(spielmodus.getItem(), 0, 1, spielmodus.getName(), true));

        int i = 17;
        for (String server : servers.split(";")) {
            i++;
            if (i > 45) break;

            String[] data = server.split(":");
            if (data.length < 2) continue;

            inv.setItem(i, ItemManager.createItem(Material.EMERALD, 0, 1, "§f§l"+data[0], new ArrayList<>(Arrays.asList("§7"+data[1]+" Spieler online", "", "§8» §c§nRechtsklick§8 | §7§oJoinen")), true));
        }

        p.openInventory(inv);
    }

    public static void click(InventoryClickEvent e, Player p) {
        if (e.getCurrentItem().getData().getItemType().equals(Material.EMERALD)) {
            new PluginMessage(p, "CONNECT", e.getCurrentItem().getItemMeta().getDisplayName().replace("§f§l", ""));
            p.closeInventory();
        }
    }

}
