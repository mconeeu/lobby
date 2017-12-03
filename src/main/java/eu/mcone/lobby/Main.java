/*
 * Copyright (c) 2017 Dominik L., Rufus Maiwald, BamDev and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby;

import eu.mcone.bukkitcoresystem.api.HologramAPI;
import eu.mcone.bukkitcoresystem.command.holo_CMD;
import eu.mcone.bukkitcoresystem.mysql.MySQL_Config;
import eu.mcone.lobby.command.npc_CMD;
import eu.mcone.lobby.command.spawn_CMD;
import eu.mcone.lobby.event.*;
import eu.mcone.lobby.util.Scoreboard;
import eu.mcone.lobby.trail.TrailManager;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static String MainPrefix = "§8[§3Lobby§8] ";

    private static Main instance;
    public static MySQL_Config config;
    public static TrailManager trail;
    public static HologramAPI holo;
    
    public void onEnable() {
        instance = this;

        Bukkit.getServer().getConsoleSender().sendMessage(MainPrefix + "§aScoreboard-Manager wird gestartet");
        Scoreboard.startUpdateScoreboardScheduler();

        Bukkit.getServer().getConsoleSender().sendMessage(MainPrefix + "§aMySQL Configs wird initiiert");
        config = new MySQL_Config(eu.mcone.bukkitcoresystem.Main.mysql3, "Lobby", 800);
        registerMySQLConfig();

        Bukkit.getServer().getConsoleSender().sendMessage(MainPrefix + "§aTrail-Scheduler werden gestartet");
        trail = new TrailManager(eu.mcone.bukkitcoresystem.Main.mysql1);
        trail.createMySQLTable();

        Bukkit.getServer().getConsoleSender().sendMessage(MainPrefix + "§aHologram-Manager wird gestartet");
        holo = new HologramAPI(eu.mcone.bukkitcoresystem.Main.mysql1, "Lobby");

        Bukkit.getServer().getPluginManager().registerEvents(new PlayerInteract_Event(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new InventoryClick_Event(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerFish_Event(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerMove_Event(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerToggleFlight_Event(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerJoin_Event(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerQuit_Event(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new WeatherChange_Event(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerPickupItem_Event(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new WeatherChange_Event(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new FoodLevelChange_Event(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new EntitiyDamage_Event(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new EntityDamageByEntity_Event(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerDeathEvent(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new BlockBreak_Event(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new BlockPlace_Event(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerInteractEntity_Event(), this);

        getCommand("npc").setExecutor(new npc_CMD());
        getCommand("spawn").setExecutor(new spawn_CMD());
        getCommand("holo").setExecutor(new holo_CMD(holo));

        for (World w : Bukkit.getServer().getWorlds()) {
            w.setAutoSave(false);
        }

        Bukkit.getServer().getConsoleSender().sendMessage(MainPrefix + "§aVersion §f" + this.getDescription().getVersion() + "§a wurde aktiviert...");

        for (Player p : Bukkit.getOnlinePlayers()) {
            //for Players who are already on server (reload)
            Scoreboard.setObjective(p);
            PlayerJoin_Event.setJoinItems(p);
        }
    }

    public void onDisable(){
        holo.unsetHolograms();
        Bukkit.getServer().getConsoleSender().sendMessage(MainPrefix + "§cPlugin wurde deaktiviert!");
    }

    private void registerMySQLConfig(){
        //create table
        config.createTable();

        //System
        config.insertMySQLConfig("System-Prefix", "&8[&7&l!&8]&3 Lobby &8» &7");
        config.insertMySQLConfig("System-WorldName", "Lobby");

        //Locations
        config.insertMySQLConfig("Location-Spawn", "[\"\",\"\",\"\",\"\",\"\",\"\"]");
        config.insertMySQLConfig("Location-Navigator-1", "[\"\",\"\",\"\",\"\",\"\",\"\"]");
        config.insertMySQLConfig("Location-Navigator-2", "[\"\",\"\",\"\",\"\",\"\",\"\"]");
        config.insertMySQLConfig("Location-Navigator-3", "[\"\",\"\",\"\",\"\",\"\",\"\"]");
        config.insertMySQLConfig("Location-Navigator-4", "[\"\",\"\",\"\",\"\",\"\",\"\"]");
        config.insertMySQLConfig("Location-Navigator-5", "[\"\",\"\",\"\",\"\",\"\",\"\"]");
        config.insertMySQLConfig("Location-Navigator-6", "[\"\",\"\",\"\",\"\",\"\",\"\"]");
        config.insertMySQLConfig("Location-Navigator-7", "[\"\",\"\",\"\",\"\",\"\",\"\"]");
        /*Location 8 ist der Spawn*/
        config.insertMySQLConfig("Location-Navigator-9", "[\"\",\"\",\"\",\"\",\"\",\"\"]");

        //Lobby compass
        config.insertMySQLConfig("Navigator-1","{\"name\":\"§8//§oMCONE§8//\",\"ItemID\":160,\"Lore\":[]}");
        config.insertMySQLConfig("Navigator-2","{\"name\":\"&c&lBedwars\",\"ItemID\":355,\"Lore\":[\"&7&oTöte deine Gegner nachdem du ihre Betten abgebaut hast\",\"&7&oum zu gewinnen\",\"\",\"&8» &f&nRechtsklick&8 | &7&oTeleportieren\"]}");
        config.insertMySQLConfig("Navigator-3","{\"name\":\"§8//§oMCONE§8//\",\"ItemID\":160,\"Lore\":[]}");
        config.insertMySQLConfig("Navigator-4","{\"name\":\"&9&lSykPvP\",\"ItemID\":276,\"Lore\":[\"&7&oFinde deine Gegner auf einer Sky-Map und Töte sie\",\"&7um Coins zu erhalten\",\"\",\"&8» &f&nRechtsklick&8 | &7&oTeleportieren\"]}");
        config.insertMySQLConfig("Navigator-5","{\"name\":\"&2&lMinewar\",\"ItemID\":257,\"Lore\":[\"&7&oGrabe dich unter der Erde zu deinen Gegner und rüste\",\"&7dich aus um sie zu töten und zu gewinnen.\",\"\",\"&8» &f&nRechtsklick&8 | &7&oTeleportieren\"]}");
        config.insertMySQLConfig("Navigator-6","{\"name\":\"&e&lKnockback-FFA\",\"ItemID\":346,\"Lore\":[\"&7&oSchlage die Gegner von der Plattform um Coins\",\"&7zu erhalten\",\"\",\"&8» &f&nRechtsklick&8 | &7&oTeleportieren\"]}");
        config.insertMySQLConfig("Navigator-7","{\"name\":\"&7Comming Soon\",\"ItemID\":166,\"Lore\":[\"&8&oAn diesem Spielmodus arbeiten wir noch...\"]}");
        config.insertMySQLConfig("Navigator-8","{\"name\":\"&f&lSpawn\",\"ItemID\":399,\"Lore\":[\"&7&oZurück zum Lobby Spawn.\",\"&7&oHier startet unser Lobby Rätsel\",\"\",\"&8» &f&nRechtsklick&8 | &7&oTeleportieren\"]}");
        config.insertMySQLConfig("Navigator-9","{\"name\":\"&e&lBuild\",\"ItemID\":2,\"Lore\":[\"&7&oCreative Server. überzeuge uns von deinen Baukünsten\",\"&7&ound werde Builder im MC ONE Team!\",\"\",\"&8» &f&nRechtsklick&8 | &7&oTeleportieren\"]}");

        //Trails
        config.insertMySQLConfig("Trail-Coins-Cookies", 10);
        config.insertMySQLConfig("Trail-Coins-Glow", 10);
        config.insertMySQLConfig("Trail-Coins-Ender", 10);
        config.insertMySQLConfig("Trail-Coins-Music", 10);
        config.insertMySQLConfig("Trail-Coins-Heart", 10);
        config.insertMySQLConfig("Trail-Coins-Lava", 10);
        config.insertMySQLConfig("Trail-Coins-Snow", 10);
        config.insertMySQLConfig("Trail-Coins-Water", 10);

        //store
        config.store();
    }

    public static Main getInstance() {
        return Main.instance;
    }
}
