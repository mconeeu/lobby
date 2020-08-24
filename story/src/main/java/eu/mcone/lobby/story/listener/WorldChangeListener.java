package eu.mcone.lobby.story.listener;

import eu.mcone.lobby.api.LobbyPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class WorldChangeListener implements Listener {

    @EventHandler
    public void on(PlayerChangedWorldEvent e) {
        //TODO: Testen ob das wirklich nötog ist
        /*for (StoryProgress storyProgress : StoryProgress.values()) {
            NPC npc = storyProgress.getNpc();
            npc.toggleVisibility(e.getPlayer(), false);
        }
        for (TutorialStory tutorialStory : TutorialStory.values()) {
            NPC npc = tutorialStory.getNpc();
            npc.toggleVisibility(e.getPlayer(), false);
        }
        for (TraderProgress traderProgress : TraderProgress.values()) {
            NPC npc = traderProgress.getNpc();
            npc.toggleVisibility(e.getPlayer(), false);
        }*/

        GeneralPlayerListener.spawnStoryNpcs(
                LobbyPlugin.getInstance().getLobbyPlayer(e.getPlayer().getUniqueId())
        );
    }

}
