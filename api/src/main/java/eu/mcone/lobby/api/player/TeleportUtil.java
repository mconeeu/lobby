package eu.mcone.lobby.api.player;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.api.player.settings.LobbySettings;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashSet;
import java.util.Set;

public class TeleportUtil {

    private static final Set<Player> inAnimation = new HashSet<>();

    static {
        CoreSystem.getInstance().getVanishManager().registerVanishRule(20, (player, playerCanSee) -> {
            playerCanSee.removeIf(inAnimation::contains);
        });
    }

    public static void teleportWithAnimation(Player p, Location location) {
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);
        CorePlayer cp = lp.getCorePlayer();
        LobbySettings settings = lp.getSettings();

        if (location != null) {
            if (settings.isAllowAnimation() && !cp.getWorld().equals(LobbyWorld.OFFICE.getWorld()) && !cp.getWorld().equals(LobbyWorld.CAVE.getWorld())) {
                inAnimation.add(p);
                for (Player all : p.getWorld().getPlayers()) {
                    all.spigot().playEffect(p.getLocation(), Effect.SMALL_SMOKE, 1, 1, 1, 1, 1, 3, 30, 15);
                }
                CoreSystem.getInstance().getVanishManager().recalculateVanishes();

                p.teleport(makeFixedLocation(p.getLocation()));

                p.setGameMode(GameMode.SPECTATOR);
                LobbyPlugin.getInstance().getPlayerSounds().playSounds(p, Sound.GLASS);
                LobbyPlugin.getInstance().getPlayerSounds().playSounds(p, Sound.CLICK);
                p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, Integer.MAX_VALUE, 1));

                Bukkit.getScheduler().runTaskLater(LobbyPlugin.getInstance(), () -> {
                    p.teleport(makeFixedLocation(p.getLocation().add(0, 20, 0)));
                    LobbyPlugin.getInstance().getPlayerSounds().playSounds(p, Sound.ENDERDRAGON_WINGS);
                    LobbyPlugin.getInstance().getPlayerSounds().playSounds(p, Sound.ENDERMAN_TELEPORT);

                    Bukkit.getScheduler().runTaskLater(LobbyPlugin.getInstance(), () -> {
                        p.teleport(makeFixedLocation(p.getLocation().add(0, 25, 0)));
                        LobbyPlugin.getInstance().getPlayerSounds().playSounds(p, Sound.ENDERMAN_TELEPORT);

                        Bukkit.getScheduler().runTaskLater(LobbyPlugin.getInstance(), () -> {
                            p.teleport(makeFixedLocation(p.getLocation().add(0, 20, 0)));
                            LobbyPlugin.getInstance().getPlayerSounds().playSounds(p, Sound.ENDERMAN_TELEPORT);

                            /*
                             * BACK
                             */

                            Bukkit.getScheduler().runTaskLater(LobbyPlugin.getInstance(), () -> {
                                p.teleport(makeFixedLocation(location).add(0, 65, 0));
                                LobbyPlugin.getInstance().getPlayerSounds().playSounds(p, Sound.ENDERMAN_TELEPORT);

                                Bukkit.getScheduler().runTaskLater(LobbyPlugin.getInstance(), () -> {
                                    p.teleport(makeFixedLocation(p.getLocation().subtract(0, 35, 0)));
                                    LobbyPlugin.getInstance().getPlayerSounds().playSounds(p, Sound.ENDERMAN_TELEPORT);

                                    Bukkit.getScheduler().runTaskLater(LobbyPlugin.getInstance(), () -> {
                                        p.teleport(makeFixedLocation(p.getLocation().subtract(0, 18, 0)));
                                        LobbyPlugin.getInstance().getPlayerSounds().playSounds(p, Sound.ENDERMAN_TELEPORT);

                                        Bukkit.getScheduler().runTaskLater(LobbyPlugin.getInstance(), () -> {
                                            p.teleport(location);

                                            inAnimation.remove(p);
                                            CoreSystem.getInstance().getVanishManager().recalculateVanishes();

                                            p.setGameMode(GameMode.SURVIVAL);
                                            p.removePotionEffect(PotionEffectType.CONFUSION);
                                            LobbyPlugin.getInstance().getPlayerSounds().playSounds(p, Sound.FIREWORK_TWINKLE);
                                        }, 12);
                                    }, 8);
                                }, 7);
                            }, 8);
                        }, 13);
                    }, 12);
                }, 1);
            } else {
                p.teleport(location);
                LobbyPlugin.getInstance().getPlayerSounds().playSounds(p, Sound.GLASS);
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
