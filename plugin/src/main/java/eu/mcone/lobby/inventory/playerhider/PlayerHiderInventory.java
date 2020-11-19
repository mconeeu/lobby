package eu.mcone.lobby.inventory.playerhider;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.facades.Sound;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.games.jumpnrun.JumpNRunGame;
import eu.mcone.lobby.api.player.HotbarItem;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.api.player.vanish.VanishPlayerVisibility;
import eu.mcone.lobby.games.LobbyGames;
import eu.mcone.lobby.util.LobbyVanishManager;
import org.bukkit.entity.Player;

public class PlayerHiderInventory extends CoreInventory {

    public PlayerHiderInventory(Player player, LobbyVanishManager manager) {
        super("§8» §a§lSpielersichtbarkeit", player, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);
        LobbyPlayer lobbyPlayer = LobbyPlugin.getInstance().getLobbyPlayer(player);

        if (manager.isInSilentLobby(player)) {
            setItem(InventorySlot.ROW_2_SLOT_3, VanishPlayerVisibility.EVERYBODY.getItem(), e -> {
                manager.quitSilentLobby(player);
                updateHiderSystem(player, lobbyPlayer, VanishPlayerVisibility.EVERYBODY, manager);
                player.closeInventory();
            });

            setItem(InventorySlot.ROW_2_SLOT_5, VanishPlayerVisibility.ONLY_VIPS.getItem(), e -> {
                manager.quitSilentLobby(player);
                updateHiderSystem(player, lobbyPlayer, VanishPlayerVisibility.ONLY_VIPS, manager);
                player.closeInventory();
            });

            setItem(InventorySlot.ROW_2_SLOT_7, VanishPlayerVisibility.NOBODY.getItem(), e -> {
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

        if (LobbyGames.getInstance().getCurrentGame(p) instanceof JumpNRunGame) {
            int slot = 0;
            if (LobbyPlugin.getInstance().getVanishManager().isInSilentLobby(p)) {
                p.getInventory().setItem(slot, HotbarItem.LOBBY_HIDER_UNAVAILABLE_SILENT_LOBBY);
            } else {
                p.getInventory().setItem(slot, LobbyPlugin.getInstance().getVanishManager().getVanishPlayerVisibility(p).getItem());
            }
        } else {
            LobbyPlugin.getInstance().getHotbarSettings().updateInventory(p, lobbyPlayer);
            Sound.click(p);

            if (vanishPlayerVisibility.equals(VanishPlayerVisibility.EVERYBODY)) {
                LobbyPlugin.getInstance().getBackpackManager().setCurrentBackpackItem(LobbyPlugin.getInstance().getGamePlayer(player), true);
            }
        }
    }
}
