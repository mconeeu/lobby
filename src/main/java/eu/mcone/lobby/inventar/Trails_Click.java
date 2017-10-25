/*
 * Copyright (c) 2017 Dominik L., BamDev, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.inventar;

import eu.mcone.lobby.Main;
import eu.mcone.lobby.trail.Trails;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class Trails_Click {

    public Trails_Click(InventoryClickEvent e, Player p) {
        e.setCancelled(true);
        if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Trails.COOKIES.getName())){
            Main.trail.setTrail(p, Trails.COOKIES);
        }else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Trails.GLOW.getName())){
            Main.trail.setTrail(p, Trails.GLOW);
        }else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Trails.ENDER.getName())){
            Main.trail.setTrail(p, Trails.ENDER);
        }else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Trails.MUSIC.getName())){
            Main.trail.setTrail(p, Trails.MUSIC);
        }else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Trails.HEART.getName())){
            Main.trail.setTrail(p, Trails.HEART);
        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Trails.LAVA.getName())){
            Main.trail.setTrail(p, Trails.LAVA);
        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Trails.SNOW.getName())){
            Main.trail.setTrail(p, Trails.SNOW);
        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Trails.WATER.getName())){
            Main.trail.setTrail(p, Trails.WATER);
        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cTrail ablegen")){
            Main.trail.removeTrail(p);
        }
    }
}
