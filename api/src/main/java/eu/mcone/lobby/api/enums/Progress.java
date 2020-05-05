/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.api.enums;

import eu.mcone.coresystem.api.bukkit.npc.entity.PlayerNpc;
import eu.mcone.lobby.api.LobbyWorld;
import lombok.Getter;

public enum Progress {

    //TEIL |

    WELCOME(1, "Begrüßen", new String[]{"§eEdward heißt dich Willkommen"}, "edward-welcome", LobbyWorld.ONE_ISLAND, "§8[§7§l!§8] §cNPC §8» §fEdward §8| §7Hallo Einreisender dich habe ich hier auf der Insel noch nie gesehen du siehst klug aus. Hast du einen Ausweis, weil auf dieser Insel ist Ausweis Plicht und kannst du mir vielleicht helfen also nicht nur mir sondern die ganze Insel brauch deine Hilfe aber jetzt hol dir erstmal deinen Ausweis ab und dann komm zu mir. Ich werde dann irgendwo draußen auf dich warten, du wirst mich schon finden."),
    DUTY(2, "Pass", new String[]{"§eDer Zoll hat dir dein Personalausweis gegeben"}, "duty", LobbyWorld.ONE_ISLAND, "§8[§7§l!§8] §cNPC §8» §fZoll §8| §7Halloo Neuling Du brauchst ein Pass ich habe schon eine Vorlage von deinem altem Pass aber jetzt Herzlich willkommen auf One Island und hier ist dein Pass. Geh einfach noch einmal durch den Metall Detecktor und dann ist alles in Ordnung!"),
    START(3, "Einführung", new String[]{"§eEinführung ins Geschehen"}, "edward-start", LobbyWorld.ONE_ISLAND, "§8[§7§l!§8] §cNPC §8» §fEdward §8| §7Da bist du ja wieder ich dachte du kommst garnicht mehr! Du heißt also %%player%%. So aber jetzt zum Thema warum du überhaupt noch einmal zu mir kommen solltest unser geliebter Bürgermeister wurde entführt und wurde mit einem Virus infiziert! Du musst ihn finden und heilen! sonnst wird er es nicht überleben. Du musst zu meinem Freund Salia er weiß alles über ihn. Er hält sich meisten beim großen One Island Strand auf, weil er Muscheln mag!"),
    SALIA(4, "Informationen", new String[]{"§eWichtige Informationen über", "§eden Bürgermeister"}, "salia", LobbyWorld.ONE_ISLAND, "§8[§7§l!§8] §cNPC §8» §fSalia §8| §7Ahh du musst der neue Freund von Edward sein! Du willst helfen den Bürgermeister zu finden und ihn zu heilen. Der Bürgermeister heißt Rufi und es war mein bester Freund. Wenn du es schaffst ihn zu heilen wirst du von mir und der ganzen Insel ein Geschenk bekommen. Aber wo er sich momentan auffhält weiß momentan keiner. Ich weiß nur wo du so ein Heilstoff her bekommst mein Freund Robert ist ein Magie Profi. Er hält sich meisten am Leuchturm auf!"),
    ROBERT(5, "Anfänger Magie", new String[]{"§eEinfache Magie Tricks!", "§eund du hast ein Zauberstab bekommen"}, "robert", LobbyWorld.ONE_ISLAND, "§8[§7§l!§8] §cNPC §8» §fRobert §8|§7 §ePeng PANG pufff §7%%player%% du bist es ich kann Gedanken lesen. Aber um erlich zu sein hat es mir Salia verraten ich wusste das du kommst nur Salia denkt ich bin Magiar. Ich bin aber nur ein Magiar Lehrling also kann ich dir kein Gegengift herrstellen. Aber meine Lehrerin Mandalai die Hexe kann dir bestimmt so etwas zaubern sie wohnt ganz in der nähe in einen merkwürdigen Haus!"),
    MANDALAI(6, "Hexe", new String[]{"§eDu hast ein Zauber Trank gekauft"}, "mandalai", LobbyWorld.ONE_ISLAND, "§8[§7§l!§8] §cNPC §8» §fMandalai §8|§7 Hallo %%player%%. Uhh was hast du denn da in deinem Rucksack ein §5Zauberstab §7der sieht echt gut aus. aber du willst doch bestimmt nur irgend etwas kaufen. Nehm dir am besten deinen Trank aus meinen Kelch lass einfach dafür 20 Coins da. Achja besuch dann Edward er hat gesagt er wartet auf dich im Rathaus."),
    EDWARD_CITYHALL(7, "Lage Besprechung", new String[]{"§eEdward hat dir genauere Kordinaten von Rufi gegeben"}, "edward-cityhall", LobbyWorld.ONE_ISLAND, "§8[§7§l!§8] §cNPC §8» §fEdward §8|§7 %%player%% da bist wieder und du hast den Trank dabei. Jetzt musst du nur noch schnell Rufi finden er wird warscheinlich irgenwo festgehalten."),
    INFECTION(8, "Infizierung", new String[]{"§eDu hast den Bürgermeister Rufi geheilt!"}, "rufi-infected", LobbyWorld.ONE_ISLAND, "§8[§7§l!§8] §cNPC §8» §fHeer Rufi §8|§7 Danke du hast mich gerretet du kannst dir eine Belohnung in der Mitte von der Insel abholen da ist ein sehr großes Loch spring da runter und dann kannst du dir deine Belohnung abholen. Ich komm ab jetzt hier alleine zurecht."),
    ONEHIT_SWORD(9, "Belohnung", new String[]{"§eDu hast deine Belohnung abgeholt"}, "edward-cave", LobbyWorld.ONE_ISLAND, "§8[§7§l!§8] §cNPC §8» §fEdward §8|§7 Du hast es geschaft den Bürgermeister zu finden und zu heilen jetzt musst nur noch die Täter finden. Sie sind warscheinlich zu Paradise Island geflohen das ist unsere Nachbar Insel. ich weiß das sie da sind weil mein Freund Sparow da lebt. Aber jetzt brauche ich erstmal eine große und lange Pause. Achja nehme dir hier aus der Kiste deine Belohnun!"),

