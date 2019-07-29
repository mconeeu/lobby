/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.api.enums;

import eu.mcone.coresystem.api.bukkit.npc.entity.PlayerNpc;
import eu.mcone.lobby.api.LobbyWorld;
import lombok.Getter;

public enum Progress {

    WELCOME(1, "Begrüßen", new String[]{"§eEdward hat dir ein Insel Geschenk gegeben"}, "edward-welcome", LobbyWorld.ONE_ISLAND, "§8[§7§l!§8] §cNPC §8» §fEdward §8| §7Halloo Neuling wer bist du denn ? Hast du dein Ausweis ? Nein dann geh mal zum Zoll Neuling und dann kannst du uns helfen hast du das verstanden mehr erzähle ich dir wenn ich weiß wie du heißt und jetzt geh zum Zoll einfach gerade aus gehen und dann komm zu mir wenn du aus den Zoll Gebäude sofort rechts ähh oder links."),
    DUTY(2, "Pass", new String[]{"§eDer Zoll hat dir dein Personalausweis gegeben"}, "duty", LobbyWorld.ONE_ISLAND, "§8[§7§l!§8] §cNPC §8» §fZoll §8| §7Halloo Neuling Du brauchst ein Pass ich habe schon eine Vorlage von dein alten Land aber jetzt Herzlich willkommen auf One Island und hier ist dein Pass einfach noch einmal durch den Metall Detecktor gehen dann ist alles in Ordnung"),
    START(3, "Einführung", new String[]{"§eEinführung ins geschehen"}, "edward-start", LobbyWorld.ONE_ISLAND, "§8[§7§l!§8] §cNPC §8» §fEdward §8| §7Arhhh da bist du wieder ich habe auf dich gewartet. Du heißt also §f%%player%% Hallo .. §7So aber jetzt zum Thema unser geliebter Bürgermeister ist schwer infiziert! Leute haben ein Überfall auf ihn gemacht! Du musst ihn schnell Retten sonnst stirbt er! Du musst zu mein Freund Salia er hat mehr informationen! Er ist meistens am großen Strand."),
    SALIA(4, "Informationen", new String[]{"§eWichtige Informationen über", "§edenn Bürgermeister"}, "salia", LobbyWorld.ONE_ISLAND, "§8[§7§l!§8] §cNPC §8» §fSalia §8| §7Arrrrhh ... da bist du ja ich hab auf dich gewartet! Du willst helfen den Bürgermeister zurück zu infizieren. Der Bürgermeister heißt Rufi und es war mein bester Freund ,wenn du schaffst ihn zurück zu infiezieren wirst du eine Belohnung kriegen. Aber wo er sich momentan auffhält weiß ich auch nicht ich weiß nur das mein Freund Robert viel über Magie weiß. Am besten kaufst du da einen gegengift oder sowas! ach und noch was er wohnt in der nähe des Leuchturms!"),
    ROBERT(5, "Anfänger Magie", new String[]{"§eEinfache Magie Tricks!", "§eund du hast ein Zauberstab bekommen"}, "robert", LobbyWorld.ONE_ISLAND, "§8[§7§l!§8] §cNPC §8» §fRobert §8|§7 §ePeng pang pufff§7.  ...  Ahhh %%player%% bist du es! Ich bin der Magier Profi. §ePENGGG §7haha nein das war ein scherz ich hab was für dich ein §5Zauberstab §7ich lege dir ihn in deinen Rucksack. Damit wirst du bestimmnt zurecht kommen. Ich auf jedenfall nicht aber jetzt zum Thema ich hab von Edward gehört du möchstest rufi helfen aber hast nicht die beste Ausrüstung geh am besten zu unsere Hexe Mandalai die kann Zaubern die hat bestimmnt auch ein Trank für dich. Ach nochwas geh am besten mal in einer Höhle du must eigentlich immer Rote Knöpfe drücken um in sie reinzukommen dann steht da irgendwo ein Code denn wirst du brauchen!"),
    MANDALAI(6, "Hexe", new String[]{"§e Du hast den Zauber Trank gekauft"}, "mandalai", LobbyWorld.ONE_ISLAND, "§8[§7§l!§8] §cNPC §8» §fMandalai §8|§7 Hallo mein Freund du musst %%player%% sein. Uhh was hast du denn da in dein Rucksack ein §5Zauberstab §7der sieht echt cool aus aber du willst doch irgend etwas kaufen. nehm dir am besten dein Trank aus meinen Kelch du kannst mir am besten 20 Coins da lassen. Das passt schon. Geh am besten dann wieder nach Edward der hat mir gesagt der warte auf dich im Rathaus."),
    EDWARD_CITYHALL(7, "Lage Besprechung", new String[]{"§e Edward hat dir gesagt wie es weiter geht"}, "edward-cityhall", LobbyWorld.ONE_ISLAND, "§8[§7§l!§8] §cNPC §8» §fEdward §8|§7 Hallo %%player%% ahh da bist wieder und du hast den Trank dabei du musst jetzt schnell Rufi finden ich weiß selbst nicht wo er ist er wird aber bestimnnt irgend wo in einer Höhle festgehalten "),
    INFECTION(8, "Infizierung", new String[]{"§eDu hast Rufi zurück infiziert"}, "rufi-infected", LobbyWorld.ONE_ISLAND, "§8[§7§l!§8] §cNPC §8» §fHeer Rufi §8|§7 Danke Danke du hast mich gerretet du kannst dir die belohnung in der Mitte von der Insel abholen da ist ein sehr großes Loch spring da einfach runter und da kannst du dir dann deine Belohnung abholen"),

