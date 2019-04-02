/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Getter @Setter
public class LobbyPlayerProfile extends GameProfile {

    private List<Integer> items = new ArrayList<>();
    private int chests, progressId;
    private LobbySettings settings = new LobbySettings();
    private Map<String, Long> secrets = new HashMap<>();

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
        secrets = lp.getSecrets();
    }

    public List<Item> getItemList() {
        List<Item> result = new ArrayList<>();
        for (int id : items) {
            result.add(Item.getItemByID(id));
        }
        return result;
    }

}
