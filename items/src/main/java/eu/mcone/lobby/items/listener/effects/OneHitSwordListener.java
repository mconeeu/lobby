/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.items.listener.effects;

import eu.mcone.lobby.api.items.LobbyItem;
import net.minecraft.server.v1_8_R3.EntityLightning;
import net.minecraft.server.v1_8_R3.PacketPlayOutNamedSoundEffect;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityWeather;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Set;

public class OneHitSwordListener implements Listener {

    @EventHandler
    public void on(PlayerInteractEvent e) {
        if (e.hasItem() && e.getItem().equals(LobbyItem.ONE_HIT_SWORD.getItemStack()) && (e.getAction().equals(Action.LEFT_CLICK_BLOCK) || e.getAction().equals(Action.LEFT_CLICK_AIR))) {
            Player p = e.getPlayer();

            p.getWorld().playEffect(p.getLocation(), Effect.MAGIC_CRIT, 10);

            Location loc = p.getTargetBlock((Set<Material>) null, 50).getLocation();
            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(new PacketPlayOutSpawnEntityWeather(new EntityLightning(((CraftPlayer) p).getHandle().getWorld(), loc.getX(), loc.getY(), loc.getZ(), false, false)));
            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(new PacketPlayOutNamedSoundEffect("ambient.weather.thunder", loc.getX(), loc.getY(), loc.getZ(), 10000.0F, 63));
        }
    }

}
