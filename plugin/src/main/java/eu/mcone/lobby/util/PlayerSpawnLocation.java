package eu.mcone.lobby.util;

import eu.mcone.coresystem.api.bukkit.world.CoreWorld;
import eu.mcone.lobby.api.LobbyWorld;
import lombok.Getter;
import org.bukkit.Material;

@Getter
public enum PlayerSpawnLocation {
    LAST_LOGIN("§7Zuletzt eingelogt", Material.GRASS, null, null),
    SPAWN("§7Spawn", Material.NETHER_STAR, LobbyWorld.ONE_ISLAND.getWorld(), "spawn"),
    OFFICE("§7Büro", Material.BOOK_AND_QUILL, LobbyWorld.OFFICE.getWorld(), null);

    private String displayname;
    private Material material;
    private CoreWorld world;
    private String location;

    PlayerSpawnLocation(final String displayname, final Material material, final CoreWorld world, final String location) {
        this.displayname = displayname;
        this.material = material;
        this.world = world;
        this.location = location;
    }
}
