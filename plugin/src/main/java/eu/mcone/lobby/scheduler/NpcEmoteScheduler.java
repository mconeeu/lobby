/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.scheduler;

import eu.mcone.coresystem.api.bukkit.gamemode.Gamemode;
import eu.mcone.coresystem.api.bukkit.npc.NPC;
import eu.mcone.coresystem.api.bukkit.npc.entity.PlayerNpc;
import eu.mcone.lobby.api.LobbyWorld;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Random;

public class NpcEmoteScheduler implements Runnable {

    private static final Random EMOTE_RANDOM = new Random();

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            setEmote(player);
        }
    }

    public static void setEmote(Player player) {
        for (Gamemode gm : Gamemode.values()) {
            NPC npc = LobbyWorld.ONE_ISLAND.getWorld().getNPC(gm.getName().toLowerCase());

            if (npc != null && npc.canBeSeenBy(player)) {
                ((PlayerNpc) npc).playLabymodEmote(EMOTE_RANDOM.nextInt((160) + 1), player);
            }
        }
    }

}
