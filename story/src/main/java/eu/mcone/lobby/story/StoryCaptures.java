package eu.mcone.lobby.story;

import eu.mcone.coresystem.api.bukkit.npc.entity.PlayerNpc;
import eu.mcone.lobby.api.LobbyWorld;
import lombok.Getter;

@Getter
public enum StoryCaptures {

    VENDOR("capture-vendor", "vendor"),
    RESIDENTS("capture-residents", "citizens"),
    WELCOME("edward-welcome", "edward-welcome"),
    START("capture-start", "edward-start"),
    SALIA("capture-salia", "salia"),
    ROBERT("capture-robert", "robert"),
    FRANK1("capture-frank1", "frank1"),
    FRANK2("capture-frank2", "frank2"),
    FRANK3("capture-frank3", "frank3"),
    FRANK4("capture-frank4", "frank4"),
    FRANK5("capture-frank5", "frank5"),
    FRANK6("capture-frank6", "frank6"),
    FRANK7("capture-frankend", "frank7");

    private final String capture, npcName;

    StoryCaptures(String capture, String npcName) {
        this.capture = capture;
        this.npcName = npcName;
    }

    public PlayerNpc getNpc() {
        return (PlayerNpc) LobbyWorld.ONE_ISLAND.getWorld().getNPC(npcName);
    }

}
