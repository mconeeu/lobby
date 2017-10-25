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
        if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§5CookieTrail")){
            Main.trail.setTrail(p, Trails.COOKIES);
        }else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§6GlowTrail")){
            Main.trail.setTrail(p, Trails.GLOW);
        }else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§5EnderTrail")){
            Main.trail.setTrail(p, Trails.ENDER);
        }else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aMusikTrail")){
            Main.trail.setTrail(p, Trails.MUSIC);
        }else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cLavaTrail")){
            Main.trail.setTrail(p, Trails.LAVA);
        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aHeartTrail")){
            Main.trail.setTrail(p, Trails.HEART);
        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§9WaterTrail")){
            Main.trail.setTrail(p, Trails.WATER);
        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§fSnowTrail")){
            Main.trail.setTrail(p, Trails.SNOW);
        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cTrail ablegen")){
            Main.trail.removeTrail(p);
        }
    }
}
