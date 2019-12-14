/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.api.player;

import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import lombok.Getter;
import org.bukkit.Material;

@Getter
public enum SpawnPoint {

    SPAWN(new ItemBuilder(Material.GRASS).displayName("§f§lSpawn").lore("§7§oSpawne am Lobby Spawn")),
    LAST_LOCATION(new ItemBuilder(Material.ENDER_PEARL).displayName("§a§lLetzter Ort").lore("§7§oSpawne bei deinem","§7§oletzten Logout Ort")),
    OFFICE(new ItemBuilder(Material.BOOK_AND_QUILL).displayName("§c§lBüro").lore("§7§oSpawne in deinem Büro"));

    private ItemBuilder item;

    SpawnPoint(ItemBuilder item) {
        this.item = item;
    }

}
