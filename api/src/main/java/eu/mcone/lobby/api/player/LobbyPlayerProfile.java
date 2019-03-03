/*
 * Copyright (c) 2017 - 2018 Dominik Lippl, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.api.player;

import eu.mcone.coresystem.api.bukkit.player.profile.GameProfile;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.enums.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter @Setter
public class LobbyPlayerProfile extends GameProfile {

    private List<Integer> items = new ArrayList<>();
    private int chests, progressId;
    private LobbySettings settings = new LobbySettings();

    LobbyPlayerProfile(Player p) {
        super(p);

        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p.getUniqueId());

        items = new ArrayList<>();
        for (Item item : lp.getItems()) {
            items.add(item.getId());
        }
        chests = lp.getChests();
        progressId = lp.getProgressId();
        settings = lp.getSettings();
    }

    public List<Item> getItems() {
        List<Item> result = new ArrayList<>();
        for (int id : items) {
            result.add(Item.getItemByID(id));
        }
        return result;
    }

}
