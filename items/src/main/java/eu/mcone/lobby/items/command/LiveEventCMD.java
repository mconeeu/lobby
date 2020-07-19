package eu.mcone.lobby.items.command;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.command.CorePlayerCommand;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.items.liveevents.LiveEvents;
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
                    for (LiveEvents liveEvents : LiveEvents.values()) {
                        if (liveEvents != null) {
                            if (liveEvents.getName().equalsIgnoreCase(eventName)) {
                                LobbyPlugin.getInstance().getLiveEventManager().startLiveEventAsteroid();
                                LobbyPlugin.getInstance().getMessenger().send(p, "§2Das §aLiveEvent§2 startet nun!");
                            } else {
                                LobbyPlugin.getInstance().getMessenger().send(p, "§4Dieses §cLiveEvent§4 existiert nicht!");
                            }
                        }
                    }


                } else if (args[0].equalsIgnoreCase("remove")) {
                    for (LiveEvents liveEvents : LiveEvents.values()) {
                        if (liveEvents != null) {
                            if (liveEvents.getName().equalsIgnoreCase(eventName)) {
                                LobbyPlugin.getInstance().getLiveEventManager().removeLiveEventAsteroid();
                                LobbyPlugin.getInstance().getMessenger().send(p, "§4Das §cLiveEvent§4 wurde gelöscht!");
                            } else {
                                LobbyPlugin.getInstance().getMessenger().send(p, "§4Dieses §cLiveEvent§4 existiert nicht!");
                            }
                        }
                    }
                } else {
                    LobbyPlugin.getInstance().getMessenger().send(p, "§4Bitte benutze: §c/liveevent [<start>|<remove>] <name>");

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
