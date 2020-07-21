package eu.mcone.lobby.api.enums;

import eu.mcone.coresystem.api.bukkit.npc.entity.PlayerNpc;
import eu.mcone.lobby.api.LobbyWorld;
import lombok.Getter;

@Getter
public enum TutorialStory {

    //TEIL |

    WELCOME(1, "Begrüßung", new String[]{"§eFrank begrüßt sich"}, "frank1", LobbyWorld.ONE_ISLAND,
            "§8[§7§l!§8] §cNPC §8» §fFrank §8|§7 Hey Ich bin Frank. Ich zeige hier jeden den Weg, ich wohne hier auf der Insel schon seit über 20 Jahren. Such doch mal den Ankäufer Marktplatz. Ich werde dort auf dich warten."),

    VENDOR(2, "AnKäufer", new String[]{"§eFrank zeigt dir den Verkäufer"}, "frank2", LobbyWorld.ONE_ISLAND,
            "§8[§7§l!§8] §cNPC §8» §fFrank §8|§7 Ahoi da bist du wieder hier ist unser kleiner Insel Marktplatz du kannst beim Ankäufer der sich immer durch die Lobby bewegt alle deine Items in deinem Rucksack verkaufen. Probiers doch mal aus! Als nächstes such doch mal den Schmuggler."),

    SMUGGLER(3, "Schmuggler", new String[]{"§eFrank zeigt dir den Schmuggler"}, "frank3", LobbyWorld.ONE_ISLAND,
            "§8[§7§l!§8] §cNPC §8» §fFrank §8|§7 Pass auf der Schmuggler handelt nur mit Leuten die einen Ausweis haben. Wenn du noch kein hast hol in dir bei Edward und wenn wir gerade von Edward reden er hat gesagt du sollst auch mal bei ihm vorbeischauen. Als nächstes zeige ich dir den Insel Händler. Ich verstecke mich dort suche mich!"),

    TRADER(4, "Insel-Händler", new String[]{"§eFrank zeigt dir den Händler"}, "frank4", LobbyWorld.ONE_ISLAND,
            "§8[§7§l!§8] §cNPC §8» §fFrank §8|§7 Du hast mich gefunden. Das ist unser Insel Händler er hat jeden Tag neue Ware im Täglichen Shop oder du kaufst dir einfach ein paar Kisten und öffnest die sie dann beim Chestopening. Ich warte auf dich bei der Bank!"),

    BANK(5, "Bank", new String[]{"§eFrank zeigt dir die Bank"}, "frank5", LobbyWorld.ONE_ISLAND,
            "§8[§7§l!§8] §cNPC §8» §fFrank §8|§7 Hier ist unsere kleine Bank. Wenn du noch kein Konto hast kannst du dir es hier erstellen. Damit kannst du dir jeden Tag eine Belohnung abholen und auch Coins in wertvolle Emeralds umtauschen. Suche jetzt mal das Dorf Skyleck ich warte beim Dorf Eingang auf dich"),

    OTHER_ISLAND(6, "Andere Insel", new String[]{"§eFrank zeigt dir das andere Dorf"}, "frank6", LobbyWorld.ONE_ISLAND,
            "§8[§7§l!§8] §cNPC §8» §fFrank §8|§7 Der Letzte Punkt in meiner kleinen OneIsland Tour. Auf der anderen Seite der Brücke befindet sich das Dorf Skyleck das musst du aber selber erkunden. Ich habe dir auch noch ein kleines Geschenk in deinen Rucksack gelegt und jetzt kaufe ich mir erstmal ein cooles Büro!"),

    MOVE(7, "Wiedersehen", new String[]{"§eDu triffst Frank wieder"}, "frank7", LobbyWorld.ONE_ISLAND,
            "§8[§7§l!§8] §cNPC §8» §fFrank §8|§7 Ich habe dir doch schon alles gezeigt oder?");


    private final int id;
    private final String name, npcName, message;
    private final LobbyWorld world;
    private final String[] description;

    TutorialStory(int id, String name, String[] description, String npcName, LobbyWorld world, String message) {
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

    public static TutorialStory getTutorialStoryById(int id) {
        for (TutorialStory tutorialStory : values()) {
            if (tutorialStory.getId() == id) {
                return tutorialStory;
            }
        }

        return null;
    }
}

