/*
 * Copyright (c) 2017 - 2018 Dominik L., Rufus Maiwald, BamDev and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.trail;

import eu.mcone.lobby.Lobby;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum Trail {
    COOKIES(0, "§5§lCookie-Trail", "lobby.trail.cookie", Lobby.config.getIntConfigValue("Trail-Coins-Cookies") , Material.COOKIE, loc -> {
        loc.getWorld().dropItem(loc, new ItemStack(Material.COOKIE));
    }),
    GLOW(1, "§6§lGlow-Trail", "lobby.trail.glow", Lobby.config.getIntConfigValue("Trail-Coins-Glow"), Material.GOLD_INGOT, loc -> {
        loc.getWorld().dropItem(loc, new ItemStack(Material.GOLD_INGOT));
        loc.getWorld().dropItem(loc, new ItemStack(Material.BLAZE_ROD));
    }),
    ENDER(2, "§5§lEnder-Trail", "lobby.trail.ender", Lobby.config.getIntConfigValue("Trail-Coins-Ender"), Material.ENDER_PEARL, loc -> {
        loc.getWorld().dropItem(loc, new ItemStack(Material.ENDER_PEARL));
        loc.getWorld().dropItem(loc, new ItemStack(Material.EYE_OF_ENDER));
    }),
    MUSIC(3, "§a§lMusik-Trail", "lobby.trail.musik", Lobby.config.getIntConfigValue("Trail-Coins-Music"), Material.JUKEBOX, loc -> {
        loc.getWorld().dropItem(loc, new ItemStack(Material.JUKEBOX));
        loc.getWorld().dropItem(loc, new ItemStack(Material.RECORD_10));
        loc.getWorld().dropItem(loc, new ItemStack(Material.RECORD_6));
    }),
    HEART(4, "§a§lHerzen-Trail", "lobby.trail.herzen", Lobby.config.getIntConfigValue("Trail-Coins-Heart"), Material.REDSTONE, loc -> {
        loc.getWorld().playEffect(loc, Effect.HEART, 5);
        loc.getWorld().playEffect(loc, Effect.HEART, 5);
        loc.getWorld().playEffect(loc, Effect.SPELL, 5);
    }),
    LAVA(5, "§c§lLava-Trail", "lobby.trail.lava", Lobby.config.getIntConfigValue("Trail-Coins-Lava"), Material.LAVA_BUCKET, loc -> {
        loc.getWorld().playEffect(loc, Effect.LAVA_POP, 5);
        loc.getWorld().playEffect(loc, Effect.LAVA_POP, 5);
        loc.getWorld().playEffect(loc, Effect.LARGE_SMOKE, 5);
    }),
    SNOW(6, "§f§lSchnee-Trail", "lobby.trail.schnee", Lobby.config.getIntConfigValue("Trail-Coins-Snow"), Material.SNOW_BALL, loc -> {
        loc.getWorld().playEffect(loc, Effect.SNOW_SHOVEL, 2);
        loc.getWorld().playEffect(loc, Effect.SNOW_SHOVEL, 2);
        loc.getWorld().playEffect(loc, Effect.SNOW_SHOVEL, 2);
        loc.getWorld().playEffect(loc, Effect.SNOWBALL_BREAK, 10);
        loc.getWorld().playEffect(loc, Effect.SNOWBALL_BREAK, 10);
        loc.getWorld().playEffect(loc, Effect.SNOWBALL_BREAK, 10);
        loc.getWorld().playEffect(loc, Effect.SNOWBALL_BREAK, 10);
        loc.getWorld().playEffect(loc, Effect.SNOWBALL_BREAK, 10);
    }),
    WATER(7, "§9§lWasser-Trail", "lobby.trail.wasser", Lobby.config.getIntConfigValue("Trail-Coins-Water"), Material.WATER_BUCKET, loc -> {
        loc.getWorld().playEffect(loc, Effect.SPLASH, 5);
        loc.getWorld().playEffect(loc, Effect.SPLASH, 5);
        loc.getWorld().playEffect(loc, Effect.WATERDRIP, 5);
        loc.getWorld().playEffect(loc, Effect.WATERDRIP, 5);
        loc.getWorld().playEffect(loc, Effect.WATERDRIP, 5);
    });

    private int id, coins;
    private String name, perm;
    private Material item;
    private TrailEffect effect;

    Trail(int id, String name, String perm, int coins, Material item, TrailEffect effect) {
        this.id = id;
        this.name = name;
        this.perm = perm;
        this.coins = coins;
        this.item = item;
        this.effect = effect;
    }

    public static Trail getTrailbyID(int id) {
        for (Trail t : values()) {
            if (t.id == id) {
                return t;
            }
        }

        return null;
    }

    public static Trail getTrailbyName(String name) {
        for (Trail t : values()) {
            if (t.name.equals(name)) {
                return t;
            }
        }

        return null;
    }

    public String getName() {
        return name;
    }

    public String getPerm() {
        return perm;
    }

    public int getCoins() {
        return coins;
    }

    public Material getItem() {
        return item;
    }

    public int getId() {
        return id;
    }

    public TrailEffect getEffect() {
        return effect;
    }
}
