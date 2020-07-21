package eu.mcone.lobby.story;

import eu.mcone.coresystem.api.bukkit.npc.entity.PlayerNpc;
import eu.mcone.lobby.api.LobbyWorld;
import lombok.Getter;

@Getter
public enum StoryCaptures {

    VENDOR("capture-vendor", (PlayerNpc) LobbyWorld.ONE_ISLAND.getWorld().getNPC("vendor")),
    RESIDENTS("capture-residents", (PlayerNpc) LobbyWorld.ONE_ISLAND.getWorld().getNPC("citizens")),
    WELCOME("edward-welcome", (PlayerNpc) LobbyWorld.ONE_ISLAND.getWorld().getNPC("edward-welcome")),
    START("capture-start", (PlayerNpc) LobbyWorld.ONE_ISLAND.getWorld().getNPC("edward-start")),
    SALIA("capture-salia", (PlayerNpc) LobbyWorld.ONE_ISLAND.getWorld().getNPC("salia")),
    ROBERT("capture-robert", (PlayerNpc) LobbyWorld.ONE_ISLAND.getWorld().getNPC("robert")),
    FRANK1("capture-frank1", (PlayerNpc) LobbyWorld.ONE_ISLAND.getWorld().getNPC("frank1")),
    FRANK2("capture-frank2", (PlayerNpc) LobbyWorld.ONE_ISLAND.getWorld().getNPC("frank2")),
    FRANK3("capture-frank3", (PlayerNpc) LobbyWorld.ONE_ISLAND.getWorld().getNPC("frank3")),
    FRANK4("capture-frank4", (PlayerNpc) LobbyWorld.ONE_ISLAND.getWorld().getNPC("frank4")),
    FRANK5("capture-frank5", (PlayerNpc) LobbyWorld.ONE_ISLAND.getWorld().getNPC("frank5")),
    FRANK6("capture-frank6", (PlayerNpc) LobbyWorld.ONE_ISLAND.getWorld().getNPC("frank6"));

    private final String capture;
    private final PlayerNpc npc;

    StoryCaptures(String capture, PlayerNpc npc) {
        this.capture = capture;
        this.npc = npc;
    }
}
