/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.story.listener;

import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.api.enums.BankProgress;
import eu.mcone.lobby.api.enums.JumpNRun;
import eu.mcone.lobby.api.enums.LobbyItem;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.items.manager.OfficeManager;
import eu.mcone.lobby.story.inventory.john.JohnBankRobberyInventory;
import eu.mcone.lobby.story.inventory.story.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);

        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && e.getClickedBlock() != null) {
                Material clicked = e.getClickedBlock().getType();

                switch (clicked) {
                    case CAULDRON: {
                        int progressID = lp.getProgressId();
                        if (progressID >= 6 && progressID < 8 && !lp.hasLobbyItem(LobbyItem.MAGICDRINK)) {
                            new WitchInventory(p);
                        }
                        return;
                    }
                    case ENDER_CHEST: {
                        if (lp.getProgressId() >= 6) {
                            if (LobbyWorld.ONE_ISLAND.getWorld().getBlockLocation("end-chest").equals(e.getClickedBlock().getLocation())) {
                                new EndInventory(p);
                            }
                        }
                        if (lp.getBankprogressId() == BankProgress.CUTTER.getId()) {
                            if (LobbyWorld.ONE_ISLAND.getWorld().getBlockLocation("bank-robbery-wool-chest").equals(e.getClickedBlock().getLocation())) {
                                new WoolInventory(p);
                            }
                        } else if (lp.getBankprogressId() == BankProgress.SWORD.getId()) {
                            if (LobbyWorld.PARADISE_ISLAND.getWorld().getBlockLocation("bank-robbery-sword-chest").equals(e.getClickedBlock().getLocation())) {
                                new SwordInventory(p);
                            }
                        } else if (lp.getBankprogressId() == BankProgress.BANK_ROBBERY_MIDDLE.getId()) {
                            if (LobbyWorld.ONE_ISLAND.getWorld().getBlockLocation("bank-robbery-safe-chest").equals(e.getClickedBlock().getLocation())) {
                                new BankSafeInventory(p);
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
                                LobbyPlugin.getInstance().getMessenger().send(e.getPlayer(), "§7Du hast das Secret §f" + name + "§7 entdeckt! §8[§a+35 Coins§8]");
                                lp.getCorePlayer().addCoins(35);
                            } else {
                                LobbyPlugin.getInstance().getMessenger().send(e.getPlayer(), "§4Du hast dieses §cSecret §4bereits gefunden!");
                            }

                            //JUMP AND RUNS
                        } else if (sign.getLine(0).equals("§7»§c Jump'n'Run")) {
                            for (JumpNRun jumpnrun : JumpNRun.values()) {
                                if (sign.getLine(1).equals(jumpnrun.getJumpandrunname())) {
                                    if (LobbyPlugin.getInstance().getOneHitManager().isFighting(p) || LobbyPlugin.getInstance().getCatchManager().isCatching(p)) {
                                        LobbyPlugin.getInstance().getMessenger().send(e.getPlayer(), "§4Du darfst im moment keine Jump and Runs spielen, weil du gerade ein Lobbygame spielst!");
                                        return;
                                    }
                                    LobbyWorld.ONE_ISLAND.getWorld().teleport(p, jumpnrun.getWarpLocation());
                                    LobbyPlugin.getInstance().getMessenger().send(e.getPlayer(), "Du hast dich zum §f" + jumpnrun.getJumpandrunname() + " §7Jump and Run telepotiert");
                                    return;
                                }
                            }

                            LobbyPlugin.getInstance().getMessenger().send(e.getPlayer(), "§4Das §c" + sign.getLine(1) + "§4 Jump and Run ist momentan in §oWartungen§4!");
                        }
                        return;
                    }
                    case WOOD_BUTTON: {
                        if (LobbyWorld.ONE_ISLAND.getWorld().getBlockLocation("bank-robbery-entrance-button").equals(e.getClickedBlock().getLocation())) {
                            if (lp.getBankprogressId() == BankProgress.BANK_ROBBERY_MIDDLE.getId()) {

                                Bukkit.getScheduler().runTaskLaterAsynchronously(LobbyPlugin.getInstance(), () -> {
                                    p.sendMessage("§8[§7§l!§8] §cKnopf im Ohr §8» §fJohn§8 |§7 Du bist drin jetzt drück auf der rechten Seite ganz links unten den Knopf");
                                }, 35L);

                            }
                        } else if (LobbyWorld.ONE_ISLAND.getWorld().getBlockLocation("bank-robbery-safe-entrance-button").equals(e.getClickedBlock().getLocation())) {
                            if (lp.getBankprogressId() == BankProgress.BANK_ROBBERY_MIDDLE.getId()) {
                                Bukkit.getScheduler().runTaskLaterAsynchronously(LobbyPlugin.getInstance(), () -> {
                                    p.sendMessage("§8[§7§l!§8] §cKnopf im Ohr §8» §fJohn§8 |§7 Super du bist drin jetzt klau die Goldbarren in der Truhe!");
                                }, 20L);
                            } else {
                                e.setCancelled(true);
                            }
                        } else if (LobbyWorld.ONE_ISLAND.getWorld().getBlockLocation("bank-robbery-end").equals(e.getClickedBlock().getLocation())) {
                            LobbyWorld.ONE_ISLAND.getWorld().getNPC("JohnEnd").toggleVisibility(p, false);
                            JohnBankRobberyInventory.currentlyInBank = null;
                            OfficeManager.getOffice(p);
                            lp.setBankProgress(BankProgress.BANK_ROBBERY_END);
                            p.sendMessage("§8[§7§l!§8] §cNPC §8» §fJohn §8|§7 Wir haben es geschafft ich überlasse dir 25.000 Coins und ein kleines Geschenk im Rucksack, wir sehen uns!");
                            lp.getCorePlayer().addCoins(25000);

                            lp.removeLobbyItem(LobbyItem.GOLD_BARDING);
                            lp.removeLobbyItem(LobbyItem.BANK_MAP);
                            lp.removeLobbyItem(LobbyItem.IRON_SWORD);
                            lp.removeLobbyItem(LobbyItem.BUTTON);
                            lp.removeLobbyItem(LobbyItem.BANK_OUTFIT);
                            lp.addLobbyItem(LobbyItem.GOLD_NUGGET);

                            if (p.hasPermission("lobby.silenthub")) {
                                p.getInventory().setItem(3, null);
                            } else {
                                p.getInventory().setItem(2, null);
                            }

                            p.getInventory().setLeggings(null);
                            p.getInventory().setBoots(null);
                            p.getInventory().setChestplate(null);
                        }

                        break;
                    }
                    case STONE_BUTTON: {
                        if (LobbyWorld.ONE_ISLAND.getWorld().getBlockLocation("bank-robbery-main-entrance-button").equals(e.getClickedBlock().getLocation())) {
                            if (lp.getBankprogressId() == BankProgress.BANK_ROBBERY_MIDDLE.getId()) {

                                p.sendMessage("§8[§7§l!§8] §cKnopf im Ohr §8» §fJohn§8 |§7 Drücke jetzt gleich bei den Bücher Regalen ein Holz Knopf dann öffnet sich eine Geheime Tür rechts!");

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

            if (e.hasItem() && e.getItem().equals(LobbyItem.RADIO_SET1.getItemStack())) {
                if (lp.getProgressId() == 10) {
                    p.sendMessage("§8[§7§l!§8] §cFunkgerät §8» §fEdward§8|§7 Du hast mit Sparow geredet richtig und du willst mir bestimmnt mitteilen das Marvin gefangen wurde richtig? Das stimmnt es ist ein riesen kracher in One Island aber am besten besorgst du uns ein paar mehr Informationen und dann funk mich wieder an");
                } else {
                    p.sendMessage("§8[§7§l!§8] §cFunkgerät §7» Du hast keine neue Nachricht");
                }
            }
        }

    }


}
