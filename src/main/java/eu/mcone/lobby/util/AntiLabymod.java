/*
 * Copyright (c) 2017 - 2018 Dominik L., Rufus Maiwald, BamDev and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.util;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.server.v1_8_R3.PacketDataSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutCustomPayload;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class AntiLabymod implements Listener {

    public static void setLabySettings(Player p){
        try{
            HashMap<String, Boolean> dList = new HashMap<>();

            for (DisabledLabyModFunctions d : DisabledLabyModFunctions.values()) {
                dList.put(d.getName(), false);
            }

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream objOut = new ObjectOutputStream(out);
            objOut.writeObject(dList);
            ByteBuf bb = Unpooled.copiedBuffer(out.toByteArray());

            PacketDataSerializer serializer = new PacketDataSerializer(bb);
            PacketPlayOutCustomPayload packet = new PacketPlayOutCustomPayload("LABYMOD", serializer);
            ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private enum DisabledLabyModFunctions {
        POTIONS("POTIONS"),
        ARMOR("ARMOR"),
        DAMAGEINDICATOR("DAMAGEINDICATOR"),
        MINIMAP_RADAR("MINIMAP_RADAR");

        private String name;

        DisabledLabyModFunctions(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

}
