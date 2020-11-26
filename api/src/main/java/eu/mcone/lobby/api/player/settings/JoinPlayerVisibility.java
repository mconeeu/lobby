package eu.mcone.lobby.api.player.settings;

import eu.mcone.coresystem.api.bukkit.inventory.settings.Option;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@Getter
public enum JoinPlayerVisibility implements Option {

    NO_PREFERENCE(new ItemBuilder(Material.BARRIER).displayName("§f§lNichts ausgewählt").create()),
    SILENTLOBBY(new ItemBuilder(Material.TNT).displayName("§c§lPrivate Lobby").lore("§7§oSpawne in deiner eigenen ", "§7§oPrivaten Lobby beim joinen").create()),
    PLAYERHIDER(new ItemBuilder(Material.INK_SACK, 1, 1).displayName("§a§lSpieler verstecken").lore("§7§oSchalte beim betreten alle Spieler aus").create());

    private final ItemStack item;

    JoinPlayerVisibility(ItemStack item) {
        this.item = item;
    }

}

