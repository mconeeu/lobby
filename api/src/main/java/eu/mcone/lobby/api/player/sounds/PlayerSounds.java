package eu.mcone.lobby.api.player.sounds;

import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.api.player.settings.LobbySettings;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class PlayerSounds implements SoundManager {

    @Override
    public void playSounds(Player p, Sound sound) {
        if (p != null) {
            LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);
            if (lp != null) {
                LobbySettings settings = lp.getSettings();
                if (settings != null) {
                    if (!settings.isDoNotDisturb()) {
                        p.playSound(p.getLocation(), sound, 1, 1);
                    }
                }
            }
        }
    }

    @Override
    public void playSound(Player p, Location location, Sound sound, int v1, int v2) {
        if (p != null) {
            LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);
            if (lp != null) {
                LobbySettings settings = lp.getSettings();
                if (settings != null) {
                    if (!settings.isDoNotDisturb()) {
                        p.playSound(location, sound, v1, v2);
                    }
                }
            }
        }
    }

    @Override
    public void playErrorSound(Player p) {
        if (p != null) {
            LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);
            if (lp != null) {
                LobbySettings settings = lp.getSettings();
                if (settings != null) {
                    if (!settings.isDoNotDisturb()) {
                        p.playSound(p.getLocation(), settings.getErrorSound().getSound(), 1, 1);
                    }
                } else {
                    System.out.println("SoundSystem LobbyPlayer Settings are null");
                }
            } else {
                System.out.println("SoundSystem LobbyPlayer is null");
            }
        } else {
            System.out.println("Player is null");
        }
    }

    @Override
    public void playNavigatorAnimationSound(Player p) {
        if (p != null) {
            LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);
            if (lp != null) {
                LobbySettings settings = lp.getSettings();
                if (settings != null) {
                    if (!settings.isDoNotDisturb()) {
                        p.playSound(p.getLocation(), settings.getNavigatorSound().getSound(), 1, 1);
                    }
                } else {
                    System.out.println("SoundSystem LobbyPlayer Settings are null");
                }
            } else {
                System.out.println("SoundSystem LobbyPlayer is null");
            }
        } else {
            System.out.println("SoundSystem Player is null");
        }
    }
}
