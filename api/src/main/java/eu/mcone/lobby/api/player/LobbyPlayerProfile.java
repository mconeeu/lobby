/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.api.player;

import eu.mcone.coresystem.api.bukkit.player.profile.GameProfile;
import eu.mcone.lobby.api.enums.JumpNRun;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@Getter
@Setter
public class LobbyPlayerProfile extends GameProfile {

    private int chests, progressId, bankprogressId, centralbankprogressId, tuturialStoryId, traderStoryProgressID;
    private Date dailyReward;
    private LobbySettings settings = new LobbySettings();
    private Map<String, Long> secrets = new HashMap<>(), jumpnruns = new HashMap<>();

    private transient Map<JumpNRun, Long> jumpnrunSet = new HashMap<>();

    LobbyPlayerProfile(Player p, int chests, int progressId, int bankprogressId, int centralbankprogressId, int tuturialStoryId, int traderStoryProgressID, Date dailyReward, LobbySettings settings, Map<String, Long> secrets, Map<JumpNRun, Long> jumpnruns) {
        super(p);

        this.chests = chests;
        this.progressId = progressId;
        this.bankprogressId = bankprogressId;
        this.centralbankprogressId = centralbankprogressId;
        this.tuturialStoryId = tuturialStoryId;
        this.traderStoryProgressID = traderStoryProgressID;
        this.dailyReward = dailyReward;
        this.settings = settings;
        this.secrets = secrets;

        for (Map.Entry<JumpNRun, Long> entry : jumpnruns.entrySet()) {
            this.jumpnruns.put(String.valueOf(entry.getKey().getId()), entry.getValue());
        }
    }

    @Override
    public void doSetData(Player player) {
        for (Map.Entry<String, Long> entry : jumpnruns.entrySet()) {
            jumpnrunSet.put(JumpNRun.getJumpNRunById(Integer.parseInt(entry.getKey())), entry.getValue());
        }
    }

}
