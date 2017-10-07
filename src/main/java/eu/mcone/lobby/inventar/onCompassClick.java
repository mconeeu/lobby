/*
 * Copyright (c) 2017 Dominik L., BamDev, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.inventar;

import eu.mcone.lobby.Main;
import eu.mcone.lobby.utils.Factory;
import org.bukkit.*;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class onCompassClick implements Listener{

    public static Location getConfigLocation(String path, YamlConfiguration cfg){

        World w = (null);
        double x = cfg.getDouble(path + ".X");
        double y = cfg.getDouble(path + ".y");
        double z = cfg.getDouble(path + ".Z");
        float yaw = (float) cfg.getDouble(path + ".Yaw");
        float pitch = (float) cfg.getDouble(path + ".Pitch");

        return new Location(w, x, y, z, yaw, pitch);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (p.getGameMode().equals(GameMode.CREATIVE)) {
            e.setCancelled(false);
        } else {
            e.setCancelled(true);
        }

        if (e.getCurrentItem() != null) {
            try {

                if (e.getInventory().getName().equals("§8» §3Navigator")) {
                    e.setCancelled(true);
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Main.cfg.getString("Lobby-Navigator-1-Name").replaceAll("&", "§").replaceAll(">>", "»"))) {
                        p.teleport(Factory.getConfigLocation("Navigator-1", Factory.cfg));
                        p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
                        p.playEffect(p.getLocation(), Effect.BLAZE_SHOOT, 1);

                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Main.cfg.getString("Lobby-Navigator-2-Name").replaceAll("&", "§").replaceAll(">>", "»"))) {
                        p.teleport(Factory.getConfigLocation("Navigator-2", Factory.cfg));
                        p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
                        p.playEffect(p.getLocation(), Effect.BLAZE_SHOOT, 1);

                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Main.cfg.getString("Lobby-Navigator-3-Name").replaceAll("&", "§").replaceAll(">>", "»"))) {
                        p.teleport(Factory.getConfigLocation("Navigator-3", Factory.cfg));
                        p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
                        p.playEffect(p.getLocation(), Effect.BLAZE_SHOOT, 1);

                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Main.cfg.getString("Lobby-Navigator-4-Name").replaceAll("&", "§").replaceAll(">>", "»"))) {
                        p.teleport(Factory.getConfigLocation("Navigator-4", Factory.cfg));
                        p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
                        p.playEffect(p.getLocation(), Effect.BLAZE_SHOOT, 1);

                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Main.cfg.getString("Lobby-Navigator-5-Name").replaceAll("&", "§").replaceAll(">>", "»"))) {
                        p.teleport(Factory.getConfigLocation("Navigator-5", Factory.cfg));
                        p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
                        p.playEffect(p.getLocation(), Effect.BLAZE_SHOOT, 1);

                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Main.cfg.getString("Lobby-Navigator-6-Name").replaceAll("&", "§").replaceAll(">>", "»"))) {
                        p.teleport(Factory.getConfigLocation("Navigator-6", Factory.cfg));
                        p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
                        p.playEffect(p.getLocation(), Effect.BLAZE_SHOOT, 1);

                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Main.cfg.getString("Lobby-Navigator-7-Name").replaceAll("&", "§").replaceAll(">>", "»"))) {
                        p.teleport(Factory.getConfigLocation("Navigator-7", Factory.cfg));
                        p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
                        p.playEffect(p.getLocation(), Effect.BLAZE_SHOOT, 1);

                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Main.cfg.getString("Lobby-Navigator-8-Name").replaceAll("&", "§").replaceAll(">>", "»"))) {
                        p.teleport(Factory.getConfigLocation("Navigator-8", Factory.cfg));
                        p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
                        p.playEffect(p.getLocation(), Effect.BLAZE_SHOOT, 1);

                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Main.cfg.getString("Lobby-Navigator-9-Name").replaceAll("&", "§").replaceAll(">>", "»"))) {
                        p.teleport(Factory.getConfigLocation("Navigator-9", Factory.cfg));
                        p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
                        p.playEffect(p.getLocation(), Effect.BLAZE_SHOOT, 1);

                    }
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        }
    }
}