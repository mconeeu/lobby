/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.items.listener.effects;

import eu.mcone.gamesystem.api.enums.Item;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class LoveGunListener implements Listener {

    @EventHandler
    public void on(PlayerInteractEvent e) {
        if (e.hasItem() && e.getItem().equals(Item.LOVEGUN.getItemStack()) && (e.getAction().equals(Action.LEFT_CLICK_BLOCK) || e.getAction().equals(Action.LEFT_CLICK_AIR))) {
            Player p = e.getPlayer();

            for (Player all : Bukkit.getOnlinePlayers()) {
                all.spigot().playEffect(p.getLocation(), Effect.FLAME, 1, 1, 1, 1, 1, 2, 29, 25);
                all.spigot().playEffect(p.getLocation(), Effect.HEART, 1, 1, 1, 1, 1, 3, 78, 42);
            }
            World w = p.getWorld();


            w.playEffect(p.getLocation(), Effect.FLAME,1);
            w.playEffect(p.getLocation(),Effect.HEART,10);



        }
    }

}
