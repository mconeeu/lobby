package eu.mcone.lobby.api.player.hotbar.items;

import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.lobby.api.player.HotbarItem;
import eu.mcone.lobby.api.player.hotbar.HotbarGeneralCategorys;
import lombok.Getter;
import org.bukkit.Material;

@Getter
public enum HotbarItemEnum {

    /*   Compass     */
    COMPASS(new ItemBuilder(Material.COMPASS), HotbarGeneralCategorys.NAVIGATOR, true),
    BEACON(new ItemBuilder(Material.BEACON), HotbarGeneralCategorys.NAVIGATOR, false),
    SHARP(new ItemBuilder(Material.PRISMARINE_SHARD), HotbarGeneralCategorys.NAVIGATOR, false),
    BONE_MEAL(new ItemBuilder(Material.BONE), HotbarGeneralCategorys.NAVIGATOR, false),
    STICK(new ItemBuilder(Material.STICK), HotbarGeneralCategorys.NAVIGATOR, false),
    NETHER_BRICK(new ItemBuilder(Material.NETHER_BRICK), HotbarGeneralCategorys.NAVIGATOR, false),
    DIAMOND(new ItemBuilder(Material.DIAMOND), HotbarGeneralCategorys.NAVIGATOR, false),
    SUGAR(new ItemBuilder(Material.SUGAR), HotbarGeneralCategorys.NAVIGATOR, false),
    MUSIC_DISK(new ItemBuilder(Material.RECORD_5), HotbarGeneralCategorys.NAVIGATOR, false),
    RABBITS_FEET(new ItemBuilder(Material.RABBIT_FOOT), HotbarGeneralCategorys.NAVIGATOR, false),
    FIREWORK_CHARGE(new ItemBuilder(Material.FIREWORK_CHARGE), HotbarGeneralCategorys.NAVIGATOR, false),
    IRON_SWORD(new ItemBuilder(Material.IRON_SWORD), HotbarGeneralCategorys.NAVIGATOR, false),

    /*   CHOOSER     */
    NETHER_STAR(new ItemBuilder(Material.NETHER_STAR), HotbarGeneralCategorys.LOBBY_CHOOSER, true),
    STRING(new ItemBuilder(Material.STRING), HotbarGeneralCategorys.LOBBY_CHOOSER, false),
    BLAZE_ROD(new ItemBuilder(Material.BLAZE_ROD), HotbarGeneralCategorys.LOBBY_CHOOSER, false),
    FISH(new ItemBuilder(Material.INK_SACK), HotbarGeneralCategorys.LOBBY_CHOOSER, false),
    GOLDEN_NUGGET(new ItemBuilder(Material.GOLD_NUGGET), HotbarGeneralCategorys.LOBBY_CHOOSER, false),
    RABBIT_HIDE(new ItemBuilder(Material.RABBIT_HIDE), HotbarGeneralCategorys.LOBBY_CHOOSER, false),
    ANVIL(new ItemBuilder(Material.ANVIL), HotbarGeneralCategorys.LOBBY_CHOOSER, false),
    COOKIE(new ItemBuilder(Material.COOKIE), HotbarGeneralCategorys.LOBBY_CHOOSER, false),
    POTION(new ItemBuilder(Material.POTION), HotbarGeneralCategorys.LOBBY_CHOOSER, false),
    REDSTONE_COMPERATOR(new ItemBuilder(Material.REDSTONE_COMPARATOR), HotbarGeneralCategorys.LOBBY_CHOOSER, false),
    COOKED_RABBIT(new ItemBuilder(Material.COOKED_RABBIT), HotbarGeneralCategorys.LOBBY_CHOOSER, false),

    /*   BACKPACK     */
    MINECART_CHEST(new ItemBuilder(Material.STORAGE_MINECART), HotbarGeneralCategorys.BACKPACK, true),
    CHEST(new ItemBuilder(Material.CHEST), HotbarGeneralCategorys.BACKPACK, false),
    END_PORTAL(new ItemBuilder(Material.ENDER_PORTAL_FRAME), HotbarGeneralCategorys.BACKPACK, false),
    DROPPER(new ItemBuilder(Material.DROPPER), HotbarGeneralCategorys.BACKPACK, false),
    STICKY_PISTON(new ItemBuilder(Material.PISTON_STICKY_BASE), HotbarGeneralCategorys.BACKPACK, false),
    CACTUS(new ItemBuilder(Material.CACTUS), HotbarGeneralCategorys.BACKPACK, false),
    FURNACE(new ItemBuilder(Material.FURNACE), HotbarGeneralCategorys.BACKPACK, false),
    BREWING_STAND(new ItemBuilder(Material.BREWING_STAND_ITEM), HotbarGeneralCategorys.BACKPACK, false),
    CAULDRON(new ItemBuilder(Material.CAULDRON_ITEM), HotbarGeneralCategorys.BACKPACK, false),
    MINECART_HOPPER(new ItemBuilder(Material.HOPPER_MINECART), HotbarGeneralCategorys.BACKPACK, false),
    MINECART_TNT(new ItemBuilder(Material.EXPLOSIVE_MINECART), HotbarGeneralCategorys.BACKPACK, false),
    HOPPER(new ItemBuilder(Material.HOPPER), HotbarGeneralCategorys.BACKPACK, false),
    MINECART(new ItemBuilder(Material.MINECART), HotbarGeneralCategorys.BACKPACK, false),
    ENDER_CHEST(new ItemBuilder(Material.ENDER_CHEST), HotbarGeneralCategorys.BACKPACK, false),

    SKULL(new ItemBuilder(Material.SKULL_ITEM), HotbarGeneralCategorys.PROFILE, true),
    BOOK(new ItemBuilder(Material.BOOK_AND_QUILL), HotbarGeneralCategorys.PROFILE, false),
    PAPER(new ItemBuilder(Material.PAPER), HotbarGeneralCategorys.PROFILE, false),
    RAW_FISH(new ItemBuilder(Material.RAW_FISH), HotbarGeneralCategorys.PROFILE, false),
    FLOWER(new ItemBuilder(Material.YELLOW_FLOWER), HotbarGeneralCategorys.PROFILE, false),
    SLIME_BALL(new ItemBuilder(Material.SLIME_BALL), HotbarGeneralCategorys.PROFILE, true);

    private final ItemBuilder item;
    private final HotbarGeneralCategorys categorys;
    private final Boolean mainItem;


    private final String compassDisplayName = HotbarItem.COMPASS.getItemMeta().getDisplayName();

    HotbarItemEnum(ItemBuilder itemBuilder, HotbarGeneralCategorys categorys, Boolean mainItem) {
        this.item = itemBuilder;
        this.categorys = categorys;
        this.mainItem = mainItem;
    }
}
