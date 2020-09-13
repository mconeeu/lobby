package eu.mcone.lobby.items.casino.numbers;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.Skull;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.HashSet;

public class NumbersChooseInventory extends CoreInventory {

    public static HashSet<Player> isInGame = new HashSet<>();
    public static HashMap<Player, Integer> chooseMoney = new HashMap<>();
    public static HashMap<Player, Integer> Game = new HashMap<>();

    public NumbersChooseInventory(Player player) {
        super("§8» §f§lWelche Zahl?", player, InventorySlot.ROW_5, InventoryOption.FILL_EMPTY_SLOTS);

        setItem(InventorySlot.ROW_2_SLOT_4, Skull.fromUrl("http://textures.minecraft.net/texture/71bc2bcfb2bd3759e6b1e86fc7a79585e1127dd357fc202893f9de241bc9e530", 1).toItemBuilder()
                .displayName("§f1")
                .create(), e -> {
            Game.put(player, 1);
            new NumbersFinishInventory(player);
        });

        setItem(InventorySlot.ROW_2_SLOT_5, Skull.fromUrl("http://textures.minecraft.net/texture/4cd9eeee883468881d83848a46bf3012485c23f75753b8fbe8487341419847", 1).toItemBuilder()
                .displayName("§f2")
                .create(), e -> {
            Game.put(player, 2);
            new NumbersFinishInventory(player);
        });

        setItem(InventorySlot.ROW_2_SLOT_6, Skull.fromUrl("http://textures.minecraft.net/texture/1d4eae13933860a6df5e8e955693b95a8c3b15c36b8b587532ac0996bc37e5", 1).toItemBuilder()
                .displayName("§f3")
                .create(), e -> {
            Game.put(player, 3);
            new NumbersFinishInventory(player);
        });

        setItem(InventorySlot.ROW_3_SLOT_4, Skull.fromUrl("http://textures.minecraft.net/texture/d2e78fb22424232dc27b81fbcb47fd24c1acf76098753f2d9c28598287db5", 1).toItemBuilder()
                .displayName("§f4")
                .create(), e -> {
            Game.put(player, 4);
            new NumbersFinishInventory(player);
        });

        setItem(InventorySlot.ROW_3_SLOT_5, Skull.fromUrl("http://textures.minecraft.net/texture/6d57e3bc88a65730e31a14e3f41e038a5ecf0891a6c243643b8e5476ae2", 1).toItemBuilder()
                .displayName("§f5")
                .create(), e -> {
            Game.put(player, 5);
            new NumbersFinishInventory(player);
        });

        setItem(InventorySlot.ROW_3_SLOT_6, Skull.fromUrl("http://textures.minecraft.net/texture/334b36de7d679b8bbc725499adaef24dc518f5ae23e716981e1dcc6b2720ab", 1).toItemBuilder()
                .displayName("§f6")
                .create(), e -> {
            Game.put(player, 6);
            new NumbersFinishInventory(player);
        });

        setItem(InventorySlot.ROW_4_SLOT_4, Skull.fromUrl("http://textures.minecraft.net/texture/6db6eb25d1faabe30cf444dc633b5832475e38096b7e2402a3ec476dd7b9", 1).toItemBuilder()
                .displayName("§f7")
                .create(), e -> {
            Game.put(player, 7);
            new NumbersFinishInventory(player);
        });

        setItem(InventorySlot.ROW_4_SLOT_5, Skull.fromUrl("http://textures.minecraft.net/texture/59194973a3f17bda9978ed6273383997222774b454386c8319c04f1f4f74c2b5", 1).toItemBuilder()
                .displayName("§f8")
                .create(), e -> {
            Game.put(player, 8);
            new NumbersFinishInventory(player);
        });

        setItem(InventorySlot.ROW_4_SLOT_6, Skull.fromUrl("http://textures.minecraft.net/texture/e67caf7591b38e125a8017d58cfc6433bfaf84cd499d794f41d10bff2e5b840", 1).toItemBuilder()
                .displayName("§f9")
                .create(), e -> {
            Game.put(player, 9);
            new NumbersFinishInventory(player);
        });

        openInventory();

    }
}
