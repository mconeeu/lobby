package eu.mcone.lobby.api.player.settings;

import eu.mcone.coresystem.api.bukkit.inventory.settings.Option;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@Getter
public enum SpawnVillage implements Option {

    RANDOM(new ItemBuilder(Material.GOLD_NUGGET).displayName("§f§lZufällig").lore("§7§oSpawne an irgendeinem Lobby Dorf Spawn").create()),
    RAISEN(new ItemBuilder(Material.DIAMOND_BARDING).displayName("§a§lDorf Raisen").lore("§7§oSpawne am Spawn vo Raisen").create()),
    SKYLECK(new ItemBuilder(Material.IRON_INGOT).displayName("§c§lDorf Skyleck").lore("§7§oSpawne am Spawn von Skyleck").create());

    private final ItemStack item;

    SpawnVillage(ItemStack item) {
        this.item = item;
    }

}
