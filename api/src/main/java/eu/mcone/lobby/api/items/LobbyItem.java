/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.api.items;

import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.gameapi.api.backpack.Level;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

@Getter
public enum LobbyItem {

    //Story items
    MAGICDRINK(0, "ZauberTrank", LobbyCategory.STORY_ITEMS, Level.EPIC, 0, 0, new ItemBuilder(Material.POTION, 1, 0).displayName("§5§lZauber Trank").lore("§7Kategorie: §bItem", "§7Seltenheit: §5Episch", "", "§7Helfe den Bürgermeister Rufi!").create()),
    MAGICWAND(1, "ZauberStab", LobbyCategory.STORY_ITEMS, Level.EPIC, 0, 0, new ItemBuilder(Material.STICK, 1, 0).displayName("§5§lZauber Stab").lore("§7Kategorie: §bItem", "§7Seltenheit: §5Episch", "", "§7Zauber wie ein Profi!").create()),
    ONE_HIT_SWORD(2, "One Hit Schwertt", LobbyCategory.STORY_ITEMS, Level.MYSTICAL, 0, 0, new ItemBuilder(Material.DIAMOND_SWORD, 1, 0).displayName("§c§lOne Hit Sword").lore("§7Kategorie: §bItem", "§7Seltenheit: §cMythisch", "", "§7Schieße Blitze duch die Lobby").enchantment(Enchantment.DAMAGE_ALL, 5).unbreakable(true).itemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE).create()),
    PASS(3, "Ausweiss", LobbyCategory.STORY_ITEMS, Level.EPIC, 0, 0, new ItemBuilder(Material.BOOK, 1, 0).displayName("§5§lPersonalausweis").lore("§7Kategorie: §bItem", "§7Seltenheit: §5Episch", "", "§7Dein Pass").enchantment(Enchantment.DAMAGE_ALL, 5).unbreakable(true).itemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE).create()),
    BOAT_PASS(4, "Fahrkarte", LobbyCategory.STORY_ITEMS, Level.EPIC, 0, 0, new ItemBuilder(Material.PAPER, 1, 0).displayName("§5§lFahrkarte").lore("§7Kategorie: §bItem", "§7Seltenheit: §5Episch", "§720 Coins", "§7Deine Fahrkarte zu Paradise Island").enchantment(Enchantment.DAMAGE_ALL, 5).unbreakable(true).itemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE).create()),
    RADIO_SET1(5, "Funkgerät1", LobbyCategory.STORY_ITEMS, Level.EPIC, 0, 0, new ItemBuilder(Material.TRIPWIRE_HOOK, 1, 0).displayName("§5§lFunkgerät").lore("§7Kategorie: §bItem", "§7Seltenheit: §5Episch", "", "§7Funk verbunden mit Edward").create()),
    GPS(6, "GPS", LobbyCategory.STORY_ITEMS, Level.EPIC, 0, 0, new ItemBuilder(Material.PRISMARINE_SHARD, 1, 0).displayName("§5§lGPS-Sender").lore("§7Kategorie: §bItem", "§7Seltenheit: §5Episch", "", "§7GPS verbunden mit Edward").create()),
    RADIO_SET_2(7, "Funkgerät2", LobbyCategory.STORY_ITEMS, Level.UNUSUAL, 0, 0, new ItemBuilder(Material.REDSTONE_COMPARATOR, 1, 0).displayName("§3§lAltes Funkgerät").lore("§7Kategorie: §bItem", "§7Seltenheit: §3Ungewöhnlich", "", "§7Funk verbunden mit Sparow").create()),
    BANKCARD(8, "Bankkarte", LobbyCategory.STORY_ITEMS, Level.LEGENDARY, 0, 0, new ItemBuilder(Material.PAINTING, 1, 0).displayName("§6§lBankkarte").lore("§7Kategorie: §bItem", "§7Seltenheit: §6Legendär", "", "§7Karte rein karte raus Geld da").create()),
    BANKCARD_PREMIUM(9, "Bankkarte", LobbyCategory.STORY_ITEMS, Level.LEGENDARY, 0, 0, new ItemBuilder(Material.PAINTING, 1, 0).displayName("§6§lPremium Bankkarte").lore("§7Kategorie: §bItem", "§7Seltenheit: §6Legendär", "", "§7Karte rein karte raus mehr Geld da").create()),
    OFFICE_CARD_BRONZE(10, "Büro Karte", LobbyCategory.STORY_ITEMS, Level.LEGENDARY, 0, 0, new ItemBuilder(Material.PAPER, 1, 0).displayName("§6§lBüro Karte").lore("§7Kategorie: §bItem", "§7Seltenheit: §6Legendär", "", "§7Ihre Büro SchlüsselKarte").create()),
    OFFICE_CARD_SILVER(11, "Büro Karte", LobbyCategory.STORY_ITEMS, Level.LEGENDARY, 0, 0, new ItemBuilder(Material.PAPER, 1, 0).displayName("§6§lBüro Karte").lore("§7Kategorie: §bItem", "§7Seltenheit: §6Legendär", "", "§7Ihre Büro SchlüsselKarte").create()),
    OFFICE_CARD_GOLD(12, "Büro Karte", LobbyCategory.STORY_ITEMS, Level.LEGENDARY, 0, 0, new ItemBuilder(Material.PAPER, 1, 0).displayName("§6§lBüro Karte").lore("§7Kategorie: §bItem", "§7Seltenheit: §6Legendär", "", "§7Ihre Büro SchlüsselKarte").create()),

    //BANK GENERAL
    BUTTON(13, "Knopf im Ohr", LobbyCategory.STORY_ITEMS, Level.LEGENDARY, 0, 0, new ItemBuilder(Material.STONE_BUTTON, 1, 0).displayName("§6§lKnopf im Ohr").lore("§7Kategorie: §bItem", "§7Seltenheit: §6Legendär", "", "§7Verbunden mit John").create()),

    //bank robberyS
    BANK_MAP(14, "Bank Plan", LobbyCategory.STORY_ITEMS, Level.LEGENDARY, 0, 0, new ItemBuilder(Material.EMPTY_MAP, 1, 0).displayName("§6§lPlan der Bank").lore("§7Kategorie: §bItem", "§7Seltenheit: §6Legendär", "", "§7Ach dahinten ist der Tresor").create()),
    GOLD_BARDING(15, "Gold Barren", LobbyCategory.STORY_ITEMS, Level.LEGENDARY, 0, 0, new ItemBuilder(Material.GOLD_INGOT, 58).displayName("§6§lGold Barren").lore("§7Kategorie: §bItem", "§7Seltenheit: §6Legendär", "", "§724 Karat Barren").create()),
    WHITE_WOOL(16, "Bank Plan", LobbyCategory.STORY_ITEMS, Level.UNUSUAL, 0, 0, new ItemBuilder(Material.WOOL, 37, 0).displayName("§3§lWeiße Wolle").lore("§7Kategorie: §bItem", "§7Seltenheit: §3Ungewöhnlich", "", "§7Zum nähen geeignet").create()),
    BANK_OUTFIT(17, "Bank Outfit", LobbyCategory.STORY_ITEMS, Level.UNUSUAL, 0, 0, new ItemBuilder(Material.CHEST, 1, 0).displayName("§3§lBank Outfit im Packet").lore("§7Kategorie: §bItem / Outfit", "§7Seltenheit: §3Ungewöhnlich", "", "§7Sehe aus wie ein Bänker").create()),
    GOLD_NUGGET(18, "Gold Münze", LobbyCategory.STORY_ITEMS, Level.MYSTICAL, 0, 0, new ItemBuilder(Material.GOLD_NUGGET, 1, 0).displayName("§c§lGold Münze").lore("§7Kategorie: §bItem", "§7Seltenheit: §cMythisch", "", "§7Ein kleines Geschenk von John").create()),
    IRON_SWORD(19, "Eisen Schwert", LobbyCategory.STORY_ITEMS, Level.UNUSUAL, 0, 0, new ItemBuilder(Material.IRON_SWORD, 1, 0).displayName("§3§lEisen Schwert").lore("§7Kategorie: §bSchwerter", "§7Seltenheit: §3Ungewöhnlich", "§7Mit dem Eisen Schwert in die Bank!").create()),

    //bank robbery central
    HACKER_TERMINAL_LOW(20, "Hacker Terminal", LobbyCategory.STORY_ITEMS, Level.EPIC, 0, 0, new ItemBuilder(Material.IRON_TRAPDOOR, 1).displayName("§5§lHacker Terminal").lore("§7Kategorie: §bItem", "§7Seltenheit: §5Episch", "", "§7Dü Dü alles gehackt").create()),
    HACKER_TERMINAL_HIGH(21, "Hacker Terminal", LobbyCategory.STORY_ITEMS, Level.EPIC, 0, 0, new ItemBuilder(Material.IRON_TRAPDOOR, 1).displayName("§5§lHacker Terminal").lore("§7Kategorie: §bItem", "§7Seltenheit: §5Episch", "", "§7Dü Dü alles gehackt").create()),
    CENTRAL_BANK_MAP(22, "Plan der Central Bank", LobbyCategory.STORY_ITEMS, Level.LEGENDARY, 0, 0, new ItemBuilder(Material.EMPTY_MAP, 1, 0).displayName("§6§lPlan der Central Bank").lore("§7Kategorie: §bItem", "§7Seltenheit: §6Legendär", "", "§7Ah da geht es rein!").create()),
    DIAMOND_BANK_SWORD(23, "Diamanten Schwert", LobbyCategory.STORY_ITEMS, Level.EPIC, 0, 0, new ItemBuilder(Material.DIAMOND_SWORD, 1, 0).displayName("§5§lDiamantschwer Schwert").lore("§7Kategorie: §bSchwerter", "§7Seltenheit: §5Episch", "", "§7Gut zum erpressen!").create()),

    WORKER_FILE_1(24, "Mitarbeiter Daten", LobbyCategory.STORY_ITEMS, Level.EPIC, 0, 0, new ItemBuilder(Material.BOOK, 1, 0).displayName("§5§lMitarbeiter Akten").lore("§7Kategorie: §bAkten", "§7Seltenheit: §5Episch", "", "§7Der Mitarbeiter Schmid!").create()),
    NORMAL_FILES_1(25, "Bank Daten1", LobbyCategory.STORY_ITEMS, Level.EPIC, 0, 0, new ItemBuilder(Material.BOOK, 1, 0).displayName("§5§lBank Akten").lore("§7Kategorie: §bAkten", "§7Seltenheit: §5Episch", "", "§7Super für die Polizei!").create()),
    NORMAL_FILES_2(26, "Bank Daten2", LobbyCategory.STORY_ITEMS, Level.EPIC, 0, 0, new ItemBuilder(Material.BOOK, 1, 0).displayName("§5§lBank Schlüssel Akten").lore("§7Kategorie: §bAkten", "§7Seltenheit: §5Episch", "", "§7Zu viele Passwörter!").create()),
    NORMAL_FILES_3(27, "Bank Daten3", LobbyCategory.STORY_ITEMS, Level.EPIC, 0, 0, new ItemBuilder(Material.BOOK, 1, 0).displayName("§5§lGeld Tresor Akten").lore("§7Kategorie: §bAkten", "§7Seltenheit: §5Episch", "", "§7Ich mache Minus oder Plus").create()),

    //tutorial story
    COMPASS(28, "Kompass", LobbyCategory.STORY_ITEMS, Level.EPIC, 0, 0, new ItemBuilder(Material.LEASH, 1, 0).displayName("§5§lKompass").lore("§7Kategorie: §bItem", "§7Seltenheit: §5Episch", "", "§7Funktioniert der Kompass überhaupt??").create()),

    //Armor
    IRON_HEAD(0, "eisenhelm", LobbyCategory.ARMOR, Level.UNUSUAL, 0, 0, new ItemBuilder(Material.IRON_HELMET, 1, 0).displayName("§3§lEisen Helm").lore("§7Kategorie: §bRüstung", "§7Seltenheit: §3Ungewöhnlich", "§72 Eisen", "§7Mit dem Eisen Helm in den Krieg").create()),
    IRON_CHESTPLATE(1, "eisenbrustpanzer", LobbyCategory.ARMOR, Level.UNUSUAL, 0, 0, new ItemBuilder(Material.IRON_CHESTPLATE, 1, 0).displayName("§3§lEisen Brustpanzer").lore("§7Kategorie: §bRüstung", "§7Seltenheit: §3Ungewöhnlich", "§76 Eisen", "§7Mit der Eisen Platte in den Krieg").create()),
    IRON_LEGGINS(2, "eisenhose", LobbyCategory.ARMOR, Level.UNUSUAL, 0, 0, new ItemBuilder(Material.IRON_LEGGINGS, 1, 0).displayName("§3§lEisen Hose").lore("§7Kategorie: §bRüstung", "§7Seltenheit: §3Ungewöhnlich", "§74 Eisen", "§7Mit der Eisen Hose in den Krieg").create()),
    IRON_BOOTS(3, "eisenstiefel", LobbyCategory.ARMOR, Level.UNUSUAL, 0, 0, new ItemBuilder(Material.IRON_BOOTS, 1, 0).displayName("§3§lEisen Schuhe").lore("§7Kategorie: §bRüstung", "§7Seltenheit: §3Ungewöhnlich", "§74 Eisen", "§7Mit den Eisen Schuhe in den Krieg").create()),

    //Coin items (Chest opening)
    EMERALDS_20(0, "20+ Emeralds", null, Level.USUAL, 0, 0, new ItemBuilder(Material.GOLD_INGOT, 1, 0).displayName("§7§lEmerald-Loot ").lore("§7Kategorie: §bEmeralds", "§7Seltenheit: §7Gewöhnlich").create()),
    EMERALDS_100(1, "100+ Emeralds", null, Level.UNUSUAL, 0, 0, new ItemBuilder(Material.GOLD_BLOCK, 1, 0).displayName("§3§lEmerald-Loot ").lore("§7Kategorie: §bEmeralds", "§7Seltenheit: §3Ungewöhnlich").create()),
    EMERALDS_250(2, "250+ Emeralds", null, Level.EPIC, 0, 0, new ItemBuilder(Material.DIAMOND_BLOCK, 1, 0).displayName("§5§lEmerald-Loot ").lore("§7Kategorie: §bEmeralds", "§7Seltenheit: §5Episch").create()),
    EMERALDS_400(3, "400+ Emeralds", null, Level.LEGENDARY, 0, 0, new ItemBuilder(Material.EMERALD_BLOCK, 1, 0).displayName("§6§lEmerald-Loot ").lore("§7Kategorie: §bEmeralds", "§7Seltenheit: §6Legendär").create());

    //Ingame Items
