/*
 * Copyright (c) 2017 - 2018 Dominik Lippl, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.items.listener.effects;

import eu.mcone.lobby.api.enums.Item;
import org.bukkit.Effect;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class Bomb implements Listener {

    @EventHandler
    public void on(PlayerInteractEvent e) {
        if (e.hasItem() && e.getItem().equals(Item.BOMB.getItemStack()) && (e.getAction().equals(Action.LEFT_CLICK_BLOCK) || e.getAction().equals(Action.LEFT_CLICK_BLOCK))) {
            Player p = e.getPlayer();

            p.getWorld().playEffect(p.getLocation(), Effect.EXPLOSION_HUGE, 10);
            p.getWorld().playEffect(p.getLocation(), Effect.EXPLOSION_LARGE, 10);
        }
    }

}