/*
 * Copyright (c) 2017 Dominik L., Rufus Maiwald, BamDev and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.events;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class PlayerMove_Event implements Listener{

    @EventHandler
    public void onDoubleJumpMove(PlayerMoveEvent e)
    {
        Player p = e.getPlayer();
        if (!p.isOnGround()) {
            return;
        }
        p.setAllowFlight(true);
    }

    @EventHandler
    public void onJumpPadMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();

        if ((p.getLocation().getBlock().getType() == Material.GOLD_PLATE) && (p.getLocation().subtract(0.0D, 1.0D, 0.0D).getBlock().getType() == Material.COAL_BLOCK)) {
            Vector vec = p.getLocation().getDirection().multiply(2.0D).setY(1.0D);
            p.setVelocity(vec);
            p.playEffect(p.getLocation(), org.bukkit.Effect.BLAZE_SHOOT, 10);
            p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 2.0F, 2.0F);
        }
    }

}
