package eu.mcone.lobby.api.enums;

import eu.mcone.coresystem.api.bukkit.npc.entity.PlayerNpc;
import eu.mcone.lobby.api.LobbyWorld;
import lombok.Getter;

@Getter
public enum TraderProgress {

    //TEIL |

    WELCOME(1, "Begrüßung", new String[]{"§eJanoff begrüßt dich"}, "warehouse-Janoff", LobbyWorld.ONE_ISLAND,
            "§8[§7§l!§8] §cNPC §8» §fJanoff §8|§7 ");

    private final int id;
    private final String name, npcName, message;
    private final LobbyWorld world;
    private final String[] description;

    TraderProgress(int id, String name, String[] description, String npcName, LobbyWorld world, String message) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.npcName = npcName;
        this.world = world;
        this.message = message;
    }

    public PlayerNpc getNpc() {
        return (PlayerNpc) world.getWorld().getNPC(npcName);
    }

    public static TraderProgress getTraderStoryById(int id) {
        for (TraderProgress traderProgress : values()) {
            if (traderProgress.getId() == id) {
                return traderProgress;
            }
        }

        return null;
    }
}

