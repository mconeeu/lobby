/*
 * Copyright (c) 2017 - 2018 Dominik L., Rufus Maiwald, BamDev and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.inventory;

import eu.mcone.coresystem.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.bukkit.util.ItemFactory;
import eu.mcone.lobby.Lobby;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class GadgetsInventory extends CoreInventory {

    public GadgetsInventory(Player p) {
        super("§8» §3Lobby Gadgets", p, 9, Option.FILL_EMPTY_SLOTS);

        setItem(2, ItemFactory.createItem(Material.GOLD_HELMET, 0, 1, "§8» §5Hüte", true), () -> {
            new HatInventory(p);
            p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
        });
        setItem(4, ItemFactory.createItem(Material.GOLD_BOOTS, 0, 1, "§8» §3Trails", true), () -> {
            new TrailsInventory(p);
            p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
        });
        setItem(6, ItemFactory.createItem(Material.GOLD_INGOT, 0, 1, "§8» §bGadgets", true), () -> {
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
            p.closeInventory();
            p.sendMessage(Lobby.config.getConfigValue("System-Prefix") + "§2Gib uns Ideen für Gadgets! §8- §7TeamSpeak: §fmcone.eu");
        });

        openInventory();
    }

}
