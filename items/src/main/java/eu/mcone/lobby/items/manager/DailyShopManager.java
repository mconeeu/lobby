package eu.mcone.lobby.items.manager;

import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.config.LobbyConfig;
import eu.mcone.lobby.api.enums.Category;
import eu.mcone.lobby.api.enums.Item;
import eu.mcone.lobby.api.enums.Level;
import lombok.Getter;
import net.minecraft.server.v1_8_R3.Items;
import org.bukkit.Bukkit;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class DailyShopManager implements Runnable {

    private LobbyConfig lobbyConfig;
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

        lobbyConfig = LobbyPlugin.getInstance().getLobbyConfig().parseConfig();

        if (lobbyConfig.getDailyShop() == 0) {
            lobbyConfig.setDailyShop(System.currentTimeMillis() / 1000);
            LobbyPlugin.getInstance().getLobbyConfig().save();
        }

        Date currentDate = new Date();
        refill = new GregorianCalendar(currentDate.getYear(), currentDate.getMonth(), currentDate.getDay(), 24, 0).getTime().getTime();

        Bukkit.getScheduler().runTaskTimerAsynchronously(LobbyPlugin.getInstance(), this, 0, 20 * 60);
    }

    @Override
    public void run() {
        if (refill >= lobbyConfig.getDailyShop()) {
            //Normal Items
            Random normalRandom = new Random(availableItemsNormal.size());

            for (int i = 0; i <= 6; i++) {
                dailyItems.add(availableItemsNormal.get(normalRandom.nextInt()));
            }

            //Epic
            Random epicRandom = new Random(availableItemsEpic.size());
            dailyItems.add(availableItemsEpic.get(epicRandom.nextInt()));

            lobbyConfig.setDailyShop(System.currentTimeMillis() / 1000);
            LobbyPlugin.getInstance().getLobbyConfig().save();
        }
    }
}
