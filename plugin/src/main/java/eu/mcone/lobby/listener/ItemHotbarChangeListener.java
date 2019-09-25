package eu.mcone.lobby.listener;

import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;

public class ItemHotbarChangeListener implements Listener {

    @EventHandler
    public void on(PlayerItemHeldEvent e) {
        e.getPlayer().playSound(e.getPlayer().getLocation().add(0, 5, 0), Sound.ITEM_PICKUP, 1, 1);
    }
}
