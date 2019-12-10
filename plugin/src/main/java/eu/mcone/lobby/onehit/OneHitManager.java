package eu.mcone.lobby.onehit;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.item.Skull;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.lobby.Lobby;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.api.enums.JumpNRun;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.story.LobbyStory;
import eu.mcone.lobby.story.jumpnrun.JumpNRunPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Copyright (c) 2018 - 2019 © All rights reserved
 * You are not allowed to decompile the codee
 * Created by Marvin Hülsmann on 09.12.2019 in lobby.
 */


public class OneHitManager {

    public static ArrayList<Player> isFighting = new ArrayList<>();

    public static void setStart(Player p) {
        if (!isFighting.contains(p)) {
            oneHitFightItems(p);
            isFighting.add(p);

            randomeSpawnLocation(p);

            LobbyPlugin.getInstance().getMessager().send(p, "§cTöte alle Spieler mit einen roten Helm!");
            LobbyPlugin.getInstance().getBackpackManager().getPetHandler().despawnPet(p);
        }
    }


    public static void cancelTask(Player p) {
        if (isFighting.contains(p)) {
            isFighting.remove(p);
            p.getInventory().clear();
            LobbyWorld.ONE_ISLAND.getWorld().teleportSilently(p, "spawn");
            lobbyitems(p);
        }
    }

    public static void randomeSpawnLocation(Player p) {

        String Loc1 = "Onehit-1";
        String Loc2 = "Onehit-2";
        String Loc3 = "Onehit-3";
        String Loc4 = "Onehit-4";
        String Loc5 = "Onehit-5";
        String Loc6 = "Onehit-6";

///
        int max = 6;
        Random r = new Random();
        int randomNum = r.nextInt(max);

        if (randomNum == 1) {
            LobbyWorld.ONE_ISLAND.getWorld().teleportSilently(p, Loc1);
        } else if (randomNum == 2) {
            LobbyWorld.ONE_ISLAND.getWorld().teleportSilently(p, Loc2);
        } else if (randomNum == 3) {
            LobbyWorld.ONE_ISLAND.getWorld().teleportSilently(p, Loc3);
        } else if (randomNum == 4) {
            LobbyWorld.ONE_ISLAND.getWorld().teleportSilently(p, Loc4);
        } else if (randomNum == 5) {
            LobbyWorld.ONE_ISLAND.getWorld().teleportSilently(p, Loc5);
        } else if (randomNum == 6) {
            LobbyWorld.ONE_ISLAND.getWorld().teleportSilently(p, Loc6);
        }
    }

    public static void oneHitFightItems(Player p) {
        p.getInventory().clear();


        randomeSpawnLocation(p);

        p.setGameMode(GameMode.SURVIVAL);
        p.getActivePotionEffects().clear();
        p.removePotionEffect(PotionEffectType.INVISIBILITY);

        p.getInventory().setHelmet(ItemBuilder.createLeatherArmorItem(Material.LEATHER_HELMET, Color.RED).create());


        p.setMaxHealth(2);
        p.setHealth(1);
        p.setFoodLevel(20);

        p.getInventory().setItem(0, new ItemBuilder(Material.IRON_SWORD, 1, 0).unbreakable(true).displayName("§fOneHit-Schwert").create());
        p.getInventory().setItem(1, new ItemBuilder(Material.BOW, 1, 0).unbreakable(true).displayName("§cOneHit-Bogen").create());
        p.getInventory().setItem(7, new ItemBuilder(Material.ARROW, 1, 0).displayName("§bOneHit-Pfeil").create());

        p.getInventory().setItem(8, new ItemBuilder(Material.IRON_DOOR, 1).displayName("§4Verlassen").create());
    }

    public static void lobbyitems(Player p) {
        p.getInventory().clear();


        p.getInventory().clear();
        p.getInventory().setArmorContents(null);
        p.setGameMode(GameMode.SURVIVAL);
        p.removePotionEffect(PotionEffectType.INVISIBILITY);
        p.getActivePotionEffects().clear();

        p.setMaxHealth(2);
        p.setHealth(2);
        p.setFoodLevel(20);


        CorePlayer cp = CoreSystem.getInstance().getCorePlayer(p);

        p.getInventory().setItem(0, new ItemBuilder(Material.INK_SACK, 1, 10).displayName("§3§lSpieler Verstecken §8» §7§oBlende alle anderen Spieler aus").create());
        p.getInventory().setItem(1, new ItemBuilder(Material.NETHER_STAR, 1, 0).displayName("§3§lLobby-Wechsler §8» §7§oWähle deine Lobby").create());

        p.getInventory().setItem(4, new ItemBuilder(Material.COMPASS, 1, 0).displayName("§3§lNavigator §8» §7§oWähle einen Spielmodus").create());

        if (p.hasPermission("system.bungee.nick")) {
            p.getInventory().setItem(6, cp.isNicked() ?
                    new ItemBuilder(Material.NAME_TAG, 1, 0).displayName("§a§lNicken §8» §7§oAktiviert").lore("§7§oKlicke zum deaktivieren").create() :
                    new ItemBuilder(Material.NAME_TAG, 1, 0).displayName("§c§lNicken §8» §7§oDeaktiviert").lore("§7§oKlicke zum aktivieren").create()
            );
        }

        p.getInventory().setItem(7, new ItemBuilder(Material.STORAGE_MINECART, 1, 0).displayName("§3§lRucksack §8» §7§oZeige deine gesammelten Items an").create());

        if (p.hasPermission("lobby.silenthub")) {
            p.getInventory().setItem(2, new ItemBuilder(Material.TNT, 1, 0).displayName("§6§lPrivate Lobby §8» §7§oBetrete deine eigene Private Lobby").create());
        }

        p.getInventory().setItem(8, new Skull(p.getName(), 1).toItemBuilder().displayName("§3§lProfil §8» §7§oEinstellungen / Stats / Freunde").create());

        LobbyPlugin.getInstance().getBackpackManager().setRankBoots(p);
    }

}






