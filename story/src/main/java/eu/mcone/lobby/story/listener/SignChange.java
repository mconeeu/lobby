/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.story.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class SignChange implements Listener {

    @EventHandler
    public void on(SignChangeEvent e) {
        if (e.getLine(0).equals("[secrets]") && e.getLine(1) != null) {
            String name = e.getLine(1);

            e.setLine(0, "§7»§c Secrets");
            e.setLine(1, "§7»§7§l "+name+"§7 «");
            e.setLine(3,"§2§lEntdecken!");
        }
    }

}
