package eu.mcone.lobby.api.story.progress.bank;

import lombok.Getter;

@Getter
public enum BankRobberySmallProgress {

    SMUGGLER(1, "Schmuggler","Schmuggler Karte", new String[]{"§eDu kaufst die Bank Karte"}),
    CUTTER(2,"Schneider","Schneiderei", new String[]{"§eDu kaufst bei der Schneiderei ein"}),
    SWORD(3,"Sicherheits-Schwert","Sicherheits-Schwert", new String[]{"§eDu klaust das Sicherheits schwert"}),
    BANK_ROBBERY_START(4,"Bank Raub start","Bank Raub", new String[]{"§eDu bist in die Bank rein"}),
    BANK_ROBBERY_MIDDLE(5,"Bank Raub mitte","Knacken", new String[]{"§eDu hast den Tresor geknackt"}),
    BANK_ROBBERY_END(6,"Bank Raub ende","Flüchten", new String[]{"§eDu bist geflüchtet"});

    private final int id;
    private final String name, title;
    private final String[] description;

    BankRobberySmallProgress(int id, String name, String title, String[] description){
        this.id = id;
        this.name = name;
        this.title = title;
        this.description = description;
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

