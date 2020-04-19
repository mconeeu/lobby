/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.items.inventory.smuggler;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.item.Skull;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.enums.BankProgress;
import eu.mcone.lobby.api.enums.LobbyItem;
import eu.mcone.lobby.api.player.LobbyPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class SmugglerInventory extends CoreInventory {


    public SmugglerInventory(Player p) {
        super("§8» §7§lSchmugler", p, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);

        setItem(InventorySlot.ROW_1_SLOT_5, Skull.fromUrl("http://textures.minecraft.net/texture/5163dafac1d91a8c91db576caac784336791a6e18d8f7f62778fc47bf146b6", 1).toItemBuilder().displayName("§7§lSchmuggler").lore("§7§oBeim Schmuggler kannst ausgewählte", "§7§oWare für deinen Rucksack kaufen.", "§7§ooder geschmugglte Kisten", "§7§oAber pass auf er kann auch kappute Ware verkaufen!").create());


        if (lp.getBankprogressId() == BankProgress.SMUGGLER.getId()) {
            setItem(InventorySlot.ROW_2_SLOT_6, new ItemBuilder(Material.EMPTY_MAP).displayName("§f§lPlan der Bank").lore("", "§6Kosten 20 Coins").create(), e -> {
                p.closeInventory();

                if (lp.getCorePlayer().getCoins() - 20 >= 0) {
                    if (!lp.hasLobbyItem(LobbyItem.BANK_MAP)) {
                        lp.getCorePlayer().removeCoins(20);
                        lp.addLobbyItem(LobbyItem.BANK_MAP);
                        p.sendMessage("§8[§7§l!§8] §cKnopf im Ohr §8» §fJohn§8|§7 Perfekt du hast die Karte komm wieder zum Büro dann kann ich dir denn nächsten Schritt sagen!");
                        lp.setBankProgress(BankProgress.CUTTER);
                    } else {
                        p.sendMessage("§4Du hast das Item bereits!");
                    }
                } else {
                    p.sendMessage("§cDu hast nicht genügend Coins!");
                }
            });

            setItem(InventorySlot.ROW_2_SLOT_4, new ItemBuilder(Material.CHEST, 1, 0).displayName("§6Kisten kaufen").create(),
                    e -> new ChestBuyInventorySmuggler(p));

        } else {
            setItem(InventorySlot.ROW_2_SLOT_5, new ItemBuilder(Material.CHEST, 1, 0).displayName("§6Kisten kaufen").create(),
                    e -> new ChestBuyInventorySmuggler(p));
        }


        openInventory();
    }
}



