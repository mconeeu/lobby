/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.listener;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.player.HotbarItem;
import eu.mcone.lobby.inventory.LobbySwitchInventory;
import eu.mcone.lobby.inventory.compass.MinigamesInventory;
import eu.mcone.lobby.story.LobbyStory;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryTriggerListener implements Listener {

    @EventHandler
    public void on(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        CorePlayer cp = CoreSystem.getInstance().getCorePlayer(p);
        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            ItemStack i = e.getItem();
            if ((i == null) || (!i.hasItemMeta()) || (!i.getItemMeta().hasDisplayName())) {
                return;
            }


            if (i.getItemMeta().getDisplayName().equalsIgnoreCase(HotbarItem.PROFILE_DISPLAY_NAME)) {
                e.setCancelled(true);
                p.performCommand("profile");
            } else if (i.getItemMeta().getDisplayName().equals(HotbarItem.COMPASS.getItemMeta().getDisplayName())) {
                e.setCancelled(true);
                new MinigamesInventory(p);
              LobbyPlugin.getInstance().getPlayerSounds().playSounds(p, Sound.CHICKEN_EGG_POP);
            } else if (i.getItemMeta().getDisplayName().equals(HotbarItem.LOBBY_CHANGER.getItemMeta().getDisplayName())) {
                if (!LobbyStory.getInstance().getOfficeManager().isInOffice(p)) {
                    new LobbySwitchInventory(p);
                } else {
                    LobbyPlugin.getInstance().getMessenger().sendError(p, "Der Lobby Wechsler ist im Büro deaktiviert!");
                }
            } else if (i.equals(HotbarItem.NICK_ENABLED)) {
                if (cp.isNicked()) {
                    CoreSystem.getInstance().getChannelHandler().createSetRequest(p, "CMD", "nick");
                }
            } else if (i.equals(HotbarItem.NICK_DISABLED)) {
                if (!cp.isNicked()) {
                    CoreSystem.getInstance().getChannelHandler().createSetRequest(p, "CMD", "nick");
                }
            }
        }
    }

}
