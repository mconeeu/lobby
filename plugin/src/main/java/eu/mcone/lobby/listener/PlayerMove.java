/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.listener;

import fr.neatmonster.nocheatplus.hooks.NCPExemptionManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMove implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void on(PlayerMoveEvent e) {
        Player p = e.getPlayer();

        if(p.getGameMode().equals(GameMode.ADVENTURE)) {
            if(PlayerToggleFlight.djPlayers.contains(p.getUniqueId()) && !p.getLocation().add(0,-1, 0).getBlock().getType().equals(Material.AIR)) {
                p.setAllowFlight(true);
                p.setFlying(false);

                if (Bukkit.getPluginManager().getPlugin("NoCheatPlus") != null)
                    NCPExemptionManager.unexempt(p.getUniqueId());
                PlayerToggleFlight.djPlayers.remove(p.getUniqueId());
            }
        }
    }

}
