/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.api.player;

import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class HotbarItem {

    public static final ItemStack LOBBY_CHANGER = new ItemBuilder(Material.NETHER_STAR)
            .displayName("§3§lLobby-Wechsler §8» §7§oWähle deine Lobby")
            .create();

    public static final ItemStack COMPASS = new ItemBuilder(Material.COMPASS)
            .displayName("§3§lNavigator §8» §7§oWähle einen Spielmodus")
            .create();

    public static final ItemStack NICK_DISABLED = new ItemBuilder(Material.NAME_TAG, 1, 0)
            .displayName("§c§lNicken §8» §7§oDeaktiviert").lore("§7§oKlicke zum aktivieren")
            .create();

    public static final ItemStack NICK_ACTIVATED = new ItemBuilder(Material.NAME_TAG, 1, 0)
            .displayName("§a§lNicken §8» §7§oAktiviert").lore("§7§oKlicke zum deaktivieren")
            .create();

    public static final ItemStack BACKPACK = new ItemBuilder(Material.STORAGE_MINECART, 1, 0)
            .displayName("§3§lRucksack §8» §7§oZeige deine gesammelten Items an")
            .create();

    public static final ItemStack LOADING = new ItemBuilder(Material.INK_SACK, 1, 2)
            .displayName("§7§oLädt...")
            .create();

    public static final ItemStack STACKING_REMOVE = new ItemBuilder(Material.BARRIER, 1, 0)
            .displayName("§c§lSpieler absetzen")
            .create();


    /*
     * LobbyHider
     */

    public static final ItemStack SETTING_HIDER = new ItemBuilder(Material.INK_SACK, 1, 8)
            .displayName("§3§lSpieler Verstecken §8» §7§oAlle Spieler deaktivert")
            .create();

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
