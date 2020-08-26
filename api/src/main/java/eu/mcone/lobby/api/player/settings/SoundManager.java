package eu.mcone.lobby.api.player.settings;

import lombok.Getter;
import org.bukkit.Sound;

@Getter
public enum SoundManager {

    ORB_PICKUP(Sound.ORB_PICKUP, "Pickup"),
    NOTE_BASS(Sound.NOTE_BASS, "Bass"),
    NOTE_PLING(Sound.NOTE_PLING, "Pling"),
    SPLASH(Sound.SPLASH, "Splash"),
    SLIME_ATTACK(Sound.SLIME_ATTACK, "Slime Angriff"),
    ANVIL_BREAK(Sound.ANVIL_BREAK, "Amboss geht kaputt"),
    DIG_GRAVEL(Sound.DIG_GRAVEL, "Graben"),
    EAT(Sound.EAT, "Essen"),
    CHEST_CLOSE(Sound.CHEST_CLOSE, "Kiste schließen"),
    DOOR_CLOSE(Sound.DOOR_CLOSE, "Tür schließen"),
    FIZZ(Sound.FIZZ, "Fizz"),
    STEP_SAND(Sound.STEP_SAND, "Über Sand laufen"),
    SWIM(Sound.SWIM, "Schwimmen"),
    NOTE_BASS_GUITAR(Sound.NOTE_BASS_GUITAR, "Bass Guitar"),
    NOTE_SNARE_DRUM(Sound.NOTE_SNARE_DRUM, "Snare Drum"),
    PISTON(Sound.PISTON_EXTEND, "Kolben"),
    CLICK(Sound.CLICK, "Click"),
    CHICKEN_EGG_POP(Sound.CHICKEN_EGG_POP, "Hünchen Pop"),
    VILLAGER_DEATH(Sound.VILLAGER_DEATH, "Villager stirbt");

    private final Sound sound;
    private final String soundName;

    SoundManager(Sound sound, String soundName) {
        this.sound = sound;
        this.soundName = soundName;
    }

}
