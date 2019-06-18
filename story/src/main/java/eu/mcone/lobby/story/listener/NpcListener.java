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
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.api.enums.Item;
import eu.mcone.lobby.api.enums.Progress;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.items.inventory.smuggler.SmugglerInventory;
import eu.mcone.lobby.story.inventory.searcher.SearcherInventory;
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
            LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p.getUniqueId());
            CoreWorld w = CoreSystem.getInstance().getWorldManager().getWorld(npc.getData().getLocation().getWorld());

            if (w.equals(LobbyWorld.ONE_ISLAND.getWorld())) {
                switch (npc.getData().getName()) {
                    case "researcher": {
                        new SearcherInventory(p);
                        break;
                    }
                    case "robert": {
                        if (lp.getProgressId() >= Progress.SALIA.getId() && !lp.hasItem(Item.MAGICWAND)) {
                            lp.addItem(Item.MAGICWAND);
                        }
                        break;
                    }
                    case "duty": {
                        if (lp.getProgressId() >= Progress.DUTY.getId() && !lp.hasItem(Item.PASS)) {
                            lp.addItem(Item.PASS);
                        }
                        break;
                    }
                    case "rufi-infected": {
                        if (lp.getProgressId() == Progress.EDWARD_CITYHALL.getId()) {
                            if (p.getItemInHand().equals(Item.MAGICDRINK.getItemStack())) {
                                p.getInventory().remove(p.getItemInHand());
                                lp.removeItem(Item.MAGICDRINK);
                                lp.setProgress(Progress.INFECTION);

                                p.spigot().playEffect(npc.getData().getLocation().bukkit(), Effect.INSTANT_SPELL, 1, 1, 1, 1, 1, 5, 1000, 1);

                                Bukkit.getScheduler().runTaskLaterAsynchronously(LobbyPlugin.getInstance(), () -> {
                                    npc.setSkin(RUFI_HEADLED_SKIN, p);
                                    npc.changeDisplayname(RUFI_HEADLED_DISPLAY_NAME, p);
                                }, 2);
                                p.sendMessage("§8[§7§l!§8] §cNPC §8» §fHeer Rufi §8|§7 Danke Danke du hast mich gerretet du kannst dir die belohnung in der Mitte von der Insel holen da ist eine Fackel und da neben ein Knopf den musst du betätigen dann hast du deine Belohnung");
                            } else {
                                p.sendMessage("§8[§7§l!§8] §cNPC §8» §fHeer Rufi §8|§7 Das hilft mir nicht! Hast du eventuell einen Heiltrank, den du mir geben kannst?");
                            }
                        } else if (lp.getProgressId() == Progress.INFECTION.getId()) {
                            p.sendMessage("§8[§7§l!§8] §cNPC §8» §fHeer Rufi §8|§7 Hol dir deine belohnung ab!");
                        }
                        return;
                    }
                    case "smuggler": {
                        if (lp.getProgressId() > Progress.DUTY.getId()) {
                            new SmugglerInventory(p);
                        } else {
                            p.sendMessage("§8[§7§l!§8] §cNPC §8» §fSchmuggler §8|§7 Wer bist du ? Kenn ich dich.");
                        }
                        break;
                    }
                    case "merchant-boatticket": {
                        new CustomerInventory(p);
                        break;
                    }
                    case "captain": {
                        if (p.getItemInHand().equals(Item.BOAT_PASS.getItemStack())) {
                            if (lp.getProgressId() > Progress.MARVIN_KILL.getId()) {
                                lp.removeItem(Item.BOAT_PASS);
                                p.getInventory().remove(p.getItemInHand());
                                LobbyWorld.DESTROYED_PARADISE_ISLAND.getWorld().teleportSilently(p, "spawn");
                                p.sendMessage("§8[§7§l!§8] §cNPC §8» §fKapitän §8|§7 Du bist nun im zerstörten Paradise Island");
                            } else {
                                lp.removeItem(Item.BOAT_PASS);
                                p.getInventory().remove(p.getItemInHand());

                                LobbyWorld.PARADISE_ISLAND.getWorld().teleportSilently(p, "spawn");
                                p.sendMessage("§8[§7§l!§8] §cNPC §8» §fKapitän §8|§7 Du bist nun in Paradise Island");

                                if (lp.getProgressId() == 9) {
                                    p.sendMessage("§8[§7§l!§8] §fServer §8» §fFunkgerät §8|§7 Bringg Bringgg  Hallo " + p.getName() + "§7 ich sehe das du auf der Insel bist und wollte so mit fragen ob du Sparow gefunden hab? Ich schreib dir einfach in ein paar minuten zurück.");
                                }
                            }
                        } else {
                            p.sendMessage("§8[§7§l!§8] §cNPC §8» §fKapitän §8|§7 Ich brauche das Ticket du Landratte");
                        }
                        return;
                    }
                    case "edward-cave": {
                        if (lp.getProgressId() == Progress.INFECTION.getId()) {
                            if (!lp.hasItem(Item.RADIO_SET1)) {
                                lp.addItem(Item.RADIO_SET1);
                            }
                            if (!lp.hasItem(Item.GPS)) {
                                lp.addItem(Item.GPS);
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
            } else if (w.equals(LobbyWorld.PARADISE_ISLAND.getWorld())) {
                switch (npc.getData().getName()) {
                    case "captain": {
                        LobbyWorld.ONE_ISLAND.getWorld().teleportSilently(p, "spawn");
                        p.sendMessage("§8[§7§l!§8] §cNPC §8» §fKapitän §8|§7 Du bist nun in One Island");
                        break;
                    }
                    case "marvin": {
                        if (lp.getProgressId() + 1 == Progress.MARVIN.getId() || lp.getProgressId() == Progress.MARVIN.getId()) {
                            LobbyWorld.CAVE.getWorld().teleportSilently(p, "spawn");

                            lp.removeItem(Item.RADIO_SET1);
                            lp.removeItem(Item.GPS);
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
                        if (lp.getProgressId() > Progress.MARVIN_KILL.getId()) {
                            if (!lp.hasItem(Item.RADIO_SET_2)) {
                                lp.addItem(Item.RADIO_SET_2);
                                p.sendMessage("sd");
                            }
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
