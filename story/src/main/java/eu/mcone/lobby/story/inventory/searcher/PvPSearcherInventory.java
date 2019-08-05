/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.story.inventory.searcher;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.gamesystem.api.game.player.GamePlayer;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.enums.Item;
import eu.mcone.lobby.api.player.LobbyPlayer;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;

class PvPSearcherInventory extends CoreInventory {

    PvPSearcherInventory(Player p) {
        super("§8» §f§lForscher §8| §fKampf Forschung", p, InventorySlot.ROW_6);
        GamePlayer lp = LobbyPlugin.getInstance().getGamePlayer(p.getUniqueId());
        
        setItem(1, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
        setItem(2, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
        setItem(3, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
        setItem(4, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
        setItem(5, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
        setItem(6, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
        setItem(7, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
        setItem(8, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());

        setItem(10, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
        setItem(17, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());

        setItem(19, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());

        setItem(26, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
        setItem(28, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
        setItem(35, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
        setItem(37, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());

        setItem(44, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());

        setItem(46, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
        setItem(47, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
        setItem(48, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
        setItem(49, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
        setItem(50, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
        setItem(51, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());


        setItem(51, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
        setItem(52, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());
        setItem(53, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).displayName("§8//§oMCONE§8//").create());

        //SWORD

        if (!lp.hasItem(Item.IRON_SWORD)) {
            setItem(InventorySlot.ROW_2_SLOT_4, Item.IRON_SWORD.getItemStack(), e -> {
                if (lp.hasItem(Item.MATERIAL_IRON_4)) {
                    lp.removeItem(Item.MATERIAL_IRON_4);
                    lp.addItem(Item.IRON_SWORD);

                    p.closeInventory();
                    p.sendMessage("§7Du hast das Item Iron Head gekauft");
                } else {
                    p.closeInventory();
                    p.sendMessage("§4Du hast nicht genügend Materielien um dieses zu erforschen");
                }
            });
        }











        //ARMOR

        if (!lp.hasItem(Item.IRON_HEAD)) {

            setItem(InventorySlot.ROW_2_SLOT_3, Item.IRON_HEAD.getItemStack(), e -> {

                if (lp.hasItem(Item.MATERIAL_IRON_2)) {

                    lp.removeItem(Item.MATERIAL_IRON_2);

                    lp.addItem(Item.IRON_HEAD);

                    p.closeInventory();
                    p.sendMessage("§7Du hast das Item Iron Head gekauft");

                } else {
                    p.closeInventory();
                    p.sendMessage("§7Du hast nicht genügend Materielien um dieses zu erforschen");
                }
            });
        }


        if (!lp.hasItem(Item.IRON_CHESTPLATE)) {

            setItem(InventorySlot.ROW_3_SLOT_3, Item.IRON_CHESTPLATE.getItemStack(), e -> {

                if (lp.hasItem(Item.MATERIAL_IRON_6)) {

                    lp.removeItem(Item.MATERIAL_IRON_6);

                    lp.addItem(Item.IRON_CHESTPLATE);

                    p.closeInventory();
                    p.sendMessage("§7Du hast das Item Iron Chestplate gekauft");

                } else {
                    p.closeInventory();
                    p.sendMessage("§7Du hast nicht genügend Materielien um dieses zu erforschen");
                }
            });
        }

        if (!lp.hasItem(Item.IRON_LEGGINS)) {

            setItem(InventorySlot.ROW_4_SLOT_3, Item.IRON_LEGGINS.getItemStack(), e -> {


                if (lp.hasItem(Item.MATERIAL_IRON_4)) {

                    lp.removeItem(Item.MATERIAL_IRON_4);

                    lp.addItem(Item.IRON_LEGGINS);

                    p.closeInventory();
                    p.sendMessage("§7Du hast das Item Iron Hose gekauft");

                } else {
                    p.closeInventory();
                    p.sendMessage("§7Du hast nicht genügend Materielien um dieses zu erforschen");
                }
            });
        }

        if (!lp.hasItem(Item.IRON_BOOTS)) {

            setItem(InventorySlot.ROW_5_SLOT_3, Item.IRON_BOOTS.getItemStack(), e -> {

                if (lp.hasItem(Item.MATERIAL_IRON_4)) {

                    lp.removeItem(Item.MATERIAL_IRON_4);

                    lp.addItem(Item.IRON_BOOTS);

                    p.closeInventory();
                    p.sendMessage("§7Du hast das Item Iron Schuhe gekauft");

                } else {
                    p.closeInventory();
                    p.sendMessage("§7Du hast nicht genügend Materielien um dieses zu erforschen");
                }
            });
        }


        setItem(InventorySlot.ROW_1_SLOT_1, new ItemBuilder(Material.PRISMARINE_CRYSTALS, 1, 0).displayName("§c§lFinanzen Forschungen").lore("§7§oForsche hier im Finatz Thema.", "§7§oDie Items stehen dir danach in", "§7§odeinem Rucksack zur Verfügung.").create(),
                e -> {

                    p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
                    new SearcherInventory(p);
                });


        setItem(InventorySlot.ROW_2_SLOT_1, new ItemBuilder(Material.DIAMOND_SWORD, 1, 0).displayName("§c§lKampf Forschungen").lore("§7§oForsche hier im PvP Thema.", "§7§oDie Items stehen dir danach in", "§7§odeinem Rucksack zur Verfügung.").enchantment(Enchantment.DAMAGE_ALL, 5).unbreakable(true).itemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE).create(),
        e -> {

            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1, 1);

        });


        openInventory();
    }
}

