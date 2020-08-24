/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.listener;

import eu.mcone.lobby.Lobby;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.games.LobbyGames;
import fr.neatmonster.nocheatplus.checks.CheckType;
import fr.neatmonster.nocheatplus.hooks.NCPExemptionManager;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DoubleJumpListener implements Listener {

    private static final List<UUID> DJ_PLAYERS = new ArrayList<>();

    @EventHandler
    public void onToggleFlight(PlayerToggleFlightEvent e) {
        Player p = e.getPlayer();

        if (p.getGameMode().equals(GameMode.CREATIVE)) {
            e.setCancelled(false);
        } else if (p.getGameMode().equals(GameMode.SURVIVAL) && p.hasPermission("mcone.premium") && !LobbyGames.getInstance().isPlaying(p)) {
            e.setCancelled(true);
            DJ_PLAYERS.add(p.getUniqueId());

            if (Bukkit.getPluginManager().getPlugin("NoCheatPlus") != null) {
                NCPExemptionManager.exemptPermanently(p, CheckType.MOVING_SURVIVALFLY);
                Bukkit.getScheduler().runTaskLaterAsynchronously(LobbyPlugin.getInstance(), () -> NCPExemptionManager.unexempt(p), 3 * 20);
            }

            p.setAllowFlight(false);
            p.setFlying(false);

            Vector vec = p.getLocation().getDirection().normalize();
            vec = vec.setY(Math.max(0.4000000059604645D, vec.getY())).multiply(1.5F);
            p.setVelocity(vec);

            p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 2.0F, 2.0F);
            p.playEffect(p.getLocation(), Effect.BLAZE_SHOOT, 10);
        } else {
            e.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();

        if (p.getGameMode().equals(GameMode.SURVIVAL)) {
            if (DJ_PLAYERS.contains(p.getUniqueId()) && !p.getLocation().add(0, -1, 0).getBlock().getType().equals(Material.AIR)) {
                p.setAllowFlight(true);
                p.setFlying(false);

                if (Bukkit.getPluginManager().getPlugin("NoCheatPlus") != null)
                    NCPExemptionManager.unexempt(p);
                DJ_PLAYERS.remove(p.getUniqueId());
            }
        }
    }

    @EventHandler
    public void onGamemodeChange(PlayerGameModeChangeEvent e) {
        Player p = e.getPlayer();

        if ((p.getGameMode().equals(GameMode.CREATIVE) || p.getGameMode().equals(GameMode.SPECTATOR))
                && (!e.getNewGameMode().equals(GameMode.CREATIVE) || !e.getNewGameMode().equals(GameMode.SPECTATOR))
                && !LobbyGames.getInstance().isPlaying(p)) {
            Bukkit.getScheduler().runTask(Lobby.getSystem(), () -> p.setAllowFlight(true));
        }
    }

}
