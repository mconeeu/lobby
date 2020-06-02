package eu.mcone.lobby.story.listener;

import eu.mcone.coresystem.api.bukkit.npc.NPC;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.enums.Progress;
import eu.mcone.lobby.items.inventory.office.secretary.SecretaryInventory;
import eu.mcone.lobby.items.manager.OfficeManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class WorldChangeListener implements Listener {

    @EventHandler
    public void on(PlayerChangedWorldEvent e) {
        for (Progress progress : Progress.values()) {
            NPC npc = progress.getNpc();
            npc.toggleVisibility(e.getPlayer(), false);
        }

        Player player = e.getPlayer();



        if (e.getFrom().getName().equalsIgnoreCase("Lobby-Office")) {
            OfficeManager.quitOffice(player);

            if (SecretaryInventory.isInviting.contains(player)) {
                SecretaryInventory.isInviting.remove(player);
                LobbyPlugin.getInstance().getMessenger().send(player, "§4Du hast das Büro verlassen dadurch wurde dein Einladungslink gelöscht!");
            }

            if (OfficeManager.ISTOGETHEROFFICE.contains(player)) {
                OfficeManager.ISTOGETHEROFFICE.remove(player);

                for (Player all : OfficeManager.ISTOGETHEROFFICE) {
                    all.hidePlayer(player);
                }
            }
        }

        LobbyPlayerLoadedListener.spawnStoryNpcs(LobbyPlugin.getInstance().getLobbyPlayer(e.getPlayer().getUniqueId()));
    }

}
