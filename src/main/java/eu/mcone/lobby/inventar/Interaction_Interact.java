/*
 * Copyright (c) 2017 Dominik L., Rufus Maiwald, BamDev and the MC ONE Minecraftnetwork. All rights reserved
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

        for (int i = 0; i <= 26; i++) {
            inv.setItem(i, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 0, "§r"));
        }
        inv.setItem(4, ItemManager.createSkullItem("§f§l" + e.getRightClicked().getName(), e.getRightClicked().getName(), new String[]{ScoreboardManager.getObjectiveRang((Player) e.getRightClicked()), "","§7Coins: §f" + coins , "§7Onlinetime: §f" + onlinetime + " Stunden"}));

        p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
        p.openInventory(inv);
    }
}
