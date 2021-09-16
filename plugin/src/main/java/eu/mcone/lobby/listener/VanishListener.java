package eu.mcone.lobby.listener;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.facades.Msg;
import eu.mcone.lobby.api.player.HotbarItem;
import eu.mcone.lobby.api.player.vanish.VanishPlayerVisibility;
import eu.mcone.lobby.inventory.playerhider.PlayerHiderInventory;
import eu.mcone.lobby.util.LobbyVanishManager;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

@RequiredArgsConstructor
public class VanishListener implements Listener {

    private final LobbyVanishManager manager;


    @EventHandler
    public void on(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            ItemStack i = e.getItem();
            if ((i == null) || (!i.hasItemMeta()) || (!i.getItemMeta().hasDisplayName())) {
                return;
            }

            if (i.equals(HotbarItem.LOBBY_HIDER_UNAVAILABLE_SETTING)) {
                Msg.sendError(p, "Du hast alle Spieler über eine Lobby Einstellung ausgeschaltet!");
            } else if (i.equals(HotbarItem.SILENT_LOBBY_JOIN)) {
                if (!CoreSystem.getInstance().getCooldownSystem().addAndCheck(getClass(), p.getUniqueId())) {
                    Msg.sendError(p, "§4Bitte warte kurz, bevor du erneut die Sichbarkeit von Spielern veränderst!");
                    return;
                }

                manager.joinSilentLobby(p);
            } else if (i.equals(HotbarItem.SILENT_LOBBY_QUIT)) {
                if (!CoreSystem.getInstance().getCooldownSystem().addAndCheck(getClass(), p.getUniqueId())) {
                    Msg.sendError(p, "§4Bitte warte kurz, bevor du erneut die Sichbarkeit von Spielern veränderst!");
                    return;
                }

                manager.quitSilentLobby(p);
            } else {
                for (VanishPlayerVisibility target : VanishPlayerVisibility.values()) {
                    if (i.equals(target.getItem())) {
                        new PlayerHiderInventory(p, manager);
                        break;
                    }
                }
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        manager.playerLeaved(e.getPlayer());
    }

}
