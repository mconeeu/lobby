package eu.mcone.lobby.items.listener.effects;

import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.api.items.LobbyItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

public class CompassListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.hasItem() && e.getItem().equals(LobbyItem.COMPASS.getItemStack()) && (e.getAction().equals(Action.LEFT_CLICK_BLOCK) || e.getAction().equals(Action.LEFT_CLICK_AIR))) {
            Player p = e.getPlayer();

            p.sendMessage("§8[§7§l!§8] §cItem §8» §fKompass §8|§7 Hier müsste es lang gehen...");

            if (p.hasPermission("lobby.silenthub")) {
                p.getInventory().setItem(3, null);
            } else {
                p.getInventory().setItem(2, null);
            }

            if (p.getLocation().add(0, 5, 0).getBlock().getType() != Material.AIR || p.getLocation().add(0, 4, 0).getBlock().getType() != Material.AIR || (p.getLocation().add(0, 3, 0).getBlock().getType() != Material.AIR)) {
                p.teleport(LobbyPlugin.getInstance().getLobbyWorld(LobbyWorld.ONE_ISLAND).getLocation("island-middle"));
                playCompassEffect(p, true);
            } else {
                playCompassEffect(p, false);
            }
        }
    }

    private void playCompassEffect(Player p, boolean isMiddle) {
        Vector v0 = p.getLocation().getDirection().multiply(7).setY(20);
        Vector v1 = p.getLocation().getDirection().multiply(2).setY(4);
        Vector v2 = p.getLocation().getDirection().multiply(4).setX(8);
        Vector v3 = p.getLocation().getDirection().multiply(3).setZ(8).setY(0.7);
        Vector v4 = p.getLocation().getDirection().multiply(12).setX(-12).setY(0.9);
        Vector v5 = p.getLocation().getDirection().multiply(20).setZ(-1).setY(10);
        Vector v6 = p.getLocation().getDirection().multiply(4).setY(-5);

        if (isMiddle) {
            p.setVelocity(v0);
        } else {
            p.setVelocity(v1);
        }

        LobbyPlugin.getInstance().getPlayerSounds().playSounds(p, Sound.EXPLODE);

        Bukkit.getScheduler().runTaskLater(LobbyPlugin.getInstance(), () -> {
            p.setVelocity(v2);
            LobbyPlugin.getInstance().getPlayerSounds().playSounds(p, Sound.EXPLODE);
            Bukkit.getScheduler().runTaskLater(LobbyPlugin.getInstance(), () -> {
                p.setVelocity(v3);
                LobbyPlugin.getInstance().getPlayerSounds().playSounds(p, Sound.FIRE_IGNITE);
                Bukkit.getScheduler().runTaskLater(LobbyPlugin.getInstance(), () -> {
                    p.setVelocity(v4);
                    LobbyPlugin.getInstance().getPlayerSounds().playSounds(p, Sound.EXPLODE);
                    Bukkit.getScheduler().runTaskLater(LobbyPlugin.getInstance(), () -> {
                        p.setVelocity(v5);
                        LobbyPlugin.getInstance().getPlayerSounds().playSounds(p, Sound.EXPLODE);
                        Bukkit.getScheduler().runTaskLater(LobbyPlugin.getInstance(), () -> {
                            LobbyPlugin.getInstance().getPlayerSounds().playNavigatorAnimationSound(p);
                            Bukkit.getScheduler().runTaskLater(LobbyPlugin.getInstance(), () -> {
                                LobbyPlugin.getInstance().getPlayerSounds().playNavigatorAnimationSound(p);
                                p.setVelocity(v6);
                                Bukkit.getScheduler().runTaskLater(LobbyPlugin.getInstance(), () -> {
                                     LobbyPlugin.getInstance().getPlayerSounds().playSounds(p, Sound.ORB_PICKUP);
                                    if (p.hasPermission("lobby.silenthub")) {
                                        p.getInventory().setItem(3, LobbyItem.COMPASS.getItemStack());
                                    } else {
                                        p.getInventory().setItem(2, LobbyItem.COMPASS.getItemStack());
                                    }
                                }, 32);
                            }, 20);
                        }, 45);
                    }, 11);
                }, 10);
            }, 22);
        }, 12);
    }
}
