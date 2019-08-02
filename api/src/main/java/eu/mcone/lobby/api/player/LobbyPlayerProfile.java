/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.api.player;

import eu.mcone.coresystem.api.bukkit.player.profile.GameProfile;
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
    private int chests, progressId, bankprogressId;
    private LobbySettings settings = new LobbySettings();
    private Map<String, Long> secrets = new HashMap<>();

    private transient List<Item> itemList = new ArrayList<>();

    LobbyPlayerProfile(Player p, List<Item> items, int chests, int progressId, int bankprogressId, LobbySettings settings, Map<String, Long> secrets) {
        super(p);

        this.items = new ArrayList<>();
        for (Item item : items) {
            this.items.add(item.getId());
        }
        this.chests = chests;
        this.progressId = progressId;
        this.bankprogressId = bankprogressId;
        this.settings = settings;
        this.secrets = secrets;
    }

    @Override
    public void doSetData(Player player) {
        for (int id : items) {
            itemList.add(Item.getItemByID(id));
        }
    }

}
