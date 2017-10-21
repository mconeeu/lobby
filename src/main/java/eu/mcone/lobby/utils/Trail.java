/*
 * Copyright (c) 2017 Dominik L., BamDev, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.utils;

import eu.mcone.lobby.Main;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Trail {

    public Trail(){
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), () -> {
            for(Entity ent : Bukkit.getWorld(Bukkit.getWorld(Main.config.getConfigValue("System-WorldName")).getName()).getEntities()) {
                if (!(ent instanceof Player) && !(ent.getType().equals(EntityType.FISHING_HOOK))) {
                    ent.remove();
                }
            }
        }, 20, 20);

        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), () -> {
            for(Player all : Bukkit.getOnlinePlayers()){
                if(Var.cookies.contains(all)){
                    all.getWorld().dropItem(all.getLocation(), new ItemStack(Material.COOKIE));
                }
                if(Var.glow.contains(all)){
                    all.getWorld().dropItem(all.getLocation(), new ItemStack(Material.GOLD_INGOT));
                    all.getWorld().dropItem(all.getLocation(), new ItemStack(Material.BLAZE_ROD));
                }
                if(Var.ender.contains(all)){
                    all.getWorld().dropItem(all.getLocation(), new ItemStack(Material.ENDER_PEARL));
                    all.getWorld().dropItem(all.getLocation(), new ItemStack(Material.EYE_OF_ENDER));
                }
                if (Var.musik.contains(all)) {
                    all.getWorld().dropItem(all.getLocation(), new ItemStack(Material.JUKEBOX));
                    all.getWorld().dropItem(all.getLocation(), new ItemStack(Material.RECORD_10));
                    all.getWorld().dropItem(all.getLocation(), new ItemStack(Material.RECORD_6));
                }


                //HeartTrail
                if (Var.heart.contains(all)) {
                    all.getWorld().playEffect(all.getLocation(), Effect.HEART, 5);
                    all.getWorld().playEffect(all.getLocation(), Effect.HEART, 5);
                    all.getWorld().playEffect(all.getLocation(), Effect.SPELL, 5);
                }
                //LavaTrail
                if (Var.lava.contains(all)) {
                    all.getWorld().playEffect(all.getLocation(), Effect.LAVA_POP, 5);
                    all.getWorld().playEffect(all.getLocation(), Effect.LAVA_POP, 5);
                    all.getWorld().playEffect(all.getLocation(), Effect.LARGE_SMOKE, 5);
                }
                //SnowTrail
                if (Var.snow.contains(all)) {
                    all.getWorld().playEffect(all.getLocation(), Effect.SNOW_SHOVEL, 2);
                    all.getWorld().playEffect(all.getLocation(), Effect.SNOW_SHOVEL, 2);
                    all.getWorld().playEffect(all.getLocation(), Effect.SNOW_SHOVEL, 2);
                    all.getWorld().playEffect(all.getLocation(), Effect.SNOWBALL_BREAK, 10);
                    all.getWorld().playEffect(all.getLocation(), Effect.SNOWBALL_BREAK, 10);
                    all.getWorld().playEffect(all.getLocation(), Effect.SNOWBALL_BREAK, 10);
                    all.getWorld().playEffect(all.getLocation(), Effect.SNOWBALL_BREAK, 10);
                    all.getWorld().playEffect(all.getLocation(), Effect.SNOWBALL_BREAK, 10);
                }
                //Water Trail
                if (Var.wasser.contains(all)) {
                    all.getWorld().playEffect(all.getLocation(), Effect.SPLASH, 5);
                    all.getWorld().playEffect(all.getLocation(), Effect.SPLASH, 5);
                    all.getWorld().playEffect(all.getLocation(), Effect.WATERDRIP, 5);
                    all.getWorld().playEffect(all.getLocation(), Effect.WATERDRIP, 5);
                    all.getWorld().playEffect(all.getLocation(), Effect.WATERDRIP, 5);
                }
            }
        }, 10, 2);
    }

}
