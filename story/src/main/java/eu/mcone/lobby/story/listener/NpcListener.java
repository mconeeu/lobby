/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.story.listener;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.event.NpcInteractEvent;
import eu.mcone.coresystem.api.bukkit.npc.entity.PlayerNpc;
import eu.mcone.coresystem.api.bukkit.world.CoreWorld;
import eu.mcone.coresystem.api.core.player.SkinInfo;
import eu.mcone.gamesystem.api.enums.Item;
import eu.mcone.gamesystem.api.game.player.GamePlayer;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.api.enums.BankProgress;
import eu.mcone.lobby.api.enums.Progress;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.items.inventory.smuggler.SmugglerInventory;
import eu.mcone.lobby.items.manager.OfficeManager;
import eu.mcone.lobby.story.inventory.john.JohnBankRobberyInventory;
import eu.mcone.lobby.story.inventory.searcher.SearcherInventory;
import eu.mcone.lobby.story.inventory.story.CustomerInventory;
import eu.mcone.lobby.story.inventory.story.CorpseInventory;
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
            LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p.getUniqueId());
            GamePlayer gamePlayer = LobbyPlugin.getInstance().getGamePlayer(p.getUniqueId());
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
                        if (lp.getProgressId() >= Progress.SALIA.getId() && !gamePlayer.hasItem(Item.MAGICWAND)) {
                            gamePlayer.addItem(Item.MAGICWAND);
                        }
                        break;
                    }
                    case "JohnEnd": {

                        LobbyWorld.ONE_ISLAND.getWorld().getNPC("JohnEnd").toggleVisibility(p, false);
                        JohnBankRobberyInventory.currentlyInBank = null;
                        OfficeManager.getOffice(p);
                        p.sendMessage("§8[§7§l!§8] §cNPC §8» §fJohn §8|§7 Wir haben es geschafft ich überlasse dir 25.000 Coins und ein kleines Geschenk im Rucksack");
                        lp.getCorePlayer().addCoins(25000);
                        gamePlayer.removeItem(Item.GOLD_BARDING);
                        gamePlayer.removeItem(Item.BANK_MAP);
                        gamePlayer.removeItem(Item.IRON_SWORD);
                        gamePlayer.removeItem(Item.BUTTON);
                        gamePlayer.addItem(Item.GOLD_NUGGET);
                        break;
                    }
                    case "cutter": {
                        if (lp.getBankprogressId() == BankProgress.CUTTER.getId()) {
                            if (gamePlayer.hasItem(Item.WHITE_WOOL)) {

                                gamePlayer.removeItem(Item.WHITE_WOOL);
                                p.sendMessage("§8[§7§l!§8] §cNPC §8» §fJoguloa §8|§7 Perfekt die Wolle war es. Hier bitte das bestellte Outfit!");
                                lp.setBankProgress(BankProgress.SWORD);
                                p.sendMessage("§8[§7§l!§8] §cKnopf im Ohr §8» §fJohn§8|§7 Ok du hast das Packet komm zurück ins Büro damit wir die Letzte Mission besprechen können!");

                            } else {
                                p.sendMessage("§8[§7§l!§8] §cNPC §8» §fJoguloa §8|§7 Ah du musst " + p.getName() + "ich konnte deine Bestellung leider nicht bearbeiten ,weil ich keine Wolle da hab du kannst sie aber doch besorgen sie liegt warscheinlich noch in einer Kiste im Boot!");
                            }

                        } else {
                            p.sendMessage("§8[§7§l!§8] §cNPC §8» §fJoguloa §8|§7 Ich habe leider moemtan viel zu viel zu tun komm später wieder");
                        }
                    }

                    case "duty": {
                        if (lp.getProgressId() >= Progress.DUTY.getId() && !gamePlayer.hasItem(Item.PASS)) {
                            gamePlayer.addItem(Item.PASS);
                        }
                        break;
                    }
                    case "rufi-infected": {
                        if (lp.getProgressId() == Progress.EDWARD_CITYHALL.getId()) {
                            if (p.getItemInHand().equals(Item.MAGICDRINK.getItemStack())) {
                                p.getInventory().remove(p.getItemInHand());
                                gamePlayer.removeItem(Item.MAGICDRINK);
                                lp.setProgress(Progress.INFECTION);

                                p.spigot().playEffect(npc.getData().getLocation().bukkit(), Effect.INSTANT_SPELL, 1, 1, 1, 1, 1, 5, 1000, 1);

                                Bukkit.getScheduler().runTaskLaterAsynchronously(LobbyPlugin.getInstance(), () -> {
                                    npc.setSkin(RUFI_HEADLED_SKIN, p);
                                    npc.changeDisplayname(RUFI_HEADLED_DISPLAY_NAME, p);
                                }, 2);
                                p.sendMessage("§8[§7§l!§8] §cNPC §8» §fHeer Rufi §8|§7 Danke Danke du hast mich gerretet du kannst dir die belohnung in der Mitte von One-Island abholen, springe in ein großes Loch!");
                            } else {
                                p.sendMessage("§8[§7§l!§8] §cNPC §8» §fHeer Rufi §8|§7 Das hilft mir nicht! Hast du eventuell einen Heiltrank, den du mir geben kannst?");
                            }
                        } else if (lp.getProgressId() == Progress.INFECTION.getId()) {
                            p.sendMessage("§8[§7§l!§8] §cNPC §8» §fHeer Rufi §8|§7 Hol dir deine Belohnung in der Mitte von One-Island ab!");
                        }
                        return;
                    }
                    case "smuggler": {
                        if (lp.getProgressId() > Progress.DUTY.getId() || lp.getBankprogressId() == BankProgress.SMUGGLER.getId()) {
                            new SmugglerInventory(p);
                        } else {
                            p.sendMessage("§8[§7§l!§8] §cNPC §8» §fSchmuggler §8|§7 Ich handel nur mit Leuten die ich kenne hol dir ein Ausweis!");
                        }
                        break;
                    }
                    case "merchant-boatticket": {
                        new CustomerInventory(p);
                        break;
                    }
                    case "captain": {
                        if (p.getItemInHand().equals(Item.BOAT_PASS.getItemStack())) {
                            //   if (lp.getProgressId() > Progress.MARVIN_KILL.getId()) {
                            gamePlayer.removeItem(Item.BOAT_PASS);
                            p.getInventory().remove(p.getItemInHand());
                            LobbyWorld.DESTROYED_PARADISE_ISLAND.getWorld().teleportSilently(p, "spawn");
                            p.sendMessage("§8[§7§l!§8] §cNPC §8» §fKapitän §8|§7 Du bist nun im zerstörten Paradise Island");
                        } else {
                            gamePlayer.removeItem(Item.BOAT_PASS);
                            p.getInventory().remove(p.getItemInHand());

                            LobbyWorld.PARADISE_ISLAND.getWorld().teleportSilently(p, "spawn");
                            p.sendMessage("§8[§7§l!§8] §cNPC §8» §fKapitän §8|§7 Du bist nun in Paradise Island");

                            if (lp.getProgressId() == 9) {
                                p.sendMessage("§8[§7§l!§8] §fServer §8» §fFunkgerät §8|§7 Bringg Bringgg  Hallo " + p.getName() + "§7 ich sehe das du auf der Insel bist und wollte so mit fragen ob du Sparow gefunden hab? Ich schreib dir einfach in ein paar minuten zurück.");
                            }
                        }
                     /*   } else {
                            p.sendMessage("§8[§7§l!§8] §cNPC §8» §fKapitän §8|§7 Ich brauche das Ticket du Landratte");
                        }
                        return;
                     */
                    }
                    case "edward-cave": {
                        if (lp.getProgressId() == Progress.INFECTION.getId()) {

                            CoreSystem.getInstance().createTitle()
                                    .title("§3§lDAS WAR DIE MCONE STORY")
                                    .subTitle("§f§lTEIL 1")
                                    .stay(5)
                                    .fadeIn(1)
                                    .fadeOut(1)
                                    .send(p);

                            Bukkit.getScheduler().runTaskLaterAsynchronously(LobbyPlugin.getInstance(), () ->
                                    CoreSystem.getInstance().createTitle()
                                            .title("§3§lTEIL 2 IN ENTWICKLUNG")
                                            .subTitle("§c§lENDE")
                                            .stay(5)
                                            .fadeIn(1)
                                            .fadeOut(1)
                                            .send(p), 140L);

                            lp.setProgress(Progress.ONEHIT_SWORD);

                            CoreSystem.getInstance().createActionBar().message("§3Regie und Entwickelt von DrMarv").send(p);

                            p.sendMessage("§fDas war Teil 1 der MCONE Story der 2 Teil ist bereits in Planung und auch schon in Entwicklung");

                            // TODO: CHAPTER 2
                            /*if (!gamePlayer.hasItem(Item.RADIO_SET1)) {
                                gamePlayer.addItem(Item.RADIO_SET1);
                            }
                            if (!gamePlayer.hasItem(Item.GPS)) {
                                gamePlayer.addItem(Item.GPS);
                            }*/
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
                 /*   case "marvin": {
                        if (lp.getProgressId() + 1 == Progress.MARVIN.getId() || lp.getProgressId() == Progress.MARVIN.getId()) {
                            LobbyWorld.CAVE.getWorld().teleportSilently(p, "spawn");

                            gamePlayer.removeItem(Item.RADIO_SET1);
                            gamePlayer.removeItem(Item.GPS);
                        }
                        break;
                    }
                */
                }
            } else if (w.equals(LobbyWorld.DESTROYED_PARADISE_ISLAND.getWorld())) {
                switch (npc.getData().getName()) {
                    case "captain": {
                        LobbyWorld.ONE_ISLAND.getWorld().teleportSilently(p, "spawn");
                        p.sendMessage("§8[§7§l!§8] §cNPC §8» §fKapitän §8|§7 Du bist nun in One Island");
                        break;
                    }
                  /*  case "sparow": {
                        if (lp.getProgressId() > Progress.MARVIN_KILL.getId()) {
                            if (!gamePlayer.hasItem(Item.RADIO_SET_2)) {
                                gamePlayer.addItem(Item.RADIO_SET_2);
                                p.sendMessage("sd");
                            }
                        }
                        break;
                    }
                */
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
                        if (lp.getBankprogressId() == BankProgress.SMUGGLER.getId()) {
                            new JohnBankRobberyInventory(p);
                        } else {
                            p.sendMessage("§8[§7§l!§8] §cNPC §8» §fJohn §8|§7 Hallo " + p.getName() + "schönes Büro aber leider gehört es bis jetzt noch mir aber du etwas für mich erledigen wo du das Büro und Coins bekommst das klingt doch gut, oder? Ich stecke dir ein Knopf ins Ohr damit wir uns verständigen können!");
                            lp.setBankProgress(BankProgress.SMUGGLER);
                            gamePlayer.addItem(Item.BUTTON);
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
                            lp.setProgress(progress);

                            if (progress.getId() > 1) {
                                Progress.getProgressByID(progress.getId() - 1).getNpc().toggleVisibility(p, false);
                            }

                            Progress future = Progress.getProgressByID(progress.getId() + 1);
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


        }
    }

}
