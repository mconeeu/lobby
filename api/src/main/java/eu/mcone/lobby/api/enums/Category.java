/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.api.enums;

import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

@Getter
public enum Category {

    STORY_ITEMS("Story-Items", "", new ItemBuilder(Material.BOOK, 1, 0).displayName("§c§lStory-Items").lore("§7§oHier findest Du alle Items,", "§7§odie Du für die Story benötigst", "", "§8» §f§nLinksklick§8 | §7§oÖffnen").create()),
    MATERIAL("Materialien", "", new ItemBuilder(Material.DIAMOND, 1, 0).displayName("§9§lMaterial").lore("§7§oHier befindet sich deine Materialien", "§7§ozum Items herstellen", "", "§8» §f§nLinksklick§8 | §7§oÖffnen").create()),
    ARMOR("Rüstung", "", new ItemBuilder(Material.DIAMOND_CHESTPLATE, 1).displayName("§d§lRüstung").lore("§7§oHier befindet sich deine Rüstung", "§7§ofür deine Gang Kämpfe", "", "§8» §f§nLinksklick§8 | §7§oÖffnen").unbreakable(true).itemFlags(ItemFlag.HIDE_ATTRIBUTES).create());

    private String name, description;
    private ItemStack item;

    Category(String name, String description, ItemStack item) {
        this.name = name;
        this.description = description;
        this.item = item;
    }

}
