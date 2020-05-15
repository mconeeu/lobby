package eu.mcone.lobby.api.player;

import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import lombok.Getter;
import org.bukkit.Material;

@Getter
public enum SpawnType {

    NONE(new ItemBuilder(Material.BARRIER).displayName("§f§lNichts ausgewählt")),
    SILENTLOBBY(new ItemBuilder(Material.TNT).displayName("§c§lPrivate Lobby beim joinen betreten").lore("§7§oSpawne in deiner eigenen Privaten Lobby beim spawnen")),
    PLAYERHIDER_DEATIVE(new ItemBuilder(Material.INK_SACK, 1, 1).displayName("§a§lSpieler verstecken beim joinen aktivieren").lore("§7§oSchalte beim joinen alle Spieler aus"));

    private ItemBuilder item;

    SpawnType(ItemBuilder item) {
        this.item = item;
    }

}

