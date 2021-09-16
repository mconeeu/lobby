/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.inventory.modify;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.menu.MenuInventory;
import eu.mcone.coresystem.api.bukkit.inventory.modify.CoreInventoryInitializeEntry;
import eu.mcone.coresystem.api.bukkit.inventory.modify.CoreInventoryModifier;
import eu.mcone.coresystem.api.bukkit.inventory.profile.ProfileInventory;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.inventory.LobbyProfileInventory;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class ProfileInventoryModifier implements CoreInventoryModifier {

    @Override
    public void onInitialize(CoreInventoryInitializeEntry entry) {
        entry.size(
                MenuInventory.getInvSize(ProfileInventory.MAX_ITEMS + 2)
        );
    }

    @Override
    public void onCreate(CoreInventory coreInv, Player p) {
        MenuInventory inv = (MenuInventory) coreInv;
        inv.setMaxItems(inv.getMaxItems()+2);

        inv.addMenuItem(
                new ItemBuilder(Material.GRASS, 1, 0)
                        .displayName("§3§lLobby")
                        .lore(
                                "§7§oWähle zwischen Lobbyeinstellungen",
                                "§7§oSecrets oder dein Story Fortschritt",
                                "",
                                "§8» §f§nLinksklick§8 | §7§oAnzeigen")
                        .create(),
                e -> new LobbyProfileInventory(p)
        );

        inv.addMenuItem(
                new ItemBuilder(Material.CHEST, 1, 0)
                        .displayName("§e§lModifizierte Inventare")
                        .lore(
                                "§7§oModifiziere Shop-Inventare aus",
                                "§7§oallen Spielmodi",
                                "",
                                "§8» §f§nLinksklick§8 | §7§oAnzeigen"
                        )
                        .create(),
                e -> LobbyPlugin.getInstance().getInventoryModificationManager().openGamemodeModificationInventory(p)
        );
    }

}
