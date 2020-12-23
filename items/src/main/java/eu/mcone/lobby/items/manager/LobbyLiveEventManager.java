package eu.mcone.lobby.items.manager;

import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.liveevent.LiveEvent;
import eu.mcone.lobby.api.liveevent.LiveEventManager;
import eu.mcone.lobby.items.liveevents.events.Asteroid;
import org.bukkit.Bukkit;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class LobbyLiveEventManager implements Runnable, LiveEventManager {

    private static final Set<LiveEvent> EVENTS = new HashSet<>(Collections.singletonList(
            new Asteroid()
    ));

    public LobbyLiveEventManager() {
        Bukkit.getScheduler().runTaskTimerAsynchronously(LobbyPlugin.getInstance(), this, 0, 20);
    }

    @Override
    public void run() {
        for (LiveEvent event : EVENTS) {
            if (event.getDate().before(new Date())) {
                if (!event.isRunning()) {
                    LobbyPlugin.getInstance().sendConsoleMessage("§fStarting LobbyEvent " + event.getClass().getSimpleName());
                    event.startEvent();
                }
            }
        }
    }

    @Override
    public boolean startEvent(Class<? extends LiveEvent> event) {
        for (LiveEvent e : EVENTS) {
            if (event.equals(e.getClass())) {
                if (!e.isRunning()) {
                    e.startEvent();
                    return true;
                } else return false;
            }
        }

        return false;
    }

    @Override
    public boolean removeEvent(Class<? extends LiveEvent> event) {
        for (LiveEvent e : EVENTS) {
            if (event.equals(e.getClass())) {
                e.removeEvent();
                return true;
            }
        }

        return false;
    }

    @Override
    public Set<LiveEvent> getEvents() {
        return EVENTS;
    }
}
