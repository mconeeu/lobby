/*
 * Copyright (c) 2017 Dominik L., BamDev, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.inventar;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class onHats implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e){
        Player p = (Player)e.getWhoClicked();

        try{
            //e.setCancelled(true);
            if (e.getInventory().getName().equals("§8» §5Hüte")) {
                if (e.getCurrentItem().getItemMeta().getDisplayName() != "§8» §4Hat entfernen") {
                    p.getInventory().setHelmet(e.getCurrentItem());
                    p.playSound(p.getLocation(), org.bukkit.Sound.ARROW_HIT, 1.0F, 1.0F);
                    p.closeInventory();

                } else if (e.getCurrentItem().getItemMeta().getDisplayName() == "§8» §4Hat entfernen") {
                    p.getInventory().setHelmet(null);
                    p.playSound(p.getLocation(), org.bukkit.Sound.ANVIL_BREAK, 1.0F, 1.0F);
                    p.closeInventory();
                }
            }
        }catch (Exception e1){

        }
    }

}
