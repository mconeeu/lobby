/*
 * Copyright (c) 2017 - 2018 Dominik L., Rufus Maiwald, BamDev and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.inventory;

import eu.mcone.coresystem.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.bukkit.util.ItemFactory;
import eu.mcone.lobby.Lobby;
import eu.mcone.lobby.trail.Trail;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;

public class TrailsBuyInventory extends CoreInventory {

    public TrailsBuyInventory(Player p, Trail trail) {
        super("§8» §6Trail kaufen", p, 27, Option.FILL_EMPTY_SLOTS);

        setItem(4, ItemFactory.createItem(trail.getItem(), 0, 1, trail.getName(), new ArrayList<>(Arrays.asList("", "§7§oKostet: §f§o" + trail.getCoins() + " Coins")), true));
        setItem(21, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 5, 1, "§a§lTrail kaufen", new ArrayList<>(Arrays.asList("", "§8» §a§nRechtsklick§8 | §7§oKaufen")), true),
                () -> Lobby.getInstance().getTrailManager().buyTrail(p, trail));
        setItem(23, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 14, 1, "§c§lAbbrechen", new ArrayList<>(Arrays.asList("", "§8» §c§nRechtsklick§8 | §7§oAbbrechen")), true),
                p::closeInventory);

        openInventory();
        player.playSound(player.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
    }

}
