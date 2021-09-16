package eu.mcone.lobby.api.player;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.facades.Msg;
import eu.mcone.coresystem.api.bukkit.facades.Sound;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.api.player.settings.LobbySettings;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashSet;
import java.util.Set;

public class TeleportUtil {

    private static final Set<Player> IN_ANIMATION = new HashSet<>();

    static {
        CoreSystem.getInstance().getVanishManager().registerVanishRule(20, (player, playerCanSee) -> playerCanSee.removeIf(IN_ANIMATION::contains));
    }

    public static void teleportWithAnimation(Player p, Location location) {
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);
        CorePlayer cp = lp.getCorePlayer();
        LobbySettings settings = lp.getSettings();
        boolean isLabyModPlayer = cp.isLabyModPlayer();

        if (location != null) {
            if (settings.isAllowAnimation() && !cp.getWorld().equals(LobbyWorld.OFFICE.getWorld()) && !cp.getWorld().equals(LobbyWorld.CAVE.getWorld())) {
                IN_ANIMATION.add(p);
                for (Player all : p.getWorld().getPlayers()) {
                    all.spigot().playEffect(p.getLocation(), Effect.SMALL_SMOKE, 1, 1, 1, 1, 1, 3, 30, 15);
                }
                CoreSystem.getInstance().getVanishManager().recalculateVanishes();

                p.teleport(makeFixedLocation(p.getLocation()));
                if (isLabyModPlayer) {
                    CoreSystem.getInstance().getLabyModAPI().setCineScopes(p, 20, 3);
                }

                p.setGameMode(GameMode.SPECTATOR);
                Sound.play(p, org.bukkit.Sound.GLASS);
                Sound.tick(p);
                p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 61, 1));

                Bukkit.getScheduler().runTaskLater(LobbyPlugin.getInstance(), () -> {
                    p.teleport(makeFixedLocation(p.getLocation().add(0, 20, 0)));
                    Sound.play(p, org.bukkit.Sound.ENDERDRAGON_WINGS);
                    Sound.teleport(p);

                    Bukkit.getScheduler().runTaskLater(LobbyPlugin.getInstance(), () -> {
                        p.teleport(makeFixedLocation(p.getLocation().add(0, 25, 0)));
                        Sound.teleport(p);

                        Bukkit.getScheduler().runTaskLater(LobbyPlugin.getInstance(), () -> {
                            p.teleport(makeFixedLocation(p.getLocation().add(0, 20, 0)));
                            Sound.teleport(p);

                            /*
                             * BACK
                             */

                            Bukkit.getScheduler().runTaskLater(LobbyPlugin.getInstance(), () -> {
                                p.teleport(makeFixedLocation(location).add(0, 65, 0));
                                Sound.teleport(p);

                                Bukkit.getScheduler().runTaskLater(LobbyPlugin.getInstance(), () -> {
                                    p.teleport(makeFixedLocation(p.getLocation().subtract(0, 35, 0)));
                                    Sound.teleport(p);

                                    Bukkit.getScheduler().runTaskLater(LobbyPlugin.getInstance(), () -> {
                                        p.teleport(makeFixedLocation(p.getLocation().subtract(0, 18, 0)));
                                        Sound.teleport(p);

                                        Bukkit.getScheduler().runTaskLater(LobbyPlugin.getInstance(), () -> {
                                            p.teleport(location);

                                            IN_ANIMATION.remove(p);
                                            CoreSystem.getInstance().getVanishManager().recalculateVanishes();

                                            p.setGameMode(GameMode.SURVIVAL);
                                            p.removePotionEffect(PotionEffectType.CONFUSION);
                                            Sound.play(p, org.bukkit.Sound.FIREWORK_TWINKLE);

                                            if (isLabyModPlayer) {
                                                CoreSystem.getInstance().getLabyModAPI().unsetCineScopes(p);
                                            }
                                        }, 12);
                                    }, 8);
                                }, 7);
                            }, 8);
                        }, 13);
                    }, 12);
                }, 1);
            } else {
                p.teleport(location);
                Sound.play(p, org.bukkit.Sound.GLASS);
            }
        } else {
            Msg.send(p, "§4Dieser Ort ist gerade nicht erreichbar!");
        }
    }

    public static boolean isInAnimation(Player p) {
        return IN_ANIMATION.contains(p);
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
