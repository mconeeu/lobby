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
import eu.mcone.lobby.items.inventory.office.OfficeTraderInventory;
import eu.mcone.lobby.items.inventory.trader.TraderInventory;
import net.minecraft.server.v1_8_R3.PacketPlayInUseEntity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NpcInteractListener implements Listener {

    @EventHandler
    public void onNpcInteract(NpcInteractEvent e) {
        Player p = e.getPlayer();
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p.getUniqueId());

        if (e.getNpc().getData().getType().equals(EntityType.PLAYER) && e.getAction().equals(PacketPlayInUseEntity.EnumEntityUseAction.INTERACT)) {
            switch (e.getNpc().getData().getName()) {
                case "merchant":
                    new TraderInventory(p);
                    break;
                case "bankman": {
                    if (!lp.hasItem(Item.BANKCARD)) {
                        new BankCreateCardInventory(p);
                    } else {
                        new BankMenInventory(p);
                    }
                    break;
                }
                case "officetrader": {
                    new TraderInventory(p);
                }
                case "officeseller": {
                    new OfficeTraderInventory(p);
                }
            }
        }
    }

}
