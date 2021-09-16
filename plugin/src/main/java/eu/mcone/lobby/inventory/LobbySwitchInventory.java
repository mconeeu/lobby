package eu.mcone.lobby.inventory;

import eu.mcone.coresystem.api.bukkit.facades.Msg;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.lobby.Lobby;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.story.LobbyStory;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;

public class LobbySwitchInventory extends CoreInventory {

    public LobbySwitchInventory(Player p) {
        super("§8» §3§lLobby wechseln", p, InventorySlot.ROW_4, InventoryOption.FILL_EMPTY_SLOTS);

        if (LobbyStory.getInstance().getOfficeManager().isInOffice(p)) {
            Msg.send(p, "§4Du darfst im §cOffice §4keinen §cLobby §4wechsel machen!");
            p.closeInventory();
            return;
        }

        if (p.hasPermission("lobby.silenthub")) {
            if (LobbyPlugin.getInstance().getVanishManager().isInSilentLobby(p)) {
                setItem(
                        InventorySlot.ROW_2_SLOT_1,
                        new ItemBuilder(Material.TNT, 1, 0)
                                .enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1)
                                .itemFlags(ItemFlag.HIDE_ENCHANTS)
                                .displayName("§f§lPrivate Lobby")
                                .create(),
                        e -> p.sendMessage("§8[§7§l!§8] §fServer §8» §cDu befindest dich bereits auf diesem Server")
                );

                setItem(
                        InventorySlot.ROW_3_SLOT_1,
                        new ItemBuilder(Material.INK_SACK, 1, 10)
                                .displayName("§a§lPrivate Lobby online")
                                .create(),
                        e -> {
                            p.closeInventory();
                            p.sendMessage("§8[§7§l!§8] §fServer §8» §cDu befindest dich bereits auf diesem Server");
                        }
                );

                setItem(
                        InventorySlot.ROW_2_SLOT_5,
                        new ItemBuilder(Material.IRON_INGOT, Bukkit.getOnlinePlayers().size() - Lobby.getSystem().getVanishManager().getSilentLobbyPlayers().size(), 0)
                                .displayName("§fLobby-1")
                                .create(),
                        e -> {
                            LobbyPlugin.getInstance().getVanishManager().quitSilentLobby(p);
                            p.closeInventory();
                        }
                );
            } else {
                setItem(
                        InventorySlot.ROW_2_SLOT_1,
                        new ItemBuilder(Material.TNT, 1, 0)
                                .displayName("§f§lPrivate Lobby")
                                .create(),
                        e -> {
                            p.closeInventory();
                            LobbyPlugin.getInstance().getVanishManager().joinSilentLobby(p);
                        }
                );

                setItem(
                        InventorySlot.ROW_3_SLOT_1,
                        new ItemBuilder(Material.INK_SACK, 1, 10)
                                .displayName("§a§lPrivate Lobby online")
                                .create(),
                        e -> {
                            p.closeInventory();
                            LobbyPlugin.getInstance().getVanishManager().joinSilentLobby(p);
                        }
                );

                setItem(
                        InventorySlot.ROW_2_SLOT_5,
                        new ItemBuilder(Material.IRON_INGOT, Bukkit.getOnlinePlayers().size(), 0)
                                .enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1)
                                .itemFlags(ItemFlag.HIDE_ENCHANTS)
                                .displayName("§fLobby-1")
                                .create(),
                        e -> p.sendMessage("§8[§7§l!§8] §fServer §8» §cDu befindest dich bereits auf diesem Server")
                );
            }
        } else {
            setItem(
                    InventorySlot.ROW_2_SLOT_5,
                    new ItemBuilder(Material.IRON_INGOT, Bukkit.getOnlinePlayers().size() - Lobby.getSystem().getVanishManager().getSilentLobbyPlayers().size(), 0)
                            .displayName("§fLobby-1")
                            .create(),
                    e -> {
                        p.sendMessage("§8[§7§l!§8] §fServer §8» §cDu befindest dich bereits auf diesem Server");
                    }
            );
        }

        setItem(InventorySlot.ROW_2_SLOT_2, new ItemBuilder(Material.GOLD_INGOT, 1, 0).displayName("§f§lPremium Lobby-1").create());
        setItem(InventorySlot.ROW_2_SLOT_3, new ItemBuilder(Material.GOLD_INGOT, 1, 0).displayName("§f§lPremium Lobby-2").create());

        setItem(InventorySlot.ROW_3_SLOT_2, new ItemBuilder(Material.INK_SACK, 1, 1).displayName("§c§lLobby offline").create());
        setItem(InventorySlot.ROW_3_SLOT_3, new ItemBuilder(Material.INK_SACK, 1, 1).displayName("§c§lLobby offline").create());


        setItem(InventorySlot.ROW_2_SLOT_6, new ItemBuilder(Material.IRON_INGOT, 1, 0).displayName("§fLobby-2").create());
        setItem(InventorySlot.ROW_2_SLOT_7, new ItemBuilder(Material.IRON_INGOT, 1, 0).displayName("§fLobby-3").create());
        setItem(InventorySlot.ROW_2_SLOT_8, new ItemBuilder(Material.IRON_INGOT, 1, 0).displayName("§fLobby-4").create());


        setItem(
                InventorySlot.ROW_3_SLOT_5,
                new ItemBuilder(Material.INK_SACK, 1, 10)
                        .displayName("§a§lLobby online")
                        .create(),
                e -> {
                    if (LobbyPlugin.getInstance().getVanishManager().isInSilentLobby(p)) {
                        LobbyPlugin.getInstance().getVanishManager().quitSilentLobby(p);
                        p.closeInventory();
                    } else {
                        p.sendMessage("§8[§7§l!§8] §fServer §8» §cDu befindest dich bereits auf diesem Server");
                    }
                }
        );

        setItem(InventorySlot.ROW_3_SLOT_6, new ItemBuilder(Material.INK_SACK, 1, 1).displayName("§c§lLobby offline").create());
        setItem(InventorySlot.ROW_3_SLOT_7, new ItemBuilder(Material.INK_SACK, 1, 1).displayName("§c§lLobby offline").create());
        setItem(InventorySlot.ROW_3_SLOT_8, new ItemBuilder(Material.INK_SACK, 1, 1).displayName("§c§lLobby offline").create());

        openInventory();
    }
}
