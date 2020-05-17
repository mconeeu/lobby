package eu.mcone.lobby.api.enums.bank.central;

import lombok.Getter;

@Getter
public enum BankProgress {

    SPY(1, "Ausspähen"),
    HACKER_TERMINAL(2, "Hacker Terminal"), //roger npc // other small mission
    CENTRAL_PLAN(3, "Plan der Central Bank"), //at the bank bankman-central
    SWORD(4, "Schwert"), //idk
    CUSTOMER(5, "Befragen des Mitarbeiters"), //bank-worker
    CHOOSE(6, "Auswahl"); //choose your chapter 2 //duo //boom //silent ..

    private int id;
    private String name;

    BankProgress(int id, String name) {
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

