/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.inventory;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.coresystem.api.bukkit.util.ItemBuilder;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class InteractionInventory extends CoreInventory {

    public InteractionInventory(Player p, Player clicked) {
        super("§8» §3Interaktionsmenü", p, InventorySlot.ROW_3, CoreInventory.Option.FILL_EMPTY_SLOTS);
        CorePlayer c = CoreSystem.getInstance().getCorePlayer(clicked);
        double onlinetime = Math.floor(((double) c.getOnlinetime() / 60 / 60) * 100) / 100;

        setItem(InventorySlot.ROW_1_SLOT_5, ItemBuilder.createSkullItem(clicked.getName(), 1).displayName("§f§l" + clicked.getName()).lore(
                CoreSystem.getInstance().getCorePlayer(clicked).getMainGroup().getLabel(),
                "",
                "§7Coins: §f" + c.getCoins(),
                "§7Onlinetime: §f" + onlinetime + " Stunden", "§7Status: " + c.getState().getName()
                ).create()
        );

        setItem(InventorySlot.ROW_3_SLOT_3, ItemBuilder.createSkullItemFromURL("http://textures.minecraft.net/texture/6f74f58f541342393b3b16787dd051dfacec8cb5cd3229c61e5f73d63947ad", 1).displayName("§7Online-Profil Ansehen").create(), e -> {
            TextComponent tc0 = new TextComponent(TextComponent.fromLegacyText(CoreSystem.getInstance().getTranslationManager().get("lobby.prefix") + "§2Das Profil von " + clicked.getName() + " findest du "));

            TextComponent tc = new TextComponent();
            tc.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.mcone.eu/user.php?uuid=" + clicked.getUniqueId()));
            tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.GRAY + "Browser öffnen").create()));
            tc.setText(ChatColor.DARK_GREEN + "§f§l§nhier");

            tc0.addExtra(tc);
            p.spigot().sendMessage(tc0);
            p.closeInventory();
        });

        CoreSystem.getInstance().getChannelHandler().createGetRequest(p, friendString -> {
            boolean isFriend = false;
            for (String friend : friendString.split(",")) {
                if (friend.contains(clicked.getName())) isFriend = true;
            }

            if (isFriend) {
                setItem(InventorySlot.ROW_3_SLOT_5, new ItemBuilder(Material.BARRIER, 1, 0).displayName("§cFreund entfernen").create(), e -> {
                    p.closeInventory();
                    CoreSystem.getInstance().getChannelHandler().createSetRequest(p, "CMD", "friend remove " + clicked.getName());
                });
            } else {
                setItem(InventorySlot.ROW_3_SLOT_5, new ItemBuilder(Material.SKULL_ITEM, 1, 3).displayName("§7Freund hinzufügen").create(), e -> {
                    p.closeInventory();
                    CoreSystem.getInstance().getChannelHandler().createSetRequest(p, "CMD", "friend add " + clicked.getName());
                });
            }

            setItem(InventorySlot.ROW_3_SLOT_7, new ItemBuilder(Material.CAKE, 1, 0).displayName("§7In §5Party §7einladen").create(), e -> {
                p.closeInventory();
                CoreSystem.getInstance().getChannelHandler().createSetRequest(p, "CMD", "party invite " + clicked.getName());
            });

            openInventory();
            getPlayer().playSound(getPlayer().getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
        }, "FRIENDS");
    }

}
