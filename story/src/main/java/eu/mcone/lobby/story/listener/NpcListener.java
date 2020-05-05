/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.story.listener;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.event.npc.NpcInteractEvent;
import eu.mcone.coresystem.api.bukkit.npc.entity.PlayerNpc;
import eu.mcone.coresystem.api.bukkit.world.CoreWorld;
import eu.mcone.coresystem.api.core.player.SkinInfo;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.api.enums.BankProgress;
import eu.mcone.lobby.api.enums.LobbyItem;
import eu.mcone.lobby.api.enums.Progress;
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
                    case "researcher": {
                        new SearcherInventory(p);
                        break;
                    }
                    case "Leiche": {
                        new CorpseInventory(p);
                    }
                    case "robert": {
                        if (lp.getProgressId() >= Progress.SALIA.getId() && !lp.hasLobbyItem(LobbyItem.MAGICWAND)) {
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
                        if (lp.getBankprogressId() == BankProgress.CUTTER.getId()) {
                            if (lp.hasLobbyItem(LobbyItem.WHITE_WOOL)) {

                                lp.removeLobbyItem(LobbyItem.WHITE_WOOL);
                                p.sendMessage("§8[§7§l!§8] §cNPC §8» §fJoguloa §8|§7 Perfekt die Wolle war es. Hier bitte das bestellte Outfit!");
                                lp.setBankProgress(BankProgress.SWORD);
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
                        if (lp.getProgressId() == Progress.EDWARD_CITYHALL.getId()) {
                            if (p.getItemInHand().equals(LobbyItem.MAGICDRINK.getItemStack())) {
                                p.getInventory().remove(p.getItemInHand());
                                lp.removeLobbyItem(LobbyItem.MAGICDRINK);
                                lp.setProgress(Progress.INFECTION);

                                Progress.EDWARD_CITYHALL.getNpc().toggleVisibility(p, false);
                                Progress.ONEHIT_SWORD.getNpc().toggleVisibility(p, true);

                                p.spigot().playEffect(npc.getData().getLocation().bukkit(), Effect.INSTANT_SPELL, 1, 1, 1, 1, 1, 5, 1000, 1);

                                Bukkit.getScheduler().runTaskLaterAsynchronously(LobbyPlugin.getInstance(), () -> {
                                    npc.setSkin(RUFI_HEADLED_SKIN, p);
                                    npc.changeDisplayname(RUFI_HEADLED_DISPLAY_NAME, p);
                                }, 2);
                                p.sendMessage("§8[§7§l!§8] §cNPC §8» §fRufi §8|§7 Danke Danke du hast mich gerretet du kannst dir die Belohnung in der Mitte von One-Island abholen, springe in ein großes Loch!");
                            } else {
                                p.sendMessage("§8[§7§l!§8] §cNPC §8» §fRufi §8|§7 Das hilft mir nicht! Hast du eventuell einen Heiltrank, den du mir geben kannst?");
                            }
                        } else if (lp.getProgressId() == Progress.INFECTION.getId()) {
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
                    case "smuggler": {
                        if (lp.hasLobbyItem(LobbyItem.PASS) || lp.getBankprogressId() == BankProgress.SMUGGLER.getId()) {
                            new SmugglerInventory(p);
                        } else {
                            p.sendMessage("§8[§7§l!§8] §cNPC §8» §fSchmugler §8|§7 Ich handel nur mit Leuten die ich kenne hol dir ein Ausweis!");
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
                        if (lp.getProgressId() == Progress.INFECTION.getId()) {

                            CoreSystem.getInstance().createTitle()
                                    .title("§3§lDAS WAR DIE MCONE STORY")
                                    .subTitle("§f§lTEIL 1")
                                    .stay(3)
                                    .fadeIn(1)
                                    .fadeOut(1)
                                    .send(p);

                            Bukkit.getScheduler().runTaskLaterAsynchronously(LobbyPlugin.getInstance(), () ->
                                    CoreSystem.getInstance().createTitle()
                                            .title("§3§lTEIL 2 IN ENTWICKLUNG")
                                            .subTitle(null)
                                            .stay(2)
                                            .fadeIn(1)
                                            .fadeOut(1)
                                            .send(p), 0L);


                            Bukkit.getScheduler().runTaskLaterAsynchronously(LobbyPlugin.getInstance(), () -> {
                                CoreSystem.getInstance().createActionBar().message("§3Regie und Entwickelt von DrMarv").send(p);
                            }, 160);


                            p.sendMessage("§fDas war Teil 1 der MCONE Story der 2 Teil ist bereits in Planung und auch schon in Entwicklung");


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
                }
            } else if (w.equals(LobbyWorld.PARADISE_ISLAND.getWorld())) {
                switch (npc.getData().getName()) {
                    case "captain": {
                        LobbyWorld.ONE_ISLAND.getWorld().teleportSilently(p, "spawn");
                        p.sendMessage("§8[§7§l!§8] §cNPC §8» §fKapitän §8|§7 Du bist nun in One Island");
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
                        if (lp.getProgressId() > Progress.MARVIN_KILL.getId()) {
                            if (!lp.hasLobbyItem(LobbyItem.RADIO_SET_2)) {
                                lp.addLobbyItem(LobbyItem.RADIO_SET_2);
                            }
                        }
                        break;
                    }

                    case "marvin": {
                        if (lp.getProgressId() + 1 == Progress.MARVIN.getId() || lp.getProgressId() == Progress.MARVIN.getId()) {
                            LobbyWorld.CAVE.getWorld().teleportSilently(p, "spawn");

                            lp.removeLobbyItem(LobbyItem.RADIO_SET1);
                            lp.removeLobbyItem(LobbyItem.GPS);
                        }
                        break;
                    }


                }
            } else if (w.equals(LobbyWorld.CAVE.getWorld())) {
                switch (npc.getData().getName()) {
                    case "marvin-kill": {
                        p.sendMessage("§8[§7§l!§8] §cNPC §8» §fMarvin §8|§e PENG");
                        Bukkit.getScheduler().runTaskLater(
                                LobbyPlugin.getInstance(),
                                () -> LobbyWorld.DESTROYED_PARADISE_ISLAND.getWorld().teleportSilently(p, "spawn"),
                                2 * 20
                        );
                        return;
                    }
                }
            } else if (w.equals(LobbyWorld.OFFICE.getWorld())) {
                switch (npc.getData().getName()) {
                    case "John1":
                    case "John2":
                    case "John3": {
                        if (lp.hasLobbyItem(LobbyItem.OFFICE_CARD_BRONZE) || lp.hasLobbyItem(LobbyItem.OFFICE_CARD_SILVER) || lp.hasLobbyItem(LobbyItem.OFFICE_CARD_GOLD)) {
                            if (lp.getBankprogressId() >= BankProgress.SMUGGLER.getId()) {
                                new JohnBankRobberyInventory(p);
                            } else {
                                p.sendMessage("§8[§7§l!§8] §cNPC §8» §fJohn §8|§7 Hallo " + p.getName() + " schönes Büro aber leider gehört es bis jetzt noch mir aber du kannst etwas für mich erledigen wo du das Büro und ein paar Coins bekommst das klingt doch gut, oder? Ich stecke dir ein Knopf ins Ohr damit wir uns verständigen können!");
                                lp.setBankProgress(BankProgress.SMUGGLER);
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

            for (Progress progress : Progress.values()) {
                if (e.getNpc().getData().getName().equals(progress.getNpcName())) {
                    p.getWorld().playEffect(e.getNpc().getData().getLocation().bukkit(), Effect.LAVA_POP, 100);

                    if (progress.getId() >= lp.getProgressId()) {
                        if (progress.getId() <= lp.getProgressId() + 1) {
                            p.sendMessage("\n" + progress.getMessage().replaceAll("%%player%%", p.getName()));

                            // TODO: folgende If Abfrage löschen wenn Teil 2 freigeschaltet werden soll!!
                            if (!progress.equals(Progress.ONEHIT_SWORD)) {
                                lp.setProgress(progress);

                                if (progress.getId() > 1) {
                                    Progress.getProgressByID(progress.getId() - 1).getNpc().toggleVisibility(p, false);
                                }

                                Progress future = Progress.getProgressByID(progress.getId() + 1);
                                if (future != null) {
                                    future.getNpc().toggleVisibility(p, true);
                                }
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


        }
    }

}
