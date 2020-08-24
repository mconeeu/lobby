package eu.mcone.lobby.api.story;

import eu.mcone.coresystem.api.bukkit.npc.NPC;
import eu.mcone.lobby.api.LobbyWorld;
import lombok.Getter;

@Getter
public enum GeneralStoryNpc {

    CASINO(LobbyWorld.ONE_ISLAND, "casino"),
    MERCHANT(LobbyWorld.ONE_ISLAND, "merchant"),
    MERCHANT2(LobbyWorld.ONE_ISLAND,"merchant2"),
    BANKMAN(LobbyWorld.ONE_ISLAND,"bankman"),
    BANKMAN2(LobbyWorld.ONE_ISLAND,"bankman-central"),
    OFFICE_TRADER(LobbyWorld.OFFICE,"officetrader"),
    OFFICE_SELLER(LobbyWorld.ONE_ISLAND,"officeseller"),
    OFFICE_PAGE(LobbyWorld.ONE_ISLAND,"officepage"),
    ASSISTANT_1(LobbyWorld.OFFICE,"assistentin1"),
    ASSISTANT_2(LobbyWorld.OFFICE,"assistentin2"),
    ASSISTANT_3(LobbyWorld.OFFICE,"assistentin3"),
    CHAUFFEUR_1(LobbyWorld.OFFICE,"Chauffeur"),
    CHAUFFEUR_2(LobbyWorld.OFFICE,"Chauffeur1"),
    CHAUFFEUR_3(LobbyWorld.OFFICE,"Chauffeur2"),
    VENDOR(LobbyWorld.ONE_ISLAND,"vendor");

    private final LobbyWorld world;
    private final String npcName;

    GeneralStoryNpc(LobbyWorld world, String npcName) {
        this.world = world;
        this.npcName = npcName;
    }

    public NPC getNpc() {
        return world.getWorld().getNPC(npcName);
    }
    
}
