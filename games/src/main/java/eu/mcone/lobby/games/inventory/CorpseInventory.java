/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.games.inventory;

import eu.mcone.coresystem.api.bukkit.facades.Sound;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.gameapi.api.GameAPI;
import eu.mcone.gameapi.api.backpack.defaults.DefaultItem;
import eu.mcone.gameapi.api.player.GamePlayer;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.games.jumpnrun.JumpNRunLobbyGame;
import org.bukkit.entity.Player;

public class CorpseInventory extends CoreInventory {

    public CorpseInventory(Player p, JumpNRunLobbyGame game) {
        super("§8» §3§lLeiche", p, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);
        GamePlayer lp = GameAPI.getInstance().getGamePlayer(p);

        setItem(InventorySlot.ROW_2_SLOT_5, DefaultItem.HEAD_SECRET_STRIPCLUB.getItemStack(), e -> {
            if (!lp.hasDefaultItem(DefaultItem.HEAD_SECRET_STRIPCLUB)) {
                lp.addDefaultItem(DefaultItem.HEAD_SECRET_STRIPCLUB);
                LobbyPlugin.getInstance().getMessenger().send(p, "§aDu hast den alten Kopf von §fKirpha aufgenommen!");
            } else {
                Sound.error(player);
                LobbyPlugin.getInstance().getMessenger().send(p, "§cDu besitzt diese Item bereits!");
            }

            game.finishGame(p);
            p.closeInventory();
        });

        openInventory();
    }
}
