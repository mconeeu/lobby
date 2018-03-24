/*
 * Copyright (c) 2017 - 2018 Dominik L., Rufus Maiwald, BamDev and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby;

import eu.mcone.coresystem.bukkit.CoreSystem;
import eu.mcone.coresystem.bukkit.hologram.HologramManager;
import eu.mcone.coresystem.bukkit.npc.NpcManager;
import eu.mcone.coresystem.bukkit.player.CorePlayer;
import eu.mcone.coresystem.bukkit.util.BuildSystem;
import eu.mcone.coresystem.bukkit.util.LocationManager;
import eu.mcone.coresystem.lib.mysql.MySQL_Config;
import eu.mcone.lobby.listener.*;
import eu.mcone.lobby.trail.TrailManager;
import eu.mcone.lobby.util.Objective;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Lobby extends JavaPlugin {

    private static String MainPrefix = "§8[§3Lobby§8] ";

    @Getter
    private static Lobby instance;
    public static MySQL_Config config;

    @Getter
    private TrailManager trailManager;
    @Getter
    private HologramManager hologramManager;
    @Getter
    private NpcManager npcManager;
    @Getter
    private BuildSystem buildSystem;
    @Getter
    private LocationManager locationManager;

    public void onEnable() {
        instance = this;

        Bukkit.getServer().getConsoleSender().sendMessage(MainPrefix + "§aMySQL Config wird initiiert");
        config = new MySQL_Config(CoreSystem.mysql3, "Lobby", 800);
        registerMySQLConfig();

        Bukkit.getServer().getConsoleSender().sendMessage(MainPrefix + "§aTrail-Scheduler werden gestartet");
        trailManager = new TrailManager(CoreSystem.mysql1);
        trailManager.createMySQLTable();

        Bukkit.getServer().getConsoleSender().sendMessage(MainPrefix + "§aScoreboard-Scheduler wird gestartet");
        startScheduler();

        Bukkit.getServer().getConsoleSender().sendMessage(MainPrefix + "§aHologram-Manager wird gestartet");
        hologramManager = new HologramManager(CoreSystem.mysql1, "Lobby");

        Bukkit.getServer().getConsoleSender().sendMessage(MainPrefix + "§aNPC-Manager wird gestartet");
        npcManager = new NpcManager(CoreSystem.mysql1, "Lobby");

        Bukkit.getServer().getConsoleSender().sendMessage(MainPrefix + "§aLocationManager witd initiiert");
        locationManager = new LocationManager("Lobby")
                .registerLocation("bedwars")
                .registerLocation("skypvp")
                .registerLocation("knockit")
                .registerLocation("minewar")
                .registerLocation("build")
                .downloadLocations();

        Bukkit.getServer().getConsoleSender().sendMessage(MainPrefix + "§aBuild-System witd initiiert");
        buildSystem = new BuildSystem(false, BuildSystem.BuildEvent.BLOCK_BREAK, BuildSystem.BuildEvent.BLOCK_PLACE);

        getServer().getConsoleSender().sendMessage(MainPrefix + "§aBefehle und Events werden registriert...");
        registerEvents();

        Bukkit.getServer().getConsoleSender().sendMessage(MainPrefix + "§aVersion §f" + this.getDescription().getVersion() + "§a wurde aktiviert...");

        for (CorePlayer p : CoreSystem.getOnlineCorePlayers()) {
            p.getScoreboard().setNewObjective(new Objective());
            PlayerJoin.setJoinItems(p.bukkit());
        }
    }

    public void onDisable(){
        hologramManager.unsetHolograms();
        npcManager.unsetNPCs();
        Bukkit.getServer().getConsoleSender().sendMessage(MainPrefix + "§cPlugin wurde deaktiviert!");
    }

    private void registerEvents() {
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerInteract(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new CoinsChange(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerFish(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerMove(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerToggleFlight(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerDropItem(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerQuit(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new WeatherChange(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerPickupItem(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new WeatherChange(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new FoodLevelChange(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new EntitiyDamage(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new EntityDamageByEntity(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerDeath(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerInteractEntity(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerAchievementAwarded(), this);
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

    private void startScheduler() {
        Bukkit.getScheduler().runTaskTimer(getInstance(), Objective::updateLines, 50L, 100L);
    }

}
