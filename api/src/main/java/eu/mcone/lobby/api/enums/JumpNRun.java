package eu.mcone.lobby.api.enums;

import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.lobby.api.LobbyWorld;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@Getter
public enum JumpNRun {
    STIPCLUB_KIRPHA(
            0,
            new ItemBuilder(Material.LEATHER_BOOTS, 1, 0).displayName("§fStripclub").lore("§8» §f§nLinksklick§8 | §7§oBetreten").create(),
            "jumpandrun_stripclub_warp", "Stripclub",
            "jumpandrun_stripclub_spawn",
            /* START */ new Location(LobbyWorld.ONE_ISLAND.getWorld().bukkit(), 8, 88, -67),
            null,
            new Location[]{
                    new Location(LobbyWorld.ONE_ISLAND.getWorld().bukkit(), 18, 90, -61),
                    new Location(LobbyWorld.ONE_ISLAND.getWorld().bukkit(), 18, 90, -53)
            }
    ),
    CAVE(
            1,
            new ItemBuilder(Material.STONE, 1, 0).displayName("§fHöhle").lore("§8» §f§nLinksklick§8 | §7§oBetreten").create(),
            "jumpandrun_cave_warp", "Cave",
            "jumpandrun_cave_start",
            /* START */ new Location(LobbyWorld.ONE_ISLAND.getWorld().bukkit(), -42, 63, -20),
            new Location(LobbyWorld.ONE_ISLAND.getWorld().bukkit(), 18, 70, -15),
            new Location[]{
                    new Location(LobbyWorld.ONE_ISLAND.getWorld().bukkit(), -12, 66, -16),
            }
    ),

    TOWN_HALL(
            2,
            new ItemBuilder(Material.BOOKSHELF, 1, 0).displayName("§fBibliothek").lore("§8» §f§nLinksklick§8 | §7§oBetreten").create(),
            "jumpandrun_townhall_warp", "Bibliothek",
            "jumpandrun_townhall_start",
            /* START */ new Location(LobbyWorld.ONE_ISLAND.getWorld().bukkit(), 53, 92, -4),
            new Location(LobbyWorld.ONE_ISLAND.getWorld().bukkit(), 49, 69, -6),
            new Location[]{
                    new Location(LobbyWorld.ONE_ISLAND.getWorld().bukkit(), 53, 74, 0),
            }
    );

    private int id;
    private ItemStack itemstack;
    private String warpLocation, startLocation, jumpandrunname;
    private Location startPlateLocation, endPlateLocation;
    private Location[] checkpoints;

    JumpNRun(int id, ItemStack itemStack, String warpLocation, String jumpandrunname, String startLocation, Location startPlateLocation, Location endPlateLocation, Location[] checkpoints) {
        this.id = id;
        this.itemstack = itemStack;
        this.warpLocation = warpLocation;
        this.jumpandrunname = jumpandrunname;
        this.startLocation = startLocation;
        this.startPlateLocation = startPlateLocation;
        this.endPlateLocation = endPlateLocation;
        this.checkpoints = checkpoints;
    }

    public static JumpNRun getJumpNRunById(int id) {
        for (JumpNRun jumpnrun : values()) {
            if (jumpnrun.getId() == id) {
                return jumpnrun;
            }
        }
        return null;
    }

}