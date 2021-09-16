/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.games.listener;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.event.npc.NpcInteractEvent;
import eu.mcone.coresystem.api.bukkit.facades.Msg;
import eu.mcone.coresystem.api.bukkit.npc.entity.PlayerNpc;
import eu.mcone.coresystem.api.bukkit.world.CoreWorld;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.api.games.jumpnrun.JumpNRun;
import eu.mcone.lobby.api.player.HotbarItem;
import eu.mcone.lobby.games.inventory.CorpseInventory;
import eu.mcone.lobby.games.jumpnrun.JumpNRunLobbyGame;
import lombok.RequiredArgsConstructor;
import net.minecraft.server.v1_8_R3.PacketPlayInUseEntity;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

@RequiredArgsConstructor
public class JumpNRunListener implements Listener {

    private final JumpNRunLobbyGame game;

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && e.getClickedBlock() != null) {
                if (e.getClickedBlock().getType().equals(Material.WALL_SIGN)) {
                    Sign sign = (Sign) e.getClickedBlock().getState();

                    if (sign.getLine(0).equals("§7»§c Jump'n'Run")) {
                        for (JumpNRun jumpnrun : JumpNRun.values()) {
                            if (sign.getLine(1).equals(jumpnrun.getJumpandrunname())) {
                                LobbyWorld.ONE_ISLAND.getWorld().teleport(p, jumpnrun.getWarpLocation());
                                Msg.send(e.getPlayer(), "Du hast dich zum §f" + jumpnrun.getJumpandrunname() + " §7Jump and Run telepotiert");
                                return;
                            }
                        }

                        Msg.send(e.getPlayer(), "§4Das §c" + sign.getLine(1) + "§4 Jump and Run ist momentan in §oWartungen§4!");
                    }
                }
            }

            if (game.isPlaying(p)) {
                ItemStack i = e.getItem();
                if ((i == null) || (!i.hasItemMeta()) || (!i.getItemMeta().hasDisplayName())) {
                    return;
                }

                if (i.equals(HotbarItem.LEAVE_JUMPNRUN)) {
                    game.quitGame(p);
                } else if (i.equals(HotbarItem.TO_CHECKPOINT)) {
                    game.tpToCheckpoint(p);
                }
            }
        } else if (e.getAction().equals(Action.PHYSICAL) && e.getClickedBlock() != null) {
            Material clicked = e.getClickedBlock().getType();

            switch (clicked) {
                case GOLD_PLATE: {
                    Location loc = e.getClickedBlock().getLocation();

                    for (JumpNRun jumpnrun : JumpNRun.values()) {
                        Location start = jumpnrun.getStartPlateLocation();
                        Location end = jumpnrun.getEndPlateLocation();

                        if (start != null && loc.equals(jumpnrun.getStartPlateLocation())) {
                            game.startGame(p, jumpnrun);
                        } else if (end != null && game.isPlaying(p) && loc.equals(jumpnrun.getEndPlateLocation())) {
                            game.finishGame(p);
                        }
                    }
                    break;
                }
                case IRON_PLATE: {
                    if (game.isPlaying(p)) {
                        for (JumpNRun jumpnrun : JumpNRun.values()) {
                            for (int i = 0; i < jumpnrun.getCheckpoints().length; i++) {
                                if (jumpnrun.getCheckpoints()[i].equals(e.getClickedBlock().getLocation())) {
                                    game.setCheckpoint(p, i + 1);
                                    return;
                                }
                            }
                        }
                    }
                    break;
                }
            }
        }
    }

    @EventHandler
    public void onNpcInteract(NpcInteractEvent e) {
        if (e.getNpc().getData().getType().equals(EntityType.PLAYER) && e.getAction().equals(PacketPlayInUseEntity.EnumEntityUseAction.INTERACT)) {
            Player p = e.getPlayer();
            PlayerNpc npc = (PlayerNpc) e.getNpc();
            CoreWorld w = CoreSystem.getInstance().getWorldManager().getWorld(npc.getData().getLocation().getWorld());

            if (w.equals(LobbyWorld.ONE_ISLAND.getWorld()) && npc.getData().getName().equals("Leiche")) {
                new CorpseInventory(p, game);
            }
        }
    }

}
