/*
 * Copyright (c) 2017 Dominik L., BamDev, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.trail;

import eu.mcone.lobby.Main;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class TrailManager {
    
    private HashMap<Player, Trails> trails = new HashMap<>();

    public TrailManager(){
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), () -> {
            for(Entity ent : Bukkit.getWorld(Bukkit.getWorld(Main.config.getConfigValue("System-WorldName")).getName()).getEntities()) {
                if (!(ent instanceof Player) && !(ent.getType().equals(EntityType.FISHING_HOOK))) {
                    ent.remove();
                }
            }
        }, 20, 20);

        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), () -> {
            for(HashMap.Entry<Player, Trails> trailEntry : trails.entrySet()){
                Player p = trailEntry.getKey();
                
                if(trailEntry.getValue().equals(Trails.COOKIES)){
                    p.getWorld().dropItem(p.getLocation(), new ItemStack(Material.COOKIE));
                } else if(trailEntry.getValue().equals(Trails.GLOW)){
                    p.getWorld().dropItem(p.getLocation(), new ItemStack(Material.GOLD_INGOT));
                    p.getWorld().dropItem(p.getLocation(), new ItemStack(Material.BLAZE_ROD));
                } else if(trailEntry.getValue().equals(Trails.ENDER)){
                    p.getWorld().dropItem(p.getLocation(), new ItemStack(Material.ENDER_PEARL));
                    p.getWorld().dropItem(p.getLocation(), new ItemStack(Material.EYE_OF_ENDER));
                } else if (trailEntry.getValue().equals(Trails.MUSIC)) {
                    p.getWorld().dropItem(p.getLocation(), new ItemStack(Material.JUKEBOX));
                    p.getWorld().dropItem(p.getLocation(), new ItemStack(Material.RECORD_10));
                    p.getWorld().dropItem(p.getLocation(), new ItemStack(Material.RECORD_6));
                } else if (trailEntry.getValue().equals(Trails.HEART)) {
                    p.getWorld().playEffect(p.getLocation(), Effect.HEART, 5);
                    p.getWorld().playEffect(p.getLocation(), Effect.HEART, 5);
                    p.getWorld().playEffect(p.getLocation(), Effect.SPELL, 5);
                } else if (trailEntry.getValue().equals(Trails.LAVA)) {
                    p.getWorld().playEffect(p.getLocation(), Effect.LAVA_POP, 5);
                    p.getWorld().playEffect(p.getLocation(), Effect.LAVA_POP, 5);
                    p.getWorld().playEffect(p.getLocation(), Effect.LARGE_SMOKE, 5);
                } else if (trailEntry.getValue().equals(Trails.SNOW)) {
                    p.getWorld().playEffect(p.getLocation(), Effect.SNOW_SHOVEL, 2);
                    p.getWorld().playEffect(p.getLocation(), Effect.SNOW_SHOVEL, 2);
                    p.getWorld().playEffect(p.getLocation(), Effect.SNOW_SHOVEL, 2);
                    p.getWorld().playEffect(p.getLocation(), Effect.SNOWBALL_BREAK, 10);
                    p.getWorld().playEffect(p.getLocation(), Effect.SNOWBALL_BREAK, 10);
                    p.getWorld().playEffect(p.getLocation(), Effect.SNOWBALL_BREAK, 10);
                    p.getWorld().playEffect(p.getLocation(), Effect.SNOWBALL_BREAK, 10);
                    p.getWorld().playEffect(p.getLocation(), Effect.SNOWBALL_BREAK, 10);
                } else if (trailEntry.getValue().equals(Trails.WATER)) {
                    p.getWorld().playEffect(p.getLocation(), Effect.SPLASH, 5);
                    p.getWorld().playEffect(p.getLocation(), Effect.SPLASH, 5);
                    p.getWorld().playEffect(p.getLocation(), Effect.WATERDRIP, 5);
                    p.getWorld().playEffect(p.getLocation(), Effect.WATERDRIP, 5);
                    p.getWorld().playEffect(p.getLocation(), Effect.WATERDRIP, 5);
                }
            }
        }, 10, 2);
    }

    public void setTrail(Player p, Trails trail) {
        Trails entry = this.trails.get(p);

        if (p.hasPermission("lobby.trail."+trail.getPerm())) {
            if ((entry != null) && (entry == trail)) {
                p.sendMessage(Main.config.getConfigValue("System-Prefix") + "§cDu hast diesen Trail schon aktiviert!");
                p.closeInventory();
            }else{
                this.trails.put(p, trail);
                p.sendMessage(Main.config.getConfigValue("System-Prefix") + "§f" + trail.getName() + " §2aktiviert!");
                p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
                p.closeInventory();
            }
        } else {
            p.sendMessage(Main.config.getConfigValue("System-Prefix") + "§4Du hast keine Berechtigung für diesen Trail!");
        }
    }

    public void removeTrail(Player p) {
        p.sendMessage(Main.config.getConfigValue("System-Prefix") + "§2Trail entfernt!");
        this.trails.remove(p);
        p.closeInventory();
    }

    public boolean hasPermissionForTail(Player p, Trails trails) {
        return p.hasPermission("lobby.trail." + trails.getPerm());
    }

    public Trails getTrail(Player p) {
        return this.trails.get(p);
    }

    public boolean hasTrail(Player p, Trails trail) {
        return this.trails.get(p).equals(trail);
    }
}
