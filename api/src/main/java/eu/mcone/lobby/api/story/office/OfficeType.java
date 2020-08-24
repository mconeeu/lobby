package eu.mcone.lobby.api.story.office;

import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.api.items.LobbyItem;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;

@Getter
public enum OfficeType {

    BRONZE(Material.CLAY_BRICK, "§cBronze", "bronzeOffice", 150, LobbyItem.OFFICE_CARD_BRONZE),
    SILVER(Material.IRON_INGOT, "§fSilber", "silverOffice", 250, LobbyItem.OFFICE_CARD_SILVER),
    GOLD(Material.GOLD_INGOT, "§6Gold", "goldOffice", 500, LobbyItem.OFFICE_CARD_GOLD);

    private final Material item;
    private final String label, spawnLocation;
    private final int emeraldPrice;
    private final LobbyItem officeCard;

    OfficeType(Material item, String label, String spawnLocation, int emeraldPrice, LobbyItem officeCard) {
        this.item = item;
        this.label = label;
        this.spawnLocation = spawnLocation;
        this.emeraldPrice = emeraldPrice;
        this.officeCard = officeCard;
    }

    public void teleport(Player p) {
        LobbyWorld.OFFICE.getWorld().teleport(p, spawnLocation);
    }

}