    ONEHIT_SWORD(9, "Belohnung", new String[]{"§eDu hast die Belohnung bekommen"}, "edward-cave", LobbyWorld.ONE_ISLAND, "§8[§7§l!§8] §cNPC §8» §fEdward §8|§7 Du hast es geschaft den Bürgermeister zu helfen jetzt musst nur noch die Täter finden und sie sind nicht mehr hier du musst zu Paradise Island ich weiß das sie da sind weil mein Freund Sparow da lebt wir können aber mit ein funkgerät zusammen bleiben wenn du auf diese Mission gehst. Du musst zum Fähren verkäufer am großen Hafen da kannst du dir ein Ticket zu Paradise Island kaufen. "),
    SPAROW(10, "Treffen", new String[]{"§eDu hast dich mit Edwards Freund getroffen"}, "sparow", LobbyWorld.PARADISE_ISLAND, "§8[§7§l!§8] §cNPC §8» §fSparow §8|§7 Hi du musst %%player%% Edward hat mir viel über dir erzählt aber leider kannst du uns nicht helfen die Polizei hat Marvin gefagen ach ja sicher du weißt garnicht wer Marvin ist oder ? oke ich erzähle es dir wo Marvin rufi infietiert hat ist er danach sofort hier nach Paradise Island ausgewandert er wollte sich ein Hotel mieten aber leider ist Angela Merkel und Donald Trump hier deswegen hat er kein Schlüssel bekommen und dann hab ich nur in der Post gelesen das Marvin gefangen wurde ich glaub die Hotel Rezeption weiß mehr darüber ach noch was du musst edward bescheid sagen am besten machst du das über dein Funkgerät er hat gesagt einfach drauf drücken oder so dann bist du verbundem"),
    RECEPTION(11, "Treffen", new String[]{"§eDu hast dich mit der Rezeption unterhalten"}, "reception", LobbyWorld.PARADISE_ISLAND, "§8[§7§l!§8] §cNPC §8» §fRezepzion §8|§7 Hi du bist der der Marvin töten wollte ? er wurde schon gefangen nur zu Info er wollte sich hier ein Ticket holen hat es nicht geschaft , weil Angela Merkel und Donald Trump da sind oke mehr kann ich dir auch nicht verraten außer das Marvin im Knast sitzt kannst ja mal hingehen."),
    POLICE(12, "Police", new String[]{"§eDu hast dich mit der Polizei unterhalten"}, "police", LobbyWorld.PARADISE_ISLAND, "§8[§7§l!§8] §cNPC §8» §fPolizei §8|§7 Schönen Guten Tag sie wollen bestimmnt mit Marvin reden oder unser bester fang sie können einfach gerade aus gehen er ist in seiner Zelle"),
    MARVIN(13, "Marvin", new String[]{"§eDu hast dich mit den Mörder Marvin unterhalten"}, "marvin", LobbyWorld.PARADISE_ISLAND, "§8[§7§l!§8] §cNPC §8» §fMarvin §8|§7 Arhh Orhh Hahaha du bist der der mich töten wollte richtig §4Peng"),
    MARVIN_KILL(14, "Marvin2", new String[]{"§eDu wurdest entführt"}, "marvin-welcome", LobbyWorld.CAVE, "§8[§7§l!§8] §cNPC §8» §fMarvin §8|§7 Ohh da bist du jetzt gefangen und jetzt bringe ich dich um weil du Rufi gerretes hast komm raus aus deiner Zelle ich warte auf dich da hinten"),
    SPAROW_DESTROYED_ISLE(15, "Sparow", new String[]{"§eDu redest mit Sparow"}, "sparow", LobbyWorld.DESTROYED_PARADISE_ISLAND, "§8[§7§l!§8] §cNPC §8» §fSparow §8|§7 Ohh %%player%% wo warst du wir haben dich überall gesucht wurdest du entführt ? Wir haben uns große sorgen gemacht Edward konnte dich nicht mehr tracken über dem GPS sender Edward hat gesagt du sollst");

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
