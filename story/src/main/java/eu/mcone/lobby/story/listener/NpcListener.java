/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.story.listener;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.event.npc.NpcInteractEvent;
import eu.mcone.coresystem.api.bukkit.npc.NPC;
import eu.mcone.coresystem.api.bukkit.npc.entity.PlayerNpc;
import eu.mcone.coresystem.api.bukkit.world.CoreWorld;
import eu.mcone.coresystem.api.core.labymod.LabyModEmote;
import eu.mcone.coresystem.api.core.player.SkinInfo;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.api.enums.LobbyItem;
import eu.mcone.lobby.api.enums.StoryProgress;
import eu.mcone.lobby.api.enums.TraderProgress;
import eu.mcone.lobby.api.enums.TutorialStory;
import eu.mcone.lobby.api.enums.bank.BankRobberySmallProgress;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.items.inventory.smuggler.SmugglerInventory;
import eu.mcone.lobby.story.inventory.john.JohnBankRobberyInventory;
import eu.mcone.lobby.story.inventory.searcher.SearcherInventory;
import eu.mcone.lobby.story.inventory.story.CaptainInventory;
import eu.mcone.lobby.story.inventory.story.CorpseInventory;
import eu.mcone.lobby.story.inventory.story.CustomerInventory;
import net.minecraft.server.v1_8_R3.PacketPlayInUseEntity;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class NpcListener implements Listener {

    static final String RUFI_HEADLED_DISPLAY_NAME = "§erufi";
    static final SkinInfo RUFI_HEADLED_SKIN = CoreSystem.getInstance().getPlayerUtils().getSkinInfo("rufi");

    @EventHandler
    public void on(NpcInteractEvent e) {
        if (e.getNpc().getData().getType().equals(EntityType.PLAYER) && e.getAction().equals(PacketPlayInUseEntity.EnumEntityUseAction.INTERACT)) {
            Player p = e.getPlayer();
            PlayerNpc npc = (PlayerNpc) e.getNpc();
            LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);
            CoreWorld w = CoreSystem.getInstance().getWorldManager().getWorld(npc.getData().getLocation().getWorld());

            if (w.equals(LobbyWorld.ONE_ISLAND.getWorld())) {
                switch (npc.getData().getName()) {
                    case "roger": {
                        p.sendMessage("§8[§7§l!§8] §cNPC §8» §fRoger §8|§7 Ouh ähm hier ist betreten verboten und ich wohne nicht hier du bist warscheinlich " + p.getName() + " wusste ichs doch du willst dieses Hacker Gerät abholen hat John mir gesagt ich habe es irgendwo versteckt ich war aber nicht besoffen das ist klar!");
                        break;
                    }
                    case "researcher": {
                        new SearcherInventory(p);
                        break;
                    }
                    case "Leiche": {
                        new CorpseInventory(p);
                    }
                    case "robert": {
                        npc.playLabymodEmote(LabyModEmote.SALUTE, p);
                        if (lp.getProgressId() >= StoryProgress.SALIA.getId() && !lp.hasLobbyItem(LobbyItem.MAGICWAND)) {
                            if (!lp.hasLobbyItem(LobbyItem.MAGICWAND)) {
                                lp.addLobbyItem(LobbyItem.MAGICWAND);
                            }
                        }
                        break;
                    }
                    case "JohnEnd": {
                        break;
                    }
                    case "cutter": {
                        npc.playLabymodEmote(LabyModEmote.BOW_DOWN, p);
                        if (lp.getBankprogressId() == BankRobberySmallProgress.CUTTER.getId()) {
                            if (lp.hasLobbyItem(LobbyItem.WHITE_WOOL)) {

                                lp.removeLobbyItem(LobbyItem.WHITE_WOOL);
                                p.sendMessage("§8[§7§l!§8] §cNPC §8» §fJoguloa §8|§7 Perfekt die Wolle war es. Hier bitte das bestellte Outfit!");
                                lp.setBankProgress(BankRobberySmallProgress.SWORD);
                                p.sendMessage("§8[§7§l!§8] §cKnopf im Ohr §8» §fJohn§8 |§7 Ok du hast das Packet komm zurück ins Büro damit wir die Letzte Mission besprechen können!");
                                lp.addLobbyItem(LobbyItem.BANK_OUTFIT);
                            } else {
                                p.sendMessage("§8[§7§l!§8] §cNPC §8» §fJoguloa §8|§7 Ah du musst " + p.getName() + " sein ich konnte deine Bestellung leider nicht bearbeiten, weil ich keine Wolle da hab du kannst sie aber besorgen sie liegt warscheinlich noch in einer Kiste im Boot!");
                            }
                            return;
                        }
                        p.sendMessage("§8[§7§l!§8] §cNPC §8» §fJoguloa §8|§7 Ich habe leider momentan viel zu tun komm später wieder!");
                    }

                    case "duty": {
                        if (!lp.hasLobbyItem(LobbyItem.PASS)) {
                            lp.addLobbyItem(LobbyItem.PASS);
                        }
                        break;
                    }
                    case "rufi-infected": {
                        if (lp.getProgressId() == StoryProgress.EDWARD_CITYHALL.getId()) {
                            if (p.getItemInHand().equals(LobbyItem.MAGICDRINK.getItemStack())) {
                                p.getInventory().remove(p.getItemInHand());
                                lp.removeLobbyItem(LobbyItem.MAGICDRINK);
                                lp.setProgress(StoryProgress.INFECTION);

                                StoryProgress.EDWARD_CITYHALL.getNpc().toggleVisibility(p, false);
                                StoryProgress.ONEHIT_SWORD.getNpc().toggleVisibility(p, true);

                                p.spigot().playEffect(npc.getData().getLocation().bukkit(), Effect.INSTANT_SPELL, 1, 1, 1, 1, 1, 5, 1000, 1);

                                Bukkit.getScheduler().runTaskLaterAsynchronously(LobbyPlugin.getInstance(), () -> {
                                    npc.setSkin(RUFI_HEADLED_SKIN, p);
                                    npc.changeDisplayname(RUFI_HEADLED_DISPLAY_NAME, p);
                                }, 2);
                                p.sendMessage("§8[§7§l!§8] §cNPC §8» §fRufi §8|§7 Danke Danke du hast mich gerretet du kannst dir die Belohnung in der Mitte von One-Island abholen, springe in ein großes Loch!");
                            } else {
                                p.sendMessage("§8[§7§l!§8] §cNPC §8» §fRufi §8|§7 Das hilft mir nicht! Hast du eventuell einen Heiltrank, den du mir geben kannst?");
                            }
                        } else if (lp.getProgressId() == StoryProgress.INFECTION.getId()) {
                            p.sendMessage("§8[§7§l!§8] §cNPC §8» §fRufi §8|§7 Hol dir deine Belohnung in der Mitte von One-Island ab!");
                        }
                        return;
                    }
                    case "edward-cityhall": {
                        if (!lp.hasLobbyItem(LobbyItem.MAGICDRINK)) {
                            p.sendMessage("§8[§7§l!§8] §cNPC §8» §fEdward §8|§7 Du hast den Zauber Trank nicht dabei kaufe ihn bei der Zauberin Mandalai und komme dann wieder zurück!");
                            return;
                        }
                        break;
                    }
                    case "smuggler":
                    case "smuggler2": {
                        if (lp.hasLobbyItem(LobbyItem.PASS) || lp.getBankprogressId() == BankRobberySmallProgress.SMUGGLER.getId()) {
                            new SmugglerInventory(p);
                        } else {
                            p.sendMessage("§8[§7§l!§8] §cNPC §8» §fSchmuggler §8|§7 Ich handel nur mit Leuten die ich kenne hol dir ein Ausweis!");
                        }
                        break;
                    }
                    case "merchant-boatticket": {
                        if (!LobbyPlugin.getInstance().getOneHitManager().isFighting(p)) {
                            new CustomerInventory(p);
                        }
                        break;
                    }
                    case "captain": {
                        if (lp.hasLobbyItem(LobbyItem.BOAT_PASS)) {
                            if (p.getItemInHand().equals(LobbyItem.BOAT_PASS.getItemStack())) {
                                new CaptainInventory(p);
                                return;
                            } else {
                                p.sendMessage("§8[§7§l!§8] §cNPC §8» §fKapitän §8|§7 Nimm das Ticket in die Hand, du Landratte!");
                            }
                        } else if (!p.getItemInHand().equals(LobbyItem.BOAT_PASS.getItemStack())) {
                            p.sendMessage("§8[§7§l!§8] §cNPC §8» §fKapitän §8|§7 Ich brauche das Ticket du Fischgesicht, kaufe es dir beim Verkäufer!");
                        } else {
                            p.sendMessage("§8[§7§l!§8] §cNPC §8» §fKapitän §8|§7 Nimm das Ticket in die Hand!");
                        }
                        break;
                    }
                    case "edward-cave": {
                        if (lp.getProgressId() == StoryProgress.INFECTION.getId()) {

                            CoreSystem.getInstance().createTitle()
                                    .title("§3§lDAS WAR DIE MCONE STORY")
                                    .subTitle("§f§lTEIL 1")
                                    .stay(3)
                                    .fadeIn(1)
                                    .fadeOut(1)
                                    .send(p);

                            Bukkit.getScheduler().runTaskLater(LobbyPlugin.getInstance(), () ->
                                    CoreSystem.getInstance().createTitle()
                                            .title("§3§lTEIL 2")
                                            .subTitle("§eIN ENTWICKLUNG")
                                            .stay(2)
                                            .fadeIn(1)
                                            .fadeOut(1)
                                            .send(p), 40L);


                            Bukkit.getScheduler().runTaskLater(LobbyPlugin.getInstance(), () -> {
                                CoreSystem.getInstance().createActionBar().message("§3Regie und Entwickelt von Marvio").send(p);
                            }, 40L);


                            p.sendMessage("§fDas war das erste Kapitel der MCONE Story das 2 Kapitel ist bereits in Planung und auch schon in Entwicklung");


                            //TODO CHAPTER 2 TEIL 2
                            lp.setProgress(StoryProgress.ONEHIT_SWORD);

                            if (!lp.hasLobbyItem(LobbyItem.RADIO_SET1)) {
                                lp.addLobbyItem(LobbyItem.RADIO_SET1);
                            }
                            if (!lp.hasLobbyItem(LobbyItem.GPS)) {
                                lp.addLobbyItem(LobbyItem.GPS);
                            }
                        }

                        break;
                    }
                    case "edward-welcome": {
                        if (lp.getProgressId() < 1) {
                            p.playEffect(p.getLocation(), Effect.EXPLOSION_HUGE, 10);
                            p.playEffect(p.getLocation(), Effect.EXPLOSION_LARGE, 10);

                            LobbyWorld.ONE_ISLAND.getWorld().getHologram("story-welcome").toggleVisibility(p, false);
                            CoreSystem.getInstance().createTitle()
                                    .title("§3§lMCONE PRÄSENTIRT")
                                    .subTitle("§f§lDIE STORY VON ONE ISLAND")
                                    .stay(5)
                                    .fadeIn(1)
                                    .fadeOut(1)
                                    .send(p);
                        }

                        break;
                    }
                    case "frank6": {
                        if (!lp.hasLobbyItem(LobbyItem.COMPASS)) {
                            lp.addLobbyItem(LobbyItem.COMPASS);
                        }
                    }
                }
            } else if (w.equals(LobbyWorld.PARADISE_ISLAND.getWorld())) {
                switch (npc.getData().getName()) {
                    case "captain": {
                        LobbyWorld.ONE_ISLAND.getWorld().teleportSilently(p, "spawn");
                        p.sendMessage("§8[§7§l!§8] §cNPC §8» §fKapitän §8|§7 Du bist nun in One Island");
                        break;
                    }
                    case "marvin": {
                        if (lp.getProgressId() + 1 == StoryProgress.MARVIN.getId() || lp.getProgressId() == StoryProgress.MARVIN.getId()) {
                            LobbyWorld.CAVE.getWorld().teleportSilently(p, "spawn");
                            p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 150, 2));
                            p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 2));

                            lp.removeLobbyItem(LobbyItem.RADIO_SET1);
                            lp.removeLobbyItem(LobbyItem.GPS);
                        }
                        break;
                    }
                }
            } else if (w.equals(LobbyWorld.DESTROYED_PARADISE_ISLAND.getWorld())) {
                switch (npc.getData().getName()) {
                    case "captain": {
                        LobbyWorld.ONE_ISLAND.getWorld().teleportSilently(p, "spawn");
                        p.sendMessage("§8[§7§l!§8] §cNPC §8» §fKapitän §8|§7 Du bist nun in One Island");
                        break;
                    }
                    case "sparow": {
                        if (lp.getProgressId() == StoryProgress.MARVIN_WELCOME.getId()) {
                            if (!lp.hasLobbyItem(LobbyItem.RADIO_SET_2)) {
                                lp.addLobbyItem(LobbyItem.RADIO_SET_2);
                            }

                            CoreSystem.getInstance().createTitle()
                                    .title("§3§lDAS WAR DIE MCONE STORY")
                                    .subTitle("§f§lTEIL 2")
                                    .stay(3)
                                    .fadeIn(1)
                                    .fadeOut(1)
                                    .send(p);

                            Bukkit.getScheduler().runTaskLater(LobbyPlugin.getInstance(), () ->
                                    CoreSystem.getInstance().createTitle()
                                            .title("§3§lTEIL 2")
                                            .subTitle("§eIN ENTWICKLUNG")
                                            .stay(2)
                                            .fadeIn(1)
                                            .fadeOut(1)
                                            .send(p), 20L);


                            Bukkit.getScheduler().runTaskLaterAsynchronously(LobbyPlugin.getInstance(), () -> {
                                CoreSystem.getInstance().createActionBar().message("§3Regie und Entwickelt von Marvio").send(p);
                                p.sendMessage("§fDas war das 2 Kapitel der MCONE Story das 3 Kapitel ist bereits in Planung!");
                            }, 20L);
                        }
                        break;
                    }


                }
            } else if (w.equals(LobbyWorld.CAVE.getWorld())) {
                if ("marvin-kill".equals(npc.getData().getName())) {
                    LobbyWorld.DESTROYED_PARADISE_ISLAND.getWorld().teleportSilently(p, "spawn");
                    p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 180, 2));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 2));
                }
            } else if (w.equals(LobbyWorld.OFFICE.getWorld())) {
                switch (npc.getData().getName()) {
                    case "John1":
                    case "John2":
                    case "John3": {
                        if (lp.hasLobbyItem(LobbyItem.OFFICE_CARD_BRONZE) || lp.hasLobbyItem(LobbyItem.OFFICE_CARD_SILVER) || lp.hasLobbyItem(LobbyItem.OFFICE_CARD_GOLD)) {
                            if (lp.getBankprogressId() >= BankRobberySmallProgress.SMUGGLER.getId()) {
                                if (lp.getBankprogressId() != BankRobberySmallProgress.BANK_ROBBERY_END.getId()) {
                                    new JohnBankRobberyInventory(p);
                                } else {
                                    //TODO CENTRAL ROBBERY
                                    /*
                                    if (lp.getCentralbankprogressId() >= BankProgress.SPY.getId()) {
                                        if (lp.hasLobbyItem(LobbyItem.WORKER_FILE_1)) {
                                            if (lp.getCentralbankprogressId() == BankProgress.SPY.getId()) {
                                                lp.setCentralBankProgress(BankProgress.HACKER_TERMINAL);
                                            }
                                        }
                                        new JohnBankRobberyCentralBank(p);
                                    } else {
                                        p.sendMessage("§8[§7§l!§8] §cNPC §8» §fJohn §8|§7 Hallo " + p.getName() + " da bist du ja wieder du dachtest warscheinlich das ich nach diesen kleinen Banküberfall weg bin, oder? Das war nur ein kleiner Test ich wollte nur gucken wie du dich so anstellst. Jetzt rauben wir die OneIsland-Central Bank aus!");
                                        lp.setCentralBankProgress(BankProgress.SPY);
                                        lp.addLobbyItem(LobbyItem.BUTTON);
                                        new JohnBankRobberyCentralBank(p);
                                    }
                                     */
                                    p.sendMessage("§8[§7§l!§8] §cNPC §8» §fJohn §8|§7 Ich bleibe hier, hahah...");

                                }
                            } else {
                                p.sendMessage("§8[§7§l!§8] §cNPC §8» §fJohn §8|§7 Hallo " + p.getName() + " schönes Büro aber leider gehört es bis jetzt noch mir aber du kannst etwas für mich erledigen wo du das Büro und ein paar Coins bekommst das klingt doch gut, oder? Ich stecke dir ein Knopf ins Ohr damit wir uns verständigen können!");
                                lp.setBankProgress(BankRobberySmallProgress.SMUGGLER);
                                new JohnBankRobberyInventory(p);
                                lp.addLobbyItem(LobbyItem.BUTTON);
                            }

                        } else {
                            p.sendMessage("§8[§7§l!§8] §cNPC §8» §fJohn §8|§7 Ich rede nicht mit dir!");
                        }
                        break;
                    }
                }
            }
            for (StoryProgress storyProgress : StoryProgress.values()) {
                NPC progressNpc = storyProgress.getWorld().getWorld().getNPC(storyProgress.getNpcName());

                if (e.getNpc().equals(progressNpc)) {
                    p.getWorld().playEffect(e.getNpc().getData().getLocation().bukkit(), Effect.LAVA_POP, 100);
                    npc.playLabymodEmote(LabyModEmote.HELLO, p);

                    if (storyProgress.getId() >= lp.getProgressId()) {
                        if (storyProgress.getId() <= lp.getProgressId() + 1) {
                            // TODO REMOVE THE IF WHEN CHAPTER 2 TEIL 2 OPEN!
                            if (!lp.hasLobbyItem(LobbyItem.RADIO_SET1)) {
                                p.sendMessage("\n" + storyProgress.getMessage().replaceAll("%%player%%", p.getName()));

                                lp.setProgress(storyProgress);

                                if (storyProgress.getId() > 1) {
                                    StoryProgress.getProgressByID(storyProgress.getId() - 1).getNpc().toggleVisibility(p, false);
                                }

                                StoryProgress future = StoryProgress.getProgressByID(storyProgress.getId() + 1);
                                if (future != null) {
                                    future.getNpc().toggleVisibility(p, true);
                                }
                            } else {
                                p.sendMessage("§8§l[§7§l!§8§l] §fLobby §8 » §7Teil 2 in Entwicklung!");
                            }
                        } else {
                            p.sendMessage("§8§l[§7§l!§8§l] §fLobby §8 » §7Du bist noch nicht so weit!");
                        }
                    } else {
                        p.sendMessage("§8§l[§7§l!§8§l] §fLobby§8 » §7Das hast du schon gemacht!");
                    }
                    break;
                }
            }

            for (TutorialStory tutorialStory : TutorialStory.values()) {
                NPC tutorialProgressNpc = tutorialStory.getWorld().getWorld().getNPC(tutorialStory.getNpcName());

                if (e.getNpc().equals(tutorialProgressNpc)) {
                    p.getWorld().playEffect(e.getNpc().getData().getLocation().bukkit(), Effect.LAVA_POP, 100);
                    npc.playLabymodEmote(LabyModEmote.HELLO, p);

                    if (tutorialStory.getId() >= lp.getTutorialStoryProgressId()) {
                        if (tutorialStory.getId() <= lp.getTutorialStoryProgressId() + 1) {
                            p.sendMessage("\n" + tutorialStory.getMessage().replaceAll("%%player%%", p.getName()));

                            lp.setTutorialStoryProgress(tutorialStory);

                            if (tutorialStory.getId() > 1) {
                                TutorialStory.getTutorialStoryById(tutorialStory.getId() - 1).getNpc().toggleVisibility(p, false);
                            }

                            TutorialStory future = TutorialStory.getTutorialStoryById(tutorialStory.getId() + 1);
                            if (future != null) {
                                future.getNpc().toggleVisibility(p, true);
                            }
                        } else {
                            p.sendMessage("§8§l[§7§l!§8§l] §fLobby §8 » §7Du bist noch nicht so weit!");
                        }
                    } else {
                        p.sendMessage("§8§l[§7§l!§8§l] §fLobby§8 » §7Das hast du schon gemacht!");
                    }
                    break;
                }
            }
            for (TraderProgress traderProgress : TraderProgress.values()) {
                NPC tutorialProgressNpc = traderProgress.getWorld().getWorld().getNPC(traderProgress.getNpcName());

                if (e.getNpc().equals(tutorialProgressNpc)) {
                    p.getWorld().playEffect(e.getNpc().getData().getLocation().bukkit(), Effect.LAVA_POP, 100);
                    npc.playLabymodEmote(LabyModEmote.BUUUH, p);

                    if (traderProgress.getId() >= lp.getTraderStoryProgressID()) {
                        if (traderProgress.getId() <= lp.getTraderStoryProgressID() + 1) {

                            p.sendMessage("§8[§7§l!§8] §cNPC §8» §fJanoff §8|§7 Ich möchte gerade nicht mit dir reden");

                       /*     p.sendMessage("\n" + traderProgress.getMessage().replaceAll("%%player%%", p.getName()));
                            lp.setTraderStoryProgress(traderProgress);

                            if (traderProgress.getId() > 1) {
                             / TraderProgress.getTraderStoryById(traderProgress.getId() - 1).getNpc().toggleVisibility(p, false);
                            }


                            TraderProgress future = TraderProgress.getTraderStoryById(traderProgress.getId() + 1);
                            if (future != null) {
                                future.getNpc().toggleVisibility(p, true);
                            } */
                        } else {
                            p.sendMessage("§8§l[§7§l!§8§l] §fLobby §8 » §7Du bist noch nicht so weit!");
                        }
                    } else {
                        p.sendMessage("§8§l[§7§l!§8§l] §fLobby§8 » §7Das hast du schon gemacht!");
                    }
                    break;
                }
            }
        }
    }

}
