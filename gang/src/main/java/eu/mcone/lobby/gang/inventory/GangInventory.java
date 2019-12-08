/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.gang.inventory;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.Skull;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.gang.Gang;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.gang.LobbyGang;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GangInventory extends CoreInventory {

    public GangInventory(Player p) {
        super(LobbyPlugin.getInstance().getGamePlayer(p).isInGang() ? "§2§l"+LobbyPlugin.getInstance().getGamePlayer(p).getGang().getName() : "§2§lGang gründen", p, InventorySlot.ROW_6);
        CorePlayer cp = CoreSystem.getInstance().getCorePlayer(p);
        LobbyPlayer lp = LobbyPlugin.getInstance().getGamePlayer(p);

        if (lp.isInGang()) {
            Gang gang = lp.getGang();

            setItem(0, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
            setItem(1, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
            setItem(2, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
            setItem(3, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
            setItem(4, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
            setItem(5, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
            setItem(6, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
            setItem(7, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
            setItem(8, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());

            setItem(17, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
            setItem(26, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
            setItem(35, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());

            setItem(9, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
            setItem(18, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
            setItem(27, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());

            setItem(36, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
            setItem(37, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
            setItem(38, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
            setItem(39, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
            setItem(40, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
            setItem(41, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
            setItem(42, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
            setItem(43, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
            setItem(44, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());

            setItem(45, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
            setItem(46, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
            setItem(47, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());

            setItem(51, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
            setItem(52, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
            setItem(53, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());

            setGangMembers(gang, p);

            setItem(InventorySlot.ROW_6_SLOT_4, new ItemBuilder(Material.BARRIER).displayName("§cVerlasse die Gang").lore("§7§oVerlasse den Clan für immer").create(), e -> {
                gang.leaveGang(p);
                p.closeInventory();
            });

            setItem(InventorySlot.ROW_6_SLOT_5, new ItemBuilder(Material.CAKE).displayName("§cGang Anfrage").lore("§7§oSchaue dir die Gang Anfragen an").create(), e -> {
                new GangInquiry(p);
            });

            if (gang.getLeader().equals(p.getUniqueId())) {
               setItem(InventorySlot.ROW_6_SLOT_6, new ItemBuilder(Material.REDSTONE).displayName("§cGang Einstellungen").lore("§7§oÄnder die Gang einstellungen").create(), e ->
                        new GangSettingsInventory(p, gang));
            }
        } else {
            setItem(31, new ItemBuilder(Material.CHAINMAIL_CHESTPLATE).displayName("§2§lGang Gründen").lore(
                    "§7§oZusammen mit deinen Gangmitgliedern kannst du",
                    "§7§ogegen andere Gangs kämpfen und Coins",
                    "§7§overdienen",
                    "",
                    "§c§oDas Gründen einer Gang kostet 10.000 Coins!"
            ).enchantment(Enchantment.DURABILITY, 1).itemFlags(ItemFlag.HIDE_ENCHANTS).create(), e -> {
                if ((cp.getCoins()-100) >= 0) {
                    LobbyGang.getInstance().createGang(p);
                    p.sendMessage(Gang.GANG_PREFIX + "§2Du hast erfolgreich eine neue Gang erstellt! Sprich mit Sorn um deine Gang zu verwalten.");
                } else {
                    p.sendMessage(Gang.GANG_PREFIX + "§4Dir fehlen §c"+(10000-cp.getCoins())+" Coins§4 um eine Gang zu gründen!");
                }

                p.closeInventory();
            });
        }

        openInventory();
    }

    private void setGangMembers(Gang gang, Player p) {
        List<HashMap.Entry<String, String>> members = new ArrayList<>(gang.getMembers().entrySet());
        int x = 0;

        for (int i = 10; i <= 43; i++) {
            if (i == 17) i = 19;
            else if (i == 26) i = 28;
            else if (i == 35) i = 37;

            if (x < gang.getMembers().size()) {
                String uuid = members.get(x).getValue();
                String name = members.get(x).getKey();

                if (p.getUniqueId().toString().equals(uuid) || gang.getLeader().toString().equals(uuid)) {
                    setItem(i, new Skull(name, 1).toItemBuilder().displayName(Gang.getPrefix(gang, uuid) + name).create());
                } else {
                    setItem(i, new Skull(name, 1).toItemBuilder().displayName(Gang.getPrefix(gang, uuid) + name).create(), e ->
                            new GangPlayerInventory(p, gang, name, uuid));
                }

                x++;
            } else {
                break;
            }
        }
    }

}
