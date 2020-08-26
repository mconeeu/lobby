package eu.mcone.lobby.items.command;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.command.CorePlayerCommand;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.liveevent.LiveEvent;
import eu.mcone.lobby.items.LobbyItems;
import org.bukkit.entity.Player;

public class LiveEventCMD extends CorePlayerCommand {

    public LiveEventCMD() {
        super("liveevent");
    }

    @Override
    public boolean onPlayerCommand(Player p, String[] args) {
        if (p.hasPermission("lobby.liveevent")) {


            if (args.length == 2) {
                String eventName = args[1];
                if (args[0].equalsIgnoreCase("start")) {
                    for (LiveEvent event : LobbyItems.getInstance().getLiveEventManager().getEvents()) {
                        if (event.getName().equalsIgnoreCase(eventName)) {
                            if (LobbyItems.getInstance().getLiveEventManager().startEvent(event.getClass())) {
                                LobbyPlugin.getInstance().getMessenger().sendSuccess(p, "Das ![LiveEvent] startet nun!");
                            } else {
                                LobbyPlugin.getInstance().getMessenger().sendError(p, "Das ![LiveEvent] ist bereits gestartet!");
                            }
                        }

                        break;
                    }

                    LobbyPlugin.getInstance().getMessenger().send(p, "§4Dieses §cLiveEvent§4 existiert nicht!");
                } else if (args[0].equalsIgnoreCase("remove")) {
                    for (LiveEvent event : LobbyItems.getInstance().getLiveEventManager().getEvents()) {
                        if (event.getName().equalsIgnoreCase(eventName)) {
                            if (LobbyItems.getInstance().getLiveEventManager().removeEvent(event.getClass())) {
                                LobbyPlugin.getInstance().getMessenger().sendSuccess(p, "Das ![LiveEvent] wird gelöscht!");
                            } else {
                                LobbyPlugin.getInstance().getMessenger().sendError(p, "Das ![LiveEvent] wurde noch nicht gestartet!");
                            }
                        }

                        break;
                    }

                    LobbyPlugin.getInstance().getMessenger().sendError(p, "Dieses ![LiveEvent] existiert nicht!");
                } else {
                    LobbyPlugin.getInstance().getMessenger().sendError(p, "§4Bitte benutze: §c/liveevent [<start>|<remove>] <name>");

                }
            } else {
                LobbyPlugin.getInstance().getMessenger().send(p, "§4Bitte benutze: §c/liveevent [<start>|<remove>] <name>");
            }
        } else {
            CoreSystem.getInstance().getMessenger().sendTransl(p, "system.command.noperm");
        }
        return false;
    }
}
