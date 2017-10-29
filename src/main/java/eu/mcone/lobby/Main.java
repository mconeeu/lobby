/*
 * Copyright (c) 2017 Dominik L., Rufus Maiwald, BamDev and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby;

import de.Dominik.BukkitCoreSystem.mysql.MySQL_Config;
import eu.mcone.lobby.commands.holo_CMD;
import eu.mcone.lobby.commands.npc_CMD;
import eu.mcone.lobby.commands.set_CMD;
import eu.mcone.lobby.commands.spawn_CMD;
import eu.mcone.lobby.events.*;
import eu.mcone.lobby.hologram.HologramManager;
import eu.mcone.lobby.scoreboard.ScoreboardManager;
import eu.mcone.lobby.trail.TrailManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static String MainPrefix = "§8[§3Lobby§8] ";

    private static Main instance;
    public static MySQL_Config config;
    public static TrailManager trail;
    public static HologramManager holo;
    
    public void onEnable() {
        instance = this;

        ScoreboardManager.updateScoreboardScheduler();

        Bukkit.getServer().getConsoleSender().sendMessage(MainPrefix + "§aMySQL Config wird initiiert");
        config = new MySQL_Config(de.Dominik.BukkitCoreSystem.main.Main.mysql3, "Lobby", 800);
        registerMySQLConfig();

        Bukkit.getServer().getConsoleSender().sendMessage(MainPrefix + "§aTrail-Scheduler werden gestartet");
        trail = new TrailManager(de.Dominik.BukkitCoreSystem.main.Main.mysql1);
        trail.createMySQLTable();

        Bukkit.getServer().getConsoleSender().sendMessage(MainPrefix + "§aHologram-Manager werden gestartet");
        holo = new HologramManager();

        Bukkit.getServer().getPluginManager().registerEvents(new PlayerInteract_Event(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new InventoryClick_Event(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerFish_Event(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerMove_Event(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerToggleFlight_Event(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerJoin_Event(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerQuit_Event(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new WeatherChange_Event(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerPickupItem_Event(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new InventoryDrag_Event(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new WeatherChange_Event(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new FoodLevelChange_Event(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new EntitiyDamageEvent(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new EntityDamageByEntity_Event(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerDeathEvent(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new BlockBreak_Event(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new BlockPlace_Event(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerInteractEntity_Event(), this);

        getCommand("set").setExecutor(new set_CMD());
        getCommand("npc").setExecutor(new npc_CMD());
        getCommand("spawn").setExecutor(new spawn_CMD());
        getCommand("setholo").setExecutor(new holo_CMD());

        Bukkit.getServer().getConsoleSender().sendMessage(MainPrefix + "§aVersion §f" + this.getDescription().getVersion() + "§a wurde aktiviert...");
    }

    public void onDisable(){
        Bukkit.getServer().getConsoleSender().sendMessage(MainPrefix + "§aPlugin wurde deaktiviert!");
    }

    private void registerMySQLConfig(){
        //create table
        config.createTable();

        //System
        config.insertMySQLConfig("System-Prefix", "&8[&7&l!&8]&3 Lobby &8» &7");
        config.insertMySQLConfig("System-WorldName", "Lobby");

        //Locations
        config.insertMySQLConfig("Location-Spawn", "{[\"\",\"\",\"\",\"\",\"\",\"\"]}");

        //TitleAPI
        config.insertMySQLConfig("TitleAPI-Join-Title", "§fWillkommen auf §3§lMC ONE");
        config.insertMySQLConfig("TitleAPI-Join-Subtitle", "§7§oDein Nummer 1 Minecraftnetzwerk");

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
