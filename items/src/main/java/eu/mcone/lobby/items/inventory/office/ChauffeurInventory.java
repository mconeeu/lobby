package eu.mcone.lobby.items.inventory.office;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.item.Skull;
import eu.mcone.coresystem.api.bukkit.world.CoreWorld;
import eu.mcone.lobby.api.LobbyWorld;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class ChauffeurInventory extends CoreInventory {

    public ChauffeurInventory(Player p) {
        super("§8» §d§lBüro §8| §eChauffeur", p, InventorySlot.ROW_5, InventoryOption.FILL_EMPTY_SLOTS);
        setItem(InventorySlot.ROW_1_SLOT_5, Skull.fromUrl("http://textures.minecraft.net/texture/5163dafac1d91a8c91db576caac784336791a6e18d8f7f62778fc47bf146b6", 1).toItemBuilder()
                .displayName("§fChauffeur")
                .lore("§7§oDer Chauffeur bringt dich zu viele Punkte auf der Map", "§7§oBlitzschnell und kostenfrei!")
                .create());

        setItem(InventorySlot.ROW_4_SLOT_2, new ItemBuilder(Material.NETHER_STAR, 1, 0).displayName("§f§lZum Spawn reisen").create(),
                e -> ChauffeurSpawnPoints.SPAWN.getWorld().teleport(p, ChauffeurSpawnPoints.SPAWN.getSpawnLocation()));

        setItem(InventorySlot.ROW_3_SLOT_3, new ItemBuilder(Material.DEAD_BUSH, 1, 0).displayName("§f§lZum Rathaus reisen").create(),
                e -> ChauffeurSpawnPoints.TOWN_HALL.getWorld().teleport(p, ChauffeurSpawnPoints.TOWN_HALL.getSpawnLocation()));

        setItem(InventorySlot.ROW_4_SLOT_4, new ItemBuilder(Material.BED, 1, 0).displayName("§f§lZum Eingang vom Büro reisen").create(),
                e -> ChauffeurSpawnPoints.OFFICE_ENTRANCE.getWorld().teleport(p, ChauffeurSpawnPoints.OFFICE_ENTRANCE.getSpawnLocation()));

        setItem(InventorySlot.ROW_3_SLOT_5, new ItemBuilder(Material.RAW_FISH, 1, 0).displayName("§f§lZum Hafen reisen").create(),
                e -> ChauffeurSpawnPoints.PORT.getWorld().teleport(p, ChauffeurSpawnPoints.PORT.getSpawnLocation()));

        setItem(InventorySlot.ROW_4_SLOT_6, new ItemBuilder(Material.IRON_DOOR, 1, 0).displayName("§f§lZum Bank Gebeäude reisen").create(),
                e -> ChauffeurSpawnPoints.BANK.getWorld().teleport(p, ChauffeurSpawnPoints.BANK.getSpawnLocation()));

        setItem(InventorySlot.ROW_3_SLOT_7, new ItemBuilder(Material.CHEST, 1, 0).displayName("§f§lZum Händler reisen").create(),
                e -> ChauffeurSpawnPoints.DEALER.getWorld().teleport(p, ChauffeurSpawnPoints.DEALER.getSpawnLocation()));

        setItem(InventorySlot.ROW_4_SLOT_8, new ItemBuilder(Material.BOOK, 1, 0).displayName("§f§lZu den Gangs reisen").create(),
                e -> ChauffeurSpawnPoints.GANG.getWorld().teleport(p, ChauffeurSpawnPoints.GANG.getSpawnLocation()));

        openInventory();
    }

    public enum ChauffeurSpawnPoints {
        SPAWN(LobbyWorld.ONE_ISLAND.getWorld(), "spawn"),
        TOWN_HALL(LobbyWorld.ONE_ISLAND.getWorld(), "townHall"),
        OFFICE_ENTRANCE(LobbyWorld.ONE_ISLAND.getWorld(), "officeEntrance"),
        PORT(LobbyWorld.ONE_ISLAND.getWorld(), "port"),
        BEACH(LobbyWorld.ONE_ISLAND.getWorld(), "beach"),
        BANK(LobbyWorld.ONE_ISLAND.getWorld(), "bank"),
        DEALER(LobbyWorld.ONE_ISLAND.getWorld(), "dealer"),
        GANG(LobbyWorld.ONE_ISLAND.getWorld(), "gang");

        @Getter
        private CoreWorld world;
        @Getter
        private String spawnLocation;

        ChauffeurSpawnPoints(final CoreWorld world, final String spawnLocation) {
            this.world = world;
            this.spawnLocation = spawnLocation;
        }
    }
}
