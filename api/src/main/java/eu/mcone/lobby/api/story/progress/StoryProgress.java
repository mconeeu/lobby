/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.api.story.progress;

import eu.mcone.coresystem.api.bukkit.npc.entity.PlayerNpc;
import eu.mcone.lobby.api.LobbyWorld;
import lombok.Getter;

@Getter
public enum StoryProgress {

    //TEIL |

    WELCOME(1, "Begrüßen", new String[]{"§eEdward heißt dich Willkommen"}, "edward-welcome", LobbyWorld.ONE_ISLAND, "§8[§7§l!§8] §cNPC §8» §fEdward §8| §7Hallo Einreisender dich habe ich hier auf der Insel noch nie gesehen du siehst klug aus. Hast du einen Ausweis, weil auf dieser Insel ist Ausweispflicht und kannst du mir vielleicht helfen also nicht nur mir sondern die ganze Insel brauch deine Hilfe aber jetzt hol dir erstmal deinen Ausweis ab und dann komm zu mir. Ich werde dann irgendwo draußen auf dich warten, du wirst mich schon finden."),
    DUTY(2, "Pass", new String[]{"§eDer Zoll hat dir dein Personalausweis gegeben"}, "duty", LobbyWorld.ONE_ISLAND, "§8[§7§l!§8] §cNPC §8» §fZoll §8| §7Halloo Neuling Du brauchst ein Pass ich habe schon eine Vorlage von deinem altem Pass aber jetzt Herzlich willkommen auf One Island und hier ist dein Pass. Geh einfach noch einmal durch den Metall Detecktor und dann ist alles in Ordnung!"),
    START(3, "Einführung", new String[]{"§eEinführung ins Geschehen"}, "edward-start", LobbyWorld.ONE_ISLAND, "§8[§7§l!§8] §cNPC §8» §fEdward §8| §7Da bist du ja wieder ich dachte du kommst garnicht mehr! Du heißt also %%player%%. So aber jetzt zum Thema warum du überhaupt noch einmal zu mir kommen solltest unser geliebter Bürgermeister wurde entführt und wurde mit einem Virus infiziert! Du musst ihn finden und heilen! sonnst wird er es nicht überleben. Du musst zu meinem Freund Salia er weiß alles über ihn. Er hält sich meisten beim großen One Island Strand auf, weil er Muscheln mag!"),
    SALIA(4, "Informationen", new String[]{"§eWichtige Informationen über", "§eden Bürgermeister"}, "salia", LobbyWorld.ONE_ISLAND, "§8[§7§l!§8] §cNPC §8» §fSalia §8| §7Ahh du musst der neue Freund von Edward sein! Du willst helfen den Bürgermeister zu finden und ihn zu heilen. Der Bürgermeister heißt Rufi und es war mein bester Freund. Wenn du es schaffst ihn zu heilen wirst du von mir und der ganzen Insel ein Geschenk bekommen. Aber wo er sich momentan auffhält weiß momentan keiner. Ich weiß nur wo du so ein Heilstoff her bekommst mein Freund Robert ist ein Magie Profi. Er hält sich meisten am Leuchturm auf!"),
    ROBERT(5, "Anfänger Magie", new String[]{"§eEinfache Magie Tricks!", "§eund du hast ein Zauberstab bekommen"}, "robert", LobbyWorld.ONE_ISLAND, "§8[§7§l!§8] §cNPC §8» §fRobert §8|§7 §ePeng PANG pufff §7%%player%% du bist es ich kann Gedanken lesen. Aber um erlich zu sein hat es mir Salia verraten ich wusste das du kommst nur Salia denkt ich bin Magiar. Ich bin aber nur ein Magiar Lehrling also kann ich dir kein Gegengift herrstellen. Aber meine Lehrerin Mandalai die Hexe kann dir bestimmt so etwas zaubern sie wohnt ganz in der nähe in einen merkwürdigen Haus!"),
    MANDALAI(6, "Hexe", new String[]{"§eDu hast ein Zauber Trank gekauft"}, "mandalai", LobbyWorld.ONE_ISLAND, "§8[§7§l!§8] §cNPC §8» §fMandalai §8|§7 Hallo %%player%%. Uhh was hast du denn da in deinem Rucksack ein §5Zauberstab §7der sieht echt gut aus. aber du willst doch bestimmt nur irgend etwas kaufen. Nehm dir am besten deinen Trank aus meinen Kelch lass einfach dafür 20 Coins da. Achja besuch dann Edward er hat gesagt er wartet auf dich im Rathaus."),
    EDWARD_CITYHALL(7, "Lage Besprechung", new String[]{"§eEdward hat dir genauere Kordinaten von Rufi gegeben"}, "edward-cityhall", LobbyWorld.ONE_ISLAND, "§8[§7§l!§8] §cNPC §8» §fEdward §8|§7 %%player%% da bist wieder und du hast den Trank dabei. Jetzt musst du nur noch schnell Rufi finden er wird warscheinlich irgenwo festgehalten."),
    INFECTION(8, "Infizierung", new String[]{"§eDu hast den Bürgermeister Rufi geheilt!"}, "rufi-infected", LobbyWorld.ONE_ISLAND, "§8[§7§l!§8] §cNPC §8» §fHeer Rufi §8|§7 Danke du hast mich gerretet du kannst dir eine Belohnung in der Mitte von der Insel abholen da ist ein sehr großes Loch spring da runter und dann kannst du dir deine Belohnung abholen. Ich komm ab jetzt hier alleine zurecht."),
    ONEHIT_SWORD(9, "Belohnung", new String[]{"§eDu hast deine Belohnung abgeholt"}, "edward-cave", LobbyWorld.ONE_ISLAND, "§8[§7§l!§8] §cNPC §8» §fEdward §8|§7 Du hast es geschaft den Bürgermeister zu finden und zu heilen jetzt musst nur noch die Täter finden. Sie sind warscheinlich zu Paradise Island geflohen das ist unsere Nachbar Insel. ich weiß das sie da sind weil mein Freund Sparow da lebt. Aber jetzt brauche ich erstmal eine große und lange Pause. Achja nehme dir hier aus der Kiste deine Belohnun!"),

