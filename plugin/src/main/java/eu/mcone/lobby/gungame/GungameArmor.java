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
    fifteen(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.STONE_SWORD, 1).enchantment(Enchantment.DAMAGE_ALL, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.CHAINMAIL_CHESTPLATE).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.CHAINMAIL_LEGGINGS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.CHAINMAIL_BOOTS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.CHAINMAIL_HELMET));
    }}),
    sixteen(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.STONE_SWORD, 1).enchantment(Enchantment.DAMAGE_ALL, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.CHAINMAIL_CHESTPLATE).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.CHAINMAIL_LEGGINGS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.CHAINMAIL_BOOTS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.CHAINMAIL_HELMET).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
    }}),
    seventeen(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.GOLD_SWORD, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.CHAINMAIL_CHESTPLATE).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.CHAINMAIL_LEGGINGS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.CHAINMAIL_BOOTS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.CHAINMAIL_HELMET).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
    }}),
    eighteen(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.GOLD_SWORD, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.GOLD_CHESTPLATE));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.CHAINMAIL_LEGGINGS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.CHAINMAIL_BOOTS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.CHAINMAIL_HELMET).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
    }}),
    nineteen(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.GOLD_SWORD, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.GOLD_CHESTPLATE));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.GOLD_LEGGINGS));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.CHAINMAIL_BOOTS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.CHAINMAIL_HELMET).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
    }}),
    twenty(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.GOLD_SWORD, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.GOLD_CHESTPLATE));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.GOLD_LEGGINGS));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.GOLD_BOOTS));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.CHAINMAIL_HELMET).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
    }}),
    twenty_one(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.GOLD_SWORD, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.GOLD_CHESTPLATE));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.GOLD_LEGGINGS));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.GOLD_BOOTS));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.GOLD_HELMET));
    }}),
    twenty_two(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.GOLD_SWORD, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.GOLD_CHESTPLATE));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.GOLD_LEGGINGS));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.GOLD_BOOTS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.GOLD_HELMET));
    }}),
    twenty_three(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.GOLD_SWORD, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.GOLD_CHESTPLATE));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.GOLD_LEGGINGS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.GOLD_BOOTS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.GOLD_HELMET));
    }}),
    twenty_four(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.GOLD_AXE, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.GOLD_CHESTPLATE));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.GOLD_LEGGINGS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.GOLD_BOOTS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.GOLD_HELMET));
    }}),
    twenty_five(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.GOLD_AXE, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.GOLD_CHESTPLATE).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.GOLD_LEGGINGS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.GOLD_BOOTS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.GOLD_HELMET));
    }}),
    twenty_six(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.GOLD_AXE, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.GOLD_CHESTPLATE).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.GOLD_LEGGINGS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.GOLD_BOOTS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.GOLD_HELMET).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
    }}),
    twenty_eight(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.IRON_AXE, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.GOLD_CHESTPLATE).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.GOLD_LEGGINGS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.GOLD_BOOTS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.GOLD_HELMET).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
    }}),
    thirty(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.IRON_AXE, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.IRON_CHESTPLATE));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.GOLD_LEGGINGS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.GOLD_BOOTS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.GOLD_HELMET).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
    }}),
    thirty_one(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.IRON_AXE, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.IRON_CHESTPLATE));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.GOLD_LEGGINGS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.IRON_BOOTS));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.GOLD_HELMET).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
    }}),
    thirty_four(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.IRON_AXE, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.IRON_CHESTPLATE));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.IRON_LEGGINGS));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.IRON_BOOTS));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.IRON_HELMET));
    }}),
    thirty_six(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.IRON_SWORD, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.IRON_CHESTPLATE));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.IRON_LEGGINGS));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.IRON_BOOTS));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.IRON_HELMET));
    }}),
    thirty_seven(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.IRON_SWORD, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.IRON_CHESTPLATE).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.IRON_LEGGINGS));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.IRON_BOOTS));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.IRON_HELMET));
    }}),
    thirty_eighteen(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.IRON_SWORD, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.IRON_CHESTPLATE).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.IRON_LEGGINGS));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.IRON_BOOTS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.IRON_HELMET));
    }}),
    thirty_nine(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.IRON_SWORD, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.IRON_CHESTPLATE).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.IRON_LEGGINGS));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.IRON_BOOTS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.IRON_HELMET).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
    }}),
    forty(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.IRON_SWORD, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.IRON_CHESTPLATE).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.IRON_LEGGINGS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.IRON_BOOTS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.IRON_HELMET).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
    }}),
    forty_one(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.IRON_SWORD, 1).enchantment(Enchantment.DAMAGE_ALL,1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.IRON_CHESTPLATE).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.IRON_LEGGINGS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.IRON_BOOTS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.IRON_HELMET).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
    }}),
    forty_two(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.DIAMOND_AXE, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.IRON_CHESTPLATE).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.IRON_LEGGINGS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.IRON_BOOTS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.IRON_HELMET).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
    }}),
    forty_three(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.DIAMOND_AXE, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.DIAMOND_CHESTPLATE));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.IRON_LEGGINGS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.IRON_BOOTS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.IRON_HELMET).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
    }}),
    forty_four(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.DIAMOND_AXE, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.DIAMOND_CHESTPLATE));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.DIAMOND_LEGGINGS));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.IRON_BOOTS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.IRON_HELMET).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
    }}),
    fourty_five(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.DIAMOND_AXE, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.DIAMOND_CHESTPLATE));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.DIAMOND_LEGGINGS));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.DIAMOND_BOOTS));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.IRON_HELMET).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
    }}),
    fourty_six(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.DIAMOND_AXE, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.DIAMOND_CHESTPLATE));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.DIAMOND_LEGGINGS));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.DIAMOND_BOOTS));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.DIAMOND_HELMET));
    }}),
    fourty_seven(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.DIAMOND_AXE, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.DIAMOND_CHESTPLATE));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.DIAMOND_LEGGINGS));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.DIAMOND_BOOTS));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.DIAMOND_HELMET));
    }}),
    fourty_eight(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.DIAMOND_AXE, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.DIAMOND_CHESTPLATE).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL,1));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.DIAMOND_LEGGINGS));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.DIAMOND_BOOTS));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.DIAMOND_HELMET));
    }}),
    fourty_nine(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.DIAMOND_AXE, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.DIAMOND_CHESTPLATE).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL,1));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.DIAMOND_LEGGINGS));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.DIAMOND_BOOTS));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.DIAMOND_HELMET).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL,1));
    }}),
    fifty(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.DIAMOND_AXE, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.DIAMOND_CHESTPLATE).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL,1));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.DIAMOND_LEGGINGS));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.DIAMOND_BOOTS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL,1));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.DIAMOND_HELMET).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL,1));
    }}),
    fifty_one(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.DIAMOND_AXE, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.DIAMOND_CHESTPLATE).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL,1));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.DIAMOND_LEGGINGS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL,1));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.DIAMOND_BOOTS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL,1));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.DIAMOND_HELMET).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL,1));
    }}),
    fifty_two(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.DIAMOND_SWORD, 1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.DIAMOND_CHESTPLATE).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.IRON_LEGGINGS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.IRON_BOOTS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.IRON_HELMET).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
    }}),
    fifty_three(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.DIAMOND_SWORD, 1).enchantment(Enchantment.DAMAGE_ALL,1));
        put(PlayerInventorySlot.CHESTPLATE, new ItemBuilder(Material.DIAMOND_CHESTPLATE).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.LEGGINGS, new ItemBuilder(Material.IRON_LEGGINGS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.BOOTS, new ItemBuilder(Material.IRON_BOOTS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        put(PlayerInventorySlot.HELMET, new ItemBuilder(Material.IRON_HELMET).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
    }}),
    fifty_four(new HashMap<Integer, ItemBuilder>() {{
        put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.DIAMOND_SWORD, 1).enchantment(Enchantment.DAMAGE_ALL,1));
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
