/*
 * Copyright (c) 2017 Dominik L., Rufus Maiwald, BamDev and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.listener;

import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;

public class PlayerToggleFlight implements Listener{

    @EventHandler
    public void on(PlayerToggleFlightEvent e)
    {
        Player p = e.getPlayer();
        if (!e.isFlying()) {
            return;
        }
        if (p.getGameMode() == GameMode.CREATIVE) {
            return;
        }
        e.setCancelled(true);
        p.setAllowFlight(false);
        Vector vec = p.getLocation().getDirection().normalize();
        vec = vec.setY(Math.max(0.4000000059604645D, vec.getY())).multiply(1.5F);
        p.setVelocity(vec);
        p.setFlying(false);
        p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 2.0F, 2.0F);
        p.playEffect(p.getLocation(), Effect.BLAZE_SHOOT, 10);
    }

}
