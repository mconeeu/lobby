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

class TrailsInventory extends CoreInventory {

    TrailsInventory(Player p) {
        super("§8» §3Trails", p , 18, Option.FILL_EMPTY_SLOTS);

        setItem(0, new ItemBuilder(Material.BARRIER, 1, 0).displayName("§8» §c§lTrail ablegen").create(),
                () -> Lobby.getInstance().getTrailManager().removeTrail(p));

        Lobby.getInstance().getTrailManager().setInvItem(this, player, Trail.COOKIES, 2);
        Lobby.getInstance().getTrailManager().setInvItem(this, player, Trail.GLOW, 4);
        Lobby.getInstance().getTrailManager().setInvItem(this, player, Trail.ENDER, 6);
        Lobby.getInstance().getTrailManager().setInvItem(this, player, Trail.MUSIC, 8);
        Lobby.getInstance().getTrailManager().setInvItem(this, player, Trail.LAVA, 11);
        Lobby.getInstance().getTrailManager().setInvItem(this, player, Trail.HEART, 13);
        Lobby.getInstance().getTrailManager().setInvItem(this, player, Trail.WATER, 15);
        Lobby.getInstance().getTrailManager().setInvItem(this, player, Trail.SNOW, 17);

        setItem(9, new ItemBuilder(Material.IRON_DOOR, 1, 0).displayName("§7§l↩ Zurück").create(), () -> {
            new GadgetsInventory(p);
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
        });

        openInventory();
        player.playSound(player.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
    }

}
