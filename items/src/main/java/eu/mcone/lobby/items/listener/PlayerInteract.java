/*
 * Copyright (c) 2017 - 2018 Dominik Lippl, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.items.listener;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.lobby.api.enums.Category;
import eu.mcone.lobby.items.inventory.backpack.BackpackInventory;
import eu.mcone.lobby.items.inventory.chest.ChestOpeningInventory;
import eu.mcone.lobby.items.manager.SilenthubUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerInteract implements Listener {

    @EventHandler
    public void on(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                Material clicked = e.getClickedBlock().getType();

                switch (clicked) {
                    case ENDER_PORTAL_FRAME: {
                        new ChestOpeningInventory(p);
                        return;
                    }
                }
            }

            ItemStack i = e.getItem();
            if ((i == null) || (!i.hasItemMeta()) || (!i.getItemMeta().hasDisplayName())) {
                return;
            }

            if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6§lPrivate Lobby §8» §7§oBetrete deine eigene Private Lobby")) {
                if (!CoreSystem.getInstance().getCooldownSystem().addAndCheck(CoreSystem.getInstance(), this.getClass(), p.getUniqueId()))
                    return;

                if (SilenthubUtils.isActivatedSilentHub(p)) {
                    SilenthubUtils.deactivateSilentLobby(p);
                } else {
                    p.sendMessage("§8[§7§l!§8] §cPrivate Lobby §8» §aDu bist nun in der Privaten Lobby");
                    SilenthubUtils.activateSilentLobby(p);
                }
            } else if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§3§lRucksack §8» §7§oZeige deine gesammelten Items an")) {
                BackpackInventory.openNewInventory(Category.STORY_ITEMS, p);
            }
        }
    }

}
