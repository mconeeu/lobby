/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.story.listener;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.gameapi.api.GameAPI;
import eu.mcone.gameapi.api.GamePlugin;
import eu.mcone.gameapi.api.backpack.defaults.DefaultItem;
import eu.mcone.gameapi.api.player.GamePlayer;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.api.items.LobbyItem;
import eu.mcone.lobby.api.story.progress.bank.BankRobberySmallProgress;
import eu.mcone.lobby.api.story.progress.bank.central.BankProgress;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.story.LobbyStory;
import eu.mcone.lobby.story.inventory.john.JohnBankRobberyInventory;
import eu.mcone.lobby.story.inventory.story.bank.BankInfosInventory;
import eu.mcone.lobby.story.inventory.story.bank.BankSafeInventory;
import eu.mcone.lobby.story.inventory.story.bank.SwordInventory;
import eu.mcone.lobby.story.inventory.story.bank.WoolInventory;
import eu.mcone.lobby.story.inventory.story.chapter1.EndInventory;
import eu.mcone.lobby.story.inventory.story.chapter1.WitchInventory;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
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
                        if (lp.getBankprogressId() == BankRobberySmallProgress.CUTTER.getId()) {
                            if (LobbyWorld.ONE_ISLAND.getWorld().getBlockLocation("bank-robbery-wool-chest").equals(e.getClickedBlock().getLocation())) {
                                new WoolInventory(p);
                            }
                        } else if (lp.getBankprogressId() == BankRobberySmallProgress.SWORD.getId()) {
                            if (LobbyWorld.PARADISE_ISLAND.getWorld().getBlockLocation("bank-robbery-sword-chest").equals(e.getClickedBlock().getLocation())) {
                                new SwordInventory(p);
                            }
                        } else if (lp.getBankprogressId() == BankRobberySmallProgress.BANK_ROBBERY_MIDDLE.getId()) {
                            if (LobbyWorld.ONE_ISLAND.getWorld().getBlockLocation("bank-robbery-safe-chest").equals(e.getClickedBlock().getLocation())) {
                                new BankSafeInventory(p);
                            }
                        }

                        if (lp.getCentralbankprogressId() == BankProgress.SPY.getId()) {
                            if (LobbyWorld.ONE_ISLAND.getWorld().getBlockLocation("bank-central-info1").equals(e.getClickedBlock().getLocation())) {
                                new BankInfosInventory(p, LobbyItem.NORMAL_FILES_1);
                            } else if (LobbyWorld.ONE_ISLAND.getWorld().getBlockLocation("bank-central-info2").equals(e.getClickedBlock().getLocation())) {
                                new BankInfosInventory(p, LobbyItem.NORMAL_FILES_2);
                            } else if (LobbyWorld.ONE_ISLAND.getWorld().getBlockLocation("bank-central-info3").equals(e.getClickedBlock().getLocation())) {
                                new BankInfosInventory(p, LobbyItem.NORMAL_FILES_3);
                            } else if (LobbyWorld.ONE_ISLAND.getWorld().getBlockLocation("bank-central-info4").equals(e.getClickedBlock().getLocation())) {
                                new BankInfosInventory(p, LobbyItem.NORMAL_FILES_1);
                            }
                        } else {
                            LobbyPlugin.getInstance().getMessenger().sendError(p, "Diese ![Kiste] wurde abgeschlossen!");
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
                                if (lp.getSecretsCount() >= 15) {
                                    GamePlayer gamePlayer = LobbyPlugin.getInstance().getGamePlayer(p);
                                    if (!gamePlayer.hasDefaultItem(DefaultItem.HEAD_GIFT_SECRETS)) {
                                        gamePlayer.addOnePassXp(GamePlugin.getGamePlugin().getOnePassManager().getSecretAward());
                                        gamePlayer.addDefaultItem(DefaultItem.HEAD_GIFT_SECRETS);
                                        GameAPI.getInstance().getMessenger().send(p, "§2Du hast alle §315 Secrets§2 entdeckt dafür hast du §3" + GamePlugin.getGamePlugin().getOnePassManager().getSecretAward() + " Xp §2im OnePass erhalten!");
                                    }
                                }
                                LobbyPlugin.getInstance().getMessenger().send(e.getPlayer(), "§7Du hast das Secret §f" + name + "§7 entdeckt! §8[§a+35 Coins§8]");
                                lp.getCorePlayer().addCoins(35);
                            } else {
                                LobbyPlugin.getInstance().getMessenger().send(e.getPlayer(), "§4Du hast dieses §cSecret §4bereits gefunden!");
                            }
                        }
                        return;
                    }
                    case WOOD_BUTTON: {
                        //central bank entrance // always close
                        if (LobbyWorld.ONE_ISLAND.getWorld().getBlockLocation("bank-central-entrance").equals(e.getClickedBlock().getLocation())) {
                            e.setCancelled(true);
                        } else if (LobbyWorld.ONE_ISLAND.getWorld().getBlockLocation("bank-robbery-entrance-button").equals(e.getClickedBlock().getLocation())) {
                            if (lp.getBankprogressId() == BankRobberySmallProgress.BANK_ROBBERY_MIDDLE.getId()) {

                                Bukkit.getScheduler().runTaskLaterAsynchronously(LobbyPlugin.getInstance(), () -> {
                                    p.sendMessage("§8[§7§l!§8] §cKnopf im Ohr §8» §fJohn§8 |§7 Du bist drin jetzt drück auf der rechten Seite ganz links unten den Knopf");
                                }, 35L);

                            }
                        } else if (LobbyWorld.ONE_ISLAND.getWorld().getBlockLocation("bank-robbery-safe-entrance-button").equals(e.getClickedBlock().getLocation())) {
                            if (lp.getBankprogressId() == BankRobberySmallProgress.BANK_ROBBERY_MIDDLE.getId()) {
                                Bukkit.getScheduler().runTaskLaterAsynchronously(LobbyPlugin.getInstance(), () -> {
                                    p.sendMessage("§8[§7§l!§8] §cKnopf im Ohr §8» §fJohn§8 |§7 Super du bist drin jetzt klau die Goldbarren in der Truhe!");
                                }, 20L);
                            } else {
                                e.setCancelled(true);
                            }
                        } else if (LobbyWorld.ONE_ISLAND.getWorld().getBlockLocation("bank-robbery-end").equals(e.getClickedBlock().getLocation())) {
                            if (lp.getBankprogressId() == BankRobberySmallProgress.BANK_ROBBERY_MIDDLE.getId()) {
                                LobbyWorld.ONE_ISLAND.getWorld().getNPC("JohnEnd").toggleVisibility(p, false);
                                JohnBankRobberyInventory.currentlyInBank = null;
                                LobbyStory.getInstance().getOfficeManager().joinOffice(p);
                                lp.setBankProgress(BankRobberySmallProgress.BANK_ROBBERY_END);
                                p.sendMessage("§8[§7§l!§8] §cNPC §8» §fJohn §8|§7 Wir haben es §fgeschafft§7 ich überlasse dir §f25.000 Coins §7und ein kleines Geschenk im Rucksack, wir sehen uns!");
                                lp.getCorePlayer().addCoins(25000);

                                LobbyPlugin.getInstance().getPlayerSounds().playSounds(p, Sound.LEVEL_UP);
                                LobbyPlugin.getInstance().getPlayerSounds().playSounds(p, Sound.NOTE_PIANO);

                                JohnBankRobberyInventory.robberyTime.cancel();

                                CoreSystem.getInstance().getLabyModAPI().setCurrentServer(p, "MCONE-Lobby");
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
                        }

                        break;
                    }
                    case STONE_BUTTON: {
                        if (LobbyWorld.ONE_ISLAND.getWorld().getBlockLocation("bank-robbery-main-entrance-button").equals(e.getClickedBlock().getLocation())) {
                            if (lp.getBankprogressId() == BankRobberySmallProgress.BANK_ROBBERY_MIDDLE.getId()) {

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
                    p.sendMessage("§8[§7§l!§8] §cFunkgerät §8» §fEdward§8 |§7 Du hast mit Sparow geredet oder? Und du willst mir bestimmt mitteilen das Marvin gefangen wurde oder? Das stimmt dieser Artikel ist ein riesen Kracher in der One Island Zeitung aber am besten besorgst du uns ein paar mehr Informationen und dann funkst du mich einfach wieder an.");
                } else {
                    p.sendMessage("§8[§7§l!§8] §cFunkgerät §7» Du hast keine neue Nachricht");
                }
            }
        }

    }


}
