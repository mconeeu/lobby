/*
 * Copyright (c) 2017 Dominik L., Rufus Maiwald, BamDev and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.util;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import eu.mcone.lobby.Main;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class NPC {

    private static HashMap<String, EntityPlayer> list = new HashMap<>();

    public NPC(Location loc, String displayname){
        EntityPlayer npc;
        MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
        WorldServer world = ((CraftWorld) Bukkit.getWorlds().get(0)).getHandle();

        GameProfile gameprofile = new GameProfile(UUID.fromString("baa2a833-795e-49dc-9a23-d07c66f053c1"), "");
        String texture = "eyJ0aW1lc3RhbXAiOjE1MDgwODcxOTg4MTIsInByb2ZpbGVJZCI6IjQ0YjhhNWQ2YzJjMzQ1NzY5OTdmNzFiOTRmNWViN2UwIiwicHJvZmlsZU5hbWUiOiJydWZpIiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS8xYzIyODY2ODE1YjlmYjU2YTE2NTMxZWZmYTliMTlkZGIwMzVhZTIyZjVhMjNhZmNlZjVhMTJjN2M1ZDIyMmZlIn19fQ==";
        String signature = "BL4nnMuxTlZU741jGvV5VWJzEm1ZKNYo8FD4ZgqpfhHYrHlx/s3hF8vtYr60UMJgVsN+gqnOBzQOoX+7Cjt8T/WfJlAPbHNLmJEpCKrAt+0LGMruw+p2nMjtyJioqPTa2f4zQavth3OasFpOn3EWbPQU9f/jTFEpSK19cJV4ufBVxgYL6RYcirggBl4MFtk8AU/iuW+3SMnJe7RXj3h0D4WRj3vJAPtfFQMs3Mp4i0DspMNbsi/njT6Qm+fxYDabQlStOCBEqgL9fy6Yuge2jhqIFMS/+LjzJm0xDYZs4sYr4Qm3uWCCyXBbcFZ/yYdPM/LH4hdgRP0fQhqW+6jPeEQIkj6GQYoABY5pO7szKS9fD/L8oITZR92LWC1YcMNGUlvAJ99mKAKV8fmvPIvdVhL3l452C98fatSXnpARB1XSSuOg1sHpAy2ZQToHPD9ZV6j+SUePdTqGn0F1h7D410RBSlqHo+btiU6U6/brObYQvz0hKzd64fjXa+lDVHFvxpV1y8SrjC0zYmFL487izoudbzxlYjzF4OVTDRzhSQbCFQPxCEltPoP3e+PUIZM1bocaWAxTmXCR04gCG42SLi4VKcRYFYQl1lNdqGgnkIEnmPjuMIGrfkCmY9/LS4PbuTe994UcLRZ1Q7iFqgn2IyOjjkDJCAFVdc27YF4Itjg=";
        gameprofile.getProperties().put("textures", new Property("textures", texture, signature));

        npc = new EntityPlayer(server, world, gameprofile, new PlayerInteractManager(world));

        npc.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());

        list.put(displayname, npc);
    }

    public static void set(Player p, String displayname) {
        EntityPlayer npc = list.get(displayname);

        PlayerConnection connection = ((CraftPlayer)p).getHandle().playerConnection;
        connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, npc));
        connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));

        Player npcplayer = npc.getBukkitEntity().getPlayer();
        npcplayer.setPlayerListName("");

        new BukkitRunnable() {
            @Override
            public void run() {
                connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, npc));
            }
        }.runTaskLater(Main.getInstance(), 50);
    }

    public static void unset(Player p, String displayname) {
        EntityPlayer npc = list.get(displayname);

        PlayerConnection connection = ((CraftPlayer)p).getHandle().playerConnection;
        connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, npc));
        connection.sendPacket(new PacketPlayOutEntityDestroy(npc.getId()));
    }

}
