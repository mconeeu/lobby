/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.items.listener;

import eu.mcone.coresystem.api.bukkit.event.NpcInteractEvent;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.enums.Item;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.items.inventory.bank.BankCreateCardInventory;
import eu.mcone.lobby.items.inventory.bank.BankMenInventory;
import eu.mcone.lobby.items.inventory.trader.TraderInventory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NpcInteract implements Listener {

    @EventHandler
    public void on(NpcInteractEvent e) {
        Player p = e.getPlayer();
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p.getUniqueId());

        if (e.getAction().equals(NpcInteractEvent.Action.RIGHT_CLICK)) {
            switch (e.getNpc().getData().getName()) {
                case "merchant":
                    new TraderInventory(p);
                    break;
                case "Bank": {
                    if (!lp.hasItem(Item.BANKCARD)) {
                        new BankCreateCardInventory(p);
                    } else {
                        new BankMenInventory(p);
                    }
                    break;
                }
            }
        }
    }

}
