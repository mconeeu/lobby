/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.pets.inventory.backpack;

import eu.mcone.gamesystem.api.lobby.backpack.BackpackInventory;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.enums.Category;
import eu.mcone.lobby.api.enums.Item;
import eu.mcone.lobby.pets.LobbyPets;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class AnimalInventory extends BackpackInventory {

    public AnimalInventory(Player p) {
        super(p);

        for (Item item : Item.values()) {
            if (item.hasCategory() && item.getCategory().equals(Category.ANIMAL) && playerHasItem(item)) {
                addItem(item.getItemStack(), e -> {
                    p.playSound(p.getLocation(), Sound.ANVIL_USE, 7,7);
                    p.closeInventory();
                    p.sendMessage("§8[§7§l!§8] §fRucksack §8» §2Du hast dein §a"+item.getName()+"§2 erfolgreich zu dir gerufen!");


                    Bukkit.getScheduler().runTask(LobbyPlugin.getInstance(), () -> LobbyPets.getInstance().spawnPet(p, item));
                });
            }
        }
    }

}
