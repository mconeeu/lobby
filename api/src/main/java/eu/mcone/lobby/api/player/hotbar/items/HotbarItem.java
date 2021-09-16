/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.api.player.hotbar.items;

import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.lobby.api.player.hotbar.HotbarCategory;
import lombok.Getter;
import org.bukkit.Material;

@Getter
public enum HotbarItem {

    /*   Compass     */
    COMPASS(new ItemBuilder(Material.COMPASS), HotbarCategory.NAVIGATOR, true),
    BEACON(new ItemBuilder(Material.BEACON), HotbarCategory.NAVIGATOR, false),
    SHARP(new ItemBuilder(Material.PRISMARINE_SHARD), HotbarCategory.NAVIGATOR, false),
    BONE_MEAL(new ItemBuilder(Material.BONE), HotbarCategory.NAVIGATOR, false),
    STICK(new ItemBuilder(Material.STICK), HotbarCategory.NAVIGATOR, false),
    NETHER_BRICK(new ItemBuilder(Material.NETHER_BRICK), HotbarCategory.NAVIGATOR, false),
    DIAMOND(new ItemBuilder(Material.DIAMOND), HotbarCategory.NAVIGATOR, false),
    SUGAR(new ItemBuilder(Material.SUGAR), HotbarCategory.NAVIGATOR, false),
    MUSIC_DISK(new ItemBuilder(Material.RECORD_5), HotbarCategory.NAVIGATOR, false),
    RABBITS_FEET(new ItemBuilder(Material.RABBIT_FOOT), HotbarCategory.NAVIGATOR, false),
    FIREWORK_CHARGE(new ItemBuilder(Material.FIREWORK_CHARGE), HotbarCategory.NAVIGATOR, false),
    IRON_SWORD(new ItemBuilder(Material.IRON_SWORD), HotbarCategory.NAVIGATOR, false),

    /*   CHOOSER     */
    NETHER_STAR(new ItemBuilder(Material.NETHER_STAR), HotbarCategory.LOBBY_CHANGER, true),
    STRING(new ItemBuilder(Material.STRING), HotbarCategory.LOBBY_CHANGER, false),
    BLAZE_ROD(new ItemBuilder(Material.BLAZE_ROD), HotbarCategory.LOBBY_CHANGER, false),
    FISH(new ItemBuilder(Material.INK_SACK), HotbarCategory.LOBBY_CHANGER, false),
    GOLDEN_NUGGET(new ItemBuilder(Material.GOLD_NUGGET), HotbarCategory.LOBBY_CHANGER, false),
    RABBIT_HIDE(new ItemBuilder(Material.RABBIT_HIDE), HotbarCategory.LOBBY_CHANGER, false),
    ANVIL(new ItemBuilder(Material.ANVIL), HotbarCategory.LOBBY_CHANGER, false),
    COOKIE(new ItemBuilder(Material.COOKIE), HotbarCategory.LOBBY_CHANGER, false),
    POTION(new ItemBuilder(Material.POTION), HotbarCategory.LOBBY_CHANGER, false),
    REDSTONE_COMPERATOR(new ItemBuilder(Material.REDSTONE_COMPARATOR), HotbarCategory.LOBBY_CHANGER, false),
    COOKED_RABBIT(new ItemBuilder(Material.COOKED_RABBIT), HotbarCategory.LOBBY_CHANGER, false),

    /*   BACKPACK     */
    MINECART_CHEST(new ItemBuilder(Material.STORAGE_MINECART), HotbarCategory.BACKPACK, true),
    CHEST(new ItemBuilder(Material.CHEST), HotbarCategory.BACKPACK, false),
    END_PORTAL(new ItemBuilder(Material.ENDER_PORTAL_FRAME), HotbarCategory.BACKPACK, false),
    DROPPER(new ItemBuilder(Material.DROPPER), HotbarCategory.BACKPACK, false),
    STICKY_PISTON(new ItemBuilder(Material.PISTON_STICKY_BASE), HotbarCategory.BACKPACK, false),
    CACTUS(new ItemBuilder(Material.CACTUS), HotbarCategory.BACKPACK, false),
    FURNACE(new ItemBuilder(Material.FURNACE), HotbarCategory.BACKPACK, false),
    BREWING_STAND(new ItemBuilder(Material.BREWING_STAND_ITEM), HotbarCategory.BACKPACK, false),
    CAULDRON(new ItemBuilder(Material.CAULDRON_ITEM), HotbarCategory.BACKPACK, false),
    MINECART_HOPPER(new ItemBuilder(Material.HOPPER_MINECART), HotbarCategory.BACKPACK, false),
    MINECART_TNT(new ItemBuilder(Material.EXPLOSIVE_MINECART), HotbarCategory.BACKPACK, false),
    HOPPER(new ItemBuilder(Material.HOPPER), HotbarCategory.BACKPACK, false),
    MINECART(new ItemBuilder(Material.MINECART), HotbarCategory.BACKPACK, false),
    ENDER_CHEST(new ItemBuilder(Material.ENDER_CHEST), HotbarCategory.BACKPACK, false),

    /* PROFILE */
    SKULL(new ItemBuilder(Material.SKULL_ITEM), HotbarCategory.PROFILE, true),
    BOOK(new ItemBuilder(Material.BOOK_AND_QUILL), HotbarCategory.PROFILE, false),
    PAPER(new ItemBuilder(Material.PAPER), HotbarCategory.PROFILE, false),
    RAW_FISH(new ItemBuilder(Material.RAW_FISH), HotbarCategory.PROFILE, false),
    FLOWER(new ItemBuilder(Material.YELLOW_FLOWER), HotbarCategory.PROFILE, false),
    SLIME_BALL(new ItemBuilder(Material.SLIME_BALL), HotbarCategory.PROFILE, true);

    private final ItemBuilder item;
    private final HotbarCategory categorys;
    private final boolean mainItem;


    HotbarItem(ItemBuilder itemBuilder, HotbarCategory categorys, Boolean mainItem) {
        this.item = itemBuilder;
        this.categorys = categorys;
        this.mainItem = mainItem;
    }
}
