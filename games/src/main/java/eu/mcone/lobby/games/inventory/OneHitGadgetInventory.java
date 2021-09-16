/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.games.inventory;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.facades.Msg;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.games.pvp.onehit.OneHitItem;
import eu.mcone.lobby.games.pvp.onehit.OneHitLobbyGame;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.DisplaySlot;

public class OneHitGadgetInventory extends CoreInventory {

    public OneHitGadgetInventory(Player p, OneHitLobbyGame game) {
        super("§8» §3§lGadgets", p, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);

        setItem(InventorySlot.ROW_2_SLOT_3, new ItemBuilder(Material.ARROW, 1, 0)
                        .displayName("§fOneHit-Pfeil")
                        .lore("§7Um dieses Item kaufen zu können", "§7benötigst du eine §a2er Killstreak")
                        .create(),
                e -> {
                    if (game.isPlaying(p)) {
                        if (p.getLevel() >= 2) {
                            p.setLevel(p.getLevel() - 2);

                            p.getInventory().setItem(5, OneHitItem.ONEHIT_ARROW);
                            Msg.send(p, "§7Du hast erfolgreich ein §fPfeil §7für eine §f2er Killstreak gekauft!");
                            p.closeInventory();
                        } else {
                            p.closeInventory();
                            Msg.send(p, "§4Du benötigst eine 2er Killstreak!");
                        }
                    }
                }
        );

        setItem(InventorySlot.ROW_2_SLOT_4, new ItemBuilder(Material.POTION, 1, 0)
                        .displayName("§fOneHit-Boost | 10sek")
                        .lore("§7Um dieses Item kaufen zu können", "§7benötigst du eine §a1er Killstreak", "§4Achtung: Du erhälst den Jumpboost sofort!")
                        .create(),
                e -> {
                    if (game.isPlaying(p)) {
                        if (p.getLevel() >= 1) {
                            p.setLevel(p.getLevel() - 1);

                            p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 220, 2));
                            Msg.send(p, "§7Du hast erfolgreich den §fJumpboost §7für eine §f1er Killstreak gekauft!");
                            p.closeInventory();
                        } else {
                            p.closeInventory();
                            Msg.send(p, "§4Du benötigst eine 1er Killstreak!");
                        }
                    }

                }
        );

        setItem(InventorySlot.ROW_2_SLOT_5, new ItemBuilder(Material.POTION, 1, 0)
                        .displayName("§fOneHit-Speed | 10sek")
                        .lore("§7Um dieses Item kaufen zu können", "§7benötigst du eine §a1er Killstreak", "§4Achtung: Du erhälst den Speedboost sofort!")
                        .create(),
                e -> {
                    if (game.isPlaying(p)) {
                        if (p.getLevel() >= 1) {
                            p.setLevel(p.getLevel() - 1);

                            p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 220, 1));
                            Msg.send(p, "§7Du hast erfolgreich den §fSpeedboost §7für eine §f1er Killstreak gekauft!");
                            p.closeInventory();
                        } else {
                            p.closeInventory();
                            Msg.send(p, "§4Du benötigst eine 1er Killstreak!");
                        }
                    }
                }
        );

        setItem(InventorySlot.ROW_2_SLOT_6, new ItemBuilder(Material.SNOW_BALL, 1, 0)
                        .displayName("§fOneHit-Schneball")
                        .lore("§7Um dieses Item kaufen zu können", "§7benötigst du eine §a3er Killstreak")
                        .create(),

                e -> {
                    if (game.isPlaying(p)) {
                        if (p.getLevel() >= 3) {
                            p.setLevel(p.getLevel() - 3);
                            LobbyPlayer lobbyPlayer = LobbyPlugin.getInstance().getLobbyPlayer(p);
                            if (lobbyPlayer.getSettings().isScoreboard()) {
                                CoreSystem.getInstance().getCorePlayer(p).getScoreboard().getObjective(DisplaySlot.SIDEBAR).reload();
                            }

                            p.getInventory().setItem(2, OneHitItem.ONEHIT_SNOWBALL);
                            Msg.send(p, "§7Du hast erfolgreich den §fSchneball §7für eine §f3er Killstreak gekauft!");
                            p.closeInventory();
                        } else {
                            p.closeInventory();
                            Msg.send(p, "§4Du benötigst eine 3er Killstreak!");
                        }
                    }
                }
        );

        setItem(InventorySlot.ROW_2_SLOT_7, new ItemBuilder(Material.FEATHER, 1, 0)
                        .displayName("§fOneHit-Doppel Sprung")
                        .lore("§7Um dieses Item kaufen zu können", "§7benötigst du eine §a2er Killstreak", "§4Du kannst 1x mal Doppelt springen!")
                        .create(),
                e -> {
                    if (game.isPlaying(p)) {
                        if (p.getLevel() >= 2) {
                            p.setLevel(p.getLevel() - 2);
                            LobbyPlayer lobbyPlayer = LobbyPlugin.getInstance().getLobbyPlayer(p);
                            if (lobbyPlayer.getSettings().isScoreboard()) {
                                CoreSystem.getInstance().getCorePlayer(p).getScoreboard().getObjective(DisplaySlot.SIDEBAR).reload();
                            }

                            p.setGameMode(GameMode.SURVIVAL);
                            p.setAllowFlight(true);

                            CoreSystem.getInstance().createActionBar().message("§fDoppelsprung bereit").send(p);

                            Msg.send(p, "§7Du hast erfolgreich den §fDoppel Sprung §7für eine §f2 Killstreak gekauft!");
                            p.closeInventory();
                        } else {
                            p.closeInventory();
                            Msg.send(p, "§4Du benötigst eine 2er Killstreak!");
                        }
                    }
                }
        );


        openInventory();
    }
}