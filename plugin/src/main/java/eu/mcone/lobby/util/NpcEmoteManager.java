package eu.mcone.lobby.util;

import eu.mcone.coresystem.api.bukkit.gamemode.Gamemode;
import eu.mcone.coresystem.api.bukkit.npc.NPC;
import eu.mcone.coresystem.api.bukkit.npc.entity.PlayerNpc;
import eu.mcone.lobby.api.LobbyWorld;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Random;

public class NpcEmoteManager implements Runnable {

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            setEmote(player);
        }
    }

    private static int getRandomNumberInRange() {
        Random r = new Random();
        return r.nextInt((160) + 1);
    }


    public static void setEmote(Player players) {
        int randomeEmote = getRandomNumberInRange();

        for (Gamemode gm : Gamemode.values()) {
            NPC npc = LobbyWorld.ONE_ISLAND.getWorld().getNPC(gm.getName().toLowerCase());
            if (npc != null) {
                ((PlayerNpc) npc).playLabymodEmote(randomeEmote, players);
            }
        }
    }
}
