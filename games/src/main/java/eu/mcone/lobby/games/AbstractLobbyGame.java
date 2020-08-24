/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.games;

import eu.mcone.lobby.api.games.LobbyGame;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

@Getter
public abstract class AbstractLobbyGame implements LobbyGame {

    protected final String name;
    protected final ChatColor color;
    protected final String[] shortNames;

    public AbstractLobbyGame(String name, ChatColor color, String... shortNames) {
        this.name = name;
        this.color = color;
        this.shortNames = shortNames;
    }

    public void sendMessage(Player p, String message) {
        p.sendMessage("§8[§7§l!§8]"+color+" §8»§7 "+message);
    }

    public abstract void playerLeaved(Player p);

    public abstract void quitGame(Player p);

}
