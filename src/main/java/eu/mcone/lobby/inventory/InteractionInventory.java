/*
 * Copyright (c) 2017 Dominik L., Rufus Maiwald, BamDev and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.inventory;

import eu.mcone.bukkitcoresystem.CoreSystem;
import eu.mcone.bukkitcoresystem.channel.PluginMessage;
import eu.mcone.bukkitcoresystem.util.ItemFactory;
import eu.mcone.lobby.Lobby;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.meta.SkullMeta;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class InteractionInventory {

    public InteractionInventory(Player p, Player clicked) {
        CoreSystem.mysql1.select("SELECT status, coins, onlinetime FROM userinfo WHERE uuid='" + clicked.getUniqueId().toString() + "'", rs -> {
            try {
                if (rs.next()) {
                    double onlinetime = Math.floor((rs.getInt("onlinetime") / 60) * 100) / 100;
                    int coins = rs.getInt("coins");
                    String status = getStatus(rs.getString("status"));

                    Inventory inv = org.bukkit.Bukkit.createInventory(null, 27, "§8» §3Interaktionsmenü");

                    for (int i = 0; i <= 26; i++) {
                        inv.setItem(i, ItemFactory.createItem(Material.STAINED_GLASS_PANE, 7, 1, "§8//§oMCONE§8//", true));
                    }
                    inv.setItem(4, ItemFactory.createSkullItem("§f§l" + clicked.getName(), clicked.getName(), 1, new ArrayList<>(Arrays.asList(CoreSystem.getCorePlayer(clicked).getGroup().getLabel(), "","§7Coins: §f" + coins , "§7Onlinetime: §f" + onlinetime + " Stunden", "§7Status: " + status))));

                    inv.setItem(20, ItemFactory.createCustomSkullItem("§7Online-Profil Ansehen", "http://textures.minecraft.net/texture/6f74f58f541342393b3b16787dd051dfacec8cb5cd3229c61e5f73d63947ad", 1, new ArrayList<>()));
                    CoreSystem.mysql1.select("SELECT uuid FROM `bungeesystem_friends` WHERE `uuid`='"+p.getUniqueId()+"' AND `target`='"+clicked.getUniqueId()+"' AND `key`='friend';", rs1 -> {
                        try {
                            if (rs1.next()) {
                                inv.setItem(22, ItemFactory.createItem(Material.BARRIER, 0, 1, "§4Freund entfernen", true));
                            } else {
                                inv.setItem(22, ItemFactory.createItem(Material.SKULL_ITEM, 3, 1, "§7Freund hinzufügen", true));
                            }

                            inv.setItem(24, ItemFactory.createItem(Material.CAKE, 0, 1, "§7In §5Party §7einladen", true));


                            p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
                            p.openInventory(inv);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    });
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public static void click(InventoryClickEvent e, Player p) {
        if ((e.getCurrentItem() == null) || !e.getCurrentItem().hasItemMeta() || e.getSlotType() == InventoryType.SlotType.OUTSIDE) {
            e.setCancelled(true);
        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7Online-Profil Ansehen")) {
            SkullMeta meta = (SkullMeta) e.getClickedInventory().getItem(4).getItemMeta();
            String skullOwner = meta.getOwner();

            TextComponent tc0 = new TextComponent(TextComponent.fromLegacyText(Lobby.config.getConfigValue("System-Prefix") + "§2Das Profil von "+skullOwner+" findest du "));

            TextComponent tc = new TextComponent();
            tc.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.mcone.eu/user.php?name="+skullOwner));
            tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.GRAY+"Browser öffnen").create()));
            tc.setText(ChatColor.DARK_GREEN+"§f§l§nhier");

            tc0.addExtra(tc);
            p.spigot().sendMessage(tc0);
            p.closeInventory();
        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§4Freund entfernen")) {
            SkullMeta meta = (SkullMeta) e.getClickedInventory().getItem(4).getItemMeta();
            String skullOwner = meta.getOwner();

            p.closeInventory();
            new PluginMessage(p, "CMD", "friend remove "+skullOwner);
        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7Freund hinzufügen")) {
            SkullMeta meta = (SkullMeta) e.getClickedInventory().getItem(4).getItemMeta();
            String skullOwner = meta.getOwner();

            p.closeInventory();
            new PluginMessage(p, "CMD", "friend add "+skullOwner);
        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7In §5Party §7einladen")) {
            SkullMeta meta = (SkullMeta) e.getClickedInventory().getItem(4).getItemMeta();
            String skullOwner = meta.getOwner();

            p.closeInventory();
            new PluginMessage(p, "CMD", "party invite "+skullOwner);
        }
    }

    private static String getStatus(String status) {
        switch (status) {
            case "online":
                return "§aonline";
            case "afk":
                return "§6AFK";
            default:
                return "§aonline";
        }
    }
}
