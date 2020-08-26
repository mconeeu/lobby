package eu.mcone.lobby.api.player.sounds;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public interface SoundManager {
    void playSounds(Player p, Sound sound);

    void playSound(Player p, Location location, Sound sound, int v1, int v2);

    void playErrorSound(Player p);

    void playNavigatorAnimationSound(Player p);
}
