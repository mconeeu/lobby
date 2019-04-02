/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.story.listener;

import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.enums.Item;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.story.inventory.story.EndInventory;
import eu.mcone.lobby.story.inventory.story.WitchInventory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

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
                            String name = ChatColor.stripColor(sign.getLine(1)).replace("»", "").replace("«", "").trim();

                            if (lp.checkAndAddSecret(name, System.currentTimeMillis() / 1000)) {
                                LobbyPlugin.getInstance().getMessager().send(e.getPlayer(), "§7Du hast das Secret §f" + name + "§7 entdeckt! §8[§a+35 Coins§8]");
                                lp.getCorePlayer().addCoins(35);
                            } else {
                                LobbyPlugin.getInstance().getMessager().send(e.getPlayer(), "§4Du hast dieses §cSecret §4bereits gefunden!");
                            }
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
