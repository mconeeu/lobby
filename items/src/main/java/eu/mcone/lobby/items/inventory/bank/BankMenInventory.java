/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.items.inventory.bank;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.item.Skull;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.enums.LobbyItem;
import eu.mcone.lobby.api.player.LobbyPlayer;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class BankMenInventory extends CoreInventory {

    public BankMenInventory(Player p) {
        super("§8» §d§lBänker", p, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);
        CorePlayer cp = CoreSystem.getInstance().getCorePlayer(p);
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);


        if (lp.hasLobbyItem(LobbyItem.BANKCARD)) {
            setItem(InventorySlot.ROW_1_SLOT_1, Skull.fromUrl("http://textures.minecraft.net/texture/5163dafac1d91a8c91db576caac784336791a6e18d8f7f62778fc47bf146b6", 1).toItemBuilder()
                    .displayName("§d§lBänker")
                    .lore("§7§oBeim Bänker kannst du dein Bank Konto",
                            "§7§oVerwalten für das passende Kleingeld.",
                            "§7§oDie meisten Items erhälst du"
                    ).create());

            setItem(InventorySlot.ROW_2_SLOT_2, new ItemBuilder(Material.GOLD_INGOT, 1, 0).displayName("§6§lCoin-Guthaben")
                    .lore("§7Du hast momemtan §6" + cp.getCoins())
                    .create());

            setItem(InventorySlot.ROW_2_SLOT_4, new ItemBuilder(Material.EMERALD, 1, 0).displayName("§a§lEmerald-Guthaben")
                    .lore("§7Du hast momemtan §a" + cp.getEmeralds())
                    .create());

            setItem(InventorySlot.ROW_2_SLOT_8, new ItemBuilder(Material.SIGN, 1, 0).displayName("§f§lUmwandler").create(),
                    e -> new BankChangeCoins(p));


        } else if (lp.hasLobbyItem(LobbyItem.BANKCARD_PREMIUM)) {

            setItem(InventorySlot.ROW_1_SLOT_1, Skull.fromUrl("http://textures.minecraft.net/texture/5163dafac1d91a8c91db576caac784336791a6e18d8f7f62778fc47bf146b6", 1).toItemBuilder()
                    .displayName("§d§lBänker")
                    .lore("§7§oBeim Bänker kannst du dein Bank Konto",
                            "§7§oVerwalten für das passende Kleingeld.",
                            "§7§oDie meisten Items erhälst du"
                    ).create());

            setItem(InventorySlot.ROW_2_SLOT_2, new ItemBuilder(Material.GOLD_INGOT, 1, 0).displayName("§6§lCoin-Guthaben")
                    .lore("§7Du hast momemtan §6" + cp.getCoins())
                    .create());

            setItem(InventorySlot.ROW_2_SLOT_4, new ItemBuilder(Material.EMERALD, 1, 0).displayName("§a§lEmerald-Guthaben")
                    .lore("§7Du hast momemtan §a" + cp.getEmeralds())
                    .create());

            setItem(InventorySlot.ROW_2_SLOT_8, new ItemBuilder(Material.SIGN, 1, 0).displayName("§f§lUmwandler").create(),
                    e -> new BankChangeCoins(p));

            Calendar lastDailyRewardPlusOneDay = Calendar.getInstance(TimeZone.getTimeZone("Europe/Berlin"));
            lastDailyRewardPlusOneDay.setTime(lp.getLastDailyRewardDate());
            lastDailyRewardPlusOneDay.add(Calendar.DAY_OF_MONTH, 1);

            if (lp.getDailyReward() == null || new Date().after(lastDailyRewardPlusOneDay.getTime())) {
                setItem(InventorySlot.ROW_2_SLOT_6, Skull.fromUrl("http://textures.minecraft.net/texture/f5612dc7b86d71afc1197301c15fd979e9f39e7b1f41d8f1ebdf8115576e2e", 1).toItemBuilder().displayName("§4§lTägliche Belohnung").lore("§8» §f§nRechtsklick§8 | §7§oAbholen").create(),
                        e -> {
                            lp.getCorePlayer().addCoins(100);
                            lp.setDailyReward();

                            LobbyPlugin.getInstance().getMessenger().send(p, "§2Du hast dir deine §a§oTägliche Belohnung §2abgeholt!");
                            LobbyPlugin.getInstance().getMessenger().send(p, "§8[§a+100 Coins§8]");

                            p.closeInventory();
                            p.playSound(p.getLocation(), Sound.NOTE_PLING, 1, 1);
                        });
            } else {
                setItem(InventorySlot.ROW_2_SLOT_6, Skull.fromUrl("http://textures.minecraft.net/texture/86d35a963d5987894b6bc214e328b39cd2382426ff9c8e082b0b6a6e044d3a3", 1).toItemBuilder().displayName("§4§lTägliche Belohnung").lore("§c§oAb Morgen Verfügbar").create(),
                        e -> {
                            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1, 1);
                            LobbyPlugin.getInstance().getMessenger().send(p, "§4Du hast deine §cBelohnung §4erst am nächsten Tag abholen!");
                        });
            }

        }


        openInventory();
    }
}

