package eu.mcone.lobby.items.manager;

import eu.mcone.gameapi.api.backpack.Level;
import eu.mcone.gameapi.api.backpack.defaults.DefaultCategory;
import eu.mcone.gameapi.api.backpack.defaults.DefaultItem;
import eu.mcone.lobby.api.LobbyPlugin;
import lombok.Getter;
import org.bukkit.Bukkit;

import java.util.*;

public class DailyShopManager implements Runnable {

    @Getter
    private long refill;
    @Getter
    private final List<DefaultItem> dailyItems;

    private final List<DefaultItem> availableItemsNormal, availableItemsEpic;

    public DailyShopManager() {
        dailyItems = new ArrayList<>();
        availableItemsNormal = new ArrayList<>();
        availableItemsEpic = new ArrayList<>();

        for (DefaultItem item : DefaultItem.values()) {
            if (item.getCategory() != null) {
                if (!item.getCategory().equals(DefaultCategory.EXCLUSIVE)) {
                    if (item.getLevel().equals(Level.USUAL) || item.getLevel().equals(Level.UNUSUAL)) {
                        availableItemsNormal.add(item);
                    } else {
                        availableItemsEpic.add(item);
                    }
                }
            }
        }

        getRandomItems();
        refill = calculateRefill();

        Bukkit.getScheduler().runTaskTimerAsynchronously(LobbyPlugin.getInstance(), this, 0, 20 * 60);
    }

    @Override
    public void run() {
        if (System.currentTimeMillis() / 1000 >= refill) {
            getRandomItems();
            refill = calculateRefill();
        }
    }

    private long calculateRefill() {
        Calendar calendar = Calendar.getInstance(Locale.GERMANY);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTimeInMillis();
    }

    private void getRandomItems() {
        dailyItems.clear();

        int i = 1;
        while (i <= 9) {
            DefaultItem randomItem = availableItemsNormal.get(new Random().nextInt(availableItemsNormal.size()));

            if (!dailyItems.contains(randomItem)) {
                dailyItems.add(randomItem);
                i++;
            }
        }

        dailyItems.add(availableItemsEpic.get(new Random().nextInt(availableItemsEpic.size())));
    }
}
