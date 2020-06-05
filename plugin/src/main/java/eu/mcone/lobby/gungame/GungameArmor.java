package eu.mcone.lobby.gungame;

import eu.mcone.coresystem.api.bukkit.inventory.PlayerInventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import lombok.Getter;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum GungameArmor {

    NULL(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.WOOD_AXE, 1));
    }}),
    ONE(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.WOOD_SWORD, 1));
    }}),
    TWO(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.WOOD_SWORD, 1));
        put(PlayerInventorySlot.CHESTPLATE, ItemBuilder.createLeatherArmorItem(Material.LEATHER_CHESTPLATE, Color.BLUE));
    }}),
    THREE(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.WOOD_SWORD, 1));
        put(PlayerInventorySlot.CHESTPLATE, ItemBuilder.createLeatherArmorItem(Material.LEATHER_CHESTPLATE, Color.BLUE));
        put(PlayerInventorySlot.LEGGINGS, ItemBuilder.createLeatherArmorItem(Material.LEATHER_LEGGINGS, Color.BLUE));
    }}),
    FOUR(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.WOOD_SWORD, 1));
        put(PlayerInventorySlot.CHESTPLATE, ItemBuilder.createLeatherArmorItem(Material.LEATHER_CHESTPLATE, Color.BLUE));
        put(PlayerInventorySlot.LEGGINGS, ItemBuilder.createLeatherArmorItem(Material.LEATHER_LEGGINGS, Color.BLUE));
        put(PlayerInventorySlot.BOOTS, ItemBuilder.createLeatherArmorItem(Material.LEATHER_LEGGINGS, Color.BLUE));
    }}),
    FIVE(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.WOOD_SWORD, 1));
        put(PlayerInventorySlot.CHESTPLATE, ItemBuilder.createLeatherArmorItem(Material.LEATHER_CHESTPLATE, Color.BLUE).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.LEGGINGS, ItemBuilder.createLeatherArmorItem(Material.LEATHER_LEGGINGS, Color.BLUE));
        put(PlayerInventorySlot.BOOTS, ItemBuilder.createLeatherArmorItem(Material.LEATHER_LEGGINGS, Color.BLUE));
    }}),
    SIX(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.WOOD_SWORD, 1));
        put(PlayerInventorySlot.CHESTPLATE, ItemBuilder.createLeatherArmorItem(Material.LEATHER_CHESTPLATE, Color.BLUE).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.LEGGINGS, ItemBuilder.createLeatherArmorItem(Material.LEATHER_LEGGINGS, Color.BLUE).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.BOOTS, ItemBuilder.createLeatherArmorItem(Material.LEATHER_LEGGINGS, Color.BLUE));
    }}),
    SEVEN(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.WOOD_SWORD, 1));
        put(PlayerInventorySlot.CHESTPLATE, ItemBuilder.createLeatherArmorItem(Material.LEATHER_CHESTPLATE, Color.BLUE).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.LEGGINGS, ItemBuilder.createLeatherArmorItem(Material.LEATHER_LEGGINGS, Color.BLUE).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.BOOTS, ItemBuilder.createLeatherArmorItem(Material.LEATHER_LEGGINGS, Color.BLUE).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
    }}),
    EIGHT(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.WOOD_SWORD, 1).enchantment(Enchantment.DAMAGE_ALL, 1));
        put(PlayerInventorySlot.CHESTPLATE, ItemBuilder.createLeatherArmorItem(Material.LEATHER_CHESTPLATE, Color.BLUE).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.LEGGINGS, ItemBuilder.createLeatherArmorItem(Material.LEATHER_LEGGINGS, Color.BLUE).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.BOOTS, ItemBuilder.createLeatherArmorItem(Material.LEATHER_LEGGINGS, Color.BLUE).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
    }}),
    NINE(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.WOOD_SWORD, 1).enchantment(Enchantment.DAMAGE_ALL, 1));
        put(PlayerInventorySlot.CHESTPLATE, ItemBuilder.createLeatherArmorItem(Material.LEATHER_CHESTPLATE, Color.BLUE).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.LEGGINGS, ItemBuilder.createLeatherArmorItem(Material.LEATHER_LEGGINGS, Color.BLUE).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.BOOTS, ItemBuilder.createLeatherArmorItem(Material.LEATHER_LEGGINGS, Color.BLUE).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.CHAINMAIL_HELMET));
    }}),
    TEN(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.WOOD_SWORD, 1).enchantment(Enchantment.DAMAGE_ALL, 1));
        put(PlayerInventorySlot.CHESTPLATE, ItemBuilder.createLeatherArmorItem(Material.LEATHER_CHESTPLATE, Color.BLUE).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.LEGGINGS, ItemBuilder.createLeatherArmorItem(Material.LEATHER_LEGGINGS, Color.BLUE).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.CHAINMAIL_BOOTS));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.CHAINMAIL_HELMET));
    }}),
    ELEVEN(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.WOOD_SWORD, 1).enchantment(Enchantment.DAMAGE_ALL, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.CHAINMAIL_CHESTPLATE));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.CHAINMAIL_LEGGINGS));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.CHAINMAIL_BOOTS));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.CHAINMAIL_HELMET));
    }}),
    TWELF(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.STONE_SWORD, 1).enchantment(Enchantment.DAMAGE_ALL, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.CHAINMAIL_CHESTPLATE));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.CHAINMAIL_LEGGINGS));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.CHAINMAIL_BOOTS));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.CHAINMAIL_HELMET));
    }}),
    THIRTEEN(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.STONE_SWORD, 1).enchantment(Enchantment.DAMAGE_ALL, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.CHAINMAIL_CHESTPLATE).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.CHAINMAIL_LEGGINGS));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.CHAINMAIL_BOOTS));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.CHAINMAIL_HELMET));
    }}),
    FOURTEEN(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.STONE_SWORD, 1).enchantment(Enchantment.DAMAGE_ALL, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.CHAINMAIL_CHESTPLATE).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.CHAINMAIL_LEGGINGS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.CHAINMAIL_BOOTS));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.CHAINMAIL_HELMET));
    }}),
    FIFTEEN(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.STONE_SWORD, 1).enchantment(Enchantment.DAMAGE_ALL, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.CHAINMAIL_CHESTPLATE).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.CHAINMAIL_LEGGINGS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.CHAINMAIL_BOOTS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.CHAINMAIL_HELMET));
    }}),
    SIXTEEN(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.STONE_SWORD, 1).enchantment(Enchantment.DAMAGE_ALL, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.CHAINMAIL_CHESTPLATE).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.CHAINMAIL_LEGGINGS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.CHAINMAIL_BOOTS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.CHAINMAIL_HELMET).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
    }}),
    SEVENTEEN(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.GOLD_SWORD, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.CHAINMAIL_CHESTPLATE).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.CHAINMAIL_LEGGINGS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.CHAINMAIL_BOOTS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.CHAINMAIL_HELMET).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
    }}),
    EIGHTEEN(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.GOLD_SWORD, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.GOLD_CHESTPLATE));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.CHAINMAIL_LEGGINGS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.CHAINMAIL_BOOTS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.CHAINMAIL_HELMET).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
    }}),
    NINETEEN(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.GOLD_SWORD, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.GOLD_CHESTPLATE));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.GOLD_LEGGINGS));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.CHAINMAIL_BOOTS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.CHAINMAIL_HELMET).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
    }}),
    TWENTY(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.GOLD_SWORD, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.GOLD_CHESTPLATE));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.GOLD_LEGGINGS));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.GOLD_BOOTS));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.CHAINMAIL_HELMET).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
    }}),
    TWENTY_ONE(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.GOLD_SWORD, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.GOLD_CHESTPLATE));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.GOLD_LEGGINGS));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.GOLD_BOOTS));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.GOLD_HELMET));
    }}),
    TWENTY_TWO(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.GOLD_SWORD, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.GOLD_CHESTPLATE));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.GOLD_LEGGINGS));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.GOLD_BOOTS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.GOLD_HELMET));
    }}),
    TWENTY_THREE(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.GOLD_SWORD, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.GOLD_CHESTPLATE));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.GOLD_LEGGINGS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.GOLD_BOOTS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.GOLD_HELMET));
    }}),
    TWENTY_FOUR(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.GOLD_AXE, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.GOLD_CHESTPLATE));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.GOLD_LEGGINGS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.GOLD_BOOTS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.GOLD_HELMET));
    }}),
    TWENTY_FIVE(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.GOLD_AXE, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.GOLD_CHESTPLATE).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.GOLD_LEGGINGS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.GOLD_BOOTS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.GOLD_HELMET));
    }}),
    TWENTY_SIX(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.GOLD_AXE, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.GOLD_CHESTPLATE).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.GOLD_LEGGINGS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.GOLD_BOOTS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.GOLD_HELMET).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
    }}),
    TWENTY_EIGHT(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.IRON_AXE, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.GOLD_CHESTPLATE).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.GOLD_LEGGINGS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.GOLD_BOOTS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.GOLD_HELMET).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
    }}),
    THIRTY(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.IRON_AXE, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.IRON_CHESTPLATE));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.GOLD_LEGGINGS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.GOLD_BOOTS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.GOLD_HELMET).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
    }}),
    THIRTY_ONE(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.IRON_AXE, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.IRON_CHESTPLATE));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.GOLD_LEGGINGS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.IRON_BOOTS));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.GOLD_HELMET).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
    }}),
    THIRTY_FOUR(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.IRON_AXE, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.IRON_CHESTPLATE));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.IRON_LEGGINGS));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.IRON_BOOTS));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.IRON_HELMET));
    }}),
    THIRTY_SIX(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.IRON_SWORD, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.IRON_CHESTPLATE));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.IRON_LEGGINGS));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.IRON_BOOTS));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.IRON_HELMET));
    }}),
    THIRTY_SEVEN(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.IRON_SWORD, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.IRON_CHESTPLATE).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.IRON_LEGGINGS));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.IRON_BOOTS));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.IRON_HELMET));
    }}),
    THIRTY_EIGHTEEN(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.IRON_SWORD, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.IRON_CHESTPLATE).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.IRON_LEGGINGS));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.IRON_BOOTS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.IRON_HELMET));
    }}),
    THIRTY_NINE(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.IRON_SWORD, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.IRON_CHESTPLATE).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.IRON_LEGGINGS));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.IRON_BOOTS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.IRON_HELMET).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
    }}),
    FORTY(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.IRON_SWORD, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.IRON_CHESTPLATE).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.IRON_LEGGINGS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.IRON_BOOTS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.IRON_HELMET).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
    }}),
    FORTY_ONE(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.IRON_SWORD, 1).enchantment(Enchantment.DAMAGE_ALL, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.IRON_CHESTPLATE).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.IRON_LEGGINGS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.IRON_BOOTS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.IRON_HELMET).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
    }}),
    FORTY_TWO(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.DIAMOND_AXE, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.IRON_CHESTPLATE).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.IRON_LEGGINGS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.IRON_BOOTS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.IRON_HELMET).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
    }}),
    FORTY_THREE(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.DIAMOND_AXE, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.DIAMOND_CHESTPLATE));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.IRON_LEGGINGS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.IRON_BOOTS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.IRON_HELMET).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
    }}),
    FORTY_FOUR(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.DIAMOND_AXE, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.DIAMOND_CHESTPLATE));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.DIAMOND_LEGGINGS));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.IRON_BOOTS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.IRON_HELMET).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
    }}),
    FOURTY_FIVE(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.DIAMOND_AXE, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.DIAMOND_CHESTPLATE));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.DIAMOND_LEGGINGS));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.DIAMOND_BOOTS));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.IRON_HELMET).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
    }}),
    FOURTY_SIX(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.DIAMOND_AXE, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.DIAMOND_CHESTPLATE));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.DIAMOND_LEGGINGS));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.DIAMOND_BOOTS));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.DIAMOND_HELMET));
    }}),
    FOURTY_SEVEN(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.DIAMOND_AXE, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.DIAMOND_CHESTPLATE));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.DIAMOND_LEGGINGS));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.DIAMOND_BOOTS));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.DIAMOND_HELMET));
    }}),
    FOURTY_EIGHT(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.DIAMOND_AXE, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.DIAMOND_CHESTPLATE).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.DIAMOND_LEGGINGS));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.DIAMOND_BOOTS));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.DIAMOND_HELMET));
    }}),
    FOURTY_NINE(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.DIAMOND_AXE, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.DIAMOND_CHESTPLATE).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.DIAMOND_LEGGINGS));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.DIAMOND_BOOTS));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.DIAMOND_HELMET).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
    }}),
    FIFTY(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.DIAMOND_AXE, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.DIAMOND_CHESTPLATE).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.DIAMOND_LEGGINGS));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.DIAMOND_BOOTS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.DIAMOND_HELMET).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
    }}),
    FIFTY_ONE(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.DIAMOND_AXE, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.DIAMOND_CHESTPLATE).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.DIAMOND_LEGGINGS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.DIAMOND_BOOTS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.DIAMOND_HELMET).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
    }}),
    FIFTY_TWO(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.DIAMOND_SWORD, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.DIAMOND_CHESTPLATE).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.IRON_LEGGINGS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.IRON_BOOTS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.IRON_HELMET).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
    }}),
    FIFTY_THREE(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.DIAMOND_SWORD, 1).enchantment(Enchantment.DAMAGE_ALL, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.DIAMOND_CHESTPLATE).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.IRON_LEGGINGS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.IRON_BOOTS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.IRON_HELMET).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
    }}),
    FIFTY_FOUR(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.DIAMOND_SWORD, 1).enchantment(Enchantment.DAMAGE_ALL, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.DIAMOND_CHESTPLATE).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.IRON_LEGGINGS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.IRON_BOOTS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.IRON_HELMET).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
    }});


    private final Map<Integer, ItemBuilder> items;

    GungameArmor(Map<Integer, ItemBuilder> items) {
        this.items = items;
    }

    public static Map<Integer, ItemBuilder> getItemsForStreak(int killstreak) {
        if (killstreak > values().length - 1) {
            killstreak = values().length - 1;
        }

        return values()[killstreak].getItems();
    }

}
