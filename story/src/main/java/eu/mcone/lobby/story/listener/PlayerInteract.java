/*
 * Copyright (c) 2017 - 2018 Dominik Lippl, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.story.listener;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.enums.Item;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.story.inventory.story.EndInventory;
import eu.mcone.lobby.story.inventory.story.WitchInventory;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;

public class PlayerInteract implements Listener {

    @EventHandler
    public void on(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p.getUniqueId());

        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && e.getClickedBlock() != null) {
                Material clicked = e.getClickedBlock().getType();
                e.setCancelled(true);

                switch (clicked) {
                    case CAULDRON: {
                        int progressID = lp.getProgressId();
                        if (progressID >= 6 && progressID < 8 && !lp.hasItem(Item.MAGICDRINK)) {
                            new WitchInventory(p);
                        }
                        return;
                    }
                    case ENDER_CHEST: {
                        if (lp.getProgressId() >= 6) {
                            new EndInventory(p);
                        }
                        return;
                    }
                    case SIGN:
                    case SIGN_POST:
                    case WALL_SIGN: {
                        Sign sign = (Sign) e.getClickedBlock().getState();

                        if (sign.getLine(0).equals("§7»§c Secrets")) {
                            String name = sign.getLine(1).replace("§7§l", "");
                            long millis = System.currentTimeMillis() / 1000;

                            Bukkit.getScheduler().runTaskAsynchronously(LobbyPlugin.getInstance(), () -> {
                                Document entry = CoreSystem.getInstance().getMongoDB().getCollection("lobby_secrets").find(
                                        combine(
                                                eq("uuid", e.getPlayer().getUniqueId().toString()),
                                                eq("secret", name)
                                        )
                                ).first();

                                if (entry != null) {
                                    e.getPlayer().sendMessage(CoreSystem.getInstance().getTranslationManager().get("lobby.prefix") + "§7Du hast das Secret §c" + name + "§7 entdeckt! §8[§e+§a 35 Coins§8]");
                                    Bukkit.getScheduler().runTaskAsynchronously(LobbyPlugin.getInstance(), () -> {
                                        CoreSystem.getInstance().getMongoDB().getCollection("lobby_secrets").insertOne(
                                                new Document("uuid", e.getPlayer().getUniqueId().toString())
                                                        .append("secret", name)
                                                        .append("timestamp", millis)
                                        );
                                        CoreSystem.getInstance().getCorePlayer(p).addCoins(35);
                                    });
                                } else {
                                    e.getPlayer().sendMessage(CoreSystem.getInstance().getTranslationManager().get("lobby.prefix") + "§7Du hast dieses §4Secret §7bereits gefunden!");
                                }
                            });
                        }
                        return;
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
        }
    }

}
