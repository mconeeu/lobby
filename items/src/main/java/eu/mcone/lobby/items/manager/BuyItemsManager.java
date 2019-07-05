package eu.mcone.lobby.items.manager;

import eu.mcone.lobby.api.LobbyPlugin;
import org.bukkit.Bukkit;

public class BuyItemsManager implements Runnable {

    public BuyItemsManager() {
        Bukkit.getScheduler().runTaskTimerAsynchronously(LobbyPlugin.getInstance(), this, 0, 60 * 60 * 20);
    }

    @Override
    public void run() {

    }

}
