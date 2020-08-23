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
import eu.mcone.lobby.api.story.LobbyStoryManager;
import eu.mcone.lobby.api.story.progress.StoryProgress;
import eu.mcone.lobby.api.story.progress.TraderStoryProgress;
import eu.mcone.lobby.api.story.progress.TutorialStoryProgress;
import eu.mcone.lobby.story.listener.*;
import eu.mcone.lobby.story.office.LobbyOfficeManager;
import io.sentry.event.Event;
import io.sentry.event.EventBuilder;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class LobbyStory extends LobbyAddon implements LobbyStoryManager {

    @Getter
    private static LobbyStory instance;
    @Getter
    private LobbyOfficeManager officeManager;

    @Override
    public void onEnable(LobbyPlugin plugin) {
        instance = this;
        this.officeManager = new LobbyOfficeManager(plugin);

        plugin.registerEvents(
                new AfkListener(),
                new CoreManagerReloadListener(),
                new GeneralPlayerListener(),
                new NpcListener(),
                new InventoryTriggerListener(),
                new SignsListener(),
                new WorldChangeListener(),
                new PlayerMoveListener()
        );

        reload(plugin);
    }

    @Override
    public void reload(LobbyPlugin plugin) {
        prepareNpcs();
        loadStoryCaptures();
        LobbyWorld.ONE_ISLAND.getWorld().getHologram("story-welcome").togglePlayerVisibility(ListMode.WHITELIST);

        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            for (Player all : Bukkit.getOnlinePlayers()) {
                if (all.getWorld().getName().equalsIgnoreCase(LobbyWorld.OFFICE.getName())) {
                    getOfficeManager().joinOffice(all);
                } else if (all.getWorld().getName().equalsIgnoreCase(LobbyWorld.GUNGAME.getName())) {
                    plugin.getLobbyWorld(LobbyWorld.ONE_ISLAND).teleportSilently(all, "spawn");
                }
            }
        }, 20);
    }

    @Override
    public void onDisable(LobbyPlugin plugin) {
    }

    private static void prepareNpcs() {
        /*  OneIsland Story */
        for (StoryProgress storyProgress : StoryProgress.values()) {
            prepareNpc(storyProgress.getNpcName(), storyProgress.getNpc());
        }
        /*  Tutorial Story */
        for (TutorialStoryProgress tutorialStoryProgress : TutorialStoryProgress.values()) {
            prepareNpc(tutorialStoryProgress.getNpcName(), tutorialStoryProgress.getNpc());
        }
        /*  Trader Story */
        for (TraderStoryProgress traderStoryProgress : TraderStoryProgress.values()) {
            prepareNpc(traderStoryProgress.getNpcName(), traderStoryProgress.getNpc());
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
