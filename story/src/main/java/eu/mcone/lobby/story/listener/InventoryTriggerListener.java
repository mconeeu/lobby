/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.story.listener;

import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.api.enums.BankProgress;
import eu.mcone.lobby.api.enums.Item;
import eu.mcone.lobby.api.enums.JumpNRun;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.story.LobbyStory;
import eu.mcone.lobby.story.inventory.story.*;
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

public class InventoryTriggerListener implements Listener {


    @EventHandler
    public void on(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        LobbyPlayer lp = LobbyPlugin.getInstance().getGamePlayer(p);

        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && e.getClickedBlock() != null) {
                Material clicked = e.getClickedBlock().getType();

                switch (clicked) {
                    case CAULDRON: {
                        int progressID = lp.getProgressId();
                        if (progressID >= 6 && progressID < 8 && !Item.MAGICDRINK.has(lp)) {
                            new WitchInventory(p);
                        }
                        return;
                    }
                    case ENDER_CHEST: {
                        Location loc = e.getClickedBlock().getLocation();
                        if (lp.getProgressId() >= 6) {
                            if (loc.getX() == 80 && loc.getY() == 103 && loc.getZ() == -42) {
                                new EndInventory(p);
                            }
                        } else if (lp.getBankprogressId() == BankProgress.CUTTER.getId()) {
                            new WoolInventory(p);
                        } else if (lp.getBankprogressId() == BankProgress.SWORD.getId()) {
                            if (loc.getX() == 12 && loc.getY() == 91 && loc.getZ() == 24) {
                                new SwordInventory(p);
                            }
                        } else if (lp.getBankprogressId() == BankProgress.BANK_ROBBERY_MIDDLE.getId()) {
                            if (loc.getX() == 11 && loc.getY() == 104 && loc.getZ() == -17) {
                                new BankSaveInventory(p);
                            }
                        }
                        return;
                    }
                    case SIGN:
                    case SIGN_POST:
                    case WALL_SIGN: {
                        Sign sign = (Sign) e.getClickedBlock().getState();

                        if (sign.getLine(0).equals("§7»§c Secrets")) {
                            String name = ChatColor.stripColor(sign.getLine(1)).replace("»", "").replace("«", "").trim();

                            if (lp.checkAndAddSecret(name, System.currentTimeMillis() / 1000)) {
                                LobbyPlugin.getInstance().getMessager().send(e.getPlayer(), "§7Du hast das Secret §f" + name + "§7 entdeckt! §8[§a+35 Coins§8]");
                                lp.getCorePlayer().addCoins(35);
                            } else {
                                LobbyPlugin.getInstance().getMessager().send(e.getPlayer(), "§4Du hast dieses §cSecret §4bereits gefunden!");
                            }

                            //JUMP AND RUNS
                        } else if (sign.getLine(0).equals("§7»§c Jump'n'Run")) {
                            for (JumpNRun jumpnrun : JumpNRun.values()) {
                                if (sign.getLine(1).equals(jumpnrun.getJumpandrunname())) {
                                    LobbyWorld.ONE_ISLAND.getWorld().teleport(p, jumpnrun.getWarpLocation());
                                    LobbyPlugin.getInstance().getMessager().send(e.getPlayer(), "Du hast dich zum §f" + jumpnrun.getJumpandrunname() + " §7Jump and Run telepotiert");
                                } else {
                                    LobbyPlugin.getInstance().getMessager().send(e.getPlayer(), "§4Das §c" + sign.getLine(1) + "§4 Jump and Run ist momentan in §oWartungen§4!");
                                }
                            }
                        }
                        return;
                    }
                    case WOOD_BUTTON: {
                        Location loc = e.getClickedBlock().getLocation();
                        if (loc.getX() == 10 && loc.getY() == 104 && loc.getZ() == 1) {
                            if (lp.getBankprogressId() == BankProgress.BANK_ROBBERY_MIDDLE.getId()) {

                                Bukkit.getScheduler().runTaskLaterAsynchronously(LobbyPlugin.getInstance(), () -> {
                                    p.sendMessage("§8[§7§l!§8] §cKnopf im Ohr §8» §fJohn§8|§7 Du bist drin jetzt drück auf der rechten Seite ganz links unten denn Knopf");
                                }, 30L);

                            }
                        } else if (loc.getX() == 13 && loc.getY() == 103 && loc.getZ() == -8) {
                            if (lp.getBankprogressId() == BankProgress.BANK_ROBBERY_MIDDLE.getId()) {
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
                            if (lp.getBankprogressId() == BankProgress.BANK_ROBBERY_MIDDLE.getId()) {

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
                if (lp.getProgressId() == 10) {
                    p.sendMessage("§8[§7§l!§8] §cFunkgerät §8» §fEdward§8|§7 Du hast mit Sparow geredet richtig und du willst mir bestimmnt mitteilen das Marvin gefangen wurde richtig? Das stimmnt es ist ein riesen kracher in One Island aber am besten besorgst du uns ein paar mehr Informationen und dann funk mich wieder an");
                } else {
                    p.sendMessage("§8[§7§l!§8] §cFunkgerät §7» Du hast keine neue Nachricht");
                }
            }


            //JUMPANDRUN

            if (e.hasItem() && e.getItem().getItemMeta().hasDisplayName()) {
                if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§fJump and Run")) {
                    LobbyStory.getInstance().getJumpAndRunManager().setCancel(p);
                } else if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§c§lZurück zum Checkpoint")) {
                    LobbyStory.getInstance().getJumpAndRunManager().tpToCheckpoint(p);
                }
            }

        } else if (e.getAction().equals(Action.PHYSICAL) && e.getClickedBlock() != null) {
            Material clicked = e.getClickedBlock().getType();

            switch (clicked) {
                case GOLD_PLATE: {
                    Location loc = e.getClickedBlock().getLocation();

                    for (JumpNRun jumpnrun : JumpNRun.values()) {
                        Location start = jumpnrun.getStartPlateLocation();
                        Location end = jumpnrun.getEndPlateLocation();

                        if (start != null && loc.equals(jumpnrun.getStartPlateLocation())) {
                            LobbyStory.getInstance().getJumpAndRunManager().setStart(p, jumpnrun);
                        } else if (end != null && loc.equals(jumpnrun.getEndPlateLocation())) {
                            LobbyStory.getInstance().getJumpAndRunManager().setFinish(p);
                        }
                    }
                    break;
                }
                case IRON_PLATE: {
                    if (LobbyStory.getInstance().getJumpAndRunManager().isCurrentlyPlaying(p)) {
                        for (JumpNRun jumpNRun : JumpNRun.values()) {
                            for (int i = 0; i < jumpNRun.getCheckpoints().length; i++) {
                                if (jumpNRun.getCheckpoints()[i].equals(e.getClickedBlock().getLocation())) {
                                    LobbyStory.getInstance().getJumpAndRunManager().setCheckpoint(p, i + 1);
                                    return;
                                }
                            }
                        }
                    }
                    break;
                }
            }
        }

    }


}
