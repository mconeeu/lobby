/*
 * Copyright (c) 2017 Dominik L., Rufus Maiwald, BamDev and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.inventar;

import eu.mcone.lobby.Main;
import eu.mcone.lobby.trail.Trail;
import de.Dominik.BukkitCoreSystem.util.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class Profil_Click {

    public Profil_Click(InventoryClickEvent e, Player p) {
        if ((e.getCurrentItem() == null) || !e.getCurrentItem().hasItemMeta() || e.getSlotType() == InventoryType.SlotType.OUTSIDE) {
            e.setCancelled(true);
        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §5Hüte")) {
            Inventory inv = Bukkit.createInventory(null, 9, "§8» §5Hüte");
            inv.setItem(0, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 1, "§8//§oMCONE§8//", true));
            inv.setItem(1, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 1, "§8//§oMCONE§8//", true));
            inv.setItem(2, ItemManager.createItem(Material.CHAINMAIL_HELMET, 0, 1, "§8» §8Ketten-Helm", true));
            inv.setItem(3, ItemManager.createItem(Material.DIAMOND_HELMET, 0, 1, "§8» §bDiamant-Helm", true));
            inv.setItem(4, ItemManager.createItem(Material.IRON_HELMET, 0, 1, "§8» §7Eisen-Helm", true));
            inv.setItem(5, ItemManager.createItem(Material.GOLD_HELMET, 0, 1, "§8» §eGold-Helm", true));
            inv.setItem(6, ItemManager.createItem(Material.BARRIER, 0, 1, "§8» §4Hat entfernen", true));
            inv.setItem(7, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 1, "§8//§oMCONE§8//", true));
            inv.setItem(8, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 1, "§8//§oMCONE§8//", true));

            p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);
            p.openInventory(inv);
        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §3Trails")) {
            Inventory inv = Bukkit.createInventory(null, 18, "§8» §3Trails");

            for (int i = 0; i <= 17; i++) {
                inv.setItem(i, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 1, "§8//§oMCONE§8//", true));
            }
            
            inv.setItem(0, ItemManager.createItem(Material.BARRIER, 0, 1, "§cTrail ablegen", true));
            Main.trail.setInvItem(inv, p, Trail.COOKIES, 2);
            Main.trail.setInvItem(inv, p, Trail.GLOW, 4);
            Main.trail.setInvItem(inv, p, Trail.ENDER, 6);
            Main.trail.setInvItem(inv, p, Trail.MUSIC, 8);
            Main.trail.setInvItem(inv, p, Trail.LAVA, 10);
            Main.trail.setInvItem(inv, p, Trail.HEART, 12);
            Main.trail.setInvItem(inv, p, Trail.WATER, 14);
            Main.trail.setInvItem(inv, p, Trail.SNOW, 16);

            p.openInventory(inv);
            p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §bGadgets")) {
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
            p.closeInventory();
            p.sendMessage(Main.config.getConfigValue("System-Prefix") + "§bGib uns Ideen für Gadgets! §7- §bTeamSpeak3-Server §7: §fMcOne.eu");
        }
    }

}
