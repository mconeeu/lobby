package eu.mcone.lobby.api.liveevent;

import java.util.Set;

public interface LiveEventManager {

    Set<LiveEvent> getEvents();

    boolean startEvent(Class<? extends LiveEvent> event);

    boolean removeEvent(Class<? extends LiveEvent> event);

}