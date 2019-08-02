package eu.mcone.lobby.api.enums;

import eu.mcone.coresystem.api.bukkit.npc.entity.PlayerNpc;
import eu.mcone.lobby.api.LobbyWorld;
import lombok.Getter;

public enum BankProgress {

    START(1, "Begrüßen"),
    SMUGGLER(2, "Schmuggler"),
    CUTTER(3,"Schneider"),
    SWORD(4,"Sicherheits-Schwerd"),
    BANK_ROBBERY_START(5,"Bank Raub start"),
    BANK_ROBBERY_MIDDLE(6,"Bank Raub mitte"),
    BANK_ROBBERY_END(7,"Bank Raub ende");


    @Getter
    private int id;
    @Getter
    private String name;



    BankProgress(int id, String name){
        this.id = id;
        this.name = name;
    }


    public static BankProgress getProgressByID(int id) {
        for (BankProgress bankprogress : values()) {
            if (bankprogress.getId() == id) {
                return bankprogress;
            }
        }

        return null;
    }

}

