package eu.mcone.lobby.story.jumpnrun;

import eu.mcone.lobby.api.enums.JumpNRun;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.entity.Player;

@Getter
@RequiredArgsConstructor
public class JumpNRunPlayer {

    private final Player player;
    private final JumpNRun jumpNRun;
    private final long time;
    @Setter
    private int checkpoint = 0;

}
