/*
 * Copyright (c) 2017 Dominik L., Rufus Maiwald, BamDev and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.listener;

import eu.mcone.lobby.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener{

    @EventHandler
    public void on(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        e.setQuitMessage(null);

        Main.trail.removeTrail(p);
/*
        for (Player all : Bukkit.getOnlinePlayers()){
            PlayerHoloListener.HoloSkypvp(all);
        }
*/
    }

}
