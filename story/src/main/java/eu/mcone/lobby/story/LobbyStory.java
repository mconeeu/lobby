/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.story;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.npc.NPC;
import eu.mcone.coresystem.api.bukkit.npc.entity.PlayerNpc;
import eu.mcone.coresystem.api.bukkit.spawnable.ListMode;
import eu.mcone.lobby.api.LobbyAddon;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.api.enums.StoryProgress;
import eu.mcone.lobby.api.enums.TraderProgress;
import eu.mcone.lobby.api.enums.TutorialStory;
import eu.mcone.lobby.story.listener.*;
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
        /*  OneIsland Story */
        for (StoryProgress storyProgress : StoryProgress.values()) {
            NPC npc = storyProgress.getNpc();
            npc.togglePlayerVisibility(ListMode.WHITELIST);
        }
        /*  Tutorial Story */
        for (TutorialStory tutorialStory : TutorialStory.values()) {
            NPC npc = tutorialStory.getNpc();
            npc.togglePlayerVisibility(ListMode.WHITELIST);
        }
        /*  Trader Story */

        for (TraderProgress traderProgress : TraderProgress.values()) {
            NPC npc = traderProgress.getNpc();
            npc.togglePlayerVisibility(ListMode.WHITELIST);
        }


        Bukkit.getScheduler().runTaskLater(LobbyPlugin.getInstance(), () -> {
            for (Player all : Bukkit.getOnlinePlayers()) {
                if (all.getWorld().getName().equalsIgnoreCase(LobbyWorld.OFFICE.getName())) {
                    LobbyPlugin.getInstance().getOfficeManager().joinOffice(all);
                } else if (all.getWorld().getName().equalsIgnoreCase(LobbyWorld.GUNGAME.getName())) {
                    LobbyPlugin.getInstance().getLobbyWorld(LobbyWorld.ONE_ISLAND).teleportSilently(all, "Spawn");
                }
            }
        }, 20);


        LobbyWorld.ONE_ISLAND.getWorld().getHologram("story-welcome").togglePlayerVisibility(ListMode.WHITELIST);

        ((PlayerNpc) LobbyWorld.ONE_ISLAND.getWorld().getNPC("vendor")).playMotionCapture("capture-vendor");
        ((PlayerNpc) LobbyWorld.ONE_ISLAND.getWorld().getNPC("citizens")).playMotionCapture("capture-residents");
        ((PlayerNpc) LobbyWorld.ONE_ISLAND.getWorld().getNPC("edward-welcome")).playMotionCapture("capture-welcome");
        ((PlayerNpc) LobbyWorld.ONE_ISLAND.getWorld().getNPC("edward-start")).playMotionCapture("capture-start");

        ((PlayerNpc) LobbyWorld.ONE_ISLAND.getWorld().getNPC("salia")).playMotionCapture("capture-salia");
        ((PlayerNpc) LobbyWorld.ONE_ISLAND.getWorld().getNPC("edward-start")).playMotionCapture("capture-start");
        ((PlayerNpc) LobbyWorld.ONE_ISLAND.getWorld().getNPC("robert")).playMotionCapture("capture-robert");


        ((PlayerNpc) LobbyWorld.ONE_ISLAND.getWorld().getNPC("frank1")).playMotionCapture("capture-frank1");
        ((PlayerNpc) LobbyWorld.ONE_ISLAND.getWorld().getNPC("frank2")).playMotionCapture("capture-frank2");
        ((PlayerNpc) LobbyWorld.ONE_ISLAND.getWorld().getNPC("frank3")).playMotionCapture("capture-frank3");
        ((PlayerNpc) LobbyWorld.ONE_ISLAND.getWorld().getNPC("frank4")).playMotionCapture("capture-frank4");
        ((PlayerNpc) LobbyWorld.ONE_ISLAND.getWorld().getNPC("frank5")).playMotionCapture("capture-frank5");
        ((PlayerNpc) LobbyWorld.ONE_ISLAND.getWorld().getNPC("frank6")).playMotionCapture("capture-frank6");

        CoreSystem.getInstance().getNpcManager().getMotionCaptureHandler().getMotionCaptureScheduler().addNpc((PlayerNpc) LobbyWorld.ONE_ISLAND.getWorld().getNPC("frank1"));
        CoreSystem.getInstance().getNpcManager().getMotionCaptureHandler().getMotionCaptureScheduler().addNpc((PlayerNpc) LobbyWorld.ONE_ISLAND.getWorld().getNPC("frank2"));
        CoreSystem.getInstance().getNpcManager().getMotionCaptureHandler().getMotionCaptureScheduler().addNpc((PlayerNpc) LobbyWorld.ONE_ISLAND.getWorld().getNPC("frank3"));
        CoreSystem.getInstance().getNpcManager().getMotionCaptureHandler().getMotionCaptureScheduler().addNpc((PlayerNpc) LobbyWorld.ONE_ISLAND.getWorld().getNPC("frank4"));
        CoreSystem.getInstance().getNpcManager().getMotionCaptureHandler().getMotionCaptureScheduler().addNpc((PlayerNpc) LobbyWorld.ONE_ISLAND.getWorld().getNPC("frank5"));
        CoreSystem.getInstance().getNpcManager().getMotionCaptureHandler().getMotionCaptureScheduler().addNpc((PlayerNpc) LobbyWorld.ONE_ISLAND.getWorld().getNPC("frank6"));

        CoreSystem.getInstance().getNpcManager().getMotionCaptureHandler().getMotionCaptureScheduler().addNpc((PlayerNpc) LobbyWorld.ONE_ISLAND.getWorld().getNPC("robert"));
        CoreSystem.getInstance().getNpcManager().getMotionCaptureHandler().getMotionCaptureScheduler().addNpc((PlayerNpc) LobbyWorld.ONE_ISLAND.getWorld().getNPC("salia"));
        CoreSystem.getInstance().getNpcManager().getMotionCaptureHandler().getMotionCaptureScheduler().addNpc((PlayerNpc) LobbyWorld.ONE_ISLAND.getWorld().getNPC("edward-start"));
        CoreSystem.getInstance().getNpcManager().getMotionCaptureHandler().getMotionCaptureScheduler().addNpc((PlayerNpc) LobbyWorld.ONE_ISLAND.getWorld().getNPC("citizens"));
        CoreSystem.getInstance().getNpcManager().getMotionCaptureHandler().getMotionCaptureScheduler().addNpc((PlayerNpc) LobbyWorld.ONE_ISLAND.getWorld().getNPC("vendor"));
        CoreSystem.getInstance().getNpcManager().getMotionCaptureHandler().getMotionCaptureScheduler().addNpc((PlayerNpc) LobbyWorld.ONE_ISLAND.getWorld().getNPC("edward-welcome"));
    }

    @Override
    public void onDisable() {
    }

}
