package eu.mcone.lobby.items.liveevents;

import lombok.Getter;

@Getter
public enum LiveEvents {

    ASTEROID_LAND("asteroid", 17, 16, 42);

    private final String name;
    private final int date;
    private final int hour_of_day;
    private final int minute;


    LiveEvents(String name, int date, int hour_of_day, int minute) {
        this.name = name;
        this.date = date;
        this.hour_of_day = hour_of_day;
        this.minute = minute;
    }
}
