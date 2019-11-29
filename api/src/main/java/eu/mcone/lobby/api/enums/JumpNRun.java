package eu.mcone.lobby.api.enums;

import eu.mcone.lobby.api.LobbyWorld;
import lombok.Getter;
import org.bukkit.Location;

@Getter
public enum JumpNRun {
    STIPCLUB_KIRPHA(
            0,
            "jumpandrun_stripclub_warp", "Stripclub",
            "jumpandrun_stripclub_spawn",
            /* START */ new Location(LobbyWorld.ONE_ISLAND.getWorld().bukkit(), 8, 88, -67),
            null,
            new Location[]{
                    new Location(LobbyWorld.ONE_ISLAND.getWorld().bukkit(), 18, 90, -61),
                    new Location(LobbyWorld.ONE_ISLAND.getWorld().bukkit(), 18, 90, -53)
            }
    );

    private int id;
    private String warpLocation, startLocation, jumpandrunname;
    private Location startPlateLocation, endPlateLocation;
    private Location[] checkpoints;

    JumpNRun(int id, String warpLocation, String jumpandrunname, String startLocation, Location startPlateLocation, Location endPlateLocation, Location[] checkpoints) {
        this.id = id;
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