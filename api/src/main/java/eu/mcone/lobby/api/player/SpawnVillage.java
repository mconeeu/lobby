package eu.mcone.lobby.api.player;

import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import lombok.Getter;
import org.bukkit.Material;

@Getter
public enum SpawnVillage {

    RANDOM(new ItemBuilder(Material.GOLD_NUGGET).displayName("§f§lZufällig").lore("§7§oSpawne an irgendeinem Lobby Dorf Spawn")),
    RAISEN(new ItemBuilder(Material.DIAMOND_BARDING).displayName("§a§lDorf Raisen").lore("§7§oSpawne am Spawn vo Raisen")),
    SKYLECK(new ItemBuilder(Material.IRON_INGOT).displayName("§c§lDorf Skyleck").lore("§7§oSpawne am Spawn von Skyleck"));

    private final ItemBuilder item;

    SpawnVillage(ItemBuilder item) {
        this.item = item;
    }

}
