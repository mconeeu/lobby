/*
 * Copyright (c) 2017 Dominik L., Rufus Maiwald, BamDev and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.inventory;

import eu.mcone.bukkitcoresystem.Main;
import eu.mcone.bukkitcoresystem.messager.PluginMessage;
import eu.mcone.bukkitcoresystem.util.ItemManager;
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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import static eu.mcone.bukkitcoresystem.util.Scoreboard.getObjectiveRang;

public class InteractionInventory {

    public static void open(Player p, Player clicked) {
        ResultSet rs = Main.mysql1.getResult("SELECT status, coins, onlinetime FROM userinfo WHERE uuid='" + clicked.getUniqueId().toString() + "'");
        try {
            if (rs.next()) {
                double onlinetime = Math.floor((rs.getInt("onlinetime") / 60) * 100) / 100;
                int coins = rs.getInt("coins");
                String status = getStatus(rs.getString("status"));

                Inventory inv = org.bukkit.Bukkit.createInventory(null, 27, "§8» §3Interaktionsmenü");

                for (int i = 0; i <= 26; i++) {
                    inv.setItem(i, ItemManager.createItem(Material.STAINED_GLASS_PANE, 7, 1, "§8//§oMCONE§8//", true));
                }
                inv.setItem(4, ItemManager.createSkullItem("§f§l" + clicked.getName(), clicked.getName(), 1, new ArrayList<>(Arrays.asList(getObjectiveRang(clicked), "","§7Coins: §f" + coins , "§7Onlinetime: §f" + onlinetime + " Stunden", "§7Status: " + status))));

                inv.setItem(20, ItemManager.createCustomSkullItem("§7Online-Profil Ansehen", "Globe", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjFkZDRmZTRhNDI5YWJkNjY1ZGZkYjNlMjEzMjFkNmVmYTZhNmI1ZTdiOTU2ZGI5YzVkNTljOWVmYWIyNSJ9fX0=", 1, new ArrayList<>()));
                ResultSet rs1 = Main.mysql1.getResult("SELECT uuid FROM `bungeesystem_friends` WHERE `uuid`='"+p.getUniqueId()+"' AND `target`='"+clicked.getUniqueId()+"' AND `key`='friend';");
                if (rs1.next()) {
                    inv.setItem(22, ItemManager.createItem(Material.BARRIER, 0, 1, "§4Freund entfernen", true));
                } else {
                    inv.setItem(22, ItemManager.createItem(Material.SKULL_ITEM, 3, 1, "§7Freund hinzufügen", true));
                }
                inv.setItem(24, ItemManager.createItem(Material.CAKE, 0, 1, "§7In §5Party §7einladen", true));


                p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
                p.openInventory(inv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void click(InventoryClickEvent e, Player p) {
        if ((e.getCurrentItem() == null) || !e.getCurrentItem().hasItemMeta() || e.getSlotType() == InventoryType.SlotType.OUTSIDE) {
            e.setCancelled(true);
        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7Online-Profil Ansehen")) {
            SkullMeta meta = (SkullMeta) e.getClickedInventory().getItem(4).getItemMeta();
            String skullOwner = meta.getOwner();

            TextComponent tc = new TextComponent();
            tc.setText(ChatColor.WHITE+"Klicke hier");
            tc.setUnderlined(true);
            tc.setItalic(true);
            tc.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.mcone.eu/user.php?name="+skullOwner));
            tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.GRAY+"Browser öffnen").create()));

            TextComponent tc2 = new TextComponent();
            tc2.setText(ChatColor.DARK_GREEN+" um das Online-Profil von "+skullOwner+" anzuzeigen");

            tc.addExtra(tc2);
            p.spigot().sendMessage(tc);
            p.closeInventory();
        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§4Freund entfernen")) {
            SkullMeta meta = (SkullMeta) e.getClickedInventory().getItem(4).getItemMeta();
            String skullOwner = meta.getOwner();

            p.closeInventory();
            PluginMessage.send("CMD", "friend remove "+skullOwner, p);
        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7Freund hinzufügen")) {
            SkullMeta meta = (SkullMeta) e.getClickedInventory().getItem(4).getItemMeta();
            String skullOwner = meta.getOwner();

            p.closeInventory();
            PluginMessage.send("CMD", "friend add "+skullOwner, p);
        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7In §5Party §7einladen")) {
            SkullMeta meta = (SkullMeta) e.getClickedInventory().getItem(4).getItemMeta();
            String skullOwner = meta.getOwner();

            p.closeInventory();
            PluginMessage.send("CMD", "party invite "+skullOwner, p);
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
