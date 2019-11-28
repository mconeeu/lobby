/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.story.listener;

import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.gamesystem.api.enums.Category;
import eu.mcone.gamesystem.api.enums.Item;
import eu.mcone.gamesystem.api.game.player.GamePlayer;
import eu.mcone.gamesystem.api.lobby.backpack.BackpackInventory;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.api.enums.BankProgress;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.story.inventory.story.*;
import eu.mcone.lobby.story.utils.JumpAndRunManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;

public class InventoryTriggerListener implements Listener {


    @EventHandler
    public void on(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        LobbyPlayer lobbyPlayer = LobbyPlugin.getInstance().getLobbyPlayer(p.getUniqueId());
        GamePlayer gamePlayer = LobbyPlugin.getInstance().getGamePlayer(p.getUniqueId());
        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && e.getClickedBlock() != null) {
                Material clicked = e.getClickedBlock().getType();

                switch (clicked) {
                    case CAULDRON: {
                        int progressID = lobbyPlayer.getProgressId();
                        if (progressID >= 6 && progressID < 8 && !gamePlayer.hasItem(Item.MAGICDRINK)) {
                            new WitchInventory(p);
                        }
                        return;
                    }
                    case ENDER_CHEST: {
                        if (lobbyPlayer.getProgressId() >= 6) {
                            new EndInventory(p);
                        } else if (lobbyPlayer.getBankprogressId() == BankProgress.CUTTER.getId()) {
                            new WoolInventory(p);
                        } else if (lobbyPlayer.getBankprogressId() == BankProgress.SWORD.getId()) {
                            new SwordInventory(p);
                        } else if (lobbyPlayer.getBankprogressId() == BankProgress.BANK_ROBBERY_MIDDLE.getId()) {
                            new BankSaveInventory(p);
                        }
                        return;
                    }
                    case SIGN:
                    case SIGN_POST:
                    case WALL_SIGN: {
                        Sign sign = (Sign) e.getClickedBlock().getState();

                        if (sign.getLine(0).equals("§7»§c Secrets")) {
                            String name = ChatColor.stripColor(sign.getLine(1)).replace("»", "").replace("«", "").trim();

                            if (lobbyPlayer.checkAndAddSecret(name, System.currentTimeMillis() / 1000)) {
                                LobbyPlugin.getInstance().getMessager().send(e.getPlayer(), "§7Du hast das Secret §f" + name + "§7 entdeckt! §8[§a+35 Coins§8]");
                                lobbyPlayer.getCorePlayer().addCoins(35);
                            } else {
                                LobbyPlugin.getInstance().getMessager().send(e.getPlayer(), "§4Du hast dieses §cSecret §4bereits gefunden!");
                            }

                        } else if (sign.getLine(1).equals("§7»§5§l Stripclub§7 «")) {
                            String name = ChatColor.stripColor(sign.getLine(1)).replace("»", "").replace("«", "").trim();
                            LobbyWorld.ONE_ISLAND.getWorld().teleport(p, JumpAndRunManager.JumpAndRunList.STIPCLUB_KIRPHA.getSpawnLocation());
                            LobbyPlugin.getInstance().getMessager().send(e.getPlayer(), "Du hast dich zum §fStripclub Jump and Run telepotiert");
                        }
                        return;
                    }
                    case WOOD_BUTTON: {
                        Location loc = e.getClickedBlock().getLocation();
                        if (loc.getX() == 10 && loc.getY() == 104 && loc.getZ() == 1) {
                            if (lobbyPlayer.getBankprogressId() == BankProgress.BANK_ROBBERY_MIDDLE.getId()) {

                                Bukkit.getScheduler().runTaskLaterAsynchronously(LobbyPlugin.getInstance(), () -> {
                                    p.sendMessage("§8[§7§l!§8] §cKnopf im Ohr §8» §fJohn§8|§7 Du bist drin jetzt drück auf der rechten Seite ganz links unten denn Knopf");
                                }, 30L);

                            }
                        } else if (loc.getX() == 13 && loc.getY() == 103 && loc.getZ() == -8) {
                            if (lobbyPlayer.getBankprogressId() == BankProgress.BANK_ROBBERY_MIDDLE.getId()) {
                                Bukkit.getScheduler().runTaskLaterAsynchronously(LobbyPlugin.getInstance(), () -> {
                                    p.sendMessage("§8[§7§l!§8] §cKnopf im Ohr §8» §fJohn§8|§7 Perfekt du bist drin jetzt klau die Goldbarren in der Truhe!");
                                }, 20L);
                            } else {
                                e.setCancelled(true);
                            }
                        }

                        break;
                    }
                    case STONE_BUTTON: {
                        Location loc = e.getClickedBlock().getLocation();
                        if (loc.getX() == 25 && loc.getY() == 104 && loc.getZ() == 3) {
                            if (lobbyPlayer.getBankprogressId() == BankProgress.BANK_ROBBERY_MIDDLE.getId()) {

                                p.sendMessage("§8[§7§l!§8] §cKnopf im Ohr §8» §fJohn§8|§7 Drücke jetzt gleich bei den Bücher Regalen ein Holz Knopf dann öffnet sich eine Geheime Tür links!");

                            } else {
                                e.setCancelled(true);
                            }
                        }
                        break;
                    }


                    default:
                        e.setCancelled(false);
                }
            }

            if (e.hasItem() && e.getItem().equals(Item.RADIO_SET1.getItemStack())) {
                if (lobbyPlayer.getProgressId() == 10) {
                    p.sendMessage("§8[§7§l!§8] §cFunkgerät §8» §fEdward§8|§7 Du hast mit Sparow geredet richtig und du willst mir bestimmnt mitteilen das Marvin gefangen wurde richtig? Das stimmnt es ist ein riesen kracher in One Island aber am besten besorgst du uns ein paar mehr Informationen und dann funk mich wieder an");
                } else {
                    p.sendMessage("§8[§7§l!§8] §cFunkgerät §7» Du hast keine neue Nachricht");
                }
            }

            if (e.hasItem() && e.getItem().getItemMeta().hasDisplayName()) {
                if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§fJump and Run")) {
                    JumpAndRunManager.playjumpandrun.remove(p);
                    LobbyWorld.ONE_ISLAND.getWorld().teleport(p, "spawn");
                    p.sendMessage("§8[§7§l!§8] §fJump and Run §8» §cDu hast das Jump and Run beendet!");
                    JumpAndRunManager.lobbyitems(p);
                }
            }

        } else if (e.getAction().equals(Action.PHYSICAL) && e.getClickedBlock() != null) {
            Material clicked = e.getClickedBlock().getType();

            switch (clicked) {
                case GOLD_PLATE: {
                    JumpAndRunManager.setPlayStripClubJumpAndRun(p);
                    break;
                }
            }
        }

    }


}
