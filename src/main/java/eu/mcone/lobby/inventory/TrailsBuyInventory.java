/*
 * Copyright (c) 2017 - 2018 Dominik L., Rufus Maiwald, BamDev and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.inventory;

import eu.mcone.coresystem.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.bukkit.util.ItemBuilder;
import eu.mcone.lobby.Lobby;
import eu.mcone.lobby.trail.Trail;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class TrailsBuyInventory extends CoreInventory {

    public TrailsBuyInventory(Player p, Trail trail) {
        super("§8» §6Trail kaufen", p, 27, Option.FILL_EMPTY_SLOTS);

        setItem(4, new ItemBuilder(trail.getItem(), 1, 0).displayName(trail.getName()).lore("", "§7§oKostet: §f§o" + trail.getCoins() + " Coins").create());
        setItem(21, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 5).displayName("§a§lTrail kaufen").lore("", "§8» §a§nRechtsklick§8 | §7§oKaufen").create(),
                () -> Lobby.getInstance().getTrailManager().buyTrail(p, trail));
        setItem(23, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 14).displayName("§c§lAbbrechen").lore("", "§8» §c§nRechtsklick§8 | §7§oAbbrechen").create(),
                p::closeInventory);

        openInventory();
        player.playSound(player.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
    }

}
