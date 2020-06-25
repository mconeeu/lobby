/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.story;

import eu.mcone.coresystem.api.bukkit.npc.NPC;
import eu.mcone.coresystem.api.bukkit.spawnable.ListMode;
import eu.mcone.lobby.api.LobbyAddon;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.api.enums.StoryProgress;
import eu.mcone.lobby.api.enums.TraderProgress;
import eu.mcone.lobby.api.enums.TutorialStory;
import eu.mcone.lobby.story.listener.*;
import lombok.Getter;

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


        LobbyWorld.ONE_ISLAND.getWorld().getHologram("story-welcome").togglePlayerVisibility(ListMode.WHITELIST);

       /* ((PlayerNpc) LobbyWorld.ONE_ISLAND.getWorld().getNPC("vendor")).playMotionCapture("capture-vendor");
        ((PlayerNpc) LobbyWorld.ONE_ISLAND.getWorld().getNPC("edward-welcome")).playMotionCapture("capture-welcome");
        ((PlayerNpc) LobbyWorld.ONE_ISLAND.getWorld().getNPC("edward-start")).playMotionCapture("capture-start");
        ((PlayerNpc) LobbyWorld.ONE_ISLAND.getWorld().getNPC("duty")).playMotionCapture("capture-duty");
        ((PlayerNpc) LobbyWorld.ONE_ISLAND.getWorld().getNPC("salia")).playMotionCapture("capture-salia");
        ((PlayerNpc) LobbyWorld.ONE_ISLAND.getWorld().getNPC("robert")).playMotionCapture("capture-robert");
        ((PlayerNpc) LobbyWorld.ONE_ISLAND.getWorld().getNPC("edward-cityhall")).playMotionCapture("capture-cityhall"); */
    }

    @Override
    public void onDisable() {
    }

}
