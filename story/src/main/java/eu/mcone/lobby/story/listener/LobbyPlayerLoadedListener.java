/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.story.listener;

import eu.mcone.coresystem.api.bukkit.npc.entity.PlayerNpc;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.api.enums.StoryProgress;
import eu.mcone.lobby.api.enums.TraderProgress;
import eu.mcone.lobby.api.enums.TutorialStory;
import eu.mcone.lobby.api.enums.bank.BankRobberySmallProgress;
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

        /* One-Island Story Show NPCs */
        if (lp.getProgressId() <= 17) {
            if (lp.getProgressId() > 0) {
                StoryProgress.getProgressByID(lp.getProgressId()).getNpc().toggleVisibility(p, true);

                if (lp.getProgressId() == StoryProgress.INFECTION.getId()) {
                    PlayerNpc infectionNpc = StoryProgress.INFECTION.getNpc();

                    infectionNpc.setSkin(NpcListener.RUFI_HEADLED_SKIN, p);
                    infectionNpc.changeDisplayname(NpcListener.RUFI_HEADLED_DISPLAY_NAME, p);
                }
            }

            StoryProgress futureStory = StoryProgress.getProgressByID(lp.getProgressId() + 1);
            if (futureStory != null) {
                futureStory.getNpc().toggleVisibility(p, true);
            }
        }

        /* Tutorial Story Show NPCs */
        if (lp.getTutorialStoryProgressId() <= 6) {
            if (lp.getTutorialStoryProgressId() > 0) {
                TutorialStory.getTutorialStoryById(lp.getTutorialStoryProgressId()).getNpc().toggleVisibility(p, true);
            }

            TutorialStory futureTutorial = TutorialStory.getTutorialStoryById(lp.getTutorialStoryProgressId() + 1);
            if (futureTutorial != null) {
                futureTutorial.getNpc().toggleVisibility(p, true);
            }
        }

        /* Trader Story Show NPCs */
        if (lp.getTraderStoryProgressID() <= 5) {
            if (lp.getTraderStoryProgressID() > 0) {
                TraderProgress.getTraderStoryById(lp.getTraderStoryProgressID()).getNpc().toggleVisibility(p, true);
            }

            TraderProgress futureTutorial = TraderProgress.getTraderStoryById(lp.getTraderStoryProgressID() + 1);
            if (futureTutorial != null) {
                futureTutorial.getNpc().toggleVisibility(p, true);
            }
        }

        /* Bank Story Show NPCs */
        if (lp.getBankprogressId() != BankRobberySmallProgress.BANK_ROBBERY_MIDDLE.getId()) {
            LobbyWorld.ONE_ISLAND.getWorld().getNPC("JohnEnd").toggleVisibility(p, false);
        }
    }

    public static void spawnStoryHolograms(Player p, int progressId) {
        if (progressId < 1) {
            LobbyWorld.ONE_ISLAND.getWorld().getHologram("story-welcome").toggleVisibility(p, true);
        }
    }

}
