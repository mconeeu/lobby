/*
 * Copyright (c) 2017 Dominik L., BamDev, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.inventar;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.util.Vector;

public class onFishingRod implements Listener {

    @EventHandler
    public void onPlayerFish(PlayerFishEvent e){
        Player p = e.getPlayer();
        FishHook hook = e.getHook();
        try{
            if (hook.getLocation().subtract(0.0D, 1.0D, 0.0D).getBlock().getType() != Material.AIR) {
                Location playerloc = p.getLocation();
                Location hookloc = hook.getLocation();

                Vector vec = p.getVelocity();
                double distance = playerloc.distance(hookloc);

                vec.setX(1.08D * distance * (hookloc.getX() - playerloc.getX()) / distance);
                vec.setY(1.0D * distance * (hookloc.getY() - playerloc.getY()) / distance);
                vec.setZ(1.08D * distance * (hookloc.getZ() - playerloc.getZ()) / distance);

                p.playSound(p.getLocation(), org.bukkit.Sound.ENDERDRAGON_WINGS, 2.0F, 2.0F);
                p.setVelocity(vec);
                p.getInventory().getItemInHand().setDurability((short)0);
            }
        } catch (Exception e1) {

        }
    }

}
