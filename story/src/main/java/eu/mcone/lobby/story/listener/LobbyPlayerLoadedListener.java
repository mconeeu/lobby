/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.story.listener;

import eu.mcone.coresystem.api.bukkit.npc.entity.PlayerNpc;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.api.enums.BankProgress;
import eu.mcone.lobby.api.enums.Progress;
import eu.mcone.lobby.api.event.LobbyPlayerLoadedEvent;
import eu.mcone.lobby.api.player.LobbyPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class LobbyPlayerLoadedListener implements Listener {

    @EventHandler
    public void on(LobbyPlayerLoadedEvent e) {
        LobbyPlayer lp = e.getPlayer();
        Player p = lp.bukkit();

        spawnStoryNpcs(lp);
        spawnStoryHolograms(p, lp.getProgressId());

    }

    public static void spawnStoryNpcs(LobbyPlayer lp) {
        Player p = lp.bukkit();

        if (lp.getProgressId() <= 15) {
            if (lp.getProgressId() > 0) {
                Progress.getProgressByID(lp.getProgressId()).getNpc().toggleVisibility(p, true);

                if (lp.getProgressId() == Progress.INFECTION.getId()) {
                    PlayerNpc infectionNpc = Progress.INFECTION.getNpc();

                    infectionNpc.setSkin(NpcListener.RUFI_HEADLED_SKIN, p);
                    infectionNpc.changeDisplayname(NpcListener.RUFI_HEADLED_DISPLAY_NAME, p);
                }
            }

            Progress future = Progress.getProgressByID(lp.getProgressId() + 1);
            if (future != null) {
                future.getNpc().toggleVisibility(p, true);
            }
        }

        if (lp.getBankprogressId() != BankProgress.BANK_ROBBERY_MIDDLE.getId()) {
            LobbyWorld.ONE_ISLAND.getWorld().getNPC("JohnEnd").toggleVisibility(p, false);
        }
    }

    public static void spawnStoryHolograms(Player p, int progressId) {
        if (progressId < 1) {
            LobbyWorld.ONE_ISLAND.getWorld().getHologram("story-welcome").toggleVisibility(p, true);
        }
    }

}
