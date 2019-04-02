/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.story.listener;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.event.NpcInteractEvent;
import eu.mcone.coresystem.api.bukkit.npc.NPC;
import eu.mcone.coresystem.api.core.exception.CoreException;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.api.enums.Item;
import eu.mcone.lobby.api.enums.Progress;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.items.inventory.smuggler.SmugglerInventory;
import eu.mcone.lobby.story.inventory.searcher.SearcherInventory;
import eu.mcone.lobby.story.inventory.story.CustomerInventory;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NpcInteract implements Listener {

    @EventHandler
    public void on(NpcInteractEvent e) {
        Player p = e.getPlayer();
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p.getUniqueId());
        NPC npc = e.getNpc();

        if (e.getAction().equals(NpcInteractEvent.Action.RIGHT_CLICK)) {
            if (e.getWorld().equals(LobbyPlugin.getInstance().getLobbyWorld(LobbyWorld.DIM_1))) {
                switch (npc.getData().getName()) {
                    case "researcher": {
                        new SearcherInventory(p);
                        break;
                    }
                    case "robert": {
                        if (lp.getProgressId() >= 4 && !lp.hasItem(Item.MAGICWAND)) {
                            lp.addItem(Item.MAGICWAND);
                        }
                        break;
                    }
                    case "zoll": {
                        if (lp.getProgressId() >= 2 && !lp.hasItem(Item.PASS)) {
                            lp.addItem(Item.PASS);
                        }
                        break;
                    }
                    case "rufi": {
                        if (lp.getProgressId() == 7) {
                            if (p.getItemInHand().equals(Item.MAGICDRINK.getItemStack())) {
                                p.getInventory().remove(p.getItemInHand());
                                lp.removeItem(Item.MAGICDRINK);
                                lp.setProgress(Progress.INFECTION);

                                p.spigot().playEffect(npc.getLocation(), Effect.INSTANT_SPELL, 1, 1, 1, 1, 1, 5, 1000, 1);

                                Bukkit.getScheduler().runTaskLaterAsynchronously(LobbyPlugin.getInstance(), () -> {
                                    try {
                                        npc.setSkin(CoreSystem.getInstance().getDatabaseSkinManager().getSkin("rufi"), p);
                                    } catch (CoreException e1) {
                                        e1.printStackTrace();
                                    }
                                }, 2);
                                p.sendMessage("§8[§7§l!§8] §cNPC §8» §fHeer Rufi §8|§7 Danke Danke du hast mich gerretet du kannst dir die belohnung in der Mitte von der Insel holen da ist eine Fackel und da neben ein Knopf den musst du betätigen dann hast du deine Belohnung");
                            } else {
                                p.sendMessage("§8[§7§l!§8] §cNPC §8» §fHeer Rufi §8|§7 Das hilft mir nicht! Hast du eventuell einen Heiltrank, den du mir geben kannst?");
                            }
                        } else if (lp.getProgressId() > 7) {
                            p.sendMessage("§8[§7§l!§8] §cNPC §8» §fHeer Rufi §8|§7 Hol dir deine belohnung ab!");
                        }
                        return;
                    }
                    case "smuggler": {
                        if (lp.getProgressId() > 2) {
                            new SmugglerInventory(p);
                        } else {
                            p.sendMessage("§8[§7§l!§8] §cNPC §8» §fSchmuggler §8|§7 Wer bist du ? Kenn ich dich.");
                        }
                        break;
                    }
                    case "captain3": {
                        new CustomerInventory(p);
                        break;
                    }
                    case "captain2": {
                        if (p.getItemInHand().equals(Item.BOAT_PASS.getItemStack())) {
                            if (lp.getProgressId() > 15) {
                                lp.removeItem(Item.BOAT_PASS);
                                p.getInventory().remove(p.getItemInHand());
                                LobbyPlugin.getInstance().getLobbyWorld(LobbyWorld.DIM_3).teleportSilently(p, "dim3-spawn");
                                p.sendMessage("§8[§7§l!§8] §cNPC §8» §fKapitän §8|§7 Du bist nun im zerstörten Paradise Island");
                            } else {
                                lp.removeItem(Item.BOAT_PASS);
                                p.getInventory().remove(p.getItemInHand());

                                LobbyPlugin.getInstance().getLobbyWorld(LobbyWorld.DIM_2).teleportSilently(p, "dim2-spawn");
                                p.sendMessage("§8[§7§l!§8] §cNPC §8» §fKapitän §8|§7 Du bist nun in Paradise Island");

                                if (lp.getProgressId() == 9) {
                                    p.sendMessage("§8[§7§l!§8] §fServer §8» §fFunkgerät §8|§7 Bringg Bringgg  Hallo " + p.getName() + "§7 ich sehe das du auf der Insel bist und wollte so mit fragen ob du Sparow gefunden hab? Ich schreib dir einfach in ein paar minuten zurück.");
                                }
                            }
                        } else {
                            p.sendMessage("§8[§7§l!§8] §cNPC §8» §fKapitän §8|§7 Ich brauche das Ticket du Landratte");
                        }
                        break;
                    }
                    case "edward3": {
                        if (lp.getProgressId() == 8) {
                            if (!lp.hasItem(Item.RADIO_SET1)) {
                                lp.addItem(Item.RADIO_SET1);
                            }
                            if (!lp.hasItem(Item.GPS)) {
                                lp.addItem(Item.GPS);
                            }
                        }
                        break;
                    }
                    case "edward4": {
                        if (lp.getProgressId() < 1) {
                            p.playEffect(p.getLocation(), Effect.EXPLOSION_HUGE, 10);
                            p.playEffect(p.getLocation(), Effect.EXPLOSION_LARGE, 10);

                            CoreSystem.getInstance().createTitle()
                                    .title("§3§lMCONE PRÄSENTIRT")
                                    .subTitle("§f§lDIE STORY VON ONE ISLAND")
                                    .stay(5)
                                    .fadeIn(1)
                                    .fadeOut(1)
                                    .send(p);
                            Bukkit.getScheduler().runTaskLaterAsynchronously(LobbyPlugin.getInstance(), () ->
                                    CoreSystem.getInstance().createTitle()
                                            .title("§e§lEDWARD WARTET")
                                            .subTitle("§c§lER STEHT VOR DER YACHT")
                                            .stay(5)
                                            .fadeIn(1)
                                            .fadeOut(1)
                                            .send(p), 140L);
                        }
                        break;
                    }
                }
            } else if (e.getWorld().equals(LobbyPlugin.getInstance().getLobbyWorld(LobbyWorld.DIM_2))) {
                switch (npc.getData().getName()) {
                    case "captain": {
                        LobbyPlugin.getInstance().getLobbyWorld(LobbyWorld.DIM_1).teleportSilently(p, "dim1-spawn");
                        p.sendMessage("§8[§7§l!§8] §cNPC §8» §fKapitän §8|§7 Du bist nun in One Island");
                        break;
                    }
                    case "Hotel-Men": {
                        if (lp.getProgressId() == 10) {
                            p.sendMessage("§8[§7§l!§8] §cNPC §8» §fRezepzion §8|§7 Hi bist du der Marvin töten wollte ? er wurde schon gefangen nur zu Info er wollte sich hier ein Ticket holen hat es nicht geschaft , weil Angela Merkel und Donald Trump da sind oke mehr kann ich dir auch nicht verraten außer das Marvin im Knast sitzt kannst ja mal hingehen.");
                            lp.setProgress(Progress.STAFF);
                        } else {
                            p.sendMessage("§8[§7§l!§8] §cNPC §8» §fRezepzion §8|§7 Hi ähm Neu ? leider kannst du kein Ticket bekommen hier ist Prominenz");

                        }
                        return;
                    }
                    case "marvin": {
                        if (lp.getProgressId() == 13) {
                            p.sendMessage("§8[§7§l!§8] §cNPC §8» §fMarvin §8|§7 Arhh Orhh Hahaha du bist der der mich töten wollte richtig §4Peng");
                            lp.setProgress(Progress.MARVIN_2);
                            LobbyPlugin.getInstance().getLobbyWorld(LobbyWorld.DIM_4).teleportSilently(p, "dim4-spawn");

                            lp.removeItem(Item.RADIO_SET1);
                            lp.removeItem(Item.GPS);
                        } else {
                            p.sendMessage("§8[§7§l!§8] §cNPC §8» §fMarvin §8|§7 Orhh Arh Was bist du den für ein klein Hirn!");
                        }
                        break;
                    }
                }
            } else if (e.getWorld().equals(LobbyPlugin.getInstance().getLobbyWorld(LobbyWorld.DIM_2))) {
                switch (npc.getData().getName()) {
                    case "descaptain": {
                        LobbyPlugin.getInstance().getLobbyWorld(LobbyWorld.DIM_1).teleportSilently(p, "dim1-spawn");
                        p.sendMessage("§8[§7§l!§8] §cNPC §8» §fKapitän §8|§7 Du bist nun in One Island");
                        break;
                    }
                    case "dessparow": {
                        if (lp.getProgressId() > 14) {
                            if (!lp.hasItem(Item.RADIO_SET_2)) {
                                lp.addItem(Item.RADIO_SET_2);
                                p.sendMessage("sd");
                            }
                        }
                        break;
                    }
                }
            } else if (e.getWorld().equals(LobbyPlugin.getInstance().getLobbyWorld(LobbyWorld.DIM_2))) {
                switch (npc.getData().getName()) {
                    case "marvin2dim4": {
                        LobbyPlugin.getInstance().getLobbyWorld(LobbyWorld.DIM_3).teleportSilently(p, "dim3-spawn");
                        break;
                    }
                }
            }

            for (Progress progress : Progress.values()) {
                if (e.getNpc().getData().getName().equals(progress.getNpcDatabaseName())) {
                    p.getWorld().playEffect(e.getNpc().getLocation(), Effect.LAVA_POP, 100);

                    if (progress.getId() >= lp.getProgressId()) {
                        if (progress.getId() <= lp.getProgressId() + 1) {
                            if (progress.getMessage() != null) {
                                p.sendMessage("\n" + progress.getMessage().replaceAll("%%player%%", p.getName()));
                                lp.setProgress(progress);
                            }
                        } else {
                            p.sendMessage("§8§l[§7§l!§8§l] §cSecrets§8 » §7Du bist noch nicht so weit!");
                        }
                    } else {
                        p.sendMessage("§8§l[§7§l!§8§l] §cSecrets§8 » §7Das hast du schon gemacht!");
                    }
                    break;
                }
            }
        }
    }

}
