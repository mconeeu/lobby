package eu.mcone.lobby.items.casino.numbers;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.items.casino.CasinoMainInventory;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.Random;

public class NumbersFinsihInventory extends CoreInventory {

    public NumbersFinsihInventory(Player player) {
        super("§8» §fDie Gewinnerzahl", player, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);


        Bukkit.getScheduler().runTaskLater(LobbyPlugin.getInstance(), () -> {
            setItem(InventorySlot.ROW_2_SLOT_5, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 14).displayName("§fLaden..").create());
            player.playSound(player.getLocation(), Sound.NOTE_PLING, 1, 1);
            Bukkit.getScheduler().runTaskLater(LobbyPlugin.getInstance(), () -> {
                setItem(InventorySlot.ROW_2_SLOT_5, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 4).displayName("§fLaden...").create());
                player.playSound(player.getLocation(), Sound.NOTE_PLING, 1, 1);
                Bukkit.getScheduler().runTaskLater(LobbyPlugin.getInstance(), () -> {
                    setItem(InventorySlot.ROW_2_SLOT_5, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 3).displayName("§fLaden.").create());
                    player.playSound(player.getLocation(), Sound.NOTE_PLING, 1, 1);
                    player.playSound(player.getLocation(), Sound.NOTE_STICKS, 1, 1);
                    Bukkit.getScheduler().runTaskLater(LobbyPlugin.getInstance(), () -> {
                        int win = getRandomNumberInRange(1, 9);

                        if (NumbersChooseInventory.isInGame.contains(player)) {
                            if (NumbersChooseInventory.Game.containsValue(win) && NumbersChooseInventory.Game.containsKey(player)) {
                                int addMoney = NumbersChooseInventory.chooseMoney.get(player);

                                setItem(InventorySlot.ROW_2_SLOT_5, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 13).displayName("§fGewonnen")
                                        .lore("§7Du hast die Zahl", "§f" + win + "§7 erfolgreich erraten")
                                        .create());

                                player.playSound(player.getLocation(), Sound.LEVEL_UP, 1, 1);

                                CorePlayer corePlayer = CoreSystem.getInstance().getCorePlayer(player);
                                corePlayer.addCoins(addMoney * 8);

                                LobbyPlugin.getInstance().getMessenger().send(player, "§aDu hast §f" + addMoney * 8 + "§a gewonnen!");
                            } else {
                                player.playSound(player.getLocation(), Sound.WITHER_DEATH, 1, 1);
                                player.playSound(player.getLocation(), Sound.NOTE_STICKS, 1, 1);

                                CorePlayer corePlayer = CoreSystem.getInstance().getCorePlayer(player);
                                corePlayer.removeCoins(NumbersChooseInventory.chooseMoney.get(player));

                                setItem(InventorySlot.ROW_2_SLOT_5, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 14).displayName("§cVerloren").lore("§7Die Zahl die gewonnen ", " §7hat war: §f" + win).create());

                                LobbyPlugin.getInstance().getMessenger().send(player, "§8[§a-" + NumbersChooseInventory.chooseMoney.get(player) + " Coins§8]");
                            }

                            NumbersChooseInventory.isInGame.remove(player);

                            setItem(InventorySlot.ROW_3_SLOT_9,
                                    new ItemBuilder(Material.IRON_DOOR, 1, 0).displayName("§7Zurück").create(),
                                    e -> new CasinoMainInventory(player)
                            );
                        }
                    }, 10);
                }, 25);
            }, 35);
        }, 5);


        openInventory();
    }

    private static int getRandomNumberInRange(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
