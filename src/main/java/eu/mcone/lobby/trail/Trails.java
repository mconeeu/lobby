/*
 * Copyright (c) 2017 Dominik L., BamDev, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.trail;

public enum Trails {
    COOKIES("Cookie-Trail", "cookie"), GLOW("Glow-Trail", "glow"), ENDER("Ender-Trail", "ender"), MUSIC("Musik-Trail", "musik"), HEART("Herzen-Trail", "herzen"), LAVA("Lava-Trail", "lava"), SNOW("Schnee-Trail", "schnee"), WATER("Wasser-Trail", "wasser");

    private String name;
    private String perm;

    private Trails(String name, String permission) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getPerm() {
        return perm;
    }
}
