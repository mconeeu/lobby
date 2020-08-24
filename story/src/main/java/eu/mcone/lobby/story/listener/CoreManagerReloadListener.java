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
import eu.mcone.lobby.api.story.progress.StoryProgress;
import eu.mcone.lobby.api.story.progress.TraderStoryProgress;
import eu.mcone.lobby.api.story.progress.TutorialStoryProgress;
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
        for (TutorialStoryProgress tutorialStoryProgress : TutorialStoryProgress.values()) {
            NPC npc = tutorialStoryProgress.getNpc();
            npc.togglePlayerVisibility(ListMode.WHITELIST);
        }
        for (TraderStoryProgress traderStoryProgress : TraderStoryProgress.values()) {
            NPC npc = traderStoryProgress.getNpc();
            npc.togglePlayerVisibility(ListMode.WHITELIST);
        }

        for (LobbyPlayer lp : LobbyPlugin.getInstance().getOnlineLobbyPlayers()) {
            GeneralPlayerListener.spawnStoryNpcs(lp);
        }
    }

    @EventHandler
    public void on(HologramManagerReloadedEvent e) {
        LobbyWorld.ONE_ISLAND.getWorld().getHologram("story-welcome").togglePlayerVisibility(ListMode.WHITELIST);

        for (LobbyPlayer lp : LobbyPlugin.getInstance().getOnlineLobbyPlayers()) {
            GeneralPlayerListener.spawnStoryHolograms(lp.bukkit(), lp.getProgressId());
        }
    }

}
