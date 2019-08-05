/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.items.inventory.bank;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.item.Skull;
import eu.mcone.gamesystem.api.game.player.GamePlayer;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.enums.Item;
import eu.mcone.lobby.api.player.LobbyPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

public class BankCreateCardInventory extends CoreInventory {

    public BankCreateCardInventory(Player p) {
        super("§8» §d§lBänker", p, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);
        GamePlayer lp = LobbyPlugin.getInstance().getGamePlayer(p.getUniqueId());

        setItem(InventorySlot.ROW_1_SLOT_1,
                Skull.fromUrl("http://textures.minecraft.net/texture/5163dafac1d91a8c91db576caac784336791a6e18d8f7f62778fc47bf146b6", 1)
                        .toItemBuilder()
                        .displayName("§d§lBänker").lore("§7§oBeim Bänker kannst du dein Bank Konto", "§7§oVerwalten für das passende Kleingeld.")
                        .create()
        );

        setItem(InventorySlot.ROW_2_SLOT_5, new ItemBuilder(Material.PAINTING, 1, 0)
                        .displayName("§cKonto erstellen")
                        .lore("§7Erstelle dir ein Konto für §61000 Coins§7.")
                        .create(),
                e -> {
                    if ((lp.getCorePlayer().getCoins() - 1000) >= 0) {
                        lp.getCorePlayer().removeCoins(1000);
                        lp.getCorePlayer().getScoreboard().getObjective(DisplaySlot.SIDEBAR).reload();

                        if (!lp.hasItem(Item.BANKCARD)) {
                            lp.addItem(Item.BANKCARD);

                            new BankMenInventory(p);
                        } else {
                            p.sendMessage("§8[§7§l!§8] §cNPC §8» §fBänker §8|§7 Du hast doch schon ein Konto bei mir");
                        }
                    } else {
                        p.sendMessage("§8[§7§l!§8] §cNPC §8» §fBänker §8|§7 Du hast leider zu wening Coins");
                    }

                }
        );

        openInventory();
    }
}
