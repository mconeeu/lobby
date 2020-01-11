/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.inventory;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.enums.LobbyItem;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.api.player.LobbySettings;
import eu.mcone.lobby.api.player.SpawnPoint;
import eu.mcone.lobby.util.RealTimeUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class LobbySettingsInventory extends CoreInventory {

    public LobbySettingsInventory(Player p) {
        super("§8» §c§lLobby Einstellung", p, InventorySlot.ROW_4, InventoryOption.FILL_EMPTY_SLOTS);
        LobbyPlayer lp = LobbyPlugin.getInstance().getGamePlayer(p);
        LobbySettings settings = lp.getSettings();


        setItem(InventorySlot.ROW_2_SLOT_4, new ItemBuilder(Material.WATCH, 1, 0).displayName("§f§lTag-Nacht Zyklus").create());
        if (settings.isRealTime()) {
            setItem(InventorySlot.ROW_3_SLOT_4, new ItemBuilder(Material.INK_SACK, 1, 10).displayName("§a§lEchtzeit").lore("§7§oKlicke um in der Lobby", "§7§oimmer Tag zu haben").create(), e -> {
                settings.setRealTime(false);
                setSettings(p, lp);
            });
        } else {
            setItem(InventorySlot.ROW_3_SLOT_4, new ItemBuilder(Material.INK_SACK, 1, 1).displayName("§e§lImmer Tag").lore("§7§oKlicke um die Zeit in der", "§7§oLobby an die echte Zeit", "§7§oanzupassen").create(), e -> {
                settings.setRealTime(true);
                setSettings(p, lp);

                RealTimeUtil.setCurrentRealTime(lp);
            });
        }

        if (p.hasPermission("lobby.silenthub")) {
            setItem(InventorySlot.ROW_2_SLOT_5, new ItemBuilder(Material.TNT, 1, 0).displayName("§f§lIn privater Lobby joinen").create());
            if (settings.isSpawnInSilentLobby()) {
                setItem(InventorySlot.ROW_3_SLOT_5, new ItemBuilder(Material.INK_SACK, 1, 10).displayName("§a§lAktiviert").lore("§7§oKlicke um das Spawnen", "§7§oin der Privaten Lobby", "§7§ozu deaktivieren").create(), e -> {
                    settings.setSpawnInSilentLobby(false);
                    setSettings(p, lp);
                });
            } else {
                setItem(InventorySlot.ROW_3_SLOT_5, new ItemBuilder(Material.INK_SACK, 1, 1).displayName("§c§lDeaktiviert").lore("§7§oKlicke um das Spawnen", "§7§oin der Privaten Lobby", "§7§ozu aktivieren").create(), e -> {
                    settings.setSpawnInSilentLobby(true);
                    setSettings(p, lp);
                });
            }
        }

        setItem(InventorySlot.ROW_2_SLOT_6, new ItemBuilder(Material.NETHER_STAR, 1, 0).displayName("§f§lSpawn Ort").create());
        setItem(InventorySlot.ROW_3_SLOT_6, settings.getSpawnPoint().getItem().create(), e -> {
            switch (settings.getSpawnPoint()) {
                case SPAWN: {
                    settings.setSpawnPoint(SpawnPoint.LAST_LOCATION);
                    setSettings(p, lp);
                    break;
                }
                case LAST_LOCATION: {
                    if (LobbyItem.OFFICE_CARD_BRONZE.has(lp) || LobbyItem.OFFICE_CARD_SILVER.has(lp) || LobbyItem.OFFICE_CARD_GOLD.has(lp)) {
                        settings.setSpawnPoint(SpawnPoint.OFFICE);
                    } else {
                        settings.setSpawnPoint(SpawnPoint.SPAWN);
                    }

                    setSettings(p, lp);
                    break;
                }
                case OFFICE: {
                    settings.setSpawnPoint(SpawnPoint.SPAWN);
                    setSettings(p, lp);
                    break;
                }
            }
        });

        setItem(InventorySlot.ROW_4_SLOT_9, new ItemBuilder(Material.IRON_DOOR, 1, 0).displayName("§7§l↩ Zurück").create(), e -> Bukkit.dispatchCommand(p, "profile"));

        openInventory();
    }

    private void setSettings(Player p, LobbyPlayer lp) {
        lp.saveData();
        p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
        new LobbySettingsInventory(p);
    }

}