    //TEIL ||

    //  ONEHIT_SWORD(9, "Belohnung", new String[]{"§eDu hast die Belohnung bekommen"}, "edward-cave", LobbyWorld.ONE_ISLAND, "§8[§7§l!§8] §cNPC §8» §fEdward §8|§7 Du hast es geschaft den Bürgermeister zu helfen jetzt musst nur noch die Täter finden und sie sind nicht mehr hier du musst zu Paradise Island ich weiß das sie da sind weil mein Freund Sparow da lebt wir können aber mit einen Funkgerät zusammen bleiben wenn du auf diese Mission gehst. Du musst zum Fähren Verkäufer am großen Hafen da kannst du dir ein Ticket zu Paradise Island kaufen. Achja nimm dir deine Belohnung hier aus der Kiste!"),
    SPAROW(10, "Treffen", new String[]{"§eDu hast dich mit Sparow getroffen"}, "sparow", LobbyWorld.PARADISE_ISLAND, "§8[§7§l!§8] §cNPC §8» §fSparow §8|§7 Hi du musst %%player%% sein. Edward hat mir viel über dich erzählt aber Marvin wurde bereits von der Polizei gefangen genommen. Achja du weißt garnicht wer Marvin ist oder? Oke pass auf wo Marvin rufi infiziert hat ist er danach sofort nach Paradise Island gereist er wollte sich ein Hotel mieten aber leider ist Mrs Merkel und Mr Trump auf der Insel zu besuch. Deswegen konnter er sich kein Zimmer mieten und dann habe kurze Zeit später in der Post gelesen das Marvin gefangen wurde ich glaub die Hotel Rezeption weiß mehr darüber und noch etwas du musst Edward bescheid sagen. Am besten machst du das mit deinem Funkgerät in deinem Rucksack einfach draufklicken hat er gesagt!"),
    RECEPTION(11, "Treffen", new String[]{"§eDu hast dich mit der Rezeption unterhalten"}, "reception", LobbyWorld.PARADISE_ISLAND, "§8[§7§l!§8] §cNPC §8» §fRezepzion §8|§7 Ohh du musst der sein der Marvin töten wollte oder? Nur zu Info er wurde bereits gefangen er wollte sich hier ein Hotel-Ticket holen hat es aber nicht geschafft, weil 2 Prominente hier Urlaub machen mehr weiß ich aber auch nicht außer das Marvin in einer Übergangszelle in der Polizei sitzt."),
    POLICE(12, "Police", new String[]{"§eDu hast dich mit der Polizei unterhalten"}, "police", LobbyWorld.PARADISE_ISLAND, "§8[§7§l!§8] §cNPC §8» §fPolizei §8|§7 Schönen Guten Tag sie wollen bestimmt mit Marvin reden oder eher gesagt unser bester Fang auf Paradise Island. Sie können einfach gerade aus durchgehen er ist in seiner Zelle."),
    MARVIN(13, "Marvin", new String[]{"§eDu hast dich mit den Mörder Marvin unterhalten"}, "marvin", LobbyWorld.PARADISE_ISLAND, "§8[§7§l!§8] §cNPC §8» §fMarvin §8|§7 Tschhh...... §4PENG"),
    MARVIN_KILL(14, "Marvin Höhle", new String[]{"§eDu wurdest entführt"}, "marvin-welcome", LobbyWorld.CAVE, "§8[§7§l!§8] §cNPC §8» §fMarvin §8|§7 Ich habe gehört du wolltest mich umbringen oder eher gesagt fangen, weil du gekommen bist hat der Wächter die falsche Tür geöffnet und somit konnte ich alle überwältigen. Komm mal aus deiner Zelle raus ich warte hinten auf dich. Hahaha"),
    MARVIN_WELCOME(15, "Marvin Höhle", new String[]{"§eDu wurdest ausgesetzt"}, "marvin-kill", LobbyWorld.CAVE, "§8[§7§l!§8] §cNPC §8» §fMarvin §8|§7 Tschhh §cPUFF"),
    SPAROW_DESTROYED_ISLE(16, "Sparow", new String[]{"§eDu redest mit dem kranken Sparow"}, "sparow", LobbyWorld.DESTROYED_PARADISE_ISLAND, "§8[§7§l!§8] §cNPC §8» §fSparow §8|§7 %%player%% wo warst du wir haben dich überall gesucht wurdest du entführt? Wir haben uns große Sorgen gemacht Edward konnte dich nicht mehr über deinem GPS Sdender tracken. Edward hat gesagt du sollst schnell wieder zurück zu One Island reisen. Er hat irgendetwas merkwürdiges entdeckt. Ich verabschiede mich jetzt erstmal von dir deswegen lebe wohl und passe auf dich auf!"),

    //TEIL |||

    EDWARD_LABOR_START(17, "Der Start", new String[]{"§eDer Start ins Labor"}, "edward-labor", LobbyWorld.ONE_ISLAND, "§8[§7§l!§8] §cNPC §8» §fEdward §8|§7 Ahh da bist du wieder ich habe etwas sehr großes unter OneIsland entdeckt das muss du dir ansehen");


    private final int id;
    private final String name, npcName, message;
    private final LobbyWorld world;
    private final String[] description;

    StoryProgress(int id, String name, String[] description, String npcName, LobbyWorld world, String message) {
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

    public static StoryProgress getProgressByID(int id) {
        for (StoryProgress storyProgress : values()) {
            if (storyProgress.getId() == id) {
                return storyProgress;
            }
        }

        return null;
    }

}
