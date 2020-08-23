package eu.mcone.lobby.api.player.settings;

import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import lombok.Getter;
import org.bukkit.Material;

@Getter
public enum JoinPlayerVisibility {

    NO_PREFERENCE(new ItemBuilder(Material.BARRIER).displayName("§f§lNichts ausgewählt")),
    SILENTLOBBY(new ItemBuilder(Material.TNT).displayName("§c§lPrivate Lobby").lore("§7§oSpawne in deiner eigenen ", "§7§oPrivaten Lobby beim joinen")),
    PLAYERHIDER(new ItemBuilder(Material.INK_SACK, 1, 1).displayName("§a§lSpieler verstecken").lore("§7§oSchalte beim betreten alle Spieler aus"));

    private final ItemBuilder item;

    JoinPlayerVisibility(ItemBuilder item) {
        this.item = item;
    }

}

