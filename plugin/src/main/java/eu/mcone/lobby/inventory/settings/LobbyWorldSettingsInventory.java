package eu.mcone.lobby.inventory.settings;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.items.LobbyItem;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.api.player.settings.LobbySettings;
import eu.mcone.lobby.api.player.settings.SpawnPoint;
import eu.mcone.lobby.api.player.settings.SpawnVillage;
import eu.mcone.lobby.scheduler.WorldRealTimeScheduler;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class LobbyWorldSettingsInventory extends CoreInventory {

    public LobbyWorldSettingsInventory(Player p) {
        super("§8» §c§lWelt", p, InventorySlot.ROW_4, InventoryOption.FILL_EMPTY_SLOTS);
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);
        LobbySettings settings = lp.getSettings();

        setItem(InventorySlot.ROW_2_SLOT_2, new ItemBuilder(Material.WATCH, 1, 0).displayName("§f§lTag-Nacht Zyklus").create());
        if (settings.isRealTime()) {
            setItem(InventorySlot.ROW_3_SLOT_2, new ItemBuilder(Material.INK_SACK, 1, 10).displayName("§a§lEchtzeit").lore("§7§oKlicke um in der Lobby", "§7§oimmer Tag zu haben").create(), e -> {
                settings.setRealTime(false);
                setSettings(p, lp);

                p.resetPlayerTime();
            });
        } else {
            setItem(InventorySlot.ROW_3_SLOT_2, new ItemBuilder(Material.INK_SACK, 1, 1).displayName("§e§lImmer Tag").lore("§7§oKlicke um die Zeit in der", "§7§oLobby an die echte Zeit", "§7§oanzupassen").create(), e -> {
                settings.setRealTime(true);
                setSettings(p, lp);

                WorldRealTimeScheduler.setCurrentRealTime(lp);
            });
        }

        setItem(InventorySlot.ROW_2_SLOT_3, new ItemBuilder(Material.NETHER_STAR, 1, 0).displayName("§f§lSpawn Ort").create());
        setItem(InventorySlot.ROW_3_SLOT_3, settings.getSpawnPoint().getItem().create(), e ->

        {
            switch (settings.getSpawnPoint()) {
                case SPAWN: {
                    settings.setSpawnPoint(SpawnPoint.LAST_LOCATION);
                    setSettings(p, lp);
                    break;
                }
                case LAST_LOCATION: {
                    if (lp.hasLobbyItem(LobbyItem.OFFICE_CARD_BRONZE) || lp.hasLobbyItem(LobbyItem.OFFICE_CARD_SILVER) || lp.hasLobbyItem(LobbyItem.OFFICE_CARD_GOLD)) {
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

        setItem(InventorySlot.ROW_2_SLOT_4, new ItemBuilder(Material.LONG_GRASS, 1, 0).displayName("§f§lDorfspawn Ort").create());
        if (settings.getSpawnPoint().equals(SpawnPoint.SPAWN)) {
            setItem(InventorySlot.ROW_3_SLOT_4, settings.getSpawnVillage().getItem().create(), e -> {
                switch (settings.getSpawnVillage()) {
                    case RANDOM: {
                        settings.setSpawnVillage(SpawnVillage.RAISEN);
                        setSettings(p, lp);
                        break;
                    }
                    case RAISEN: {
                        settings.setSpawnVillage(SpawnVillage.SKYLECK);
                        setSettings(p, lp);
                        break;
                    }
                    case SKYLECK: {
                        settings.setSpawnVillage(SpawnVillage.RANDOM);
                        setSettings(p, lp);
                        break;
                    }
                }
            });
        } else {
            setItem(InventorySlot.ROW_3_SLOT_4, new ItemBuilder(Material.INK_SACK, 1, 8).displayName("§cNicht verfügbar").create());
        }


        setItem(InventorySlot.ROW_4_SLOT_9, BACK_ITEM, e ->
                new LobbySettingsInventory(p));

        openInventory();
    }

    private void setSettings(Player p, LobbyPlayer lp) {
        lp.saveData();
      LobbyPlugin.getInstance().getPlayerSounds().playSounds(p, Sound.CHICKEN_EGG_POP);
        new LobbyWorldSettingsInventory(p);
    }
}
