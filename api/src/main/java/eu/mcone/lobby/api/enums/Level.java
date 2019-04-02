/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.api.enums;

import lombok.Getter;
import org.bukkit.ChatColor;

public enum Level {

    USUAL("§7§lGewöhnlich", ChatColor.GRAY, 54, 8),
    UNUSUAL("§3§lUngewöhnlich", ChatColor.DARK_AQUA, 18, 3),
    EPIC("§5§lEpisch", ChatColor.LIGHT_PURPLE, 9, 10),
    LEGENDARY("§6§lLegendär", ChatColor.GOLD, 2, 1);

    @Getter
    private String displayname;
    @Getter
    private ChatColor chatColor;
    @Getter
    private int itemAmount, glasSubId;

    Level(String displayname, ChatColor chatcolor, int itemAmount, int glasSubID) {
        this.displayname = displayname;
        this.chatColor = chatcolor;
        this.itemAmount = itemAmount;
        this.glasSubId = glasSubID;
    }

}
