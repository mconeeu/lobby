/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.util;

import eu.mcone.coresystem.api.bukkit.facades.Msg;
import eu.mcone.lobby.Lobby;
import eu.mcone.lobby.listener.StackingListener;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class StackingManager {

    private final Map<Player, Player> stacking;

    public StackingManager(Lobby plugin) {
        this.stacking = new HashMap<>();
        plugin.registerEvents(new StackingListener(this));
    }

    public boolean stack(Player carrier, Player stacked) {
        if (!stacking.containsKey(carrier)) {
            stacking.put(carrier, stacked);
            return true;
        } else return false;
    }

    public boolean unstack(Player carrier) {
        if (stacking.containsKey(carrier)) {
            Player stacked = stacking.remove(carrier);
            carrier.eject();
            
            if (stacked != null) {
                Msg.sendInfo(stacked, "![" + carrier.getName() + "] ist nun nicht mehr auf deinem Kopf!");
                Msg.sendInfo(carrier, "![" + stacked.getName() + "] trägt dich nicht mehr!");
            } else {
                Msg.sendInfo(carrier, "Du trägst nun niemand mehr!");
            }

            return true;
        } else return false;
    }

    public Player getStackedPlayer(Player carrier) {
        return stacking.getOrDefault(carrier, null);
    }

    public Player getCarryingPlayer(Player stacked) {
        for (Map.Entry<Player, Player> entry : stacking.entrySet()) {
            if (entry.getValue().equals(stacked)) {
                return entry.getKey();
            }
        }

        return null;
    }

    public boolean isStacked(Player stacked) {
        return stacking.containsValue(stacked);
    }

    public boolean isStacking(Player carrier) {
        return getStackedPlayer(carrier) != null;
    }

    public void playerLeaved(Player player) {
        if (stacking.containsKey(player)) {
            Player stacked = getStackedPlayer(player);

            stacking.remove(player);
            Msg.sendError(stacked, "![" + player.getName() + "] ist nun nicht mehr auf deinem Kopf!");
        } else {
            Player carrying = getCarryingPlayer(player);

            if (carrying != null) {
                stacking.remove(carrying);
                Msg.send(player, "![" + carrying.getName() + "] hat dich fallen gelassen!");
            }
        }
    }

}
