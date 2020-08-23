/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.items.inventory.chestopening;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.item.Skull;
import eu.mcone.coresystem.api.core.exception.CoreException;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.api.player.settings.SpawnVillage;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class ChestInfoInventory extends CoreInventory {

    public ChestInfoInventory(Player p) {
        super("§8» §e§lDeine Kisten", p, InventorySlot.ROW_3);
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);

        setItem(InventorySlot.ROW_2_SLOT_3, new ItemBuilder(Material.ENDER_PORTAL_FRAME).displayName("§f§lZum Chestopening teleportieren").create(), e -> {
            p.closeInventory();
            if (lp.getSettings().getSpawnVillage().equals(SpawnVillage.SKYLECK)) {
                LobbyWorld.ONE_ISLAND.getWorld().teleport(p, "chest-opening2");
            } else if (lp.getSettings().getSpawnVillage().equals(SpawnVillage.RAISEN)) {
                LobbyWorld.ONE_ISLAND.getWorld().teleport(p, "chest-opening");
            } else {
                LobbyWorld.ONE_ISLAND.getWorld().teleport(p, "chest-opening");
            }
        });

        setItem(InventorySlot.ROW_2_SLOT_5, new ItemBuilder(Material.CHEST, 1, 0).displayName("§7Du hast §e§l" + lp.getChests() + " Kisten").create());

        try {
            setItem(InventorySlot.ROW_2_SLOT_7, new Skull(CoreSystem.getInstance().getPlayerUtils().getSkinFromSkinDatabase("merchant"), 1).toItemBuilder().displayName("§f§lZum Händler teleportieren").create(), e -> {
                p.closeInventory();
                if (lp.getSettings().getSpawnVillage().equals(SpawnVillage.SKYLECK)) {
                    LobbyWorld.ONE_ISLAND.getWorld().teleport(p, "merchant2");
                } else if (lp.getSettings().getSpawnVillage().equals(SpawnVillage.RAISEN)) {
                    LobbyWorld.ONE_ISLAND.getWorld().teleport(p, "merchant");
                } else {
                    LobbyWorld.ONE_ISLAND.getWorld().teleport(p, "merchant");
                }
            });
        } catch (CoreException e) {
            e.printStackTrace();
        }

        openInventory();
    }

}



