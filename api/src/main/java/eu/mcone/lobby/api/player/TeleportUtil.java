package eu.mcone.lobby.api.player;

import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.api.player.settings.LobbySettings;
import io.netty.channel.local.LocalAddress;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class TeleportUtil {

    public static void teleportWithAnimation(Player p, Location location) {
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);
        CorePlayer cp = lp.getCorePlayer();
        LobbySettings settings = lp.getSettings();

        if (location != null) {
            if (settings.isAllowAnimation() && !cp.getWorld().equals(LobbyWorld.OFFICE.getWorld()) && !cp.getWorld().equals(LobbyWorld.CAVE.getWorld())) {
                for (Player all : p.getWorld().getPlayers()) {
                    all.spigot().playEffect(p.getLocation(), Effect.SMALL_SMOKE, 1, 1, 1, 1, 1, 3, 30, 15);
                    all.hidePlayer(p);
                }

                p.teleport(makeFixedLocation(p.getLocation()));

                p.setGameMode(GameMode.SPECTATOR);
                p.playSound(p.getLocation(), Sound.GLASS, 3, 2);
                p.playSound(p.getLocation(), Sound.CLICK, 3, 2);
                p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, Integer.MAX_VALUE, 1));

                Bukkit.getScheduler().runTaskLater(LobbyPlugin.getInstance(), () -> {
                    p.teleport(makeFixedLocation(p.getLocation().add(0, 20, 0)));
                    p.playSound(p.getLocation(), Sound.ENDERDRAGON_WINGS, 3, 2);
                    p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 3, 2);

                    Bukkit.getScheduler().runTaskLater(LobbyPlugin.getInstance(), () -> {
                        p.teleport(makeFixedLocation(p.getLocation().add(0, 25, 0)));
                        p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 3, 2);

                        Bukkit.getScheduler().runTaskLater(LobbyPlugin.getInstance(), () -> {
                            p.teleport(makeFixedLocation(p.getLocation().add(0, 20, 0)));
                            p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 3, 2);

                            /*
                             * BACK
                             */

                            Bukkit.getScheduler().runTaskLater(LobbyPlugin.getInstance(), () -> {
                                p.teleport(makeFixedLocation(location).add(0, 65, 0));
                                p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 3, 3);

                                Bukkit.getScheduler().runTaskLater(LobbyPlugin.getInstance(), () -> {
                                    p.teleport(makeFixedLocation(p.getLocation().subtract(0, 35, 0)));
                                    p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 3, 2);

                                    Bukkit.getScheduler().runTaskLater(LobbyPlugin.getInstance(), () -> {
                                        p.teleport(makeFixedLocation(p.getLocation().subtract(0, 18, 0)));
                                        p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 3, 2);

                                        Bukkit.getScheduler().runTaskLater(LobbyPlugin.getInstance(), () -> {
                                            p.teleport(location);

                                            p.setGameMode(GameMode.SURVIVAL);
                                            p.removePotionEffect(PotionEffectType.CONFUSION);
                                            p.playSound(p.getLocation(), Sound.FIREWORK_TWINKLE, 3, 2);
                                        }, 12);
                                    }, 8);
                                }, 7);
                            }, 8);
                        }, 13);
                    }, 12);
                }, 1);
            } else {
                p.teleport(location);
                p.playSound(p.getLocation(), Sound.GLASS, 3, 2);
            }
        } else {
            LobbyPlugin.getInstance().getMessenger().send(p, "§4Dieser Ort ist gerade nicht erreichbar!");
        }
    }

    private static Location makeFixedLocation(Location location) {
        return new Location(
                location.getWorld(),
                location.getX(),
                location.getY(),
                location.getZ(),
                location.getPitch(),
                (float) (location.getYaw() + 100.0)
        );
    }

}
