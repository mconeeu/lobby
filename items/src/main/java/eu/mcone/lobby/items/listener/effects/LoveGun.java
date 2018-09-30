/*
 * Copyright (c) 2017 - 2018 Dominik Lippl, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.items.listener.effects;

import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.enums.Item;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class LoveGun implements Listener {

    @EventHandler
    public void on(PlayerInteractEvent e) {
        if (e.hasItem() && e.getItem().equals(Item.LOVEGUN.getItemStack()) && (e.getAction().equals(Action.LEFT_CLICK_BLOCK) || e.getAction().equals(Action.LEFT_CLICK_BLOCK))) {
            Player p = e.getPlayer();

            World w = p.getWorld();

            Bukkit.getScheduler().runTaskAsynchronously(LobbyPlugin.getInstance(), () -> {
                w.playEffect(p.getLocation(), Effect.HEART, 100);
                w.playEffect(p.getLocation(), Effect.LAVA_POP, 100);
                w.playEffect(p.getLocation(), Effect.FIREWORKS_SPARK, 100);
            });
        }
    }

}
