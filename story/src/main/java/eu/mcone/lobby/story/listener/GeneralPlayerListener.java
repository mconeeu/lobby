/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.story.listener;

import eu.mcone.coresystem.api.bukkit.npc.entity.PlayerNpc;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.api.items.LobbyItem;
import eu.mcone.lobby.api.story.progress.StoryProgress;
import eu.mcone.lobby.api.story.progress.TraderStoryProgress;
import eu.mcone.lobby.api.story.progress.TutorialStoryProgress;
import eu.mcone.lobby.api.story.progress.bank.BankRobberySmallProgress;
import eu.mcone.lobby.api.event.LobbyPlayerLoadedEvent;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.story.inventory.john.JohnBankRobberyInventory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class GeneralPlayerListener implements Listener {

    @EventHandler
    public void onLoaded(LobbyPlayerLoadedEvent e) {
        LobbyPlayer lp = e.getPlayer();
        Player p = lp.bukkit();

        if (!lp.hasLobbyItem(LobbyItem.BANKCARD_PREMIUM)) {
            if (p.hasPermission("group.premium")) {
                if (!lp.hasLobbyItem(LobbyItem.BANKCARD)) {
                    lp.addLobbyItem(LobbyItem.BANKCARD_PREMIUM);
                } else {
                    lp.removeLobbyItem(LobbyItem.BANKCARD);
                    lp.addLobbyItem(LobbyItem.BANKCARD_PREMIUM);
                }

            }
        } else {
            if (p.hasPermission("group.premium")) {
                if (lp.hasLobbyItem(LobbyItem.BANKCARD)) {
                    lp.removeLobbyItem(LobbyItem.BANKCARD);
                }
            } else {
                if (lp.hasLobbyItem(LobbyItem.BANKCARD_PREMIUM)) {
                    lp.removeLobbyItem(LobbyItem.BANKCARD_PREMIUM);
                }
            }
        }

        spawnStoryNpcs(lp);
        spawnStoryHolograms(p, lp.getProgressId());
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onQuit(PlayerQuitEvent e) {
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(e.getPlayer());

        if (lp.getBankprogressId() == BankRobberySmallProgress.BANK_ROBBERY_MIDDLE.getId()) {
            lp.setBankProgress(BankRobberySmallProgress.BANK_ROBBERY_START);
            JohnBankRobberyInventory.currentlyInBank = null;
            lp.removeLobbyItem(LobbyItem.GOLD_BARDING);
        }
    }

    public static void spawnStoryNpcs(LobbyPlayer lp) {
        Player p = lp.bukkit();

        /* One-Island Story Show NPCs */
        if (lp.getProgressId() <= 17) {
            if (lp.getProgressId() > 0) {
                StoryProgress.getProgressByID(lp.getProgressId()).getNpc().toggleVisibility(p, true);

                if (lp.getProgressId() == StoryProgress.INFECTION.getId()) {
                    PlayerNpc infectionNpc = StoryProgress.INFECTION.getNpc();

                    infectionNpc.setSkin(NpcListener.RUFI_HEALED_SKIN, p);
                    infectionNpc.changeDisplayname(NpcListener.RUFI_HEALED_DISPLAY_NAME, p);
                }
            }

            StoryProgress futureStory = StoryProgress.getProgressByID(lp.getProgressId() + 1);
            if (futureStory != null) {
                futureStory.getNpc().toggleVisibility(p, true);
            }
        }

        /* Tutorial Story Show NPCs */
        if (lp.getTutorialStoryProgressId() <= 7) {
            if (lp.getTutorialStoryProgressId() > 0) {
                TutorialStoryProgress.getTutorialStoryById(lp.getTutorialStoryProgressId()).getNpc().toggleVisibility(p, true);
            }

            TutorialStoryProgress futureTutorial = TutorialStoryProgress.getTutorialStoryById(lp.getTutorialStoryProgressId() + 1);
            if (futureTutorial != null) {
                futureTutorial.getNpc().toggleVisibility(p, true);
            }
        }

        /* Trader Story Show NPCs */
        if (lp.getTraderStoryProgressID() <= 5) {
            if (lp.getTraderStoryProgressID() > 0) {
                TraderStoryProgress.getTraderStoryById(lp.getTraderStoryProgressID()).getNpc().toggleVisibility(p, true);
            }

            TraderStoryProgress futureTutorial = TraderStoryProgress.getTraderStoryById(lp.getTraderStoryProgressID() + 1);
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
