/*
 * Copyright (c) 2017 - 2018 Dominik L., Rufus Maiwald, BamDev and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.inventory;

import eu.mcone.coresystem.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.bukkit.util.ItemBuilder;
import eu.mcone.lobby.Lobby;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class GadgetsInventory extends CoreInventory {

    public GadgetsInventory(Player p) {
        super("§8» §3Lobby Gadgets", p, 9, Option.FILL_EMPTY_SLOTS);

        setItem(2, new ItemBuilder(Material.GOLD_HELMET, 1, 0).displayName("§8» §5Hüte").create(), () -> {
            new HatInventory(p);
            p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
        });
        setItem(4, new ItemBuilder(Material.GOLD_BOOTS, 1, 0).displayName("§8» §3Trails").create(), () -> {
            new TrailsInventory(p);
            p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
        });
        setItem(6, new ItemBuilder(Material.GOLD_INGOT, 1, 0).displayName("§8» §bGadgets").create(), () -> {
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
            p.closeInventory();
            p.sendMessage(Lobby.config.getConfigValue("System-Prefix") + "§2Gib uns Ideen für Gadgets! §8- §7TeamSpeak: §fmcone.eu");
        });

        openInventory();
    }

}
