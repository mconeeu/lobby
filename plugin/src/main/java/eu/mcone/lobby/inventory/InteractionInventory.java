/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.inventory;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.item.Skull;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.api.player.LobbySettings;
import eu.mcone.lobby.games.LobbyGames;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class InteractionInventory extends CoreInventory {

    public static HashMap<Player, Player> stacking = new HashMap<>();

    public InteractionInventory(Player p, Player clicked) {
        super("§8» §3Interaktionsmenü", p, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);
        CorePlayer clickedCorePlayer = CoreSystem.getInstance().getCorePlayer(clicked);
        double onlinetime = Math.floor(((double) (clickedCorePlayer.isNicked() ? clickedCorePlayer.getNick().getOnlineTime() : clickedCorePlayer.getOnlinetime()) / 60 / 60) * 100) / 100;

        setItem(InventorySlot.ROW_1_SLOT_5, new Skull((clickedCorePlayer.isNicked() ? clickedCorePlayer.getNick().getName() : clicked.getName()), 1).toItemBuilder().displayName("§f§l" + clicked.getName()).lore(
                CoreSystem.getInstance().getCorePlayer(clicked).getMainGroup().getLabel(),
                "",
                "§7Coins: §f" + (clickedCorePlayer.isNicked() ? clickedCorePlayer.getNick().getCoins() : clickedCorePlayer.getCoins()),
                "§7Onlinetime: §f" + onlinetime + " Stunden", "§7Status: " + clickedCorePlayer.getState().getName()
                ).create()
        );

        setItem(InventorySlot.ROW_3_SLOT_2, Skull.fromUrl("http://textures.minecraft.net/texture/6f74f58f541342393b3b16787dd051dfacec8cb5cad3229c61e5f73d63947ad", 1).toItemBuilder().displayName("§7Online-Profil Ansehen").create(), e -> {
            TextComponent tc0 = new TextComponent(TextComponent.fromLegacyText(CoreSystem.getInstance().getTranslationManager().get("lobby.prefix") + "§2Das Profil von " + clicked.getName() + " findest du "));

            TextComponent tc = new TextComponent();
            tc.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.mcone.eu/user.php?uuid=" + clicked.getUniqueId()));
            tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.GRAY + "Browser öffnen").create()));
            tc.setText(ChatColor.DARK_GREEN + "§f§nhier");

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
                setItem(InventorySlot.ROW_3_SLOT_4, new ItemBuilder(Material.BARRIER, 1, 0).displayName("§cFreund entfernen").create(), e -> {
                    p.closeInventory();
                    CoreSystem.getInstance().getChannelHandler().createSetRequest(p, "CMD", "friend remove " + clicked.getName());
                });
            } else {
                setItem(InventorySlot.ROW_3_SLOT_4, new ItemBuilder(Material.SKULL_ITEM, 1, 3).displayName("§7Freund hinzufügen").create(), e -> {
                    p.closeInventory();
                    CoreSystem.getInstance().getChannelHandler().createSetRequest(p, "CMD", "friend add " + clicked.getName());
                });
            }

            setItem(InventorySlot.ROW_3_SLOT_6, new ItemBuilder(Material.LEATHER_HELMET, 1, 0).displayName("§fTragen").create(), e -> {
                p.closeInventory();

                LobbyPlayer lc = LobbyPlugin.getInstance().getLobbyPlayer(clicked);
                LobbySettings settings = lc.getSettings();

                if (settings.isStacking()) {
                    if (!LobbyGames.getInstance().isPlaying(p)) {
                        p.setPassenger(lc.bukkit());
                        CoreSystem.getInstance().createActionBar()
                                .message("§f§oBenutze LSHIFT um abzusteigen")
                                .send(lc.bukkit());
                        LobbyPlugin.getInstance().getMessenger().send(lc.bukkit(), "§aDu wirst nun von §3" + p.getName() + "§a getragen.");
                        LobbyPlugin.getInstance().getMessenger().send(p, "§4Schleiche um §c" + lc.bukkit().getName() + "§4 fallen zu lassen");

                        stacking.put(p, clicked);
                    } else {
                        LobbyPlugin.getInstance().getMessenger().send(p, "§4Der §cSpieler §4spielt momentan ein Lobby-Game!");
                    }
                } else {
                    LobbyPlugin.getInstance().getMessenger().send(p, "§4Der §cSpieler §4hat diese §cFunktion§4 ausgeschaltet!");
                }
            });

            setItem(InventorySlot.ROW_3_SLOT_8, new ItemBuilder(Material.CAKE, 1, 0).displayName("§7In §5Party §7einladen").create(), e -> {
                p.closeInventory();
                CoreSystem.getInstance().getChannelHandler().createSetRequest(p, "CMD", "party invite " + clicked.getName());
            });

            openInventory();
            getPlayer().playSound(getPlayer().getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
        }, "FRIENDS");
    }

}
