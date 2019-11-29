package eu.mcone.lobby.story.inventory.story;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.gamesystem.api.enums.Item;
import eu.mcone.gamesystem.api.game.player.GamePlayer;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.story.LobbyStory;
import eu.mcone.lobby.story.jumpnrun.JumpAndRunManager;
import eu.mcone.lobby.api.enums.JumpNRun;
import org.bukkit.entity.Player;

public class CorpseInventory extends CoreInventory {


    public CorpseInventory(Player p) {
        super("§8» §3§lLeiche", p, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);
        GamePlayer lp = LobbyPlugin.getInstance().getGamePlayer(p.getUniqueId());

        setItem(InventorySlot.ROW_2_SLOT_5, Item.HEAD_SECRET_STRIPCLUB.getItemStack(), e -> {
            if (!lp.hasItem(Item.HEAD_SECRET_STRIPCLUB)) {
                lp.addItem(Item.HEAD_SECRET_STRIPCLUB);
                LobbyPlugin.getInstance().getMessager().send(p, "§aDu hast den alten Kopf von §fKirpha aufgenommen!");
                LobbyStory.getInstance().getJumpAndRunManager().setFinish(p);
            } else {
                LobbyPlugin.getInstance().getMessager().send(p, "§cDu besitzt diese Item bereits!");
                LobbyStory.getInstance().getJumpAndRunManager().setFinish(p);
            }
            p.closeInventory();
        });

        openInventory();
    }
}
