package eu.mcone.lobby.listener;

import eu.mcone.lobby.Lobby;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.inventory.InteractionInventory;
import eu.mcone.lobby.util.StackingManager;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.spigotmc.event.entity.EntityDismountEvent;

@RequiredArgsConstructor
public class StackingListener implements Listener {

    private final StackingManager manager;

    @EventHandler
    public void on(EntityDismountEvent e) {
        Player p = (Player) e.getEntity();

        if (e.getDismounted() instanceof Player && manager.isStacked((Player) e.getDismounted())) {
            manager.unstack(p);
        }
    }

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent e) {
        Player p = e.getPlayer();

        if (e.isSneaking() && manager.isStacking(p)) {
            manager.unstack(p);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        manager.playerLeaved(e.getPlayer());
    }

}
