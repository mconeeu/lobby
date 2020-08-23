/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.games.jumpnrun;

import eu.mcone.lobby.api.games.jumpnrun.JumpNRun;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.entity.Player;

@Getter
@RequiredArgsConstructor
public class JumpNRunPlayer {

    private final Player player;
    private final JumpNRun jumpNRun;
    private final long time;
    @Setter
    private int checkpoint = 0;

}