    //TEIL ||

    //ONEHIT_SWORD(9, "Belohnung", new String[]{"§eDu hast die Belohnung bekommen"}, "edward-cave", LobbyWorld.ONE_ISLAND, "§8[§7§l!§8] §cNPC §8» §fEdward §8|§7 Du hast es geschaft den Bürgermeister zu helfen jetzt musst nur noch die Täter finden und sie sind nicht mehr hier du musst zu Paradise Island ich weiß das sie da sind weil mein Freund Sparow da lebt wir können aber mit ein funkgerät zusammen bleiben wenn du auf diese Mission gehst. Du musst zum Fähren verkäufer am großen Hafen da kannst du dir ein Ticket zu Paradise Island kaufen. "),
    SPAROW(10, "Treffen", new String[]{"§eDu hast dich mit Edwards Freund getroffen"}, "sparow", LobbyWorld.PARADISE_ISLAND, "§8[§7§l!§8] §cNPC §8» §fSparow §8|§7 Hi du musst %%player%% Edward hat mir viel über dir erzählt aber leider kannst du uns nicht helfen die Polizei hat Marvin gefagen ach ja sicher du weißt garnicht wer Marvin ist oder ? oke ich erzähle es dir wo Marvin rufi infietiert hat ist er danach sofort hier nach Paradise Island ausgewandert er wollte sich ein Hotel mieten aber leider ist Angela Merkel und Donald Trump hier deswegen hat er kein Schlüssel bekommen und dann hab ich nur in der Post gelesen das Marvin gefangen wurde ich glaub die Hotel Rezeption weiß mehr darüber ach noch was du musst edward bescheid sagen am besten machst du das über dein Funkgerät er hat gesagt einfach drauf drücken oder so dann bist du verbundem"),
    RECEPTION(11, "Treffen", new String[]{"§eDu hast dich mit der Rezeption unterhalten"}, "reception", LobbyWorld.PARADISE_ISLAND, "§8[§7§l!§8] §cNPC §8» §fRezepzion §8|§7 Hi du bist der der Marvin töten wollte ? er wurde schon gefangen nur zu Info er wollte sich hier ein Ticket holen hat es nicht geschaft , weil Angela Merkel und Donald Trump da sind oke mehr kann ich dir auch nicht verraten außer das Marvin im Knast sitzt kannst ja mal hingehen."),
    POLICE(12, "Police", new String[]{"§eDu hast dich mit der Polizei unterhalten"}, "police", LobbyWorld.PARADISE_ISLAND, "§8[§7§l!§8] §cNPC §8» §fPolizei §8|§7 Schönen Guten Tag sie wollen bestimmnt mit Marvin reden oder unser bester fang sie können einfach gerade aus gehen er ist in seiner Zelle"),
    MARVIN(13, "Marvin", new String[]{"§eDu hast dich mit den Mörder Marvin unterhalten"}, "marvin", LobbyWorld.PARADISE_ISLAND, "§8[§7§l!§8] §cNPC §8» §fMarvin §8|§7 Arhh Orhh Hahaha du bist der der mich töten wollte richtig §4Peng"),
    MARVIN_KILL(14, "Marvin2", new String[]{"§eDu wurdest entführt"}, "marvin-welcome", LobbyWorld.CAVE, "§8[§7§l!§8] §cNPC §8» §fMarvin §8|§7 Ohh da bist du jetzt gefangen und jetzt bringe ich dich um weil du Rufi gerretes hast komm raus aus deiner Zelle ich warte auf dich da hinten"),
    SPAROW_DESTROYED_ISLE(15, "Sparow", new String[]{"§eDu redest mit Sparow"}, "sparow", LobbyWorld.DESTROYED_PARADISE_ISLAND, "§8[§7§l!§8] §cNPC §8» §fSparow §8|§7 Ohh %%player%% wo warst du wir haben dich überall gesucht wurdest du entführt ? Wir haben uns große sorgen gemacht Edward konnte dich nicht mehr tracken über dem GPS sender Edward hat gesagt du sollst schnell wieder zurück zu One Island kommen er hat irgendetwas großes Entdeckt, Ich verabschiede mich jetzt deswegen erstmal von dir lebe wohl und passe auf dich auf!");

    //TEIL |||




    @Getter
    private int id;
    @Getter
    private String name, npcName, message;
    @Getter
    private LobbyWorld world;
    @Getter
    private String[] description;

    Progress(int id, String name, String[] description, String npcName, LobbyWorld world, String message) {
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

    public static Progress getProgressByID(int id) {
        for (Progress progress : values()) {
            if (progress.getId() == id) {
                return progress;
            }
        }

        return null;
    }

}
