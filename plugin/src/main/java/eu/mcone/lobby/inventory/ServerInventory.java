/*
 * Copyright (c) 2017 - 2018 Dominik Lippl, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.inventory;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class ServerInventory extends CoreInventory {

    public enum Gamemode {
        BEDWARS("BW", "§c§lBedwars", "§8» §c§lBedwars", Material.BED),
        SKYPVP("SP", "§9§lSkyPvP", "§8» §9§lSkyPvP", Material.DIAMOND_SWORD),
        KNOCKIT("KI", "§a§lKnockIT", "§8» §a§lKnockIT", Material.STICK),
        BUILD("BU", "§e§lBuild", "§8» §e§lBuild", Material.GRASS),
        MINEWAR("MW", "§5§lMinewar", "§8» §5§lMinewar", Material.IRON_PICKAXE);

        private String id, name, npcName;
        private Material item;

        Gamemode(String id, String name, String npcName, Material item) {
            this.id = id;
            this.name = name;
            this.npcName = npcName;
            this.item = item;
        }

        public static Gamemode getGamemodeByNpcName(String npcName) {
            for (Gamemode sm : values()) {
                if (sm.getNpcName().equals(npcName)) {
                    return sm;
                }
            }

            return null;
        }

        public String getNpcName() {
            return npcName;
        }

        public String getName() {
            return name;
        }

        public String getId() {
            return id;
        }

        public Material getItem() {
            return item;
        }

    }

    public ServerInventory(Player p, Gamemode modus) {
        super(modus.getNpcName()+" §8| §8Server", p, 54);

        CoreSystem.getInstance().getChannelHandler().createGetRequest(player, servers -> {
            for (int i = 0; i <= 17; i++) {
                setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
            }
            for (int i = 45; i <= 53; i++) {
                setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
            }
            setItem(4, new ItemBuilder(modus.getItem(), 1, 0).displayName(modus.getName()).create());

            int i = 17;
            for (String server : servers.split(";")) {
                i++;
                if (i > 45) break;

                String[] data = server.split(":");
                if (data.length < 2) continue;

                setItem(i, new ItemBuilder(Material.EMERALD, 1, 0).displayName("§f§l"+data[0]).lore("§7"+data[1]+" Spieler online", "", "§8» §c§nRechtsklick§8 | §7§oJoinen").create(), e -> {
                    CoreSystem.getInstance().getChannelHandler().sendPluginMessage(p, "CONNECT", data[0]);
                    p.closeInventory();
                });

                openInventory();
            }
        }, "SERVERS", modus.toString());
    }

}
