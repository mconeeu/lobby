/*
 * Copyright (c) 2017 Dominik L., BamDev, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.inventar;

import de.Dominik.BukkitCoreSystem.API.CoinsAPI;
import de.Dominik.BukkitCoreSystem.API.OnlineTimeAPI;
import de.Dominik.BukkitCoreSystem.Main.Main;
import eu.mcone.lobby.scoreboard.ScoreboardManager;
import eu.mcone.lobby.utils.ItemManager;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Interaction_Interact {

    public Interaction_Interact(PlayerInteractEntityEvent e, Player p) {

        int coins = CoinsAPI.getCoins(p);
        double onlinetime = OnlineTimeAPI.getTime(p);

        onlinetime = Math.floor((onlinetime / 60) * 100) / 100;

        Inventory inv = org.bukkit.Bukkit.createInventory(null, 27, "§8» §3Interaktionsmenü");

        inv.setItem(0, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, ""));
        inv.setItem(1, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, ""));
        inv.setItem(2, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, ""));
        inv.setItem(3, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, ""));
        inv.setItem(4, ItemManager.createSkullItem("§f§l" + e.getRightClicked().getName(), e.getRightClicked().getName(), new String[]{ScoreboardManager.getObjectiveRang((Player) e.getRightClicked()), "","§7Coins: §f" + coins , "§7Onlinetime: §f" + onlinetime + " Stunden"}));
        inv.setItem(5, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, ""));
        inv.setItem(6, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, ""));
        inv.setItem(7, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, ""));
        inv.setItem(8, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, ""));
        inv.setItem(9, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, ""));
        inv.setItem(10, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, ""));
        inv.setItem(11, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, ""));
        inv.setItem(12, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, ""));
        inv.setItem(13, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, ""));
        inv.setItem(14, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, ""));
        inv.setItem(14, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, ""));
        inv.setItem(15, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, ""));
        inv.setItem(16, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, ""));
        inv.setItem(17, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, ""));
        inv.setItem(18, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, ""));
        inv.setItem(19, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, ""));
        inv.setItem(20, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, ""));
        inv.setItem(21, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, ""));
        inv.setItem(22, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, ""));
        inv.setItem(23, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, ""));
        inv.setItem(24, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, ""));
        inv.setItem(25, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, ""));
        inv.setItem(26, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, ""));

        p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
        p.openInventory(inv);
    }
}
