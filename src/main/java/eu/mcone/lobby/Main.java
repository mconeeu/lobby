/*
 * Copyright (c) 2017 Dominik L., BamDev, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby;

import eu.mcone.lobby.block.*;
import eu.mcone.lobby.commands.holo_CMD;
import eu.mcone.lobby.commands.set_CMD;
import eu.mcone.lobby.inventar.*;
import eu.mcone.lobby.scoreboard.ScoreboardManager;
import eu.mcone.lobby.mysql.MySQL;
import eu.mcone.lobby.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main extends JavaPlugin {

    MySQL mc1stats = new MySQL("mysql.mcone.eu", 3306, "mc1system_stats", "mc1system_stats", "qN8FQK.hj)_Lat?uK)-#6F-$3![t;2E6KZ$sb+Am3g!VHRDe&w$nQX)5}VKb@-@[}e");

    private static Main plugin;
    public static Main instance;
    public static World world;

    public static ArrayList<Player> HideShow = new ArrayList();
    public static ArrayList<Player> emerald = new ArrayList();
    public static ArrayList<Player> lava = new ArrayList();
    public static ArrayList<Player> cloud = new ArrayList();
    public static ArrayList<Player> water = new ArrayList();

    public static String prefix = "§8[§7§l!§8] §fLobby §8» §7 ";
    public static String noperm = prefix + "§cDu hast keine Rechte dafür§8.";
    public static File f = new File("plugins/McOne-Lobby", "config.yml");
    public static FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
    public static HashMap<Player, String> partikel = new HashMap();

    
    public void onEnable() {
//        mc1stats.connect();
        plugin = this;
        trail();
        ScoreboardManager.UpdateScoreboardScheduler();

        setupConfig();

        Bukkit.getServer().getPluginManager().registerEvents(new AchievmentListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new AntiLabymod(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new BuildListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new DamageListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new FeedListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new ItemListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new WeatherListener(), this);

        Bukkit.getServer().getPluginManager().registerEvents(new InteractListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new onCompass(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new onCompassClick(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new onFishingRod(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new onHats(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new onProfile(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new onProfileClick(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new onTrails(), this);

        Bukkit.getServer().getPluginManager().registerEvents(new DoubleJump(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new JoinListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new JumpPad(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new QuitListener(), this);

        getCommand("set").setExecutor(new set_CMD());
        getCommand("setholo").setExecutor(new holo_CMD());

        Bukkit.getServer().getConsoleSender().sendMessage("§7------------------------");
        Bukkit.getServer().getConsoleSender().sendMessage(prefix + "§aPlugin wurde gestartet!");
        Bukkit.getServer().getConsoleSender().sendMessage(prefix + "§aPlugin developed by: BamDev");
        Bukkit.getServer().getConsoleSender().sendMessage(prefix + "§aFür: McOne.eu");
        Bukkit.getServer().getConsoleSender().sendMessage(prefix + "§aAlle Rechte liegen bei McOne.eu");
        Bukkit.getServer().getConsoleSender().sendMessage("§7------------------------");
    }

    public void onDisable(){
        mc1stats.close();
        Bukkit.getServer().getConsoleSender().sendMessage("§7------------------------");
        Bukkit.getServer().getConsoleSender().sendMessage(prefix + "§aPlugin wurde gestopt!");
        Bukkit.getServer().getConsoleSender().sendMessage(prefix + "§aPlugin developed by: BamDev");
        Bukkit.getServer().getConsoleSender().sendMessage(prefix + "§aFür: McOne.eu");
        Bukkit.getServer().getConsoleSender().sendMessage(prefix + "§aAlle Rechte liegen bei McOne.eu");
        Bukkit.getServer().getConsoleSender().sendMessage("§7------------------------");
    }

    private void setupConfig() {
        if (!f.exists()) {
            try{
                f.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        getConfig().options().header(""
                + "Plugin Entwickler: xXTwinsterHDXx"
                + "\nPlugin Version: 2.0");
        getConfig().options().copyDefaults(true);

        getConfig().addDefault("MySQL-StatsAPI-KnockbackFFA","McOne_KnockbackFFA_Stats");
        getConfig().addDefault("MySQL-StatsAPI-Minewar","McOne_Minewar_Stats");
        getConfig().addDefault("MySQL-StatsAPI-Bedwars","McOne_Bedwars_Stats");
        getConfig().addDefault("MySQL-StatsAPI-Skypvp","McOne_SkyPvP_Stats");

        getConfig().addDefault("Lobby-WorldName","world");

        getConfig().addDefault("Lobby-Navigator-1-Name","§r");
        getConfig().addDefault("Lobby-Navigator-1-ItemID", Integer.valueOf(160));
        getConfig().addDefault("Lobby-Navigator-1-Lore-1","");
        getConfig().addDefault("Lobby-Navigator-1-Lore-2","");
        getConfig().addDefault("Lobby-Navigator-1-Lore-3","");
        getConfig().addDefault("Lobby-Navigator-1-Lore-4","");


        getConfig().addDefault("Lobby-Navigator-2-Name","&c&lBedwars");
        getConfig().addDefault("Lobby-Navigator-2-ItemID", Integer.valueOf(355));
        getConfig().addDefault("Lobby-Navigator-2-Lore-1","&8>> &7&oTöte deine Gegner nachdem du ihre Betten abgebaut hast");
        getConfig().addDefault("Lobby-Navigator-2-Lore-2","&7&oum zu gewinnen");
        getConfig().addDefault("Lobby-Navigator-2-Lore-3","");
        getConfig().addDefault("Lobby-Navigator-2-Lore-4","&8>> &f&nRechtsklick&8 | &7&oTeleportieren");

        getConfig().addDefault("Lobby-Navigator-3-Name","§r");
        getConfig().addDefault("Lobby-Navigator-3-ItemID", Integer.valueOf(160));
        getConfig().addDefault("Lobby-Navigator-3-Lore-1","");
        getConfig().addDefault("Lobby-Navigator-3-Lore-2","");
        getConfig().addDefault("Lobby-Navigator-3-Lore-3","");
        getConfig().addDefault("Lobby-Navigator-3-Lore-4","");

        getConfig().addDefault("Lobby-Navigator-4-Name","&9&lSykPvP");
        getConfig().addDefault("Lobby-Navigator-4-ItemID", Integer.valueOf(276));
        getConfig().addDefault("Lobby-Navigator-4-Lore-1","&8>> &7&oFinde deine Gegner auf einer Sky-Map und Töte sie");
        getConfig().addDefault("Lobby-Navigator-4-Lore-2","&7um Coins zu erhalten");
        getConfig().addDefault("Lobby-Navigator-4-Lore-3","");
        getConfig().addDefault("Lobby-Navigator-4-Lore-4","&8>> &f&nRechtsklick&8 | &7&oTeleportieren");

        getConfig().addDefault("Lobby-Navigator-5-Name","&2&lMinewar");
        getConfig().addDefault("Lobby-Navigator-5-ItemID", Integer.valueOf(257));
        getConfig().addDefault("Lobby-Navigator-5-Lore-1","&8>> &7&oGrabe dich unter der Erde zu deinen Gegner und rüste");
        getConfig().addDefault("Lobby-Navigator-5-Lore-2","&7dich aus um sie zu töten und zu gewinnen.");
        getConfig().addDefault("Lobby-Navigator-5-Lore-3","");
        getConfig().addDefault("Lobby-Navigator-5-Lore-4","&8>> &f&nRechtsklick&8 | &7&oTeleportieren");

        getConfig().addDefault("Lobby-Navigator-6-Name","&e&lKnockback-FFA");
        getConfig().addDefault("Lobby-Navigator-6-ItemID", Integer.valueOf(346));
        getConfig().addDefault("Lobby-Navigator-6-Lore-1","&8>> &7&oSchlage die Gegner von der Plattform um Coins");
        getConfig().addDefault("Lobby-Navigator-6-Lore-2","&7zu erhalten");
        getConfig().addDefault("Lobby-Navigator-6-Lore-3","");
        getConfig().addDefault("Lobby-Navigator-6-Lore-4","&8>> &f&nRechtsklick&8 | &7&oTeleportieren");

        getConfig().addDefault("Lobby-Navigator-7-Name","&8Comming Soon");
        getConfig().addDefault("Lobby-Navigator-7-ItemID", Integer.valueOf(166));
        getConfig().addDefault("Lobby-Navigator-7-Lore-1","");
        getConfig().addDefault("Lobby-Navigator-7-Lore-2","&8&oAn diesem Spielmodus arbeiten wir noch...");
        getConfig().addDefault("Lobby-Navigator-7-Lore-3","&8--");
        getConfig().addDefault("Lobby-Navigator-7-Lore-4","");

        getConfig().addDefault("Lobby-Navigator-8-Name","&f&lSpawn");
        getConfig().addDefault("Lobby-Navigator-8-ItemID", Integer.valueOf(399));
        getConfig().addDefault("Lobby-Navigator-8-Lore-1","&8>> &7&oZurück zum Lobby Spawn.");
        getConfig().addDefault("Lobby-Navigator-8-Lore-2","&7&oHier startet unser Lobby Rätsel");
        getConfig().addDefault("Lobby-Navigator-8-Lore-3","");
        getConfig().addDefault("Lobby-Navigator-8-Lore-4","&8>> &f&nRechtsklick&8 | &7&oTeleportieren");

        getConfig().addDefault("Lobby-Navigator-9-Name","&e&lBuild");
        getConfig().addDefault("Lobby-Navigator-9-ItemID", Integer.valueOf(2));
        getConfig().addDefault("Lobby-Navigator-9-Lore-1","&8>> &7&oCreative Server. überzeuge uns von deinen Baukünsten");
        getConfig().addDefault("Lobby-Navigator-9-Lore-2","&7&ound werde Builder im MC ONE Team!");
        getConfig().addDefault("Lobby-Navigator-9-Lore-3","");
        getConfig().addDefault("Lobby-Navigator-9-Lore-4","&8>> &f&nRechtsklick&8 | &7&oTeleportieren");

        getConfig().addDefault("Lobby-Tablist-Spielverderber","&8[&7SV&8] &8");
        getConfig().addDefault("Lobby-Tablist-Spielverderber","&8[&7SV&8] &8");

        getConfig().addDefault("Lobby-Tablist-Spielverderber","&8[&7SV&8] &8");
        getConfig().addDefault("Lobby-Tablist-Spieler","&8[&fS&8] &7");
        getConfig().addDefault("Lobby-Tablist-Premium","&8[&6P&8] &7");
        getConfig().addDefault("Lobby-Tablist-Premium+","&8[&6P+&8] &7");
        getConfig().addDefault("Lobby-Tablist-YouTuber","&8[&5YT&8] &7");
        getConfig().addDefault("Lobby-Tablist-Team","&7[c&C&7] &4");
        getConfig().addDefault("Lobby-Tablist-Supporter","&8[&aSup&8] &7");
        getConfig().addDefault("Lobby-Tablist-Moderator","&8[&2Mod&8] &7");
        getConfig().addDefault("Lobby-Tablist-SrModerator","&8[&2SrMod&8] &7");
        getConfig().addDefault("Lobby-Tablist-JrSupporter","&8[&aJrSup&8] &7");
        getConfig().addDefault("Lobby-Tablist-Builder","&8[&eB&8] &7");
        getConfig().addDefault("Lobby-Tablist-Developer","&8[&bDev&8] &7");
        getConfig().addDefault("Lobby-Tablist-Admin","&8[&cA&8] &7");

        saveConfig();
    }

    public void loadConfig(){
        FileConfiguration cfg = getConfig();
        cfg.options().copyDefaults(true);

        saveConfig();
    }

    private void trail(){
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {

            public void run() {
                for(Entity ent : Bukkit.getWorld(Bukkit.getWorld(Main.cfg.getString("Lobby-WorldName")).getName()).getEntities()) {
                    //if(ent.getType().valueOf("FISHING_HOOK") != null) {
                    if(ent instanceof Player) {

                    } else {
                        ent.remove();
                    }
                }
            }
        }, 20, 20);

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            ItemStack i = new ItemStack(Material.COOKIE);
            public void run() {
                for(Player all : Bukkit.getOnlinePlayers()){
                    if(Var.cookies.contains(all)){
                        all.getWorld().dropItem(all.getLocation(), i);
                    }
                }
            }
        }, 20, 2);

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            ItemStack i = new ItemStack(Material.GOLD_INGOT);
            ItemStack i2 = new ItemStack(Material.BLAZE_ROD);
            public void run() {
                for(Player all : Bukkit.getOnlinePlayers()){
                    if(Var.glow.contains(all)){
                        all.getWorld().dropItem(all.getLocation(), i);
                        all.getWorld().dropItem(all.getLocation(), i2);
                    }
                }
            }
        }, 10, 2);

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            ItemStack i = new ItemStack(Material.ENDER_PEARL);
            ItemStack i2 = new ItemStack(Material.EYE_OF_ENDER);
            public void run() {
                for(Player all : Bukkit.getOnlinePlayers()){
                    if(Var.ender.contains(all)){
                        all.getWorld().dropItem(all.getLocation(), i);
                        all.getWorld().dropItem(all.getLocation(), i2);
                    }
                }
            }
        }, 10, 2);

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            ItemStack i = new ItemStack(Material.JUKEBOX);
            ItemStack i2 = new ItemStack(Material.RECORD_10);
            ItemStack i3 = new ItemStack(Material.RECORD_6);
            public void run() {
                for(Player all : Bukkit.getOnlinePlayers()){
                    if(Var.musik.contains(all)){
                        all.getWorld().dropItem(all.getLocation(), i);
                        all.getWorld().dropItem(all.getLocation(), i2);
                        all.getWorld().dropItem(all.getLocation(), i3);
                    }
                }
            }
        }, 10, 2);

        /**
         * Effecs
         */

        //HeartTrail
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            public void run() {
                for(Player all : Bukkit.getOnlinePlayers()){
                    if(Var.heart.contains(all)){
                        all.getWorld().playEffect(all.getLocation(), Effect.HEART, 5);
                        all.getWorld().playEffect(all.getLocation(), Effect.HEART, 5);
                        all.getWorld().playEffect(all.getLocation(), Effect.SPELL, 5);
                    }
                }
            }
        }, 10, 2);

        //LavaTrail
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            public void run() {
                for(Player all : Bukkit.getOnlinePlayers()){
                    if(Var.lava.contains(all)){
                        all.getWorld().playEffect(all.getLocation(), Effect.LAVA_POP, 5);
                        all.getWorld().playEffect(all.getLocation(), Effect.LAVA_POP, 5);
                        all.getWorld().playEffect(all.getLocation(), Effect.LARGE_SMOKE, 5);
                    }
                }
            }
        }, 10, 2);

        //WaterTrail
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            public void run() {
                for(Player all : Bukkit.getOnlinePlayers()){
                    if(Var.wasser.contains(all)){
                        all.getWorld().playEffect(all.getLocation(), Effect.SPLASH, 5);
                        all.getWorld().playEffect(all.getLocation(), Effect.SPLASH, 5);
                        all.getWorld().playEffect(all.getLocation(), Effect.WATERDRIP, 5);
                        all.getWorld().playEffect(all.getLocation(), Effect.WATERDRIP, 5);
                        all.getWorld().playEffect(all.getLocation(), Effect.WATERDRIP, 5);
                    }
                }
            }
        }, 10, 2);

        //SnowTrail
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            public void run() {
                for(Player all : Bukkit.getOnlinePlayers()){
                    if(Var.snow.contains(all)){
                        all.getWorld().playEffect(all.getLocation(), Effect.SNOW_SHOVEL, 2);
                        all.getWorld().playEffect(all.getLocation(), Effect.SNOW_SHOVEL, 2);
                        all.getWorld().playEffect(all.getLocation(), Effect.SNOW_SHOVEL, 2);
                        all.getWorld().playEffect(all.getLocation(), Effect.SNOWBALL_BREAK, 10);
                        all.getWorld().playEffect(all.getLocation(), Effect.SNOWBALL_BREAK, 10);
                        all.getWorld().playEffect(all.getLocation(), Effect.SNOWBALL_BREAK, 10);
                        all.getWorld().playEffect(all.getLocation(), Effect.SNOWBALL_BREAK, 10);
                        all.getWorld().playEffect(all.getLocation(), Effect.SNOWBALL_BREAK, 10);
                    }
                }
            }
        }, 10, 2);
    }

    public static Main getPlugin(){

        return plugin;
    }

    public static Main getInstance() {
        return Main.plugin;
    }
}
