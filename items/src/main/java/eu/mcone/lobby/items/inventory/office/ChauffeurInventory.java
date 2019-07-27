package eu.mcone.lobby.items.inventory.office;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.item.Skull;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.api.player.LobbyPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class ChauffeurInventory extends CoreInventory {

    public ChauffeurInventory(Player p) {
        super("§8» §d§lBüro §8| §Chauffeur", p, InventorySlot.ROW_4, InventoryOption.FILL_EMPTY_SLOTS);
        CorePlayer cp = CoreSystem.getInstance().getCorePlayer(p);
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p.getUniqueId());

        setItem(5, Skull.fromUrl("http://textures.minecraft.net/texture/5163dafac1d91a8c91db576caac784336791a6e18d8f7f62778fc47bf146b6", 1).toItemBuilder()
                .displayName("§fChauffeur")
                .lore("$7§oDer Chauffeur bringt dich zu viele Punkte der Map", "§7§oBlitzschnell und kostenfrei")
                .create());

        setItem(InventorySlot.ROW_3_SLOT_1, new ItemBuilder(Material.NETHER_STAR, 1, 0).displayName("§f§lZum Spawn Reisen")
                .create(), e -> {
            LobbyWorld.ONE_ISLAND.getWorld().teleport(p, "spawn");
        });

        setItem(InventorySlot.ROW_3_SLOT_2, new ItemBuilder(Material.DEAD_BUSH, 1, 0).displayName("§f§lZum Rathaus Reisen")
                .create(), e -> {
            LobbyWorld.ONE_ISLAND.getWorld().teleport(p, "town-hall");
        });

        setItem(InventorySlot.ROW_3_SLOT_3, new ItemBuilder(Material.BED, 1, 0).displayName("§f§lZum Eingang vom Büro Reisen")
                .create(), e -> {
            LobbyWorld.ONE_ISLAND.getWorld().teleport(p, "office-entrance");
        });

        setItem(InventorySlot.ROW_3_SLOT_4, new ItemBuilder(Material.RAW_FISH, 1, 0).displayName("§f§lZum Hafen Reisen")
                .create(), e -> {
            LobbyWorld.ONE_ISLAND.getWorld().teleport(p, "port");
        });

        setItem(InventorySlot.ROW_3_SLOT_5, new ItemBuilder(Material.SAND, 1, 0).displayName("§f§lZum großen Strand Reisen")
                .create(), e -> {
            LobbyWorld.ONE_ISLAND.getWorld().teleport(p, "beach");
        });

        openInventory();

    }
}
