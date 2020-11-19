package eu.mcone.lobby.items.casino.numbers;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.facades.Sound;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.items.casino.CasinoMainInventory;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Random;

public class NumbersFinishInventory extends CoreInventory {

    public NumbersFinishInventory(Player p) {
        super("§8» §fDie Gewinnerzahl", p, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);

        Bukkit.getScheduler().runTaskLater(LobbyPlugin.getInstance(), () -> {
            setItem(InventorySlot.ROW_2_SLOT_5, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 14).displayName("§fLaden..").create());
            Sound.done(p);
            Sound.done(p);

            Bukkit.getScheduler().runTaskLater(LobbyPlugin.getInstance(), () -> {
                setItem(InventorySlot.ROW_2_SLOT_5, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 4).displayName("§fLaden...").create());
                Sound.done(p);

                Bukkit.getScheduler().runTaskLater(LobbyPlugin.getInstance(), () -> {
                    setItem(InventorySlot.ROW_2_SLOT_5, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 3).displayName("§fLaden.").create());
                    Sound.done(p);
                    Sound.click(p);

                    Bukkit.getScheduler().runTaskLater(LobbyPlugin.getInstance(), () -> {
                        int win = getRandomNumberInRange(1, 9);

                        if (NumbersChooseInventory.isInGame.contains(p)) {
                            if (NumbersChooseInventory.Game.containsValue(win) && NumbersChooseInventory.Game.containsKey(p)) {
                                int addMoney = NumbersChooseInventory.chooseMoney.get(p);

                                setItem(InventorySlot.ROW_2_SLOT_5, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 13).displayName("§fGewonnen")
                                        .lore("§7Du hast die Zahl", "§f" + win + "§7 erfolgreich erraten")
                                        .create());

                                Sound.success(p);

                                CorePlayer corePlayer = CoreSystem.getInstance().getCorePlayer(p);
                                corePlayer.addCoins(addMoney * 8);

                                LobbyPlugin.getInstance().getMessenger().send(p, "§aDu hast §f" + addMoney * 8 + "§a gewonnen!");
                            } else {
                                Sound.epic(p);

                                CorePlayer corePlayer = CoreSystem.getInstance().getCorePlayer(p);
                                corePlayer.removeCoins(NumbersChooseInventory.chooseMoney.get(p));

                                setItem(InventorySlot.ROW_2_SLOT_5, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 14).displayName("§cVerloren").lore("§7Die Zahl die gewonnen ", " §7hat war: §f" + win).create());

                                LobbyPlugin.getInstance().getMessenger().send(p, "§8[§a-" + NumbersChooseInventory.chooseMoney.get(p) + " Coins§8]");
                            }

                            NumbersChooseInventory.isInGame.remove(p);

                            setItem(InventorySlot.ROW_3_SLOT_9,
                                    BACK_ITEM,
                                    e -> new CasinoMainInventory(p)
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
