package eu.mcone.lobby.api.player.scoreboard.widgets;

import lombok.Getter;

@Getter
public enum Line {

    LINE_ONE("LINE_ONE", 7, 6),
    LINE_TWO("LINE_TWO", 4, 3);

    private final String name;
    private final int displayKey;
    private final int displayValue;

    Line(String name, int displayKey, int displayValue) {
        this.name = name;
        this.displayKey = displayKey;
        this.displayValue = displayValue;
    }


}
