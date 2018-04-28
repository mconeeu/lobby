/*
 * Copyright (c) 2017 - 2018 Dominik Lippl, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.hologram.HologramManager;
import eu.mcone.coresystem.api.bukkit.npc.NpcManager;
import eu.mcone.coresystem.api.bukkit.player.BukkitCorePlayer;
import eu.mcone.coresystem.api.bukkit.world.BuildSystem;
import eu.mcone.coresystem.api.bukkit.world.LocationManager;
import eu.mcone.coresystem.api.core.mysql.MySQL_Config;
import eu.mcone.lobby.api.Lobby;
import eu.mcone.lobby.listener.*;
import eu.mcone.lobby.util.Objective;
import lombok.Getter;
import org.bukkit.Bukkit;

public class LobbyPlugin extends Lobby {

    private static String MainPrefix = "§8[§3Lobby§8] ";
    @Getter
    private MySQL_Config conf;

    @Getter
    private HologramManager hologramManager;
    @Getter
    private NpcManager npcManager;
    @Getter
    private BuildSystem buildSystem;
    @Getter
    private LocationManager locationManager;

    @Override
    public void onEnable() {
        setInstance(this);

        Bukkit.getWorld("Lobby").setAnimalSpawnLimit(0);
        Bukkit.getWorld("Lobby").setMonsterSpawnLimit(0);

        Bukkit.getServer().getConsoleSender().sendMessage(MainPrefix + "§aMySQL Config wird initiiert");
        conf = new MySQL_Config(CoreSystem.getInstance().getMySQL(3), "Lobby", 800);
        registerMySQLConfig();

        Bukkit.getServer().getConsoleSender().sendMessage(MainPrefix + "§aScoreboard-Scheduler wird gestartet");
        startScheduler();

        Bukkit.getServer().getConsoleSender().sendMessage(MainPrefix + "§aHologram-Manager wird gestartet");
        hologramManager = CoreSystem.getInstance().inititaliseHologramManager("Lobby");

        Bukkit.getServer().getConsoleSender().sendMessage(MainPrefix + "§aNPC-Manager wird gestartet");
        npcManager = CoreSystem.getInstance().initialiseNpcManager("Lobby");

        Bukkit.getServer().getConsoleSender().sendMessage(MainPrefix + "§aLocationManager witd initiiert");
        locationManager = CoreSystem.getInstance().initialiseLocationManager("Lobby")
                .registerLocation("bedwars")
                .registerLocation("skypvp")
                .registerLocation("knockit")
                .registerLocation("minewar")
                .registerLocation("build")
                .downloadLocations();

        Bukkit.getServer().getConsoleSender().sendMessage(MainPrefix + "§aBuild-System witd initiiert");
        buildSystem = CoreSystem.getInstance().initialiseBuildSystem(true, BuildSystem.BuildEvent.BLOCK_BREAK, BuildSystem.BuildEvent.BLOCK_PLACE);

        getServer().getConsoleSender().sendMessage(MainPrefix + "§aBefehle und Events werden registriert...");
        registerEvents();

        Bukkit.getServer().getConsoleSender().sendMessage(MainPrefix + "§aVersion §f" + this.getDescription().getVersion() + "§a wurde aktiviert...");

        for (BukkitCorePlayer p : CoreSystem.getInstance().getOnlineCorePlayers()) {
            p.getScoreboard().setNewObjective(new Objective());
            PlayerJoin.setJoinItems(p.bukkit());
        }
    }

    @Override
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
        Bukkit.getServer().getPluginManager().registerEvents(new WeatherChange(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new InventoryClick(), this);
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
        conf.createTable();

        //System
        conf.insertMySQLConfig("System-Prefix", "&8[&7&l!&8]&3 Lobby &8» &7");
        conf.insertMySQLConfig("System-WorldName", "Lobby");

        //Locations
        conf.insertMySQLConfig("Location-Spawn", "[\"\",\"\",\"\",\"\",\"\",\"\"]");
        conf.insertMySQLConfig("Location-Navigator-1", "[\"\",\"\",\"\",\"\",\"\",\"\"]");
        conf.insertMySQLConfig("Location-Navigator-2", "[\"\",\"\",\"\",\"\",\"\",\"\"]");
        conf.insertMySQLConfig("Location-Navigator-3", "[\"\",\"\",\"\",\"\",\"\",\"\"]");
        conf.insertMySQLConfig("Location-Navigator-4", "[\"\",\"\",\"\",\"\",\"\",\"\"]");
        conf.insertMySQLConfig("Location-Navigator-5", "[\"\",\"\",\"\",\"\",\"\",\"\"]");
        conf.insertMySQLConfig("Location-Navigator-6", "[\"\",\"\",\"\",\"\",\"\",\"\"]");
        conf.insertMySQLConfig("Location-Navigator-7", "[\"\",\"\",\"\",\"\",\"\",\"\"]");
        /*Location 8 ist der Spawn*/
        conf.insertMySQLConfig("Location-Navigator-9", "[\"\",\"\",\"\",\"\",\"\",\"\"]");

        //Lobby compass
        conf.insertMySQLConfig("Navigator-1","{\"name\":\"§8//§oMCONE§8//\",\"ItemID\":160,\"Lore\":[]}");
        conf.insertMySQLConfig("Navigator-2","{\"name\":\"&c&lBedwars\",\"ItemID\":355,\"Lore\":[\"&7&oTöte deine Gegner nachdem du ihre Betten abgebaut hast\",\"&7&oum zu gewinnen\",\"\",\"&8» &f&nRechtsklick&8 | &7&oTeleportieren\"]}");
        conf.insertMySQLConfig("Navigator-3","{\"name\":\"§8//§oMCONE§8//\",\"ItemID\":160,\"Lore\":[]}");
        conf.insertMySQLConfig("Navigator-4","{\"name\":\"&9&lSykPvP\",\"ItemID\":276,\"Lore\":[\"&7&oFinde deine Gegner auf einer Sky-Map und Töte sie\",\"&7um Coins zu erhalten\",\"\",\"&8» &f&nRechtsklick&8 | &7&oTeleportieren\"]}");
        conf.insertMySQLConfig("Navigator-5","{\"name\":\"&2&lMinewar\",\"ItemID\":257,\"Lore\":[\"&7&oGrabe dich unter der Erde zu deinen Gegner und rüste\",\"&7dich aus um sie zu töten und zu gewinnen.\",\"\",\"&8» &f&nRechtsklick&8 | &7&oTeleportieren\"]}");
        conf.insertMySQLConfig("Navigator-6","{\"name\":\"&e&lKnockback-FFA\",\"ItemID\":346,\"Lore\":[\"&7&oSchlage die Gegner von der Plattform um Coins\",\"&7zu erhalten\",\"\",\"&8» &f&nRechtsklick&8 | &7&oTeleportieren\"]}");
        conf.insertMySQLConfig("Navigator-7","{\"name\":\"&7Comming Soon\",\"ItemID\":166,\"Lore\":[\"&8&oAn diesem Spielmodus arbeiten wir noch...\"]}");
        conf.insertMySQLConfig("Navigator-8","{\"name\":\"&f&lSpawn\",\"ItemID\":399,\"Lore\":[\"&7&oZurück zum Lobby Spawn.\",\"&7&oHier startet unser Lobby Rätsel\",\"\",\"&8» &f&nRechtsklick&8 | &7&oTeleportieren\"]}");
        conf.insertMySQLConfig("Navigator-9","{\"name\":\"&e&lBuild\",\"ItemID\":2,\"Lore\":[\"&7&oCreative Server. überzeuge uns von deinen Baukünsten\",\"&7&ound werde Builder im MC ONE Team!\",\"\",\"&8» &f&nRechtsklick&8 | &7&oTeleportieren\"]}");

        //Trails
        conf.insertMySQLConfig("Trail-Coins-Cookies", 10);
        conf.insertMySQLConfig("Trail-Coins-Glow", 10);
        conf.insertMySQLConfig("Trail-Coins-Ender", 10);
        conf.insertMySQLConfig("Trail-Coins-Music", 10);
        conf.insertMySQLConfig("Trail-Coins-Heart", 10);
        conf.insertMySQLConfig("Trail-Coins-Lava", 10);
        conf.insertMySQLConfig("Trail-Coins-Snow", 10);
        conf.insertMySQLConfig("Trail-Coins-Water", 10);

        //store
        conf.store();
    }

    private void startScheduler() {
        Bukkit.getScheduler().runTaskTimer(this, Objective::updateLines, 50L, 100L);
    }

}
