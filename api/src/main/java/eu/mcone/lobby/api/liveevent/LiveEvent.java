package eu.mcone.lobby.api.liveevent;

import lombok.Getter;

import java.util.Date;

@Getter
public abstract class LiveEvent {

    private final String name;
    private final Date date;
    private boolean isRunning;

    public LiveEvent(String name, Date date) {
        this.name = name;
        this.date = date;
    }

    public void startEvent() {
        this.isRunning = true;
        onStartEvent();
    }

    public void removeEvent() {
        this.isRunning = false;
        onRemoveEvent();
    }

    protected abstract void onStartEvent();

    protected abstract void onRemoveEvent();

}
