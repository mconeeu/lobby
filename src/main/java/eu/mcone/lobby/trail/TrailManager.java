/*
 * Copyright (c) 2017 Dominik L., Rufus Maiwald, BamDev and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.trail;

import eu.mcone.bukkitcoresystem.api.CoinsAPI;
import eu.mcone.bukkitcoresystem.mysql.MySQL;
import eu.mcone.bukkitcoresystem.util.ItemFactory;
import eu.mcone.lobby.Lobby;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

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

        Bukkit.getScheduler().runTaskTimer(Lobby.getInstance(), () -> {
            for(final Entity ent : Bukkit.getWorld(Bukkit.getWorld(Lobby.config.getConfigValue("System-WorldName")).getName()).getEntities()) {
                if (!(ent instanceof Player) && !(ent.getType().equals(EntityType.FISHING_HOOK))) {
                    ent.remove();
                }
            }
        }, 100L, 15L);

        Bukkit.getScheduler().runTaskTimer(Lobby.getInstance(), () -> {
            for(final HashMap.Entry<Player, Trail> trailEntry : trails.entrySet()){
                if(trailEntry.getValue().equals(Trail.COOKIES)){
                    trailEntry.getKey().getWorld().dropItem(trailEntry.getKey().getLocation(), new ItemStack(Material.COOKIE));
                } else if(trailEntry.getValue().equals(Trail.GLOW)){
                    trailEntry.getKey().getWorld().dropItem(trailEntry.getKey().getLocation(), new ItemStack(Material.GOLD_INGOT));
                    trailEntry.getKey().getWorld().dropItem(trailEntry.getKey().getLocation(), new ItemStack(Material.BLAZE_ROD));
                } else if(trailEntry.getValue().equals(Trail.ENDER)){
                    trailEntry.getKey().getWorld().dropItem(trailEntry.getKey().getLocation(), new ItemStack(Material.ENDER_PEARL));
                    trailEntry.getKey().getWorld().dropItem(trailEntry.getKey().getLocation(), new ItemStack(Material.EYE_OF_ENDER));
                } else if (trailEntry.getValue().equals(Trail.MUSIC)) {
                    trailEntry.getKey().getWorld().dropItem(trailEntry.getKey().getLocation(), new ItemStack(Material.JUKEBOX));
                    trailEntry.getKey().getWorld().dropItem(trailEntry.getKey().getLocation(), new ItemStack(Material.RECORD_10));
                    trailEntry.getKey().getWorld().dropItem(trailEntry.getKey().getLocation(), new ItemStack(Material.RECORD_6));
                } else if (trailEntry.getValue().equals(Trail.HEART)) {
                    trailEntry.getKey().getWorld().playEffect(trailEntry.getKey().getLocation(), Effect.HEART, 5);
                    trailEntry.getKey().getWorld().playEffect(trailEntry.getKey().getLocation(), Effect.HEART, 5);
                    trailEntry.getKey().getWorld().playEffect(trailEntry.getKey().getLocation(), Effect.SPELL, 5);
                } else if (trailEntry.getValue().equals(Trail.LAVA)) {
                    trailEntry.getKey().getWorld().playEffect(trailEntry.getKey().getLocation(), Effect.LAVA_POP, 5);
                    trailEntry.getKey().getWorld().playEffect(trailEntry.getKey().getLocation(), Effect.LAVA_POP, 5);
                    trailEntry.getKey().getWorld().playEffect(trailEntry.getKey().getLocation(), Effect.LARGE_SMOKE, 5);
                } else if (trailEntry.getValue().equals(Trail.SNOW)) {
                    trailEntry.getKey().getWorld().playEffect(trailEntry.getKey().getLocation(), Effect.SNOW_SHOVEL, 2);
                    trailEntry.getKey().getWorld().playEffect(trailEntry.getKey().getLocation(), Effect.SNOW_SHOVEL, 2);
                    trailEntry.getKey().getWorld().playEffect(trailEntry.getKey().getLocation(), Effect.SNOW_SHOVEL, 2);
                    trailEntry.getKey().getWorld().playEffect(trailEntry.getKey().getLocation(), Effect.SNOWBALL_BREAK, 10);
                    trailEntry.getKey().getWorld().playEffect(trailEntry.getKey().getLocation(), Effect.SNOWBALL_BREAK, 10);
                    trailEntry.getKey().getWorld().playEffect(trailEntry.getKey().getLocation(), Effect.SNOWBALL_BREAK, 10);
                    trailEntry.getKey().getWorld().playEffect(trailEntry.getKey().getLocation(), Effect.SNOWBALL_BREAK, 10);
                    trailEntry.getKey().getWorld().playEffect(trailEntry.getKey().getLocation(), Effect.SNOWBALL_BREAK, 10);
                } else if (trailEntry.getValue().equals(Trail.WATER)) {
                    trailEntry.getKey().getWorld().playEffect(trailEntry.getKey().getLocation(), Effect.SPLASH, 5);
                    trailEntry.getKey().getWorld().playEffect(trailEntry.getKey().getLocation(), Effect.SPLASH, 5);
                    trailEntry.getKey().getWorld().playEffect(trailEntry.getKey().getLocation(), Effect.WATERDRIP, 5);
                    trailEntry.getKey().getWorld().playEffect(trailEntry.getKey().getLocation(), Effect.WATERDRIP, 5);
                    trailEntry.getKey().getWorld().playEffect(trailEntry.getKey().getLocation(), Effect.WATERDRIP, 5);
                }
            }
        }, 100L, 1);
    }

    public void setTrail(Player p, Trail trail) {
        Trail entry = this.trails.get(p);

        if (hasTrail(p, trail)) {
            if ((entry != null) && (entry == trail)) {
                p.sendMessage(Lobby.config.getConfigValue("System-Prefix") + "§cDu hast diesen Trail schon aktiviert!");
                p.closeInventory();
            } else {
                this.trails.put(p, trail);
                p.sendMessage(Lobby.config.getConfigValue("System-Prefix") + "§f" + trail.getName() + " §7aktiviert!");
                p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
                p.closeInventory();
            }
        } else {
            p.sendMessage(Lobby.config.getConfigValue("System-Prefix") + "§4Du besitzt diesen Trail nicht!");
            p.closeInventory();
        }
    }

    public void removeTrail(Player p) {
        p.sendMessage(Lobby.config.getConfigValue("System-Prefix") + "§7Trail entfernt!");
        if (this.trails.containsKey(p)) this.trails.remove(p);
        p.closeInventory();
    }

    public void setInvItem(Inventory inv, Player p, Trail trail, int i) {
        if (hasTrail(p, trail)) {
            inv.setItem(i, ItemFactory.createItem(trail.getItem(), 0, 1, trail.getName(), new ArrayList<>(Arrays.asList("§r", "§2§oDu besitzt dieses Item!", "§8» §f§nRechtsklick§8 | §7§oAktivieren")), true));
        } else {
            inv.setItem(i, ItemFactory.createItem(trail.getItem(), 0, 1, trail.getName(), new ArrayList<>(Arrays.asList("§r", "§c§oDu besitzt dieses Item nicht!", "§7§oKostet: §f§o" + trail.getCoins() + " Coins")), true));
        }
    }

    public void buyTrail(Player p, Trail trail) {
        if (!hasTrail(p, trail)) {
            if ((CoinsAPI.getCoins(p.getUniqueId()) - trail.getCoins()) >= 0) {
                CoinsAPI.removeCoins(p.getUniqueId(), trail.getCoins());
                this.mysql.update("INSERT INTO `lobby_items` (`id`, `uuid`, `cat`, `item`, `timestamp`) VALUES (NULL, '" + p.getUniqueId() + "', 'trail', '" + trail.getId() + "', " + (System.currentTimeMillis() / 1000L) + ");");

                ArrayList<Trail> trailArrayList = this.allowedTrails.get(p.getUniqueId()) != null ? this.allowedTrails.get(p.getUniqueId()) : new ArrayList<>();

                if (!trailArrayList.contains(trail)) {
                    trailArrayList.add(trail);
                    this.allowedTrails.put(p.getUniqueId(), trailArrayList);
                }

                p.closeInventory();
                p.sendMessage(Lobby.config.getConfigValue("System-Prefix") + "§2Du hast erfolgreich den Trail " + trail.getName() + "§2 gekauft!");
                p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 1);
            } else {
                p.closeInventory();
                p.sendMessage(Lobby.config.getConfigValue("System-Prefix") + "§4Du hast nicht genügend Coins!");
                p.playSound(p.getLocation(), Sound.NOTE_BASS, 1, 1);
            }
        } else {
            p.closeInventory();
            p.sendMessage(Lobby.config.getConfigValue("System-Prefix") + "§4Du besitzt dieses Item bereits!");
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1, 1);
        }
    }

    public void createMySQLTable() {
        this.mysql.update("CREATE TABLE IF NOT EXISTS lobby_items (`id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY, `uuid` VARCHAR(100), `cat` VARCHAR(50) NOT NULL, `item` VARCHAR(100) NOT NULL, `timestamp` int(50)) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
    }

    public void loadAllowedTrails(final UUID uuid){
        Bukkit.getScheduler().runTaskAsynchronously(Lobby.getInstance(), () -> {
            this.mysql.select("SELECT `cat`, `item` FROM `lobby_items` WHERE `uuid`='"+uuid+"' AND `cat`='trail'", rs -> {
                try {
                    while (rs.next()) {
                        ArrayList<Trail> trailArrayList = this.allowedTrails.get(uuid) != null ? this.allowedTrails.get(uuid) : new ArrayList<>();

                        if (!trailArrayList.contains(Trail.getTrailbyID(rs.getInt("item")))) {
                            trailArrayList.add(Trail.getTrailbyID(rs.getInt("item")));
                            this.allowedTrails.put(uuid, trailArrayList);
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
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

    public void unregisterPlayer(Player p) {
        if (trails.containsKey(p)) trails.remove(p);
        if (allowedTrails.containsKey(p.getUniqueId())) allowedTrails.remove(p.getUniqueId());
    }

}
