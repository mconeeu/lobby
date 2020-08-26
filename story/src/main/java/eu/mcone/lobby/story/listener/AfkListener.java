/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.story.listener;

import eu.mcone.coresystem.api.bukkit.event.AfkEvent;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.api.items.LobbyItem;
import eu.mcone.lobby.api.story.progress.bank.BankRobberySmallProgress;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.story.inventory.john.JohnBankRobberyInventory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class AfkListener implements Listener {

    @EventHandler
    public void onAFK(AfkEvent e) {
        Player p = e.getPlayer();
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(e.getPlayer());


        if (lp.getBankprogressId() == BankRobberySmallProgress.BANK_ROBBERY_MIDDLE.getId()) {
            lp.setBankProgress(BankRobberySmallProgress.BANK_ROBBERY_START);
            LobbyPlugin.getInstance().getMessenger().send(p, "§4Der Banküberfall ist gescheitert!");
            LobbyWorld.ONE_ISLAND.getWorld().teleportSilently(p, "office-entrance");

            JohnBankRobberyInventory.currentlyInBank = null;
            if (lp.hasLobbyItem(LobbyItem.GOLD_BARDING)) {
                lp.removeLobbyItem(LobbyItem.GOLD_BARDING);
            }
        }
    }

}
