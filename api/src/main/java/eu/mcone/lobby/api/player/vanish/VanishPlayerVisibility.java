package eu.mcone.lobby.api.player.vanish;

import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@Getter
public enum VanishPlayerVisibility {

    NOBODY(
            "Alle Spieler unsichtbar",
            new ItemBuilder(Material.INK_SACK, 1, 10)
                    .displayName("§3§lSpieler Anzeigen §8» §7§oAlle Spieler unsichtbar")
                    .create()
    ),
    ONLY_VIPS(
            "Nur VIPs sichtbar",
            new ItemBuilder(Material.INK_SACK, 1, 11)
                    .displayName("§3§lSpieler Anzeigen §8» §7§oNur VIPs sichtbar")
                    .create()
    ),
    EVERYBODY(
            "Alle Spieler sichtbar",
            new ItemBuilder(Material.INK_SACK, 1, 2)
                    .displayName("§3§lSpieler Verstecken §8» §7§oAlle Spieler sichtbar")
                    .create()
    );

    private final String name;
    private final ItemStack item;

    VanishPlayerVisibility(String name, ItemStack item) {
        this.name = name;
        this.item = item;
    }

    public VanishPlayerVisibility getNextVanishPlayerVisibility() {
        int i = ordinal();

        if (i <= 2) {
            return NOBODY;
        } else return values()[++i];
    }

}
