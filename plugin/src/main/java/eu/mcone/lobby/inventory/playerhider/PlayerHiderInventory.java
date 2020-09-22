package eu.mcone.lobby.inventory.playerhider;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.api.player.vanish.VanishPlayerVisibility;
import eu.mcone.lobby.util.LobbyVanishManager;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class PlayerHiderInventory extends CoreInventory {

    public PlayerHiderInventory(Player player, LobbyVanishManager manager) {
        super("§8» §a§lSpielersichtbarkeit", player, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);
        LobbyPlayer lobbyPlayer = LobbyPlugin.getInstance().getLobbyPlayer(player);

        if (manager.isInSilentLobby(player)) {
            setItem(InventorySlot.ROW_2_SLOT_2, VanishPlayerVisibility.SILENT.getItem(), e -> {
                player.closeInventory();
                LobbyPlugin.getInstance().getPlayerSounds().playErrorSound(player);
            });
            setItem(InventorySlot.ROW_2_SLOT_4, VanishPlayerVisibility.EVERYBODY.getItem(), e -> {
                manager.quitSilentLobby(player);
                player.closeInventory();
            });

            setItem(InventorySlot.ROW_2_SLOT_6, VanishPlayerVisibility.ONLY_VIPS.getItem(), e -> {
                manager.quitSilentLobby(player);
                updateHiderSystem(player, lobbyPlayer, VanishPlayerVisibility.ONLY_VIPS, manager);
                player.closeInventory();
            });

            setItem(InventorySlot.ROW_2_SLOT_8, VanishPlayerVisibility.NOBODY.getItem(), e -> {
                manager.quitSilentLobby(player);
                updateHiderSystem(player, lobbyPlayer, VanishPlayerVisibility.NOBODY, manager);
                player.closeInventory();
            });

        } else {
            setItem(InventorySlot.ROW_2_SLOT_3, VanishPlayerVisibility.EVERYBODY.getItem(), e -> {
                updateHiderSystem(player, lobbyPlayer, VanishPlayerVisibility.EVERYBODY, manager);
            });

            setItem(InventorySlot.ROW_2_SLOT_5, VanishPlayerVisibility.ONLY_VIPS.getItem(), e -> {
                updateHiderSystem(player, lobbyPlayer, VanishPlayerVisibility.ONLY_VIPS, manager);
            });

            setItem(InventorySlot.ROW_2_SLOT_7, VanishPlayerVisibility.NOBODY.getItem(), e -> {
                updateHiderSystem(player, lobbyPlayer, VanishPlayerVisibility.NOBODY, manager);
            });
        }

        openInventory();

    }

    private void updateHiderSystem(Player p, LobbyPlayer lobbyPlayer, VanishPlayerVisibility vanishPlayerVisibility, LobbyVanishManager manager) {
        player.closeInventory();

        if (!CoreSystem.getInstance().getCooldownSystem().addAndCheck(getClass(), p.getUniqueId())) {
            LobbyPlugin.getInstance().getMessenger().sendError(p, "§4Bitte warte kurz, bevor du erneut die Sichbarkeit von Spielern veränderst!");
            return;
        }

        manager.setVanishPlayerVisibility(player, vanishPlayerVisibility);
        LobbyPlugin.getInstance().getHotbarSettings().updateInventory(p, lobbyPlayer);
        LobbyPlugin.getInstance().getPlayerSounds().playSounds(p, Sound.CHICKEN_EGG_POP);
    }
}
