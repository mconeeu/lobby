/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.story.inventory.story;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.enums.LobbyItem;
import eu.mcone.lobby.api.player.LobbyPlayer;
import org.bukkit.entity.Player;

public class CustomerInventory extends CoreInventory {

    public CustomerInventory(Player p) {
        super("§fVerkäufer", p, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);

        setItem(InventorySlot.ROW_2_SLOT_5, LobbyItem.BOAT_PASS.getItemStack(), e -> {
            if (!lp.hasLobbyItem(LobbyItem.BOAT_PASS)) {
                if ((lp.getCorePlayer().getCoins() - 20) >= 0) {
                    lp.getCorePlayer().removeCoins(20);
                    lp.addLobbyItem(LobbyItem.BOAT_PASS);
                    if (p.hasPermission("lobby.silenthub")) {
                        p.getInventory().setItem(3, LobbyItem.BOAT_PASS.getItemStack());
                    } else {
                        p.getInventory().setItem(2, LobbyItem.BOAT_PASS.getItemStack());
                    }
                    p.sendMessage("§8[§7§l!§8] §cNPC §8» §fVerkäufer §8|§7 Bitte sehr ihr Ticket");
                } else {
                    p.sendMessage("§8[§7§l!§8] §cNPC §8» §fVerkäufer §8|§7 Du hast zu wenig Coins!");
                }
            } else {
                p.sendMessage("§8[§7§l!§8] §cNPC §8» §fVerkäufer §8|§7 Du hast doch schon ein Ticket!");
            }
            p.closeInventory();
        });

        openInventory();
    }

}
