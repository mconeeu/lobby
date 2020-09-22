package eu.mcone.lobby.inventory.settings.hotbar;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.player.HotbarItem;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.api.player.settings.LobbySettings;
import eu.mcone.lobby.inventory.settings.LobbySettingsInventory;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class LobbyHotbarSettingsInventory extends CoreInventory {

    public LobbyHotbarSettingsInventory(Player p) {
        super("§8» §c§lHotbar Items", p, InventorySlot.ROW_4, InventoryOption.FILL_EMPTY_SLOTS);
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);
        CorePlayer corePlayer = lp.getCorePlayer();
        LobbySettings settings = lp.getSettings();


        setItem(InventorySlot.ROW_2_SLOT_2, new ItemBuilder(Material.COMPASS, 1, 0).displayName("§f§lKompass").create());
        setItem(InventorySlot.ROW_3_SLOT_2, settings.getCompassItem().getItem().create(), e -> {

            new HotbarItemsInventory(p, settings.getCompassItem().getCategorys());
        });

        setItem(InventorySlot.ROW_2_SLOT_3, new ItemBuilder(Material.NETHER_STAR, 1, 0).displayName("§f§lLobby-Wechsler").create());
        setItem(InventorySlot.ROW_3_SLOT_3, settings.getLobbySwitcherItem().getItem().create(), e -> {
            new HotbarItemsInventory(p, settings.getLobbySwitcherItem().getCategorys());
        });

        setItem(InventorySlot.ROW_2_SLOT_4, new ItemBuilder(Material.STORAGE_MINECART, 1, 0).displayName("§f§lRucksack").create());
        setItem(InventorySlot.ROW_3_SLOT_4, settings.getBackpackItem().getItem().create(), e -> {
            new HotbarItemsInventory(p, settings.getBackpackItem().getCategorys());
        });

        setItem(InventorySlot.ROW_2_SLOT_5, HotbarItem.getProfile(corePlayer.getSkin()));
        setItem(InventorySlot.ROW_3_SLOT_5, settings.getProfileItem().getItem().create(), e -> {
            new HotbarItemsInventory(p, settings.getProfileItem().getCategorys());
        });


        setItem(InventorySlot.ROW_4_SLOT_9, BACK_ITEM, e ->
                new LobbySettingsInventory(p));

        openInventory();
    }
}
