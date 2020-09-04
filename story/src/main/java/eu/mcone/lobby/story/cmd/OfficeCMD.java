package eu.mcone.lobby.story.cmd;

import eu.mcone.coresystem.api.bukkit.command.CorePlayerCommand;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.story.LobbyStory;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class OfficeCMD extends CorePlayerCommand {

    public OfficeCMD() {
        super("office", null, "büro");
    }

    @Override
    public boolean onPlayerCommand(Player player, String[] args) {
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("clear")) {
                LobbyStory.getInstance().getOfficeManager().clearOffice(player);
            } else {
                Player target = Bukkit.getPlayer(args[0]);

                if (target != null) {
                    LobbyStory.getInstance().getOfficeManager().acceptInvite(player, target);
                } else {
                    LobbyPlugin.getInstance().getMessenger().sendError(player, "Der Spieler !["+args[0]+"] ist nicht (mehr) online!");
                }
            }

            return true;
        } else if (args.length == 2 && args[0].equalsIgnoreCase("kick")) {
            Player target = Bukkit.getPlayer(args[1]);

            if (target != null) {
                LobbyStory.getInstance().getOfficeManager().kickFromOffice(player, target);
            } else {
                LobbyPlugin.getInstance().getMessenger().sendError(player, "Der Spieler !["+args[0]+"] ist nicht (mehr) online!");
            }

            return true;
        }

        LobbyPlugin.getInstance().getMessenger().sendError(player, "Bitte benutze: ![/office <Spieler|clear>] oder ![/office kick <Spieler>]");
        return false;
    }
}
