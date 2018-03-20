/*
 * Copyright (c) 2017 - 2018 Dominik L., Rufus Maiwald, BamDev and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.inventory;

import eu.mcone.coresystem.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.bukkit.util.ItemFactory;
import eu.mcone.lobby.Lobby;
import eu.mcone.lobby.trail.Trail;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import javax.swing.text.html.Option;

class TrailsInventory extends CoreInventory {

    TrailsInventory(Player p) {
        super("§8» §3Trails", p , 18, Option.FILL_EMPTY_SLOTS);

        setItem(0, ItemFactory.createItem(Material.BARRIER, 0, 1, "§8» §c§lTrail ablegen", true),
                () -> Lobby.getInstance().getTrailManager().removeTrail(p));

        Lobby.getInstance().getTrailManager().setInvItem(this, player, Trail.COOKIES, 2);
        Lobby.getInstance().getTrailManager().setInvItem(this, player, Trail.GLOW, 4);
        Lobby.getInstance().getTrailManager().setInvItem(this, player, Trail.ENDER, 6);
        Lobby.getInstance().getTrailManager().setInvItem(this, player, Trail.MUSIC, 8);
        Lobby.getInstance().getTrailManager().setInvItem(this, player, Trail.LAVA, 11);
        Lobby.getInstance().getTrailManager().setInvItem(this, player, Trail.HEART, 13);
        Lobby.getInstance().getTrailManager().setInvItem(this, player, Trail.WATER, 15);
        Lobby.getInstance().getTrailManager().setInvItem(this, player, Trail.SNOW, 17);

        setItem(9, ItemFactory.createItem(Material.IRON_DOOR, 0, 1, "§7§l↩ Zurück", true), () -> {
            new GadgetsInventory(p);
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
        });

        openInventory();
        player.playSound(player.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
    }

}
