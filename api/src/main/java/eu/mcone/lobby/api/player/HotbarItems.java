/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.api.player;

import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

public class HotbarItems {

    public static final ItemStack LOBBY_CHANGER = new ItemBuilder(Material.NETHER_STAR)
            .displayName("§3§lLobby-Wechsler §8» §7§oWähle deine Lobby")
            .create();

    public static final ItemStack COMPASS = new ItemBuilder(Material.COMPASS)
            .displayName("§3§lNavigator §8» §7§oWähle einen Spielmodus")
            .create();

    public static final ItemStack ACTIVATE_NICK = new ItemBuilder(Material.NAME_TAG, 1, 0)
            .displayName("§c§lNicken §8» §7§oDeaktiviert").lore("§7§oKlicke zum aktivieren")
            .create();

    public static final ItemStack DEACTIVATE_NICK = new ItemBuilder(Material.NAME_TAG, 1, 0)
            .displayName("§a§lNicken §8» §7§oAktiviert").lore("§7§oKlicke zum deaktivieren")
            .create();

    public static final ItemStack BACKPACK = new ItemBuilder(Material.STORAGE_MINECART, 1, 0)
            .displayName("§3§lRucksack §8» §7§oZeige deine gesammelten Items an")
            .create();

    public static final ItemStack LOADING = new ItemBuilder(Material.INK_SACK, 1, 2)
            .displayName("§7§oLädt...")
            .create();


    /*
     * LobbyHider
     */

    public static final ItemStack HIDE_PLAYERS = new ItemBuilder(Material.INK_SACK, 1, 10)
            .displayName("§3§lSpieler Verstecken §8» §7§oBlende alle anderen Spieler aus")
            .create();

    public static final ItemStack LOBBY_HIDER_UNAVAILABLE = new ItemBuilder(Material.INK_SACK, 1, 8)
            .displayName("§7§lSpieler Verstecken §8» §7§oIn der Privaten Lobby deaktiviert")
            .create();

    public static final ItemStack SHOW_PLAYERS = new ItemBuilder(Material.INK_SACK, 1, 2)
            .displayName("§3§lSpieler Anzeigen §8» §7§oZeigt alle Spieler wieder an")
            .create();


    public static final ItemStack LOBBY_HIDER_UNAVAILABLE_OFFICE = new ItemBuilder(Material.INK_SACK, 1, 8)
            .displayName("§7§lSpieler Verstecken §8» §7§oIm Büro deaktiviert")
            .create();

    public static final ItemStack LOBBY_HIDER_UNAVAILABLE_OFFICE_SILENTHUB = new ItemBuilder(Material.INK_SACK, 1, 8)
            .displayName("§7§lPrivate Lobby §8» §7§oIm Büro deaktiviert")
            .create();

    /*
     * Private Lobby
     */

    public static final ItemStack PRIVATE_LOBBY = new ItemBuilder(Material.TNT, 1, 0)
            .displayName("§6§lPrivate Lobby §8» §7§oBetrete deine eigene Private Lobby")
            .create();

    public static final ItemStack LEAVE_PRIVATE_LOBBY = new ItemBuilder(Material.TNT, 1, 0)
            .displayName("§6§lPrivate Lobby §8» §7§oVerlasse die Private Lobby")
            .create();


    /*
     * JumpNRun
     */

    public static final ItemStack LEAVE_JUMPNRUN = new ItemBuilder(Material.IRON_DOOR, 1, 0)
            .displayName("§fJump and Run")
            .lore("§cverlassen & beenden")
            .create();

    public static final ItemStack TO_CHECKPOINT = new ItemBuilder(Material.INK_SACK, 1, 1)
            .displayName("§c§lZurück zum Checkpoint")
            .create();

    /*
     * Catching Fighting
     */

    public static final ItemStack CATCH_STICK = new ItemBuilder(Material.BLAZE_ROD, 1, 0)
            .unbreakable(true)
            .itemFlags(ItemFlag.HIDE_UNBREAKABLE)
            .displayName("§fFang-Stock")
            .create();


    public static final ItemStack CATCHER_TRACKER = new ItemBuilder(Material.COMPASS, 1, 0)
            .unbreakable(true)
            .itemFlags(ItemFlag.HIDE_UNBREAKABLE)
            .displayName("§cFänger Tracker")
            .create();

