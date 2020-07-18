package eu.mcone.lobby.items.command;

import eu.mcone.coresystem.api.bukkit.command.CorePlayerCommand;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.items.inventory.office.secretary.SecretaryInventory;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class OfficeCMD extends CorePlayerCommand {

    public OfficeCMD() {
        super("Office");
    }

    @Override
    public boolean onPlayerCommand(Player player, String[] args) {
        if (args.length == 1) {

            Player target = Bukkit.getPlayer(args[0]);

            if (SecretaryInventory.isInviting.contains(target)) {
                LobbyPlugin.getInstance().getLobbyWorld(LobbyWorld.ONE_ISLAND).teleportSilently(player, "spawn");
                LobbyPlugin.getInstance().getOfficeManager().joinOtherOffice(target, player);
                SecretaryInventory.isInviting.remove(target);
            } else {
                LobbyPlugin.getInstance().getMessenger().send(player, "§4Der Einladungslink ist ungültig!");
            }

        }
        return false;
    }
}
