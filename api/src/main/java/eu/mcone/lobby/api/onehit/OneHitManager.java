/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.api.onehit;

import org.bukkit.entity.Player;

public interface OneHitManager {

    void setStart(Player p);

    void leave(Player p);

    boolean isFighting(Player p);

}