    public static final ItemStack CATCH_RUN_TRACKER = new ItemBuilder(Material.COMPASS, 1, 0)
            .unbreakable(true)
            .itemFlags(ItemFlag.HIDE_UNBREAKABLE)
            .displayName("§cLäufer Tracker")
            .create();


    public static final ItemStack CATCH_STICK_RUN = new ItemBuilder(Material.STICK, 1, 0)
            .unbreakable(true)
            .itemFlags(ItemFlag.HIDE_UNBREAKABLE)
            .displayName("§fLauf-Stock")
            .create();


    public static final ItemStack CATCH_ROD = new ItemBuilder(Material.FISHING_ROD, 1, 0)
            .displayName("§eCatch-Enterhacken")
            .unbreakable(true)
            .itemFlags(ItemFlag.HIDE_UNBREAKABLE)
            .create();

    public static final ItemStack LEAVE_CATCH_FIGHTING = new ItemBuilder(Material.IRON_DOOR, 1)
            .displayName("§4Verlassen")
            .create();

    /*
     * OneHit Fighting
     */

    public static final ItemStack ONEHIT_SWORD = new ItemBuilder(Material.IRON_SWORD, 1, 0)
            .unbreakable(true)
            .itemFlags(ItemFlag.HIDE_UNBREAKABLE)
            .displayName("§fOneHit-Schwert")
            .create();

    public static final ItemStack STORY_ONEHIT_SWORD = new ItemBuilder(Material.DIAMOND_SWORD, 1, 0)
            .unbreakable(true)
            .itemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ENCHANTS)
            .displayName("§fOneHit-Schwert")
            .enchantment(Enchantment.DAMAGE_ALL, 1)
            .create();

    public static final ItemStack ONEHIT_BOW = new ItemBuilder(Material.BOW, 1, 0)
            .unbreakable(true)
            .itemFlags(ItemFlag.HIDE_UNBREAKABLE)
            .displayName("§cOneHit-Bogen")
            .create();

    public static final ItemStack ONEHIT_GADGET = new ItemBuilder(Material.CHEST, 1, 0)
            .displayName("§eGadgets")
            .create();

    public static final ItemStack ONEHIT_ARROW = new ItemBuilder(Material.ARROW, 1, 0)
            .displayName("§bOneHit-Pfeil")
            .create();

    public static final ItemStack ONEHIT_SNOWBALL = new ItemBuilder(Material.SNOW_BALL, 1, 0)
            .displayName("§fOneHit-Schneball")
            .create();

    public static final ItemStack LEAVE_ONEHIT_FIGHTING = new ItemBuilder(Material.IRON_DOOR, 1)
            .displayName("§4Verlassen")
            .create();


    /*
     * Lobby-PVP ITEMS
     */

    /*public static final ItemStack SWORD_BLUE = new ItemBuilder(Material.STONE_SWORD, 1, 0)
            .unbreakable(true)
            .itemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ENCHANTS)
            .displayName("§fBlaues-Schwert")
            .enchantment(Enchantment.DAMAGE_ALL, 1)
            .create();

    public static final ItemStack SWORD_RED = new ItemBuilder(Material.STONE_SWORD, 1, 0)
            .unbreakable(true)
            .itemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ENCHANTS)
            .displayName("§fRotes-Schwert")
            .enchantment(Enchantment.DAMAGE_ALL, 1)
            .create();

    public static final ItemStack ROD_RED = new ItemBuilder(Material.FISHING_ROD, 1, 0)
            .unbreakable(true)
            .itemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ENCHANTS)
            .displayName("§fRotes-ANgel")
            .create();

    public static final ItemStack ROD_BLUE = new ItemBuilder(Material.FISHING_ROD, 1, 0)
            .unbreakable(true)
            .itemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ENCHANTS)
            .displayName("§fBlaue-Angel")
            .create();

    public static final ItemStack BOW_BLUE = new ItemBuilder(Material.BOW, 1, 0)
            .unbreakable(true)
            .itemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ENCHANTS)
            .displayName("§fBlauer-Bogen")
            .create();

    public static final ItemStack BOW_RED = new ItemBuilder(Material.BOW, 1, 0)
            .unbreakable(true)
            .itemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ENCHANTS)
            .displayName("§fRoter-Bogen")
            .create();

    public static final ItemStack ARROWS = new ItemBuilder(Material.ARROW, 11, 0)
            .unbreakable(true)
            .itemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ENCHANTS)
            .displayName("§fPfeile")
            .create();*/

}
