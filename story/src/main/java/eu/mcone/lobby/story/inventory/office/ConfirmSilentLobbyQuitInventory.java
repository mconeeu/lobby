/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.story.inventory.office;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class ConfirmSilentLobbyQuitInventory extends CoreInventory {

    public enum Target {
        OWNER, GUEST
    }

    public ConfirmSilentLobbyQuitInventory(Player p, Player invited, Target target, Runnable runnable) {
        super("§c§lSilentLobby verlassen?", target.equals(Target.OWNER) ? p : invited, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);

        setItem(
                InventorySlot.ROW_1_SLOT_5,
                new ItemBuilder(Material.TNT)
                        .displayName("§c§lSilentLobby verlassen")
                        .lore(target.equals(Target.OWNER)
                                ? new String[]{"§7§oDu musst die SilentLobby", "§7§overlassen um andere Spieler", "§7§oeinzuladen!"}
                                : new String[]{"§7§oDu musst die SilentLobby", "§7§overlassen um eine Büro Einladung", "§7§oanzunehmen!"})
                        .create()
        );

        setItem(
                InventorySlot.ROW_3_SLOT_4,
                new ItemBuilder(Material.INK_SACK, 1, DyeColor.RED.getDyeData())
                        .displayName("§c§lAbbrechen")
                        .lore("§7§oMenü schließen")
                        .create(),
                e -> p.closeInventory()
        );
        setItem(
                InventorySlot.ROW_3_SLOT_6,
                new ItemBuilder(Material.INK_SACK, 1, DyeColor.LIME.getDyeData())
                        .displayName("§a§lSilentLobby verlassen")
                        .lore(target.equals(Target.OWNER)
                                ? new String[]{"§f§o"+invited.getName()+"§7§o wird eingeladen"}
                                : new String[]{"§7§oBüro von §f§o"+p.getName()+"§7§o wird", "§7§obetreten"})
                        .create(),
                e -> runnable.run()
        );

        openInventory();
        System.out.println("opened inventory");
    }

}
