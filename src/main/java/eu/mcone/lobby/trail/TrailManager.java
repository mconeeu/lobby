/*
 * Copyright (c) 2017 Dominik L., Rufus Maiwald, BamDev and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.trail;

import eu.mcone.bukkitcoresystem.api.CoinsAPI;
import eu.mcone.bukkitcoresystem.mysql.MySQL;
import eu.mcone.bukkitcoresystem.util.ItemManager;
import eu.mcone.lobby.Main;
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
    private HashMap<Player, Trail> trails = new HashMap<>();
    private HashMap<UUID, ArrayList<Trail>> allowedTrails = new HashMap<>();

    public TrailManager(MySQL mysql){
        this.mysql = mysql;

        Bukkit.getScheduler().runTaskTimer(Main.getInstance(), () -> {
            for(Entity ent : Bukkit.getWorld(Bukkit.getWorld(Main.config.getConfigValue("System-WorldName")).getName()).getEntities()) {
                if (!(ent instanceof Player) && !(ent.getType().equals(EntityType.FISHING_HOOK))) {
                    ent.remove();
                }
            }
            updateAllowedTrails();
        }, 100L, 15L);

        Bukkit.getScheduler().runTaskTimer(Main.getInstance(), () -> {
            for(HashMap.Entry<Player, Trail> trailEntry : trails.entrySet()){
                Player p = trailEntry.getKey();
                
                if(trailEntry.getValue().equals(Trail.COOKIES)){
                    p.getWorld().dropItem(p.getLocation(), new ItemStack(Material.COOKIE));
                } else if(trailEntry.getValue().equals(Trail.GLOW)){
                    p.getWorld().dropItem(p.getLocation(), new ItemStack(Material.GOLD_INGOT));
                    p.getWorld().dropItem(p.getLocation(), new ItemStack(Material.BLAZE_ROD));
                } else if(trailEntry.getValue().equals(Trail.ENDER)){
                    p.getWorld().dropItem(p.getLocation(), new ItemStack(Material.ENDER_PEARL));
                    p.getWorld().dropItem(p.getLocation(), new ItemStack(Material.EYE_OF_ENDER));
                } else if (trailEntry.getValue().equals(Trail.MUSIC)) {
                    p.getWorld().dropItem(p.getLocation(), new ItemStack(Material.JUKEBOX));
                    p.getWorld().dropItem(p.getLocation(), new ItemStack(Material.RECORD_10));
                    p.getWorld().dropItem(p.getLocation(), new ItemStack(Material.RECORD_6));
                } else if (trailEntry.getValue().equals(Trail.HEART)) {
                    p.getWorld().playEffect(p.getLocation(), Effect.HEART, 5);
                    p.getWorld().playEffect(p.getLocation(), Effect.HEART, 5);
                    p.getWorld().playEffect(p.getLocation(), Effect.SPELL, 5);
                } else if (trailEntry.getValue().equals(Trail.LAVA)) {
                    p.getWorld().playEffect(p.getLocation(), Effect.LAVA_POP, 5);
                    p.getWorld().playEffect(p.getLocation(), Effect.LAVA_POP, 5);
                    p.getWorld().playEffect(p.getLocation(), Effect.LARGE_SMOKE, 5);
                } else if (trailEntry.getValue().equals(Trail.SNOW)) {
                    p.getWorld().playEffect(p.getLocation(), Effect.SNOW_SHOVEL, 2);
                    p.getWorld().playEffect(p.getLocation(), Effect.SNOW_SHOVEL, 2);
                    p.getWorld().playEffect(p.getLocation(), Effect.SNOW_SHOVEL, 2);
                    p.getWorld().playEffect(p.getLocation(), Effect.SNOWBALL_BREAK, 10);
                    p.getWorld().playEffect(p.getLocation(), Effect.SNOWBALL_BREAK, 10);
                    p.getWorld().playEffect(p.getLocation(), Effect.SNOWBALL_BREAK, 10);
                    p.getWorld().playEffect(p.getLocation(), Effect.SNOWBALL_BREAK, 10);
                    p.getWorld().playEffect(p.getLocation(), Effect.SNOWBALL_BREAK, 10);
                } else if (trailEntry.getValue().equals(Trail.WATER)) {
                    p.getWorld().playEffect(p.getLocation(), Effect.SPLASH, 5);
                    p.getWorld().playEffect(p.getLocation(), Effect.SPLASH, 5);
                    p.getWorld().playEffect(p.getLocation(), Effect.WATERDRIP, 5);
                    p.getWorld().playEffect(p.getLocation(), Effect.WATERDRIP, 5);
                    p.getWorld().playEffect(p.getLocation(), Effect.WATERDRIP, 5);
                }
            }
        }, 100L, 5L);
    }

    public void setTrail(Player p, Trail trail) {
        Trail entry = this.trails.get(p);
        ArrayList<Trail> allowedTrailList = getAllowedTrailsList(p);

        if (p.hasPermission(trail.getPerm()) || p.hasPermission("group.premium") || allowedTrailList.contains(trail)) {
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
        if (this.trails.containsKey(p)) this.trails.remove(p);
        p.closeInventory();
    }

    public void setInvItem(Inventory inv, Player p, Trail trail, int i) {
        ArrayList<Trail> allowedTrailList = getAllowedTrailsList(p);

        if (hasTrail(p, trail)) {
            inv.setItem(i, ItemManager.createItem(trail.getItem(), 0, 1, trail.getName(), new ArrayList<>(Arrays.asList("§r", "§2§oDu besitzt dieses Item!", "§8» §f§nRechtsklick§8 | §7§oAktivieren")), true));
        } else {
            inv.setItem(i, ItemManager.createItem(trail.getItem(), 0, 1, trail.getName(), new ArrayList<>(Arrays.asList("§r", "§c§oDu besitzt dieses Item nicht!", "§7§oKostet: §f§o" + trail.getCoins() + " Coins")), true));
        }
    }

    public void buyTrail(Player p, Trail trail) {
        if (!hasTrail(p, trail)) {
            if ((CoinsAPI.getCoins(p) - trail.getCoins()) >= 0) {
                CoinsAPI.removeCoins(p, trail.getCoins());
                this.mysql.update("INSERT INTO `lobby_items` (`id`, `uuid`, `cat`, `item`, `timestamp`) VALUES (NULL, '" + p.getUniqueId() + "', 'trail', '" + trail.getId() + "', " + (System.currentTimeMillis() / 1000L) + ");");
                p.closeInventory();
                p.sendMessage(Main.config.getConfigValue("System-Prefix") + "§2Du hast erfolgreich den Trail " + trail.getName() + "§2 gekauft!");
                p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 1);
            } else {
                p.closeInventory();
                p.sendMessage(Main.config.getConfigValue("System-Prefix") + "§4Du hast nicht genügend Coins!");
                p.playSound(p.getLocation(), Sound.NOTE_BASS, 1, 1);
            }
        } else {
            p.closeInventory();
            p.sendMessage(Main.config.getConfigValue("System-Prefix") + "§4Du besitzt dieses Item bereits!");
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1, 1);
        }
    }

    public void createMySQLTable() {
        this.mysql.update("CREATE TABLE IF NOT EXISTS lobby_items (`id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY, `uuid` VARCHAR(100), `cat` VARCHAR(50) NOT NULL, `item` VARCHAR(100) NOT NULL, `timestamp` int(50)) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
    }

    private void updateAllowedTrails(){
        Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> {
            ResultSet rs = this.mysql.getResult("SELECT `uuid`, `item` FROM `lobby_items` WHERE `cat`='trail';");
            try {
                while (rs.next()) {
                    UUID uuid = UUID.fromString(rs.getString("uuid"));
                    int item = rs.getInt("item");
                    Trail trail = Trail.getTrailbyID(item);

                    ArrayList<Trail> trailArrayList = this.allowedTrails.get(uuid) != null ? this.allowedTrails.get(uuid) : new ArrayList<>();

                    if (!trailArrayList.contains(trail)) {
                        trailArrayList.add(trail);
                        this.allowedTrails.put(uuid, trailArrayList);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    private ArrayList<Trail> getAllowedTrailsList(Player p) {
        if (this.allowedTrails.containsKey(p.getUniqueId())) {
            return this.allowedTrails.get(p.getUniqueId());
        } else {
            return new ArrayList<>();
        }
    }

    public boolean hasTrail(Player p, Trail trail) {
        ArrayList<Trail> trailList = getAllowedTrailsList(p);

        return p.hasPermission(trail.getPerm()) || p.hasPermission("mcone.premium") || trailList.contains(trail);
    }
}
