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
import eu.mcone.gamesystem.api.enums.Item;
import eu.mcone.gamesystem.api.game.player.GamePlayer;
import eu.mcone.lobby.api.LobbyPlugin;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class BankMenInventory extends CoreInventory {

    public BankMenInventory(Player p) {
        super("§8» §d§lBänker", p, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);
        CorePlayer cp = CoreSystem.getInstance().getCorePlayer(p);
        GamePlayer lp = LobbyPlugin.getInstance().getGamePlayer(p.getUniqueId());

        if (lp.hasItem(Item.BANKCARD)) {

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

            //         setItem(InventorySlot.ROW_2_SLOT_6, new ItemBuilder(Material.SIGN, 1, 0).displayName("§f§lEmerald(s) Überweisen").create(),
//                e -> {
//
//                });

        } else if (lp.hasItem(Item.BANKCARD_PREMIUM)) {

            setItem(InventorySlot.ROW_1_SLOT_1, Skull.fromUrl("http://textures.minecraft.net/texture/5163dafac1d91a8c91db576caac784336791a6e18d8f7f62778fc47bf146b6", 1).toItemBuilder()
                    .displayName("§d§lBänker")
                    .lore("§7§oBeim Bänker kannst du dein Bank Konto",
                            "§7§oVerwalten für das passende Kleingeld.",
                            "§7§oDie meisten Items erhälst du"
                    ).create());

            setItem(InventorySlot.ROW_2_SLOT_2, new ItemBuilder(Material.GOLD_INGOT, 1, 0).displayName("§6§lCoin-Guthaben")
                    .lore("§7Du hast momemtan §6" + cp.getCoins())
                    .create());

            setItem(InventorySlot.ROW_2_SLOT_8, new ItemBuilder(Material.EMERALD, 1, 0).displayName("§a§lEmerald-Guthaben")
                    .lore("§7Du hast momemtan §a" + cp.getEmeralds())
                    .create());

            setItem(InventorySlot.ROW_2_SLOT_4, new ItemBuilder(Material.SIGN, 1, 0).displayName("§f§lUmwandler").create(),
                    e -> new BankChangeCoins(p));

//        setItem(InventorySlot.ROW_2_SLOT_6, new ItemBuilder(Material.SIGN, 1, 0).displayName("§f§lEmerald(s) Überweisen").create(),
//                e -> {
//
//                });

//        //TODO: Every 12 hours -> Coin gift
//        setItem(InventorySlot.ROW_3_SLOT_5, Skull.fromUrl("http://textures.minecraft.net/texture/f5612dc7b86d71afc1197301c15fd979e9f39e7b1f41d8f1ebdf8115576e2e", 1).toItemBuilder().displayName("§d§lHole dir deine Belohnung ab").create(),
//                e -> {
//                    p.closeInventory();
//                    p.sendMessage("Haha");
//                });

        }


        openInventory();
    }
}

