/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.inventory;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.gamemode.Gamemode;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class ServerInventory extends CoreInventory {

    public ServerInventory(Player p, Gamemode modus) {
        super("§8» "+modus.getLabel()+" §8| §8Server", p, 54);

        CoreSystem.getInstance().getChannelHandler().createGetRequest(getPlayer(), servers -> {
            for (int i = 0; i <= 17; i++) {
                setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
            }
            for (int i = 45; i <= 53; i++) {
                setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
            }
            setItem(4, new ItemBuilder(modus.getItem(), 1, 0).displayName(modus.getLabel()).create());

            int i = 17;
            for (String server : servers.split(";")) {
                i++;
                if (i > 45) break;

                String[] data = server.split(":");
                if (data.length < 2) continue;

                setItem(i, new ItemBuilder(Material.EMERALD, 1, 0).displayName("§f§l"+data[0]).lore("§7"+data[1]+" Spieler online", "", "§8» §c§nRechtsklick§8 | §7§oJoinen").create(), e -> {
                    CoreSystem.getInstance().getChannelHandler().createSetRequest(p, "CONNECT", data[0]);
                    p.closeInventory();
                });

                openInventory();
            }
        }, "SERVERS", modus.toString());
    }

}
