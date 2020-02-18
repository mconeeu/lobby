package eu.mcone.lobby.story.inventory.story;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.gameapi.api.GameAPI;
import eu.mcone.gameapi.api.backpack.defaults.DefaultItem;
import eu.mcone.gameapi.api.player.GamePlayer;
import eu.mcone.lobby.api.LobbyPlugin;
import org.bukkit.entity.Player;

public class CorpseInventory extends CoreInventory {

    public CorpseInventory(Player p) {
        super("§8» §3§lLeiche", p, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);
        GamePlayer lp = GameAPI.getInstance().getGamePlayer(p);

        setItem(InventorySlot.ROW_2_SLOT_5, DefaultItem.HEAD_SECRET_STRIPCLUB.getItemStack(), e -> {
            if (!lp.hasDefaultItem(DefaultItem.HEAD_SECRET_STRIPCLUB)) {
                lp.addDefaultItem(DefaultItem.HEAD_SECRET_STRIPCLUB);
                LobbyPlugin.getInstance().getMessager().send(p, "§aDu hast den alten Kopf von §fKirpha aufgenommen!");
                LobbyPlugin.getInstance().getJumpNRunManager().setFinish(p);
            } else {
                LobbyPlugin.getInstance().getMessager().send(p, "§cDu besitzt diese Item bereits!");
                LobbyPlugin.getInstance().getJumpNRunManager().setFinish(p);
            }
            p.closeInventory();
        });

        openInventory();
    }
}
