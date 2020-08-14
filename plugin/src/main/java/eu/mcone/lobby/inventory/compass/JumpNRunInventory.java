package eu.mcone.lobby.inventory.compass;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.lobby.Lobby;
import eu.mcone.lobby.api.enums.JumpNRun;
import eu.mcone.lobby.api.games.jumpnrun.JumpNRunGame;
import eu.mcone.lobby.games.LobbyGames;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class JumpNRunInventory extends CoreInventory {

    public JumpNRunInventory(Player player) {
        super("§8» §3§lJump & Runs", player, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);
        Bukkit.getScheduler().runTaskLater(Lobby.getSystem(), () -> {
            int i = 11;

            for (JumpNRun jumpNRun : JumpNRun.values()) {
                setItem(i, jumpNRun.getItemstack(), e -> LobbyGames.getInstance().getGame(JumpNRunGame.class).startGame(player, jumpNRun));
                i++;
            }
            player.playSound(player.getLocation(), Sound.NOTE_PLING, 1, 1);
        }, 2L);

        setItem(InventorySlot.ROW_3_SLOT_9, new ItemBuilder(Material.IRON_DOOR, 1, 0).displayName("§7§l↩ Zurück").create(), e -> new LobbyGamesInventory(player));

        openInventory();
    }

}
