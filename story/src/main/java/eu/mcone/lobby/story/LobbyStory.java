/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.story;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.npc.NPC;
import eu.mcone.coresystem.api.bukkit.spawnable.ListMode;
import eu.mcone.coresystem.api.core.exception.MotionCaptureNotDefinedException;
import eu.mcone.lobby.api.LobbyAddon;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.api.enums.StoryProgress;
import eu.mcone.lobby.api.enums.TraderProgress;
import eu.mcone.lobby.api.enums.TutorialStory;
import eu.mcone.lobby.story.listener.*;
import io.sentry.event.Event;
import io.sentry.event.EventBuilder;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class LobbyStory extends LobbyAddon {

    @Getter
    private static LobbyStory instance;

    @Override
    public void onEnable() {
        instance = this;

        LobbyPlugin.getInstance().registerEvents(
                new CoreManagerReloadListener(),
                new LobbyPlayerLoadedListener(),
                new NpcListener(),
                new InventoryTriggerListener(),
                new SignsListener(),
                new WorldChangeListener(),
                new PlayerMoveListener()
        );

        reload();
    }

    @Override
    public void reload() {
        prepareNpcs();
        loadStoryCaptures();
        LobbyWorld.ONE_ISLAND.getWorld().getHologram("story-welcome").togglePlayerVisibility(ListMode.WHITELIST);

        Bukkit.getScheduler().runTaskLater(LobbyPlugin.getInstance(), () -> {
            for (Player all : Bukkit.getOnlinePlayers()) {
                if (all.getWorld().getName().equalsIgnoreCase(LobbyWorld.OFFICE.getName())) {
                    LobbyPlugin.getInstance().getOfficeManager().joinOffice(all);
                } else if (all.getWorld().getName().equalsIgnoreCase(LobbyWorld.GUNGAME.getName())) {
                    LobbyPlugin.getInstance().getLobbyWorld(LobbyWorld.ONE_ISLAND).teleportSilently(all, "Spawn");
                }
            }
        }, 20);
    }

    @Override
    public void onDisable() {
    }

    private static void prepareNpcs() {
        /*  OneIsland Story */
        for (StoryProgress storyProgress : StoryProgress.values()) {
            prepareNpc(storyProgress.getNpcName(), storyProgress.getNpc());
        }
        /*  Tutorial Story */
        for (TutorialStory tutorialStory : TutorialStory.values()) {
            prepareNpc(tutorialStory.getNpcName(), tutorialStory.getNpc());
        }
        /*  Trader Story */
        for (TraderProgress traderProgress : TraderProgress.values()) {
            prepareNpc(traderProgress.getNpcName(), traderProgress.getNpc());
        }
    }

    private static void prepareNpc(String name, NPC npc) {
        try {
            npc.togglePlayerVisibility(ListMode.WHITELIST);
        } catch (NullPointerException e) {
            if (LobbyPlugin.getInstance().hasSentryClient()) {
                LobbyPlugin.getInstance().getSentryClient().sendEvent(new EventBuilder().withLevel(Event.Level.ERROR).withMessage("Could not load npc "+name));
            }

            LobbyPlugin.getInstance().sendConsoleMessage("§cCould not load Npc "+name);
        }
    }

    private static void loadStoryCaptures() {
        for (StoryCaptures capture : StoryCaptures.values()) {
            try {
                capture.getNpc().playMotionCapture(capture.getCapture());
                CoreSystem.getInstance().getNpcManager().getMotionCaptureHandler().getMotionCaptureScheduler().addNpc(capture.getNpc());
            } catch (MotionCaptureNotDefinedException e) {
                if (LobbyPlugin.getInstance().hasSentryClient()) {
                    LobbyPlugin.getInstance().getSentryClient().sendEvent(new EventBuilder().withLevel(Event.Level.ERROR).withMessage("Could not load motion capture "+capture.getCapture()));
                }

                LobbyPlugin.getInstance().sendConsoleMessage("§cCould not load motion capture "+capture.getCapture());
            } catch (NullPointerException e) {
                if (LobbyPlugin.getInstance().hasSentryClient()) {
                    LobbyPlugin.getInstance().getSentryClient().sendEvent(new EventBuilder().withLevel(Event.Level.ERROR).withMessage("Could not load npc "+capture.getNpcName()+" for story motion capture "+capture.name()));
                }

                LobbyPlugin.getInstance().sendConsoleMessage("§cCould not load npc "+capture.getNpcName()+" for story motion capture "+capture.name());
            }
        }
    }

}
