package eu.mcone.lobby.api.player.hotbar.items.enums;

import eu.mcone.coresystem.api.bukkit.item.Skull;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;

@Getter
public enum SlotAmountEnum {

    FIRST(0, Skull.fromUrl(
            "http://textures.minecraft.net/texture/71bc2bcfb2bd3759e6b1e86fc7a79585e1127dd357fc202893f9de241bc9e530"
            , 1).toItemBuilder().displayName("§f1").create()),

    SECOND(1, Skull.fromUrl(
            "http://textures.minecraft.net/texture/4cd9eeee883468881d83848a46bf3012485c23f75753b8fbe8487341419847"
            , 1).toItemBuilder().displayName("§f2").create()),

    FIVE(4, Skull.fromUrl(
            "http://textures.minecraft.net/texture/6d57e3bc88a65730e31a14e3f41e038a5ecf0891a6c243643b8e5476ae2"
            , 1).toItemBuilder().displayName("§f5").create()),

    EIGHT(7, Skull.fromUrl(
            "http://textures.minecraft.net/texture/59194973a3f17bda9978ed6273383997222774b454386c8319c04f1f4f74c2b5"
            , 1).toItemBuilder().displayName("§f8").create()),

    NINE(8, Skull.fromUrl(
            "http://textures.minecraft.net/texture/e67caf7591b38e125a8017d58cfc6433bfaf84cd499d794f41d10bff2e5b840"
            , 1).toItemBuilder().displayName("§f9").create());

    private final int slot;
    private final ItemStack itemStack;

    SlotAmountEnum(int slot, ItemStack itemStack) {
        this.slot = slot;
        this.itemStack = itemStack;
    }

    public static SlotAmountEnum getBySlotInt(int slot) {
        for (SlotAmountEnum value : values()) {
            if (value.slot == slot) {
                return value;
            }
        }

        return null;
    }

}
