package eu.mcone.lobby.inventory;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.lobby.api.enums.Item;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.util.PlayerSpawnLocation;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class PlayerSpawnLocationInventory extends CoreInventory {

    PlayerSpawnLocationInventory(Player player) {
        super("§8» §c§lLobby Einstellung", player, InventorySlot.ROW_4, InventoryOption.FILL_EMPTY_SLOTS);

        LobbyPlayer lp = LobbyPlugin.getInstance().getGamePlayer(player.getUniqueId());

        PlayerSpawnLocation playerSpawnLocation = PlayerSpawnLocation.valueOf(lp.getSettings().getSpawnLocation());

        if (player.hasPermission("lobby.silenthub")) {
            setItem(InventorySlot.ROW_2_SLOT_3, new ItemBuilder(Material.TNT, 1, 0).displayName("§f§lSpawne in deiner Privaten Lobby").create());

            if (lp.getSettings().isSpawnInSilentLobby()) {
                setItem(InventorySlot.ROW_3_SLOT_3, new ItemBuilder(Material.INK_SACK, 1, 10).displayName("§a§lAktiviert").lore("§7§oKlicke um das Spawnen", "§7§oin der privaten Lobby", "§7§ozu aktivieren").create(), e -> {
                    setSilentData(lp, false);
                    player.playSound(player.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
                });
            } else {
                setItem(InventorySlot.ROW_3_SLOT_3, new ItemBuilder(Material.INK_SACK, 1, 1).displayName("§c§lDeaktiviert").lore("§7§oKlicke um das Spawnen", "§7§oin der privaten Lobby", "§7§ozu aktivieren").create(), e -> {
                    setSilentData(lp, true);

                    player.playSound(player.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
                });
            }
        } else {
            setItem(InventorySlot.ROW_2_SLOT_3, new ItemBuilder(Material.TNT, 0, 0).displayName("§6§lKaufe Premium und die Silentlobby benutzen zu könenn.").create());
        }

        for (PlayerSpawnLocation spawns : PlayerSpawnLocation.values()) {
            if (spawns.equals(PlayerSpawnLocation.OFFICE)) {
                if (Item.OFFICE_CARD_BRONZE.has(lp)
                        || Item.OFFICE_CARD_SILVER.has(lp)
                        || Item.OFFICE_CARD_GOLD.has(lp)) {

                    setItem(InventorySlot.ROW_2_SLOT_5, new ItemBuilder(spawns.getMaterial()).displayName(spawns.getDisplayname()).create());

                    if (playerSpawnLocation.equals(spawns)) {
                        setItem(InventorySlot.ROW_3_SLOT_5, new ItemBuilder(Material.INK_SACK, 1, 10).displayName("§a§lAktiviert").lore("§7§oKlicke um deinen Spawnpunkt", "§7§obei deinem Office", "§7§ozu setzten").create(), e -> {
                            setData(lp, PlayerSpawnLocation.LAST_LOGIN);
                            player.playSound(player.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
                        });
                    } else {
                        setItem(InventorySlot.ROW_3_SLOT_5, new ItemBuilder(Material.INK_SACK, 1, 1).displayName("§c§lDeaktiviert").lore("§7§oKlicke um deinen Spawnpunkt", "§7§obei deinem letzten logout", "§7§oPunkt zu setzten").create(), e -> {
                            setData(lp, PlayerSpawnLocation.OFFICE);
                            player.playSound(player.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
                        });
                    }
                } else {
                    setItem(InventorySlot.ROW_2_SLOT_5, new ItemBuilder(Material.BARRIER).displayName("§cBüro kaufen").lore("§7Du musst dir zuerst ein Büro kaufen!").create());
                }
            } else if (spawns.equals(PlayerSpawnLocation.SPAWN)) {
                setItem(InventorySlot.ROW_2_SLOT_6, new ItemBuilder(spawns.getMaterial()).displayName(spawns.getDisplayname()).lore("§7Setzte deinen Spawnpunkt beim Lobby spawn").create());

                if (playerSpawnLocation.equals(spawns)) {
                    setItem(InventorySlot.ROW_3_SLOT_6, new ItemBuilder(Material.INK_SACK, 1, 10).displayName("§a§lAktiviert").lore("§7§oKlicke um deinen Spawnpunkt", "§7§obei deinem letzten logout", "§7§oPunkt zu setzten").create(), e -> {
                        setData(lp, PlayerSpawnLocation.LAST_LOGIN);
                        player.playSound(player.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
                    });
                } else {
                    setItem(InventorySlot.ROW_3_SLOT_6, new ItemBuilder(Material.INK_SACK, 1, 1).displayName("§c§lDeaktiviert").lore("§7§oKlicke um deinen Spawnpunkt", "§7§obeim Lobby spawn", "§7§ozu setzten").create(), e -> {
                        lp.getSettings().setSpawnLocation(PlayerSpawnLocation.SPAWN.toString());
                        setData(lp, PlayerSpawnLocation.SPAWN);
                        player.playSound(player.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
                    });
                }
            } else if (spawns.equals(PlayerSpawnLocation.LAST_LOGIN)) {
                setItem(InventorySlot.ROW_2_SLOT_7, new ItemBuilder(spawns.getMaterial()).displayName(spawns.getDisplayname()).lore("§7Setzte deinen Spawnpunkt beim letzten logout Punkt").create());

                if (playerSpawnLocation.equals(spawns)) {
                    setItem(InventorySlot.ROW_3_SLOT_7, new ItemBuilder(Material.INK_SACK, 1, 10).displayName("§a§lAktiviert").create(), e -> {
                        setData(lp, PlayerSpawnLocation.LAST_LOGIN);
                        player.playSound(player.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
                    });
                } else {
                    setItem(InventorySlot.ROW_3_SLOT_7, new ItemBuilder(Material.INK_SACK, 1, 1).displayName("§c§lDeaktiviert").lore("§7§oKlicke um deinen Spawnpunkt", "§7§obei deinem letzten logout", "§7§oPunkt zu setzten").create(), e -> {
                        setData(lp, PlayerSpawnLocation.LAST_LOGIN);
                        player.playSound(player.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
                    });
                }
            }
        }

        openInventory();
    }

    private void setData(LobbyPlayer lobbyPlayer, PlayerSpawnLocation playerSpawnLocation) {
        lobbyPlayer.getSettings().setSpawnLocation(playerSpawnLocation.toString());
        lobbyPlayer.saveData();
        new PlayerSpawnLocationInventory(lobbyPlayer.bukkit());
    }

    private void setSilentData(LobbyPlayer lobbyPlayer, boolean spawnInSilentLobby) {
        lobbyPlayer.getSettings().setSpawnInSilentLobby(spawnInSilentLobby);
        lobbyPlayer.saveData();
        new PlayerSpawnLocationInventory(lobbyPlayer.bukkit());
    }
}
