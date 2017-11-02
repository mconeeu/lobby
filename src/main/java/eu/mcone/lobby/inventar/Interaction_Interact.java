/*
 * Copyright (c) 2017 Dominik L., Rufus Maiwald, BamDev and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.inventar;

import de.Dominik.BukkitCoreSystem.api.CoinsAPI;
import de.Dominik.BukkitCoreSystem.api.OnlineTimeAPI;
import eu.mcone.lobby.util.ScoreboardManager;
import de.Dominik.BukkitCoreSystem.util.ItemManager;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.Arrays;

public class Interaction_Interact {

    public Interaction_Interact(Player p, Player clicked) {

        int coins = CoinsAPI.getCoins(clicked);
        double onlinetime = OnlineTimeAPI.getTime(clicked);

        onlinetime = Math.floor((onlinetime / 60) * 100) / 100;

        Inventory inv = org.bukkit.Bukkit.createInventory(null, 27, "§8» §3Interaktionsmenü");

        for (int i = 0; i <= 26; i++) {
            inv.setItem(i, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 1, "§8//§oMCONE§8//", true));
        }
        inv.setItem(4, ItemManager.createSkullItem("§f§l" + clicked.getName(), clicked.getName(), 1, new ArrayList<>(Arrays.asList(ScoreboardManager.getObjectiveRang(clicked), "","§7Coins: §f" + coins , "§7Onlinetime: §f" + onlinetime + " Stunden"))));

        p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
        p.openInventory(inv);
    }
}
