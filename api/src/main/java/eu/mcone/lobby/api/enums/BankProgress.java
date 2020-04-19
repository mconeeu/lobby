package eu.mcone.lobby.api.enums;

import lombok.Getter;

@Getter
public enum BankProgress {

    SMUGGLER(1, "Schmuggler"),
    CUTTER(2,"Schneider"),
    SWORD(3,"Sicherheits-Schwert"),
    BANK_ROBBERY_START(4,"Bank Raub start"),
    BANK_ROBBERY_MIDDLE(5,"Bank Raub mitte"),
    BANK_ROBBERY_END(6,"Bank Raub ende");

    private int id;
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

