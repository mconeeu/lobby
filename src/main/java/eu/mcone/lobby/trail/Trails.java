/*
 * Copyright (c) 2017 Dominik L., BamDev, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.trail;

import org.bukkit.Material;

public enum Trails {
    COOKIES(0, "§5§lCookie-Trail", "lobby.trail.cookie", Material.COOKIE),
    GLOW(1, "§6§lGlow-Trail", "lobby.trail.glow", Material.GOLD_INGOT),
    ENDER(2, "§5§lEnder-Trail", "lobby.trail.ender", Material.ENDER_PEARL),
    MUSIC(3, "§a§lMusik-Trail", "lobby.trail.musik", Material.JUKEBOX),
    HEART(4, "§a§lHerzen-Trail", "lobby.trail.herzen", Material.REDSTONE),
    LAVA(5, "§c§lLava-Trail", "lobby.trail.lava", Material.LAVA_BUCKET),
    SNOW(6, "§f§lSchnee-Trail", "lobby.trail.schnee", Material.SNOW_BALL),
    WATER(7, "§9§lWasser-Trail", "lobby.trail.wasser", Material.WATER_BUCKET);

    private int id;
    private String name;
    private String perm;
    private Material item;

    private Trails(int id, String name, String perm, Material item) {
        this.id = id;
        this.name = name;
        this.perm = perm;
        this.item = item;
    }

    public static Trails getTrailbyID(int id) {
        for (Trails t : values()) {
            if (t.id == id) {
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

    public Material getItem() {
        return item;
    }

    public int getId() {
        return id;
    }
}
