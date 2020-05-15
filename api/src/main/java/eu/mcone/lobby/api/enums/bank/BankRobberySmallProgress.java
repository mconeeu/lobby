package eu.mcone.lobby.api.enums.bank;

import lombok.Getter;

@Getter
public enum BankRobberySmallProgress {

    SMUGGLER(1, "Schmuggler"),
    CUTTER(2,"Schneider"),
    SWORD(3,"Sicherheits-Schwert"),
    BANK_ROBBERY_START(4,"Bank Raub start"),
    BANK_ROBBERY_MIDDLE(5,"Bank Raub mitte"),
    BANK_ROBBERY_END(6,"Bank Raub ende");

    private int id;
    private String name;

    BankRobberySmallProgress(int id, String name){
        this.id = id;
        this.name = name;
    }

    public static BankRobberySmallProgress getProgressByID(int id) {
        for (BankRobberySmallProgress bankprogress : values()) {
            if (bankprogress.getId() == id) {
                return bankprogress;
            }
        }

        return null;
    }

}

