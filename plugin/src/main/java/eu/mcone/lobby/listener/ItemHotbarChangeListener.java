package eu.mcone.lobby.listener;

import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;

public class ItemHotbarChangeListener implements Listener {

    @EventHandler
    public void on(PlayerItemHeldEvent e) {
        e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ITEM_PICKUP, 0.5F, 1);
    }
}
