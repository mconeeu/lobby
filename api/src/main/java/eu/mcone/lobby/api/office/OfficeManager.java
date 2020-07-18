package eu.mcone.lobby.api.office;

import org.bukkit.entity.Player;

public interface OfficeManager {

    void getOffice(Player player);

    void getOfficeFromOther(Player player, Player other);

    void joinOtherOffice(Player player, Player other);

    void joinOffice(Player player);

    void quitOffice(Player player);

    void vanishPlayer(Player player);

    void unVanishPlayer(Player player);

    void updateOffice(Player player);
}
