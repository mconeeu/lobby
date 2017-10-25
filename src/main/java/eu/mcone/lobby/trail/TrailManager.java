/*
 * Copyright (c) 2017 Dominik L., BamDev, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.trail;

import de.Dominik.BukkitCoreSystem.MySQL.MySQL;
import eu.mcone.lobby.Main;
import eu.mcone.lobby.utils.ItemManager;
import javafx.print.PageLayout;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

public class TrailManager {

    private MySQL mysql;
    private HashMap<Player, Trails> trails = new HashMap<>();
    private HashMap<UUID, ArrayList<Trails>> allowedTrails = new HashMap<>();

    public TrailManager(MySQL mysql){
        this.mysql = mysql;

        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), () -> {
            for(Entity ent : Bukkit.getWorld(Bukkit.getWorld(Main.config.getConfigValue("System-WorldName")).getName()).getEntities()) {
                if (!(ent instanceof Player) && !(ent.getType().equals(EntityType.FISHING_HOOK))) {
                    ent.remove();
                }
            }
            updateAllowedTrails();
        }, 20, 15);

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
        ArrayList<Trails> allowedTrailsList = getAllowedTrailsList(p);

        if (p.hasPermission(trail.getPerm()) || p.hasPermission("group.premium") || allowedTrailsList.contains(trail)) {
            if ((entry != null) && (entry == trail)) {
                p.sendMessage(Main.config.getConfigValue("System-Prefix") + "§cDu hast diesen Trail schon aktiviert!");
                p.closeInventory();
            }else{
                this.trails.put(p, trail);
                p.sendMessage(Main.config.getConfigValue("System-Prefix") + "§f" + trail.getName() + " §7aktiviert!");
                p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
                p.closeInventory();
            }
        } else {
            p.sendMessage(Main.config.getConfigValue("System-Prefix") + "§4Du besitzt diesen Trail nicht!");
            p.closeInventory();
        }
    }

    public void removeTrail(Player p) {
        p.sendMessage(Main.config.getConfigValue("System-Prefix") + "§7Trail entfernt!");
        this.trails.remove(p);
        p.closeInventory();
    }

    public void setInvItem(Inventory inv, Player p, Trails trail, int i) {
        ArrayList<Trails> allowedTrailsList = getAllowedTrailsList(p);

        if (p.hasPermission(trail.getPerm()) || p.hasPermission("group.premium") || allowedTrailsList.contains(trail)) {
            inv.setItem(i, ItemManager.createItemLore(trail.getItem(), 0, 0, trail.getName(), new ArrayList<>(Arrays.asList("§r", "§2§oDu besitzt dieses Item!", "§8» §f§nRechtsklick§8 | §7§oAktivieren", ""))));
        } else {
            inv.setItem(i, ItemManager.createItemLore(trail.getItem(), 0, 0, trail.getName(), new ArrayList<>(Arrays.asList("§r", "§c§oDu besitzt dieses Item nicht!", ""))));
        }
    }

    public void createMySQLTable() {
        this.mysql.update("CREATE TABLE IF NOT EXISTS lobby_items (`id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY, `uuid` VARCHAR(100), `cat` VARCHAR(50) NOT NULL, `item` VARCHAR(100) NOT NULL, `timestamp` int(50)) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
    }

    private void updateAllowedTrails(){
        ResultSet rs = this.mysql.getResult("SELECT * FROM lobby_items WHERE `cat`='trail';");
        try {
            while (rs.next()) {
                UUID uuid = UUID.fromString(rs.getString("uuid"));

                ArrayList<Trails> trailsArrayList = allowedTrails.get(uuid) != null ? allowedTrails.get(uuid) : new ArrayList<>();
                trailsArrayList.add(Trails.getTrailbyID(rs.getInt("item")));

                allowedTrails.put(uuid, trailsArrayList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Trails> getAllowedTrailsList(Player p) {
        if (this.allowedTrails.containsKey(p.getUniqueId())) {
            return this.allowedTrails.get(p.getUniqueId());
        } else {
            return new ArrayList<>();
        }
    }
}
