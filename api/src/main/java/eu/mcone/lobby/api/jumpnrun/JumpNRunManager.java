/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.api.jumpnrun;

import eu.mcone.lobby.api.enums.JumpNRun;
import org.bukkit.entity.Player;

public interface JumpNRunManager {

    void setStart(Player p, JumpNRun jumpNRun);

    void setCheckpoint(Player p, int checkpoint);

    void setCancel(Player p);

    void setFinish(Player p);

    boolean isJumping(Player p);
}
