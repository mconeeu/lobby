package eu.mcone.lobby.inventory;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.lobby.api.LobbyPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class LobbyInventory extends CoreInventory {


    public LobbyInventory(Player p) {
        super("§8» §3§lLobby wechseln", p, InventorySlot.ROW_4, InventoryOption.FILL_EMPTY_SLOTS);

        if (p.hasPermission("lobby.silenthub")) {
            setItem(InventorySlot.ROW_2_SLOT_1, new ItemBuilder(Material.TNT, 1, 0).displayName("§f§lPrivate Lobby").create(),
                    e -> {
                        if (LobbyPlugin.getInstance().getSilentLobbyManager().isActivatedSilentHub(p)) {
                            p.sendMessage("§8[§7§l!§8] §fServer §8» §cDu befindest dich bereits auf diesem Server");
                        } else {
                            p.closeInventory();
                            LobbyPlugin.getInstance().getSilentLobbyManager().activateSilentLobby(p);
                        }
                    });

            setItem(InventorySlot.ROW_3_SLOT_1, new ItemBuilder(Material.INK_SACK, 1, 10)
                            .displayName("§a§lPrivate Lobby online")
                            .create(),

                    e -> {
                        if (LobbyPlugin.getInstance().getSilentLobbyManager().isActivatedSilentHub(p)) {
                            p.sendMessage("§8[§7§l!§8] §fServer §8» §cDu befindest dich bereits auf diesem Server");
                        } else {
                            p.closeInventory();
                            LobbyPlugin.getInstance().getSilentLobbyManager().activateSilentLobby(p);
                        }
                    });
        }
        setItem(InventorySlot.ROW_2_SLOT_2, new ItemBuilder(Material.GOLD_INGOT, 1, 0).displayName("§f§lPremium Lobby-1").create());
        setItem(InventorySlot.ROW_2_SLOT_3, new ItemBuilder(Material.GOLD_INGOT, 1, 0).displayName("§f§lPremium Lobby-2").create());

        setItem(InventorySlot.ROW_3_SLOT_2, new ItemBuilder(Material.INK_SACK, 1, 1).displayName("§c§lLobby offline").create());
        setItem(InventorySlot.ROW_3_SLOT_3, new ItemBuilder(Material.INK_SACK, 1, 1).displayName("§c§lLobby offline").create());


        setItem(InventorySlot.ROW_2_SLOT_5, new ItemBuilder(Material.IRON_INGOT, Bukkit.getOnlinePlayers().size(), 0)
                        .displayName("§fLobby-1")
                        .create(),

                e -> {
                    if (LobbyPlugin.getInstance().getSilentLobbyManager().isActivatedSilentHub(p)) {
                        LobbyPlugin.getInstance().getSilentLobbyManager().deactivateSilentLobby(p);
                        p.closeInventory();
                    } else {
                        p.sendMessage("§8[§7§l!§8] §fServer §8» §cDu befindest dich bereits auf diesem Server");
                    }
                });

        setItem(InventorySlot.ROW_2_SLOT_6, new ItemBuilder(Material.IRON_INGOT, 1, 0).displayName("§fLobby-2").create());

        setItem(InventorySlot.ROW_2_SLOT_7, new ItemBuilder(Material.IRON_INGOT, 1, 0).displayName("§fLobby-3").create());

        setItem(InventorySlot.ROW_2_SLOT_8, new ItemBuilder(Material.IRON_INGOT, 1, 0).displayName("§fLobby-4").create());


        setItem(InventorySlot.ROW_3_SLOT_5, new ItemBuilder(Material.INK_SACK, 1, 10).displayName("§a§lLobby online")
                        .create(),
                e -> {
                    if (LobbyPlugin.getInstance().getSilentLobbyManager().isActivatedSilentHub(p)) {
                        LobbyPlugin.getInstance().getSilentLobbyManager().deactivateSilentLobby(p);
                        p.closeInventory();
                    } else {
                        p.sendMessage("§8[§7§l!§8] §fServer §8» §cDu befindest dich bereits auf diesem Server");
                    }
                });

        setItem(InventorySlot.ROW_3_SLOT_6, new ItemBuilder(Material.INK_SACK, 1, 1).displayName("§c§lLobby offline").create());

        setItem(InventorySlot.ROW_3_SLOT_7, new ItemBuilder(Material.INK_SACK, 1, 1).displayName("§c§lLobby offline").create());

        setItem(InventorySlot.ROW_3_SLOT_8, new ItemBuilder(Material.INK_SACK, 1, 1).displayName("§c§lLobby offline").create());

        openInventory();
    }
}
