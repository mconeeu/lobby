/*
 * Copyright (c) 2017 Dominik L., Rufus Maiwald, BamDev and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.channel;

import eu.mcone.lobby.inventory.ServerInventory;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.UUID;

public class PluginChannelListener implements PluginMessageListener {

    @Override
    public void onPluginMessageReceived(String s, Player p, byte[] bytes) {
        DataInputStream in = new DataInputStream(new ByteArrayInputStream(bytes));
        try {
            String subchannel = in.readUTF();

            switch (subchannel) {
                case "SERVERS": {
                    String input = in.readUTF();

                    if (input.equalsIgnoreCase("list")) {
                        Player t = Bukkit.getPlayer(UUID.fromString(in.readUTF()));

                        if (t != null) {
                            String modus = in.readUTF();
                            String result = in.readUTF();

                            ServerInventory.create(t, modus, result);
                        }
                    }
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
