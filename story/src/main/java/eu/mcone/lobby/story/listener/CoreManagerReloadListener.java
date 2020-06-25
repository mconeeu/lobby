/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.story.listener;

import eu.mcone.coresystem.api.bukkit.event.HologramManagerReloadedEvent;
import eu.mcone.coresystem.api.bukkit.event.npc.NpcManagerReloadedEvent;
import eu.mcone.coresystem.api.bukkit.npc.NPC;
import eu.mcone.coresystem.api.bukkit.spawnable.ListMode;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.api.enums.StoryProgress;
import eu.mcone.lobby.api.enums.TraderProgress;
import eu.mcone.lobby.api.enums.TutorialStory;
import eu.mcone.lobby.api.player.LobbyPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class CoreManagerReloadListener implements Listener {

    @EventHandler
    public void on(NpcManagerReloadedEvent e) {
        for (StoryProgress storyProgress : StoryProgress.values()) {
            NPC npc = storyProgress.getNpc();
            npc.togglePlayerVisibility(ListMode.WHITELIST);
        }
        for (TutorialStory tutorialStory: TutorialStory.values()) {
            NPC npc = tutorialStory.getNpc();
            npc.togglePlayerVisibility(ListMode.WHITELIST);
        }
        for (TraderProgress traderProgress : TraderProgress.values()) {
            NPC npc = traderProgress.getNpc();
            npc.togglePlayerVisibility(ListMode.WHITELIST);
        }


        for (LobbyPlayer lp : LobbyPlugin.getInstance().getOnlineLobbyPlayers()) {
            LobbyPlayerLoadedListener.spawnStoryNpcs(lp);
            LobbyPlayerLoadedListener.spawnStoryNpcs(lp);
        }
    }

    @EventHandler
    public void on(HologramManagerReloadedEvent e) {
        LobbyWorld.ONE_ISLAND.getWorld().getHologram("story-welcome").togglePlayerVisibility(ListMode.WHITELIST);

        for (LobbyPlayer lp : LobbyPlugin.getInstance().getOnlineLobbyPlayers()) {
            if (lp.getProgressId() < 1) {
                LobbyWorld.ONE_ISLAND.getWorld().getHologram("story-welcome").toggleVisibility(lp.bukkit(), true);
            }
        }
    }

}
