package eu.mcone.lobby.items.casino.numbers;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.facades.Msg;
import eu.mcone.coresystem.api.bukkit.facades.Sound;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.Skull;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import org.bukkit.entity.Player;

public class MoneyChooseInventory extends CoreInventory {

    public MoneyChooseInventory(Player player) {
        super("§8» §fWie viel?", player, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);
        CorePlayer corePlayer = CoreSystem.getInstance().getCorePlayer(player);

        setItem(InventorySlot.ROW_2_SLOT_3, Skull.fromUrl("http://textures.minecraft.net/texture/45bbaa2b27e0e2d8beb78d4e6cea3a6c927a2c143259a9c3cc87beddf78e466", 1).toItemBuilder()
                .displayName("§f500 Coins")
                .create(), e -> {
            if (corePlayer.getCoins() >= 500) {
                NumbersChooseInventory.chooseMoney.put(player, 500);
                new NumbersChooseInventory(player);
            } else {
                Sound.error(player);
                Msg.send(player, "§4Du hast nicht genügend Coins!");
            }
        });

        setItem(InventorySlot.ROW_2_SLOT_5, Skull.fromUrl("http://textures.minecraft.net/texture/45bbaa2b27e0e2d8beb78d4e6cea3a6c927a2c143259a9c3cc87beddf78e466", 1).toItemBuilder()
                .displayName("§f1000 Coins")
                .create(), e -> {
            if (corePlayer.getCoins() >= 1000) {
                NumbersChooseInventory.chooseMoney.put(player, 1000);
                new NumbersChooseInventory(player);
            } else {
                Sound.error(player);
                Msg.send(player, "§4Du hast nicht genügend Coins!");
            }
        });

        setItem(InventorySlot.ROW_2_SLOT_7, Skull.fromUrl("http://textures.minecraft.net/texture/45bbaa2b27e0e2d8beb78d4e6cea3a6c927a2c143259a9c3cc87beddf78e466", 1).toItemBuilder()
                .displayName("§f2000 Coins")
                .create(), e -> {
            if (corePlayer.getCoins() >= 2000) {
                NumbersChooseInventory.chooseMoney.put(player, 2000);
                new NumbersChooseInventory(player);
            } else {
                Sound.error(player);
                Msg.send(player, "§4Du hast nicht genügend Coins!");
            }
        });


        openInventory();
    }
}
