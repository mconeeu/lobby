package eu.mcone.lobby.items.manager;

import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.enums.Category;
import eu.mcone.lobby.api.enums.Item;
import eu.mcone.lobby.api.enums.Level;
import lombok.Getter;
import org.bukkit.Bukkit;

import java.util.*;

public class DailyShopManager implements Runnable {

    @Getter
    private long refill;
    @Getter
    private List<Item> dailyItems;

    private List<Item> availableItemsNormal, availableItemsEpic;

    public DailyShopManager() {
        dailyItems = new ArrayList<>();
        availableItemsNormal = new ArrayList<>();
        availableItemsEpic = new ArrayList<>();

        for (Item item : Item.values()) {
            if (item.getCategory() != null) {
                if (item.getCategory().equals(Category.TRAIL)
                        || item.getCategory().equals(Category.ANIMAL)
                        || item.getCategory().equals(Category.HAT)
                        || item.getCategory().equals(Category.OUTFITS)
                        || item.getCategory().equals(Category.GADGET)) {

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

        //Normal Items
        Random normalRandom = new Random();

        int i = 1;
        while (i <= 9) {
            Item randomItem = availableItemsNormal.get(normalRandom.nextInt(availableItemsNormal.size()));
            if (!dailyItems.contains(randomItem)) {
                dailyItems.add(randomItem);
                i++;
            }
        }

        //Epic
        Random epicRandom = new Random();
        dailyItems.add(availableItemsEpic.get(epicRandom.nextInt(availableItemsEpic.size())));
    }
}
