/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.api.player.settings;

import eu.mcone.coresystem.api.bukkit.inventory.settings.Option;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@Getter
public enum SpawnPoint implements Option {

    SPAWN(new ItemBuilder(Material.GRASS).displayName("§f§lSpawn").lore("§7§oSpawne am Lobby Spawn").create()),
    LAST_LOCATION(new ItemBuilder(Material.ENDER_PEARL).displayName("§a§lLetzter Ort").lore("§7§oSpawne bei deinem","§7§oletzten Logout Ort").create()),
    OFFICE(new ItemBuilder(Material.BOOK_AND_QUILL).displayName("§c§lBüro").lore("§7§oSpawne in deinem Büro").create());

    private final ItemStack item;

    SpawnPoint(ItemStack item) {
        this.item = item;
    }

}
