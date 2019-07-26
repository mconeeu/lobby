/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.items.inventory.smuggler;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.item.Skull;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class SmugglerInventory extends CoreInventory {


    public SmugglerInventory(Player p) {
        super("§8» §7§lSchmuggler §8| §fSchmuggle Ware",p,9*3,InventoryOption.FILL_EMPTY_SLOTS);

        setItem(4, Skull.fromUrl("http://textures.minecraft.net/texture/5163dafac1d91a8c91db576caac784336791a6e18d8f7f62778fc47bf146b6", 1).toItemBuilder().displayName("§7§lSchmuggler").lore("§7§oBeim Schmuggler kannst ausgewählte", "§7§oWare für deinen Rucksack kaufen.", "§7§ooder geschmugglte Kisten", "§7§oAber pass auf er kann auch kappute Ware verkaufen!").create());

        setItem(17 - 1, new ItemBuilder(Material.CHEST, 1, 0).displayName("§6Kisten kaufen").create(),
                e -> new ChestBuyInventorySmuggler(p));

        setItem(23 - 1, new ItemBuilder(Material.CLAY_BALL, 1, 0).displayName("§cMaterialien kaufen").create(),
                e -> new MaterialBuyInventorySmuggler(p));

        setItem(11 - 1, new ItemBuilder(Material.ANVIL, 1, 0).displayName("§cItems kaufen").create(),
                e -> new ItemsBuyInventorySmuggler(p));

        openInventory();
    }
}



