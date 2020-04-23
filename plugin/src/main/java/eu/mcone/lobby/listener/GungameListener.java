package eu.mcone.lobby.listener;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.event.BuildModeChangeEvent;
import eu.mcone.lobby.Lobby;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.player.HotbarItems;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.gungame.LobbyGungameManager;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.util.Vector;

@RequiredArgsConstructor
public class GungameListener implements Listener {

    private final LobbyGungameManager manager;


    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        Player k = LobbyPlugin.getInstance().getDamageLogger().getKiller(p);

        e.setKeepInventory(true);
        e.setKeepLevel(true);
        e.getDrops().clear();
        p.setVelocity(new Vector(0, 0, 0));

        if (manager.isFighting(p) && manager.isFighting(k)) {
            p.setExp(1);
            LobbyPlugin.getInstance().getMessenger().send(k, "§7Du hast §f" + p.getDisplayName() + " §7getötet §8[§a+2 Coins§8]");
            LobbyPlayer lk = LobbyPlugin.getInstance().getLobbyPlayer(k);
            lk.getCorePlayer().addCoins(2);
            k.playSound(k.getLocation(), Sound.LEVEL_UP, 1, 1);
            k.setExp(1);
            p.setExp(1);
            k.setLevel(k.getLevel() + 1);
            manager.updateGungameFightItems(k);

            CoreSystem.getInstance().getCorePlayer(k).getScoreboard().getObjective(DisplaySlot.SIDEBAR).reload();

            LobbyPlugin.getInstance().getMessenger().send(p, "§7Du wurdest von §f" + k.getDisplayName() + " §7getötet!");

        }

        e.setDeathMessage("");
        p.spigot().respawn();
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        if (manager.isFighting(p)) {
            p.setExp(1);
            e.setRespawnLocation(manager.getRandomSpawn());
            if (!manager.isSave(p)) {
                Bukkit.getScheduler().runTask(Lobby.getSystem(), () -> manager.addSave(p));
            }
        }
    }


    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (manager.isFighting(p)) {
            if (p.getLocation().getBlock().getType() == Material.WATER || p.getLocation().getBlock().getType() == Material.STATIONARY_WATER) {
                if (p.getHealth() > 0) {
                    p.setHealth(0);
                    p.spigot().respawn();
                }
            }
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            ItemStack i = e.getItem();
            if ((i == null) || (!i.hasItemMeta()) || (!i.getItemMeta().hasDisplayName())) {
                return;
            }

            if (i.equals(HotbarItems.LEAVE_GUNGAME_FIGHTING)) {
                manager.leave(p);
            }
        }
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e) {
        Player p = e.getPlayer();

        if (e.getMessage().equalsIgnoreCase("/spawn")) {
            if (manager.isFighting(p)) {
                manager.leave(p);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();

            if (e.getDamager() instanceof Player) {
                Player k = (Player) e.getDamager();


                if (manager.isFighting(p) && manager.isFighting(k)) {
                    if (manager.isSave(k)) {
                        LobbyPlugin.getInstance().getMessenger().send(k, "§cDu darfst noch keine Spieler angreifen!");
                        e.setCancelled(true);
                        return;
                    }
                    if (manager.isSave(p)) {
                        LobbyPlugin.getInstance().getMessenger().send(k, "§cDu darfst diesen Spieler noch nicht angreifen!");
                        e.setCancelled(true);
                        return;
                    }
                    e.setCancelled(false);
                }
            }
        }

    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBuildLeave(BuildModeChangeEvent e) {
        Player p = e.getPlayer();

        if (!e.isCanBuild() && manager.isFighting(p)) {
            p.setGameMode(GameMode.ADVENTURE);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        manager.playerLeaved(e.getPlayer());
    }

}
