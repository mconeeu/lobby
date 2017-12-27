/*
 * Copyright (c) 2017 Dominik L., Rufus Maiwald, BamDev and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.inventory;

import eu.mcone.bukkitcoresystem.util.ItemManager;
import eu.mcone.lobby.Main;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class ServerInventory {

    public enum Spielmodus {
        BEDWARS("BW", "§c§lBedwars", "§8» §c§lBedwars"),
        SKYPVP("SP", "§9§lSkyPvP", "§8» §9§lSkyPvP"),
        KNOCKIT("KI", "§a§lKnockIt", "§8» §a§lKnockIt"),
        BUILD("BU", "§e§lBuild", "§8» §e§lBuild"),
        MINEWAR("MW", "§5§lMinewar", "§8» §5§lMinewar");

        private String id, name, npcName;

        Spielmodus(String id, String name, String npcName) {
            this.id = id;
            this.name = name;
            this.npcName = npcName;
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

    }

    public ServerInventory(Player p, Spielmodus modus) {
        Inventory inv = org.bukkit.Bukkit.createInventory(null, 36, "§8» "+modus.getName()+" §8| §7Server");

        for (int i = 0; i <= 35; i++) {
            inv.setItem(i, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 1, "§8//§oMCONE§8//", true));
        }

        p.openInventory(inv);
    }

    public static void click(InventoryClickEvent e, Player p) {
        if (!e.getCurrentItem().getItemMeta().getDisplayName().equals("§8//§oMCONE§8//")) {
            p.sendMessage("working!");
            p.closeInventory();
        }
    }
}