//    BEDROCK_CB(245, "Bedrock-block", Category.INGAME, Level.LEGENDARY, 0,120, new ItemBuilder(Material.BEDROCK, 1, 0).displayName("§6§lBedrock").lore("§7Kategorie: §bIngame Item", "§7Seltenheit: §6Legendär", "", "§7Ich baue ab und er zerbricht nicht!").create()),
//    ENDSTONE_CB(246, "End-stone-block", Category.INGAME, Level.EPIC, 0,100, new ItemBuilder(Material.ENDER_STONE, 1, 0).displayName("§6§lBedrock").lore("§7Kategorie: §bIngame Item", "§7Seltenheit: §6Legendär", "", "§7Ich baue ab und er zerbricht nicht!").create()),
//    SPAWNER_CB(247, "Mob-spawner-block", Category.INGAME, Level.LEGENDARY, 0,120, new ItemBuilder(Material.MOB_SPAWNER, 1, 0).displayName("§6§lMob Spawner").lore("§7Kategorie: §bIngame Item", "§7Seltenheit: §6Legendär", "", "§7Ohh da ist was gespawnt!").create()),
//    DRAGON_EGG_CB(248, "Drachen-ei-block", Category.INGAME, Level.LEGENDARY, 0,120, new ItemBuilder(Material.DRAGON_EGG, 1, 0).displayName("§6§lDrachen Ei").lore("§7Kategorie: §bIngame Item", "§7Seltenheit: §6Legendär", "", "§7Das Ei ist wunderschön!").create()),
//    BEACON_CB(249, "Beacon-block", Category.INGAME, Level.EPIC, 0,100, new ItemBuilder(Material.ENDER_STONE, 1, 0).displayName("§6§Mob Spawner").lore("§7Kategorie: §bIngame Item", "§7Seltenheit: §6Legendär", "", "§7Ich baue ab und er zerbricht nicht!").create()),
//    BARRIERE_CB(247, "Barriere-block", Category.INGAME, Level.LEGENDARY, 120,0, new ItemBuilder(Material.BARRIER, 1, 0).displayName("§6§lBarriere").lore("§7Kategorie: §bIngame Item", "§7Seltenheit: §6Legendär", "", "§7Ich sehe den Block garnicht!").create()),
//    END_PORTAL_CB(251, "End-portal-block", Category.INGAME, Level.LEGENDARY, 0,120, new ItemBuilder(Material.ENDER_PORTAL_FRAME, 1, 0).displayName("§6§lEnd Portal Rahmen").lore("§7Kategorie: §bIngame Item", "§7Seltenheit: §6Legendär", "", "§7Ohh wie cool ein Rahmen!").create()),
//
//
//    SUPER_SWORD(260, "Super-sword", Category.INGAME, Level.MYSTICAL, 0, 140, new ItemBuilder(Material.DIAMOND_SWORD, 1, 0).displayName("§c§lSuper-Schwert").lore("§7Kategorie: §bIngame Item", "§7Seltenheit: §cMythisch", "", "§7Einer der gefährlichsten Schwerter der Welt!").create());

    private final int id, buyPrice, sellPrice;
    private final LobbyCategory category;
    private final Level level;
    private final String name;
    private final ItemStack itemStack;

    LobbyItem(int id, String name, LobbyCategory category, Level level, int buyPrice, int sellPrice, ItemStack itemStack) {
        this.id = id;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;

        this.category = category;
        this.level = level;
        this.name = name;
        this.itemStack = itemStack;
    }

    public boolean hasCategory() {
        return category != null;
    }

    public static LobbyItem getItemByID(int id) {
        for (LobbyItem i : values()) {
            if (i.getId() == id) {
                return i;
            }
        }
        return null;
    }

}
